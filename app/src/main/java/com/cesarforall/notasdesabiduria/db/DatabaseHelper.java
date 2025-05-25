package com.cesarforall.notasdesabiduria.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.cesarforall.notasdesabiduria.model.Text;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "textDatabase.db";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_NAME = "texts";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_TEXT = "text";
    private static final String COLUMN_AUTHOR = "author";
    private static final String COLUMN_SOURCE = "source";
    private static final String COLUMN_SOURCE_TYPE = "source_type"; // "book" o "movie"
    private static final String COLUMN_PAGE_NUMBER = "page_number"; // Solo para libros
    private static final String COLUMN_MINUTE = "minute"; // Solo para películas

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + " (" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_TEXT + " TEXT, " +
                COLUMN_AUTHOR + " TEXT, " +
                COLUMN_SOURCE + " TEXT, " +
                COLUMN_SOURCE_TYPE + " TEXT, " +
                COLUMN_PAGE_NUMBER + " INTEGER, " +
                COLUMN_MINUTE + " INTEGER)";
        db.execSQL(CREATE_TABLE);
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    // Método para insertar un texto con autor, fuente, tipo de fuente, página y minuto
    public void addText(Text text) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_TEXT, text.getText());
        values.put(COLUMN_AUTHOR, text.getAuthor());
        values.put(COLUMN_SOURCE, text.getSource());
        values.put(COLUMN_SOURCE_TYPE, text.getSourceType());
        if (text.getPageNumber() != -1) values.put(COLUMN_PAGE_NUMBER, text.getPageNumber());
        if (text.getMinute() != -1) values.put(COLUMN_MINUTE, text.getMinute());
        db.insert(TABLE_NAME, null, values);
        db.close();
    }


    // Método para obtener todos los textos
    public List<Text> getAllTexts() {
        List<Text> texts = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_NAME,
                new String[]{COLUMN_ID, COLUMN_TEXT, COLUMN_AUTHOR, COLUMN_SOURCE, COLUMN_SOURCE_TYPE, COLUMN_PAGE_NUMBER, COLUMN_MINUTE},
                null, null, null, null, null);

        if (cursor != null) {
            if (cursor.moveToFirst()) {
                do {
                    int id = cursor.getInt(cursor.getColumnIndex(COLUMN_ID));
                    String text = cursor.getString(cursor.getColumnIndex(COLUMN_TEXT));
                    String author = cursor.getString(cursor.getColumnIndex(COLUMN_AUTHOR));
                    String source = cursor.getString(cursor.getColumnIndex(COLUMN_SOURCE));
                    String sourceType = cursor.getString(cursor.getColumnIndex(COLUMN_SOURCE_TYPE));
                    Integer pageNumber = cursor.isNull(cursor.getColumnIndex(COLUMN_PAGE_NUMBER)) ? null : cursor.getInt(cursor.getColumnIndex(COLUMN_PAGE_NUMBER));
                    Integer minute = cursor.isNull(cursor.getColumnIndex(COLUMN_MINUTE)) ? null : cursor.getInt(cursor.getColumnIndex(COLUMN_MINUTE));
                    texts.add(new Text(id, text, author, source, sourceType, pageNumber, minute));
                } while (cursor.moveToNext());
            }
            cursor.close();
        }
        db.close();
        return texts;
    }

    // Método para eliminar un texto por ID
    public void deleteText(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME, COLUMN_ID + " = ?", new String[]{String.valueOf(id)});
        db.close();
    }
}

