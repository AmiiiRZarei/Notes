package com.example.notes.adapters;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;
import com.example.notes.EditNoteActivity;
import com.example.notes.R;
import com.example.notes.models.Note;
import com.example.notes.models.NoteDAO;
import com.example.notes.models.NoteRoomDatabase;
import java.util.ArrayList;

public class NotesAdapters extends RecyclerView.Adapter<NotesAdapters.ViewHolder>  {



    private Context context;
    private ArrayList<Note> notes;
    NoteDAO dao;

    public NotesAdapters(Context context, ArrayList<Note> notes) {
        this.context = context;
        this.notes = notes;
    }

    @NonNull
    @Override
    public NotesAdapters.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.note_item_layout , parent , false);
        return new ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull final NotesAdapters.ViewHolder holder, int position) {
        Note note = notes.get(position);
        holder.noteTv.setText(note.getNote());
        holder.dateTv.setText(note.getDate());

    }

    @Override
    public int getItemCount() {
        return notes.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {
        TextView noteTv , dateTv;
        LinearLayout noteLl;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            noteTv = itemView.findViewById(R.id.tv_note);
            dateTv = itemView.findViewById(R.id.tv_date);
            noteLl = itemView.findViewById(R.id.ll_note_item);

            itemView.setOnClickListener(this);
            itemView.setOnLongClickListener(this);

        }

        @Override
        public void onClick(View view) {
            int itemPosition = getAdapterPosition();
            Note item = notes.get(itemPosition);

           Intent edit = new Intent(context, EditNoteActivity.class);
           edit.putExtra("noteForEdit" , item.getNote());
           edit.putExtra("noteId" , item.getId());
           context.startActivity(edit);
        }


        @Override
        public boolean onLongClick(final View view) {
            int itemPosition = getAdapterPosition();
            final Note item = notes.get(itemPosition);

            final AlertDialog.Builder builder = new AlertDialog.Builder(context);
            builder.setTitle("Delete")
                    .setIcon(R.drawable.ic_baseline_delete_24)
                    .setMessage("Are you sure delete this Note?")
                    .setCancelable(true)
                    .setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            notes.remove(item);
                            dao = NoteRoomDatabase.getDatabase(context).noteDAO();
                            dao.deleteNote(item);
                            notifyDataSetChanged();
                        }
                    })
                    .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.dismiss();
                        }
                    });
            AlertDialog dialog = builder.create();
            dialog.show();
            dialog.getWindow().setBackgroundDrawableResource(R.drawable.alert_dialog_background);
            dialog.getButton(AlertDialog.BUTTON_POSITIVE).setTextColor(Color.BLUE);
            dialog.getButton(AlertDialog.BUTTON_NEGATIVE).setTextColor(Color.RED);
            dialog.getWindow().setLayout(600, 300);

            return false;
        }
    }
}
