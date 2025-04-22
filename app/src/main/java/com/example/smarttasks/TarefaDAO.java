package com.example.smarttasks;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class TarefaDAO {

    private SQLiteDatabase database;
    private DatabaseHelper dbHelper;

    public TarefaDAO(Context context) {
        dbHelper = new DatabaseHelper(context);
    }

    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }

    public long inserirTarefa(Tarefa tarefa) {
        ContentValues values = new ContentValues();
        values.put(DatabaseHelper.COLUMN_TITULO, tarefa.getTitulo());
        values.put(DatabaseHelper.COLUMN_DESCRICAO, tarefa.getDescricao());
        values.put(DatabaseHelper.COLUMN_DATA, tarefa.getData());

        return database.insert(DatabaseHelper.TABLE_TAREFAS, null, values);
    }

    public List<Tarefa> listarTarefas() {
        List<Tarefa> tarefas = new ArrayList<>();
        Cursor cursor = database.query(DatabaseHelper.TABLE_TAREFAS,
                new String[]{DatabaseHelper.COLUMN_ID, DatabaseHelper.COLUMN_TITULO,
                        DatabaseHelper.COLUMN_DESCRICAO, DatabaseHelper.COLUMN_DATA},
                null, null, null, null, null);

        if (cursor != null) {
            if (cursor.moveToFirst()) {
                do {
                    Tarefa tarefa = new Tarefa(
                            cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_TITULO)),
                            cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_DESCRICAO)),
                            cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_DATA))
                    );
                    tarefas.add(tarefa);
                } while (cursor.moveToNext());
            }
            cursor.close();
        }

        return tarefas;
    }
}
