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

    suspend fun getById(id: String) = api.getById(id)

    suspend fun searchByTitle(query: String) = dao.searchByTitle(query)

    suspend fun getAll() = api.getAll()

    suspend fun fetchAll() = dao.fetchAll()

    suspend fun insert(course: Course) = dao.insert(course)

    suspend fun insertAll(courses: List<Course>) {
        dao.insertAll(courses)
    }

    suspend fun deleteAll() {
        dao.deleteAll()
    }
}