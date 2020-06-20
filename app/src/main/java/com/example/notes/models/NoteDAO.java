package com.example.notes.models;



import android.widget.ArrayAdapter;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.ArrayList;
import java.util.List;

@Dao
public interface NoteDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void addNote(Note note);

    @Delete
    void deleteNote(Note note);

    @Update
    void UpdateNote(Note note);

    @Query("SELECT * FROM note_table ORDER BY date DESC")
    List<Note> getAllNotes();

   @Query("DELETE FROM note_table WHERE id = :id")
   void deleteById(int id);

   @Query("UPDATE note_table SET note = :note , date = :date WHERE id =:id ")
   void updateById(int id,String note , String date);













}
