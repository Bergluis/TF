package classesDominio;

import java.io.Serializable;

public class Pessoa implements Serializable{
    private int cod;
    private int nivel;
    private static final long serialVersionUID = 111L;
    private String nome;
    private String email;
    private long doc;

    private String login;
    private String senha;
    private long telefone;

    public Pessoa(int nivel,String nome, String email, long doc, String login, String senha) {
        this.nivel = nivel;
        this.nome = nome;
        this.email = email;
        this.doc = doc;
        this.login = login;
        this.senha = senha;
    }
    

    public int getNivel() {
        return nivel;
    }

    public void setNivel(int nivel) {
        this.nivel = nivel;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public long getDoc() {
        return doc;
    }

    public void setDoc(long doc) {
        this.doc = doc;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public long getTelefone() {
        return telefone;
    }

    public void setTelefone(long telefone) {
        this.telefone = telefone;
    }

    public int getCod() {
        return cod;
    }

    public void setCod(int cod) {
        this.cod = cod;
    }

    
}