package xyz.farshad.vocab.data.repository

import xyz.farshad.vocab.data.dao.WordDao
import xyz.farshad.vocab.data.entity.Word

/**
 * Created by Farshad Ahangari on 8/12/21.
 * farshad.ahg@gmail.com
 */
class WordRepository(
    private val dao: WordDao
) {

    suspend fun findByChapterId(levelId: Int): List<Word> {
        return dao.findByChapterId(levelId)
    }

    suspend fun insert(word: Word) = dao.insert(word)

    suspend fun insertAll(words: List<Word>) {
        dao.insertAll(words)
    }

    suspend fun deleteAll() {
        dao.deleteAll()
    }

    suspend fun fetchReviewWords(): List<Word>? {
        return dao.fetchReviewWords()
    }

    suspend fun update(word: Word) {
        dao.update(word)
    }
}