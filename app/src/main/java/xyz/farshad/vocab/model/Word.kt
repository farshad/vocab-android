package xyz.farshad.vocab.model

import com.orm.SugarRecord

/**
 * Created by farshad on 9/29/15.
 */
class Word : SugarRecord {
    val name: String
    val meaning: String
    val example: String
    val translate: String
    val levelId: Int
    private val viewCount: Int
    val isFavorite: Boolean

    constructor() {}

    constructor(name: String, meaning: String, example: String, translate: String, levelId: Int, viewCount: Int, favorite: Boolean) {
        this.name = name
        this.meaning = meaning
        this.example = example
        this.translate = translate
        this.levelId = levelId
        this.viewCount = viewCount
        this.isFavorite = favorite
    }

    fun getviewCount(): Int {
        return viewCount
    }
}
