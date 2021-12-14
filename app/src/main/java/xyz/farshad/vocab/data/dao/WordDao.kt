package xyz.farshad.vocab.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import xyz.farshad.vocab.data.model.Word

@Dao
interface WordDao {
    @Query("SELECT * FROM word")
    suspend fun getAll(): List<Word>

    @Query("SELECT * FROM word where level_id = :levelId")
    suspend fun findByLevelId(levelId : Int): List<Word>

    @Query("SELECT * FROM word where is_favorite = :isFavorite")
    suspend fun fetchReviewWords(isFavorite: Boolean): List<Word>?

    @Insert
    suspend fun insertAll(word: List<Word>)

    @Delete
    fun delete(word: Word)
    @Query("delete from word")
    suspend fun deleteAll()
}