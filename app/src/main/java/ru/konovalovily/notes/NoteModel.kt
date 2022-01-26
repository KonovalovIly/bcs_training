package ru.konovalovily.notes


import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity(tableName = "notes")
@Parcelize
data class NoteModel(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    @ColumnInfo(name = "note_title") var title: String,
    @ColumnInfo(name = "note_text") var text: String,
    @ColumnInfo(name = "note_data") var data: String
) : Parcelable
