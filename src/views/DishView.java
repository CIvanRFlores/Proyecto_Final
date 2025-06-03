package views;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.plaf.basic.BasicComboBoxUI;

import controllers.DishController;
import customClasses.*;

public class DishView {
	public JFrame frame;
	public JPanel mainPnl;
	public Image image;
	public ImageIcon imageIcon;
	public String message;
	public String currentWindow;
	public int relativeXSize;
	public int relativeYSize;
	public DishController dc;
	
	public DishView(String title, int frameWidth, int frameHeight) {
		frame = new JFrame(); 
		imageIcon = new ImageIcon(ClientView.class.getResource("/images/elManglarLogo.png")); //icono de la ventana
		frame.setIconImage(imageIcon.getImage());
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
		frame.setSize(frameWidth, frameHeight); 
		frame.setLocationRelativeTo(null); //colocar la ventana en el centro de la pantalla
		frame.setResizable(true); 
		frame.setMinimumSize(new Dimension(352, 300));
		frame.setTitle(title); 
		
		SideBarPanel sideBar = new SideBarPanel(frame);
		JPanel buttonPanel = sideBar.createSidePanel();
		frame.add(buttonPanel, BorderLayout.WEST);
		
		buttonPanel.getComponent(1).setBackground(Color.decode("#3C7E3A"));
		
		frame.addComponentListener(new ComponentAdapter() {
            public void componentResized(ComponentEvent e) {
            	double aspectRatio = 1.25/1;
            	
            	Dimension newSize = frame.getSize();
                int newWidth = newSize.width;
                int newHeight = (int) (newWidth/aspectRatio);

                frame.setSize(newWidth, newHeight);
       			frame.repaint();
            }
        });
	}
	
	public void dishes() {
		dc = new DishController(frame.getTitle(), frame.getWidth(), frame.getHeight());
		
		mainPnl = new JPanel();
		mainPnl.setBackground(Color.white);
		mainPnl.setBorder(BorderFactory.createEmptyBorder(30, 45, 30, 45)); 
		mainPnl.setLayout(new BorderLayout());
		frame.add(mainPnl, BorderLayout.CENTER);
		
		JPanel headerPnl = new JPanel();
		headerPnl.setLayout(new BorderLayout(20, 15));
		headerPnl.setOpaque(false); 
		mainPnl.add(headerPnl, BorderLayout.NORTH);
		
		JLabel dishesLbl = new JLabel("Platillos");
		dishesLbl.setFont(new Font("Caladea Bold", Font.BOLD, 36));
		dishesLbl.setForeground(Color.decode("#244E23")); 
		dishesLbl.setHorizontalAlignment(JLabel.LEFT); 
		dishesLbl.setHorizontalAlignment(SwingConstants.LEFT);
		headerPnl.add(dishesLbl, BorderLayout.NORTH);
		
		RoundPanel searchBarPnl = new RoundPanel(30);  
		searchBarPnl.setBackground(Color.white);
		searchBarPnl.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5)); 
		searchBarPnl.setForeground(Color.decode("#244E23")); 
		searchBarPnl.setLayout(new BorderLayout(15, 0));
		headerPnl.add(searchBarPnl, BorderLayout.CENTER);
		
		image = new ImageIcon(DishView.class.getResource("/images/magnifyingGlass.png")).getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH);
		imageIcon = new ImageIcon(image);
		JLabel logoTextLbl = new JLabel(imageIcon);
		searchBarPnl.add(logoTextLbl, BorderLayout.WEST);
		
		JTextField searchTxtFld = new JTextField();
		searchTxtFld.setBorder(null);
		searchTxtFld.setFont(new Font("Caladea Bold", Font.BOLD, 14)); 
		searchTxtFld.setForeground(Color.decode("#244E23"));
		searchTxtFld.setOpaque(false); 
		searchBarPnl.add(searchTxtFld,  BorderLayout.CENTER);
				
		JPanel actionPnl = new JPanel();
		actionPnl.setLayout(new GridLayout(1, 3, 20, 0));
		actionPnl.setOpaque(false); 
		headerPnl.add(actionPnl, BorderLayout.EAST);
		
		RoundButton searchBttn = new RoundButton(30);
		searchBttn.setBackground(Color.decode("#244E23"));
		searchBttn.setFont(new Font("Caladea Bold", Font.BOLD, 20));
		searchBttn.setForeground(Color.white);
		searchBttn.setText("Buscar");
		searchBttn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				searchDish();
			}
		});
		actionPnl.add(searchBttn);
		
		searchBttn.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent evt) {
				searchBttn.setBackground(Color.decode("#3C7E3A"));
		    }

		    public void mouseExited(MouseEvent evt) {
		    	searchBttn.setBackground(Color.decode("#244E23"));
		    }
		});
				
		actionPnl.add(Box.createHorizontalStrut(0));
		
		RoundButton addBttn = new RoundButton(30);
		addBttn.setBackground(Color.decode("#3C7E3A"));
		addBttn.setFont(new Font("Caladea Bold", Font.BOLD, 20));
		addBttn.setForeground(Color.white);
		addBttn.setText("Nuevo");
		actionPnl.add(addBttn); 

		addBttn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Object[] options = {"Platillo", "Bebida"};
				
				image = new ImageIcon(DishView.class.getResource("/images/questionMark.png")).getImage().getScaledInstance(25, 45, Image.SCALE_SMOOTH); //imagen
				imageIcon = new ImageIcon(image);
				
				message = "¿Desea crear un platillo o bebida?";
				int opc = JOptionPane.showOptionDialog(null, message, "Alimentos y bebidas", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE, imageIcon, options, null); //ventana emergente
				
				if(opc==0) {
					frame.dispose();
					dc.newDish("platillo");
				} 
				else if(opc==1){
					frame.dispose();
					dc.newDish("bebida");
				}
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
		
		
		JPanel dishesPnl = new JPanel();
		dishesPnl.setBackground(Color.white);
		dishesPnl.setBorder(BorderFactory.createEmptyBorder(40, 0, 40, 15));
		dishesPnl.setLayout(new GridLayout(18, 3, 30, 30));	
		
		for(int i=0; i<27; i++) {
			DishCard dishCard = new DishCard(30, DishView.class.getResource("/images/shrimps.png"), "platillo", "Camarones (sin cabeza)", frame);
			RoundPanel dish = dishCard.createCard();
			
			dishesPnl.add(dish);
		}
	
		JScrollPane scrollPane = new JScrollPane(dishesPnl);
		scrollPane.setBorder(BorderFactory.createEmptyBorder(30, 0, 0, 0));
		scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		scrollPane.setOpaque(false);
		mainPnl.add(scrollPane, BorderLayout.CENTER);
		
		frame.setVisible(true);
		
	}
	
	public void newDish(String type) {
		dc = new DishController(frame.getTitle(), frame.getWidth(), frame.getHeight());
		
		mainPnl = new JPanel();
		mainPnl.setBackground(Color.white);
		mainPnl.setBorder(BorderFactory.createEmptyBorder(30, 45, 30, 45)); 
		mainPnl.setLayout(new BorderLayout());
		frame.add(mainPnl, BorderLayout.CENTER);
		
		JPanel headerPnl = new JPanel();
		headerPnl.setLayout(new BorderLayout(20, 15));
		headerPnl.setOpaque(false); 
		mainPnl.add(headerPnl, BorderLayout.NORTH);
		
		JLabel dishesLbl = new JLabel(type.equals("platillo")?"Nuevo " + type : "Nueva " + type);
		dishesLbl.setFont(new Font("Caladea Bold", Font.BOLD, 36));
		dishesLbl.setForeground(Color.decode("#244E23")); 
		dishesLbl.setHorizontalAlignment(JLabel.LEFT); 
		dishesLbl.setHorizontalAlignment(SwingConstants.LEFT); 
		headerPnl.add(dishesLbl, BorderLayout.NORTH);
				
		JPanel actionPnl = new JPanel();
		actionPnl.setLayout(new GridLayout(1, 4, 20, 0));
		actionPnl.setOpaque(false); 
		headerPnl.add(actionPnl, BorderLayout.EAST);
		
		actionPnl.add(Box.createHorizontalStrut(0));
		actionPnl.add(Box.createHorizontalStrut(0));
		
		RoundButton cancelBttn = new RoundButton(30);
		cancelBttn.setBackground(Color.decode("#EF2D2D"));
		cancelBttn.setFont(new Font("Caladea Bold", Font.BOLD, 20));
		cancelBttn.setForeground(Color.white);
		cancelBttn.setText("Cancelar");
		actionPnl.add(cancelBttn);
		
		cancelBttn.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent evt) {
				Object[] options = {"Volver", "Salir"};
				
				image = new ImageIcon(ActionButtonPanel.class.getResource("/images/errorCircle.png")).getImage().getScaledInstance(45, 45, Image.SCALE_SMOOTH);
				imageIcon = new ImageIcon(image);
				
				String message = "Todos los cambios se perderán.";
				int opc = JOptionPane.showOptionDialog(null, message, "Cancelar acción", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE, imageIcon, options, null);
				
				if(opc==1) {
					frame.dispose();
					dc.dishes();
				}
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
		
		
		DishFormPanel form = new DishFormPanel(frame, type);
		JPanel formPanel = form.createDishForm();
		formPanel.setBorder(BorderFactory.createEmptyBorder(40, 0, 40, 0));
		mainPnl.add(formPanel, BorderLayout.CENTER);
		
		RoundButton saveBttn = new RoundButton(30);
		saveBttn.setBackground(Color.decode("#555BF6"));
		saveBttn.setFont(new Font("Caladea Bold", Font.BOLD, 20));
		saveBttn.setForeground(Color.white);
		saveBttn.setText("Guardar"); 

		saveBttn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(!form.ingredientFormEmptyFields()) {
					System.out.println(form.getTopLeftCmbBx());
					image = new ImageIcon(AuthView.class.getResource("/images/checkCircle.png")).getImage().getScaledInstance(45, 45, Image.SCALE_SMOOTH); 
	   				imageIcon = new ImageIcon(image);
	   				
					message = type.equals("platillo")?type+" creado correctamente." : type+" creada correctamente.";
					message = message.substring(0, 1).toUpperCase() + message.substring(1);
					JOptionPane.showMessageDialog(null, message, "Acción exitosa", JOptionPane.INFORMATION_MESSAGE, imageIcon); 
					
					frame.dispose();
					dc.dishes();
				}
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
		
		RoundButton nextBttn = new RoundButton(30);
		nextBttn.setBackground(Color.decode("#244E23"));
		nextBttn.setFont(new Font("Caladea Bold", Font.BOLD, 20));
		nextBttn.setForeground(Color.white);
		nextBttn.setText("Siguiente");
		actionPnl.add(nextBttn); 
		
		nextBttn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JPanel formPanel2 = form.createIngredientsForm();
				formPanel2.setBorder(BorderFactory.createEmptyBorder(40, 0, 40, 0));
				
				if(!form.dishFormEmptyFields()) {
					mainPnl.remove(formPanel);
					actionPnl.remove(nextBttn);
					
					mainPnl.add(formPanel2, BorderLayout.CENTER);
					actionPnl.add(saveBttn);
					
					frame.repaint();
					frame.revalidate();
				}
			}
		});
		
		nextBttn.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent evt) {
				nextBttn.setBackground(Color.decode("#3C7E3A"));
		    }

		    public void mouseExited(MouseEvent evt) {
		    	nextBttn.setBackground(Color.decode("#244E23"));
		    }
		});
		
		frame.setVisible(true);
	}
	
	public void searchDish() {
		image = new ImageIcon(AuthView.class.getResource("/images/warning.png")).getImage().getScaledInstance(45, 45, Image.SCALE_SMOOTH); 
			imageIcon = new ImageIcon(image);
			
		message = "Platillo no encontrado";
		JOptionPane.showMessageDialog(null, message, "Platillo", JOptionPane.INFORMATION_MESSAGE, imageIcon); 
	}
	
	public void dishPage(String type) {
		dc = new DishController(frame.getTitle(), frame.getWidth(), frame.getHeight());
		
		mainPnl = new JPanel();
		mainPnl.setBackground(Color.white);
		mainPnl.setBorder(BorderFactory.createEmptyBorder(30, 45, 30, 45)); 
		mainPnl.setLayout(new BorderLayout());
		frame.add(mainPnl, BorderLayout.CENTER);
		
		JPanel headerPnl = new JPanel();
		headerPnl.setLayout(new BorderLayout(20, 15));
		headerPnl.setOpaque(false); 
		mainPnl.add(headerPnl, BorderLayout.NORTH);
		
		JLabel dishesLbl = new JLabel(type.substring(0, 1).toUpperCase() + type.substring(1));
		dishesLbl.setFont(new Font("Caladea Bold", Font.BOLD, 36));
		dishesLbl.setForeground(Color.decode("#244E23")); 
		dishesLbl.setHorizontalAlignment(JLabel.LEFT); 
		dishesLbl.setHorizontalAlignment(SwingConstants.LEFT);
		headerPnl.add(dishesLbl, BorderLayout.NORTH);
		
		JPanel actionPnl = new JPanel();
		actionPnl.setLayout(new GridLayout(1, 4, 20, 0));
		actionPnl.setOpaque(false); 
		headerPnl.add(actionPnl, BorderLayout.EAST);
		
		actionPnl.add(Box.createHorizontalStrut(0));
				
		RoundButton deleteBttn = new RoundButton(30);
		deleteBttn.setBackground(Color.decode("#EF2D2D"));
		deleteBttn.setFont(new Font("Caladea Bold", Font.BOLD, 20));
		deleteBttn.setForeground(Color.white);
		deleteBttn.setText("Eliminar");
		actionPnl.add(deleteBttn);
		
		deleteBttn.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent evt) {
				
				Object[] options = {"Volver", "Eliminar"};
				
				image = new ImageIcon(DishView.class.getResource("/images/errorCircle.png")).getImage().getScaledInstance(45, 45, Image.SCALE_SMOOTH);
				imageIcon = new ImageIcon(image);
				
				String message = "Esta acción no se puede deshacer.";
				int opc = JOptionPane.showOptionDialog(null, message, "Borrar "+type, JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE, imageIcon, options, null);
				
				if(opc==1) {
					frame.dispose();
					dc.dishes();
				}
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
		actionPnl.add(editBttn); 
		
		editBttn.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent evt) {
				frame.dispose();
				dc.editDish(type);
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
		actionPnl.add(downloadBttn); 
		
		downloadBttn.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent evt) {
				downloadBttn.setBackground(Color.decode("#264F59"));
		    }

		    public void mouseExited(MouseEvent evt) {
		    	downloadBttn.setBackground(Color.decode("#367181"));
		    }
		});
		
		
		JPanel dishPnl = new JPanel();
		dishPnl.setBackground(Color.white);
		dishPnl.setBorder(BorderFactory.createEmptyBorder(40, 0, 40, 0));
		dishPnl.setLayout(new BorderLayout(0, 30));
		mainPnl.add(dishPnl, BorderLayout.CENTER);
		
		
		RoundPanel dishHeaderPnl = new RoundPanel(30);
		dishHeaderPnl.setBackground(Color.decode("#EDEDED"));
		dishHeaderPnl.setForeground(Color.decode("#EDEDED"));
		dishHeaderPnl.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
		dishHeaderPnl.setLayout(new GridLayout(1, 3, 30, 0));
		dishPnl.add(dishHeaderPnl, BorderLayout.NORTH);
		
		dishHeaderPnl.add(Box.createHorizontalStrut(0));
		
		JLabel priceLbl = new JLabel("$365 MXN");
		priceLbl.setFont(new Font("Caladea Regular", Font.PLAIN, 30));
		priceLbl.setForeground(Color.decode("#244E23")); 
		priceLbl.setHorizontalAlignment(SwingConstants.CENTER);
		dishHeaderPnl.add(priceLbl);
		
		String ingredients[] = {"Ingredientes", "Maíz", "Camarón", "Papa", "Mantequilla", "Aceite"};
		
		JComboBox<String> ingredientsCmbBx = new JComboBox<>(ingredients);
		ingredientsCmbBx.setBorder(null); 
		ingredientsCmbBx.setForeground(Color.decode("#244E23")); 
		ingredientsCmbBx.setFont(new Font("Caladea Regular", Font.PLAIN, 28)); 
		ingredientsCmbBx.setOpaque(false);
		ingredientsCmbBx.setUI(new BasicComboBoxUI());
		dishHeaderPnl.add(ingredientsCmbBx);
		
		ingredientsCmbBx.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				if(e.getStateChange()==ItemEvent.SELECTED) { //si se cambia el elemento seleccionado
					if(!ingredientsCmbBx.getSelectedItem().toString().equals(ingredients[0])) {
						ingredientsCmbBx.setSelectedItem(ingredients[0]);
					}
				}
			}
			
		});
		
		image = new ImageIcon(DishView.class.getResource("/images/shrimps.png")).getImage().getScaledInstance(420, 220, Image.SCALE_SMOOTH);
		imageIcon = new ImageIcon(image);
		JLabel dishImageLbl = new JLabel(imageIcon);
		dishPnl.add(dishImageLbl, BorderLayout.CENTER);
		
		
		RoundPanel dishFooterPnl = new RoundPanel(30);
		dishFooterPnl.setBackground(Color.decode("#EDEDED"));
		dishFooterPnl.setForeground(Color.decode("#EDEDED"));
		dishFooterPnl.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
		dishFooterPnl.setLayout(new GridLayout(2, 1, 0, 20));
		dishPnl.add(dishFooterPnl, BorderLayout.SOUTH);
		
		JLabel dishNameLbl = new JLabel("Camarones (sin cabeza)");
		dishNameLbl.setFont(new Font("Caladea Bold", Font.BOLD, 36));
		dishNameLbl.setForeground(Color.decode("#244E23")); 
		dishNameLbl.setHorizontalAlignment(JLabel.LEFT); 
		dishNameLbl.setHorizontalAlignment(SwingConstants.LEFT);
		dishFooterPnl.add(dishNameLbl);
		
		JTextArea dishDescText = new JTextArea();
		dishDescText.setForeground(Color.decode("#244E23"));
		dishDescText.setEnabled(false);
		dishDescText.setDisabledTextColor(Color.decode("#244E23"));
		dishDescText.setFont(new Font("Caladea Regular", Font.PLAIN, 30));
		dishDescText.setText("Camarones cocidos sin cabeza, servidos con maíz y papas, ideales para todos los gustos.");
		dishDescText.setLineWrap(true); 
		dishDescText.setWrapStyleWord(true); 
		dishDescText.setOpaque(false);
		dishFooterPnl.add(dishDescText);
		
		/**cuando la ventana es redimensionada, los elementos dentro de ella cambian de tamaño**/
		frame.addComponentListener(new ComponentAdapter() {
            public void componentResized(ComponentEvent e) {
            	relativeXSize = (int) (frame.getWidth()*0.420);
            	relativeYSize = (int) (frame.getWidth()*0.220);
               	image = new ImageIcon(DishView.class.getResource("/images/shrimps.png")).getImage().getScaledInstance(relativeXSize, relativeYSize, Image.SCALE_SMOOTH);
       			imageIcon = new ImageIcon(image);
       			dishImageLbl.setIcon(imageIcon);  
       			
       			priceLbl.setFont(new Font("Caladea Regular", Font.PLAIN, (int) (frame.getWidth()*0.03)));
       			ingredientsCmbBx.setFont(new Font("Caladea Regular", Font.PLAIN, (int) (frame.getWidth()*0.028))); 
       			dishNameLbl.setFont(new Font("Caladea Bold", Font.BOLD, (int) (frame.getWidth()*0.036)));
       			dishDescText.setFont(new Font("Caladea Regular", Font.PLAIN, (int) (frame.getWidth()*0.030)));
       			
       			relativeXSize = (int) (frame.getHeight()*0.045);
       			mainPnl.setBorder(BorderFactory.createEmptyBorder(relativeXSize-15, relativeXSize, relativeXSize-15, relativeXSize)); 
       			dishPnl.setBorder(BorderFactory.createEmptyBorder(relativeXSize-5, 0, relativeXSize-5, 0));
       			
       			frame.repaint();
            }
        });
		
		frame.setVisible(true);
	}
	
	public void editDish(String type) {
		dc = new DishController(frame.getTitle(), frame.getWidth(), frame.getHeight());
		
		mainPnl = new JPanel();
		mainPnl.setBackground(Color.white);
		mainPnl.setBorder(BorderFactory.createEmptyBorder(30, 45, 30, 45)); 
		mainPnl.setLayout(new BorderLayout());
		frame.add(mainPnl, BorderLayout.CENTER);
		
		JPanel headerPnl = new JPanel();
		headerPnl.setLayout(new BorderLayout(20, 15));
		headerPnl.setOpaque(false); 
		mainPnl.add(headerPnl, BorderLayout.NORTH);
		
		JLabel dishesLbl = new JLabel(type.equals("platillo")?"Editar " + type : "Editar " + type);
		dishesLbl.setFont(new Font("Caladea Bold", Font.BOLD, 36));
		dishesLbl.setForeground(Color.decode("#244E23")); 
		dishesLbl.setHorizontalAlignment(JLabel.LEFT);
		dishesLbl.setHorizontalAlignment(SwingConstants.LEFT); 
		headerPnl.add(dishesLbl, BorderLayout.NORTH);
		
		JPanel actionPnl = new JPanel();
		actionPnl.setLayout(new GridLayout(1, 4, 20, 0));
		actionPnl.setOpaque(false); 
		headerPnl.add(actionPnl, BorderLayout.EAST);
				
		actionPnl.add(Box.createHorizontalStrut(0));
		actionPnl.add(Box.createHorizontalStrut(0));
		
		RoundButton cancelBttn = new RoundButton(30);
		cancelBttn.setBackground(Color.decode("#EF2D2D"));
		cancelBttn.setFont(new Font("Caladea Bold", Font.BOLD, 20));
		cancelBttn.setForeground(Color.white);
		cancelBttn.setText("Cancelar");
		actionPnl.add(cancelBttn);
		
		cancelBttn.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent evt) {
				Object[] options = {"Volver", "Salir"};
				
				image = new ImageIcon(ActionButtonPanel.class.getResource("/images/errorCircle.png")).getImage().getScaledInstance(45, 45, Image.SCALE_SMOOTH);
				imageIcon = new ImageIcon(image);
				
				String message = "Todos los cambios se perderán.";
				int opc = JOptionPane.showOptionDialog(null, message, "Cancelar acción", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE, imageIcon, options, null);
				
				if(opc==1) {
					frame.dispose();
					dc.dishes();
				}
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
		
		
		DishFormPanel form = new DishFormPanel(frame, type);
		JPanel formPanel = form.createDishForm();
		formPanel.setBorder(BorderFactory.createEmptyBorder(40, 0, 40, 0));
		mainPnl.add(formPanel, BorderLayout.CENTER);
		
		RoundButton saveBttn = new RoundButton(30);
		saveBttn.setBackground(Color.decode("#555BF6"));
		saveBttn.setFont(new Font("Caladea Bold", Font.BOLD, 20));
		saveBttn.setForeground(Color.white);
		saveBttn.setText("Guardar"); 

		saveBttn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(!form.ingredientFormEmptyFields()) {
					System.out.println(form.getTopLeftCmbBx());
					image = new ImageIcon(AuthView.class.getResource("/images/checkCircle.png")).getImage().getScaledInstance(45, 45, Image.SCALE_SMOOTH); 
	   				imageIcon = new ImageIcon(image);
	   				
					message = type.equals("platillo")?type+" actualizado correctamente." : type+" actualizado correctamente.";
					JOptionPane.showMessageDialog(null, message, "Acción exitosa", JOptionPane.INFORMATION_MESSAGE, imageIcon); 
					
					frame.dispose();
					dc.dishes();
				}
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
		
		RoundButton nextBttn = new RoundButton(30);
		nextBttn.setBackground(Color.decode("#244E23"));
		nextBttn.setFont(new Font("Caladea Bold", Font.BOLD, 20));
		nextBttn.setForeground(Color.white);
		nextBttn.setText("Siguiente");
		actionPnl.add(nextBttn); 
		
		nextBttn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JPanel formPanel2 = form.createIngredientsForm();
				formPanel2.setBorder(BorderFactory.createEmptyBorder(40, 0, 40, 0));
				
				if(!form.dishFormEmptyFields()) {
					mainPnl.remove(formPanel);
					actionPnl.remove(nextBttn);
					
					mainPnl.add(formPanel2, BorderLayout.CENTER);
					actionPnl.add(saveBttn);
					
					frame.repaint();
					frame.revalidate();
				}
			}
		});
		
		nextBttn.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent evt) {
				nextBttn.setBackground(Color.decode("#3C7E3A"));
		    }

		    public void mouseExited(MouseEvent evt) {
		    	nextBttn.setBackground(Color.decode("#244E23"));
		    }
		});
		
		frame.setVisible(true);
	}
}