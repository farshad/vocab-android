package xyz.farshad.vocab.data.repository

import xyz.farshad.vocab.data.dao.CacheDao
import xyz.farshad.vocab.data.entity.Cache

/**
 * Created by Farshad Ahangari on 8/12/21.
 * farshad.ahg@gmail.com
 */
class CacheRepository(
    private val dao: CacheDao,
) {

    suspend fun insert(cache: Cache) {
        return dao.insert(cache)
    }

    suspend fun findByKey(key: String): Cache? {
        return dao.findByKey(key)
    }

    suspend fun delete(key: String) {
        dao.delete(key)
    }
}