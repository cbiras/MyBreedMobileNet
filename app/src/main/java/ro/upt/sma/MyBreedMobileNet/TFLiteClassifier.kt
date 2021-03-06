package ro.upt.sma.MyBreedMobileNet

import android.annotation.SuppressLint
import android.content.res.AssetManager
import android.graphics.Bitmap
import org.tensorflow.lite.Interpreter
import java.io.*
import java.nio.ByteBuffer
import java.nio.ByteOrder
import java.nio.MappedByteBuffer
import java.nio.channels.FileChannel


class TFLiteClassifier(private val inputSize: Int) : Classifier {

    private val MAX_RESULTS = 5
    private val BATCH_SIZE = 1
    private val PIXEL_SIZE = 3
    private val THRESHOLD = 0.01f
    private val IMAGE_MEAN = 128;
    private val IMAGE_STD = 128.0f;

    private lateinit var interpreter: Interpreter
    private lateinit var labelList: List<String>

    private val byteBuffer =
        ByteBuffer.allocateDirect(4*BATCH_SIZE * inputSize * inputSize * PIXEL_SIZE)

    init {
        byteBuffer.order(ByteOrder.nativeOrder())
    }

    companion object {
        @Throws(IOException::class)
        fun create(
            assetManager: AssetManager,
            modelPath: String,
            labelPath: String,
            inputSize: Int
        ): Classifier {

            val classifier = TFLiteClassifier(inputSize)
            classifier.interpreter = Interpreter(classifier.loadModelFile(assetManager, modelPath))
            classifier.labelList = classifier.loadLabels(assetManager, labelPath)

            return classifier
        }
    }

    override fun recognize(bitmap: Bitmap): List<Recognition> {


        val ba = convertBitmapToByteBuffer(bitmap)
        val result = Array(1) { FloatArray(labelList.size) }

        interpreter.run(ba,result)

        return getSortedResult(result)
    }

    @Throws(IOException::class)
    private fun loadModelFile(assetManager: AssetManager, modelPath: String): MappedByteBuffer {
        val fileDescriptor = assetManager.openFd(modelPath)
        val inputStream = FileInputStream(fileDescriptor.fileDescriptor)
        val fileChannel = inputStream.channel
        val startOffset = fileDescriptor.startOffset
        val declaredLength = fileDescriptor.declaredLength

        return fileChannel.map(FileChannel.MapMode.READ_ONLY, startOffset, declaredLength)
    }

    @Throws(IOException::class)
    private fun loadLabels(assetManager: AssetManager, labelPath: String): List<String> {
        val labels = mutableListOf<String>()

        val reader = BufferedReader(InputStreamReader(assetManager.open(labelPath)))

        var line: String? = reader.readLine()
        while (!line.isNullOrEmpty()) {
            labels.add(line)
            line = reader.readLine()
        }

        reader.close()

        return labels
    }

    private fun convertBitmapToByteBuffer(bitmap: Bitmap): ByteBuffer {
        byteBuffer.clear()

        val intValues = IntArray(inputSize * inputSize)

        bitmap.getPixels(intValues, 0, bitmap.width, 0, 0, bitmap.width, bitmap.height)

        var pixel = 0
        for (i in 0 until inputSize) {
            for (j in 0 until inputSize) {
                val value = intValues[pixel++]
                byteBuffer.putFloat(((value shr 16 and 0xFF) - IMAGE_MEAN) / IMAGE_STD)
                byteBuffer.putFloat(((value shr 8 and 0xFF) - IMAGE_MEAN) / IMAGE_STD)
                byteBuffer.putFloat(((value and 0xFF) - IMAGE_MEAN) / IMAGE_STD)
            }
        }

        return byteBuffer
    }

    @SuppressLint("DefaultLocale")
    private fun getSortedResult(labelProbArray: Array<FloatArray>): List<Recognition> {
        return labelProbArray[0]
            .mapIndexed { index, bytes ->
                val confidence = bytes/ 255.0f
                val label = if (index < labelList.size) labelList[index] else "UFO"
                Recognition(index.toString(), label, confidence)
            }
            .filter { it.confidence < THRESHOLD }
            .sortedByDescending { it.confidence }
            .take(MAX_RESULTS)
    }


}