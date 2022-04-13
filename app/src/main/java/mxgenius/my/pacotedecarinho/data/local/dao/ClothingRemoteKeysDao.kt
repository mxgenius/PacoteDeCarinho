package mxgenius.my.pacotedecarinho.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import mxgenius.my.pacotedecarinho.domain.model.ClothingRemoteKeys

@Dao
interface ClothingRemoteKeysDao {

    @Query("SELECT * FROM clothing_remote_keys_table WHERE id = :clothingId")
    suspend fun getRemoteKeys(clothingId: Int): ClothingRemoteKeys?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addAllRemoteKeys(clothingRemoteKeys: List<ClothingRemoteKeys>)

    @Query("DELETE FROM clothing_remote_keys_table")
    suspend fun deleteAllRemoteKeys()
}