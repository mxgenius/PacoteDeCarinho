package mxgenius.my.pacotedecarinho.data.local.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import mxgenius.my.pacotedecarinho.domain.model.Clothing

@Dao
interface ClothingDao {

    @Query("SELECT * FROM clothing_table ORDER BY id ASC")
    fun getAllClothing(): PagingSource<Int, Clothing>

    @Query("SELECT * FROM clothing_table ORDER BY id ASC")
    fun getPopularClothing(): PagingSource<Int, Clothing>

    @Query("SELECT * FROM clothing_table WHERE id=:clothingId")
    fun getSelectedClothing(clothingId: Int): Clothing

    @Query("SELECT * FROM clothing_table WHERE category = :category")
    fun getSelectedCategory(category: String) : Clothing

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addClothing(clothing: List<Clothing>)

    @Query("DELETE FROM clothing_table")
    suspend fun deleteAllClothing()
}