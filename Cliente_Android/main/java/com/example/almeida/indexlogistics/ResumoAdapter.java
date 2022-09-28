package com.example.almeida.indexlogistics;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;



import java.util.List;

import classesDominio.Entrega;

public class ResumoAdapter extends RecyclerView.Adapter<ResumoAdapter.MyViewHolder> {
    private List<Entrega> listaEntregas;
    private ResumoOnClickListener resumoOnClickListener;

    public ResumoAdapter(List<Entrega> listaEntregas, ResumoOnClickListener resumoOnClickListener) {

        this.listaEntregas = listaEntregas;
        this.resumoOnClickListener = resumoOnClickListener;
    }

    @Override
    public ResumoAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_list_row_2,parent,false);
        return new MyViewHolder(itemView);
    }
    @Override
    public void onBindViewHolder(final ResumoAdapter.MyViewHolder holder, final int position) {
        Entrega minhaEntrega = listaEntregas.get(position);
        holder.tvCardResumoObjeto.setText(minhaEntrega.getNome());
        int gambiarra = minhaEntrega.getSituacao();
        holder.tvCardResumoSituacao.setText(minhaEntrega.getSituacaoLiteral(gambiarra));
        holder.tvCardResumoEmpresa.setText(""+minhaEntrega.getPrazo());



        // clique no item do cliente
        if (resumoOnClickListener != null) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    resumoOnClickListener.onClickResumo(holder.itemView,position);
                }
            });
        }
    }

    @Override
    public int getItemCount() {

        return listaEntregas.size();
    }



    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tvCardResumoObjeto, tvCardResumoSituacao, tvCardResumoEmpresa;
        public MyViewHolder(View itemView) {
            super(itemView);
            tvCardResumoObjeto= (TextView) itemView.findViewById(R.id.tvCardResumoObjeto);
            tvCardResumoSituacao = (TextView) itemView.findViewById(R.id.tvCardResumoSituacao);
            tvCardResumoEmpresa= (TextView) itemView.findViewById(R.id.tvCardResumoEmpresa  );

        }
    }

    public interface ResumoOnClickListener {
        public void onClickResumo(View view, int position);
    }
}
