/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classesDominio;

import java.io.Serializable;

/**
 *
 * @author Luis
 */
public class Solicitacao implements Serializable{
    private int cod;
    private String nome;
    private String descricao;
    private int codEndSaida;
    private static final long serialVersionUID = 115L;
    private int codEndChegada;
    private int codPessoa;
    private String nomeEndChegada;
    private String nomeEndSaida;

    public String getNomeEndChegada() {
        return nomeEndChegada;
    }

    public void setNomeEndChegada(String nomeEndChegada) {
        this.nomeEndChegada = nomeEndChegada;
    }

    public String getNomeEndSaida() {
        return nomeEndSaida;
    }

    public void setNomeEndSaida(String nomeEndSaida) {
        this.nomeEndSaida = nomeEndSaida;
    }


    public Solicitacao(String nome, String descricao, int codEndSaida, int codEndChegada, int codPessoa) {
        this.nome = nome;
        this.descricao = descricao;
        this.codEndSaida = codEndSaida;
        this.codEndChegada = codEndChegada;
        this.codPessoa = codPessoa;
    }

    public int getCodPessoa() {
        return codPessoa;
    }

    public void setCodPessoa(int codPessoa) {
        this.codPessoa = codPessoa;
    }

    public int getCod() {
        return cod;
    }

    public void setCod(int cod) {
        this.cod = cod;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public int getCodEndSaida() {
        return codEndSaida;
    }

    public void setCodEndSaida(int codEndSaida) {
        this.codEndSaida = codEndSaida;
    }

    public int getCodEndChegada() {
        return codEndChegada;
    }

    public void setCodEndChegada(int codEndChegada) {
        this.codEndChegada = codEndChegada;
    }
}
