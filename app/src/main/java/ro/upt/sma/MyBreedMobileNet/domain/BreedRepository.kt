package ro.upt.sma.MyBreedMobileNet.domain

interface BreedRepository {
    fun getAll() : List<Recognized>
    fun addBreed(breed: Recognized)
    fun removeBreed(breed: Recognized)
}