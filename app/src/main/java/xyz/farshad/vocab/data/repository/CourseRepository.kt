package xyz.farshad.vocab.data.repository

import xyz.farshad.vocab.data.api.CourseApi
import xyz.farshad.vocab.data.dao.CourseDao
import xyz.farshad.vocab.data.entity.Course

/**
 * Created by Farshad Ahangari on 8/12/21.
 * farshad.ahg@gmail.com
 */
class CourseRepository(
    private val api: CourseApi,
    private val dao: CourseDao,
) {

    suspend fun getCourses() = api.getCourses()

    suspend fun fetchAll() = dao.fetchAll()

    suspend fun insertAll(courses: List<Course>) {
        dao.insertAll(courses)
    }

    suspend fun deleteAll() {
        dao.deleteAll()
    }
}