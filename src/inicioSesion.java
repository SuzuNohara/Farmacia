/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import farmacia.conectar;
import java.awt.HeadlessException;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
/**
 *
 * @author NaMYY
 */
public class inicioSesion extends javax.swing.JFrame {

    /**
     * Creates new form inicioSesion
     */
    public inicioSesion() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jPasswordField1 = new javax.swing.JPasswordField();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        jLabel1.setText("Inicio de sesión");

        jLabel2.setText("Usuario:");

        jLabel3.setText("Contraseña:");

        jButton1.setText("Iniciar sesión");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel1))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(31, 31, 31)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3))
                        .addGap(53, 53, 53)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jTextField1)
                            .addComponent(jPasswordField1, javax.swing.GroupLayout.DEFAULT_SIZE, 158, Short.MAX_VALUE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 123, Short.MAX_VALUE)
                .addComponent(jButton1)
                .addGap(121, 121, 121))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jPasswordField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 12, Short.MAX_VALUE)
                .addComponent(jButton1)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        String usuario, contrasena;
        usuario = jTextField1.getText();
        contrasena = jPasswordField1.getText();
        try{
            conectar cn = new conectar();
            Connection cnn = cn.conect();
            Statement stmt = cnn.createStatement();
            ResultSet rs = stmt.executeQuery("Select idVendedor from Vendedor where Usuario = '" + usuario + "' and Contrasena = '" + contrasena + "'");
            if(rs.next()){
                int id = rs.getInt("idVendedor");
                jTextField1.setText("");
                jPasswordField1.setText("");
                if(id == -1){
                    JFrame farm = new JFrame();
                    farm.setSize(650, 134);
                    caja f = new caja(this, farm, -1);
                    cnn.close();
                    farm.add(f);
                    farm.setDefaultCloseOperation(EXIT_ON_CLOSE);
                    farm.setVisible(true);
                    this.setVisible(false);
                }else{
                    JFrame farm = new JFrame();
                    farm.setSize(1050, 600);
                    farmacia f = new farmacia(this, farm, rs.getInt("idVendedor"));
                    cnn.close();
                    farm.add(f);
                    farm.setDefaultCloseOperation(EXIT_ON_CLOSE);
                    farm.setVisible(true);
                    this.setVisible(false);
                }
            }else{
                rs = stmt.executeQuery("Select idMedico from medico where usuario = '" + usuario + "' and cont = '" + contrasena + "'");
                if(rs.next()){
                    jTextField1.setText("");
                    jPasswordField1.setText("");
                    JFrame farm = new JFrame();
                    farm.setSize(960, 610);
                    farm.setResizable(false);
                    medico m = new medico(this, farm, rs.getInt("idMedico"));
                    m.setSize(850, 566);
                    cnn.close();
                    farm.add(m);
                    farm.setDefaultCloseOperation(EXIT_ON_CLOSE);
                    farm.setVisible(true);
                    this.setVisible(false);
                }else{
                    rs = stmt.executeQuery("select idUsuario from recepcionista where usuario = '"+usuario+"' and contrasena = '"+contrasena+"'");
                    if(rs.next()){
                        int id = rs.getInt("idUsuario");
                        if(id == -1){
                            jTextField1.setText("");
                            jPasswordField1.setText("");
                            JFrame farm = new JFrame();
                            farm.setSize(761, 464);
                            admin r = new admin(this, farm);
                            cnn.close();
                            farm.add(r);
                            farm.setDefaultCloseOperation(EXIT_ON_CLOSE);
                            farm.setVisible(true);
                            farm.setResizable(false);
                            this.setVisible(false);
                        }else{
                            jTextField1.setText("");
                            jPasswordField1.setText("");
                            JFrame farm = new JFrame();
                            farm.setSize(1005, 600);
                            recepcion r = new recepcion(this, farm, id);
                            cnn.close();
                            farm.add(r);
                            farm.setDefaultCloseOperation(EXIT_ON_CLOSE);
                            farm.setVisible(true);
                            farm.setResizable(false);
                            this.setVisible(false);
                        }
                    }else{
                        JOptionPane.showMessageDialog(rootPane, "Usuario o contraseña incorrectos");
                        jPasswordField1.setText("");
                    }
                }
            }
            rs.close();
            stmt.close();
            cnn.close();
        }catch(SQLException | HeadlessException ex){
            JOptionPane.showMessageDialog(rootPane, "Ocurrio un error al conectarse a la base de datos");
            jTextField1.setText("");
            jPasswordField1.setText("");
        }
    }//GEN-LAST:event_jButton1ActionPerformed

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
            java.util.logging.Logger.getLogger(inicioSesion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(inicioSesion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(inicioSesion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(inicioSesion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new inicioSesion().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPasswordField jPasswordField1;
    private javax.swing.JTextField jTextField1;
    // End of variables declaration//GEN-END:variables
}
