package com.example.smarttasks;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;

public class AnalisadorTarefaActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private TarefaAdapter tarefaAdapter;
    private ArrayList<Tarefa> tarefas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_analisador_tarefa);

        if (tarefas == null) {

            tarefas = new ArrayList<>();
            tarefas.add(new Tarefa("Tarefa C", "Descrição C", "2025-04-26"));
            tarefas.add(new Tarefa("Tarefa A", "Descrição A", "2025-04-23"));
            tarefas.add(new Tarefa("Tarefa B", "Descrição B", "2025-04-25"));
        }

        Collections.sort(tarefas, new TarefaUrgenciaComparator());

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        tarefaAdapter = new TarefaAdapter(tarefas, this::onClick);

        recyclerView.setAdapter(tarefaAdapter);
    }

    private void onClick(Tarefa tarefa) {
    }

    private static class TarefaUrgenciaComparator implements Comparator<Tarefa> {
        @Override
        public int compare(Tarefa tarefa1, Tarefa tarefa2) {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            try {
                Date data1 = sdf.parse(tarefa1.getData());
                Date data2 = sdf.parse(tarefa2.getData());
                return data1.compareTo(data2);
            } catch (ParseException e) {
                return 0;
            }
        }
    }
}
