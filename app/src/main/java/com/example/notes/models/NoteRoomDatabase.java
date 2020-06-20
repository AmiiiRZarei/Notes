package com.example.notes.models;


import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Note.class}, version = 1)
public abstract class NoteRoomDatabase extends RoomDatabase {

    public abstract NoteDAO noteDAO();
    private static  NoteRoomDatabase INSTANCE;

    public static NoteRoomDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (NoteRoomDatabase.class) {
                if (INSTANCE == null) {
// Create database here
                    INSTANCE = Room.databaseBuilder(context,NoteRoomDatabase.class,"note_database")
                            .allowMainThreadQueries()
                            .build();
                }
            }
        }
        return INSTANCE;
    }



}
