package ro.upt.sma.MyBreedMobileNet.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import ro.upt.sma.MyBreedMobileNet.domain.BreedRepository

class HomeViewModelFactory(private val breedRepository: BreedRepository): ViewModelProvider.NewInstanceFactory() {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return HomeViewModel(breedRepository) as T
    }
}