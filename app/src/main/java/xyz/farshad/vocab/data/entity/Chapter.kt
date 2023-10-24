package xyz.farshad.vocab.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Created by farshad on 7/14/16.
 */
@Entity
data class Chapter(
        @PrimaryKey val id: Int?,
        var serverId: String?,
        var version: Int?,
        var title: String,
        @ColumnInfo(name = "course_id") var courseId: Int = 0,
)
