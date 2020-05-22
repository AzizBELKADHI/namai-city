package com.client.view;

import javax.swing.*;
import javax.swing.Timer;
import java.awt.*;
import java.util.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;


//	public void DrawLines(ArrayList<Point2D.Double> graph,Graphics2D g,Color c) {
//		for (int i = 0; i < graph.size(); i++) {
//			g.setColor(c);

//		for (int j = i + 1; j < graph.size(); j++) {

//				double x1 = graph.get(i).x;
//				double y1 = graph.get(i).y;
//				double x2 = graph.get(i+1).x;
//				double y2 = graph.get(i+1).y;
//				g.draw(new Line2D.Double(x1,y2,x1;y2));
//			}

//	}


class Surface extends JPanel implements ActionListener {

  private final int DELAY = 150000;
  private final double ENERGY_PRICE = 0.15; //(euro)
  private final double AVERAGE_SPEED = 19.6; // (km/h)
  private final double AVERAGE_CONSUMPTION = 120; //(kw/h)
  private Timer timer;
  private String point;
  private String width;
  private String height;
  private double cost = 0;

  private Graphics p;
  public Surface(String width,String height, String point, Graphics p) {
    this.width=width;
    this.height=height;
    this.point=point;
    this.p=p;
    initTimer();
  }

  private void initTimer() {

    timer = new Timer(DELAY, this);
    timer.start();
  }

  public Timer getTimer() {

    return timer;
  }

  public double calculateDistanceBetweenPoints(
          int x1,
          int y1,
          int x2,
          int y2) {
    return Math.sqrt((y2 - y1) * (y2 - y1) + (x2 - x1) * (x2 - x1));
  }

  public boolean notInList(int e, List<Integer> list) {
    for (int i = 0; i < list.size(); i++) {
      if (list.get(i) == e) {
        return  false;
      }
    }
    return true;
  }

  public int getMinimumDistanceAbscissaIndex(int x, int y, List<Integer> listOfPoints, List<Integer> alreadyFlagged) {
    int minIndex = 0;
    double minDistance = Double.MAX_VALUE;
    for (int i = 0; i < listOfPoints.size(); i+=2) {
      if (calculateDistanceBetweenPoints(x, y, listOfPoints.get(i), listOfPoints.get(i+1)) < minDistance
      && (listOfPoints.get(i) != x && listOfPoints.get(i+1) != y) && notInList(i, alreadyFlagged)) {
        minDistance = calculateDistanceBetweenPoints(x, y, listOfPoints.get(i), listOfPoints.get(i+1));
        minIndex = i;
      }
    }
    return minIndex;
  }


  public void drawing() {


    System.out.println(p);
    Graphics2D g2d = (Graphics2D) p;

    g2d.setPaint(Color.red);

    g2d.translate(400, 200);

    int w = Integer.parseInt(width);
    int h = Integer.parseInt(height);


    Random r = new Random();

    List<Integer> listOfPoints = new ArrayList<>();
    int size =  Integer.parseInt(point);
    for (int i = 0; i < size; i++) {

      int x = Math.abs(r.nextInt(w*10)) % w;
      int y = Math.abs(r.nextInt(h*10)) % h;

      g2d.drawLine(x, y, x, y);
      listOfPoints.add(x);
      listOfPoints.add(y);
    }

    int currentIndex = 0;
    int closestIndex = 0;
    double sumDistance = 0;
    List<Integer> alreadyFlagged = new ArrayList<>();
    for (int i = 0; i <listOfPoints.size()/2 - 1; i++) {
      closestIndex = getMinimumDistanceAbscissaIndex(listOfPoints.get(currentIndex), listOfPoints.get(currentIndex + 1), listOfPoints, alreadyFlagged);
      g2d.drawLine(listOfPoints.get(currentIndex), listOfPoints.get(currentIndex + 1), listOfPoints.get(closestIndex), listOfPoints.get(closestIndex + 1));
      sumDistance+=calculateDistanceBetweenPoints(listOfPoints.get(currentIndex), listOfPoints.get(currentIndex + 1), listOfPoints.get(closestIndex), listOfPoints.get(closestIndex + 1));
      alreadyFlagged.add(currentIndex);
      currentIndex = closestIndex;
    }
    // Coût
    double cost = ENERGY_PRICE * ((sumDistance * AVERAGE_CONSUMPTION)/AVERAGE_SPEED);
    this.cost = cost;
    g2d.drawString((int) cost + "€", 10, 10);

  }

  @Override
  public void paintComponent(Graphics g) {
    super.paintComponent(g);
    drawing();
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    repaint();
  }
}



public class NetworkCard extends JFrame{
  private int nbPoints;
  private int budget;
  private int taille;
  private String forme;

  private JFrame frame;
  private JTextField textFieldWidth;
  private JTextField textFieldLength;
  private JTextField textFieldPoint;
  private JTextField textField_3;


  private JComboBox comboBoxForme;

  int x,y;
  int ax,by;
  String s ="";

  static Graphics g ;



  /**
   * Launch the application.
   */
  public static void main(String[] args) {
    EventQueue.invokeLater(new Runnable() {
      public void run() {
        try {
          NetworkCard window = new NetworkCard();
          window.frame.setVisible(true);
        } catch (Exception e) {
          e.printStackTrace();
        }
      }
    });
  }

  /**
   * Create the application.
   */
  public NetworkCard() {
    x = 400; //x=200;
    y = 200;
    initialize();

  }


  public void test(Graphics g) {
    g.setColor(Color.BLACK);
    //g.drawString("Design by ", 1200, 400);
    //g.drawString("NAMAI", 1200, 420);
    //g.drawString("Y", 610, 70);
    //g.drawString("Y'", 610, 800);
    //g.drawString("X", 190, 600);
    //g.drawString("X'", 1000, 600);
    System.out.println("x " +x);
    if(x==300&&y==3000){
      g.drawString("Origin(0,0)", 310, 314);
    }
    //g.drawLine(600, 70, 600, 800);
    //g.drawLine(200,600,1000,600);
    if(x>600||y<600){
      //g.drawString((String) cb.getSelectedItem(), 200, 200);
      //s= (String) cb.getSelectedItem();
      //se = (String) cb1.getSelectedItem();


      //System.out.println((String)comboBoxForm.getItemAt(0));
      s= (String) comboBoxForme.getItemAt(comboBoxForme.getSelectedIndex());
      //s= "Ellipse";
      // String s = (String) comboBoxForme.getSelectedItem();



    }

    if(s.equals("Square")){
      g.setColor(Color.GREEN);
      g.drawRect(x, y, Integer.parseInt(this.textFieldWidth.getText()), Integer.parseInt(this.textFieldLength.getText()));
    }


    else if(s.equals("Rectangle")){
      g.setColor(Color.RED);
      g.drawRect(x, y, Integer.parseInt(this.textFieldWidth.getText()), Integer.parseInt(this.textFieldLength.getText()));
    }

    else if(s.equals("Ellipse")){
      g.setColor(Color.BLACK);
      System.out.println(x+" "+y);
      g.drawOval(x, y, Integer.parseInt(this.textFieldWidth.getText()), Integer.parseInt(this.textFieldLength.getText()));
    }

    repaint();
   
  }



// récupérer ce que L'Utilisateur a choisi dans la COMBOBOX !!!!!!!

  private String comboBoxForme() {
    if (s.equals("Rectangle")) {
      System.out.println("");
      return s= "Rectangle";
    }else if (s.equals("Square")) {
      return s= ("Square");
    }
    return s= "Ellipse";
  }

  /**
   * Initialize the contents of the frame.
   */
  private void initialize() {

    frame = new JFrame();
    frame.getContentPane().setBackground(Color.LIGHT_GRAY);
    frame.getContentPane().setForeground(new Color(128, 128, 128));
    frame.setBounds(100, 100, 978, 570);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.getContentPane().setLayout(null);

    JPanel panel_3 = new JPanel();
    panel_3.setBounds(-2, 0, 141, 542);
    panel_3.setBackground(new Color(51, 204, 204));
    frame.getContentPane().add(panel_3);
    panel_3.setLayout(null);

    JLabel lblNewLabel_5 = new JLabel("Length");
    lblNewLabel_5.setFont(new Font("Avenir", Font.PLAIN, 13));
    lblNewLabel_5.setHorizontalAlignment(SwingConstants.CENTER);
    lblNewLabel_5.setBounds(6, 204, 135, 16);
    panel_3.add(lblNewLabel_5);

    textFieldLength = new JTextField();
    textFieldLength.setBounds(5, 225, 136, 19);
    panel_3.add(textFieldLength);
    textFieldLength.setEditable(true);
    textFieldLength.setColumns(10);

    JLabel lblNewLabel_6 = new JLabel("Width");
    lblNewLabel_6.setHorizontalAlignment(SwingConstants.CENTER);
    lblNewLabel_6.setFont(new Font("Avenir", Font.PLAIN, 13));
    lblNewLabel_6.setBounds(5, 260, 129, 16);
    panel_3.add(lblNewLabel_6);

    textFieldWidth = new JTextField();
    textFieldWidth.setBounds(4, 280, 137, 19);
    textFieldWidth.addMouseListener(new MouseAdapter() {
      @Override
      public void mouseClicked(MouseEvent e) {
      }
    });
    textFieldWidth.setEditable(true);
    panel_3.add(textFieldWidth);
    textFieldWidth.setColumns(10);


    JLabel lblNewLabel_2 = new JLabel("Points");
    lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
    lblNewLabel_2.setFont(new Font("Avenir", Font.PLAIN, 13));
    lblNewLabel_2.setBounds(5, 316, 130, 16);
    panel_3.add(lblNewLabel_2);

    textFieldPoint = new JTextField();
    textFieldPoint.setBounds(4, 336, 137, 19);
    textFieldPoint.setColumns(10);
    panel_3.add(textFieldPoint);

    JButton btnNewButton = new JButton("Validate");
    btnNewButton.setBounds(21, 424, 99, 29);
    btnNewButton.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        
    	  String length = textFieldLength.getText();
        String width = textFieldWidth.getText();
        String point = textFieldPoint.getText();
        g = frame.getGraphics();
        test(g);
        System.out.println(g);
        Surface surface = new Surface(width, length, point, g);
        surface.drawing();
        
      }
    });




    btnNewButton.setForeground(new Color(128, 128, 128));
    btnNewButton.setBackground(new Color(34, 139, 34));
    panel_3.add(btnNewButton);
    btnNewButton.setFont(new Font("Lucida Grande", Font.PLAIN, 14));


    JLabel lblNewLabel = new JLabel("Shape");
    lblNewLabel.setFont(new Font("Avenir", Font.PLAIN, 13));
    lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
    lblNewLabel.setBounds(41, 96, 61, 16);
    panel_3.add(lblNewLabel);


    String state[] = {"Ellipse", "Square", "Rectangle"};
    comboBoxForme = new JComboBox(state);
    comboBoxForme.setBounds(17, 126, 117, 27);
    panel_3.add(comboBoxForme);



  

    JLabel lblNewLabel_1 = new JLabel("Discover the best map network card option for your city");
    lblNewLabel_1.setBounds(142, 0, 465, 24);
    frame.getContentPane().add(lblNewLabel_1);
    lblNewLabel_1.setToolTipText("");
    lblNewLabel_1.setForeground(new Color(0, 0, 0));
    lblNewLabel_1.setBackground(new Color(255, 255, 102));
    lblNewLabel_1.setFont(new Font("Times New Roman", Font.PLAIN, 20));



    JPanel panel = new JPanel();
    panel.setBounds(139, 0, 511, 29);
    frame.getContentPane().add(panel);
    panel.setBackground(new Color(255, 204, 51));
    panel.setForeground(Color.BLACK);
    panel.setLayout(null);

  }


  // algo de création de points //


}