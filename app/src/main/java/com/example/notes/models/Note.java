package com.example.notes.models;


import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "note_table")
public class Note   {

    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "note")
    private String note;

    @ColumnInfo(name = "date")
    private String date;

    public Note(String note, String date) {
        this.note = note;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
