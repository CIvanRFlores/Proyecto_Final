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
		frame = new JFrame(); 
		imageIcon = new ImageIcon(OrderView.class.getResource("/images/elManglarLogo.png")); //icono de la ventana
		frame.setIconImage(imageIcon.getImage());
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
		frame.setSize(frameWidth, frameHeight); 
		frame.setLocationRelativeTo(null); //colocar la ventana en el centro de la pantalla
		frame.setResizable(true); 
		frame.setMinimumSize(new Dimension(350, 350));
		frame.setTitle(title);
		
		buttonPnl = new JPanel();
		buttonPnl.setBackground(Color.decode("#DEFFDB"));
		buttonPnl.setLayout(new GridLayout(6, 1, 0, 10));
		frame.add(buttonPnl, BorderLayout.WEST);
		
		image = new ImageIcon(OrderView.class.getResource("/images/elManglarName.png")).getImage().getScaledInstance(200, 40, Image.SCALE_SMOOTH);
		imageIcon = new ImageIcon(image);
		restaurantNameLbl = new JLabel(imageIcon);
		buttonPnl.add(restaurantNameLbl);
		
		image = new ImageIcon(OrderView.class.getResource("/images/food.png")).getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
		imageIcon = new ImageIcon(image);
		foodBttn = new RoundButton(30, imageIcon);
		foodBttn.setBackground(Color.decode("#244E23"));
		foodBttn.setFont(new Font("Caladea Bold", Font.BOLD, 28)); 
		foodBttn.setForeground(Color.white);
		foodBttn.setText("Platillos");
		buttonPnl.add(foodBttn);
		
		foodBttn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				frame.dispose(); //destruir ventana actual y llevar al usuario a la ventana de platillos
				dc = new DishController("Platillos", frame.getWidth(), frame.getHeight()); //crear controlador de platillos y asignar parámetros a la ventana
				dc.dishes(); //llamar al método que crea y muestra la ventana de platillos
			}
		});
		
		foodBttn.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent evt) {
				foodBttn.setBackground(Color.decode("#3C7E3A"));
		    }

		    public void mouseExited(MouseEvent evt) {
		    	foodBttn.setBackground(Color.decode("#244E23"));
		    }
		});
		
		
		image = new ImageIcon(OrderView.class.getResource("/images/order.png")).getImage().getScaledInstance(30, 35, Image.SCALE_SMOOTH);
		imageIcon = new ImageIcon(image);
		orderBttn = new RoundButton(30, imageIcon);
		orderBttn.setBackground(Color.decode("#3C7E3A"));
		orderBttn.setFont(new Font("Caladea Bold", Font.BOLD, 28)); 
		orderBttn.setForeground(Color.white);
		orderBttn.setText("Órdenes");
		buttonPnl.add(orderBttn);
		
		
		image = new ImageIcon(OrderView.class.getResource("/images/client.png")).getImage().getScaledInstance(30, 35, Image.SCALE_SMOOTH);
		imageIcon = new ImageIcon(image);
		clientBttn = new RoundButton(30, imageIcon); //botón
		clientBttn.setBackground(Color.decode("#244E23"));
		clientBttn.setFont(new Font("Caladea Bold", Font.BOLD, 28)); 
		clientBttn.setForeground(Color.white);
		clientBttn.setText("Clientes");
		buttonPnl.add(clientBttn);
		
		clientBttn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				frame.dispose(); //destruir ventana actual y llevar al usuario a la ventana de clientes
				cc = new ClientController("Clientes", frame.getWidth(), frame.getHeight()); //crear controlador de clientes y asignar parámetros a la ventana
				cc.clients(); //llamar al método que crea y muestra la ventana de clientes
			}
		});
		
		clientBttn.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent evt) {
				clientBttn.setBackground(Color.decode("#3C7E3A"));
		    }

		    public void mouseExited(MouseEvent evt) {
		    	clientBttn.setBackground(Color.decode("#244E23"));
		    }
		});
		
		
		image = new ImageIcon(OrderView.class.getResource("/images/inventory.png")).getImage().getScaledInstance(30, 28, Image.SCALE_SMOOTH);
		imageIcon = new ImageIcon(image);
		inventoryBttn = new RoundButton(30, imageIcon); 
		inventoryBttn.setBackground(Color.decode("#244E23"));
		inventoryBttn.setFont(new Font("Caladea Bold", Font.BOLD, 28)); 
		inventoryBttn.setForeground(Color.white);
		inventoryBttn.setText("Inventario");
		buttonPnl.add(inventoryBttn);
		
		inventoryBttn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				frame.dispose(); //destruir ventana actual y llevar al usuario a la ventana de inventario
				ic = new InventoryController("Inventario", frame.getWidth(), frame.getHeight()); //crear controlador de inventario y asignar parámetros a la ventana
				ic.inventory(); //llamar al método que crea y muestra la ventana de inventario
			}
		});
		
		inventoryBttn.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent evt) {
				inventoryBttn.setBackground(Color.decode("#3C7E3A"));
		    }

		    public void mouseExited(MouseEvent evt) {
		    	inventoryBttn.setBackground(Color.decode("#244E23"));
		    }
		});
		
		
		image = new ImageIcon(OrderView.class.getResource("/images/return.png")).getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
		imageIcon = new ImageIcon(image);
		returnBttn = new RoundButton(30, imageIcon); 
		returnBttn.setBackground(Color.decode("#EF2D2D"));
		returnBttn.setFont(new Font("Caladea Bold", Font.BOLD, 28)); 
		returnBttn.setForeground(Color.white);
		returnBttn.setText("Salir");
		buttonPnl.add(returnBttn);
		
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
		currentWindow = "orders";
		returnBttn.setBackground(Color.decode("#EF2D2D")); 
		returnBttn.setText("Salir");
		
		mainPnl = new JPanel();
		mainPnl.setBackground(Color.white);
		mainPnl.setBorder(BorderFactory.createEmptyBorder(30, 45, 30, 45)); 
		mainPnl.setLayout(new BorderLayout());
		frame.add(mainPnl, BorderLayout.CENTER);
		
		JPanel headerPnl = new JPanel();
		headerPnl.setLayout(new GridLayout(2, 1, 0, 15));
		headerPnl.setOpaque(false); 
		mainPnl.add(headerPnl, BorderLayout.NORTH);
		
		JLabel ordersLbl = new JLabel("Ordenes");
		ordersLbl.setFont(new Font("Caladea Bold", Font.BOLD, 36));
		ordersLbl.setForeground(Color.decode("#244E23")); 
		ordersLbl.setHorizontalAlignment(JLabel.LEFT); 
		ordersLbl.setHorizontalAlignment(SwingConstants.LEFT); 
		headerPnl.add(ordersLbl);
		
		JPanel actionPnl = new JPanel();
		actionPnl.setLayout(new FlowLayout(FlowLayout.TRAILING, 30, 0));
		actionPnl.setOpaque(false); 
		headerPnl.add(actionPnl);
		
		RoundPanel searchBarPnl = new RoundPanel(30);  
		searchBarPnl.setBackground(Color.white);
		searchBarPnl.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));  
		searchBarPnl.setForeground(Color.decode("#244E23")); 
		searchBarPnl.setLayout(new BorderLayout(15, 0));
		searchBarPnl.setPreferredSize(new Dimension(300, 30));
		actionPnl.add(searchBarPnl);
		
		image = new ImageIcon(OrderView.class.getResource("/images/magnifyingGlass.png")).getImage().getScaledInstance(15, 15, Image.SCALE_SMOOTH);
		imageIcon = new ImageIcon(image);
		JLabel logoTextLbl = new JLabel(imageIcon);
		searchBarPnl.add(logoTextLbl, BorderLayout.WEST);
		
		JTextField searchTxtFld = new JTextField();
		searchTxtFld.setBorder(null);
		searchTxtFld.setFont(new Font("Caladea Bold", Font.BOLD, 14)); 
		searchTxtFld.setForeground(Color.decode("#999999"));
		searchTxtFld.setOpaque(false);
		searchBarPnl.add(searchTxtFld,  BorderLayout.CENTER);
				
		RoundButton searchBttn = new RoundButton(30);
		searchBttn.setBackground(Color.decode("#244E23"));
		searchBttn.setFont(new Font("Caladea Bold", Font.BOLD, 20));
		searchBttn.setForeground(Color.white);
		searchBttn.setText("Buscar");
		actionPnl.add(searchBttn);
		
		searchBttn.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent evt) {
				searchBttn.setBackground(Color.decode("#3C7E3A"));
		    }

		    public void mouseExited(MouseEvent evt) {
		    	searchBttn.setBackground(Color.decode("#244E23"));
		    }
		});
				
		
		RoundButton newOrderBttn = new RoundButton(30);
		newOrderBttn.setBackground(Color.white);
		newOrderBttn.setFont(new Font("Caladea Bold", Font.BOLD, 20));
		newOrderBttn.setForeground(Color.decode("#244E23"));
		newOrderBttn.setText("Nuevo");
		actionPnl.add(newOrderBttn); 
		
		RoundButton ongoingOrderBttn = new RoundButton(30);
		ongoingOrderBttn.setBackground(Color.decode("#3C7E3A"));
		ongoingOrderBttn.setFont(new Font("Caladea Bold", Font.BOLD, 20));
		ongoingOrderBttn.setForeground(Color.white);
		ongoingOrderBttn.setText("En curso");
		actionPnl.add(ongoingOrderBttn); 
		
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
		
		JPanel ordersPnl = new JPanel();
		ordersPnl.setBorder(BorderFactory.createEmptyBorder(40, 0, 40, 0));  
		ordersPnl.setLayout(new FlowLayout(FlowLayout.LEFT, 30, 30));	
		ordersPnl.setOpaque(false); 
		mainPnl.add(ordersPnl, BorderLayout.CENTER);
		
		OrderCard orderCard = new OrderCard(30, "Mesa 1", "$1200.00 MXN", "Luis", 1, 25+"min");
		RoundPanel order = orderCard.createCard();
		ordersPnl.add(order);
		
		frame.setVisible(true);
	}
	
	
	public void newOrder() {
		currentWindow = "newOrder"; 
		returnBttn.setBackground(Color.decode("#244E23")); 
		returnBttn.setText("Volver");
		
		mainPnl = new JPanel();
		mainPnl.setBackground(Color.white);
		mainPnl.setBorder(BorderFactory.createEmptyBorder(30, 45, 30, 45)); 
		mainPnl.setLayout(new BorderLayout());
		frame.add(mainPnl, BorderLayout.CENTER);
		
		JPanel headerPnl = new JPanel();
		headerPnl.setLayout(new GridLayout(2, 1, 0, 15));
		headerPnl.setOpaque(false); 
		mainPnl.add(headerPnl, BorderLayout.NORTH);
		
		JLabel ordersLbl = new JLabel("Ordenes");
		ordersLbl.setFont(new Font("Caladea Bold", Font.BOLD, 36));
		ordersLbl.setForeground(Color.decode("#244E23"));
		ordersLbl.setHorizontalAlignment(JLabel.LEFT); 
		ordersLbl.setHorizontalAlignment(SwingConstants.LEFT); 
		headerPnl.add(ordersLbl);
		
		JPanel actionPnl = new JPanel();
		actionPnl.setLayout(new FlowLayout(FlowLayout.TRAILING, 30, 0));
		actionPnl.setOpaque(false);
		headerPnl.add(actionPnl);
		
		RoundPanel searchBarPnl = new RoundPanel(30);  
		searchBarPnl.setBackground(Color.white);
		searchBarPnl.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));  
		searchBarPnl.setForeground(Color.decode("#244E23")); 
		searchBarPnl.setLayout(new BorderLayout(15, 0));
		searchBarPnl.setPreferredSize(new Dimension(300, 30));
		actionPnl.add(searchBarPnl);
		
		image = new ImageIcon(OrderView.class.getResource("/images/magnifyingGlass.png")).getImage().getScaledInstance(15, 15, Image.SCALE_SMOOTH);
		imageIcon = new ImageIcon(image);
		JLabel logoTextLbl = new JLabel(imageIcon);
		searchBarPnl.add(logoTextLbl, BorderLayout.WEST);
		
		JTextField searchTxtFld = new JTextField();
		searchTxtFld.setBorder(null);
		searchTxtFld.setFont(new Font("Caladea Bold", Font.BOLD, 14)); 
		searchTxtFld.setForeground(Color.decode("#999999")); 
		searchTxtFld.setOpaque(false); 
		searchBarPnl.add(searchTxtFld,  BorderLayout.CENTER);
				
		RoundButton searchBttn = new RoundButton(30);
		searchBttn.setBackground(Color.decode("#244E23"));
		searchBttn.setFont(new Font("Caladea Bold", Font.BOLD, 20));
		searchBttn.setForeground(Color.white);
		searchBttn.setText("Buscar");
		actionPnl.add(searchBttn);
		
		searchBttn.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent evt) {
				searchBttn.setBackground(Color.decode("#3C7E3A"));
		    }

		    public void mouseExited(MouseEvent evt) {
		    	searchBttn.setBackground(Color.decode("#244E23"));
		    }
		});
				
		
		RoundButton newOrderBttn = new RoundButton(30);
		newOrderBttn.setBackground(Color.decode("#3C7E3A"));
		newOrderBttn.setFont(new Font("Caladea Bold", Font.BOLD, 20));
		newOrderBttn.setForeground(Color.white);
		newOrderBttn.setText("Nuevo");
		actionPnl.add(newOrderBttn); 
		
		RoundButton ongoingOrderBttn = new RoundButton(30);
		ongoingOrderBttn.setBackground(Color.white);
		ongoingOrderBttn.setFont(new Font("Caladea Bold", Font.BOLD, 20));
		ongoingOrderBttn.setForeground(Color.decode("#244E23"));
		ongoingOrderBttn.setText("En curso");
		actionPnl.add(ongoingOrderBttn); 

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