package xyz.farshad.vocab.data.repository

import xyz.farshad.vocab.data.dao.WordDao
import xyz.farshad.vocab.data.model.Word

/**
 * Created by Farshad Ahangari on 8/12/21.
 * farshad.ahg@gmail.com
 */
class WordRepository(
    private val dao: WordDao
) {

    suspend fun findByLevelId(levelId: Int): List<Word> {
        return dao.findByLevelId(levelId)
    }

    suspend fun insertAll(words: List<Word>) {
        dao.insertAll(words)
    }

    suspend fun deleteAll() {
        dao.deleteAll()
    }
}