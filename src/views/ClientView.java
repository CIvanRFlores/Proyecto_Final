package views;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import controllers.AuthController;
import controllers.DishController;
import controllers.OrderController;
import controllers.InventoryController;
import customClasses.RoundButton;
import customClasses.RoundPanel;

public class ClientView {

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
	public InventoryController ic;
	
	
	public ClientView(String title, int frameWidth, int frameHeight) {
		frame = new JFrame(); //crear JFrame	
		imageIcon = new ImageIcon("src/images/elManglarLogo.png"); //icono de la ventana
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
		image = new ImageIcon("src/images/elManglarName.png").getImage().getScaledInstance(200, 40, Image.SCALE_SMOOTH);
		imageIcon = new ImageIcon(image);
		restaurantNameLbl = new JLabel(imageIcon);
		buttonPnl.add(restaurantNameLbl);
		
		//botón de platillos
		image = new ImageIcon("src/images/food.png").getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
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
		image = new ImageIcon("src/images/order.png").getImage().getScaledInstance(30, 35, Image.SCALE_SMOOTH);
		imageIcon = new ImageIcon(image);
		orderBttn = new RoundButton(30, imageIcon); //botón
		orderBttn.setBackground(Color.decode("#3C7E3A"));
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
		image = new ImageIcon("src/images/client.png").getImage().getScaledInstance(30, 35, Image.SCALE_SMOOTH);
		imageIcon = new ImageIcon(image);
		clientBttn = new RoundButton(30, imageIcon); //botón
		clientBttn.setBackground(Color.decode("#3C7E3A"));
		clientBttn.setFont(new Font("Caladea Bold", Font.BOLD, 28)); 
		clientBttn.setForeground(Color.white);
		clientBttn.setText("Clientes");
		buttonPnl.add(clientBttn);
		
		
		//botón de inventario
		image = new ImageIcon("src/images/inventory.png").getImage().getScaledInstance(30, 28, Image.SCALE_SMOOTH);
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
		image = new ImageIcon("src/images/return.png").getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
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
					case "clients":
						frame.dispose(); //destruir ventana actual y llevar al usuario a la ventana de login
						ac = new AuthController("Login", frame.getWidth(), frame.getHeight()); //crear controlador de login y asignar parámetros a la ventana
						ac.login(); //llamar al método que crea y muestra la ventana de login
					break;
				
					case "newClient":
						frame.remove(mainPnl);
						clients();
					break;
					
					case "editClient":
						frame.remove(mainPnl);
						clients();
					break;
					
					case "clientHistory":
						frame.remove(mainPnl);
						clients();
					break;
				}
				
				frame.repaint();
				frame.revalidate();
			}
		});
		
		//efecto hover del botón volver
		returnBttn.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent evt) {
				if(currentWindow.equals("clients")) {
					returnBttn.setBackground(Color.decode("#ED5C5C"));
				}else {
					returnBttn.setBackground(Color.decode("#3C7E3A"));
				}
		    }

		    public void mouseExited(MouseEvent evt) {
		    	if(currentWindow.equals("clients")) {
					returnBttn.setBackground(Color.decode("#EF2D2D"));
				}else {
					returnBttn.setBackground(Color.decode("#244E23"));
				}
		    }
		});
	}
	
	public void clients() {
		currentWindow = "clients"; //indicar la ventana actual en la que se encuentra el usuario
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
		JLabel clientsLbl = new JLabel("Clientes");
		clientsLbl.setFont(new Font("Caladea Bold", Font.BOLD, 36));
		clientsLbl.setForeground(Color.decode("#244E23")); //color de letra
		clientsLbl.setHorizontalAlignment(JLabel.LEFT); //alinear etiqueta a la izquierda
		clientsLbl.setHorizontalAlignment(SwingConstants.LEFT); //centrar texto de la etiqueta 
		headerPnl.add(clientsLbl);
		
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
		image = new ImageIcon("src/images/magnifyingGlass.png").getImage().getScaledInstance(15, 15, Image.SCALE_SMOOTH);
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
				
		//botón de añadir nuevo cliente
		RoundButton newClient = new RoundButton(30);
		newClient.setBackground(Color.decode("#2EA623"));
		newClient.setFont(new Font("Caladea Bold", Font.BOLD, 20));
		newClient.setForeground(Color.white);
		newClient.setText("Añadir nuevo cliente");
		actionPnl.add(newClient); 

		//ir a ordenes en curso
		newClient.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				frame.remove(mainPnl);
				newClient();
				frame.repaint();
				frame.revalidate();
			}
			
		});
		
		//efecto hover de botón de añadir nueva órden según el estilo actual del botón 
		newClient.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent evt) {
				newClient.setBackground(Color.decode("#39C82C"));
		    }

		    public void mouseExited(MouseEvent evt) {
		    	newClient.setBackground(Color.decode("#2EA623"));
		    }
		});
		
		/**PANEL DONDE SE MUESTRAN LOS CLIENTES EXISTENTES*/
		JPanel clientsPnl = new JPanel();
		clientsPnl.setBorder(BorderFactory.createEmptyBorder(40, 0, 40, 0)); //borde invisible para centrar elementos que el panel contenga 
		clientsPnl.setLayout(new FlowLayout());	
		clientsPnl.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		clientsPnl.setOpaque(false); //tiene fondo o no
		mainPnl.add(clientsPnl, BorderLayout.CENTER);
		
		//botón de añadir editar cliente
		RoundButton editBttn = new RoundButton(30);
		editBttn.setBackground(Color.decode("#C07A00"));
		editBttn.setFont(new Font("Caladea Bold", Font.BOLD, 20));
		editBttn.setForeground(Color.white);
		editBttn.setText("Editar");
		clientsPnl.add(editBttn); 
		
		//ir a editar
		editBttn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				frame.remove(mainPnl);
				editClient();
				frame.repaint();
				frame.revalidate();
			}
			
		});
		
		
		//botón de historial del cliente
		RoundButton historyBttn = new RoundButton(30);
		historyBttn.setBackground(Color.decode("#2EA623"));
		historyBttn.setFont(new Font("Caladea Bold", Font.BOLD, 20));
		historyBttn.setForeground(Color.white);
		historyBttn.setText("Historial");
		clientsPnl.add(historyBttn);
		
		//ir a historial del cliente
		historyBttn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				frame.remove(mainPnl);
				clientHistory();
				frame.repaint();
				frame.revalidate();
			}
			
		});
	
		frame.setVisible(true);
	}
	
	
	public void newClient() {
		currentWindow = "newClient"; //indicar la ventana actual en la que se encuentra el usuario
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
		headerPnl.setLayout(new FlowLayout(FlowLayout.TRAILING, 30, 0));
		headerPnl.setOpaque(false); //tiene fondo o no
		mainPnl.add(headerPnl, BorderLayout.NORTH);
		
		//título que alude a la ventana actual
		JLabel clientsLbl = new JLabel("Añadir cliente");
		clientsLbl.setFont(new Font("Caladea Bold", Font.BOLD, 36));
		clientsLbl.setForeground(Color.decode("#244E23")); //color de letra
		clientsLbl.setHorizontalAlignment(JLabel.LEFT); //alinear etiqueta a la izquierda
		clientsLbl.setHorizontalAlignment(SwingConstants.LEFT); //centrar texto de la etiqueta 
		headerPnl.add(clientsLbl);
				
		//botón de barra de búsqueda
		RoundButton cancelBttn = new RoundButton(30);
		cancelBttn.setBackground(Color.decode("#EF2D2D"));
		cancelBttn.setFont(new Font("Caladea Bold", Font.BOLD, 20));
		cancelBttn.setForeground(Color.white);
		cancelBttn.setText("Cancelar");
		headerPnl.add(cancelBttn);
		
		//ir a clientes
		cancelBttn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				frame.remove(mainPnl);
				clients();
				frame.repaint();
				frame.revalidate();
			}
		});
		
		//efecto hover de botón de barra de búsqueda
		cancelBttn.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent evt) {
				cancelBttn.setBackground(Color.decode("#ED5C5C"));
		    }

		    public void mouseExited(MouseEvent evt) {
		    	cancelBttn.setBackground(Color.decode("#EF2D2D"));
		    }
		});
				
		
		//botón de añadir nuevo cliente
		RoundButton newClient = new RoundButton(30);
		newClient.setBackground(Color.decode("#2EA623"));
		newClient.setFont(new Font("Caladea Bold", Font.BOLD, 20));
		newClient.setForeground(Color.white);
		newClient.setText("Añadir");
		headerPnl.add(newClient); 

		//ir a clientes
		newClient.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				frame.remove(mainPnl);
				clients();
				frame.repaint();
				frame.revalidate();
			}
		});
		
		//efecto hover de botón de añadir nuevo cliente
		newClient.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent evt) {
				newClient.setBackground(Color.decode("#39C82C"));
		    }

		    public void mouseExited(MouseEvent evt) {
		    	newClient.setBackground(Color.decode("#2EA623"));
		    }
		});
	
		frame.setVisible(true);
	}
	
	
	public void editClient() {
		currentWindow = "editClient"; //indicar la ventana actual en la que se encuentra el usuario
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
		headerPnl.setLayout(new FlowLayout(FlowLayout.TRAILING, 30, 0));
		headerPnl.setOpaque(false); //tiene fondo o no
		mainPnl.add(headerPnl, BorderLayout.NORTH);
		
		//título que alude a la ventana actual
		JLabel clientsLbl = new JLabel("Editar cliente");
		clientsLbl.setFont(new Font("Caladea Bold", Font.BOLD, 36));
		clientsLbl.setForeground(Color.decode("#244E23")); //color de letra
		clientsLbl.setHorizontalAlignment(JLabel.LEFT); //alinear etiqueta a la izquierda
		clientsLbl.setHorizontalAlignment(SwingConstants.LEFT); //centrar texto de la etiqueta 
		headerPnl.add(clientsLbl);
				
		//botón de barra de búsqueda
		RoundButton cancelBttn = new RoundButton(30);
		cancelBttn.setBackground(Color.decode("#EF2D2D"));
		cancelBttn.setFont(new Font("Caladea Bold", Font.BOLD, 20));
		cancelBttn.setForeground(Color.white);
		cancelBttn.setText("Cancelar");
		headerPnl.add(cancelBttn);
		
		//ir a clientes
		cancelBttn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				frame.remove(mainPnl);
				clients();
				frame.repaint();
				frame.revalidate();
			}
		});
		
		//efecto hover de botón de barra de búsqueda
		cancelBttn.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent evt) {
				cancelBttn.setBackground(Color.decode("#ED5C5C"));
		    }

		    public void mouseExited(MouseEvent evt) {
		    	cancelBttn.setBackground(Color.decode("#EF2D2D"));
		    }
		});
				
		
		//botón de guardar
		RoundButton saveBttn = new RoundButton(30);
		saveBttn.setBackground(Color.decode("#555BF6"));
		saveBttn.setFont(new Font("Caladea Bold", Font.BOLD, 20));
		saveBttn.setForeground(Color.white);
		saveBttn.setText("Guardar");
		headerPnl.add(saveBttn); 

		//ir a clientes
		saveBttn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				frame.remove(mainPnl);
				clients();
				frame.repaint();
				frame.revalidate();
			}
		});
		
		//efecto hover de botón de guardar
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
	
	
	public void clientHistory() {
		currentWindow = "clientHistory"; //indicar la ventana actual en la que se encuentra el usuario
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
		headerPnl.setLayout(new FlowLayout(FlowLayout.TRAILING, 30, 0));
		headerPnl.setOpaque(false); //tiene fondo o no
		mainPnl.add(headerPnl, BorderLayout.NORTH);
		
		//título que alude a la ventana actual
		JLabel clientsLbl = new JLabel("Consultar historial de compras");
		clientsLbl.setFont(new Font("Caladea Bold", Font.BOLD, 36));
		clientsLbl.setForeground(Color.decode("#244E23")); //color de letra
		clientsLbl.setHorizontalAlignment(JLabel.LEFT); //alinear etiqueta a la izquierda
		clientsLbl.setHorizontalAlignment(SwingConstants.LEFT); //centrar texto de la etiqueta 
		headerPnl.add(clientsLbl);
				
		//botón de descargar historial
		RoundButton downloadBttn = new RoundButton(30);
		downloadBttn.setBackground(Color.decode("#367181"));
		downloadBttn.setFont(new Font("Caladea Bold", Font.BOLD, 20));
		downloadBttn.setForeground(Color.white);
		downloadBttn.setText("Descargar");
		headerPnl.add(downloadBttn); 

		//ir a clientes
		downloadBttn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				frame.remove(mainPnl);
				clients();
				frame.repaint();
				frame.revalidate();
			}
		});
		
		//efecto hover de botón de descargar historial
		downloadBttn.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent evt) {
				downloadBttn.setBackground(Color.decode("#264F59"));
		    }

		    public void mouseExited(MouseEvent evt) {
		    	downloadBttn.setBackground(Color.decode("#367181"));
		    }
		});
	
		frame.setVisible(true);
	}
}