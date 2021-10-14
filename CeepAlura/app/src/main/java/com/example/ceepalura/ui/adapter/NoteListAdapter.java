package com.example.ceepalura.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ceepalura.R;
import com.example.ceepalura.model.Notes;

import java.util.List;

public class NoteListAdapter extends RecyclerView.Adapter<NoteViewHolder> {

    private final Context context;
    private final List<Notes> notes;

    public NoteListAdapter(Context context, List<Notes> note) {
        this.context = context;
        this.notes = note;
    }

    @NonNull
    @Override
    public NoteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View cretedView = LayoutInflater.from(context).inflate(R.layout.note_item, parent, false);
        return new NoteViewHolder(cretedView);
    }

    @Override
    public void onBindViewHolder(NoteViewHolder holder, int position) {
        Notes note = notes.get(position);
        holder.bind(note);
    }

    @Override
    public int getItemCount() {
        return notes.size();
    }

    public void addNewNote(Notes note) {
        notes.add(note);
        notifyDataSetChanged();
    }
}

class NoteViewHolder extends RecyclerView.ViewHolder {

    private final TextView title;
    private final TextView description;

    public NoteViewHolder(View itemView) {
        super(itemView);

        title = itemView.findViewById(R.id.titleNote);
        description = itemView.findViewById(R.id.descriptionNote);
    }

    public void bind(Notes note) {
        title.setText(note.getTitle());
        description.setText(note.getDescription());
    }
}
