package xyz.farshad.vocab.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Created by farshad on 7/14/16.
 */
@Entity
data class Chapter(
        @PrimaryKey val id: String,
        var name: String,
        @ColumnInfo(name = "course_id") var courseId: Int = 0
)
