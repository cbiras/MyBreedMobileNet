package ro.upt.sma.MyBreedMobileNet.database

import ro.upt.sma.MyBreedMobileNet.domain.BreedRepository
import ro.upt.sma.MyBreedMobileNet.domain.Recognized

class BreedDbStore(private val appDatabase: AppDatabase) : BreedRepository {
    override fun getAll(): List<Recognized> {
        return appDatabase.breedDao().getAll().map { it.toDomainModel() }
    }

    override fun addBreed(breed: Recognized) {
        appDatabase.breedDao().insert(breed.toDbModel())
    }

    override fun removeBreed(breed: Recognized) {
        appDatabase.breedDao().delete(breed.toDbModel())
    }


    private fun Recognized.toDbModel() = BreedEntity(timestamp, description)

    private fun BreedEntity.toDomainModel() = Recognized(timestamp, description)
}