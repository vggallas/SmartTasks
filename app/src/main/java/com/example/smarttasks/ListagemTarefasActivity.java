package com.example.smarttasks;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ListagemTarefasActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private TarefaAdapter tarefaAdapter;
    private List<Tarefa> listaTarefas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listagem_tarefas);

        recyclerView = findViewById(R.id.recyclerViewTarefas);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        TarefaDAO tarefaDAO = new TarefaDAO(ListagemTarefasActivity.this);
        tarefaDAO.open();

        // Recuperar a lista de tarefas do banco de dados
        listaTarefas = tarefaDAO.listarTarefas();
        tarefaDAO.close();

        if (listaTarefas.isEmpty()) {
            Toast.makeText(this, "Nenhuma tarefa encontrada.", Toast.LENGTH_SHORT).show();
        }

        tarefaAdapter = new TarefaAdapter(listaTarefas, tarefa -> {
            // Aqui você pode tratar o clique em uma tarefa
        });

        recyclerView.setAdapter(tarefaAdapter);
    }
}
