package mxgenius.my.pacotedecarinho.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.Serializable
import mxgenius.my.pacotedecarinho.util.Constants.CLOTHING_DATABASE_TABLE

@Serializable
@Entity(tableName = CLOTHING_DATABASE_TABLE)
data class Clothing(
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    val category: String,
    val name: String,
    val image: String,
    val about: String,
    val price: Double
)