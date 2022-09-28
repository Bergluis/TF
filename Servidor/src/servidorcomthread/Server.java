package servidorcomthread;


import classesDominio.Cidade;
import classesDominio.Endereco;
import classesDominio.Entrega;
import classesDominio.Pessoa;
import classesDominio.Solicitacao;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.SQLException;
import java.util.ArrayList;

public class Server implements Runnable{
    public Socket cliente;
    public static ObjectOutputStream Saida;
    public static ObjectInputStream Entrada;

    public Server(Socket cliente){
        this.cliente = cliente;
    }

    public static void main(String[] args)  throws IOException{     

        //Cria um socket na porta 12345
        ServerSocket servidor = new ServerSocket (12345);
        System.out.println("Porta 12345 aberta!");

        // Aguarda alguém se conectar. A execução do servidor
        // fica bloqueada na chamada do método accept da classe
        // ServerSocket. Quando alguém se conectar ao servidor, o
        // método desbloqueia e retorna com um objeto da classe
        // Socket, que é uma porta da comunicação.
        System.out.println("Aguardando conexão do cliente...");   

        while (true) {
          Socket cliente = servidor.accept();
          // Cria uma thread do servidor para tratar a conexão
          Server tratamento = new Server(cliente);
          Thread t = new Thread(tratamento);
          // Inicia a thread para o cliente conectado
          t.start();
        }
    }

    /* A classe Thread, que foi instancia no servidor, implementa Runnable.
       Então você terá que implementar sua lógica de troca de mensagens dentro deste método 'run'.
    */
    @Override
    public void run(){
        try {
            Conexao con = new Conexao();
                     
            System.out.println("Nova conexao com o cliente " + cliente.getInetAddress().getHostAddress());
            Entrada = new ObjectInputStream(cliente.getInputStream());
            Saida = new ObjectOutputStream(cliente.getOutputStream());
            String login;
            String senha;
            //RECEBE MENSAGEM
            //Conexao con = new Conexao();
            int valor = 0;
            while(valor!=99){
            
            ///recebendo um inteiro
            valor = ((int)Entrada.readObject());
            
            if(valor==1){ //consulta o nome da pessoa
                Saida.writeObject(1);
                int cod = (int)Entrada.readObject();
                String nome = con.consultaPessoa(cod);
                Saida.writeObject(nome);
            }
            if(valor==2){ //consulta nivel
                Saida.writeObject(1);
                int cod = (int)Entrada.readObject();
                int nivel = con.consultaNivel(cod);
                Saida.writeObject(nivel);
            }
            if(valor==3){ //consulta endereços
                Saida.writeObject(1);
                int cod = (int)Entrada.readObject();
                ArrayList<Endereco> listaEnderecos = con.consultaEnderecos(cod);
                Saida.writeObject(listaEnderecos);
            }
            if(valor==4){ //consulta cidades
                ArrayList<Cidade> listaCidades = con.consultaCidades();
                Saida.writeObject(listaCidades);
            }
            else if (valor==5){ //consulta de login
                Saida.writeObject(1);
                login = (String)Entrada.readObject();
                Saida.writeObject(1);
                senha = (String)Entrada.readObject();
                int retorno = con.ConsultaDocumentoLogin(login, senha);
                Saida.writeObject(retorno);
            }
            if(valor==6){ //consulta empresas
                ArrayList<Pessoa> listaPessoas = con.consultaPessoas();
                Saida.writeObject(listaPessoas);
            }
            if(valor==7){ //consultando solicitaçoes
                Saida.writeObject(1);
                int cod = (int)Entrada.readObject();
                ArrayList<Solicitacao> listaSolicitacao = con.consultaSolicitacoes(cod);
                Saida.writeObject(listaSolicitacao);
            }
            if(valor==8){ //consultando solicitaçoes
                Saida.writeObject(1);
                int cod = (int)Entrada.readObject();
                ArrayList<Solicitacao> listaSolicitacao = con.consultaSolicitacoesFull(cod);
                Saida.writeObject(listaSolicitacao);
            }
            if(valor==9){ //consultando entregas
                Saida.writeObject(1);
                int cod = (int)Entrada.readObject();
                ArrayList<Entrega> listaEntregas = con.consultaEntregas(cod);
                Saida.writeObject(listaEntregas);
            }
            if(valor==10){ //consulta entregas aguardando aprovação
                Saida.writeObject(1);
                int cod = (int)Entrada.readObject();
                ArrayList<Entrega> listaEntregas = con.consultaEntregasWaiting(cod);
                Saida.writeObject(listaEntregas);
            }
            if(valor==11){ //consulta entrgas por empresa
                Saida.writeObject(1);
                int cod = (int)Entrada.readObject();
                ArrayList<Entrega> listaEntregas = con.consultaEntregasEmpresas(cod);
                Saida.writeObject(listaEntregas);
            }
            if(valor==111){ //consulta pessoa com cod
                ArrayList<Pessoa> listaPessoas = con.consultaPessoasHumanos();
                Saida.writeObject(listaPessoas);
            }
            if(valor==112){ //consulta pessoa empresas
                ArrayList<Pessoa> listaPessoas = con.consultaPessoasEmpresas();
                Saida.writeObject(listaPessoas);
            }
            if(valor==113){ //consulta pessoa
                Saida.writeObject(1);
                int p = (int) Entrada.readObject();
                Pessoa minhaPessoa = con.consultaPessoaFull(p);
                Saida.writeObject(minhaPessoa);
            }
            if(valor==114){ //pesquisa objetos
                Saida.writeObject(1);
                String consulta = (String)Entrada.readObject();
                int[] codigo = con.consultaObjeto(consulta);
            }
            if(valor==115){ //pesquisa solicitacao
                Saida.writeObject(1);
                int cod = (int)Entrada.readObject();
                Solicitacao s = con.consultaSolicitacao(cod);
                Saida.writeObject(s);
            }
            if(valor==116){ //pesquisa uma unica empresa
                Saida.writeObject(1);
                int cod = (int)Entrada.readObject();
                String nome = con.consultaUmaEmpresa(cod);
                Saida.writeObject(nome);
            }
            if(valor==117){ //pesquisa todos enderecos
                ArrayList<Endereco> listaEnderecos = con.consultaEnderecosFull();
                Saida.writeObject(listaEnderecos);
            }
            
            
            if(valor==12){ //pessoa se cadastrando
                Saida.writeObject(1);
                Pessoa p;
                p = (Pessoa) Entrada.readObject();  
                int retorno = con.InserePessoa(p);
                Saida.writeObject(retorno);
            }
            if(valor==14){ //cadastrando endereço
                Saida.writeObject(1);
                Endereco e;
                e = (Endereco) Entrada.readObject();
                int retorno = con.insereEndereco(e);
                Saida.writeObject(retorno);
            }
            if(valor==15){ //cadastrando solicitação
                Saida.writeObject(1);
                Solicitacao sol;
                sol = (Solicitacao) Entrada.readObject();
                int retorno = con.insereSolicitacao(sol);
                Saida.writeObject(retorno);
            }
            if(valor==16){ //cadastrando entrega
                Saida.writeObject(1);
                Entrega ent;
                ent = (Entrega) Entrada.readObject();
                int retorno = con.insereEntrega(ent);
                Saida.writeObject(retorno);
            }

            
            
            if(valor==12345){//email com senha
                Saida.writeObject(1);
                long doc = (long)Entrada.readObject();
                System.err.println(doc);
                String[] recebido = con.consultaRecuperacao(doc);
                int retorno = con.enviaEmail(recebido);
                Saida.writeObject(retorno);
            }
            if(valor==12346){//envia o email
                Saida.writeObject(1);
                String[] dados = (String[])Entrada.readObject();
                int x = con.enviaEmail(dados);
                Saida.writeObject(x);
            }
            
            
            if(valor==21){//alterando usuario
                Saida.writeObject(1);
                Pessoa p = (Pessoa) Entrada.readObject();
                int retorno = con.alteraPessoa(p);
                Saida.writeObject(retorno);
            }
            if(valor==23){ //alterando endereço
                Saida.writeObject(1);
                Endereco e;
                e = (Endereco) Entrada.readObject();
                int retorno = con.alteraEndereco(e);
                Saida.writeObject(retorno);
            }
            if(valor==24){ //alterando solicitação
                Saida.writeObject(1);
                Solicitacao sol;
                sol = (Solicitacao) Entrada.readObject();
                int retorno = con.alteraSolicitacao(sol);
                Saida.writeObject(retorno);
            }
            if(valor==25){ //alterando situação
                Saida.writeObject(1);
                String sol;
                sol = ((String) Entrada.readObject());
                Saida.writeObject(1);
                String sol2 = ((String)Entrada.readObject());
                int retorno = con.alteraSituacao(sol,sol2);
                Saida.writeObject(retorno);
            }
            if(valor==26){ //alterando situação pela empresa
                Saida.writeObject(1);
                int sol,situacao;
                sol = ((int) Entrada.readObject());
                situacao = ((int)Entrada.readObject());
                int retorno = con.alteraSituacaoEmpresa(sol,situacao);
                Saida.writeObject(retorno);
            }
            if(valor==27){ //alterando senha
                Saida.writeObject(1);
                int cod = (int)Entrada.readObject();
                Saida.writeObject(1);
                String senhaUsu = (String)Entrada.readObject();
                int x = con.alteraSenha(cod,senhaUsu);
                Saida.writeObject(x);
            }
            
            
            
            
            if(valor==33){//exclui endereco
                Saida.writeObject(1);
                int cod = (int)Entrada.readObject();
                int retorno = con.excluiEndereco(cod);
                Saida.writeObject(retorno);
            }
            if(valor==34){//exclui usuario
                Saida.writeObject(1);
                int cod = (int)Entrada.readObject();
                int retorno = con.excluiUsuario(cod);
                Saida.writeObject(retorno);
            }
            if(valor==35){//exclui solicitação
                Saida.writeObject(1);
                int cod = (int)Entrada.readObject();
                int retorno = con.excluiSolicitacao(cod);
                Saida.writeObject(retorno);
            }
            
            
            }
            Entrada.close();
            
        } catch (IOException ex) {
            System.out.println("Conexão encerrada oeee!"+ex);
        } catch (ClassNotFoundException ex) {
            System.out.println("Nao achou a classe pessoa: "+ex);
        } catch (SQLException ex) {
            System.out.println("Deu erro ao conectar no banco mysql"+ex);
        }
    }
}