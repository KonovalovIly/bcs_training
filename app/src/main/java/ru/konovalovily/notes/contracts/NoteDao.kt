package ru.konovalovily.notes.contracts

import androidx.room.*
import ru.konovalovily.notes.NoteModel

@Dao
interface NoteDao {

    @Query("SELECT * FROM notes")
    fun getNotes(): List<NoteModel>

    @Query("SELECT * FROM notes WHERE id=(:id)")
    fun getNote(id: Long): NoteModel

    @Insert
    fun insert(note: NoteModel)

    @Update
    fun updateNote(note: NoteModel)

    @Query("DELETE FROM notes WHERE id=(:id)")
    fun deleteNote(id: Long)

}