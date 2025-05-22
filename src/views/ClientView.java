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
		frame = new JFrame(); 
		imageIcon = new ImageIcon("/images/elManglarLogo.png"); //icono de la ventana
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
		
		image = new ImageIcon(ClientView.class.getResource("/images/elManglarName.png")).getImage().getScaledInstance(200, 40, Image.SCALE_SMOOTH);
		imageIcon = new ImageIcon(image);
		restaurantNameLbl = new JLabel(imageIcon);
		buttonPnl.add(restaurantNameLbl);
		
		image = new ImageIcon("src/images/food.png").getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
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
		
		
		//botón de órdenes
		image = new ImageIcon(ClientView.class.getResource("/images/order.png")).getImage().getScaledInstance(30, 35, Image.SCALE_SMOOTH);
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
		image = new ImageIcon(ClientView.class.getResource("/images/client.png")).getImage().getScaledInstance(30, 35, Image.SCALE_SMOOTH);
		imageIcon = new ImageIcon(image);
		clientBttn = new RoundButton(30, imageIcon); //botón
		clientBttn.setBackground(Color.decode("#3C7E3A"));
		clientBttn.setFont(new Font("Caladea Bold", Font.BOLD, 28)); 
		clientBttn.setForeground(Color.white);
		clientBttn.setText("Clientes");
		buttonPnl.add(clientBttn);
		
		
		//botón de inventario
		image = new ImageIcon(ClientView.class.getResource("/images/inventory.png")).getImage().getScaledInstance(30, 28, Image.SCALE_SMOOTH);
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
		image = new ImageIcon(ClientView.class.getResource("/images/return.png")).getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
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
		currentWindow = "clients"; 
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
		
		JLabel clientsLbl = new JLabel("Clientes");
		clientsLbl.setFont(new Font("Caladea Bold", Font.BOLD, 36));
		clientsLbl.setForeground(Color.decode("#244E23")); 
		clientsLbl.setHorizontalAlignment(JLabel.LEFT); 
		clientsLbl.setHorizontalAlignment(SwingConstants.LEFT); 
		headerPnl.add(clientsLbl);
		
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
		
		image = new ImageIcon(ClientView.class.getResource("/images/magnifyingGlass.png")).getImage().getScaledInstance(15, 15, Image.SCALE_SMOOTH);
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
				
		RoundButton newClient = new RoundButton(30);
		newClient.setBackground(Color.decode("#2EA623"));
		newClient.setFont(new Font("Caladea Bold", Font.BOLD, 20));
		newClient.setForeground(Color.white);
		newClient.setText("Añadir nuevo cliente");
		actionPnl.add(newClient); 

		newClient.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				frame.remove(mainPnl);
				newClient();
				frame.repaint();
				frame.revalidate();
			}
		});
		
		newClient.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent evt) {
				newClient.setBackground(Color.decode("#39C82C"));
		    }

		    public void mouseExited(MouseEvent evt) {
		    	newClient.setBackground(Color.decode("#2EA623"));
		    }
		});
		
		JPanel clientsPnl = new JPanel();
		clientsPnl.setBorder(BorderFactory.createEmptyBorder(40, 0, 40, 0));  
		clientsPnl.setLayout(new FlowLayout());	
		clientsPnl.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		clientsPnl.setOpaque(false);
		mainPnl.add(clientsPnl, BorderLayout.CENTER);
		
		RoundButton editBttn = new RoundButton(30);
		editBttn.setBackground(Color.decode("#C07A00"));
		editBttn.setFont(new Font("Caladea Bold", Font.BOLD, 20));
		editBttn.setForeground(Color.white);
		editBttn.setText("Editar");
		clientsPnl.add(editBttn); 
		
		editBttn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				frame.remove(mainPnl);
				editClient();
				frame.repaint();
				frame.revalidate();
			}
		});
		
		
		RoundButton historyBttn = new RoundButton(30);
		historyBttn.setBackground(Color.decode("#2EA623"));
		historyBttn.setFont(new Font("Caladea Bold", Font.BOLD, 20));
		historyBttn.setForeground(Color.white);
		historyBttn.setText("Historial");
		clientsPnl.add(historyBttn);
		
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
		currentWindow = "newClient"; 
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
		
		JLabel clientsLbl = new JLabel("Añadir cliente");
		clientsLbl.setFont(new Font("Caladea Bold", Font.BOLD, 36));
		clientsLbl.setForeground(Color.decode("#244E23")); 
		clientsLbl.setHorizontalAlignment(JLabel.LEFT);
		clientsLbl.setHorizontalAlignment(SwingConstants.LEFT);  
		headerPnl.add(clientsLbl);
			
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
				clients();
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
				
		
		RoundButton newClient = new RoundButton(30);
		newClient.setBackground(Color.decode("#2EA623"));
		newClient.setFont(new Font("Caladea Bold", Font.BOLD, 20));
		newClient.setForeground(Color.white);
		newClient.setText("Añadir");
		headerPnl.add(newClient); 

		newClient.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				frame.remove(mainPnl);
				clients();
				frame.repaint();
				frame.revalidate();
			}
		});
		
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
		currentWindow = "editClient"; 
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
		
		JLabel clientsLbl = new JLabel("Editar cliente");
		clientsLbl.setFont(new Font("Caladea Bold", Font.BOLD, 36));
		clientsLbl.setForeground(Color.decode("#244E23")); 
		clientsLbl.setHorizontalAlignment(JLabel.LEFT); 
		clientsLbl.setHorizontalAlignment(SwingConstants.LEFT); 
		headerPnl.add(clientsLbl);
				
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
				clients();
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
				clients();
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
	
	
	public void clientHistory() {
		currentWindow = "clientHistory"; 
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
		
		JLabel clientsLbl = new JLabel("Consultar historial de compras");
		clientsLbl.setFont(new Font("Caladea Bold", Font.BOLD, 36));
		clientsLbl.setForeground(Color.decode("#244E23"));
		clientsLbl.setHorizontalAlignment(JLabel.LEFT); 
		clientsLbl.setHorizontalAlignment(SwingConstants.LEFT); 
		headerPnl.add(clientsLbl);
				
		RoundButton downloadBttn = new RoundButton(30);
		downloadBttn.setBackground(Color.decode("#367181"));
		downloadBttn.setFont(new Font("Caladea Bold", Font.BOLD, 20));
		downloadBttn.setForeground(Color.white);
		downloadBttn.setText("Descargar");
		headerPnl.add(downloadBttn); 

		downloadBttn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				frame.remove(mainPnl);
				clients();
				frame.repaint();
				frame.revalidate();
			}
		});
		
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