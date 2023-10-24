package xyz.farshad.vocab.data.dao

import androidx.room.*
import xyz.farshad.vocab.data.entity.Word

@Dao
interface WordDao {
    @Query("SELECT * FROM word")
    suspend fun getAll(): List<Word>

    @Query("SELECT * FROM word where chapter_id = :chapterId")
    suspend fun findByChapterId(chapterId : Int): List<Word>

    @Query("SELECT * FROM word")
    suspend fun fetchReviewWords(): List<Word>?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(word: Word): Long

    @Insert
    suspend fun insertAll(word: List<Word>)

    @Update
    suspend fun update(word: Word)

    @Delete
    fun delete(word: Word)

    @Query("delete from word")
    suspend fun deleteAll()
}