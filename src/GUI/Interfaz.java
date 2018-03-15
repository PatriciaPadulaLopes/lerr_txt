package GUI;



import java.io.BufferedReader;
import java.io.File;
import java.io.FileFilter;
import java.io.FileReader;
import java.io.IOException;
import static java.lang.Thread.sleep;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JTable;

import javax.swing.RowSorter;
import javax.swing.SortOrder;
import javax.swing.Timer;
import javax.swing.plaf.IconUIResource;

import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

public final class Interfaz extends javax.swing.JFrame {

    private final String ruta = System.getProperties().getProperty("user.dir");
    private  static final String NOMEPASTA_ONDEESTAO_OS_ARQUIVOS = "C:\\lerr_txt\\";/// troca só aqui não precissa fazer mais nada.
    private DefaultTableModel modelo;
    
    public Interfaz() {
     init();
    }
    
    public void init() {
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
            JTableRender col6 = null;
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
                    col6 = getImagem( col4 );
                }else{
                    System.out.println(linha); 
                    col5 = pegarUltimaLinha(linha);//função que pega a ultima linha.
                    System.out.println(col5); 
                }
                indice++;
	    }
            
            linha = col1 + ":" + col2 +":" + col3 + ":" + col4 + ":"+col5 ;//add coluna 5 e 6
            
            modelo.addRow(linha.split(":"));
            TableColumnModel columnModel = jTable1.getColumnModel();
            columnModel.getColumn(5).setCellRenderer(col6);
            
        }
        
        TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(modelo);
        jTable1.setRowSorter(sorter);  
        List<RowSorter.SortKey> sortKeys = new ArrayList<>();
        sortKeys.add(new RowSorter.SortKey(4/** Coluna*/, SortOrder.DESCENDING));
        sorter.setSortKeys(sortKeys);
 
        atualizarTabela(3000);
       
        
      } catch (IOException e) {
	 e.printStackTrace();
      }
    }

    
    private void atualizarTabela( final long tempo){
       
   new Thread( new Runnable() {
          @Override
          public void run() {
               System.out.println(" Atualizando tabelas :" + new  SimpleDateFormat(" hh:mm:ss ").format(new Date()));
                init();
                
              try {
                  sleep(tempo);
                  run();
              } catch (InterruptedException ex) {
                  Logger.getLogger(Interfaz.class.getName()).log(Level.SEVERE, null, ex);
              }
          }
      }).start();
    }
    private String pegarUltimaLinha(String linha) {
        String[] split = linha.split("-");
        String[] split1 = split[0].split("Exercicio");
        return split1[1];
    }
    private  JTableRender getImagem(String qtd) {
        if(qtd == null || qtd.equals("")) new JTableRender();
        String atatus = null;
        double qtdErros = Double.parseDouble(qtd);
        
        JTableRender img = new JTableRender();
         
        ImageIcon imagem = null;
        
        if( qtdErros <= 2){
             imagem = criarImagem(Imagen.OTIMO.getNome(), "Status òtimo");
        }else if(qtdErros >= 2 && qtdErros <= 5){
             imagem = criarImagem( Imagen.REGULAR.getNome(), "Teste 1");
        }else if(qtdErros <= 5 && qtdErros <= 8){
             imagem = criarImagem(Imagen.RUIM.getNome(), "Teste 1");
        }else if(qtdErros >= 8){
            imagem = criarImagem(Imagen.PESSIMO.getNome(), "Teste 1");
        }
        //add imagem
        img.setValue(  imagem );
        
        return img;
    }  
    
    protected ImageIcon criarImagem(String path,String description) {
    java.net.URL imgURL = Interfaz.class.getClass().getResource("/GUI/"+"pessimo.png");
        System.out.println("URI " + imgURL);
    if (imgURL != null) {
        return new ImageIcon(imgURL, description);
    } else {
        System.err.println(" o arquivo: " + path);
        return null;
    }
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

    public JTable getjTable1() {
        return jTable1;
    }

    
  
}
