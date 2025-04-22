package com.example.smarttasks;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class CadastroTarefaActivity extends AppCompatActivity {

    private EditText editTitulo, editDescricao, editData;
    private Button buttonSalvar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_tarefa);

        editTitulo = findViewById(R.id.editTitulo);
        editDescricao = findViewById(R.id.editDescricao);
        editData = findViewById(R.id.editData);
        buttonSalvar = findViewById(R.id.btnSalvar);

        buttonSalvar.setOnClickListener(view -> {
            String titulo = editTitulo.getText().toString();
            String descricao = editDescricao.getText().toString();
            String data = editData.getText().toString();

            Tarefa tarefa = new Tarefa(titulo, descricao, data);

            TarefaDAO tarefaDAO = new TarefaDAO(CadastroTarefaActivity.this);
            tarefaDAO.open();
            tarefaDAO.inserirTarefa(tarefa);
            tarefaDAO.close();

            finish();
        });
    }
}
