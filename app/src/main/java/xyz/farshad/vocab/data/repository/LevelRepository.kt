package xyz.farshad.vocab.data.repository

import xyz.farshad.vocab.data.dao.LevelDao
import xyz.farshad.vocab.data.model.Level

/**
 * Created by Farshad Ahangari on 8/12/21.
 * farshad.ahg@gmail.com
 */
class LevelRepository(
    private val dao: LevelDao
) {

    suspend fun findByCourseId(courseId: Int): List<Level> {
        return dao.findByCourseId(courseId)
    }

    suspend fun insertAll(levels: List<Level>) {
        dao.insertAll(levels)
    }

    suspend fun deleteAll() {
        dao.deleteAll()
    }
}