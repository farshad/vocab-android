package xyz.farshad.vocab.data.repository

import xyz.farshad.vocab.data.api.SyncApi
import xyz.farshad.vocab.data.model.Sync

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