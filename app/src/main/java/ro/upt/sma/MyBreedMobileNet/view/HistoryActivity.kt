package ro.upt.sma.MyBreedMobileNet.view

import android.app.Activity
import android.content.Intent
import android.graphics.BitmapFactory
import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.view_home.*
import ro.upt.sma.MyBreedMobileNet.R
import ro.upt.sma.MyBreedMobileNet.database.BreedDbStore
import ro.upt.sma.MyBreedMobileNet.database.RoomDatabase
import ro.upt.sma.MyBreedMobileNet.domain.Recognized
import ro.upt.sma.MyBreedMobileNet.viewmodel.HomeViewModel
import ro.upt.sma.MyBreedMobileNet.viewmodel.HomeViewModelFactory

class HistoryActivity : AppCompatActivity() {
    private lateinit var viewModel: HomeViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.view_home)



        fab_add.setOnClickListener { goMain() }

        val factory = HomeViewModelFactory(BreedDbStore(RoomDatabase.getDb(this)))
        viewModel = ViewModelProviders.of(this, factory).get(HomeViewModel::class.java)
        initList()
    }

    private fun initList() {
        rv_breed_list.apply {
            layoutManager = LinearLayoutManager(this@HistoryActivity)
        }

        viewModel.breedsLiveData.observe(this, Observer { breed ->
            //TODO("Instantiate an adapter with the received list and assign it to recycler view")
            rv_breed_list.adapter =
                RecognizedRecyclerViewAdapter(
                    breed,
                    this::deleteRecognition
                )
        })

        viewModel.retrieveBreeds()


    }


    private fun goMain(){
        intent = Intent(this, CaptureActivity::class.java)

        startActivityForResult(intent,
            COMPOSE_REQUEST_CODE
        )

    }

    private fun deleteRecognition(breed: Recognized) {
        viewModel.removeBreed(breed)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        when (requestCode) {
            COMPOSE_REQUEST_CODE -> if (resultCode == Activity.RESULT_OK) extractText(data)
            else -> super.onActivityResult(requestCode, resultCode, data)
        }
    }

    private fun extractText(data: Intent?) {
        data?.let {
            val text = data.getStringExtra(CaptureActivity.EXTRA_TEXT)
            if (!text.isNullOrBlank()) {
                viewModel.addBreed(text)
            }
        }
    }




    companion object {
        const val COMPOSE_REQUEST_CODE = 6969
    }
}