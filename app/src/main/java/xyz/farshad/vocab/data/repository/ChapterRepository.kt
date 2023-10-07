package xyz.farshad.vocab.data.repository

import xyz.farshad.vocab.data.dao.ChapterDao
import xyz.farshad.vocab.data.model.Chapter

/**
 * Created by Farshad Ahangari on 8/12/21.
 * farshad.ahg@gmail.com
 */
class ChapterRepository(
    private val dao: ChapterDao
) {

    suspend fun findByCourseId(courseId: String): List<Chapter> {
        return dao.findByCourseId(courseId)
    }

    suspend fun insertAll(chapters: List<Chapter>) {
        dao.insertAll(chapters)
    }

    suspend fun deleteAll() {
        dao.deleteAll()
    }
}