package xyz.farshad.vocab.data.dao

import androidx.room.*
import xyz.farshad.vocab.data.entity.Chapter

@Dao
interface ChapterDao {
    @Query("SELECT * FROM chapter")
    fun getAll(): List<Chapter>

    @Query("SELECT * FROM chapter where course_id = :courseId")
    suspend fun findByCourseId(courseId: Int): List<Chapter>

    @Insert
    suspend fun insertAll(chapter: List<Chapter>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(chapter: Chapter): Long

    @Delete
    fun delete(chapter: Chapter)

    @Query("delete from chapter")
    suspend fun deleteAll()
}