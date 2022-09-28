package com.example.almeida.indexlogistics;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import java.util.List;

import classesDominio.Solicitacao;

public class EmpresaEntregasAdapter extends RecyclerView.Adapter<EmpresaEntregasAdapter.MyViewHolder> {
    private List<Solicitacao> listaSolicitacoes;
    private EmpresaEntregasOnClickListener empresaEntregasOnClickListener;

    public EmpresaEntregasAdapter(List<Solicitacao> listaSolicitacoes, EmpresaEntregasOnClickListener empresaEntregasOnClickListener) {
        this.listaSolicitacoes = listaSolicitacoes;
        this.empresaEntregasOnClickListener = empresaEntregasOnClickListener;
    }

    @Override
    public EmpresaEntregasAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_list_row_4,parent,false);
        return new MyViewHolder(itemView);
    }
    @Override
    public void onBindViewHolder(final EmpresaEntregasAdapter.MyViewHolder holder, final int position) {
        Solicitacao minhaSolicitacao = listaSolicitacoes.get(position);
        holder.tvEmpresaEntregasCodigo.setText("Código Entrega: "+minhaSolicitacao.getCod()+"");
        holder.tvEmpresaEntregasObjeto.setText("Nome: "+minhaSolicitacao.getNome());
        holder.tvEmpresaEntregasDescricao.setText("Descrição: "+minhaSolicitacao.getDescricao());
        holder.tvEmpresaEntregasSaida.setText("Endereço de Saída:"+minhaSolicitacao.getNomeEndSaida());
        holder.tvEmpresaEntregasDestino.setText("Endereço de Chegada: "+minhaSolicitacao.getNomeEndChegada());


        // clique no item do cliente
        if (empresaEntregasOnClickListener != null) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    empresaEntregasOnClickListener.onClickEmpresaEntregas(holder.itemView,position);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return listaSolicitacoes.size();
    }



    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tvEmpresaEntregasCodigo, tvEmpresaEntregasObjeto, tvEmpresaEntregasDescricao, tvEmpresaEntregasSaida, tvEmpresaEntregasDestino;
        public MyViewHolder(View itemView) {
            super(itemView);
            tvEmpresaEntregasCodigo= (TextView) itemView.findViewById(R.id.tvEmpresaEntregasCodigo);
            tvEmpresaEntregasObjeto= (TextView) itemView.findViewById(R.id.tvEmpresaEntregasObjeto);
            tvEmpresaEntregasDescricao= (TextView) itemView.findViewById(R.id.tvEmpresaEntregasDescricao);
            tvEmpresaEntregasSaida= (TextView) itemView.findViewById(R.id.tvEmpresaEntregasSaida);
            tvEmpresaEntregasDestino= (TextView) itemView.findViewById(R.id.tvEmpresaEntregasDestino);
        }
    }

    public interface EmpresaEntregasOnClickListener {
        public void onClickEmpresaEntregas(View view, int position);
    }
}
