package xyz.farshad.vocab.data.repository

import xyz.farshad.vocab.data.api.SelectionApi

/**
 * Created by Farshad Ahangari on 8/12/21.
 * farshad.ahg@gmail.com
 */
class SelectionRepository(
    private val api: SelectionApi,
) {

    suspend fun add(courseId: String) = api.add(courseId)
}