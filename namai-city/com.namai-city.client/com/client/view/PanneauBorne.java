package com.client.view;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.Socket;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.border.Border;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.client.application.TestJson;

public class PanneauBorne extends JPanel implements ActionListener {
	private JMenuItem historique,limiteVoitures; 
	private Button bouton;
	private JLabel label;
	private JTable tableau;
	private JLabel label2; 
	private ArrayList<JSONObject> allBornes;
	private Object[][] data;
	
	public PanneauBorne() {
		revalidate();
		repaint();
	}
	
	
	
	public void init() throws UnsupportedEncodingException, SQLException, IOException {  
		
		System.out.println("je rentre dans le PanneauBorne()");
		JSONObject bornes = TestJson.getBornes();
		JMenuBar mb;    
		JMenu actions;        
		JTextArea ta;        
		historique=new JMenuItem("historique");    
		limiteVoitures=new JMenuItem("changer seuil max");   
		label = new JLabel();
		label.setText("changement");
		historique.addActionListener(this);    
		limiteVoitures.addActionListener(this);
		mb=new JMenuBar();    
		actions=new JMenu("actions");       
		actions.add(historique);actions.add(limiteVoitures);
    	mb.add(actions);   
	//	this.setJMenuBar(mb);  
	//	this.setSize(600,800);    
		this.allBornes = (ArrayList<JSONObject>) bornes.get("bornes");
		this.data = new Object[allBornes.size()][3];
		for(int i = 0; i<allBornes.size(); i++) {
		//		System.out.println(allBornes.get(i));
				data[i][0] = allBornes.get(i).get("Id_borne");
				data[i][1] = allBornes.get(i).get("position");
				if(allBornes.get(i).get("state").equals("0")) {
					data[i][2] = "baissé";
				}
				else {
					data[i][2] = "relevé";
				}
		
		}
		  
		    //Les titres des colonnes
		    String  title[] = {"borne", "position", "état"};
		    tableau = new JTable(data, title);
		    JPanel panneauBornesOrigine = new JPanel();
		    //panneauBornesOrigine.setLayout(new BorderLayout());
		    JPanel panneauBornes = new JPanel();
		    label2 = new JLabel("nombre de voitures actuellement :" + bornes.get("actualCars"));
		    JLabel label4 = new JLabel("nombre de voitures maximum :" + bornes.get("maxCars"));
		    panneauBornesOrigine.add(label2);
		    panneauBornesOrigine.add(label4);
		    panneauBornes.add(new JScrollPane(tableau));
		    this.setBackground(Color.green);
		    label2.setFont(new Font("Dialog", Font.BOLD, 14));
		    label4.setFont(new Font("Dialog", Font.BOLD, 14));
		    Border border = BorderFactory.createLineBorder(Color.black, 2);
	        label2.setBorder(border);
	        label4.setBorder(border);

		    Dimension d = new Dimension( 400, 150 );
		    tableau.setPreferredScrollableViewportSize( d );
		    this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		    bouton = new Button("lancer simulation");
		    bouton.addActionListener(this);
		    this.add(panneauBornes);
		    this.add(panneauBornesOrigine);
		    this.add(bouton);
		    
		    
		}     
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			if(e.getSource() == historique) {	  
				JFrame fenetre2 = new JFrame();
				fenetre2.setLocationRelativeTo(null);
				fenetre2.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
				fenetre2.setTitle("bornes");
				fenetre2.setSize(1300, 720);
				fenetre2.setVisible(true);
			}
			
			if(e.getSource() == limiteVoitures) {	  
				  String name = JOptionPane.showInputDialog("saisissez nouveau seuil max vehicules :");
			      JOptionPane.showMessageDialog(this, "Hello " + name + '!');
			}     
			
			if(e.getSource() == bouton) {	  
				try {
					System.out.println("je suis ici je vais lancer la simulation");
					TestJson.launchSimulation();
					new Thread() {
				    	public void run() {
				    	try {
				    	Socket s = new Socket("172.31.249.49",3001);
				    	while(true) {
				    		DataInputStream dis;
								dis = new DataInputStream(s.getInputStream());
								String rep = dis.readUTF();
								JSONParser parser = new JSONParser(); 
								JSONObject json;
								try {
									json = (JSONObject) parser.parse(rep);
									if(json.containsKey("etat")) { 
										if(json.get("etat").equals("alert")) {
										for(int i = 0; i<allBornes.size(); i++) {
											data[i][2] = "relevé";						
									}
									}
									if(json.get("etat").equals("normal")) {
											for(int i = 0; i<allBornes.size(); i++) {
												data[i][2] = "baissé";						
											}
										}
									revalidate();
						    		repaint();
								 //   fenetre.setVisible(true);
									}
									
								//	int voitures = Integer.valueOf((String) json.get("vehicules"))
									String voitures = (String)json.get("vehicules");
									System.out.println(voitures);
									label2.setText("nombre de voitures dans la ville :: " + voitures);
									revalidate();
						    		repaint();
								} catch (ParseException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}			 
							//	int nbVoitures = dis.readInt();
					    	/*	System.out.println("nombre de voitures: "+ nbVoitures);
					    		JLabel myText = new JLabel("Nombres de voitures: " +nbVoitures, SwingConstants.CENTER);	
					    		fenetre.getContentPane().remove(myText);
					    		fenetre.getContentPane().add(myText,BorderLayout.NORTH);
					    		fenetre.getContentPane().revalidate();
							    fenetre.setVisible(true); */
							/*
							    System.out.println("test reception alerte : " + test);
					    		System.out.println("test Boucle infinie"); */
							} }
				    	catch (IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
				    	}
				    }.start();
				} catch (SQLException | IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				System.out.println("je suis quand meme arrivé ici");
				label.setText("etat change");
			}     


	}
}
