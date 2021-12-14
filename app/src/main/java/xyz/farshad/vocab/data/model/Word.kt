package xyz.farshad.vocab.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


/**
 * Created by farshad on 9/29/15.
 */
@Entity
data class Word(
        @PrimaryKey val id: Int,
        val name: String,
        val meaning: String? = null,
        val example: String? = null,
        val translate: String,
        @ColumnInfo(name = "level_id") val levelId: Int,
        @ColumnInfo(name = "view_count") val viewCount: Int,
        @ColumnInfo(name = "is_favorite") var isFavorite: Boolean
)
