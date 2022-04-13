package mxgenius.my.pacotedecarinho.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import mxgenius.my.pacotedecarinho.util.Constants.CLOTHING_REMOTE_KEYS_DATABASE_TABLE

@Entity(tableName = CLOTHING_REMOTE_KEYS_DATABASE_TABLE)
data class ClothingRemoteKeys(
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    val prevPage: Int?,
    val nextPage: Int?,
    val lastUpdated: Long?
)