package com.example.almeida.indexlogistics;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import java.util.List;

import classesDominio.Endereco;

public class EnderecosCadastradosAdapter extends RecyclerView.Adapter<EnderecosCadastradosAdapter.MyViewHolder> {
    private List<Endereco> listaEnderecos;
    private EnderecoOnClickListener enderecoOnClickListener;


    public EnderecosCadastradosAdapter(List<Endereco> listaEnderecos, EnderecoOnClickListener enderecoOnClickListener) {
        this.listaEnderecos = listaEnderecos;
        this.enderecoOnClickListener = enderecoOnClickListener;
    }

    @Override
    public EnderecosCadastradosAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_list_row,parent,false);
        return new MyViewHolder(itemView);
    }
    @Override
    public void onBindViewHolder(final EnderecosCadastradosAdapter.MyViewHolder holder, final int position) {
        Endereco meuEndereco = listaEnderecos.get(position);
        holder.tvCardEnderecosCadastradosCodigo.setText("Endereço: "+meuEndereco.getCod()+"");
        holder.tvCardEnderecosCadastradosRua.setText("Rua: "+meuEndereco.getRua());
        holder.tvCardEnderecosCadastradosCidade.setText("Cidade: "+meuEndereco.getNome_cidade());
        holder.tvCardEnderecosCadastradosComplemento.setText("Complemento: "+meuEndereco.getComplemento());

        // clique no item do cliente
        if (enderecoOnClickListener != null) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    enderecoOnClickListener.onClickEndereco(holder.itemView,position);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return listaEnderecos.size();
    }



    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tvCardEnderecosCadastradosCodigo, tvCardEnderecosCadastradosRua,tvCardEnderecosCadastradosCidade,tvCardEnderecosCadastradosComplemento;
        public MyViewHolder(View itemView) {
            super(itemView);
            tvCardEnderecosCadastradosComplemento=itemView.findViewById(R.id.tvCardEnderecosCadastradosComplemento);
            tvCardEnderecosCadastradosCidade=itemView.findViewById(R.id.tvCardEnderecosCadastradosCidade);
            tvCardEnderecosCadastradosCodigo= (TextView) itemView.findViewById(R.id.tvCardEnderecosCadastradosCodigo);
            tvCardEnderecosCadastradosRua = (TextView) itemView.findViewById(R.id.tvCardEnderecosCadastradosRua);
        }
    }

    public interface EnderecoOnClickListener {
        public void onClickEndereco(View view, int position);
    }
}
