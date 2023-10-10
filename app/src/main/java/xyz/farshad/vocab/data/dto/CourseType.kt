package xyz.farshad.vocab.data.dto

import java.io.Serializable

data class CourseType(
    var id: String? = null,
    var version: Int? = null,
    var title: String? = null,
    var description: String? = null,
) : Serializable
