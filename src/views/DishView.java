package views;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import controllers.AuthController;
import controllers.DishController;
import customClasses.DishCard;
import customClasses.RoundButton;
import customClasses.RoundPanel;

public class DishView {

	public JFrame frame;
	public JPanel buttonPnl;
	public JLabel restaurantNameLbl;
	public RoundButton foodBttn, orderBttn, clientBttn, inventoryBttn, returnBttn;
	public JPanel dishesPnl;
	public JPanel mainPnl;
	public Image image;
	public ImageIcon imageIcon;
	public String message;
	public String currentWindow;
	public int relativeXSize;
	public int relativeYSize;
	public AuthController ac;
	public DishController dc;
	
	
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
		foodBttn.setBackground(Color.decode("#3C7E3A"));
		foodBttn.setFont(new Font("Caladea Bold", Font.BOLD, 28)); 
		foodBttn.setForeground(Color.white);
		foodBttn.setText("Platillos");
		buttonPnl.add(foodBttn);
		
		
		//botón de órdenes
		image = new ImageIcon("src/images/order.png").getImage().getScaledInstance(30, 35, Image.SCALE_SMOOTH);
		imageIcon = new ImageIcon(image);
		orderBttn = new RoundButton(30, imageIcon); //botón
		orderBttn.setBackground(Color.decode("#3C7E3A"));
		orderBttn.setFont(new Font("Caladea Bold", Font.BOLD, 28)); 
		orderBttn.setForeground(Color.white);
		orderBttn.setText("Órdenes");
		buttonPnl.add(orderBttn);
		
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
		clientBttn.setBackground(Color.decode("#244E23"));
		clientBttn.setFont(new Font("Caladea Bold", Font.BOLD, 28)); 
		clientBttn.setForeground(Color.white);
		clientBttn.setText("Clientes");
		buttonPnl.add(clientBttn);
		
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
		image = new ImageIcon("src/images/inventory.png").getImage().getScaledInstance(30, 28, Image.SCALE_SMOOTH);
		imageIcon = new ImageIcon(image);
		inventoryBttn = new RoundButton(30, imageIcon); //botón
		inventoryBttn.setBackground(Color.decode("#244E23"));
		inventoryBttn.setFont(new Font("Caladea Bold", Font.BOLD, 28)); 
		inventoryBttn.setForeground(Color.white);
		inventoryBttn.setText("Inventario");
		buttonPnl.add(inventoryBttn);
		
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
		
		//efecto hover del botón volver
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
		currentWindow = "dishes"; //indicar la ventana actual en la que se encuentra el usuario
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
		JLabel dishesLbl = new JLabel("Platillos");
		dishesLbl.setFont(new Font("Caladea Bold", Font.BOLD, 36));
		dishesLbl.setForeground(Color.decode("#244E23")); //color de letra
		dishesLbl.setHorizontalAlignment(JLabel.LEFT); //alinear etiqueta a la izquierda
		dishesLbl.setHorizontalAlignment(SwingConstants.LEFT); //centrar texto de la etiqueta 
		headerPnl.add(dishesLbl);
		
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
		searchBarPnl.setPreferredSize(new Dimension(414, 30));
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
				
		
		//botón de añadir nuevo platillo
		RoundButton addBttn = new RoundButton(30);
		addBttn.setBackground(Color.decode("#3C7E3A"));
		addBttn.setFont(new Font("Caladea Bold", Font.BOLD, 20));
		addBttn.setForeground(Color.white);
		addBttn.setText("Nuevo");
		actionPnl.add(addBttn); 

		//ir a nuevo platillo
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
		
		//efecto hover de botón de añadir nuevo platillo
		addBttn.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent evt) {
				addBttn.setBackground(Color.decode("#4DA14B"));
		    }

		    public void mouseExited(MouseEvent evt) {
		    	addBttn.setBackground(Color.decode("#3C7E3A"));
		    }
		});
		
		
		/**PANEL DONDE SE MUESTRAN LOS PLATILLOS EXISTENTES*/
		CardLayout cardLayout = new CardLayout(); //crear layout de tipo CardLayout 
		JPanel dishesPnl = new JPanel();
		dishesPnl.setBorder(BorderFactory.createEmptyBorder(40, 0, 40, 0)); //borde invisible para centrar elementos que el panel contenga 
		dishesPnl.setLayout(cardLayout);	
		dishesPnl.setOpaque(false); //tiene fondo o no
		mainPnl.add(dishesPnl, BorderLayout.CENTER);
		
		//paneles individuales o páginas de platillos
		JPanel page1 = new JPanel();
		page1.setLayout(new FlowLayout(FlowLayout.LEFT, 30, 30));
		page1.setBackground(Color.white);
		
		//tarjeta del platillo
		DishCard dishCard = new DishCard(30, "src/images/shrimps.png", "<html>Camarones<br>(sin cabeza)<html>");
		RoundPanel dish = dishCard.createCard();
		page1.add(dish);
		
		//ir a página de platillo
		dish.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent evt) {
				frame.remove(mainPnl);
				dishPage();
				frame.repaint();
				frame.revalidate();
			}
		});
		
		//efecto hover del componente 2 (etiqueta) de la tarjeta del platillo
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
		
		dishesPnl.add(page1, "página 1"); //añadir tarjetas
		dishesPnl.add(page2, "página 2");	
		dishesPnl.add(page3, "página 3");	
		
		
		/**PIE DE PÁGINA CON BOTONES PARA NAVEGAR ENTRE PÁGINAS DE PLATILLOS*/
		JPanel footerPnl = new JPanel();
		footerPnl.setLayout(new FlowLayout());	
		footerPnl.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		footerPnl.setOpaque(false); //tiene fondo o no
		mainPnl.add(footerPnl, BorderLayout.SOUTH);
		
		//etiqueta de página
		JLabel pageLbl = new JLabel("Página");
		pageLbl.setFont(new Font("Caladea Bold", Font.BOLD, 20));
		pageLbl.setForeground(Color.decode("#244E23")); //color de letra
		pageLbl.setHorizontalAlignment(JLabel.CENTER); //alinear etiqueta a la izquierda
		pageLbl.setHorizontalAlignment(SwingConstants.CENTER); //centrar texto de la etiqueta 
		footerPnl.add(pageLbl);
		
		//botón de página 1
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
		
		//botón de página 2
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
		
		//botón de página 3
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
		currentWindow = "newDish"; //indicar la ventana actual en la que se encuentra el usuario
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
		headerPnl.setLayout(new FlowLayout(0, 30, 0));
		headerPnl.setOpaque(false); //tiene fondo o no
		mainPnl.add(headerPnl, BorderLayout.NORTH);
		
		//título que alude a la ventana actual
		JLabel dishesLbl = new JLabel("Nuevo platillo");
		dishesLbl.setFont(new Font("Caladea Bold", Font.BOLD, 36));
		dishesLbl.setForeground(Color.decode("#244E23")); //color de letra
		dishesLbl.setHorizontalAlignment(JLabel.LEFT); //alinear etiqueta a la izquierda
		dishesLbl.setHorizontalAlignment(SwingConstants.LEFT); //centrar texto de la etiqueta 
		headerPnl.add(dishesLbl);
				
		//botón de cancelar
		RoundButton cancelBttn = new RoundButton(30);
		cancelBttn.setBackground(Color.decode("#EF2D2D"));
		cancelBttn.setFont(new Font("Caladea Bold", Font.BOLD, 20));
		cancelBttn.setForeground(Color.white);
		cancelBttn.setText("Cancelar");
		headerPnl.add(cancelBttn);
		
		//cancelar acción
		cancelBttn.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent evt) {
				frame.remove(mainPnl);
				dishes();
				frame.repaint();
				frame.revalidate();
			}
		});
		
		//efecto hover de botón de cancelar
		cancelBttn.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent evt) {
				cancelBttn.setBackground(Color.decode("#ED5C5C"));
		    }

		    public void mouseExited(MouseEvent evt) {
		    	cancelBttn.setBackground(Color.decode("#EF2D2D"));
		    }
		});
				
		
		//botón de añadir guardar
		RoundButton saveBttn = new RoundButton(30);
		saveBttn.setBackground(Color.decode("#767AEC"));
		saveBttn.setFont(new Font("Caladea Bold", Font.BOLD, 20));
		saveBttn.setForeground(Color.white);
		saveBttn.setText("Guardar");
		saveBttn.setEnabled(false);;
		headerPnl.add(saveBttn); 
		
		//botón de continuar
		RoundButton nextBttn = new RoundButton(30);
		nextBttn.setBackground(Color.decode("#244E23"));
		nextBttn.setFont(new Font("Caladea Bold", Font.BOLD, 20));
		nextBttn.setForeground(Color.white);
		nextBttn.setText("Siguiente");
		headerPnl.add(nextBttn); 
		
		//efecto hover de botón de continuar
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
		currentWindow = "dishPage"; //indicar la ventana actual en la que se encuentra el usuario
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
		headerPnl.setLayout(new FlowLayout(0, 30, 0));
		headerPnl.setOpaque(false); //tiene fondo o no
		mainPnl.add(headerPnl, BorderLayout.NORTH);
		
		//título que alude a la ventana actual
		JLabel dishesLbl = new JLabel("Platillo");
		dishesLbl.setFont(new Font("Caladea Bold", Font.BOLD, 36));
		dishesLbl.setForeground(Color.decode("#244E23")); //color de letra
		dishesLbl.setHorizontalAlignment(JLabel.LEFT); //alinear etiqueta a la izquierda
		dishesLbl.setHorizontalAlignment(SwingConstants.LEFT); //centrar texto de la etiqueta 
		headerPnl.add(dishesLbl);
				
		//botón de borrar 
		RoundButton deleteBttn = new RoundButton(30);
		deleteBttn.setBackground(Color.decode("#EF2D2D"));
		deleteBttn.setFont(new Font("Caladea Bold", Font.BOLD, 20));
		deleteBttn.setForeground(Color.white);
		deleteBttn.setText("Eliminar");
		headerPnl.add(deleteBttn);
		
		//borrar platillo
		deleteBttn.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent evt) {
				frame.remove(mainPnl);
				dishes();
				frame.repaint();
				frame.revalidate();
			}
		});
		
		//efecto hover de botón de borrar
		deleteBttn.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent evt) {
				deleteBttn.setBackground(Color.decode("#ED5C5C"));
		    }

		    public void mouseExited(MouseEvent evt) {
		    	deleteBttn.setBackground(Color.decode("#EF2D2D"));
		    }
		});
				
		
		//botón de editar
		RoundButton editBttn = new RoundButton(30);
		editBttn.setBackground(Color.decode("#2EA623"));
		editBttn.setFont(new Font("Caladea Bold", Font.BOLD, 20));
		editBttn.setForeground(Color.white);
		editBttn.setText("Editar");
		editBttn.setEnabled(false);
		headerPnl.add(editBttn); 
		
		//ir a página de editar
		editBttn.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent evt) {
				frame.remove(mainPnl);
				editDish();
				frame.repaint();
				frame.revalidate();
			}
		});
		
		//efecto hover de botón de editar
		editBttn.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent evt) {
				editBttn.setBackground(Color.decode("#39C82C"));
		    }

		    public void mouseExited(MouseEvent evt) {
		    	editBttn.setBackground(Color.decode("#2EA623"));
		    }
		});
				
		
		//botón de descargar
		RoundButton downloadBttn = new RoundButton(30);
		downloadBttn.setBackground(Color.decode("#367181"));
		downloadBttn.setFont(new Font("Caladea Bold", Font.BOLD, 20));
		downloadBttn.setForeground(Color.white);
		downloadBttn.setText("Descargar");
		headerPnl.add(downloadBttn); 
		
		//efecto hover de botón de descargar
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
		currentWindow = "editDish"; //indicar la ventana actual en la que se encuentra el usuario
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
		headerPnl.setLayout(new FlowLayout(0, 30, 0));
		headerPnl.setOpaque(false); //tiene fondo o no
		mainPnl.add(headerPnl, BorderLayout.NORTH);
		
		//título que alude a la ventana actual
		JLabel dishesLbl = new JLabel("Editar platillo");
		dishesLbl.setFont(new Font("Caladea Bold", Font.BOLD, 36));
		dishesLbl.setForeground(Color.decode("#244E23")); //color de letra
		dishesLbl.setHorizontalAlignment(JLabel.LEFT); //alinear etiqueta a la izquierda
		dishesLbl.setHorizontalAlignment(SwingConstants.LEFT); //centrar texto de la etiqueta 
		headerPnl.add(dishesLbl);
				
		//botón de cancelar
		RoundButton cancelBttn = new RoundButton(30);
		cancelBttn.setBackground(Color.decode("#EF2D2D"));
		cancelBttn.setFont(new Font("Caladea Bold", Font.BOLD, 20));
		cancelBttn.setForeground(Color.white);
		cancelBttn.setText("Cancelar");
		headerPnl.add(cancelBttn);
		
		//cancelar acción
		cancelBttn.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent evt) {
				frame.remove(mainPnl);
				dishPage();
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
		
		//efecto hover de botón de descargar
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