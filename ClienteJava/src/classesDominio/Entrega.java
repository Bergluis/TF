package classesDominio;

import java.io.Serializable;


public class Entrega implements Serializable{
    private int cod;
    private int prazo;
    private float valor;
    private int codEmpresa;
    private int situacao;
    private String nome;
    private String descricao;
    private int saida;
    private static final long serialVersionUID = 114L;
    private int chegada;
    private int codPessoa;
    private String nome_empresa;

    public String getNome_empresa() {
        return nome_empresa;
    }

    public void setNome_empresa(String nome_empresa) {
        this.nome_empresa = nome_empresa;
    }
    private int codSolicitacao;

    public Entrega(int prazo, float valor, int codEmpresa, int situacao, String nome, String descricao, int saida, int chegada, int codPessoa) {
        this.prazo = prazo;
        this.valor = valor;
        this.codEmpresa = codEmpresa;
        this.situacao = situacao;
        this.nome = nome;
        this.descricao = descricao;
        this.saida = saida;
        this.chegada = chegada;
        this.codPessoa = codPessoa;
    }

    

    

    public int getSituacao() {
        return situacao;
    }

    public void setSituacao(int situacao) {
        this.situacao = situacao;
    }

    public int getCod() {
        return cod;
    }

    public void setCod(int cod) {
        this.cod = cod;
    }

    public int getPrazo() {
        return prazo;
    }

    public void setPrazo(int prazo) {
        this.prazo = prazo;
    }

    public float getValor() {
        return valor;
    }

    public void setValor(float valor) {
        this.valor = valor;
    }

    public int getCodEmpresa() {
        return codEmpresa;
    }

    public void setCodEmpresa(int codEmpresa) {
        this.codEmpresa = codEmpresa;
    }
    
    public String getSituacaoLiteral(int situacao){
        String x="";
        if(situacao==1){
            x="Aguardando aprovação";
        } else if(situacao==2){
            x="Aprovada";
        } else if(situacao==3){
            x="Saiu para entrega";
        } else if(situacao==4){
            x="Entregue";
        }
        return x;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public int getSaida() {
        return saida;
    }

    public void setSaida(int saida) {
        this.saida = saida;
    }

    public int getChegada() {
        return chegada;
    }

    public void setChegada(int chegada) {
        this.chegada = chegada;
    }

    public int getCodPessoa() {
        return codPessoa;
    }

    public void setCodPessoa(int codPessoa) {
        this.codPessoa = codPessoa;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getCodSolicitacao() {
        return codSolicitacao;
    }

    public void setCodSolicitacao(int codSolicitacao) {
        this.codSolicitacao = codSolicitacao;
    }
    
    
}
