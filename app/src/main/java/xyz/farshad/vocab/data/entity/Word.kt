package xyz.farshad.vocab.data.entity

import android.os.Parcel
import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable


/**
 * Created by farshad on 9/29/15.
 */
@Entity
data class Word(
    @PrimaryKey val id: Int?,
    var serverId: String?,
    var version: Int?,
    val title: String,
    val translate: String,
    val example: String? = null,
    @ColumnInfo(name = "is_favorite") var isFavorite: Boolean = false,
    @ColumnInfo(name = "chapter_id") val chapterId: Int,
    @ColumnInfo(name = "view_count") val viewCount: Int?,
) : Parcelable, Serializable {

    constructor(parcel: Parcel) : this(
        parcel.readValue(Int::class.java.classLoader) as? Int,
        parcel.readString(),
        parcel.readValue(Int::class.java.classLoader) as? Int,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString(),
        parcel.readByte() != 0.toByte(),
        parcel.readInt(),
        parcel.readValue(Int::class.java.classLoader) as? Int
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeValue(id)
        parcel.writeString(serverId)
        parcel.writeValue(version)
        parcel.writeString(title)
        parcel.writeString(translate)
        parcel.writeString(example)
        parcel.writeByte(if (isFavorite) 1 else 0)
        parcel.writeInt(chapterId)
        parcel.writeValue(viewCount)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Word> {
        override fun createFromParcel(parcel: Parcel): Word {
            return Word(parcel)
        }

        override fun newArray(size: Int): Array<Word?> {
            return arrayOfNulls(size)
        }
    }
}