package com.client.view;



import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JViewport;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.util.List;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

//////////////////////////////////////////////////////////////////////////
public class NetworkCard extends javax.swing.JFrame {

    Graphics graphics;
    boolean firstRun = false;
    String s = "";
    int x = 300;
    int y = 200;
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
    private final double ENERGY_PRICE = 0.15; // (euro) 0,15€
    private final double AVERAGE_SPEED = 19.6; // (km/h)  La Vitesse d'un tram est de 19,6 km/h
    private final double AVERAGE_CONSUMPTION = 120; // (kw/h) Un tram consomme 120kw/h

    private double cost = 0;
    private Random r; // points
    private List<Integer> listOfPoints;

    //Initialisation de la classe //
    
    public NetworkCard() {
        initComponents();
        jScrollPane2.getViewport().addChangeListener(new ListenAdditionsScrolled());
        SwingUtilities.getRootPane(btnNewButton).setDefaultButton(btnNewButton);

        // Listener sur le textField pour changer la valeur de la longueur suite à la valeur de la hauteur dans le cas du "Square" //
        
        textFieldLength.getDocument().addDocumentListener(new DocumentListener() {

            
            public void insertUpdate(DocumentEvent e) {
                if (s.equals("Square")) {
                    textFieldWidth.setText(textFieldLength.getText());
                }
            }

        
            public void removeUpdate(DocumentEvent e) {
                if (s.equals("Square")) {
                    textFieldWidth.setText(textFieldLength.getText());
                }
            }

           
            public void changedUpdate(DocumentEvent e) {
                if (s.equals("Square")) {
                    textFieldWidth.setText(textFieldLength.getText());
                }
            }
        });

    }

    
     // This method is called from within the constructor to initialize la forme  //
    
    private void initComponents() {

        jScrollPane2 = new javax.swing.JScrollPane(); //
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

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowActivated(java.awt.event.WindowEvent evt) {
                formWindowActivated(evt);
            }
        });

        jScrollPane2.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentResized(java.awt.event.ComponentEvent evt) {
                jScrollPane2ComponentResized(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(255, 255, 204));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 11)); 
        jLabel1.setText("Shape");

        comboBoxForme.setFont(new java.awt.Font("Tahoma", 1, 11));
        comboBoxForme.setForeground(new java.awt.Color(153, 0, 0));
        comboBoxForme.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Ellipse", "Square", "Rectangle" }));
        comboBoxForme.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboBoxFormeActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 11)); 
        jLabel2.setText("Length");

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 11)); 
        jLabel3.setText("Width");

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 11)); 
        jLabel4.setText("Points");

        btnNewButton.setBackground(new java.awt.Color(255, 204, 204));
        btnNewButton.setFont(new java.awt.Font("Tahoma", 1, 12)); 
        btnNewButton.setForeground(new java.awt.Color(0, 0, 204));
        btnNewButton.setText("Valider");
        btnNewButton.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        btnNewButton.setOpaque(false);
        btnNewButton.addActionListener(new java.awt.event.ActionListener() {
        	
        	/////////////
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNewButtonActionPerformed(evt);
            }
        });

        jLabel5.setBackground(new java.awt.Color(204, 255, 255));
        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 14)); 
        jLabel5.setText("Discover the best map network card option for your city");
        jLabel5.setOpaque(true);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(comboBoxForme, javax.swing.GroupLayout.Alignment.LEADING, 0, 113, Short.MAX_VALUE)
                    .addComponent(textFieldLength, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(textFieldWidth, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(textFieldPoint, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnNewButton, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 395, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(467, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(50, 50, 50)
                        .addComponent(jLabel1))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addComponent(comboBoxForme, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel2)
                .addGap(18, 18, 18)
                .addComponent(textFieldLength, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel3)
                .addGap(18, 18, 18)
                .addComponent(textFieldWidth, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel4)
                .addGap(18, 18, 18)
                .addComponent(textFieldPoint, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(34, 34, 34)
                .addComponent(btnNewButton, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(267, Short.MAX_VALUE))
        );

        jScrollPane2.setViewportView(jPanel1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2)
        );

        pack();
    }

    // Action du boutton Validate ////
    
    // Messages d'erreur //

    private void btnNewButtonActionPerformed(java.awt.event.ActionEvent evt) {
        s = (String) comboBoxForme.getItemAt(comboBoxForme.getSelectedIndex());
        if (textFieldLength.getText().length() == 0 || textFieldWidth.getText().length() == 0 || textFieldPoint.getText().length() == 0) {
            JOptionPane.showMessageDialog(null, "You must fill all the fields to continue", "Warning : Empty parameter(s)", JOptionPane.WARNING_MESSAGE);
        } else {

            try {
                sWidth = Integer.parseInt(textFieldWidth.getText());
                sLength = Integer.parseInt(textFieldLength.getText());
                point = Integer.parseInt(textFieldPoint.getText());

                // Condition pour vérifier si les valeurs sont changés
                if ((sWidth != oldWidth || sLength != oldHeigth || point != oldPoint || !s.equals(oldShape))) {
                    firstRun = true;
                    prepare();

                    drawing(s);
                    setVisible(true);

                }
            } catch (NumberFormatException exc) {
                JOptionPane.showMessageDialog(null, "You must enter valid parameters (number) to continue", "Warning : wrong parameter(s) type", JOptionPane.WARNING_MESSAGE);
            }
        }
    }

    // Listener du scrollPane pour reproduire le drawing dans chaque changement de position du scrollbar //
    public class ListenAdditionsScrolled implements ChangeListener {

       
        public void stateChanged(ChangeEvent e) {
            if (firstRun != false) {
                prepare();
                drawing(s);
            }

        }

    }

    // Listener de la JFrame pour reproduire le drawing lors de réduire/réstaurer la page //
    
    
    private void formWindowActivated(java.awt.event.WindowEvent evt) {

        if (firstRun != false) {
            prepare();
            drawing(s);
        }
    }

    // Changement de la taille de la page , le drawing doit rester //
    private void jScrollPane2ComponentResized(java.awt.event.ComponentEvent evt) {

        if (firstRun != false) {
            prepare();
            drawing(s);
        }
    }
    // Listener sur le comboBox pour changer la valeur de la longueur suite à la valeur de la hauteur dans le cas du "Square" // 
      // width = length//
    
    private void comboBoxFormeActionPerformed(java.awt.event.ActionEvent evt) {
        s = (String) comboBoxForme.getItemAt(comboBoxForme.getSelectedIndex());
        if (s.equals("Square")) {
            textFieldWidth.setEnabled(false);
            textFieldWidth.setText(textFieldLength.getText());
        } else {
            textFieldWidth.setEnabled(true);
        }
    }
    /* préparer les variables; effacer l'ancien drawing relativement à la position actuelle du scrollBar récupérée avec le viewport;
     * and aussi 
     changer la taille du JPanel et jScrollPane suite à la hauteur et la largeur saisie par l'utilisateur
     Le clear genre  */
    
    public void prepare() {
        graphics = jPanel1.getGraphics();
        pWidth = jPanel1.getWidth();
        pHeigth = jPanel1.getHeight();
        viewport = jScrollPane2.getViewport();
        xscroll = viewport.getViewPosition().x;
        yscroll = viewport.getViewPosition().y;
        graphics.clearRect(x - xscroll, y - yscroll, pWidth, pHeigth);
        graphics.setColor(new Color(255, 255, 204));
        graphics.fillRect(x - xscroll, y - yscroll, pWidth, pHeigth);
        jPanel1.setPreferredSize(new Dimension(sWidth + 320, sLength + 220));
        jScrollPane2.setPreferredSize(new Dimension(sWidth + 320, sLength + 220));
        setVisible(true);

        draw(sWidth, sLength);
    }

    // Let's draw the card  //
    public void draw(int width, int length) {

        if (s.equals("Square")) {

            graphics.setColor(Color.GREEN);
            graphics.drawRect(x, y, width, length);

        } else if (s.equals("Rectangle")) {

            graphics.setColor(Color.DARK_GRAY);
            graphics.drawRect(x, y, width, length);

        } else if (s.equals("Ellipse")) {

            graphics.setColor(Color.BLACK);
            graphics.drawOval(x, y, width, length);

        }

        oldWidth = sWidth;
        oldHeigth = sLength;
        oldPoint = point;
        oldShape = s;

    }

    // Algo calcul de la distance entre les points créés , les stations  //
    
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
        // Cost //
        
        double cost = ENERGY_PRICE * ((sumDistance * AVERAGE_CONSUMPTION) / AVERAGE_SPEED);
        this.cost = cost;
        g2d.drawString((int) cost + "€", 10, 10);

    


   /*  public static void main(String args[]) {
       
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
        */
        

        // Create and display the form //
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new NetworkCard().setVisible(true);
            }
        });
    }
    // Variables declaration
    private javax.swing.JButton btnNewButton;
    private javax.swing.JComboBox comboBoxForme;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextField textFieldLength;
    private javax.swing.JTextField textFieldPoint;
    private javax.swing.JTextField textFieldWidth;
    
}
