package xyz.farshad.vocab.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Created by farshad on 9/29/15.
 */
@Entity
data class Course(
    @PrimaryKey val id: Int?,
    var serverId: String?,
    var version: Int?,
    var title: String,
    var description: String?,
)