package ro.upt.sma.MyBreedMobileNet.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ro.upt.sma.MyBreedMobileNet.domain.BreedRepository
import ro.upt.sma.MyBreedMobileNet.domain.Recognized

class HomeViewModel(private val breedRepository: BreedRepository):ViewModel() {
    val breedsLiveData = MutableLiveData<List<Recognized>>()

    fun retrieveBreeds() {
        val chiuits = breedRepository.getAll()
        breedsLiveData.postValue(chiuits)
    }

    fun addBreed(description: String) {
        breedRepository.addBreed(Recognized(System.currentTimeMillis(), description))
        retrieveBreeds()
    }

    fun removeBreed(breed: Recognized) {
        breedRepository.removeBreed(breed)
        retrieveBreeds()
    }
}