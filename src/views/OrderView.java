package views;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import controllers.AuthController;
import controllers.DishController;
import controllers.ClientController;
import controllers.InventoryController;
import customClasses.OrderCard;
import customClasses.RoundButton;
import customClasses.RoundPanel;

public class OrderView {

	public JFrame frame;
	public JPanel buttonPnl;
	public JLabel restaurantNameLbl;
	public RoundButton foodBttn, orderBttn, clientBttn, inventoryBttn, returnBttn;
	public JPanel mainPnl;
	public Image image;
	public ImageIcon imageIcon;
	public String message;
	public String currentWindow;
	public int relativeXSize;
	public int relativeYSize;
	public AuthController ac;
	public DishController dc;
	public ClientController cc;
	public InventoryController ic;
	
	
	public OrderView(String title, int frameWidth, int frameHeight) {
		frame = new JFrame(); //crear JFrame	
		imageIcon = new ImageIcon(OrderView.class.getResource("/images/elManglarLogo.png")); //icono de la ventana
		frame.setIconImage(imageIcon.getImage());
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //cerrar ventana si se presiona la X
		frame.setSize(frameWidth, frameHeight); //tamaño de la ventana
		frame.setLocationRelativeTo(null); //colocar la ventana en el centro de la pantalla
		frame.setResizable(true); //redimensionar la ventana
		frame.setMinimumSize(new Dimension(350, 350));
		frame.setTitle(title); //colocar título a la ventana
		
		/**BOTONES LATERALES DE NAVEGACIÓN**/
		buttonPnl = new JPanel();
		buttonPnl.setBackground(Color.decode("#DEFFDB"));
		buttonPnl.setLayout(new GridLayout(6, 1, 0, 10));
		frame.add(buttonPnl, BorderLayout.WEST);
		
		//nombre del restaurante 
		image = new ImageIcon(OrderView.class.getResource("/images/elManglarName.png")).getImage().getScaledInstance(200, 40, Image.SCALE_SMOOTH);
		imageIcon = new ImageIcon(image);
		restaurantNameLbl = new JLabel(imageIcon);
		buttonPnl.add(restaurantNameLbl);
		
		//botón de platillos
		image = new ImageIcon(OrderView.class.getResource("/images/food.png")).getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
		imageIcon = new ImageIcon(image);
		foodBttn = new RoundButton(30, imageIcon); //botón
		foodBttn.setBackground(Color.decode("#244E23"));
		foodBttn.setFont(new Font("Caladea Bold", Font.BOLD, 28)); 
		foodBttn.setForeground(Color.white);
		foodBttn.setText("Platillos");
		buttonPnl.add(foodBttn);
		
		//ir a platillos
		foodBttn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				frame.dispose(); //destruir ventana actual y llevar al usuario a la ventana de platillos
				dc = new DishController("Platillos", frame.getWidth(), frame.getHeight()); //crear controlador de platillos y asignar parámetros a la ventana
				dc.dishes(); //llamar al método que crea y muestra la ventana de platillos
			}
		});
		
		//efecto hover del botón platillos
		foodBttn.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent evt) {
				foodBttn.setBackground(Color.decode("#3C7E3A"));
		    }

		    public void mouseExited(MouseEvent evt) {
		    	foodBttn.setBackground(Color.decode("#244E23"));
		    }
		});
		
		
		//botón de órdenes
		image = new ImageIcon(OrderView.class.getResource("/images/order.png")).getImage().getScaledInstance(30, 35, Image.SCALE_SMOOTH);
		imageIcon = new ImageIcon(image);
		orderBttn = new RoundButton(30, imageIcon); //botón
		orderBttn.setBackground(Color.decode("#3C7E3A"));
		orderBttn.setFont(new Font("Caladea Bold", Font.BOLD, 28)); 
		orderBttn.setForeground(Color.white);
		orderBttn.setText("Órdenes");
		buttonPnl.add(orderBttn);
		
		
		//botón de clientes
		image = new ImageIcon(OrderView.class.getResource("/images/client.png")).getImage().getScaledInstance(30, 35, Image.SCALE_SMOOTH);
		imageIcon = new ImageIcon(image);
		clientBttn = new RoundButton(30, imageIcon); //botón
		clientBttn.setBackground(Color.decode("#244E23"));
		clientBttn.setFont(new Font("Caladea Bold", Font.BOLD, 28)); 
		clientBttn.setForeground(Color.white);
		clientBttn.setText("Clientes");
		buttonPnl.add(clientBttn);
		
		//ir a clientes
		clientBttn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				frame.dispose(); //destruir ventana actual y llevar al usuario a la ventana de clientes
				cc = new ClientController("Clientes", frame.getWidth(), frame.getHeight()); //crear controlador de clientes y asignar parámetros a la ventana
				cc.clients(); //llamar al método que crea y muestra la ventana de clientes
			}
		});
		
		//efecto hover del botón clientes
		clientBttn.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent evt) {
				clientBttn.setBackground(Color.decode("#3C7E3A"));
		    }

		    public void mouseExited(MouseEvent evt) {
		    	clientBttn.setBackground(Color.decode("#244E23"));
		    }
		});
		
		
		//botón de inventario
		image = new ImageIcon(OrderView.class.getResource("/images/inventory.png")).getImage().getScaledInstance(30, 28, Image.SCALE_SMOOTH);
		imageIcon = new ImageIcon(image);
		inventoryBttn = new RoundButton(30, imageIcon); //botón
		inventoryBttn.setBackground(Color.decode("#244E23"));
		inventoryBttn.setFont(new Font("Caladea Bold", Font.BOLD, 28)); 
		inventoryBttn.setForeground(Color.white);
		inventoryBttn.setText("Inventario");
		buttonPnl.add(inventoryBttn);
		
		//ir a inventario
		inventoryBttn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				frame.dispose(); //destruir ventana actual y llevar al usuario a la ventana de inventario
				ic = new InventoryController("Inventario", frame.getWidth(), frame.getHeight()); //crear controlador de inventario y asignar parámetros a la ventana
				ic.inventory(); //llamar al método que crea y muestra la ventana de inventario
			}
		});
		
		//efecto hover del botón inventario
		inventoryBttn.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent evt) {
				inventoryBttn.setBackground(Color.decode("#3C7E3A"));
		    }

		    public void mouseExited(MouseEvent evt) {
		    	inventoryBttn.setBackground(Color.decode("#244E23"));
		    }
		});
		
		
		//botón de salir
		image = new ImageIcon(OrderView.class.getResource("/images/return.png")).getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
		imageIcon = new ImageIcon(image);
		returnBttn = new RoundButton(30, imageIcon); //botón
		returnBttn.setBackground(Color.decode("#EF2D2D"));
		returnBttn.setFont(new Font("Caladea Bold", Font.BOLD, 28)); 
		returnBttn.setForeground(Color.white);
		returnBttn.setText("Salir");
		buttonPnl.add(returnBttn);
		
		//volver a la ventana anterior de acorde a la ventana actual
		returnBttn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				switch(currentWindow) { 
					case "orders":
						frame.dispose(); //destruir ventana actual y llevar al usuario a la ventana de login
						ac = new AuthController("Login", frame.getWidth(), frame.getHeight()); //crear controlador de login y asignar parámetros a la ventana
						ac.login(); //llamar al método que crea y muestra la ventana de login
					break;
					
					case "newOrder":
						frame.remove(mainPnl);
						orders();
					break;
				}
				
				frame.repaint();
				frame.revalidate();
			}
		});
		
		//efecto hover del botón volver
		returnBttn.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent evt) {
				if(currentWindow.equals("orders")) {
					returnBttn.setBackground(Color.decode("#ED5C5C"));
				}else {
					returnBttn.setBackground(Color.decode("#3C7E3A"));
				}
		    }

		    public void mouseExited(MouseEvent evt) {
		    	if(currentWindow.equals("orders")) {
					returnBttn.setBackground(Color.decode("#EF2D2D"));
				}else {
					returnBttn.setBackground(Color.decode("#244E23"));
				}
		    }
		});
	}
	
	public void orders() {
		currentWindow = "orders"; //indicar la ventana actual en la que se encuentra el usuario
		returnBttn.setBackground(Color.decode("#EF2D2D")); //cambiar aspecto del botón
		returnBttn.setText("Salir");
		
		/**PANEL PRINCIPAL/CENTRAL*/
		mainPnl = new JPanel();
		mainPnl.setBackground(Color.white);
		mainPnl.setBorder(BorderFactory.createEmptyBorder(30, 45, 30, 45)); 
		mainPnl.setLayout(new BorderLayout());
		frame.add(mainPnl, BorderLayout.CENTER);
		
		/**CABECERA*/
		JPanel headerPnl = new JPanel();
		headerPnl.setLayout(new GridLayout(2, 1, 0, 15));
		headerPnl.setOpaque(false); //tiene fondo o no
		mainPnl.add(headerPnl, BorderLayout.NORTH);
		
		//título que alude a la ventana actual
		JLabel ordersLbl = new JLabel("Ordenes");
		ordersLbl.setFont(new Font("Caladea Bold", Font.BOLD, 36));
		ordersLbl.setForeground(Color.decode("#244E23")); //color de letra
		ordersLbl.setHorizontalAlignment(JLabel.LEFT); //alinear etiqueta a la izquierda
		ordersLbl.setHorizontalAlignment(SwingConstants.LEFT); //centrar texto de la etiqueta 
		headerPnl.add(ordersLbl);
		
		//panel de barra de búsqueda y botones
		JPanel actionPnl = new JPanel();
		actionPnl.setLayout(new FlowLayout(FlowLayout.TRAILING, 30, 0));
		actionPnl.setOpaque(false); //tiene fondo o no
		headerPnl.add(actionPnl);
		
		//barra de búsqueda
		RoundPanel searchBarPnl = new RoundPanel(30);  
		searchBarPnl.setBackground(Color.white);
		searchBarPnl.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5)); //borde invisible para centrar elementos que el panel contenga 
		searchBarPnl.setForeground(Color.decode("#244E23")); //color del borde
		searchBarPnl.setLayout(new BorderLayout(15, 0));
		searchBarPnl.setPreferredSize(new Dimension(300, 30));
		actionPnl.add(searchBarPnl);
		
		//imagen de lupa 
		image = new ImageIcon(OrderView.class.getResource("/images/magnifyingGlass.png")).getImage().getScaledInstance(15, 15, Image.SCALE_SMOOTH);
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
		
		//efecto hover de botón de barra de búsqueda
		searchBttn.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent evt) {
				searchBttn.setBackground(Color.decode("#3C7E3A"));
		    }

		    public void mouseExited(MouseEvent evt) {
		    	searchBttn.setBackground(Color.decode("#244E23"));
		    }
		});
				
		
		//botón de añadir nueva órden
		RoundButton newOrderBttn = new RoundButton(30);
		newOrderBttn.setBackground(Color.white);
		newOrderBttn.setFont(new Font("Caladea Bold", Font.BOLD, 20));
		newOrderBttn.setForeground(Color.decode("#244E23"));
		newOrderBttn.setText("Nuevo");
		actionPnl.add(newOrderBttn); 
		
		//botón de ordenes existentes
		RoundButton ongoingOrderBttn = new RoundButton(30);
		ongoingOrderBttn.setBackground(Color.decode("#3C7E3A"));
		ongoingOrderBttn.setFont(new Font("Caladea Bold", Font.BOLD, 20));
		ongoingOrderBttn.setForeground(Color.white);
		ongoingOrderBttn.setText("En curso");
		actionPnl.add(ongoingOrderBttn); 
		
		//ir a nueva órden
		newOrderBttn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				frame.remove(mainPnl);
				newOrder();
				frame.repaint();
				frame.revalidate();
				
				newOrderBttn.setBackground(Color.decode("#3C7E3A"));	
				newOrderBttn.setForeground(Color.white);
				ongoingOrderBttn.setBackground(Color.white);
				ongoingOrderBttn.setForeground(Color.decode("#244E23"));
			}
		});
		
		//efecto hover de botón de añadir nueva órden
		newOrderBttn.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent evt) {
				if(newOrderBttn.getBackground().toString().equals("#3C7E3A")){
					newOrderBttn.setBackground(Color.decode("#4DA14B"));
				}else {
					newOrderBttn.setForeground(Color.decode("#3C7E3A"));
				}
		    }

		    public void mouseExited(MouseEvent evt) {
		    	if(newOrderBttn.getBackground().toString().equals("#4DA14B")){
		    		newOrderBttn.setBackground(Color.decode("#3C7E3A"));
				}else {
					newOrderBttn.setForeground(Color.decode("#244E23"));
				}
		    }
		});
		
		/**PANEL DONDE SE MUESTRAN LAS ORDENES EXISTENTES*/
		JPanel ordersPnl = new JPanel();
		ordersPnl.setBorder(BorderFactory.createEmptyBorder(40, 0, 40, 0)); //borde invisible para centrar elementos que el panel contenga 
		ordersPnl.setLayout(new FlowLayout(FlowLayout.LEFT, 30, 30));	
		ordersPnl.setOpaque(false); //tiene fondo o no
		mainPnl.add(ordersPnl, BorderLayout.CENTER);
		
		//tarjeta del platillo
		OrderCard orderCard = new OrderCard(30, "Mesa 1", "$1200.00 MXN", "Luis", 1, 25+"min");
		RoundPanel order = orderCard.createCard();
		ordersPnl.add(order);
		
		frame.setVisible(true);
	}
	
	
	public void newOrder() {
		currentWindow = "newOrder"; //indicar la ventana actual en la que se encuentra el usuario
		returnBttn.setBackground(Color.decode("#244E23")); //cambiar aspecto del botón
		returnBttn.setText("Volver");
		
		/**PANEL PRINCIPAL/CENTRAL*/
		mainPnl = new JPanel();
		mainPnl.setBackground(Color.white);
		mainPnl.setBorder(BorderFactory.createEmptyBorder(30, 45, 30, 45)); 
		mainPnl.setLayout(new BorderLayout());
		frame.add(mainPnl, BorderLayout.CENTER);
		
		/**CABECERA*/
		JPanel headerPnl = new JPanel();
		headerPnl.setLayout(new GridLayout(2, 1, 0, 15));
		headerPnl.setOpaque(false); //tiene fondo o no
		mainPnl.add(headerPnl, BorderLayout.NORTH);
		
		//título que alude a la ventana actual
		JLabel ordersLbl = new JLabel("Ordenes");
		ordersLbl.setFont(new Font("Caladea Bold", Font.BOLD, 36));
		ordersLbl.setForeground(Color.decode("#244E23")); //color de letra
		ordersLbl.setHorizontalAlignment(JLabel.LEFT); //alinear etiqueta a la izquierda
		ordersLbl.setHorizontalAlignment(SwingConstants.LEFT); //centrar texto de la etiqueta 
		headerPnl.add(ordersLbl);
		
		//panel de barra de búsqueda y botones
		JPanel actionPnl = new JPanel();
		actionPnl.setLayout(new FlowLayout(FlowLayout.TRAILING, 30, 0));
		actionPnl.setOpaque(false); //tiene fondo o no
		headerPnl.add(actionPnl);
		
		//barra de búsqueda
		RoundPanel searchBarPnl = new RoundPanel(30);  
		searchBarPnl.setBackground(Color.white);
		searchBarPnl.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5)); //borde invisible para centrar elementos que el panel contenga 
		searchBarPnl.setForeground(Color.decode("#244E23")); //color del borde
		searchBarPnl.setLayout(new BorderLayout(15, 0));
		searchBarPnl.setPreferredSize(new Dimension(300, 30));
		actionPnl.add(searchBarPnl);
		
		//imagen de lupa 
		image = new ImageIcon(OrderView.class.getResource("/images/magnifyingGlass.png")).getImage().getScaledInstance(15, 15, Image.SCALE_SMOOTH);
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
		
		//efecto hover de botón de barra de búsqueda
		searchBttn.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent evt) {
				searchBttn.setBackground(Color.decode("#3C7E3A"));
		    }

		    public void mouseExited(MouseEvent evt) {
		    	searchBttn.setBackground(Color.decode("#244E23"));
		    }
		});
				
		
		//botón de añadir nueva órden
		RoundButton newOrderBttn = new RoundButton(30);
		newOrderBttn.setBackground(Color.decode("#3C7E3A"));
		newOrderBttn.setFont(new Font("Caladea Bold", Font.BOLD, 20));
		newOrderBttn.setForeground(Color.white);
		newOrderBttn.setText("Nuevo");
		actionPnl.add(newOrderBttn); 
		
		//botón de añadir nueva órden
		RoundButton ongoingOrderBttn = new RoundButton(30);
		ongoingOrderBttn.setBackground(Color.white);
		ongoingOrderBttn.setFont(new Font("Caladea Bold", Font.BOLD, 20));
		ongoingOrderBttn.setForeground(Color.decode("#244E23"));
		ongoingOrderBttn.setText("En curso");
		actionPnl.add(ongoingOrderBttn); 

		//ir a ordenes en curso
		ongoingOrderBttn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				frame.remove(mainPnl);
				orders();
				frame.repaint();
				frame.revalidate();
				
				ongoingOrderBttn.setBackground(Color.decode("#3C7E3A"));	
				ongoingOrderBttn.setForeground(Color.white);
				newOrderBttn.setBackground(Color.white);
				newOrderBttn.setForeground(Color.decode("#244E23"));
			}
		});
	
		
		//efecto hover de botón de añadir nueva órden según el estilo actual del botón 
		ongoingOrderBttn.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent evt) {
				if(ongoingOrderBttn.getBackground().toString().equals("#3C7E3A")){
					ongoingOrderBttn.setBackground(Color.decode("#4DA14B"));
				}else {
					ongoingOrderBttn.setForeground(Color.decode("#3C7E3A"));
				}
		    }

		    public void mouseExited(MouseEvent evt) {
		    	if(ongoingOrderBttn.getBackground().toString().equals("#4DA14B")){
		    		ongoingOrderBttn.setBackground(Color.decode("#3C7E3A"));
				}else {
					ongoingOrderBttn.setForeground(Color.decode("#244E23"));
				}
		    }
		});
		
		frame.setVisible(true);
	}
	
}