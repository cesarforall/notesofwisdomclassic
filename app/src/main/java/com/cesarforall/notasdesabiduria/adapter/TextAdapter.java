package com.cesarforall.notasdesabiduria.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.cesarforall.notasdesabiduria.R;
import com.cesarforall.notasdesabiduria.db.DatabaseHelper;
import com.cesarforall.notasdesabiduria.model.Text;

import java.util.List;

public class TextAdapter extends RecyclerView.Adapter<TextAdapter.TextViewHolder> {
    private List<Text> textList;
    private Context context;
    private DatabaseHelper dbHelper;

    public TextAdapter(List<Text> textList, Context context, DatabaseHelper dbHelper) {
        this.textList = textList;
        this.context = context;
        this.dbHelper = dbHelper;
    }

    @Override
    public TextViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_text, parent, false);
        return new TextViewHolder(view);
    }

    @Override
    public void onBindViewHolder(TextViewHolder holder, int position) {
        Text text = textList.get(position);
        holder.textView.setText(text.getText());
        holder.authorTextView.setText(text.getAuthor() + ". ");
        holder.sourceTextView.setText("\"" + text.getSource() + "\". ");

        // Hide sourceTypeTextView if not needed or use if you want
        // holder.sourceTypeTextView.setText(text.getSourceType());

        if ("book".equalsIgnoreCase(text.getSourceType()) && text.getPageNumber() != null) {
            holder.pageOrMinuteTextView.setText("Page: " + text.getPageNumber());
            holder.pageOrMinuteTextView.setVisibility(View.VISIBLE);
        } else if ("movie".equalsIgnoreCase(text.getSourceType()) && text.getMinute() != null) {
            holder.pageOrMinuteTextView.setText("Minute: " + text.getMinute());
            holder.pageOrMinuteTextView.setVisibility(View.VISIBLE);
        } else {
            holder.pageOrMinuteTextView.setVisibility(View.GONE);
        }

        holder.deleteButton.setOnClickListener(v -> {
            dbHelper.deleteText(text.getId());
            textList.remove(position);
            notifyItemRemoved(position);
            notifyItemRangeChanged(position, textList.size());
        });
    }

    @Override
    public int getItemCount() {
        return textList.size();
    }

    public static class TextViewHolder extends RecyclerView.ViewHolder {
        TextView textView;
        TextView authorTextView;
        TextView sourceTextView;
        TextView pageOrMinuteTextView;
        ImageButton deleteButton;

        public TextViewHolder(View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.textViewPhrase);
            authorTextView = itemView.findViewById(R.id.textViewAuthor);
            sourceTextView = itemView.findViewById(R.id.textViewSource);
            pageOrMinuteTextView = itemView.findViewById(R.id.textViewPageOrMinute);
            deleteButton = itemView.findViewById(R.id.buttonDelete);
        }
    }
}
