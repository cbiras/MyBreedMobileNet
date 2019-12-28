package ro.upt.sma.tensorflowlitedemo

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.view_home.*

class HistoryActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.view_home)
        fab_add.setOnClickListener { composeChiuit() }
    }

    private fun composeChiuit(){
        intent = Intent(this, CaptureActivity::class.java)

        startActivityForResult(intent, COMPOSE_REQUEST_CODE)
    }

    companion object {
        const val COMPOSE_REQUEST_CODE = 6969
    }
}