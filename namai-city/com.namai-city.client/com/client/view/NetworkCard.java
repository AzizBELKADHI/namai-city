package com.client.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ContainerEvent;
import java.awt.event.ContainerListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.JViewport;
import javax.swing.border.MatteBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.TableModel;
import net.proteanit.sql.DbUtils;
public class NetworkCard extends javax.swing.JLabel {

    Graphics graphics;
    boolean firstRun = false;
    String s = "";
    int x = 300;
    int y = 420;
    int sWidth = 0;
    int sLength = 0;
    int pWidth = 0;
    int pHeigth = 0;
    int oldWidth = 0;
    int oldPoint = 0;
    int oldHeigth = 0;
    String oldShape = "";
    int point = 0;

    JViewport viewport;
    int xscroll = 0;
    int yscroll = 0;
    private final double ENERGY_PRICE = 0.15; // (euro)
    private final double AVERAGE_SPEED = 19.6; // (km/h)
    private final double AVERAGE_CONSUMPTION = 120; // (kw/h)

    private double cost = 0;
    private Random r;
    private List<Integer> listOfPoints;
    public static Connection c; 
	private static String URL = "jdbc:postgresql://172.31.249.44:5432/NamaiDB";
	private static String login = "toto" ;
	private static String password = "toto";


	public static Connection createConnection() throws SQLException {
		try {

			return  DriverManager.getConnection (URL, login, password);
		} catch (SQLException e) {
			throw new SQLException("Can't create connection", e);
		}

	}

public void  getVilles (Connection conn, String searchCriteria)
throws SQLException
{
  List blogs = new LinkedList();
  String query = "SELECT  libelle, shape,length,width,nb_points, cost FROM CarteVille WHERE UPPER(libelle) LIKE ?";
  try
  {
    // going to do a search using "upper"
    searchCriteria = searchCriteria.toUpperCase();

    // create the preparedstatement and add the criteria
    PreparedStatement ps = conn.prepareStatement(query);
    ps.setString(1, "%" + searchCriteria + "%");

    // process the results
    ResultSet rs = ps.executeQuery();
   String n = "",e = "";      

tableRech.setModel(DbUtils.resultSetToTableModel(rs));
    rs.close();
    ps.close();
     System.out.println("fin test");
  }
  catch (SQLException se)
  {
    // log exception;
    throw se;
  }
  
}  
    //Initialisation de la class
	public NetworkCard() {
        initComponents();
        jButton1.setVisible(false);
        jButton2.setVisible(false);
        try {
       	 c = createConnection();
           getVilles(c, "");
       } catch (SQLException ex) {
           Logger.getLogger(NetworkCard.class.getName()).log(Level.SEVERE, null, ex);
       }
        
      int w=(int) jPanel1.getPreferredSize().getWidth();
      int h=(int) jPanel1.getPreferredSize().getHeight();
      System.out.println("w:"+w+"       h:"+h);
     jPanel1.setPreferredSize(new Dimension(w+300, h+120));
       // this.connect();
        jScrollPane2.getViewport().addChangeListener(new ListenAdditionsScrolled());
       // SwingUtilities swingU = null ;
        //swingU.getRootPane(btnNewButton).setDefaultButton(btnNewButton);
        jPanel1.addFocusListener(new FocusListener() {
			
			@Override
			public void focusLost(FocusEvent e) {
				 if (firstRun != false) {
	                prepare();
	                drawing(s);
	            }
				
			}
			
			@Override
			public void focusGained(FocusEvent e) {
				 if (firstRun != false) {
		                prepare();
		                drawing(s);
		            }
				
			}
		});
        // Listener sur le textField pour changer la valeur de la longueur suite à la valeur de la hauteur dans le cas du "Square"
        textFieldLength.getDocument().addDocumentListener(new DocumentListener() {

            @Override
            public void insertUpdate(DocumentEvent e) {
                if (s.equals("Square")) {
                    textFieldWidth.setText(textFieldLength.getText());
                }
                System.out.println("Vous avez choisi -Square -");
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                if (s.equals("Square")) {
                    textFieldWidth.setText(textFieldLength.getText());
                }
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                if (s.equals("Square")) {
                    textFieldWidth.setText(textFieldLength.getText());
                }
            }
        });
         textFieldRech.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                System.out.println("insertUpdate");
                 
                String ville=textFieldRech.getText();
                 try {
                	 c = createConnection();
                    getVilles(c, ville);
                } catch (SQLException ex) {
                    Logger.getLogger(NetworkCard.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                 System.out.println("Vous avez recupere vos données stockées en base");
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                System.out.println("removeUpdate");
                String ville=textFieldRech.getText();
                 try {
                	 c = createConnection();
                    getVilles(c, ville);
                } catch (SQLException ex) {
                    Logger.getLogger(NetworkCard.class.getName()).log(Level.SEVERE, null, ex);
                }
                 
                 
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                System.out.println("changedUpdate");
                String ville=textFieldRech.getText();
                try {
                	c = createConnection();
                    getVilles(c, ville);
                } catch (SQLException ex) {
                    Logger.getLogger(NetworkCard.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                System.out.println("Vous avez choisi la ville que vous voulez recuperer");
            }
             
         });

    }

    @SuppressWarnings("unchecked")
    private void initComponents() {

       jScrollPane2 = new javax.swing.JScrollPane();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        comboBoxForme = new javax.swing.JComboBox();
        jLabel2 = new javax.swing.JLabel();
        textFieldLength = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        textFieldWidth = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        textFieldPoint = new javax.swing.JTextField();
        btnNewButton = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        textFieldRech = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        textFieldVille = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        tableRech = new JTable() {
            private static final long serialVersionUID = 1L;

            public boolean isCellEditable(int row, int column) {                
                    return false;               
            };
        };

//        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
//        addWindowListener(new java.awt.event.WindowAdapter() {
//            public void windowActivated(java.awt.event.WindowEvent evt) {
//                formWindowActivated(evt);
//            }
//        });

        jScrollPane2.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentResized(java.awt.event.ComponentEvent evt) {
                jScrollPane2ComponentResized(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(255, 255, 204));
        
        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel1.setText("Shape");

        comboBoxForme.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        comboBoxForme.setForeground(new java.awt.Color(153, 0, 0));
        comboBoxForme.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Ellipse", "Square", "Rectangle" }));
        comboBoxForme.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboBoxFormeActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel2.setText("Length");

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel3.setText("Width");

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel4.setText("Stations");

        btnNewButton.setBackground(new java.awt.Color(255, 204, 204));
        btnNewButton.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnNewButton.setForeground(new java.awt.Color(0, 0, 204));
        btnNewButton.setText("Valider");
        
        btnNewButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNewButtonActionPerformed(evt);
            }
        });

        jLabel5.setBackground(new java.awt.Color(204, 255, 255));
        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel5.setText("Discover the best map network card option for your city");
        jLabel5.setOpaque(true);

        jButton1.setBackground(new java.awt.Color(255, 204, 204));
        jButton1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton1.setForeground(new java.awt.Color(0, 0, 204));
        jButton1.setText("Save");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jButton2.setBackground(new java.awt.Color(255, 204, 204));
        jButton2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton2.setForeground(new java.awt.Color(0, 0, 204));
        jButton2.setText("Annuler");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        tableRech.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null}
            },
            new String [] {
                ""
            }
        ) {
            /**
			 * 
			 */
			private static final long serialVersionUID = 1L;
			Class[] types = new Class [] {
                java.lang.String.class, java.lang.Integer.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        tableRech.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableRechMouseClicked(evt);
            }
        });
        tableRech.setAutoscrolls(true);
        tableRech.setAutoCreateColumnsFromModel(true);
        tableRech.setBackground(Color.LIGHT_GRAY);
        tableRech.setBorder(BorderFactory.createCompoundBorder());
        tableRech.setForeground(Color.BLACK);
        tableRech.setShowGrid(true);
        jScrollPane1.setViewportView(tableRech);
        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel6.setText("Chercher Par Ville :");
        jLabel6.setToolTipText("");

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel7.setText("Ville");
        jLabel7.setToolTipText("");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 110, Short.MAX_VALUE)
                    .addComponent(comboBoxForme, 0, 110, Short.MAX_VALUE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 110, Short.MAX_VALUE)
                    .addComponent(textFieldLength, javax.swing.GroupLayout.DEFAULT_SIZE, 110, Short.MAX_VALUE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, 110, Short.MAX_VALUE)
                    .addComponent(textFieldWidth, javax.swing.GroupLayout.DEFAULT_SIZE, 110, Short.MAX_VALUE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, 110, Short.MAX_VALUE)
                    .addComponent(textFieldPoint, javax.swing.GroupLayout.DEFAULT_SIZE, 110, Short.MAX_VALUE)
                    .addComponent(btnNewButton, javax.swing.GroupLayout.DEFAULT_SIZE, 110, Short.MAX_VALUE)
                    .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, 110, Short.MAX_VALUE)
                    .addComponent(textFieldVille, javax.swing.GroupLayout.DEFAULT_SIZE, 110, Short.MAX_VALUE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, 110, Short.MAX_VALUE))
                .addGap(88, 88, 88)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 500, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 500, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(textFieldRech, javax.swing.GroupLayout.PREFERRED_SIZE, 500, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 500, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(305, 305, 305))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(1, 1, 1)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(textFieldRech, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(textFieldVille, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(comboBoxForme, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(textFieldLength, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(textFieldWidth, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(textFieldPoint, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnNewButton, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton2)
                .addContainerGap(406, Short.MAX_VALUE))
        );

        jScrollPane2.setViewportView(jPanel1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 696, Short.MAX_VALUE)
        );


       
    }

    // Action du boutton Valider

    private void btnNewButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNewButtonActionPerformed
        s = (String) comboBoxForme.getItemAt(comboBoxForme.getSelectedIndex());
        if (textFieldLength.getText().length() == 0 || textFieldWidth.getText().length() == 0 || textFieldPoint.getText().length() == 0) {
            JOptionPane.showMessageDialog(null, "You must fill all the fields to continue", "Warning : Empty parameter(s)", JOptionPane.WARNING_MESSAGE);
            System.out.println("Vous devez remplir tous les champs de saisie pour continuer");
        
        } else {

            try {
                sWidth = Integer.parseInt(textFieldWidth.getText());
                sLength = Integer.parseInt(textFieldLength.getText());
                point = Integer.parseInt(textFieldPoint.getText());

                // Condition pour vérifier si les valeurs sont changées
                if ((sWidth != oldWidth || sLength != oldHeigth || point != oldPoint || !s.equals(oldShape))) {
                	if(sWidth<0 || sLength<0 || point<0) {
                	JOptionPane.showMessageDialog(null, "You must enter positive numbers", "Warning : negatif numbers", JOptionPane.WARNING_MESSAGE);
                    System.out.println("Vous devez saisir des valeurs positives pour procéder à la validation");
                	}else {
                    firstRun = true;
                    prepare();

                    drawing(s);
                   
                	}
                }
                jButton1.setVisible(true);
                jButton2.setVisible(true);
                enables(false);
                setVisible(true);
                
            } catch (NumberFormatException exc) {
                JOptionPane.showMessageDialog(null, "You must enter valid parameters (number) to continue", "Warning : wrong parameter(s) type", JOptionPane.WARNING_MESSAGE);
                System.out.println("Vous devez saisir des entiers pour continuer");
           }
            
        }
    }//GEN-LAST:event_btnNewButtonActionPerformed

    // Listener du statut du scrollPane pour reproduire le drawing dans chaque changement de position du scrollbar
    public class ListenAdditionsScrolled implements ChangeListener {

        @Override
        public void stateChanged(ChangeEvent e) {
            //firstRun est une valeur Boolean à vérifier pour empécher d'éxecuter les drawing lors de l'éxecution du programme
            if (firstRun != false) {
                prepare();
                drawing(s);
            }

        }

    }

    // Listener de la JFrame pour reproduire le drawing lors de réduire/réstaurer la page
    private void formWindowActivated(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowActivated

        if (firstRun != false) {
            prepare();
            drawing(s);
        }
    }//GEN-LAST:event_formWindowActivated

    //Listener du jScrollPane pour reproduire le drawing lors du changement de la taille de la page
    private void jScrollPane2ComponentResized(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_jScrollPane2ComponentResized

        if (firstRun != false) {
            prepare();
            drawing(s);
        }
    }//GEN-LAST:event_jScrollPane2ComponentResized
    // Listener sur le comboBox pour changer la valeur de la longueur suite à la valeur de la hauteur dans le cas du "Square"
    private void comboBoxFormeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboBoxFormeActionPerformed
        s = (String) comboBoxForme.getItemAt(comboBoxForme.getSelectedIndex());
        if (s.equals("Square")) {
            textFieldWidth.setEnabled(false);
            textFieldWidth.setText(textFieldLength.getText());
        } else {
            textFieldWidth.setEnabled(true);
        }
    }//GEN-LAST:event_comboBoxFormeActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // 
    }//GEN-LAST:event_jButton1ActionPerformed
    
    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
    	jButton1.setVisible(false);
        jButton2.setVisible(false);
        enables(true);  
    }//GEN-LAST:event_jButton2ActionPerformed
    

    private void tableRechMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableRechMouseClicked
        if (tableRech.isEnabled()==true) {
    	
    	int index = tableRech.getSelectedRow();
        TableModel model = tableRech.getModel();
        System.out.println(model.getValueAt(index, 1));
        
        s = (String) model.getValueAt(index, 1);
       

            try {
            	sLength = (int) model.getValueAt(index, 2);
            	 System.out.println("Vous avez recupere la longueur voulue");
                sWidth = (int) model.getValueAt(index, 3);
                System.out.println("Vous avez recupere la largeur voulue");
                point = (int) model.getValueAt(index, 4);
                System.out.println("Vous avez recupere le nombre de stations voulues");
               
                    firstRun = true;
                    prepare();

                    drawing(s);
                    setVisible(true);

              
            } catch (NumberFormatException exc) {
                JOptionPane.showMessageDialog(null, "You must enter valid parameters (number) to continue", "Warning : wrong parameter(s) type", JOptionPane.WARNING_MESSAGE);
                System.out.println("Vous devez saisir des entiers pour continuer");
            }
        
        
        }
    }//GEN-LAST:event_tableRechMouseClicked
    /* préparer les variables; effacer l'ancien drawing relativement à la position actuelle du scrollBar récupérée avec le viewport;
     changer la taille du JPanel et jScrollPane suite à la hauteur et la largeur saisie par l'utilisateur */
    public void prepare() {
        graphics = jPanel1.getGraphics();
        pWidth = jPanel1.getWidth();
        pHeigth = jPanel1.getHeight();
        viewport = jScrollPane2.getViewport();
        xscroll = viewport.getViewPosition().x;
        yscroll = viewport.getViewPosition().y;
        graphics.clearRect(x - xscroll - 1, y - yscroll - 20, pWidth, pHeigth);
        graphics.setColor(new Color(255, 255, 204));
        graphics.fillRect(x - xscroll-1, y - yscroll-20, pWidth, pHeigth);
        jPanel1.setPreferredSize(new Dimension(sWidth + 320, sLength + 440));
        jScrollPane2.setPreferredSize(new Dimension(sWidth + 320, sLength + 440));
        setVisible(true);

        draw(sWidth, sLength);
    }

    public void draw(int width, int length) {

        if (s.equals("Square") || s.equals("square") ) {

            graphics.setColor(Color.GREEN);
            graphics.drawRect(x, y, width, length);
           

        } else if (s.equals("Rectangle") || s.equals("rectangle")) {

            graphics.setColor(Color.RED);
            graphics.drawRect(x, y, width, length);
           

        } else if (s.equals("Ellipse") || s.equals("ellipse")) {

            graphics.setColor(Color.BLACK);
            graphics.drawOval(x, y, width, length);
           
        }

        oldWidth = sWidth;
        oldHeigth = sLength;
        oldPoint = point;
        oldShape = s;

    }

    public double calculateDistanceBetweenPoints(int x1, int y1, int x2, int y2) {
        return Math.sqrt((y2 - y1) * (y2 - y1) + (x2 - x1) * (x2 - x1));
    }

    public boolean notInList(int e, List<Integer> list) {
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i) == e) {
                return false;
            }
        }
        return true;
    }

    public int getMinimumDistanceAbscissaIndex(int x, int y, List<Integer> listOfPoints, List<Integer> alreadyFlagged) {
        int minIndex = 0;
        double minDistance = Double.MAX_VALUE;
        for (int i = 0; i < listOfPoints.size(); i += 2) {
            if (calculateDistanceBetweenPoints(x, y, listOfPoints.get(i), listOfPoints.get(i + 1)) < minDistance
                    && (listOfPoints.get(i) != x && listOfPoints.get(i + 1) != y) && notInList(i, alreadyFlagged)) {
                minDistance = calculateDistanceBetweenPoints(x, y, listOfPoints.get(i), listOfPoints.get(i + 1));
                minIndex = i;
            }
        }
        return minIndex;
    }

    public void drawing(String s) {

        Graphics2D g2d = (Graphics2D) graphics;

        g2d.setPaint(Color.red);

        g2d.translate(x, y);

        int w = sWidth;
        int h = sLength;

        int size = point;
        int xPos = x;
        int yPos = y;
        int h2 = Math.max(h, w);
        int w2 = Math.min(h, w);
        double tester = 0;
        r = new Random(2);
        listOfPoints = new ArrayList<>();
        if (s.equals("Ellipse")) {
            for (int i = 0; i < size; i++) {
                do {
                    xPos = Math.abs(r.nextInt(w * 10)) % w;
                    yPos = Math.abs(r.nextInt(h * 10)) % h;
                    if (w >= h) {
                        tester = (Math.pow(xPos - h2 / 2, 2) / Math.pow(w / 2, 2)
                                + Math.pow(yPos - w2 / 2, 2) / Math.pow(h / 2, 2));
                    } else {
                        tester = (Math.pow(xPos - w2 / 2, 2) / Math.pow(w / 2, 2)
                                + Math.pow(yPos - h2 / 2, 2) / Math.pow(h / 2, 2));
                    }
                } while (tester > 1);

                for (int l = 1; l <= 6; l += 1) {
                    g2d.drawOval(xPos, yPos, l, l);
                }

                listOfPoints.add(xPos);
                listOfPoints.add(yPos);
            }

        } else {
            for (int i = 0; i < size; i++) {
                xPos = Math.abs(r.nextInt(w * 10)) % w;
                yPos = Math.abs(r.nextInt(h * 10)) % h;

                for (int l = 1; l <= 6; l += 1) {
                    g2d.drawOval(xPos, yPos, l, l);
                }
                //g2d.drawLine(x, y, x, y);
                listOfPoints.add(xPos);
                listOfPoints.add(yPos);
            }

        }

        int currentIndex = 0;
        int closestIndex = 0;
        double sumDistance = 0;
        List<Integer> alreadyFlagged = new ArrayList<>();
        for (int i = 0; i < listOfPoints.size() / 2 - 1; i++) {
            closestIndex = getMinimumDistanceAbscissaIndex(listOfPoints.get(currentIndex),
                    listOfPoints.get(currentIndex + 1), listOfPoints, alreadyFlagged);
            g2d.drawLine(listOfPoints.get(currentIndex), listOfPoints.get(currentIndex + 1),
                    listOfPoints.get(closestIndex), listOfPoints.get(closestIndex + 1));
            sumDistance += calculateDistanceBetweenPoints(listOfPoints.get(currentIndex),
                    listOfPoints.get(currentIndex + 1), listOfPoints.get(closestIndex),
                    listOfPoints.get(closestIndex + 1));
            alreadyFlagged.add(currentIndex);
            currentIndex = closestIndex;
        }
        // Cost
        double cost = ENERGY_PRICE * ((sumDistance * AVERAGE_CONSUMPTION) / AVERAGE_SPEED);
        this.cost = cost;
        g2d.setFont(new Font("TimesRoman", Font.PLAIN, 20));
        g2d.drawString("Cost: "+(int) cost + "€", 0, -5);
        
        
    }
       
    public void enables(Boolean enabled) {
    	textFieldVille.setEnabled(enabled);
    	comboBoxForme.setEnabled(enabled);
    	textFieldLength.setEnabled(enabled);
    	textFieldWidth.setEnabled(enabled);
    	textFieldPoint.setEnabled(enabled);
    	textFieldRech.setEnabled(enabled);
    	tableRech.setEnabled(enabled);
    }
         
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
            java.util.logging.Logger.getLogger(NetworkCard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(NetworkCard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(NetworkCard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(NetworkCard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new NetworkCard().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnNewButton;
    private javax.swing.JComboBox comboBoxForme;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable tableRech;
    private javax.swing.JTextField textFieldLength;
    private javax.swing.JTextField textFieldPoint;
    private javax.swing.JTextField textFieldRech;
    private javax.swing.JTextField textFieldVille;
    private javax.swing.JTextField textFieldWidth;
    // End of variables declaration//GEN-END:variables
}
