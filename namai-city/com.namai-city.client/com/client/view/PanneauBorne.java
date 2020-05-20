	package com.client.view;
	
	import java.awt.BorderLayout;
	import java.awt.Button;
	import java.awt.CardLayout;
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
	import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
	import java.util.Date;
	
	import javax.swing.*;
	import javax.swing.border.Border;
	
	import org.json.simple.JSONObject;
	import org.json.simple.parser.JSONParser;
	import org.json.simple.parser.ParseException;
	
	import com.client.application.DateTimePicker;
	import com.client.application.TestJson;
	
	public class PanneauBorne extends JPanel implements ActionListener {
		private JMenuItem historique,limiteVoitures; 
		private Button bouton;
		private JLabel label;
		private JTable tableau;
		private JLabel labelNbCars; 
		private ArrayList<JSONObject> allBornes;
		private JComboBox liste1;
		private JComboBox liste2;
		private Object[][] data;
		private Button boutonFiltre;
		private JPanel Panneau2;
		private DateTimePicker dateTimePicker;
		private DateTimePicker dateTimePickerFin;
		private JScrollPane pane;
		private Object[][] dataCars;
		private String  title[];
	    private JTable tableauCars;
	    private JPanel panneauFiltre;
		public PanneauBorne() {
			revalidate();
			repaint();
		}
		
		
		
		public void init() throws UnsupportedEncodingException, SQLException, IOException {  
	
			JTabbedPane selection = new JTabbedPane();
			this.setLayout(new CardLayout());
			this.add(selection);
			System.out.println("je rentre dans le PanneauBorne()");
			JSONObject bornes = TestJson.getBornes();     
	  
			label = new JLabel();
			label.setText("changement");
			this.allBornes = (ArrayList<JSONObject>) bornes.get("bornes");
			this.data = new Object[allBornes.size()][3];
			for(int i = 0; i<allBornes.size(); i++) {
			
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
			    JPanel panneauBornes = new JPanel();
			    labelNbCars = new JLabel("nombre de voitures actuellement :" + bornes.get("actualCars"));
			    JLabel labelMaxCars = new JLabel("nombre de voitures maximum :" + bornes.get("maxCars"));
			    panneauBornesOrigine.add(labelNbCars);
			    panneauBornesOrigine.add(labelMaxCars);
			    panneauBornes.add(new JScrollPane(tableau));
			    labelNbCars.setFont(new Font("Dialog", Font.BOLD, 14));
			    labelMaxCars.setFont(new Font("Dialog", Font.BOLD, 14));
			    Border border = BorderFactory.createLineBorder(Color.black, 2);
			    labelNbCars.setBorder(border);
			    labelMaxCars.setBorder(border);
	
			    Dimension d = new Dimension( 400, 150 );
			    tableau.setPreferredScrollableViewportSize( d );
			    JPanel Panneau1 = new JPanel();
			    Panneau1.setLayout(new BoxLayout(Panneau1, BoxLayout.Y_AXIS));
			    Panneau2 = new JPanel();
			    Panneau2.setLayout(new BorderLayout());
			    panneauFiltre = new JPanel();
			    JLabel dateDebut = new JLabel("date debut :");
			    JLabel dateFin = new JLabel("date fin :");
			    JLabel zone = new JLabel("zone :");
			    JLabel type = new JLabel("type :");
			    Date date = new Date();
			    dateTimePicker = new DateTimePicker();
		        dateTimePicker.setFormats( DateFormat.getDateTimeInstance( DateFormat.SHORT, DateFormat.MEDIUM ) );
		        dateTimePicker.setTimeFormat( DateFormat.getTimeInstance( DateFormat.MEDIUM ) );
		        dateTimePicker.setDate(date);
		        
		        Date dateFinAff = new Date();
		        dateTimePickerFin = new DateTimePicker();
		        dateTimePickerFin.setFormats( DateFormat.getDateTimeInstance( DateFormat.SHORT, DateFormat.MEDIUM ) );
		        dateTimePickerFin.setTimeFormat( DateFormat.getTimeInstance( DateFormat.MEDIUM ) );
		        dateTimePickerFin.setDate(dateFinAff);
		        panneauFiltre.add(dateDebut);
		        panneauFiltre.add(dateTimePicker);
		        panneauFiltre.add(dateFin);
		        panneauFiltre.add(dateTimePickerFin);
		        boutonFiltre = new Button("recherche");
		        Object[] elements = new Object[]{"Entree", "Sortie", "Les deux"};
				liste1 = new JComboBox(elements);

		        Object[] elements2 = new Object[]{"Nord", "Sud", "Est", "Ouest","toute la ville"};
				liste2 = new JComboBox(elements2);
		        
				panneauFiltre.add(type);
				panneauFiltre.add(liste1);
				
				panneauFiltre.add(zone);
				panneauFiltre.add(liste2);
				panneauFiltre.add(boutonFiltre);
				boutonFiltre.addActionListener(this);
				
			    bouton = new Button("lancer simulation");
			    bouton.addActionListener(this);
			    Panneau1.add(panneauBornes);
			    Panneau1.add(panneauBornesOrigine);
			    Panneau1.add(bouton);
			    Panneau2.add(panneauFiltre, BorderLayout.NORTH);
			    selection.addTab("Infos bornes", Panneau1);
			    selection.addTab("Historique", Panneau2);
			    
			    
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
				
				if(e.getSource() == boutonFiltre) {
					 pane =new JScrollPane();
					 DateFormat f = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.mmm");
				     Date a = dateTimePicker.getDate();
				     Date b = dateTimePickerFin.getDate();
				     String x = String.valueOf(liste1.getSelectedItem());
				     String y = String.valueOf(liste2.getSelectedItem());
				     System.out.println(x + " " + y);
				     try {
						JSONObject rep = TestJson.searchVehicule(f.format(a), f.format(b), y, x);
						ArrayList<JSONObject> allCars = (ArrayList<JSONObject>) rep.get("voitures");
						dataCars = new Object[allCars.size()][4];
						for(int i = 0; i<allCars.size(); i++) {
						
								dataCars[i][0] = allCars.get(i).get("vehicule");
								dataCars[i][1] = allCars.get(i).get("date");
								dataCars[i][2] = allCars.get(i).get("position");
								if(allCars.get(i).get("type").equals("IN")) {
									dataCars[i][3] = "entree";
								}
								else {
									dataCars[i][3] = "sortie";
								}
						
						}
						  
						    //Les titres des colonnes
						    String title[] = {"vehicule", "date et heure", "position", "type"};
						    tableauCars = new JTable(dataCars, title);
						    Panneau2.removeAll();			
						    Panneau2.add(panneauFiltre, BorderLayout.NORTH);
						    Panneau2.add(new JScrollPane(tableauCars), BorderLayout.CENTER);
						    Panneau2.revalidate();
						    Panneau2.repaint();
						    revalidate();
						    repaint();
					} catch (SQLException | IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				     
				     

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
										labelNbCars.setText("nombre de voitures dans la ville :: " + voitures);
										revalidate();
							    		repaint();
									} catch (ParseException e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
									}			 
			
						    
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
