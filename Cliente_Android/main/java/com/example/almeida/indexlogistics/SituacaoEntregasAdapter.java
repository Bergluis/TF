package com.example.almeida.indexlogistics;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import java.util.List;

import classesDominio.Entrega;
import classesDominio.Pessoa;

public class SituacaoEntregasAdapter extends RecyclerView.Adapter<SituacaoEntregasAdapter.MyViewHolder> {
    private List<Entrega> listaEntregas;
    private List<Pessoa> listaPessoas;
    private SituacaoOnClickListener situacaoOnClickListener;

    public SituacaoEntregasAdapter(List<Pessoa> listaPessoas,List<Entrega> listaEntregas, SituacaoOnClickListener situacaoOnClickListener) {
        this.listaPessoas = listaPessoas;
        this.listaEntregas = listaEntregas;
        this.situacaoOnClickListener = situacaoOnClickListener;
    }

    @Override
    public SituacaoEntregasAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_list_row_5,parent,false);
        return new MyViewHolder(itemView);
    }
    @Override
    public void onBindViewHolder(final SituacaoEntregasAdapter.MyViewHolder holder, final int position) {
        Entrega minhaEntrega = listaEntregas.get(position);
        Pessoa minhaPessoa = listaPessoas.get(position);

        holder.tvSituacaoCodigo.setText(minhaEntrega.getCod()+"");
        holder.tvSituacaoObjeto.setText(minhaEntrega.getNome());
        int gambishow = minhaEntrega.getSituacao();
        holder.tvSituacaoSituacao.setText(minhaEntrega.getSituacaoLiteral(gambishow));
        holder.tvSituacaoCliente.setText(minhaPessoa.getNome());


        // clique no item do cliente
        if (situacaoOnClickListener != null) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    situacaoOnClickListener.onClickSituacao(holder.itemView,position);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return listaEntregas.size();
    }



    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tvSituacaoCodigo, tvSituacaoObjeto, tvSituacaoCliente, tvSituacaoSituacao;
        public MyViewHolder(View itemView) {
            super(itemView);
            tvSituacaoCodigo= (TextView) itemView.findViewById(R.id.tvSituacaoCodigo);
            tvSituacaoObjeto= (TextView) itemView.findViewById(R.id.tvSituacaoObjeto);
            tvSituacaoCliente= (TextView) itemView.findViewById(R.id.tvSituacaoCliente);
            tvSituacaoSituacao= (TextView) itemView.findViewById(R.id.tvSituacaoSituacao);
        }
    }

    public interface SituacaoOnClickListener {
        public void onClickSituacao(View view, int position);
    }
}
