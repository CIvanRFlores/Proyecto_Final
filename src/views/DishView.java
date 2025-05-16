package views;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import controllers.AuthController;
import customClasses.RoundButton;
import customClasses.RoundPanel;

public class DishView {

	public JFrame frame;
	public Image image;
	public ImageIcon imageIcon;
	public int relativeXSize;
	public int relativeYSize;
	AuthController ac;
	
	
	public DishView(String title, int frameWidth, int frameHeight) {
		frame = new JFrame(); //crear JFrame	
		imageIcon = new ImageIcon("src/images/elManglarLogo.png"); //icono de la ventana
		frame.setIconImage(imageIcon.getImage());
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //cerrar ventana si se presiona la X
		frame.setSize(frameWidth, frameHeight); //tamaño de la ventana
		frame.setLocationRelativeTo(null); //colocar la ventana en el centro de la pantalla
		frame.setResizable(true); //redimensionar la ventana
		frame.setMinimumSize(new Dimension(350, 350));
		frame.setTitle(title); //colocar título a la ventana
	}
	
	public void dishes() {
		/**PANEL DE FONDO**/
		JPanel backgroundPnl = new JPanel();
		backgroundPnl.setBackground(Color.white);
		backgroundPnl.setLayout(new BorderLayout());
		frame.setContentPane(backgroundPnl); //colocar como contenido del frame al panel de fondo
		
		
		/**BOTONES LATERALES DE NAVEGACIÓN**/
		JPanel buttonPnl = new JPanel();
		buttonPnl.setBackground(Color.decode("#DEFFDB"));
		buttonPnl.setLayout(new GridLayout(6, 1, 0, 10));
		backgroundPnl.add(buttonPnl, BorderLayout.WEST);
		
		//nombre del restaurante 
		image = new ImageIcon("src/images/elManglarName.png").getImage().getScaledInstance(200, 40, Image.SCALE_SMOOTH);
		imageIcon = new ImageIcon(image);
		JLabel restaurantNameLbl = new JLabel(imageIcon);
		buttonPnl.add(restaurantNameLbl);
		
		//botón de platillos
		image = new ImageIcon("src/images/food.png").getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
		imageIcon = new ImageIcon(image);
		RoundButton foodBttn = new RoundButton(imageIcon, 30); //botón
		foodBttn.setBackground(Color.decode("#3C7E3A"));
		foodBttn.setFont(new Font("Caladea Bold", Font.BOLD, 28)); 
		foodBttn.setForeground(Color.white);
		foodBttn.setText("Platillos");
		buttonPnl.add(foodBttn);
		
		//botón de órdenes
		image = new ImageIcon("src/images/order.png").getImage().getScaledInstance(30, 35, Image.SCALE_SMOOTH);
		imageIcon = new ImageIcon(image);
		RoundButton orderbttn = new RoundButton(imageIcon, 30); //botón
		orderbttn.setBackground(Color.decode("#244E23"));
		orderbttn.setFont(new Font("Caladea Bold", Font.BOLD, 28)); 
		orderbttn.setForeground(Color.white);
		orderbttn.setText("Órdenes");
		buttonPnl.add(orderbttn);
		
		//botón de clientes
		image = new ImageIcon("src/images/client.png").getImage().getScaledInstance(30, 35, Image.SCALE_SMOOTH);
		imageIcon = new ImageIcon(image);
		RoundButton clientBttn = new RoundButton(imageIcon, 30); //botón
		clientBttn.setBackground(Color.decode("#244E23"));
		clientBttn.setFont(new Font("Caladea Bold", Font.BOLD, 28)); 
		clientBttn.setForeground(Color.white);
		clientBttn.setText("Clientes");
		buttonPnl.add(clientBttn);
		
		//botón de inventario
		image = new ImageIcon("src/images/inventory.png").getImage().getScaledInstance(30, 28, Image.SCALE_SMOOTH);
		imageIcon = new ImageIcon(image);
		RoundButton inventoryBttn = new RoundButton(imageIcon, 30); //botón
		inventoryBttn.setBackground(Color.decode("#244E23"));
		inventoryBttn.setFont(new Font("Caladea Bold", Font.BOLD, 28)); 
		inventoryBttn.setForeground(Color.white);
		inventoryBttn.setText("Inventario");
		buttonPnl.add(inventoryBttn);
		
		//botón de volver
		image = new ImageIcon("src/images/return.png").getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
		imageIcon = new ImageIcon(image);
		RoundButton returnBttn = new RoundButton(imageIcon, 30); //botón
		returnBttn.setBackground(Color.decode("#244E23"));
		returnBttn.setFont(new Font("Caladea Bold", Font.BOLD, 28)); 
		returnBttn.setForeground(Color.white);
		returnBttn.setText("Volver");
		buttonPnl.add(returnBttn);
		
		returnBttn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				frame.dispose(); //destruir ventana actual y llevar al usuario a la ventana de login
				ac = new AuthController("Login", frame.getWidth(), frame.getHeight()); //crear controlador de login y asignar parámetros a la ventana
				ac.login(); //llamar al método que crea y muestra la ventana de login
			}
			
		});
		
		
		/**PANEL PRINCIPAL/CENTRAL*/
		JPanel mainPnl = new JPanel();
		mainPnl.setBackground(Color.white);
		mainPnl.setBorder(BorderFactory.createEmptyBorder(60, 60, 60, 30)); 
		mainPnl.setLayout(new BorderLayout());
		backgroundPnl.add(mainPnl, BorderLayout.CENTER);
		
		
		/**CABECERA*/
		JPanel headerPnl = new JPanel();
		headerPnl.setLayout(new GridLayout(2, 1, 0, 15));
		headerPnl.setOpaque(false); //tiene fondo o no
		mainPnl.add(headerPnl, BorderLayout.NORTH);
		
		//título que alude a la ventana actual
		JLabel dishesLbl = new JLabel("Platillos");
		dishesLbl.setFont(new Font("Caladea Bold", Font.BOLD, 36));
		dishesLbl.setForeground(Color.decode("#244E23")); //color de letra
		dishesLbl.setHorizontalAlignment(JLabel.LEFT); //alinear etiqueta a la izquierda
		dishesLbl.setHorizontalAlignment(SwingConstants.LEFT); //centrar texto de la etiqueta
		headerPnl.add(dishesLbl);
		
		//panel de barra de búsqueda y botones
		JPanel actionPnl = new JPanel();
		actionPnl.setLayout(new GridLayout(1, 3, 30, 0));
		actionPnl.setOpaque(false); //tiene fondo o no
		headerPnl.add(actionPnl);
		
		//barra de búsqueda
		RoundPanel searchBarPnl = new RoundPanel(30);  
		searchBarPnl.setBackground(Color.white);
		searchBarPnl.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); //borde invisible para centrar elementos que el panel contenga 
		searchBarPnl.setForeground(Color.decode("#244E23")); //color del borde
		searchBarPnl.setLayout(new BorderLayout(10, 0));
		searchBarPnl.setPreferredSize(new Dimension(414, 30));
		actionPnl.add(searchBarPnl);
		
		//imagen de lupa 
		image = new ImageIcon("src/images/magnifyingGlass.png").getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH);
		imageIcon = new ImageIcon(image);
		JLabel logoTextLbl = new JLabel(imageIcon);
		searchBarPnl.add(logoTextLbl, BorderLayout.WEST);
		
		//textfield
		JTextField searchTxtFld = new JTextField();
		searchTxtFld.setBorder(null);
		searchTxtFld.setFont(new Font("Caladea Bold", Font.BOLD, 14)); 
		searchTxtFld.setForeground(Color.decode("#999999")); //color de letra
		searchTxtFld.setOpaque(false); //tiene fondo o no
		searchBarPnl.add(searchTxtFld,  BorderLayout.CENTER);
				
		//botón de barra de búsqueda
		RoundButton searchBttn = new RoundButton(30);
		searchBttn.setBackground(Color.decode("#244E23"));
		searchBttn.setFont(new Font("Caladea Bold", Font.BOLD, 20));
		searchBttn.setForeground(Color.white);
		searchBttn.setText("Buscar");
		actionPnl.add(searchBttn);
		
		//botón de barra de búsqueda
		RoundButton addBttn = new RoundButton(30);
		addBttn.setBackground(Color.decode("#244E23"));
		addBttn.setFont(new Font("Caladea Bold", Font.BOLD, 20));
		addBttn.setForeground(Color.white);
		addBttn.setText("Nuevo");
		actionPnl.add(addBttn);
		
		frame.setVisible(true);
	}
	
}