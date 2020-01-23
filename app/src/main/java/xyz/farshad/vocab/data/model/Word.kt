package xyz.farshad.vocab.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey


/**
 * Created by farshad on 9/29/15.
 */
@Entity
data class Word(
        @PrimaryKey val id: Int,
        val name: String,
        val meaning: String,
        val example: String,
        val translate: String,
        val levelId: Int,
        val viewCount: Int,
        val isFavorite: Boolean
)
