package xyz.farshad.vocab.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import xyz.farshad.vocab.data.model.Word

@Dao
interface WordDao {
    @Query("SELECT * FROM word")
    fun getAll(): List<Word>

    @Query("SELECT * FROM word where level_id = :levelId")
    fun findByLevelId(levelId : Int): List<Word>

    @Insert
    fun insertAll(vararg word: Word)

    @Delete
    fun delete(word: Word)
}