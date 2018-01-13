package GUI;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileFilter;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.table.DefaultTableModel;

public final class Interfaz extends javax.swing.JFrame {

    private final String ruta = System.getProperties().getProperty("user.dir");
    private  static final String NOMEPASTA_ONDEESTAO_OS_ARQUIVOS = "C:\\lerr_txt\\";/// troca só aqui não precissa fazer mais nada.
    private DefaultTableModel modelo;
    public Interfaz() {
        initComponents();
        
      File archivo = null;
      FileReader fr = null;
      BufferedReader br = null;
 
     try {
         
         
         
         
         
         
         
    
        File diretorio = new File(NOMEPASTA_ONDEESTAO_OS_ARQUIVOS);
        File arquivos[];
        
        // lista somente arquivos TXT
        arquivos = diretorio.listFiles(new FileFilter() {  
            public boolean accept(File pathname) {  
                return pathname.getName().toLowerCase().endsWith(".txt");  
            }  
        });
        
        
        
        
        
        
        for (int i =0; i < arquivos.length; i++) {
            
            String nomeArquivo = arquivos[i].getName();
            BufferedReader reader = new BufferedReader(new FileReader(NOMEPASTA_ONDEESTAO_OS_ARQUIVOS + nomeArquivo ));
	    modelo = (DefaultTableModel) jTable1.getModel();
	    modelo.setNumRows(i);//Numero de linha tem que ser a mesma quantidade de arquivos;
	    jTable1.setModel(modelo);//mudei aqui. model ficou global e não local.
	    
            String linha = "";
            String col1="";
            String col2="";
            String col3="";
            String col4="";
            String col5="";//nova linha
            String exercicio[] = new String[200];
            int indice  = 0;
            int nrErros = 0;
            
            while ((linha = reader.readLine()) != null) {
                if (indice == 0){
     	            col1 = linha.split("\\:")[1];
                } else if (indice == 1){
     	            col2 = linha.split("\\:")[1];
                }else if (indice == 2){
     	            col3 = linha.split("\\:")[1];
                }else if (indice == 3){
     	            col4 = linha.split("\\:")[1];
                }else{
                    System.out.println(linha); 
                    col5 = pegarUltimaLinha(linha);//função que pega a ultima linha.
                    System.out.println(col5); 
                }
                indice++;
	    }
            
            linha = col1 + ":" + col2 +":" + col3 + ":" + col4 + ":"+col5;//add coluna 5
            modelo.addRow(linha.split(":"));
            
        }

		} catch (IOException e) {
			e.printStackTrace();
		}
    }

    protected String pegarUltimaLinha(String linha) {
        String[] split = linha.split("-");
        String[] split1 = split[0].split("Exercicio");
        return split1[1];
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane2 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4", "Título 5", "Título 6"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                true, false, true, true, true, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane2.setViewportView(jTable1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 385, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(47, 47, 47)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 275, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

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
            java.util.logging.Logger.getLogger(Interfaz.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Interfaz.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Interfaz.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Interfaz.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Interfaz().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables
}
