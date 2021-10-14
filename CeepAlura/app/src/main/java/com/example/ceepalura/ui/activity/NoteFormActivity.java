package com.example.ceepalura.ui.activity;

import static com.example.ceepalura.ui.activity.contants.Constants.NOTE_KEY;
import static com.example.ceepalura.ui.activity.contants.Constants.RESULT_CODE_CREATED_NOTE;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.ceepalura.R;
import com.example.ceepalura.model.Notes;

public class NoteFormActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_form);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.save_new_note_manu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(isSaveNewNoteMenu(item)){
            Notes createdNote = createNewNote();
            getCreatedNote(createdNote);
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    private void getCreatedNote(Notes createdNote) {
        Intent insertionResult = new Intent();
        insertionResult.putExtra(NOTE_KEY, createdNote);
        setResult(RESULT_CODE_CREATED_NOTE, insertionResult);
    }

    @NonNull
    private Notes createNewNote() {
        EditText title = findViewById(R.id.inputTitle);
        EditText description = findViewById(R.id.inputDescription);

        return new Notes(title.getText().toString(),
                description.getText().toString());
    }

    private boolean isSaveNewNoteMenu(MenuItem item) {
        return item.getItemId() == R.id.save_new_note_menu;
    }
}