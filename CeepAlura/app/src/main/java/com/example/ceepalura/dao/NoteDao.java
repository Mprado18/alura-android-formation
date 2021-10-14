package com.example.ceepalura.dao;

import com.example.ceepalura.model.Notes;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class NoteDao {

    private final static ArrayList<Notes> notes = new ArrayList<>();

    public List<Notes> getAllNotes() {
        return (List<Notes>) notes.clone();
    }

    public void insertNote(Notes... note) {
        NoteDao.notes.addAll(Arrays.asList(note));
    }

    public void changeNote(int position, Notes note) {
        notes.set(position, note);
    }

    public void removeNote(int position) {
        notes.remove(position);
    }

    public void swapNote(int initialPosition, int finalPosition) {
        Collections.swap(notes, initialPosition, finalPosition);
    }

    public void removeAll() {
        notes.clear();
    }

}
