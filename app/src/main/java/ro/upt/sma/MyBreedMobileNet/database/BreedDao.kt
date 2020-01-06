package ro.upt.sma.MyBreedMobileNet.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query


@Dao
interface BreedDao {
    @Query("SELECT * FROM breeds")
    fun getAll(): List<BreedEntity>


    @Insert
    fun insert(vararg breed: BreedEntity)

    @Delete
    fun delete(vararg breed: BreedEntity)
}