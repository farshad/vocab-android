package xyz.farshad.vocab.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import xyz.farshad.vocab.data.model.Course

@Dao
interface CourseDao {
    @Query("SELECT * FROM Course")
    suspend fun fetchAll(): List<Course>

    @Insert
    suspend fun insertAll(courses: List<Course>)

    @Delete
    suspend fun delete(course: Course)

    @Query("delete from course")
    suspend fun deleteAll()
}