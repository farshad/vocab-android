package xyz.farshad.vocab.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import xyz.farshad.vocab.data.model.Level

@Dao
interface LevelDao {
    @Query("SELECT * FROM level")
    fun getAll(): List<Level>

    @Query("SELECT * FROM level where course_id = :courseId")
    fun findByCourseId(courseId: Int): List<Level>

    @Insert
    fun insertAll(vararg level: Level)

    @Delete
    fun delete(level: Level)
}