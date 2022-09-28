package clientecomthread;

import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;


public class TelaLogin extends javax.swing.JFrame {
    public static Socket conexaoSocket;
    public static ObjectOutputStream Saida;
    public static ObjectInputStream Entrada;
    public static int USUARIO_NO_SISTEMA;
    public TelaLogin() {
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jSeparator1 = new javax.swing.JSeparator();
        jPanel1 = new javax.swing.JPanel();
        jTFLogin = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jPFSenha = new javax.swing.JPasswordField();
        jLabel3 = new javax.swing.JLabel();
        jBEntrar = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jBCadastro = new javax.swing.JButton();
        jBEsqueci = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        jSeparator1.setBackground(new java.awt.Color(255, 153, 102));
        jSeparator1.setForeground(new java.awt.Color(255, 153, 102));

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setAutoRequestFocus(false);
        setBackground(new java.awt.Color(250, 250, 250));
        setPreferredSize(new java.awt.Dimension(1309, 665));
        setSize(new java.awt.Dimension(1309, 665));
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(248, 248, 248));

        jTFLogin.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 153, 102), 2, true));

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel2.setText("Login:");

        jPFSenha.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 153, 102), 2, true));

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel3.setText("Senha:");

        jBEntrar.setText("Acessar");
        jBEntrar.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(240, 240, 240), 2, true));
        jBEntrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBEntrarActionPerformed(evt);
            }
        });

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/novissimo_1.png"))); // NOI18N

        jBCadastro.setText("Cadastre-se");
        jBCadastro.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(240, 240, 240), 2, true));
        jBCadastro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBCadastroActionPerformed(evt);
            }
        });

        jBEsqueci.setText("Esqueci a minha senha");
        jBEsqueci.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(240, 240, 240), 2, true));
        jBEsqueci.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBEsqueciActionPerformed(evt);
            }
        });

        jLabel1.setText("Novo por aqui?");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(33, 33, 33)
                        .addComponent(jLabel4))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(63, 63, 63)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jBEsqueci)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jLabel2)
                                .addComponent(jTFLogin, javax.swing.GroupLayout.DEFAULT_SIZE, 323, Short.MAX_VALUE)
                                .addComponent(jLabel3)
                                .addComponent(jPFSenha)
                                .addComponent(jBEntrar, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addGap(10, 10, 10)
                                    .addComponent(jLabel1)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(jBCadastro, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                .addContainerGap(67, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(91, 91, 91)
                .addComponent(jLabel4)
                .addGap(47, 47, 47)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTFLogin, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPFSenha, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jBEsqueci)
                        .addGap(21, 21, 21)
                        .addComponent(jBEntrar, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 243, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(jBCadastro))))
                .addGap(31, 31, 31))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(399, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(329, 329, 329))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        setSize(new java.awt.Dimension(1197, 698));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        try {
            this.conexaoSocket = new Socket("127.0.0.1",12345); //inicia a conexao socket
            this.Saida = new ObjectOutputStream(conexaoSocket.getOutputStream());
            this.Entrada = new ObjectInputStream(conexaoSocket.getInputStream()); //inicialisa a entrada e saida, os canais de comunicação
        } catch (IOException ex) {
            System.out.println( "Deu erro na conexao ao servidor."+ex);
        } catch (ClassCastException opa){
            System.err.println("Exeption de classe");
        }
        
    }//GEN-LAST:event_formWindowOpened

    private void jBEsqueciActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBEsqueciActionPerformed
        // TODO add your handling code here:
        TelaRecuperacaoSenha rs = new TelaRecuperacaoSenha();
        rs.setVisible(true);//chamando nova tela
        this.dispose();//fechando essa
    }//GEN-LAST:event_jBEsqueciActionPerformed

    private void jBCadastroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBCadastroActionPerformed
        TelaCadastroPessoa p = new TelaCadastroPessoa();
        p.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jBCadastroActionPerformed

    private void jBEntrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBEntrarActionPerformed
        // TODO add your handling code here:
        String login = jTFLogin.getText();
        String senha = String.valueOf(jPFSenha.getPassword());//pegando os campos
        try{
            Saida.writeObject(5);//chamando função do servidor
            int ok = (int)Entrada.readObject();//recebendo ok do servidor
            if(ok==1){
                Saida.writeObject(login);
                int ok2 = (int)Entrada.readObject();
                if(ok2 == 1){
                Saida.writeObject(senha);
                int retorno = (int) Entrada.readObject();
                if(retorno == 1234567){
                    JOptionPane.showMessageDialog(rootPane, "Usuário e/ou senha incorreto(s)");//fazendo login
                } else {
                    USUARIO_NO_SISTEMA = retorno;
                    Saida.writeObject(2);
                    ok = (int)Entrada.readObject();
                    if(ok==1){
                        Saida.writeObject(USUARIO_NO_SISTEMA);
                        int nivel = (int)Entrada.readObject();//verifica o nivel de usuario
                        if(nivel==1){ //Clientes
                            Menu m = new Menu();
                            m.setVisible(true);
                            this.dispose();
                        } else if(nivel==2){ //Empresas
                            MenuEmpresa m = new MenuEmpresa();
                            m.setVisible(true);
                            this.dispose();
                        } else if(nivel==3){ //Administrador
                            MenuAdmin m = new MenuAdmin();
                            m.setVisible(true);
                            this.dispose();
                        }
                    }
                }
            }
            } else {
                JOptionPane.showMessageDialog(rootPane, "Servidor não respondeu, tente mais tarde!");
            }
        } catch (ClassCastException e) {
            
        } catch (IOException ex) {
            Logger.getLogger(TelaLogin.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(TelaLogin.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jBEntrarActionPerformed

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
            java.util.logging.Logger.getLogger(TelaLogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TelaLogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TelaLogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TelaLogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TelaLogin().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jBCadastro;
    private javax.swing.JButton jBEntrar;
    private javax.swing.JButton jBEsqueci;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPasswordField jPFSenha;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTextField jTFLogin;
    // End of variables declaration//GEN-END:variables
}
