package xyz.farshad.vocab.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import xyz.farshad.vocab.data.model.Course

@Dao
interface CourseDao {
    @Query("SELECT * FROM Course")
    suspend fun getAll(): List<Course>

    @Insert
    suspend fun insertAll(vararg courses: Course)

    @Delete
    suspend fun delete(course: Course)
}