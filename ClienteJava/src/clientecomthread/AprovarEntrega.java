package clientecomthread;

import classesDominio.Endereco;
import classesDominio.Entrega;
import classesDominio.Pessoa;
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
public class AprovarEntrega extends javax.swing.JFrame {
    DefaultTableModel model; //define modelo da tabela
    static String cod = "",codOb = "";
    
    public AprovarEntrega() {
        initComponents();
    }

    public void mudaBotoes(boolean aprovar,boolean negar){
        jBAprovar.setEnabled(aprovar);
    }
    
    public void consulta(){
        model = (DefaultTableModel) jTable1.getModel();
        model.setNumRows(0); //limpando a tabela
        try {
            Saida.writeObject(10);
            int ok = (int)Entrada.readObject();
            if(ok==1){
                Saida.writeObject(USUARIO_NO_SISTEMA);
                ArrayList<Entrega> listaEntregas = (ArrayList<Entrega>)Entrada.readObject();
                Saida.writeObject(112);
                ArrayList<Pessoa> listaPessoas = (ArrayList<Pessoa>)Entrada.readObject();
                for(int x = 0;x<listaEntregas.size();x++){
                    Entrega minhaEntrega = listaEntregas.get(x);
                    float valor = minhaEntrega.getValor();
                    int prazo = minhaEntrega.getPrazo();
                    int cod = minhaEntrega.getCod();
                    String objetoNome = minhaEntrega.getNome();
                    int codOb = minhaEntrega.getCodSolicitacao();
                    String empresaNome = "";
                    int empresa = 0;
                        empresa = minhaEntrega.getCodEmpresa();
                    for(int y=0;y<listaPessoas.size();y++){
                        Pessoa minhaPessoa = listaPessoas.get(y);
                        if(empresa == minhaPessoa.getCod()){
                            empresaNome = minhaPessoa.getNome();
                        }
                    }
                    String valor1 = valor+"";
                    String prazo1 = prazo+"";
                    String cod1 = cod+"";
                    String codOb1 = codOb+"";
                    model.addRow(new Object[]{objetoNome,valor1,prazo1,empresaNome,cod1,codOb1});//carregando tabela
                }
            }
        } catch (IOException ex) {
            Logger.getLogger(CadastrarEndereco.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(CadastrarEndereco.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jBAprovar = new javax.swing.JButton();
        jTextField1 = new javax.swing.JTextField();
        jTextField2 = new javax.swing.JTextField();
        jBBack3 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(248, 248, 248));

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/novissimo_1.png"))); // NOI18N

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Objeto", "Valor", "Prazo", "Empresa", "Código", "Objeto ID"
            }
        ));
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);
        if (jTable1.getColumnModel().getColumnCount() > 0) {
            jTable1.getColumnModel().getColumn(0).setResizable(false);
            jTable1.getColumnModel().getColumn(1).setResizable(false);
            jTable1.getColumnModel().getColumn(2).setResizable(false);
            jTable1.getColumnModel().getColumn(3).setResizable(false);
            jTable1.getColumnModel().getColumn(4).setResizable(false);
            jTable1.getColumnModel().getColumn(5).setResizable(false);
        }

        jLabel2.setText("...");

        jLabel3.setText("...");

        jBAprovar.setText("Aprovar");
        jBAprovar.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(248, 248, 248), 2, true));
        jBAprovar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBAprovarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(49, 49, 49)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jBAprovar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel3)
                        .addComponent(jLabel2)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addComponent(jLabel1)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 374, Short.MAX_VALUE)))
                .addContainerGap(27, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(42, 42, 42)
                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(41, 41, 41)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(38, 38, 38)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel3)
                .addGap(50, 50, 50)
                .addComponent(jBAprovar, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(189, Short.MAX_VALUE))
        );

        jBBack3.setText("<<");
        jBBack3.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(248, 248, 248), 2, true));
        jBBack3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBBack3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(432, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(287, 287, 287)
                .addComponent(jBBack3, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(101, 101, 101))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGap(83, 83, 83)
                .addComponent(jBBack3)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        setSize(new java.awt.Dimension(1328, 698));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        mudaBotoes(false, false);
        consulta();
        jTextField1.setVisible(false);
        jTextField2.setVisible(false);
    }//GEN-LAST:event_formWindowOpened

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        mudaBotoes(true, true);
            int x = jTable1.getSelectedRow();
            jTextField1.setText((String) (jTable1.getValueAt(x, 4)));
            jTextField2.setText((String) (jTable1.getValueAt(x, 5)));
            cod = (jTextField1.getText());
            codOb = (jTextField2.getText());
            jLabel2.setText((String) jTable1.getValueAt(x, 0));//setando a tabela
            jLabel3.setText((String) jTable1.getValueAt(x, 3));
        
    }//GEN-LAST:event_jTable1MouseClicked

    private void jBAprovarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBAprovarActionPerformed
        mudaBotoes(false, false);
        try {
            Saida.writeObject(25);//aprovando entrega
            int y = ((int)Entrada.readObject());
            if(y==1){
                Saida.writeObject(cod);
                int ok = (int)Entrada.readObject();
                if(ok==1){
                Saida.writeObject(codOb);
                int retorno = (int)Entrada.readObject();
                if(retorno==1){
                    JOptionPane.showMessageDialog(rootPane, "Aprovada a entrega");                   
                }
            }
            }else{
                JOptionPane.showMessageDialog(rootPane, "Servidor Indisponível");
            }
        } catch (IOException ex) {
            Logger.getLogger(AprovarEntrega.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(AprovarEntrega.class.getName()).log(Level.SEVERE, null, ex);
        }
        consulta();
    }//GEN-LAST:event_jBAprovarActionPerformed

    private void jBBack3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBBack3ActionPerformed
        this.dispose();
    }//GEN-LAST:event_jBBack3ActionPerformed

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
            java.util.logging.Logger.getLogger(AprovarEntrega.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AprovarEntrega.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AprovarEntrega.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AprovarEntrega.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AprovarEntrega().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jBAprovar;
    private javax.swing.JButton jBBack3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    // End of variables declaration//GEN-END:variables
}
