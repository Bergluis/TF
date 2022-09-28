/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clientecomthread;

import classesDominio.Cidade;
import classesDominio.Endereco;
import classesDominio.Entrega;
import classesDominio.Solicitacao;
import static clientecomthread.TelaLogin.Entrada;
import static clientecomthread.TelaLogin.Saida;
import static clientecomthread.TelaLogin.USUARIO_NO_SISTEMA;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.MenuElement;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Luis
 */
public class EmpresaEntregas extends javax.swing.JFrame {
    DefaultTableModel model;
    
    /**
     * Creates new form EmpresaEntregas
     */
    public EmpresaEntregas() {
        initComponents();
    }

    public void mudaBotoes(boolean cadastrar,boolean salvar,boolean canelar){
        jBCadastrar.setEnabled(cadastrar);
        jBSalvar.setEnabled(salvar);
        jBCancelar.setEnabled(canelar);
    }
    
    public void consulta(){
        jTFSolicitacao.setEnabled(false);
        model = (DefaultTableModel) jTable1.getModel();
        model.setNumRows(0);
        try {
            Saida.writeObject(8);
            int ok = (int)Entrada.readObject();
            if(ok==1){
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
        jTFPrazo.setText("");
        jTFSolicitacao.setText("");
        jTFValor.setText("");
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jPanel3 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        jBCadastrar = new javax.swing.JButton();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jBSalvar = new javax.swing.JButton();
        jBCancelar = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jTFPrazo = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jTFSolicitacao = new javax.swing.JTextField();
        jTFValor = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLSaida = new javax.swing.JLabel();
        jLChegada = new javax.swing.JLabel();
        jBBack = new javax.swing.JButton();

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(jTable2);

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        jPanel3.setBackground(new java.awt.Color(248, 248, 248));

        jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/novissimo_1.png"))); // NOI18N

        jBCadastrar.setText("Propor entrega");
        jBCadastrar.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(248, 248, 248), 2, true));
        jBCadastrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBCadastrarActionPerformed(evt);
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

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel1.setText("Prazo para a entrega (em dias)");

        jTFPrazo.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 153, 102), 2, true));

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel2.setText("Valor da entrega");

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel4.setText("Cod. entrega");

        jTFSolicitacao.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 153, 102), 2, true));

        jTFValor.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 153, 102), 2, true));

        jLabel3.setText("R$");

        jLSaida.setText("Saida:");

        jLChegada.setText("Chegada:");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(52, 52, 52)
                        .addComponent(jLabel9))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(41, 41, 41)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jBCadastrar)
                            .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 405, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTFValor, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(jTFPrazo, javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jTFSolicitacao, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 130, Short.MAX_VALUE)
                                        .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.LEADING))
                                    .addComponent(jLabel1))
                                .addGap(62, 62, 62)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLChegada)
                                    .addComponent(jLSaida))))))
                .addContainerGap(44, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jBSalvar, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jBCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(55, 55, 55))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(64, 64, 64)
                .addComponent(jLabel9)
                .addGap(37, 37, 37)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jBCadastrar)
                .addGap(37, 37, 37)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTFSolicitacao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLSaida))
                .addGap(4, 4, 4)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLChegada))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTFPrazo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTFValor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addGap(16, 16, 16)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jBSalvar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jBCancelar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(134, Short.MAX_VALUE))
        );

        jBBack.setText("<<");
        jBBack.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(248, 248, 248), 2, true));
        jBBack.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBBackActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(417, Short.MAX_VALUE)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(220, 220, 220)
                .addComponent(jBBack, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(86, 86, 86))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGap(73, 73, 73)
                .addComponent(jBBack)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        setSize(new java.awt.Dimension(1278, 698));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jBCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBCancelarActionPerformed
        mudaBotoes(true, false, false);
        jTable1.setEnabled(true);
        limpa();
    }//GEN-LAST:event_jBCancelarActionPerformed

    private void jBSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBSalvarActionPerformed
        Solicitacao minhaSolicitacao = null;    
        if (jTFPrazo.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "Digite um prazo, nao pode ser nulo");
            } else if (jTFSolicitacao.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "Selecione uma solicitação de envio, nao pode ser nula");
            } else if (jTFValor.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "Digite um valor da entrega.");
            } else {
                int prazo = Integer.parseInt(jTFPrazo.getText());
                float valor = Float.parseFloat(jTFValor.getText());
                int cod_solicitacao = Integer.parseInt(jTFSolicitacao.getText());
                int usu = TelaLogin.USUARIO_NO_SISTEMA;
                try {
                    Saida.writeObject(115);
                    int ok = (int)Entrada.readObject();
                    if(ok==1){
                        Saida.writeObject(cod_solicitacao);
                        minhaSolicitacao = (Solicitacao)Entrada.readObject();
                    }
                } catch (IOException ex) {
                    Logger.getLogger(EmpresaEntregas.class.getName()).log(Level.SEVERE, null, ex);
                } catch (ClassNotFoundException ex) {
                Logger.getLogger(EmpresaEntregas.class.getName()).log(Level.SEVERE, null, ex);
            }
                String nome = minhaSolicitacao.getNome();
                String desc = minhaSolicitacao.getDescricao();
                Entrega minhaEntrega = new Entrega(prazo, valor, usu, 1,nome,desc,minhaSolicitacao.getCodEndSaida(),minhaSolicitacao.getCodEndChegada(),minhaSolicitacao.getCodPessoa());
                minhaEntrega.setCodSolicitacao(cod_solicitacao);
                System.err.println(minhaSolicitacao.getCodPessoa());
                try {
                    Saida.writeObject(16);
                    int teste = ((int) Entrada.readObject());
                    if(teste==1){
                        Saida.writeObject(minhaEntrega);
                        int maisUmTeste = (int)Entrada.readObject();
                        if(maisUmTeste==1){
                            JOptionPane.showMessageDialog(rootPane, "Prosta cadastrado");
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

        mudaBotoes(true, false, false);
        jTable1.setEnabled(true);
        consulta();
        limpa();
    }//GEN-LAST:event_jBSalvarActionPerformed

    private void jBCadastrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBCadastrarActionPerformed
        mudaBotoes(false, true, true);
    }//GEN-LAST:event_jBCadastrarActionPerformed

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        int p = jTable1.getSelectedRow();
        jTFSolicitacao.setText((String) jTable1.getValueAt(p, 0));
        int saida = Integer.parseInt((String) jTable1.getValueAt(p, 3));
        int chegada = Integer.parseInt((String) jTable1.getValueAt(p, 4));
        int sai,cheg;
        try {
            String saidaTexto = null,chegadaTexto = null;
            Saida.writeObject(117);
            ArrayList<Endereco> listaEnderecos = (ArrayList<Endereco>)Entrada.readObject();
            Saida.writeObject(4);
            ArrayList<Cidade> listaCidades = (ArrayList<Cidade>)Entrada.readObject();
            for(int o=0;o<listaEnderecos.size();o++){
                Endereco meuEndereco = listaEnderecos.get(o);
                if(meuEndereco.getCod()==saida){
                    saidaTexto = "DE: "+meuEndereco.getRua()+"   "+meuEndereco.getComplemento();
                    sai = meuEndereco.getCod_cidade();
                    for(int i=0;i<listaCidades.size();i++){
                        Cidade minhaCidade = listaCidades.get(i);
                        if(minhaCidade.getCod()==sai){
                            saidaTexto = saidaTexto+"   "+minhaCidade.getNome()+" "+minhaCidade.getUf();
                        }
                    }
                }
                if(meuEndereco.getCod()==chegada){
                    chegadaTexto = "PARA: "+meuEndereco.getRua()+"   "+meuEndereco.getComplemento();
                    cheg = meuEndereco.getCod_cidade();
                    for(int i=0;i<listaCidades.size();i++){
                        Cidade minhaCidade = listaCidades.get(i);
                        if(minhaCidade.getCod()==cheg){
                            chegadaTexto = chegadaTexto+"   "+minhaCidade.getNome()+" "+minhaCidade.getUf();
                        }
                    }
                }
            }
            jLSaida.setText(saidaTexto);
            jLChegada.setText(chegadaTexto);
        } catch (IOException ex) {
            Logger.getLogger(EmpresaEntregas.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(EmpresaEntregas.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jTable1MouseClicked

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        mudaBotoes(true, false, false);
        consulta();
    }//GEN-LAST:event_formWindowOpened

    private void jBBackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBBackActionPerformed
        MenuEmpresa m = new MenuEmpresa();
        m.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jBBackActionPerformed

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
            java.util.logging.Logger.getLogger(EmpresaEntregas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(EmpresaEntregas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(EmpresaEntregas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(EmpresaEntregas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new EmpresaEntregas().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jBBack;
    private javax.swing.JButton jBCadastrar;
    private javax.swing.JButton jBCancelar;
    private javax.swing.JButton jBSalvar;
    private javax.swing.JLabel jLChegada;
    private javax.swing.JLabel jLSaida;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTextField jTFPrazo;
    private javax.swing.JTextField jTFSolicitacao;
    private javax.swing.JTextField jTFValor;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    // End of variables declaration//GEN-END:variables
}
