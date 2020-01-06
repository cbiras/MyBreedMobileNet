package ro.upt.sma.MyBreedMobileNet.database

import android.content.Context
import androidx.room.Room

object RoomDatabase {

    private var appDatabase: AppDatabase? = null

    fun getDb(context: Context): AppDatabase {
        if (appDatabase == null)
            appDatabase = Room.databaseBuilder(context, AppDatabase::class.java, "breed-db")
                .allowMainThreadQueries()
                .build()

        return appDatabase!!
    }


}