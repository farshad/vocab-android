package xyz.farshad.vocab

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import xyz.farshad.vocab.model.Course

@Database(entities = [Course::class], version = 1, exportSchema = false)
abstract class SataDatabase : RoomDatabase() {

    abstract fun tokenDao(): TokenDao

    companion object {

        private var instance: SataDatabase? = null

        @Synchronized
        fun getInstance(context: Context): SataDatabase {
            if (instance == null) {
                instance = Room.databaseBuilder(context.applicationContext,
                        SataDatabase::class.java, "sata_db")
                        .fallbackToDestructiveMigration()
                        .build()
            }

            return instance
        }
    }
}
