/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classesDominio;

import java.io.Serializable;

public class Endereco implements Serializable{
    private int cod;
    private String rua;
    private int numero;
    private static final long serialVersionUID = 113L;
    private String complemento;
    private int cod_cidade;
    private String nome_cidade;

    public String getNome_cidade() {
        return nome_cidade;
    }

    public void setNome_cidade(String nome_cidade) {
        this.nome_cidade = nome_cidade;
    }

    private int cod_pessoa;

    public Endereco(String rua, int numero, int cod_cidade, int cod_pessoa) {
        this.rua = rua;
        this.numero = numero;
        this.cod_cidade = cod_cidade;
        this.cod_pessoa = cod_pessoa;
    }

    public int getCod() {
        return cod;
    }

    public void setCod(int cod) {
        this.cod = cod;
    }

    public int getCod_pessoa() {
        return cod_pessoa;
    }

    public void setCod_pessoa(int cod_pessoa) {
        this.cod_pessoa = cod_pessoa;
    }

    public String getRua() {
        return rua;
    }

    public void setRua(String rua) {
        this.rua = rua;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public int getCod_cidade() {
        return cod_cidade;
    }

    public void setCod_cidade(int cod_cidade) {
        this.cod_cidade = cod_cidade;
    }


}
