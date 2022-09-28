package clientecomthread;

import classesDominio.Cidade;
import classesDominio.Endereco;
import classesDominio.Solicitacao;
import static clientecomthread.TelaLogin.Entrada;
import static clientecomthread.TelaLogin.Saida;
import static clientecomthread.TelaLogin.USUARIO_NO_SISTEMA;
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
public class CadastrarEntrega extends javax.swing.JFrame {
    DefaultTableModel model;
    int global=0;
    
    public CadastrarEntrega() {
        initComponents();
    }

    public void mudaBotoes(boolean cadastrar,boolean excluir,boolean alterar,boolean salvar,boolean canelar){
        jBCadastrar.setEnabled(cadastrar);
        jBExcluir.setEnabled(excluir);
        jBAlterar.setEnabled(alterar);
        jBSalvar.setEnabled(salvar);
        jBCancelar.setEnabled(canelar);
    }
    
    public void consulta(){
        jCBChegada.removeAllItems();
        jCBSaida.removeAllItems();
        model = (DefaultTableModel) jTable1.getModel();
        model.setNumRows(0);
        try {
            Saida.writeObject(3);
            int ok = (int)Entrada.readObject();
            if(ok==1){
                Saida.writeObject(USUARIO_NO_SISTEMA);
                ArrayList<Endereco> listaEnderecos = (ArrayList<Endereco>)Entrada.readObject();
                for(int x = 0;x<listaEnderecos.size();x++){
                    Endereco meuEndereco = listaEnderecos.get(x);
                    String a = meuEndereco.getCod()+"; RUA: "+meuEndereco.getRua()+", compl.:"+meuEndereco.getNumero();
                    jCBSaida.addItem(a);
                    jCBChegada.addItem(a);
                }
            }
            Saida.writeObject(7);
            int ok2 = (int)Entrada.readObject();
            if(ok2==1){
                Saida.writeObject(USUARIO_NO_SISTEMA);
                ArrayList<Solicitacao> listaSolicitacao = (ArrayList<Solicitacao>)Entrada.readObject();
                for(int x = 0;x<listaSolicitacao.size();x++){
                    Solicitacao minhasolicitacao = listaSolicitacao.get(x);
                    String cod = minhasolicitacao.getCod()+"";
                    String nome = minhasolicitacao.getNome();
                    String desc = minhasolicitacao.getDescricao();
                    String cod_saida = minhasolicitacao.getCodEndSaida()+"";
                    String cod_entrada = minhasolicitacao.getCodEndChegada()+"";
                    model.addRow(new Object[]{cod,nome,desc,cod_saida,cod_entrada,USUARIO_NO_SISTEMA});
                }
            }
        } catch (IOException ex) {
            Logger.getLogger(CadastrarEndereco.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(CadastrarEndereco.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void limpa(){
        jTFNome.setText("");
        jTADescricao.setText("");
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jTFNome = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTADescricao = new javax.swing.JTextArea();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jCBSaida = new javax.swing.JComboBox<>();
        jCBChegada = new javax.swing.JComboBox<>();
        jLabel7 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jBCadastrar = new javax.swing.JButton();
        jBExcluir = new javax.swing.JButton();
        jBAlterar = new javax.swing.JButton();
        jBSalvar = new javax.swing.JButton();
        jBCancelar = new javax.swing.JButton();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(248, 248, 248));

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel1.setText("Nome do produto;");

        jTFNome.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 153, 102), 2, true));

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel2.setText("Descrição:");

        jTADescricao.setColumns(20);
        jTADescricao.setRows(5);
        jTADescricao.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 153, 102), 2, true));
        jScrollPane2.setViewportView(jTADescricao);

        jLabel3.setText("altura X largura X profundidade");

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel4.setText("Endereço da busca do produto:");

        jCBSaida.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 153, 102), 2, true));

        jCBChegada.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 153, 102), 2, true));

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel7.setText("Endereço de destino:");

        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/novissimo_1.png"))); // NOI18N

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
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "cod", "Produto", "Descrição", "Saida", "Chegada"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(jTable1);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(38, 38, 38)
                        .addComponent(jLabel6))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jBCadastrar, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jBExcluir, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jBAlterar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(18, 18, 18)
                                .addComponent(jBSalvar, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jBCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 399, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(49, 49, 49)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel2)
                            .addComponent(jLabel1)
                            .addComponent(jLabel4)
                            .addComponent(jLabel7)
                            .addComponent(jCBSaida, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel3)
                            .addComponent(jTFNome)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 344, Short.MAX_VALUE)
                            .addComponent(jCBChegada, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap(35, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(43, 43, 43)
                .addComponent(jLabel6)
                .addGap(37, 37, 37)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTFNome, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jCBSaida, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jCBChegada, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 37, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jBExcluir)
                    .addComponent(jBAlterar)
                    .addComponent(jBCancelar)
                    .addComponent(jBCadastrar)
                    .addComponent(jBSalvar))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jButton1.setText("<<");
        jButton1.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(248, 248, 248), 2, true));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(394, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(200, 200, 200)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(85, 85, 85))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGap(70, 70, 70)
                .addComponent(jButton1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        setSize(new java.awt.Dimension(1204, 688));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jBExcluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBExcluirActionPerformed
        int opcao=JOptionPane.showConfirmDialog(null, "Deseja realmente apagar a entrega de " + jTFNome.getText() + " ?");
        if(opcao == 0){
            try {
                Saida.writeObject(35);
                int teste = (int)Entrada.readObject();
                if(teste == 1){
                    int p = jTable1.getSelectedRow();
                    int cod = Integer.parseInt((String) jTable1.getValueAt(p, 0));
                    Saida.writeObject(cod);
                    int testeFinal = (int)Entrada.readObject();
                    if(testeFinal==1){
                        JOptionPane.showMessageDialog(rootPane, "Solicitação excluida");
                    } else {
                        JOptionPane.showMessageDialog(rootPane, "Essa solicitação não pode ser excluida, pois está ligado à alguma entrega");
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
        global=2;
    }//GEN-LAST:event_jBAlterarActionPerformed

    private void jBSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBSalvarActionPerformed
        if (global == 1) {
            if (jTFNome.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "Digite o produto, nao pode ser nulo");
            } else if (jTADescricao.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "Descreva o objeto, com suas dimensões");
            } else if (jCBSaida.getSelectedIndex() == jCBChegada.getSelectedIndex()) {
                JOptionPane.showMessageDialog(null, "Escolha endereços diferentes.");
            } else {
                String nome = jTFNome.getText();
                String endChegada = (String) jCBChegada.getSelectedItem();
                String endSaida = (String) jCBSaida.getSelectedItem();
                String desc = jTADescricao.getText();
                int usu = TelaLogin.USUARIO_NO_SISTEMA;
                String[] parts = endChegada.split(";");
                String[] parts2 = endSaida.split(";");
                int sai = Integer.parseInt(parts2[0]);
                int cheg = Integer.parseInt(parts[0]);
                Solicitacao minhaSolicitacao = new Solicitacao(nome, desc, sai, cheg, usu);
                try {
                    Saida.writeObject(15);
                    int teste = ((int) Entrada.readObject());
                    if(teste==1){
                        Saida.writeObject(minhaSolicitacao);
                        int maisUmTeste = (int)Entrada.readObject();
                        if(maisUmTeste==1){
                            JOptionPane.showMessageDialog(rootPane, "Solicitação cadastrado");
                        } else {
                            JOptionPane.showMessageDialog(rootPane, "Erro no cadastro");
                        }
                    }
                } catch (IOException ex) {
                    Logger.getLogger(CadastrarEndereco.class.getName()).log(Level.SEVERE, null, ex);
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(CadastrarEndereco.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

        } else {
            if (jTFNome.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "Digite o produto, nao pode ser nulo");
            } else if (jTADescricao.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "Descreva o objeto, com suas dimensões");
            } else if (jCBSaida.getSelectedIndex() == jCBChegada.getSelectedIndex()) {
                JOptionPane.showMessageDialog(null, "Escolha endereços diferentes.");
            } else {
                String nome = jTFNome.getText();
                String endChegada = (String) jCBChegada.getSelectedItem();
                String endSaida = (String) jCBSaida.getSelectedItem();
                String desc = jTADescricao.getText();
                int usu = TelaLogin.USUARIO_NO_SISTEMA;
                String[] parts = endChegada.split(";");
                String[] parts2 = endSaida.split(";");
                int sai = Integer.parseInt(parts2[0]);
                int cheg = Integer.parseInt(parts[0]);
                Solicitacao minhaSolicitacao = new Solicitacao(nome, desc, sai, cheg, usu);
                int p = jTable1.getSelectedRow();
                minhaSolicitacao.setCod(Integer.parseInt((String)jTable1.getValueAt(p, 0)));
                try {
                    Saida.writeObject(24);
                    int teste = ((int) Entrada.readObject());
                    if(teste==1){
                        Saida.writeObject(minhaSolicitacao);
                        int maisUmTeste = (int)Entrada.readObject();
                        if(maisUmTeste==1){
                            JOptionPane.showMessageDialog(rootPane, "Solicitação editada com sucesso");
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

    private void jBCadastrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBCadastrarActionPerformed
        mudaBotoes(false, false, false, true, true);
        global=1;
    }//GEN-LAST:event_jBCadastrarActionPerformed

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        mudaBotoes(true, true, true, false, false);
        consulta();
    }//GEN-LAST:event_formWindowOpened

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        int p = jTable1.getSelectedRow();
        jTFNome.setText((String) jTable1.getValueAt(p, 1));
        jTADescricao.setText((String) jTable1.getValueAt(p, 2));
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
            java.util.logging.Logger.getLogger(CadastrarEntrega.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(CadastrarEntrega.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(CadastrarEntrega.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(CadastrarEntrega.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new CadastrarEntrega().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jBAlterar;
    private javax.swing.JButton jBCadastrar;
    private javax.swing.JButton jBCancelar;
    private javax.swing.JButton jBExcluir;
    private javax.swing.JButton jBSalvar;
    private javax.swing.JButton jButton1;
    private javax.swing.JComboBox<String> jCBChegada;
    private javax.swing.JComboBox<String> jCBSaida;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTextArea jTADescricao;
    private javax.swing.JTextField jTFNome;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables
}
