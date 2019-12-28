package ro.upt.sma.tensorflowlitedemo

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap

import android.os.Bundle

import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import io.fotoapparat.Fotoapparat
import kotlinx.android.synthetic.main.activity_capture.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class CaptureActivity : AppCompatActivity() {

    private val MODEL_PATH = "model.tflite"
    private val LABEL_PATH = "labels_doggo.txt"

    private val INPUT_SIZE = 224

    private lateinit var classifier: Classifier

    private lateinit var fotoapparat: Fotoapparat

    private val c = this

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_capture)
        fab_add_main.setOnClickListener{goHistory()}

        classifier = TFLiteClassifier.create(assets, MODEL_PATH, LABEL_PATH, INPUT_SIZE)

        fotoapparat = Fotoapparat(
            context = this,
            view = cmv_camera_preview
        )

        cmv_camera_preview.setOnClickListener { view ->
            GlobalScope.launch((Dispatchers.Default)) {
                val bitmap = fotoapparat.takePicture().toBitmap().await().bitmap

                // TODO 1: Rescale the bitmap to INPUT_SIZE width and height using the Bitmap.createScaledBitmap method.
                val b = Bitmap.createScaledBitmap(bitmap,INPUT_SIZE,INPUT_SIZE,false)
                // TODO 2: Run the recognizer which will return the recognitions.
                val recognition = classifier.recognize(b)
                withContext(Dispatchers.Main) {
                    // TODO 3: Show the recognitions using the common Toast widget. Make use of joinToString method to concat multiple items.

                    Toast.makeText(c,recognition.joinToString(","){"${it.label}, ${it.confidence}"},Toast.LENGTH_LONG).show()
                }

            }
        }

        if (ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.CAMERA
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.CAMERA), 123)
        }

    }

    override fun onResume() {
        super.onResume()

        if (ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.CAMERA
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            fotoapparat.start()
        }
    }

    override fun onPause() {
        super.onPause()

        fotoapparat.stop()
    }
    fun goHistory(){
        intent = Intent(this, HistoryActivity::class.java)

        startActivityForResult(intent, HistoryActivity.COMPOSE_REQUEST_CODE)
    }

}
