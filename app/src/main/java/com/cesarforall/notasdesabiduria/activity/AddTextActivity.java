package com.cesarforall.notasdesabiduria.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

import com.cesarforall.notasdesabiduria.R;
import com.cesarforall.notasdesabiduria.db.DatabaseHelper;
import com.cesarforall.notasdesabiduria.model.Text;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class AddTextActivity extends AppCompatActivity {

    private EditText editPhrase, editAuthor, editSource, editSourceType, editPage, editMinute;
    private Button buttonSave;
    private ImageButton backButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_text);

        editPhrase = findViewById(R.id.editTextPhrase);
        editAuthor = findViewById(R.id.editTextAuthor);
        editSource = findViewById(R.id.editTextSource);
        editSourceType = findViewById(R.id.editTextSourceType);
        editPage = findViewById(R.id.editTextPage);
        editMinute = findViewById(R.id.editTextMinute);
        buttonSave = findViewById(R.id.buttonSave);
        backButton = findViewById(R.id.backButton);

        backButton.setOnClickListener(v -> finish());

        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String phrase = editPhrase.getText().toString().trim();
                String author = editAuthor.getText().toString().trim();
                String source = editSource.getText().toString().trim();
                String sourceType = editSourceType.getText().toString().trim();
                int page = editPage.getText().toString().isEmpty() ? -1 : Integer.parseInt(editPage.getText().toString());
                int minute = editMinute.getText().toString().isEmpty() ? -1 : Integer.parseInt(editMinute.getText().toString());

                Text text = new Text(phrase, author, source, sourceType, page, minute);

                DatabaseHelper db = new DatabaseHelper(AddTextActivity.this);
                db.addText(text);
                finish();
            }
        });
    }
}

