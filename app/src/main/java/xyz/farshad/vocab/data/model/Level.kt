package xyz.farshad.vocab.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Created by farshad on 7/14/16.
 */
@Entity
data class Level(
        @PrimaryKey val id: Int,
        var name: String,
        var courseId: Int = 0
)
