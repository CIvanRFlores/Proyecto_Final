package views;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import controllers.AuthController;
import controllers.ClientController;
import controllers.InventoryController;
import controllers.OrderController;
import customClasses.DishCard;
import customClasses.RoundButton;
import customClasses.RoundPanel;

public class DishView {

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
	public OrderController oc;
	public ClientController cc;
	public InventoryController ic;
	
	
	public DishView(String title, int frameWidth, int frameHeight) {
		frame = new JFrame();	
		imageIcon = new ImageIcon(DishView.class.getResource("/images/elManglarLogo.png")); //icono de la ventana
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
		
		image = new ImageIcon(DishView.class.getResource("/images/elManglarName.png")).getImage().getScaledInstance(200, 40, Image.SCALE_SMOOTH);
		imageIcon = new ImageIcon(image);
		restaurantNameLbl = new JLabel(imageIcon);
		buttonPnl.add(restaurantNameLbl);
		
		image = new ImageIcon(DishView.class.getResource("/images/food.png")).getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
		imageIcon = new ImageIcon(image);
		foodBttn = new RoundButton(30, imageIcon); 
		foodBttn.setBackground(Color.decode("#3C7E3A"));
		foodBttn.setFont(new Font("Caladea Bold", Font.BOLD, 28)); 
		foodBttn.setForeground(Color.white);
		foodBttn.setText("Platillos");
		buttonPnl.add(foodBttn);
		
		
		image = new ImageIcon(DishView.class.getResource("/images/order.png")).getImage().getScaledInstance(30, 35, Image.SCALE_SMOOTH);
		imageIcon = new ImageIcon(image);
		orderBttn = new RoundButton(30, imageIcon); 
		orderBttn.setBackground(Color.decode("#3C7E3A"));
		orderBttn.setFont(new Font("Caladea Bold", Font.BOLD, 28)); 
		orderBttn.setForeground(Color.white);
		orderBttn.setText("Órdenes");
		buttonPnl.add(orderBttn);
		
		orderBttn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				frame.dispose(); //destruir ventana actual y llevar al usuario a la ventana de ordenes
				oc = new OrderController("Ordenes", frame.getWidth(), frame.getHeight()); //crear controlador de ordenes y asignar parámetros a la ventana
				oc.orders(); //llamar al método que crea y muestra la ventana de ordenes
			}
		});
		
		orderBttn.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent evt) {
				orderBttn.setBackground(Color.decode("#3C7E3A"));
		    }

		    public void mouseExited(MouseEvent evt) {
		    	orderBttn.setBackground(Color.decode("#244E23"));
		    }
		});
		
		
		image = new ImageIcon(DishView.class.getResource("/images/client.png")).getImage().getScaledInstance(30, 35, Image.SCALE_SMOOTH);
		imageIcon = new ImageIcon(image);
		clientBttn = new RoundButton(30, imageIcon); 
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
		
		
		image = new ImageIcon(DishView.class.getResource("/images/inventory.png")).getImage().getScaledInstance(30, 28, Image.SCALE_SMOOTH);
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
		
		
		image = new ImageIcon(DishView.class.getResource("/images/return.png")).getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
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
					case "dishes":
						frame.dispose(); //destruir ventana actual y llevar al usuario a la ventana de login
						ac = new AuthController("Login", frame.getWidth(), frame.getHeight()); //crear controlador de login y asignar parámetros a la ventana
						ac.login(); //llamar al método que crea y muestra la ventana de login
					break;
					
					case "newDish":
						frame.remove(mainPnl);
						dishes();
					break;
					
					case "dishPage":
						frame.remove(mainPnl);
						dishes();
					break;
					
					case "editDish":
						frame.remove(mainPnl);
						dishPage();
					break;
				}
				
				frame.repaint();
				frame.revalidate();
			}
		});
		
		returnBttn.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent evt) {
				if(currentWindow.equals("dishes")) {
					returnBttn.setBackground(Color.decode("#ED5C5C"));
				}else {
					returnBttn.setBackground(Color.decode("#3C7E3A"));
				}
		    }

		    public void mouseExited(MouseEvent evt) {
		    	if(currentWindow.equals("dishes")) {
					returnBttn.setBackground(Color.decode("#EF2D2D"));
				}else {
					returnBttn.setBackground(Color.decode("#244E23"));
				}
		    }
		});
	}
	
	
	public void dishes() {
		currentWindow = "dishes"; 
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
		
		JLabel dishesLbl = new JLabel("Platillos");
		dishesLbl.setFont(new Font("Caladea Bold", Font.BOLD, 36));
		dishesLbl.setForeground(Color.decode("#244E23")); 
		dishesLbl.setHorizontalAlignment(JLabel.LEFT); 
		dishesLbl.setHorizontalAlignment(SwingConstants.LEFT);
		headerPnl.add(dishesLbl);
		
		JPanel actionPnl = new JPanel();
		actionPnl.setLayout(new FlowLayout(FlowLayout.TRAILING, 30, 0));
		actionPnl.setOpaque(false); 
		headerPnl.add(actionPnl);
		
		RoundPanel searchBarPnl = new RoundPanel(30);  
		searchBarPnl.setBackground(Color.white);
		searchBarPnl.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5)); 
		searchBarPnl.setForeground(Color.decode("#244E23")); 
		searchBarPnl.setLayout(new BorderLayout(15, 0));
		searchBarPnl.setPreferredSize(new Dimension(414, 30));
		actionPnl.add(searchBarPnl);
		
		image = new ImageIcon(DishView.class.getResource("/images/magnifyingGlass.png")).getImage().getScaledInstance(15, 15, Image.SCALE_SMOOTH);
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
				
		
		RoundButton addBttn = new RoundButton(30);
		addBttn.setBackground(Color.decode("#3C7E3A"));
		addBttn.setFont(new Font("Caladea Bold", Font.BOLD, 20));
		addBttn.setForeground(Color.white);
		addBttn.setText("Nuevo");
		actionPnl.add(addBttn); 

		addBttn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				/*Object[] options1 = {"Platillo","Bebida"};
				
				image = new ImageIcon("src/images/questionMark.png").getImage().getScaledInstance(45, 85, Image.SCALE_SMOOTH); //imagen
				imageIcon = new ImageIcon(image);
				
				message = "¿Desea crear un platillo o bebida?";
				JOptionPane.showOptionDialog(null, message,"Alimentos y bebidas", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE, imageIcon, options1, null); //ventana emergente
				*/
				frame.remove(mainPnl);
				newDish();
				frame.repaint();
				frame.revalidate();
			}
		});
		
		addBttn.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent evt) {
				addBttn.setBackground(Color.decode("#4DA14B"));
		    }

		    public void mouseExited(MouseEvent evt) {
		    	addBttn.setBackground(Color.decode("#3C7E3A"));
		    }
		});
		
		
		CardLayout cardLayout = new CardLayout(); //crear layout de tipo CardLayout 
		JPanel dishesPnl = new JPanel();
		dishesPnl.setBorder(BorderFactory.createEmptyBorder(40, 0, 40, 0));
		dishesPnl.setLayout(cardLayout);	
		dishesPnl.setOpaque(false); 
		mainPnl.add(dishesPnl, BorderLayout.CENTER);
		
		//paneles individuales o páginas de platillos
		JPanel page1 = new JPanel();
		page1.setLayout(new FlowLayout(FlowLayout.LEFT, 30, 30));
		page1.setBackground(Color.white);
		
		DishCard dishCard = new DishCard(30, DishView.class.getResource("/images/shrimps.png"), "<html>Camarones<br>(sin cabeza)<html>");
		RoundPanel dish = dishCard.createCard();
		page1.add(dish);
		
		dish.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent evt) {
				frame.remove(mainPnl);
				dishPage();
				frame.repaint();
				frame.revalidate();
			}
		});
		
		dish.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent evt) {
				dish.getComponent(1).setForeground(Color.decode("#3C7E3A"));
		    }

		    public void mouseExited(MouseEvent evt) {
		    	dish.getComponent(1).setForeground(Color.decode("#244E23"));
		    }
		});
	
		
		JPanel page2 = new JPanel();
		page2.setBackground(Color.white);
		
		JPanel page3 = new JPanel();
		page3.setBackground(Color.white);
		
		dishesPnl.add(page1, "página 1"); 
		dishesPnl.add(page2, "página 2");	
		dishesPnl.add(page3, "página 3");	
		
		
		JPanel footerPnl = new JPanel();
		footerPnl.setLayout(new FlowLayout());	
		footerPnl.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		footerPnl.setOpaque(false); 
		mainPnl.add(footerPnl, BorderLayout.SOUTH);
		
		JLabel pageLbl = new JLabel("Página");
		pageLbl.setFont(new Font("Caladea Bold", Font.BOLD, 20));
		pageLbl.setForeground(Color.decode("#244E23"));
		pageLbl.setHorizontalAlignment(JLabel.CENTER); 
		pageLbl.setHorizontalAlignment(SwingConstants.CENTER); 
		footerPnl.add(pageLbl);
		
		RoundButton page1Bttn = new RoundButton(30);
		page1Bttn.setBackground(Color.decode("#244E23"));
		page1Bttn.setFont(new Font("Caladea Bold", Font.BOLD, 15));
		page1Bttn.setForeground(Color.white);
		page1Bttn.setText("1");
		footerPnl.add(page1Bttn);

		page1Bttn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				cardLayout.show(dishesPnl, "página 1");
			}
		});
		
		RoundButton page2Bttn = new RoundButton(30);
		page2Bttn.setBackground(Color.decode("#244E23"));
		page2Bttn.setFont(new Font("Caladea Bold", Font.BOLD, 15));
		page2Bttn.setForeground(Color.white);
		page2Bttn.setText("2");
		footerPnl.add(page2Bttn);
		
		page2Bttn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				cardLayout.show(dishesPnl, "página 2");
			}
		});
		
		RoundButton page3Bttn = new RoundButton(30);
		page3Bttn.setBackground(Color.decode("#244E23"));
		page3Bttn.setFont(new Font("Caladea Bold", Font.BOLD, 15));
		page3Bttn.setForeground(Color.white);
		page3Bttn.setText("3");
		footerPnl.add(page3Bttn);
		
		page3Bttn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				cardLayout.show(dishesPnl, "página 3");
			}
		});
		
		
		frame.setVisible(true);
	}
	
	
	public void newDish() {
		currentWindow = "newDish"; 
		returnBttn.setBackground(Color.decode("#244E23")); 
		returnBttn.setText("Volver");
		
		mainPnl = new JPanel();
		mainPnl.setBackground(Color.white);
		mainPnl.setBorder(BorderFactory.createEmptyBorder(30, 45, 30, 45)); 
		mainPnl.setLayout(new BorderLayout());
		frame.add(mainPnl, BorderLayout.CENTER);
		
		
		JPanel headerPnl = new JPanel();
		headerPnl.setLayout(new FlowLayout(0, 30, 0));
		headerPnl.setOpaque(false); 
		mainPnl.add(headerPnl, BorderLayout.NORTH);
		
		JLabel dishesLbl = new JLabel("Nuevo platillo");
		dishesLbl.setFont(new Font("Caladea Bold", Font.BOLD, 36));
		dishesLbl.setForeground(Color.decode("#244E23")); 
		dishesLbl.setHorizontalAlignment(JLabel.LEFT); 
		dishesLbl.setHorizontalAlignment(SwingConstants.LEFT); 
		headerPnl.add(dishesLbl);
				
		RoundButton cancelBttn = new RoundButton(30);
		cancelBttn.setBackground(Color.decode("#EF2D2D"));
		cancelBttn.setFont(new Font("Caladea Bold", Font.BOLD, 20));
		cancelBttn.setForeground(Color.white);
		cancelBttn.setText("Cancelar");
		headerPnl.add(cancelBttn);
		
		cancelBttn.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent evt) {
				frame.remove(mainPnl);
				dishes();
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
		saveBttn.setBackground(Color.decode("#767AEC"));
		saveBttn.setFont(new Font("Caladea Bold", Font.BOLD, 20));
		saveBttn.setForeground(Color.white);
		saveBttn.setText("Guardar");
		saveBttn.setEnabled(false);;
		headerPnl.add(saveBttn); 
		
		RoundButton nextBttn = new RoundButton(30);
		nextBttn.setBackground(Color.decode("#244E23"));
		nextBttn.setFont(new Font("Caladea Bold", Font.BOLD, 20));
		nextBttn.setForeground(Color.white);
		nextBttn.setText("Siguiente");
		headerPnl.add(nextBttn); 
		
		nextBttn.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent evt) {
				nextBttn.setBackground(Color.decode("#3C7E3A"));
		    }

		    public void mouseExited(MouseEvent evt) {
		    	nextBttn.setBackground(Color.decode("#244E23"));
		    }
		});
	}
	
	
	public void dishPage() {
		currentWindow = "dishPage"; 
		returnBttn.setBackground(Color.decode("#244E23"));
		returnBttn.setText("Volver");
		
		mainPnl = new JPanel();
		mainPnl.setBackground(Color.white);
		mainPnl.setBorder(BorderFactory.createEmptyBorder(30, 45, 30, 45)); 
		mainPnl.setLayout(new BorderLayout());
		frame.add(mainPnl, BorderLayout.CENTER);
		
		
		JPanel headerPnl = new JPanel();
		headerPnl.setLayout(new FlowLayout(0, 30, 0));
		headerPnl.setOpaque(false); 
		mainPnl.add(headerPnl, BorderLayout.NORTH);
		
		JLabel dishesLbl = new JLabel("Platillo");
		dishesLbl.setFont(new Font("Caladea Bold", Font.BOLD, 36));
		dishesLbl.setForeground(Color.decode("#244E23")); 
		dishesLbl.setHorizontalAlignment(JLabel.LEFT); 
		dishesLbl.setHorizontalAlignment(SwingConstants.LEFT);
		headerPnl.add(dishesLbl);
				
		RoundButton deleteBttn = new RoundButton(30);
		deleteBttn.setBackground(Color.decode("#EF2D2D"));
		deleteBttn.setFont(new Font("Caladea Bold", Font.BOLD, 20));
		deleteBttn.setForeground(Color.white);
		deleteBttn.setText("Eliminar");
		headerPnl.add(deleteBttn);
		
		deleteBttn.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent evt) {
				frame.remove(mainPnl);
				dishes();
				frame.repaint();
				frame.revalidate();
			}
		});
		
		deleteBttn.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent evt) {
				deleteBttn.setBackground(Color.decode("#ED5C5C"));
		    }

		    public void mouseExited(MouseEvent evt) {
		    	deleteBttn.setBackground(Color.decode("#EF2D2D"));
		    }
		});
				
		
		RoundButton editBttn = new RoundButton(30);
		editBttn.setBackground(Color.decode("#2EA623"));
		editBttn.setFont(new Font("Caladea Bold", Font.BOLD, 20));
		editBttn.setForeground(Color.white);
		editBttn.setText("Editar");
		editBttn.setEnabled(false);
		headerPnl.add(editBttn); 
		
		editBttn.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent evt) {
				frame.remove(mainPnl);
				editDish();
				frame.repaint();
				frame.revalidate();
			}
		});
		
		editBttn.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent evt) {
				editBttn.setBackground(Color.decode("#39C82C"));
		    }

		    public void mouseExited(MouseEvent evt) {
		    	editBttn.setBackground(Color.decode("#2EA623"));
		    }
		});
				
		
		RoundButton downloadBttn = new RoundButton(30);
		downloadBttn.setBackground(Color.decode("#367181"));
		downloadBttn.setFont(new Font("Caladea Bold", Font.BOLD, 20));
		downloadBttn.setForeground(Color.white);
		downloadBttn.setText("Descargar");
		headerPnl.add(downloadBttn); 
		
		downloadBttn.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent evt) {
				downloadBttn.setBackground(Color.decode("#264F59"));
		    }

		    public void mouseExited(MouseEvent evt) {
		    	downloadBttn.setBackground(Color.decode("#367181"));
		    }
		});
	}
	
	
	public void editDish() {
		currentWindow = "editDish"; 
		returnBttn.setBackground(Color.decode("#244E23")); 
		returnBttn.setText("Volver");
		
		mainPnl = new JPanel();
		mainPnl.setBackground(Color.white);
		mainPnl.setBorder(BorderFactory.createEmptyBorder(30, 45, 30, 45)); 
		mainPnl.setLayout(new BorderLayout());
		frame.add(mainPnl, BorderLayout.CENTER);
		
		
		JPanel headerPnl = new JPanel();
		headerPnl.setLayout(new FlowLayout(0, 30, 0));
		headerPnl.setOpaque(false); 
		mainPnl.add(headerPnl, BorderLayout.NORTH);
		
		JLabel dishesLbl = new JLabel("Editar platillo");
		dishesLbl.setFont(new Font("Caladea Bold", Font.BOLD, 36));
		dishesLbl.setForeground(Color.decode("#244E23")); 
		dishesLbl.setHorizontalAlignment(JLabel.LEFT);
		dishesLbl.setHorizontalAlignment(SwingConstants.LEFT); 
		headerPnl.add(dishesLbl);
				
		RoundButton cancelBttn = new RoundButton(30);
		cancelBttn.setBackground(Color.decode("#EF2D2D"));
		cancelBttn.setFont(new Font("Caladea Bold", Font.BOLD, 20));
		cancelBttn.setForeground(Color.white);
		cancelBttn.setText("Cancelar");
		headerPnl.add(cancelBttn);
		
		cancelBttn.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent evt) {
				frame.remove(mainPnl);
				dishPage();
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
		
		saveBttn.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent evt) {
				saveBttn.setBackground(Color.decode("#767AEC"));
		    }

		    public void mouseExited(MouseEvent evt) {
		    	saveBttn.setBackground(Color.decode("#555BF6"));
		    }
		});
	}
	
}