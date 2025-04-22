package com.example.smarttasks;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class TarefaAdapter extends RecyclerView.Adapter<TarefaAdapter.TarefaViewHolder> {

    private List<Tarefa> listaTarefas;
    private OnTarefaClickListener onTarefaClickListener;

    public interface OnTarefaClickListener {
        void onTarefaClick(Tarefa tarefa);
    }

    public TarefaAdapter(List<Tarefa> listaTarefas, OnTarefaClickListener onTarefaClickListener) {
        this.listaTarefas = listaTarefas;
        this.onTarefaClickListener = onTarefaClickListener;
    }

    @Override
    public TarefaViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_tarefa, parent, false);
        return new TarefaViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(TarefaViewHolder holder, int position) {
        Tarefa tarefa = listaTarefas.get(position);
        holder.tituloTarefa.setText(tarefa.getTitulo());
        holder.descricaoTarefa.setText(tarefa.getDescricao());
        holder.dataTarefa.setText(tarefa.getData());

        holder.itemView.setOnClickListener(v -> {
            if (onTarefaClickListener != null) {
                onTarefaClickListener.onTarefaClick(tarefa);
            }
        });
    }

    @Override
    public int getItemCount() {
        return listaTarefas.size();
    }

    public class TarefaViewHolder extends RecyclerView.ViewHolder {
        TextView tituloTarefa, descricaoTarefa, dataTarefa;

        public TarefaViewHolder(View itemView) {
            super(itemView);
            tituloTarefa = itemView.findViewById(R.id.tituloTarefa);
            descricaoTarefa = itemView.findViewById(R.id.descricaoTarefa);
            dataTarefa = itemView.findViewById(R.id.dataTarefa);
        }
    }
}
