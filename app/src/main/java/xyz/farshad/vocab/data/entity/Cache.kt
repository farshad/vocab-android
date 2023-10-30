package xyz.farshad.vocab.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "local_cache")
data class Cache(
    @PrimaryKey
    val key: String,
    val value: String
)