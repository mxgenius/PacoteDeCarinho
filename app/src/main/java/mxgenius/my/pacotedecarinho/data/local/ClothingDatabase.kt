package mxgenius.my.pacotedecarinho.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import mxgenius.my.pacotedecarinho.data.local.dao.ClothingDao
import mxgenius.my.pacotedecarinho.data.local.dao.ClothingRemoteKeysDao
import mxgenius.my.pacotedecarinho.domain.model.Clothing
import mxgenius.my.pacotedecarinho.domain.model.ClothingRemoteKeys

@Database(entities = [Clothing::class, ClothingRemoteKeys::class], version = 1)
@TypeConverters(DatabaseConverter::class)
abstract class ClothingDatabase : RoomDatabase() {

    companion object {
        fun create(context: Context, useInMemory: Boolean): ClothingDatabase {
            val databaseBuilder = if (useInMemory) {
                Room.inMemoryDatabaseBuilder(context, ClothingDatabase::class.java)
            } else {
                Room.databaseBuilder(context, ClothingDatabase::class.java, "test_database.db")
            }
            return databaseBuilder
                .fallbackToDestructiveMigration()
                .build()
        }

    }

    abstract fun clothingDao(): ClothingDao
    abstract fun clothingRemoteKeysDao(): ClothingRemoteKeysDao
}