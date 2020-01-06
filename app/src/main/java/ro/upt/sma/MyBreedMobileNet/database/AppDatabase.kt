package ro.upt.sma.MyBreedMobileNet.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [BreedEntity::class],version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun breedDao(): BreedDao
}