package com.example.almeida.indexlogistics;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import classesDominio.Entrega;

public class AprovarEntregaAdapter extends RecyclerView.Adapter<AprovarEntregaAdapter.MyViewHolder> {
    private List<Entrega> listaEntregas;
    private AprovarOnClickListener aprovarOnClickListener;

    public AprovarEntregaAdapter(List<Entrega> listaEntregas, AprovarOnClickListener aprovarOnClickListener) {
        this.listaEntregas = listaEntregas;
        this.aprovarOnClickListener = aprovarOnClickListener;
    }

    @Override
    public AprovarEntregaAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_list_row_3,parent,false);
        return new MyViewHolder(itemView);
    }
    @Override
    public void onBindViewHolder(final AprovarEntregaAdapter.MyViewHolder holder, final int position) {
        Entrega minhaEntrega = listaEntregas.get(position);
        holder.tvList3Prazo.setText("Prazo: "+ minhaEntrega.getPrazo());
        holder.tvList3Valor.setText("Valor: "+minhaEntrega.getValor()+"");
        holder.tvList3Objeto.setText("Nome: "+minhaEntrega.getNome());



        // clique no item do cliente
        if (aprovarOnClickListener != null) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    aprovarOnClickListener.onClickAprovar(holder.itemView,position);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return listaEntregas.size();
    }



    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tvList3Objeto, tvList3Valor, tvList3Prazo;
        public MyViewHolder(View itemView) {
            super(itemView);
            tvList3Objeto= (TextView) itemView.findViewById(R.id.tvList3Objeto);
            tvList3Valor = (TextView) itemView.findViewById(R.id.tvList3Valor);
            tvList3Prazo= (TextView) itemView.findViewById(R.id.tvList3Prazo);

        }
    }

    public interface AprovarOnClickListener {
        public void onClickAprovar(View view, int position);
    }
}
