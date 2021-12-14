package xyz.farshad.vocab.data.repository

import retrofit2.Response
import xyz.farshad.vocab.data.api.SyncApi
import xyz.farshad.vocab.data.dao.CourseDao
import xyz.farshad.vocab.data.dao.LevelDao
import xyz.farshad.vocab.data.dao.WordDao
import xyz.farshad.vocab.data.model.Course
import xyz.farshad.vocab.data.model.Level
import xyz.farshad.vocab.data.model.Sync
import xyz.farshad.vocab.data.model.Word

/**
 * Created by Farshad Ahangari on 8/12/21.
 * farshad.ahg@gmail.com
 */
class SyncRepository(
    private val api: SyncApi
) {

    suspend fun get(): Sync {
        return api.get()
    }
}