package xyz.farshad.vocab.data.repository

import xyz.farshad.vocab.data.dao.CourseDao
import xyz.farshad.vocab.data.model.Course

/**
 * Created by Farshad Ahangari on 8/12/21.
 * farshad.ahg@gmail.com
 */
class CourseRepository(
    private val dao: CourseDao
) {

    suspend fun fetchAll(): List<Course> {
        return dao.fetchAll()
    }

    suspend fun insertAll(courses: List<Course>) {
        dao.insertAll(courses)
    }

    suspend fun deleteAll() {
        dao.deleteAll()
    }
}