package xyz.farshad.vocab.data.dto

import java.io.Serializable

data class CourseResponse(
    var id: String? = null,
    var version: Int? = null,
    var title: String? = null,
    var description: String? = null,
    var courseType: CourseType? = null,
    var chapters: List<ChapterResponse>? = null,
) : Serializable
