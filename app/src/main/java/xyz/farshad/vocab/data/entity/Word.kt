package xyz.farshad.vocab.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


/**
 * Created by farshad on 9/29/15.
 */
@Entity
data class Word(
    @PrimaryKey val id: Int?,
    var serverId: String?,
    var version: Int?,
    val title: String,
    val translate: String,
    val example: String? = null,
    @ColumnInfo(name = "is_favorite") var isFavorite: Boolean = false,
    @ColumnInfo(name = "chapter_id") val chapterId: Int,
    @ColumnInfo(name = "view_count") val viewCount: Int?,
)
