package com.example.smarttasks;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.widget.Toast;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private static final int REQUEST_CADASTRO_TAREFA = 1;
    private ArrayList<Tarefa> listaTarefas = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayShowTitleEnabled(false);
        }

        findViewById(R.id.buttonCadastro).setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, CadastroTarefaActivity.class);
            startActivityForResult(intent, REQUEST_CADASTRO_TAREFA);
        });

        findViewById(R.id.buttonListagem).setOnClickListener(view -> {

            Intent intent = new Intent(MainActivity.this, ListagemTarefasActivity.class);
            intent.putExtra("listaTarefas", listaTarefas);
            startActivity(intent);
        });

        findViewById(R.id.buttonCompartilhar).setOnClickListener(view -> {
            Intent intent = new Intent(Intent.ACTION_SEND);
            intent.setType("text/plain");
            intent.putExtra(Intent.EXTRA_SUBJECT, "SmartTasks - Gerenciador de Tarefas");
            intent.putExtra(Intent.EXTRA_TEXT, "Conheça o app SmartTasks! Confira o código fonte: https://github.com/vggallas/SmartTasks");
            startActivity(Intent.createChooser(intent, "Compartilhar via"));
        });

        findViewById(R.id.buttonAnalise).setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, AnalisadorTarefaActivity.class);
            startActivity(intent);
        });

        findViewById(R.id.buttonAcessarSite).setOnClickListener(view -> {
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://presencial.ifrs.edu.br"));
            startActivity(intent);
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_CADASTRO_TAREFA && resultCode == RESULT_OK) {
            Tarefa tarefa = (Tarefa) data.getSerializableExtra("tarefa");
            if (tarefa != null) {
                listaTarefas.add(tarefa);
                Toast.makeText(this, "Tarefa salva com sucesso!", Toast.LENGTH_SHORT).show();
            }
        }
    }
}