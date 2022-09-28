package clientecomthread;

import classesDominio.Cidade;
import classesDominio.Endereco;
import classesDominio.Pessoa;
import static clientecomthread.TelaLogin.Entrada;
import static clientecomthread.TelaLogin.Saida;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Luis
 */
public class CadastrarEmpresa extends javax.swing.JFrame {

    DefaultTableModel model;
    int global = 0;

    /**
     * Creates new form CadastrarEmpresa
     */
    public CadastrarEmpresa() {
        initComponents();
    }

    public void mudaBotoes(boolean cadastrar, boolean excluir, boolean alterar, boolean salvar, boolean canelar) {
        jBCadastrar.setEnabled(cadastrar);
        jBExcluir.setEnabled(excluir);
        jBAlterar.setEnabled(alterar);
        jBSalvar.setEnabled(salvar);
        jBCancelar.setEnabled(canelar);
    }

    public void consulta() {
        jTFCod.setVisible(false);
        model = (DefaultTableModel) jTable1.getModel();
        model.setNumRows(0);
        try {
            Saida.writeObject(6);
            ArrayList<Pessoa> listaPessoas = (ArrayList<Pessoa>) Entrada.readObject();
            for (int x = 0; x < listaPessoas.size(); x++) {
                Pessoa minhaPessoa = listaPessoas.get(x);
                String cod = minhaPessoa.getCod() + "";
                String nome = minhaPessoa.getNome();
                String doc = minhaPessoa.getDoc()+"";
                String email = minhaPessoa.getEmail();
                String login = minhaPessoa.getLogin();
                String senha = minhaPessoa.getSenha();
                model.addRow(new Object[]{nome,doc,email,login,cod,senha});//carrega as empresas
            }
        } catch (IOException ex) {
            Logger.getLogger(CadastrarEndereco.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(CadastrarEndereco.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void limpa() {
        jTFNomeEmpresa.setText("");
        jTFDocumento.setText("");
        jTFEmail.setText("");
        jTFLogin.setText("");
        jTFSenha.setText("");
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jBBack = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jTFSenha = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jTFNomeEmpresa = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jTFDocumento = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jTFEmail = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jTFLogin = new javax.swing.JTextField();
        jTFCod = new javax.swing.JTextField();
        jBCadastrar = new javax.swing.JButton();
        jBExcluir = new javax.swing.JButton();
        jBAlterar = new javax.swing.JButton();
        jBSalvar = new javax.swing.JButton();
        jBCancelar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        jBBack.setText("<<");
        jBBack.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(248, 248, 248), 2, true));
        jBBack.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBBackActionPerformed(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(248, 248, 248));

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel1.setText("Cadastro e Edição de Empresas");

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel6.setText("Senha:");

        jTFSenha.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 153, 102), 2, true));

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel2.setText("Nome:");

        jTFNomeEmpresa.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 153, 102), 2, true));

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel3.setText("CNPJ:");

        jTFDocumento.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 153, 102), 2, true));

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel4.setText("Email:");

        jTFEmail.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 153, 102), 2, true));

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel5.setText("Login:");

        jTFLogin.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 153, 102), 2, true));

        jBCadastrar.setText("Cadastrar");
        jBCadastrar.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(248, 248, 248), 2, true));
        jBCadastrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBCadastrarActionPerformed(evt);
            }
        });

        jBExcluir.setText("Excluir");
        jBExcluir.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(248, 248, 248), 2, true));
        jBExcluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBExcluirActionPerformed(evt);
            }
        });

        jBAlterar.setText("Alterar");
        jBAlterar.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(248, 248, 248), 2, true));
        jBAlterar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBAlterarActionPerformed(evt);
            }
        });

        jBSalvar.setText("Salvar");
        jBSalvar.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(248, 248, 248), 2, true));
        jBSalvar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBSalvarActionPerformed(evt);
            }
        });

        jBCancelar.setText("Cancelar");
        jBCancelar.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(248, 248, 248), 2, true));
        jBCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBCancelarActionPerformed(evt);
            }
        });

        jTable1.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 153, 102), 2, true));
        jTable1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Nome", "CNPJ", "Email", "Login", "cod", "senha"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.String.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jTFCod, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(53, 53, 53))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(51, 51, 51)
                        .addComponent(jLabel1))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(33, 33, 33)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel6)
                            .addComponent(jLabel5)
                            .addComponent(jLabel4)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3)
                            .addComponent(jTFNomeEmpresa)
                            .addComponent(jTFDocumento)
                            .addComponent(jTFEmail)
                            .addComponent(jTFLogin)
                            .addComponent(jTFSenha, javax.swing.GroupLayout.PREFERRED_SIZE, 302, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jBCadastrar, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jBExcluir, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jBAlterar, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jBSalvar, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jBCancelar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 419, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(jTFCod, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel1)
                .addGap(43, 43, 43)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTFNomeEmpresa, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTFDocumento, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTFEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTFLogin, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTFSenha, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 32, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jBCancelar)
                    .addComponent(jBSalvar)
                    .addComponent(jBAlterar)
                    .addComponent(jBExcluir)
                    .addComponent(jBCadastrar))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(464, 464, 464)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 232, Short.MAX_VALUE)
                .addComponent(jBBack, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(77, 77, 77))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(54, 54, 54)
                .addComponent(jBBack)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        setSize(new java.awt.Dimension(1274, 682));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jBBackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBBackActionPerformed
        MenuAdmin t = new MenuAdmin();
        t.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jBBackActionPerformed

    private void jBCadastrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBCadastrarActionPerformed
        mudaBotoes(false, false, false, true, true);
        global = 1;
    }//GEN-LAST:event_jBCadastrarActionPerformed

    private void jBExcluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBExcluirActionPerformed
        int opcao = JOptionPane.showConfirmDialog(null, "Deseja realmente apagar " + jTFNomeEmpresa.getText() + " ?");
        if (opcao == 0) {
            try {
                Saida.writeObject(34);//excluindo empresas
                int teste = (int) Entrada.readObject();
                if (teste == 1) {
                    int p = jTable1.getSelectedRow();
                    int cod = Integer.parseInt((String) jTable1.getValueAt(p, 4));
                    Saida.writeObject(cod);
                    int testeFinal = (int) Entrada.readObject();
                    if (testeFinal == 1) {
                        JOptionPane.showMessageDialog(rootPane, "Usuario excluido");
                    } else {
                        JOptionPane.showMessageDialog(rootPane, "Esse usuario não pode ser excluido, pois está ligado à algum endereco");
                    }
                }
            } catch (IOException ex) {
                Logger.getLogger(CadastrarEndereco.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(CadastrarEndereco.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        consulta();
    }//GEN-LAST:event_jBExcluirActionPerformed

    private void jBAlterarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBAlterarActionPerformed
        mudaBotoes(false, false, false, true, true);
        jTable1.setEnabled(false);
        jTFSenha.setEnabled(false);
        global = 2;
    }//GEN-LAST:event_jBAlterarActionPerformed

    private void jBSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBSalvarActionPerformed
        if (global == 1) { //testes para validar os campos
            if (jTFNomeEmpresa.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "Digite o nome, nao pode ser nulo");
            } else if (jTFDocumento.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "Digite o documento, nao pode ser nulo");
            } else if (jTFEmail.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "Digite um email.");
            } else if (jTFLogin.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "Digite um login.");
            } else if (jTFSenha.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "Digite uma senha.");
            } else {
                    String nome = jTFNomeEmpresa.getText();
                    long doc = Long.parseLong(jTFDocumento.getText());
                    String email = jTFEmail.getText();
                    String login = jTFLogin.getText();
                    String senha = jTFSenha.getText();
                    Pessoa p = new Pessoa(2, nome, email, doc, login, senha);//criando objeto pessoa para cadastro
                    try {
                        Saida.writeObject(12);
                        int ok = (int) Entrada.readObject();
                        if (ok == 1) {
                            Saida.writeObject(p);
                            int retorno = (int) Entrada.readObject();
                            if (retorno != 0) {
                                JOptionPane.showMessageDialog(rootPane, "Cadastro realizado com sucesso!");
                            } else {
                                JOptionPane.showMessageDialog(rootPane, "Cadastro deu erro!");
                            }
                        } else {
                            JOptionPane.showMessageDialog(rootPane, "Servidor não respondeu, tente mais tarde!");
                        }
                    } catch (IOException ex) {
                        System.out.println("Deu merda");
                    } catch (ClassNotFoundException ex) {
                        Logger.getLogger(TelaCadastroPessoa.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }else {
            if (jTFNomeEmpresa.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "Digite o nome, nao pode ser nulo");
            } else if (jTFDocumento.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "Digite o documento, nao pode ser nulo");
            } else if (jTFEmail.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "Digite um email.");
            } else if (jTFLogin.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "Digite um login.");
            } else {
                    String nome = jTFNomeEmpresa.getText();
                    long doc = Long.parseLong(jTFDocumento.getText());
                    String email = jTFEmail.getText();
                    String login = jTFLogin.getText();
                    String senha = jTFSenha.getText();
                    int cod = Integer.parseInt(jTFCod.getText());
                    Pessoa p = new Pessoa(2, nome, email, doc, login, senha);
                    p.setCod(cod);
                try {
                    Saida.writeObject(21);
                    int teste = ((int) Entrada.readObject());
                    if(teste==1){
                        Saida.writeObject(p);
                        int maisUmTeste = (int)Entrada.readObject();
                        if(maisUmTeste==1){
                            JOptionPane.showMessageDialog(rootPane, "Empresa editada com sucesso");
                        } else {
                            JOptionPane.showMessageDialog(rootPane, "Erro na edição");
                        }
                    }
                } catch (IOException ex) {
                    Logger.getLogger(CadastrarEndereco.class.getName()).log(Level.SEVERE, null, ex);
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(CadastrarEndereco.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

        }
            mudaBotoes(true, true, true, false, false);
            jTable1.setEnabled(true);
            consulta();
            limpa();
    }//GEN-LAST:event_jBSalvarActionPerformed

    private void jBCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBCancelarActionPerformed
        mudaBotoes(true, true, true, false, false);
        jTable1.setEnabled(true);
        limpa();
    }//GEN-LAST:event_jBCancelarActionPerformed

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        mudaBotoes(true, true, true, false, false);
        consulta();
    }//GEN-LAST:event_formWindowOpened

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        int p = jTable1.getSelectedRow();
        jTFNomeEmpresa.setText((String) jTable1.getValueAt(p, 0));
        jTFDocumento.setText((String) jTable1.getValueAt(p, 1));
        jTFEmail.setText((String) jTable1.getValueAt(p, 2));
        jTFLogin.setText((String) jTable1.getValueAt(p, 3));
        jTFCod.setText((String) jTable1.getValueAt(p, 4));
        jTFSenha.setText(((String) jTable1.getValueAt(p, 5)));
    }//GEN-LAST:event_jTable1MouseClicked

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(CadastrarEmpresa.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(CadastrarEmpresa.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(CadastrarEmpresa.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(CadastrarEmpresa.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new CadastrarEmpresa().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jBAlterar;
    private javax.swing.JButton jBBack;
    private javax.swing.JButton jBCadastrar;
    private javax.swing.JButton jBCancelar;
    private javax.swing.JButton jBExcluir;
    private javax.swing.JButton jBSalvar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField jTFCod;
    private javax.swing.JTextField jTFDocumento;
    private javax.swing.JTextField jTFEmail;
    private javax.swing.JTextField jTFLogin;
    private javax.swing.JTextField jTFNomeEmpresa;
    private javax.swing.JTextField jTFSenha;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables
}
