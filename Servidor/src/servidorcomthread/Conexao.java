package servidorcomthread;

import classesDominio.Cidade;
import classesDominio.Endereco;
import classesDominio.Entrega;
import classesDominio.Pessoa;
import classesDominio.Solicitacao;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class Conexao {

    String serverName = "localhost"; //endereço do banco, ip da máquina
    String mydatabase = "index";   // nome do banco
    String url = "jdbc:mysql://" + serverName + "/" + mydatabase; // string para chamar/conectar o banco
    //quando usar xamp, será "root" e senha vazia//
    String userName = "root"; // usuario do banco de dados
    String password = ""; //ifsul2016
    Connection conexao;

    Conexao() throws SQLException {
        conexao = DriverManager.getConnection(url, userName, password);
    }

    //*Inserções*//
    int InserePessoa(Pessoa P) {
        int caso = 0;
        int cod = 0;
        try {
            Conexao con = new Conexao();
            Statement st = conexao.createStatement();
            String sql="insert into usuarios (usu_nivel, usu_nome, usu_email, usu_documento, usu_login) values "
                    + "(" + P.getNivel() + ",'" + P.getNome() + "','" + P.getEmail() + "'," + P.getDoc() + ",'" + P.getLogin() + "');";
            System.out.println(sql);
            st.executeUpdate(sql);
            String consulta = ("select usu_cod from usuarios where usu_documento = " + P.getDoc() + ";");
            st.executeQuery(consulta);

            ResultSet rs = st.getResultSet();

            while (rs.next()) {
                cod = rs.getInt("usu_cod");
            }
            String senha = criptografa(P.getSenha(), cod);
            st.executeUpdate("update usuarios set usu_senha = '" + senha + "' where usu_cod = '" + cod + "';");
            caso = 1;
        } catch (Exception e) {
            System.out.println("Deu erro na inserção!" + e);
        }
        return caso;
    }

    int insereEndereco(Endereco e) {
        int retorno = 0;
        try {
            Conexao con = new Conexao();
            Statement st = conexao.createStatement();

            st.executeUpdate("insert into endereco(end_rua, end_numero, end_complemento, end_cid_fk, end_pes_cod) values"
                    + "('" + e.getRua() + "'," + e.getNumero() + ",'" + e.getComplemento() + "'," + e.getCod_cidade() + "," + e.getCod_pessoa() + ")");
            retorno = 1;
        } catch (SQLException ex) {
            System.out.println("Erro na inserção" + ex);
        }
        return retorno;
    }

    int insereSolicitacao(Solicitacao sol) {
        int retorno = 0;
        try {
            Conexao con = new Conexao();
            Statement st = conexao.createStatement();
            String sql="insert into solicitacao_de_envio(sol_nome, sol_descricao, sol_end_saida, sol_end_chegada, sol_pes_cod) values"
                    + "('" + sol.getNome() + "','" + sol.getDescricao() + "','" + sol.getCodEndSaida() + "'," + sol.getCodEndChegada() + "," + sol.getCodPessoa() + ")";
            System.out.println(sql);
            st.executeUpdate(sql);
            retorno = 1;
        } catch (SQLException ex) {
            System.out.println("Erro na inserção" + ex);
        }
        return retorno;
    }

    int insereEntrega(Entrega ent) {
        int retorno = 0;
        try {
            Conexao con = new Conexao();
            Statement st = conexao.createStatement();
            System.out.println("INSERT INTO entrega( ent_prazo, ent_valor, ent_empresa_fk, ent_situacao, ent_produto, ent_descricao, end_saida, end_chegada, end_pessoa, end_cod_solicitacao) VALUES (" + ent.getPrazo() + "," + ent.getValor() + "," + ent.getCodEmpresa() + "," + ent.getSituacao() + ",'" + ent.getNome() + "','" + ent.getDescricao() + "'," + ent.getSaida() + "," + ent.getChegada() + "," + ent.getCodPessoa() + "," + ent.getCodSolicitacao() + ");");
            st.executeUpdate("INSERT INTO entrega( ent_prazo, ent_valor, ent_empresa_fk, ent_situacao, ent_produto, ent_descricao, end_saida, end_chegada, end_pessoa, end_cod_solicitacao) VALUES (" + ent.getPrazo() + "," + ent.getValor() + "," + ent.getCodEmpresa() + "," + ent.getSituacao() + ",'" + ent.getNome() + "','" + ent.getDescricao() + "'," + ent.getSaida() + "," + ent.getChegada() + "," + ent.getCodPessoa() + "," + ent.getCodSolicitacao() + ");");
            retorno = 1;
        } catch (SQLException ex) {
            System.out.println("Erro na inserção" + ex);
        }
        return retorno;
    }

    //*Consultas*//
    int ConsultaDocumentoLogin(String login, String senha) {
        int codigoParaAutenticar = 0;
        String senhaBanco = "";
        int nivel = 0;
        int caso = 0;
        try {
            Conexao con = new Conexao();
            Statement st = conexao.createStatement();

            st.executeQuery("select usu_cod from usuarios where usu_login like '" + login + "';");
            ResultSet rs = st.getResultSet();
            if (rs.next()) {
                codigoParaAutenticar = rs.getInt("usu_cod");
            } else {
                caso = 1234567;
            }
            String senhaCripada = criptografa(senha, codigoParaAutenticar);
            st.executeQuery("select * from usuarios where usu_cod like '" + codigoParaAutenticar + "';");
            rs = st.getResultSet();
            if (rs.next()) {
                senhaBanco = rs.getString("usu_senha");
            } else {
                caso = 1234567;
            }
            if (senhaBanco.equals(senhaCripada)) {
                caso = codigoParaAutenticar;
            } else {
                caso = 1234567;
            }
        } catch (Exception e) {
            System.err.println("Nao rolou");
        }
        return caso;
    }

    String consultaPessoa(int codigo) {
        String nomePessoa = "";
        try {
            Conexao con = new Conexao();
            Statement st = conexao.createStatement();

            st.executeQuery("select usu_nome from usuarios where usu_cod = " + codigo + ";");
            ResultSet rs = st.getResultSet();
            if (rs.next()) {
                nomePessoa = rs.getString("usu_nome");
            }
        } catch (SQLException ex) {
            Logger.getLogger(Conexao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return nomePessoa;
    }

    int consultaNivel(int codigo) {
        int nivel = 0;
        try {
            Conexao con = new Conexao();
            Statement st = conexao.createStatement();

            st.executeQuery("select usu_nivel from usuarios where usu_cod like '" + codigo + "';");
            ResultSet rs = st.getResultSet();
            if (rs.next()) {
                nivel = rs.getInt("usu_nivel");
            }
        } catch (SQLException ex) {
            Logger.getLogger(Conexao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return nivel;
    }

    ArrayList<Endereco> consultaEnderecos(int codpes) {
        ArrayList<Endereco> listaEnderecos = new ArrayList<>();
        try {
            Conexao con = new Conexao();
            Statement st = conexao.createStatement();

            st.executeQuery("select * from endereco where end_pes_cod=" + codpes + ";");
            ResultSet rs = st.getResultSet();
            while (rs.next()) {
                int cod = rs.getInt("end_cod");
                String rua = rs.getString("end_rua");
                int numero = rs.getInt("end_numero");
                String comp = rs.getString("end_complemento");
                int cidade = rs.getInt("end_cid_fk");
                int pessoa = rs.getInt("end_pes_cod");
                Endereco meuEndereco = new Endereco(rua, numero, cidade, pessoa);
                meuEndereco.setNome_cidade(consultaCidade(cidade));
                meuEndereco.setComplemento(comp);
                meuEndereco.setCod(cod);
                listaEnderecos.add(meuEndereco);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Conexao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listaEnderecos;
    }

    ArrayList<Cidade> consultaCidades() {
        ArrayList<Cidade> listaCidades = new ArrayList<>();
        try {
            Conexao con = new Conexao();
            Statement st = conexao.createStatement();

            st.executeQuery("select * from cidade;");
            ResultSet rs = st.getResultSet();
            while (rs.next()) {
                int cod = rs.getInt("cid_cod");
                String nome = rs.getString("cid_nome");
                String uf = rs.getString("cid_uf");
                Cidade minhaCidade = new Cidade(cod, nome, uf);
                listaCidades.add(minhaCidade);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Conexao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listaCidades;
    }
    String consultaCidade(int cod){
        String cid="";
        try {
            Conexao con = new Conexao();
            Statement st = conexao.createStatement();

            st.executeQuery("SELECT * FROM cidade WHERE cid_cod="+cod+";");
            ResultSet rs = st.getResultSet();
            while (rs.next()) {
                
                cid = rs.getString("cid_nome");
               
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(Conexao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return cid;
    }
    ArrayList<Pessoa> consultaPessoas() {
        ArrayList<Pessoa> listaPessoas = new ArrayList<>();
        try {
            Conexao con = new Conexao();
            Statement st = conexao.createStatement();

            st.executeQuery("select * from usuarios where usu_nivel=" + 2 + ";");
            ResultSet rs = st.getResultSet();
            while (rs.next()) {
                int nivel = rs.getInt("usu_nivel");
                String nome = rs.getString("usu_nome");
                String email = rs.getString("usu_email");
                long doc = rs.getLong("usu_documento");
                String login = rs.getString("usu_login");
                String senha = rs.getString("usu_senha");
                int cod = rs.getInt("usu_cod");
                Pessoa minhaPessoa = new Pessoa(nivel, nome, email, doc, login, senha);
                minhaPessoa.setCod(cod);
                listaPessoas.add(minhaPessoa);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Conexao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listaPessoas;
    }
    String pegaNomeEndereco(int cod){
        String end="";
        try {
            Conexao con = new Conexao();
            Statement st = conexao.createStatement();
             String sql="SELECT end_rua FROM endereco WHERE end_cod="+cod+";";
            st.executeQuery(sql);
            ResultSet rs = st.getResultSet();
            while (rs.next()) {
                
                end = rs.getString("end_rua");
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(Conexao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return end;
    }
    ArrayList<Solicitacao> consultaSolicitacoes(int cod) {
        ArrayList<Solicitacao> listaSolicitacao = new ArrayList<>();
        try {
            Conexao con = new Conexao();
            Statement st = conexao.createStatement();

            st.executeQuery("select * from solicitacao_de_envio where sol_pes_cod=" + cod + ";");
            ResultSet rs = st.getResultSet();
            while (rs.next()) {
                int codSol = rs.getInt("sol_cod");
                String nome = rs.getString("sol_nome");
                String desc = rs.getString("sol_descricao");
                int solEndEntrada = rs.getInt("sol_end_chegada");
                int solEndSaida = rs.getInt("sol_end_saida");
                Solicitacao minhaSolicitacao = new Solicitacao(nome, desc, solEndSaida, solEndEntrada, cod);
                minhaSolicitacao.setCod(codSol);
                minhaSolicitacao.setNomeEndChegada(pegaNomeEndereco(solEndEntrada));
                minhaSolicitacao.setNomeEndSaida(pegaNomeEndereco(solEndSaida));
                listaSolicitacao.add(minhaSolicitacao);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Conexao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listaSolicitacao;
    }

    ArrayList<Solicitacao> consultaSolicitacoesFull(int cod) {
        ArrayList<Solicitacao> listaSolicitacao = new ArrayList<>();
        try {
            Conexao con = new Conexao();
            Statement st = conexao.createStatement();

            st.executeQuery("select * from solicitacao_de_envio;");
            ResultSet rs = st.getResultSet();
            while (rs.next()) {
                int codSol = rs.getInt("sol_cod");
                String nome = rs.getString("sol_nome");
                String desc = rs.getString("sol_descricao");
                int solEndEntrada = rs.getInt("sol_end_chegada");
                int solEndSaida = rs.getInt("sol_end_saida");
                int codigo = rs.getInt("sol_pes_cod");
                Solicitacao minhaSolicitacao = new Solicitacao(nome, desc, solEndSaida, solEndEntrada, codigo);
                minhaSolicitacao.setCod(codSol);
                minhaSolicitacao.setNomeEndChegada(pegaNomeEndereco(solEndEntrada));
                minhaSolicitacao.setNomeEndSaida(pegaNomeEndereco(solEndSaida));
                
                listaSolicitacao.add(minhaSolicitacao);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Conexao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listaSolicitacao;
    }
    String consultaEmpresa(int cod){
        String nome="";
        try {
            Conexao con = new Conexao();
            Statement st = conexao.createStatement();

            st.executeQuery("SELECT usu_nome FROM `usuarios` WHERE usu_cod="+cod+";");
            ResultSet rs = st.getResultSet();
            while (rs.next()) {
                nome=rs.getString("usu_nome");
            }
        } catch (SQLException ex) {
            Logger.getLogger(Conexao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return nome;
    }

    ArrayList<Entrega> consultaEntregas(int cod) {
        ArrayList<Entrega> listaEntregas = new ArrayList<>();
        try {
            Conexao con = new Conexao();
            Statement st = conexao.createStatement();

            st.executeQuery("select * from entrega where end_pessoa = " + cod + ";");
            ResultSet rs = st.getResultSet();
            while (rs.next()) {
                int codinho = rs.getInt("ent_cod");
                int codSol = rs.getInt("end_cod_solicitacao");
                float valor = rs.getFloat("ent_valor");
                int prazo = rs.getInt("ent_prazo");
                int empresa = rs.getInt("ent_empresa_fk");
                int situacao = rs.getInt("ent_situacao");
                String produto = rs.getString("ent_produto");
                String descricao = rs.getString("ent_descricao");
                int entrada = rs.getInt("end_chegada");
                int saida = rs.getInt("end_saida");
                int pessoa = rs.getInt("end_pessoa");
                int solicitacao = rs.getInt("end_cod_solicitacao");

                Entrega minhaEntrega = new Entrega(prazo, valor, empresa, situacao, produto, descricao, saida, entrada, pessoa);
                minhaEntrega.setCod(codinho);
                minhaEntrega.setCodSolicitacao(solicitacao);
                minhaEntrega.setNome_empresa(consultaEmpresa(empresa));
                listaEntregas.add(minhaEntrega);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Conexao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listaEntregas;
    }

    ArrayList<Entrega> consultaEntregasWaiting(int cod) {
        ArrayList<Entrega> listaEntregas = new ArrayList<>();
        try {
            Conexao con = new Conexao();
            Statement st = conexao.createStatement();

            st.executeQuery("select * from entrega where ent_situacao=1 and end_pessoa=" + cod + ";");
            ResultSet rs = st.getResultSet();
            while (rs.next()) {
                int codinho = rs.getInt("ent_cod");
                int codSol = rs.getInt("end_cod_solicitacao");
                float valor = rs.getFloat("ent_valor");
                int prazo = rs.getInt("ent_prazo");
                int empresa = rs.getInt("ent_empresa_fk");
                int situacao = rs.getInt("ent_situacao");
                String produto = rs.getString("ent_produto");
                String descricao = rs.getString("ent_descricao");
                int entrada = rs.getInt("end_chegada");
                int saida = rs.getInt("end_saida");
                int pessoa = rs.getInt("end_pessoa");
                int solicitacao = rs.getInt("end_cod_solicitacao");

                Entrega minhaEntrega = new Entrega(prazo, valor, empresa, situacao, produto, descricao, saida, entrada, pessoa);
                minhaEntrega.setCod(codinho);
                minhaEntrega.setCodSolicitacao(solicitacao);
                listaEntregas.add(minhaEntrega);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Conexao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listaEntregas;
    }

    ArrayList<Entrega> consultaEntregasEmpresas(int cod) {
        ArrayList<Entrega> listaEntregas = new ArrayList<>();
        try {
            Conexao con = new Conexao();
            Statement st = conexao.createStatement();

            st.executeQuery("select * from entrega where ent_empresa_fk=" + cod + " and ent_situacao>1 and ent_situacao<4;");
            ResultSet rs = st.getResultSet();
            while (rs.next()) {
                int codinho = rs.getInt("ent_cod");
                int codSol = rs.getInt("end_cod_solicitacao");
                float valor = rs.getFloat("ent_valor");
                int prazo = rs.getInt("ent_prazo");
                int empresa = rs.getInt("ent_empresa_fk");
                int situacao = rs.getInt("ent_situacao");
                String produto = rs.getString("ent_produto");
                String descricao = rs.getString("ent_descricao");
                int entrada = rs.getInt("end_chegada");
                int saida = rs.getInt("end_saida");
                int pessoa = rs.getInt("end_pessoa");
                int solicitacao = rs.getInt("end_cod_solicitacao");

                Entrega minhaEntrega = new Entrega(prazo, valor, empresa, situacao, produto, descricao, saida, entrada, pessoa);
                minhaEntrega.setCod(codinho);
                minhaEntrega.setCodSolicitacao(solicitacao);
                listaEntregas.add(minhaEntrega);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Conexao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listaEntregas;
    }

    ArrayList<Pessoa> consultaPessoasHumanos() {
        ArrayList<Pessoa> listaPessoas = new ArrayList<>();
        try {
            Conexao con = new Conexao();
            Statement st = conexao.createStatement();

            st.executeQuery("select * from usuarios where usu_nivel=" + 1 + ";");
            ResultSet rs = st.getResultSet();
            while (rs.next()) {
                int nivel = rs.getInt("usu_nivel");
                String nome = rs.getString("usu_nome");
                String email = rs.getString("usu_email");
                long doc = rs.getLong("usu_documento");
                String login = rs.getString("usu_login");
                String senha = rs.getString("usu_senha");
                int cod = rs.getInt("usu_cod");
                Pessoa minhaPessoa = new Pessoa(nivel, nome, email, doc, login, senha);
                minhaPessoa.setCod(cod);
                listaPessoas.add(minhaPessoa);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Conexao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listaPessoas;
    }

    String[] consultaRecuperacao(long doc) {
        String[] retorno = new String[3];
        String docString = doc + "";
        try {
            Conexao con = new Conexao();
            Statement st = conexao.createStatement();

            st.executeQuery("select * from usuarios where usu_documento=" + docString + ";");
            ResultSet rs = st.getResultSet();
            if (rs.next()) {
                String nome = rs.getString("usu_nome");
                String email = rs.getString("usu_email");
                String senha = rs.getString("usu_senha");
                int cod = rs.getInt("usu_cod");
                String senhaDescriptografada = descriptografa(senha, cod);
                retorno[0] = nome;
                System.err.println(retorno[0]);
                retorno[1] = email;
                retorno[2] = senhaDescriptografada;
            } else {
                retorno[0] = "VAZIO";
            }
        } catch (SQLException ex) {
            Logger.getLogger(Conexao.class.getName()).log(Level.SEVERE, null, ex);
        }

        return retorno;
    }

    ArrayList<Pessoa> consultaPessoasEmpresas() {
        ArrayList<Pessoa> listaPessoas = new ArrayList<>();
        try {
            Conexao con = new Conexao();
            Statement st = conexao.createStatement();

            st.executeQuery("select * from usuarios where usu_nivel=" + 2 + ";");
            ResultSet rs = st.getResultSet();
            while (rs.next()) {
                int nivel = rs.getInt("usu_nivel");
                String nome = rs.getString("usu_nome");
                String email = rs.getString("usu_email");
                long doc = rs.getLong("usu_documento");
                String login = rs.getString("usu_login");
                String senha = rs.getString("usu_senha");
                int cod = rs.getInt("usu_cod");
                Pessoa minhaPessoa = new Pessoa(nivel, nome, email, doc, login, senha);
                minhaPessoa.setCod(cod);
                listaPessoas.add(minhaPessoa);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Conexao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listaPessoas;
    }

    Pessoa consultaPessoaFull(int cod) {
        Pessoa minhaPessoa = null;

        try {
            Conexao con = new Conexao();
            Statement st = conexao.createStatement();

            st.executeQuery("select * from usuarios where usu_cod=" + cod + ";");
            ResultSet rs = st.getResultSet();
            while (rs.next()) {
                int nivel = rs.getInt("usu_nivel");
                String nome = rs.getString("usu_nome");
                String email = rs.getString("usu_email");
                long doc = rs.getLong("usu_documento");
                String login = rs.getString("usu_login");
                String senha = rs.getString("usu_senha");
                minhaPessoa = new Pessoa(nivel, nome, email, doc, login, senha);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Conexao.class.getName()).log(Level.SEVERE, null, ex);
        }

        return minhaPessoa;
    }

    int[] consultaObjeto(String pesquisa) {
        int[] pesquisado = null;
        int x = 0;

        try {
            Conexao con = new Conexao();
            Statement st = conexao.createStatement();

            st.executeQuery("select sol_cod from solicitacao_de_envio where sol_nome like '%" + pesquisa + "%';");
            ResultSet rs = st.getResultSet();
            if (rs.next()) {
                while (rs.next()) {
                    int cod = rs.getInt("sol_cod");
                    pesquisado[x] = cod;
                    x++;
                }
            }
            if (!rs.next()) {
                pesquisado[0] = 1;
            }

        } catch (SQLException ex) {
            Logger.getLogger(Conexao.class.getName()).log(Level.SEVERE, null, ex);
        }

        return pesquisado;
    }

    Solicitacao consultaSolicitacao(int cod) {
        Solicitacao minhaSolicitacao = null;
        try {
            Conexao con = new Conexao();
            Statement st = conexao.createStatement();

            st.executeQuery("select * from solicitacao_de_envio where sol_cod=" + cod + ";");
            ResultSet rs = st.getResultSet();
            while (rs.next()) {
                int codSol = rs.getInt("sol_cod");
                int pesCod = rs.getInt("sol_pes_cod");
                String nome = rs.getString("sol_nome");
                String desc = rs.getString("sol_descricao");
                int solEndEntrada = rs.getInt("sol_end_chegada");
                int solEndSaida = rs.getInt("sol_end_saida");
                minhaSolicitacao = new Solicitacao(nome, desc, solEndSaida, solEndEntrada, pesCod);
                minhaSolicitacao.setCod(codSol);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Conexao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return minhaSolicitacao;
    }

    String consultaUmaEmpresa(int cod) {
        String nomeCidade = null;
        try {
            Conexao con = new Conexao();
            Statement st = conexao.createStatement();

            st.executeQuery("select usu_nome from usuarios where usu_cod=" + cod + ";");
            ResultSet rs = st.getResultSet();
            while (rs.next()) {
                int codSol = rs.getInt("sol_cod");
            }
        } catch (SQLException ex) {
            Logger.getLogger(Conexao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return nomeCidade;
    }

    ArrayList<Endereco> consultaEnderecosFull() {
        ArrayList<Endereco> listaEnderecos = new ArrayList<>();
        String compl = "--";
        try {
            Conexao con = new Conexao();
            Statement st = conexao.createStatement();

            st.executeQuery("select * from endereco;");
            ResultSet rs = st.getResultSet();
            while (rs.next()) {
                int cod = rs.getInt("end_cod");
                String rua = rs.getString("end_rua");
                int numero = rs.getInt("end_numero");
                String comp = rs.getString("end_complemento");
                int cidade = rs.getInt("end_cid_fk");
                int pessoa = rs.getInt("end_pes_cod");
                Endereco meuEndereco = new Endereco(rua, numero, cidade, pessoa);
                meuEndereco.setComplemento(comp);
                meuEndereco.setCod(cod);
                listaEnderecos.add(meuEndereco);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Conexao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listaEnderecos;
    }

    //*Updates*//
    int alteraEndereco(Endereco e) {
        int retorno = 0;
        try {
            Conexao con = new Conexao();
            Statement st = conexao.createStatement();
            st.executeUpdate("update endereco set end_rua = '" + e.getRua() + "',end_numero = " + e.getNumero() + ""
                    + ",end_complemento = '" + e.getComplemento() + "',end_cid_fk = " + e.getCod_cidade() + " where end_cod = '" + e.getCod() + "';");
            retorno = 1;
        } catch (SQLException ex) {
            Logger.getLogger(Conexao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return retorno;
    }

    int alteraPessoa(Pessoa p) {
        int retorno = 0;
        try {
            Conexao con = new Conexao();
            Statement st = conexao.createStatement();
            st.executeUpdate("update usuarios set usu_documento = '" + p.getDoc() + "',usu_nome = '" + p.getNome() + "',usu_email = '" + p.getEmail() + "',usu_login = '" + p.getLogin() + "',usu_senha = '" + p.getSenha() + "' where usu_cod = '" + p.getCod() + "';");
            retorno = 1;
        } catch (SQLException ex) {
            Logger.getLogger(Conexao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return retorno;
    }

    int alteraSolicitacao(Solicitacao sol) {
        int retorno = 0;
        try {
            Conexao con = new Conexao();
            Statement st = conexao.createStatement();
            st.executeUpdate("update solicitacao_de_envio set sol_nome = '" + sol.getNome() + "',sol_descricao = '" + sol.getDescricao() + "'"
                    + ",sol_end_saida = '" + sol.getCodEndSaida() + "',sol_end_chegada = " + sol.getCodEndChegada() + " where sol_cod = '" + sol.getCod() + "';");
            retorno = 1;
        } catch (SQLException ex) {
            Logger.getLogger(Conexao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return retorno;
    }

    int alteraSituacao(String cod, String cod2) {
        int ok = 0;
        try {
            Conexao con = new Conexao();
            Statement st = conexao.createStatement();
            st.executeUpdate("update entrega set ent_situacao = 2 where ent_cod=" + cod + ";");
            st.executeUpdate("delete from entrega where ent_situacao = 1 and end_cod_solicitacao = " + cod2 + ";");
            st.executeUpdate("delete from solicitacao_de_envio where sol_cod=" + cod2 + ";");
            ok = 1;
        } catch (SQLException ex) {
            Logger.getLogger(Conexao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ok;
    }

    int alteraSituacaoEmpresa(int cod, int situacao) {
        int ok = 0;
        try {
            Conexao con = new Conexao();
            Statement st = conexao.createStatement();
            st.executeUpdate("update entrega set ent_situacao = " + situacao + " where ent_cod=" + cod + ";");
            ok = 1;
        } catch (SQLException ex) {
            Logger.getLogger(Conexao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ok;
    }

    int alteraSenha(int cod, String senha) {
        int ok = 0;
        try {
            Conexao con = new Conexao();
            Statement st = conexao.createStatement();
            String senhaPronta = criptografa(senha, cod);
            st.executeUpdate("update usuarios set usu_senha = '" + senhaPronta + "' where usu_cod=" + cod + ";");
            ok = 1;
        } catch (SQLException ex) {
            Logger.getLogger(Conexao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ok;
    }

    //*Delets*//
    int excluiEndereco(int cod) {
        int retorno = 0;

        int conf = 0;
        try {
            Conexao con = new Conexao();
            Statement st = conexao.createStatement();

            st.executeQuery("select * from solicitacao_de_envio where sol_end_saida = " + cod);
            ResultSet rs = st.getResultSet();
            if (rs.next()) {
                conf = 1;
            }

            st.executeQuery("select * from solicitacao_de_envio where sol_end_chegada = " + cod);
            rs = st.getResultSet();
            if (rs.next()) {
                conf = 1;
            }
        } catch (SQLException ex) {
            System.err.println("Deu pau no MYSQL!" + ex);
        }

        if (conf == 0) {
            try {
                Conexao con = new Conexao();
                Statement st = conexao.createStatement();
                st.executeUpdate("delete from endereco where end_cod = " + cod);
                retorno = 1;
            } catch (SQLException ex) {
                System.out.println("deu erro no DELETS ");
            }
        } else {
            retorno = 0;
        }

        return retorno;
    }

    int excluiUsuario(int cod) {
        int retorno = 0;

        int conf = 0;
        try {
            Conexao con = new Conexao();
            Statement st = conexao.createStatement();

            st.executeQuery("select * from solicitacao_de_envio where sol_pes_cod = " + cod);
            ResultSet rs = st.getResultSet();
            if (rs.next()) {
                conf = 1;
            }

            st.executeQuery("select * from endereco where end_pes_cod = " + cod);
            rs = st.getResultSet();
            if (rs.next()) {
                conf = 1;
            }
        } catch (SQLException ex) {
            System.err.println("Deu pau no MYSQL!" + ex);
        }

        if (conf == 0) {
            try {
                Conexao con = new Conexao();
                Statement st = conexao.createStatement();
                st.executeUpdate("delete from usuarios where usu_cod = " + cod);
                retorno = 1;
            } catch (SQLException ex) {
                System.out.println("deu erro no DELETS ");
            }
        } else {
            retorno = 0;
        }

        return retorno;
    }

    int excluiSolicitacao(int cod) {
        int retorno = 0;

        int conf = 0;
        try {
            Conexao con = new Conexao();
            Statement st = conexao.createStatement();

            st.executeQuery("select * from entrega where ent_sol_fk = " + cod);
            ResultSet rs = st.getResultSet();
            if (rs.next()) {
                conf = 1;
            }
        } catch (SQLException ex) {
            System.err.println("Deu pau no MYSQL!" + ex);
        }

        if (conf == 0) {
            try {
                Conexao con = new Conexao();
                Statement st = conexao.createStatement();
                st.executeUpdate("delete from solicitacao_de_envio where sol_cod = " + cod);
                retorno = 1;
            } catch (SQLException ex) {
                System.out.println("deu erro no DELETS ");
            }
        } else {
            retorno = 0;
        }

        return retorno;
    }

    static public String criptografa(String senha, int chave) {
        boolean x = false;
        int indiceSenha = 0, indiceAlfabeto = 0, numAlfabeto = 1, tamanhoSenha = senha.length();
        String senhaPronta = "";
        String[] embaralhado1 = {"N", "f", "$", "M", "K", "L", "2", "*", "t", "P", "D", "a", "@", "W", "g", "U", "j", "H", "h", "s", "-", "d", "F", "9", "k", "J", "G", "0", "X", "q", "z", "x", "5", "Z", "S", "T", "u", "&", "p", "6", "n", "%", "O", "1", "+", "7", "4", "C", "R", "o", "y", "Q", "A", "v", "b", "#", "e", "r", "c", "3", "Y", "w", "V", "i", "8", "l", "m", "I", "B", "E"};
        String[] embaralhado2 = {"0", "H", "X", "6", "4", "g", "Q", "B", "n", "o", "R", "m", "D", "L", "7", "e", "2", "b", "5", "8", "t", "#", "f", "*", "9", "P", "p", "V", "O", "Y", "@", "$", "w", "d", "k", "j", "z", "U", "l", "c", "W", "T", "S", "K", "x", "I", "u", "Z", "+", "G", "3", "v", "h", "&", "r", "a", "F", "1", "J", "E", "C", "s", "q", "y", "N", "M", "A", "%", "-", "i"};
        String[] embaralhado3 = {"u", "F", "R", "*", "1", "C", "r", "y", "@", "w", "L", "%", "m", "j", "n", "G", "#", "&", "9", "H", "U", "6", "g", "Q", "M", "l", "b", "t", "D", "f", "P", "Z", "+", "W", "2", "S", "X", "4", "v", "x", "V", "q", "s", "8", "-", "d", "e", "7", "0", "J", "3", "O", "$", "c", "p", "h", "5", "E", "Y", "T", "I", "z", "K", "N", "B", "i", "A", "a", "o", "k"};
        String[] usando = new String[70];
        for (int i = 0; i < senha.length(); i++) {
            while (x == false) {
                //selecionando alfabeto para o caractere
                boolean b = false;
                while (b == false) {
                    if (numAlfabeto == 1) {
                        usando = embaralhado1;
                        b = true;
                    } else if (numAlfabeto == 2) {
                        usando = embaralhado2;
                        b = true;
                    } else if (numAlfabeto == 3) {
                        usando = embaralhado3;
                        b = true;
                    } else if (numAlfabeto > 3) {
                        numAlfabeto -= 3;
                    }
                }
                //separando cada caractere
                if (senha.length() <= indiceSenha) {
                    x = true;
                    break;
                }
                char caractereSenha = senha.charAt(indiceSenha);
                String caractereSenhaString = "" + caractereSenha;
                if (caractereSenhaString.equals(usando[indiceAlfabeto])) {
                    int indiceCaractereCriptografado = indiceAlfabeto + chave;
                    if (indiceCaractereCriptografado > 69) {
                        indiceCaractereCriptografado -= 70;
                    }
                    senhaPronta = senhaPronta + usando[indiceCaractereCriptografado];
                    indiceSenha++;
                    numAlfabeto++;
                    indiceAlfabeto = 0;

                } else {
                    indiceAlfabeto++;
                }
            }
        }
        return senhaPronta;
    }

    static public String descriptografa(String senha, int chave) {
        boolean x = false;
        int indiceSenha = 0, indiceAlfabeto = 0, numAlfabeto = 1, tamanhoSenha = senha.length();
        String senhaPronta = "";
        String[] embaralhado1 = {"N", "f", "$", "M", "K", "L", "2", "*", "t", "P", "D", "a", "@", "W", "g", "U", "j", "H", "h", "s", "-", "d", "F", "9", "k", "J", "G", "0", "X", "q", "z", "x", "5", "Z", "S", "T", "u", "&", "p", "6", "n", "%", "O", "1", "+", "7", "4", "C", "R", "o", "y", "Q", "A", "v", "b", "#", "e", "r", "c", "3", "Y", "w", "V", "i", "8", "l", "m", "I", "B", "E"};
        String[] embaralhado2 = {"0", "H", "X", "6", "4", "g", "Q", "B", "n", "o", "R", "m", "D", "L", "7", "e", "2", "b", "5", "8", "t", "#", "f", "*", "9", "P", "p", "V", "O", "Y", "@", "$", "w", "d", "k", "j", "z", "U", "l", "c", "W", "T", "S", "K", "x", "I", "u", "Z", "+", "G", "3", "v", "h", "&", "r", "a", "F", "1", "J", "E", "C", "s", "q", "y", "N", "M", "A", "%", "-", "i"};
        String[] embaralhado3 = {"u", "F", "R", "*", "1", "C", "r", "y", "@", "w", "L", "%", "m", "j", "n", "G", "#", "&", "9", "H", "U", "6", "g", "Q", "M", "l", "b", "t", "D", "f", "P", "Z", "+", "W", "2", "S", "X", "4", "v", "x", "V", "q", "s", "8", "-", "d", "e", "7", "0", "J", "3", "O", "$", "c", "p", "h", "5", "E", "Y", "T", "I", "z", "K", "N", "B", "i", "A", "a", "o", "k"};
        String[] usando = new String[70];
        for (int i = 0; i < senha.length(); i++) {
            while (x == false) {
                //selecionando alfabeto para o caractere
                boolean b = false;
                while (b == false) {
                    if (numAlfabeto == 1) {
                        usando = embaralhado1;
                        b = true;
                    } else if (numAlfabeto == 2) {
                        usando = embaralhado2;
                        b = true;
                    } else if (numAlfabeto == 3) {
                        usando = embaralhado3;
                        b = true;
                    } else if (numAlfabeto > 3) {
                        numAlfabeto -= 3;
                    }
                }
                //separando cada caractere
                if (senha.length() <= indiceSenha) {
                    x = true;
                    break;
                }
                char caractereSenha = senha.charAt(indiceSenha);
                String caractereSenhaString = "" + caractereSenha;
                if (caractereSenhaString.equals(usando[indiceAlfabeto])) {
                    int indiceCaractereDescriptografado = indiceAlfabeto - chave;
                    if (indiceCaractereDescriptografado > 69) {
                        indiceCaractereDescriptografado -= 70;
                    } else if (indiceCaractereDescriptografado < 0) {
                        indiceCaractereDescriptografado += 70;
                    }
                    senhaPronta = senhaPronta + usando[indiceCaractereDescriptografado];
                    indiceSenha++;
                    numAlfabeto++;
                    indiceAlfabeto = 0;

                } else {
                    indiceAlfabeto++;
                }
            }
        }
        return senhaPronta;
    }

    int enviaEmail(String[] dados) {
        int retorno = 0;
        try {
            Email envia = new Email();
            envia.sendMail("logisticsindex@gmail.com", dados[1], "InDEX no-reply", dados[0] + " vê se não esquece mais, segue a senha:" + dados[2]);
            retorno = 1;
        } catch (Exception e) {
            System.out.println("Erro: " + e);
        }
        return retorno;
    }
}
