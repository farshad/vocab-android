package xyz.farshad.vocab.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import xyz.farshad.vocab.data.model.Chapter

@Dao
interface ChapterDao {
    @Query("SELECT * FROM chapter")
    fun getAll(): List<Chapter>

    @Query("SELECT * FROM chapter where course_id = :courseId")
    suspend fun findByCourseId(courseId: String): List<Chapter>

    @Insert
    suspend fun insertAll(chapter: List<Chapter>)

    @Delete
    fun delete(chapter: Chapter)

    @Query("delete from chapter")
    suspend fun deleteAll()
}