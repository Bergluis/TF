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
public class Cidade implements Serializable{
    private int cod;
    private String nome;
    private static final long serialVersionUID = 112L;
    private String uf;

    public Cidade(int cod, String nome, String uf) {
        this.cod = cod;
        this.nome = nome;
        this.uf = uf;
    }

    public String getUf() {
        return uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
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

}
