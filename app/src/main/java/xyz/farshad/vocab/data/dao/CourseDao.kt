package xyz.farshad.vocab.data.dao

import androidx.room.*
import xyz.farshad.vocab.data.entity.Course

@Dao
interface CourseDao {
    @Query("SELECT * FROM Course")
    suspend fun fetchAll(): List<Course>

    @Insert
    suspend fun insertAll(courses: List<Course>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(course: Course): Long

    @Transaction
    @Query("SELECT * FROM Course WHERE title LIKE '%' || :searchQuery || '%'")
    suspend fun searchByTitle(searchQuery: String): List<Course>?

    @Delete
    suspend fun delete(course: Course)

    @Query("delete from course")
    suspend fun deleteAll()
}