package com.example.notes;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import com.example.notes.models.Note;
import com.example.notes.models.NoteDAO;
import com.example.notes.models.NoteRoomDatabase;

import java.util.Date;

public class EditNoteActivity extends AppCompatActivity {

    int noteId;
    EditText noteEdit;
    NoteDAO dao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_note);
        Toolbar toolbar = findViewById(R.id.toolbar_edit_note_activity);
        setSupportActionBar(toolbar);

        noteEdit = findViewById(R.id.ed_note);
        dao = NoteRoomDatabase.getDatabase(this).noteDAO();

        Intent edit = getIntent();

        if (edit.hasExtra("noteForEdit")) {
           String oldNote= edit.getStringExtra("noteForEdit");
           noteId = edit.getExtras().getInt("noteId");
           noteEdit.setText(oldNote);
        }


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.edit_note_activity_menu , menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        String newNote = noteEdit.getText().toString();
        String newDate = new Date().toString();
        int id = item.getItemId();
        if(id == R.id.save){

            if((!newNote.isEmpty()) && noteId == 0){

                Note note = new Note(newNote , newDate);
                dao.addNote(note);

            }else

                dao.updateById(noteId,newNote,newDate);

            finish();
        }

        return super.onOptionsItemSelected(item);
    }
}