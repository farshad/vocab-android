package xyz.farshad.vocab.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import xyz.farshad.vocab.data.model.Course

@Dao
interface CourseDao {
    @Query("SELECT * FROM Course")
    fun getAll(): List<Course>

    @Insert
    fun insertAll(vararg courses: Course)

    @Delete
    fun delete(course: Course)
}