package views;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import controllers.AuthController;
import controllers.ClientController;
import controllers.DishController;
import controllers.OrderController;
import customClasses.RoundButton;
import customClasses.RoundPanel;

public class InventoryView {

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
	public OrderController oc;
	public ClientController cc;
	
	
	public InventoryView(String title, int frameWidth, int frameHeight) {
		frame = new JFrame(); //crear JFrame	
		imageIcon = new ImageIcon(InventoryView.class.getResource("/images/elManglarLogo.png")); //icono de la ventana
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
		image = new ImageIcon(InventoryView.class.getResource("/images/elManglarName.png")).getImage().getScaledInstance(200, 40, Image.SCALE_SMOOTH);
		imageIcon = new ImageIcon(image);
		restaurantNameLbl = new JLabel(imageIcon);
		buttonPnl.add(restaurantNameLbl);
		
		//botón de platillos
		image = new ImageIcon(InventoryView.class.getResource("/images/food.png")).getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
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
		image = new ImageIcon(InventoryView.class.getResource("/images/order.png")).getImage().getScaledInstance(30, 35, Image.SCALE_SMOOTH);
		imageIcon = new ImageIcon(image);
		orderBttn = new RoundButton(30, imageIcon); //botón
		orderBttn.setBackground(Color.decode("#244E23"));
		orderBttn.setFont(new Font("Caladea Bold", Font.BOLD, 28)); 
		orderBttn.setForeground(Color.white);
		orderBttn.setText("Órdenes");
		buttonPnl.add(orderBttn);
		
		//ir a ordenes
		orderBttn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				frame.dispose(); //destruir ventana actual y llevar al usuario a la ventana de ordenes
				oc = new OrderController("Ordenes", frame.getWidth(), frame.getHeight()); //crear controlador de ordenes y asignar parámetros a la ventana
				oc.orders(); //llamar al método que crea y muestra la ventana de ordenes
			}
			
		});
		
		//efecto hover del botón órdenes
		orderBttn.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent evt) {
				orderBttn.setBackground(Color.decode("#3C7E3A"));
		    }

		    public void mouseExited(MouseEvent evt) {
		    	orderBttn.setBackground(Color.decode("#244E23"));
		    }
		});
		

		//botón de clientes
		image = new ImageIcon(InventoryView.class.getResource("/images/client.png")).getImage().getScaledInstance(30, 35, Image.SCALE_SMOOTH);
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
		image = new ImageIcon(InventoryView.class.getResource("/images/inventory.png")).getImage().getScaledInstance(30, 28, Image.SCALE_SMOOTH);
		imageIcon = new ImageIcon(image);
		inventoryBttn = new RoundButton(30, imageIcon); //botón
		inventoryBttn.setBackground(Color.decode("#3C7E3A"));
		inventoryBttn.setFont(new Font("Caladea Bold", Font.BOLD, 28)); 
		inventoryBttn.setForeground(Color.white);
		inventoryBttn.setText("Inventario");
		buttonPnl.add(inventoryBttn);
		
		
		image = new ImageIcon(InventoryView.class.getResource("/images/return.png")).getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
		imageIcon = new ImageIcon(image);
		returnBttn = new RoundButton(30, imageIcon); //botón
		returnBttn.setBackground(Color.decode("#EF2D2D"));
		returnBttn.setFont(new Font("Caladea Bold", Font.BOLD, 28)); 
		returnBttn.setForeground(Color.white);
		returnBttn.setText("Salir");
		buttonPnl.add(returnBttn);
		
		returnBttn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				switch(currentWindow) { 
					case "inventory":
						frame.dispose(); //destruir ventana actual y llevar al usuario a la ventana de login
						ac = new AuthController("Login", frame.getWidth(), frame.getHeight()); //crear controlador de login y asignar parámetros a la ventana
						ac.login(); //llamar al método que crea y muestra la ventana de login
					break;
					
					case "newInventory":
						frame.remove(mainPnl);
						inventory();
					break;
					
					case "editInventory":
						frame.remove(mainPnl);
						inventory();
					break;
					
				}
				
				frame.repaint();
				frame.revalidate();
			}	
		});
		
		returnBttn.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent evt) {
				if(currentWindow.equals("inventory")) {
					returnBttn.setBackground(Color.decode("#ED5C5C"));
				}else {
					returnBttn.setBackground(Color.decode("#3C7E3A"));
				}
		    }

		    public void mouseExited(MouseEvent evt) {
		    	if(currentWindow.equals("inventory")) {
					returnBttn.setBackground(Color.decode("#EF2D2D"));
				}else {
					returnBttn.setBackground(Color.decode("#244E23"));
				}
		    }
		});
	}
	
	
	public void inventory() {
		currentWindow = "inventory"; 
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
		
		JLabel inventoryLbl = new JLabel("Inventario");
		inventoryLbl.setFont(new Font("Caladea Bold", Font.BOLD, 36));
		inventoryLbl.setForeground(Color.decode("#244E23")); 
		inventoryLbl.setHorizontalAlignment(JLabel.LEFT); 
		inventoryLbl.setHorizontalAlignment(SwingConstants.LEFT); 
		headerPnl.add(inventoryLbl);
		
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
		
		image = new ImageIcon(InventoryView.class.getResource("/images/magnifyingGlass.png")).getImage().getScaledInstance(15, 15, Image.SCALE_SMOOTH);
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
				
		RoundButton newInv = new RoundButton(30);
		newInv.setBackground(Color.decode("#2EA623"));
		newInv.setFont(new Font("Caladea Bold", Font.BOLD, 20));
		newInv.setForeground(Color.white);
		newInv.setText("Nuevo");
		actionPnl.add(newInv); 

		newInv.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				frame.remove(mainPnl);
				newInventory();
				frame.repaint();
				frame.revalidate();
			}
		});
		
		newInv.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent evt) {
				newInv.setBackground(Color.decode("#39C82C"));
		    }

		    public void mouseExited(MouseEvent evt) {
		    	newInv.setBackground(Color.decode("#2EA623"));
		    }
		});
		
		JPanel inventoryPnl = new JPanel();
		inventoryPnl.setBorder(BorderFactory.createEmptyBorder(40, 0, 40, 0)); 
		inventoryPnl.setLayout(new FlowLayout());	
		inventoryPnl.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		inventoryPnl.setOpaque(false); 
		mainPnl.add(inventoryPnl, BorderLayout.CENTER);
		
		RoundButton editBttn = new RoundButton(30);
		editBttn.setBackground(Color.decode("#3C7E3A"));
		editBttn.setFont(new Font("Caladea Bold", Font.BOLD, 20));
		editBttn.setForeground(Color.white);
		editBttn.setText("Editar");
		inventoryPnl.add(editBttn); 
		
		editBttn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				frame.remove(mainPnl);
				editInventory();
				frame.repaint();
				frame.revalidate();
			}
		});
	
		frame.setVisible(true);
	}
	
	
	public void newInventory() {
		currentWindow = "newInventory"; 
		returnBttn.setBackground(Color.decode("#244E23")); 
		returnBttn.setText("Volver");
		
		mainPnl = new JPanel();
		mainPnl.setBackground(Color.white);
		mainPnl.setBorder(BorderFactory.createEmptyBorder(30, 45, 30, 45)); 
		mainPnl.setLayout(new BorderLayout());
		frame.add(mainPnl, BorderLayout.CENTER);
		
		JPanel headerPnl = new JPanel();
		headerPnl.setLayout(new FlowLayout(FlowLayout.TRAILING, 30, 0));
		headerPnl.setOpaque(false); 
		mainPnl.add(headerPnl, BorderLayout.NORTH);
		
		JLabel inventoryLbl = new JLabel("Añadir inventario");
		inventoryLbl.setFont(new Font("Caladea Bold", Font.BOLD, 36));
		inventoryLbl.setForeground(Color.decode("#244E23")); 
		inventoryLbl.setHorizontalAlignment(JLabel.LEFT); 
		inventoryLbl.setHorizontalAlignment(SwingConstants.LEFT); 
		headerPnl.add(inventoryLbl);
				
		RoundButton cancelBttn = new RoundButton(30);
		cancelBttn.setBackground(Color.decode("#EF2D2D"));
		cancelBttn.setFont(new Font("Caladea Bold", Font.BOLD, 20));
		cancelBttn.setForeground(Color.white);
		cancelBttn.setText("Cancelar");
		headerPnl.add(cancelBttn);
		
		cancelBttn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				frame.remove(mainPnl);
				inventory();
				frame.repaint();
				frame.revalidate();
			}
		});
		
		cancelBttn.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent evt) {
				cancelBttn.setBackground(Color.decode("#ED5C5C"));
		    }

		    public void mouseExited(MouseEvent evt) {
		    	cancelBttn.setBackground(Color.decode("#EF2D2D"));
		    }
		});
				
		
		RoundButton newInv = new RoundButton(30);
		newInv.setBackground(Color.decode("#2EA623"));
		newInv.setFont(new Font("Caladea Bold", Font.BOLD, 20));
		newInv.setForeground(Color.white);
		newInv.setText("Añadir");
		headerPnl.add(newInv); 

		newInv.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				frame.remove(mainPnl);
				inventory();
				frame.repaint();
				frame.revalidate();
			}
		});
		
		newInv.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent evt) {
				newInv.setBackground(Color.decode("#39C82C"));
		    }

		    public void mouseExited(MouseEvent evt) {
		    	newInv.setBackground(Color.decode("#2EA623"));
		    }
		});
	
		frame.setVisible(true);
	}
	
	
	public void editInventory() {
		currentWindow = "editInventory"; 
		returnBttn.setBackground(Color.decode("#244E23")); 
		returnBttn.setText("Volver");
		
		mainPnl = new JPanel();
		mainPnl.setBackground(Color.white);
		mainPnl.setBorder(BorderFactory.createEmptyBorder(30, 45, 30, 45)); 
		mainPnl.setLayout(new BorderLayout());
		frame.add(mainPnl, BorderLayout.CENTER);
		
		JPanel headerPnl = new JPanel();
		headerPnl.setLayout(new FlowLayout(FlowLayout.TRAILING, 30, 0));
		headerPnl.setOpaque(false); 
		mainPnl.add(headerPnl, BorderLayout.NORTH);
		
		JLabel inventoryLbl = new JLabel("Editar inventario");
		inventoryLbl.setFont(new Font("Caladea Bold", Font.BOLD, 36));
		inventoryLbl.setForeground(Color.decode("#244E23")); 
		inventoryLbl.setHorizontalAlignment(JLabel.LEFT); 
		inventoryLbl.setHorizontalAlignment(SwingConstants.LEFT); 
		headerPnl.add(inventoryLbl);
				
		RoundButton cancelBttn = new RoundButton(30);
		cancelBttn.setBackground(Color.decode("#EF2D2D"));
		cancelBttn.setFont(new Font("Caladea Bold", Font.BOLD, 20));
		cancelBttn.setForeground(Color.white);
		cancelBttn.setText("Cancelar");
		headerPnl.add(cancelBttn);
		
		cancelBttn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				frame.remove(mainPnl);
				inventory();
				frame.repaint();
				frame.revalidate();
			}
		});
		
		cancelBttn.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent evt) {
				cancelBttn.setBackground(Color.decode("#ED5C5C"));
		    }

		    public void mouseExited(MouseEvent evt) {
		    	cancelBttn.setBackground(Color.decode("#EF2D2D"));
		    }
		});
				
		
		RoundButton saveBttn = new RoundButton(30);
		saveBttn.setBackground(Color.decode("#555BF6"));
		saveBttn.setFont(new Font("Caladea Bold", Font.BOLD, 20));
		saveBttn.setForeground(Color.white);
		saveBttn.setText("Guardar");
		headerPnl.add(saveBttn); 

		saveBttn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				frame.remove(mainPnl);
				inventory();
				frame.repaint();
				frame.revalidate();
			}
		});
		
		saveBttn.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent evt) {
				saveBttn.setBackground(Color.decode("#767AEC"));
		    }

		    public void mouseExited(MouseEvent evt) {
		    	saveBttn.setBackground(Color.decode("#555BF6"));
		    }
		});
	
		frame.setVisible(true);
	}
	
}
