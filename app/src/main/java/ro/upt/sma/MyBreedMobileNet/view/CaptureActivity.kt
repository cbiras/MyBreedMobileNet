package ro.upt.sma.MyBreedMobileNet.view

import android.Manifest
import android.app.Activity
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
import android.os.Handler
import ro.upt.sma.MyBreedMobileNet.Classifier
import ro.upt.sma.MyBreedMobileNet.R
import ro.upt.sma.MyBreedMobileNet.TFLiteClassifier


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

        classifier = TFLiteClassifier.create(
            assets,
            MODEL_PATH,
            LABEL_PATH,
            INPUT_SIZE
        )

        fotoapparat = Fotoapparat(
            context = this,
            view = cmv_camera_preview
        )

        cmv_camera_preview.setOnClickListener { view ->
            GlobalScope.launch((Dispatchers.Default)) {
                val bitmap = fotoapparat.takePicture().toBitmap().await().bitmap


                val b = Bitmap.createScaledBitmap(bitmap,INPUT_SIZE,INPUT_SIZE,false)

                val recognition = classifier.recognize(b)
                withContext(Dispatchers.Main) {
                    Toast.makeText(c,recognition[0].label,Toast.LENGTH_SHORT).show()
                    val returnIntent = Intent().apply {
                        val content = recognition[0].label
                        putExtra(EXTRA_TEXT, content)
                    }
                    setResult(Activity.RESULT_OK, returnIntent)

                    val handler = Handler()
                    handler.postDelayed(Runnable {
                        // Do something after 5s = 5000ms
                        finish()
                    }, 1300)


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

        startActivityForResult(intent,
            HistoryActivity.COMPOSE_REQUEST_CODE
        )

    }

    companion object {
        const val EXTRA_TEXT = "extra_text"
    }
}
