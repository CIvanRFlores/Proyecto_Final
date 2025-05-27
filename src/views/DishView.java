package views;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

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
	
	public DishView(String title, int frameWidth, int frameHeight) {
		frame = new JFrame(); 
		imageIcon = new ImageIcon(ClientView.class.getResource("/images/elManglarLogo.png")); //icono de la ventana
		frame.setIconImage(imageIcon.getImage());
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
		frame.setSize(frameWidth, frameHeight); 
		frame.setLocationRelativeTo(null); //colocar la ventana en el centro de la pantalla
		frame.setResizable(true); 
		frame.setMinimumSize(new Dimension(350, 350));
		frame.setTitle(title); 
		
		SideBarPanel sideBar = new SideBarPanel(frame);
		JPanel buttonPanel = sideBar.createSidePanel();
		frame.add(buttonPanel, BorderLayout.WEST);
	}
	
	public void dishes() {
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
		actionPnl.setLayout(new GridLayout(1, 4, 20, 0));
		actionPnl.setOpaque(false); 
		headerPnl.add(actionPnl);
		
		RoundPanel searchBarPnl = new RoundPanel(30);  
		searchBarPnl.setBackground(Color.white);
		searchBarPnl.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5)); 
		searchBarPnl.setForeground(Color.decode("#244E23")); 
		searchBarPnl.setLayout(new BorderLayout(15, 0));
		searchBarPnl.setPreferredSize(new Dimension(414, 30));
		actionPnl.add(searchBarPnl);
		
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
					frame.remove(mainPnl);
					newDish("platillo");
					frame.repaint();
					frame.revalidate();
				} 
				else if(opc==1){
					frame.remove(mainPnl);
					newDish("bebida");
					frame.repaint();
					frame.revalidate();
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
			DishCard dishCard = new DishCard(30, DishView.class.getResource("/images/shrimps.png"), "Camarones (sin cabeza)", frame);
			RoundPanel dish = dishCard.createCard();
			
			dish.getComponent(1).addMouseListener(new MouseAdapter() {
				public void mouseClicked(MouseEvent evt) {
					frame.remove(mainPnl);
					dishPage("platillo"); 
					frame.repaint();
					frame.revalidate();
				}
			});
			
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
		mainPnl = new JPanel();
		mainPnl.setBackground(Color.white);
		mainPnl.setBorder(BorderFactory.createEmptyBorder(30, 45, 30, 45)); 
		mainPnl.setLayout(new BorderLayout());
		frame.add(mainPnl, BorderLayout.CENTER);
		
		JPanel headerPnl = new JPanel();
		headerPnl.setLayout(new GridLayout(2, 1, 0, 15));
		headerPnl.setOpaque(false); 
		mainPnl.add(headerPnl, BorderLayout.NORTH);
		
		JLabel dishesLbl = new JLabel(type.equals("platillo")?"Nuevo " + type : "Nueva " + type);
		dishesLbl.setFont(new Font("Caladea Bold", Font.BOLD, 36));
		dishesLbl.setForeground(Color.decode("#244E23")); 
		dishesLbl.setHorizontalAlignment(JLabel.LEFT); 
		dishesLbl.setHorizontalAlignment(SwingConstants.LEFT); 
		headerPnl.add(dishesLbl);
				
		JPanel actionPnl = new JPanel();
		actionPnl.setLayout(new GridLayout(1, 4, 20, 0));
		actionPnl.setOpaque(false); 
		headerPnl.add(actionPnl);
		
		actionPnl.add(Box.createHorizontalStrut(0));
		
		RoundButton cancelBttn = new RoundButton(30);
		cancelBttn.setBackground(Color.decode("#EF2D2D"));
		cancelBttn.setFont(new Font("Caladea Bold", Font.BOLD, 20));
		cancelBttn.setForeground(Color.white);
		cancelBttn.setText("Cancelar");
		actionPnl.add(cancelBttn);
		
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
		actionPnl.add(saveBttn); 
		
		RoundButton nextBttn = new RoundButton(30);
		nextBttn.setBackground(Color.decode("#244E23"));
		nextBttn.setFont(new Font("Caladea Bold", Font.BOLD, 20));
		nextBttn.setForeground(Color.white);
		nextBttn.setText("Siguiente");
		actionPnl.add(nextBttn); 
		
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
	
	public void dishPage(String type) {
		mainPnl = new JPanel();
		mainPnl.setBackground(Color.white);
		mainPnl.setBorder(BorderFactory.createEmptyBorder(30, 45, 30, 45)); 
		mainPnl.setLayout(new BorderLayout());
		frame.add(mainPnl, BorderLayout.CENTER);
		
		JPanel headerPnl = new JPanel();
		headerPnl.setLayout(new GridLayout(2, 1, 0, 15));
		headerPnl.setOpaque(false); 
		mainPnl.add(headerPnl, BorderLayout.NORTH);
		
		JLabel dishesLbl = new JLabel("Platillo");
		dishesLbl.setFont(new Font("Caladea Bold", Font.BOLD, 36));
		dishesLbl.setForeground(Color.decode("#244E23")); 
		dishesLbl.setHorizontalAlignment(JLabel.LEFT); 
		dishesLbl.setHorizontalAlignment(SwingConstants.LEFT);
		headerPnl.add(dishesLbl);
		
		JPanel actionPnl = new JPanel();
		actionPnl.setLayout(new GridLayout(1, 4, 20, 0));
		actionPnl.setOpaque(false); 
		headerPnl.add(actionPnl);
		
		actionPnl.add(Box.createHorizontalStrut(0));
				
		RoundButton deleteBttn = new RoundButton(30);
		deleteBttn.setBackground(Color.decode("#EF2D2D"));
		deleteBttn.setFont(new Font("Caladea Bold", Font.BOLD, 20));
		deleteBttn.setForeground(Color.white);
		deleteBttn.setText("Eliminar");
		actionPnl.add(deleteBttn);
		
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
		actionPnl.add(editBttn); 
		
		editBttn.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent evt) {
				frame.remove(mainPnl);
				editDish(type);
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
		actionPnl.add(downloadBttn); 
		
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
	
	public void editDish(String type) {
		mainPnl = new JPanel();
		mainPnl.setBackground(Color.white);
		mainPnl.setBorder(BorderFactory.createEmptyBorder(30, 45, 30, 45)); 
		mainPnl.setLayout(new BorderLayout());
		frame.add(mainPnl, BorderLayout.CENTER);
		
		JPanel headerPnl = new JPanel();
		headerPnl.setLayout(new GridLayout(2, 1, 0, 15));
		headerPnl.setOpaque(false); 
		mainPnl.add(headerPnl, BorderLayout.NORTH);
		
		JLabel dishesLbl = new JLabel("Editar platillo");
		dishesLbl.setFont(new Font("Caladea Bold", Font.BOLD, 36));
		dishesLbl.setForeground(Color.decode("#244E23")); 
		dishesLbl.setHorizontalAlignment(JLabel.LEFT);
		dishesLbl.setHorizontalAlignment(SwingConstants.LEFT); 
		headerPnl.add(dishesLbl);
		
		JPanel actionPnl = new JPanel();
		actionPnl.setLayout(new GridLayout(1, 4, 20, 0));
		actionPnl.setOpaque(false); 
		headerPnl.add(actionPnl);
				
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
				frame.remove(mainPnl);
				dishPage(type);
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
		actionPnl.add(saveBttn); 
		
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