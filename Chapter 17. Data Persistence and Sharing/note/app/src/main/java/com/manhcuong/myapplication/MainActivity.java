package com.manhcuong.myapplication;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private JSONSerializer mSerializer;
//    private List<Note> noteList = new ArrayList<>();
    private List<Note> noteList;
    private RecyclerView recyclerView;
    private NoteAdapter mAdapter;
    private boolean mShowDividers;
    private SharedPreferences mPrefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogNewNote dialog = new DialogNewNote();
                dialog.show(getSupportFragmentManager(), "");

            }
        });

        mSerializer = new JSONSerializer("NoteToSelf.json", getApplicationContext());

        try {
            noteList = mSerializer.load();
        } catch (Exception e) {
            noteList = new ArrayList<>();
            Log.d("DEBUG", String.format("Error loading notes: %s", e));
        }

        recyclerView = findViewById(R.id.recyclerView);
        mAdapter = new NoteAdapter(this, noteList);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());

        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        // set the adapter
        recyclerView.setAdapter(mAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            Intent intent = new Intent(this, SettingsActivity.class);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void createNewNote(Note n){
        noteList.add(n);
        mAdapter.notifyDataSetChanged();
    }

    public void showNote(int noteToShow){
        DialogShowNote dialog = new DialogShowNote();
        dialog.sendNoteSelected(noteList.get(noteToShow));
        dialog.show(getSupportFragmentManager(), "");
    }

    @Override
    protected void onResume() {
        super.onResume();
        mPrefs = getSharedPreferences("Note to self", MODE_PRIVATE);
        mShowDividers = mPrefs.getBoolean("dividers", true);

        if (mShowDividers) {
            recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        } else {
            if (recyclerView.getItemDecorationCount() > 0) {
                recyclerView.removeItemDecorationAt(0);
            }
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        saveNotes();
    }

    public void saveNotes() {
        try {
            mSerializer.save(noteList);
        } catch (Exception e) {
            Log.d("DEBUG", String.format("Error saving notes: %s", e));
        }
    }
}