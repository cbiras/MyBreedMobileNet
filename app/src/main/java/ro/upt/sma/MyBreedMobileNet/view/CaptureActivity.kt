package ro.upt.sma.MyBreedMobileNet.view

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap

import android.os.Bundle

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
//import ro.upt.sma.MyBreedMobileNet.R
import ro.upt.sma.MyBreedMobileNet.TFLiteClassifier
import android.view.MotionEvent
import android.view.View.OnTouchListener
import android.view.Gravity

import android.view.LayoutInflater

import androidx.core.content.ContextCompat.getSystemService
import android.icu.lang.UCharacter.GraphemeClusterBreak.T

import android.content.Context
import android.view.View
import android.widget.*
import io.fotoapparat.view.CameraView
import kotlinx.android.synthetic.main.popup_window.*
import kotlinx.android.synthetic.main.popup_window.view.*

import android.widget.TextView
import androidx.core.app.ComponentActivity
import androidx.core.app.ComponentActivity.ExtraData
import androidx.core.content.ContextCompat.getSystemService
import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import android.R




class CaptureActivity : AppCompatActivity() {

    private val MODEL_PATH = "model.tflite"
    private val LABEL_PATH = "labels_doggo.txt"

    private val INPUT_SIZE = 224

    private lateinit var classifier: Classifier

    private lateinit var fotoapparat: Fotoapparat

    private val c = this

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(ro.upt.sma.MyBreedMobileNet.R.layout.activity_capture)

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


                    onRecognitionShowPopupWindowClick(view,"The breed is:  "+recognition[0].label)
                    val returnIntent = Intent().apply {
                        val content = recognition[0].label
                        putExtra(EXTRA_TEXT, content)
                    }
                    setResult(Activity.RESULT_OK, returnIntent)

                    val handler = Handler()
                    handler.postDelayed(Runnable {
                        finish()
                    }, 2300)


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
    fun onRecognitionShowPopupWindowClick(view: View,s:String) {


        val inflater = getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val popupView = inflater.inflate(ro.upt.sma.MyBreedMobileNet.R.layout.popup_window, null)

        val width = LinearLayout.LayoutParams.WRAP_CONTENT
        val height = LinearLayout.LayoutParams.WRAP_CONTENT
        val focusable = true
        val popupWindow = PopupWindow(popupView, width, height, focusable)


        popupWindow.showAtLocation(view, Gravity.CENTER, 0, 100)
        popupWindow.contentView.findViewById<TextView>(ro.upt.sma.MyBreedMobileNet.R.id.popup_text).text = s

        popupView.setOnTouchListener(object : View.OnTouchListener {
            override fun onTouch(v: View, event: MotionEvent): Boolean {
                popupWindow.dismiss()
                return true
            }
        })
    }

    companion object {
        const val EXTRA_TEXT = "extra_text"
    }
}
