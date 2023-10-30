package xyz.farshad.vocab.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import xyz.farshad.vocab.data.entity.Cache

@Dao
interface CacheDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(cache: Cache)

    @Query("select * from local_cache where `key` = :key limit 1")
    suspend fun findByKey(key: String): Cache?

    @Query("delete from local_cache where `key` = :key")
    suspend fun delete(key: String)
}