package com.example.ceepalura.ui.activity;

import static com.example.ceepalura.ui.activity.contants.Constants.NOTE_KEY;
import static com.example.ceepalura.ui.activity.contants.Constants.RESULT_CODE_CREATED_NOTE;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ceepalura.R;
import com.example.ceepalura.dao.NoteDao;
import com.example.ceepalura.model.Notes;
import com.example.ceepalura.ui.adapter.NoteListAdapter;

import java.util.List;

public class NoteListActivity extends AppCompatActivity {

    private NoteListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        List<Notes> allNotes = getNotes();
        configureRecyclerView(allNotes);

        ActivityResultLauncher<Intent> intent = getIntentActivityResultLauncher();
        configureInsertNewNoteButton(intent);
    }

    @NonNull
    private ActivityResultLauncher<Intent> getIntentActivityResultLauncher() {
        return registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if (result.getResultCode() == RESULT_CODE_CREATED_NOTE) {
                        Intent data = result.getData();
                        Notes receivedNote = (Notes) data.getSerializableExtra(NOTE_KEY);
                        adapter.addNewNote(receivedNote);
                    }
                });
    }

    private void configureInsertNewNoteButton(ActivityResultLauncher<Intent> someActivityResultLauncher) {
        TextView insertNoteButton = findViewById(R.id.insertNewNote);
        insertNoteButton.setOnClickListener(view -> {
            Intent renderNoteFormActivity = new Intent(this, NoteFormActivity.class);
            someActivityResultLauncher.launch(renderNoteFormActivity);
        });
    }

    private void configureRecyclerView(List<Notes> allNotes) {
        RecyclerView notesList = findViewById(R.id.recycleView);
        configureAdapter(allNotes, notesList);
    }

    private void configureAdapter(List<Notes> allNotes, RecyclerView notesList) {
        adapter = new NoteListAdapter(this, allNotes);
        notesList.setAdapter(adapter);
    }

    private List<Notes> getNotes() {
        NoteDao dao = new NoteDao();
        return dao.getAllNotes();
    }
}