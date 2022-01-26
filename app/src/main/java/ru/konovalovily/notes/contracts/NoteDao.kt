package ru.konovalovily.notes.contracts

import androidx.lifecycle.LiveData
import androidx.room.*
import ru.konovalovily.notes.NoteModel

@Dao
interface NoteDao {

    @Query("SELECT * FROM notes")
    fun getNotes(): LiveData<List<NoteModel>>

    @Query("SELECT * FROM notes WHERE id=(:id)")
    fun getNote(id: Long): LiveData<NoteModel>?

    @Insert
    fun insert(note: NoteModel)

    @Update
    fun updateNote(note: NoteModel)

    @Query("DELETE FROM notes WHERE id=(:id)")
    fun deleteNote(id: Long)

}