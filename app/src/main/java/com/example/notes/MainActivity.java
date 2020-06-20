package com.example.notes;

import android.content.Intent;
import android.os.Bundle;
import com.example.notes.adapters.NotesAdapters;
import com.example.notes.models.Note;
import com.example.notes.models.NoteDAO;
import com.example.notes.models.NoteRoomDatabase;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;


import java.util.ArrayList;
import java.util.List;
import java.util.function.UnaryOperator;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    NotesAdapters adapters;
    ArrayList<Note> notes = new ArrayList<>();
    NoteDAO dao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);



        recyclerView=findViewById(R.id.rv_notes);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapters = new NotesAdapters(this , notes);
        recyclerView.setAdapter(adapters);

        dao = NoteRoomDatabase.getDatabase(this).noteDAO();


        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, EditNoteActivity.class);
                startActivity(intent);
            }
        });
    }

    private void loadNotes() {

        List<Note> notesList = dao.getAllNotes();
        this.notes.clear();
        this.notes.addAll(notesList);
        adapters.notifyDataSetChanged();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onResume() {
        loadNotes();
        super.onResume();
    }
}