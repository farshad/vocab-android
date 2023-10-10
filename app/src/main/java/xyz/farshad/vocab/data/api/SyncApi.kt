package xyz.farshad.vocab.data.api

import retrofit2.http.POST
import xyz.farshad.vocab.data.entity.Sync

interface SyncApi {
    @POST("api/retrieve")
    suspend fun get(): Sync
}