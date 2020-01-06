package ro.upt.sma.MyBreedMobileNet.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "breeds")
data class BreedEntity (
    @PrimaryKey
    val timestamp: Long,

    @ColumnInfo(name = "description")
    val description: String
)