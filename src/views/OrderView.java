package views;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

import javax.swing.*;

import controllers.OrderController;
import customClasses.*;

public class OrderView {

	JFrame frame;
	JPanel mainPnl;
	JPanel buttonPnl;
	SideBarPanel sideBar;
	Image image;
	ImageIcon imageIcon;
	
	ArrayList<RoundPanel> ordersArray;
	ArrayList<RoundPanel> dishesArray;
	
	String message;
	int relativeXSize;
	int relativeYSize;
	OrderController oc;
	OptionPaneButton optionPane;
	OptionPaneButton loadingOptPn;
	
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
		
		sideBar = new SideBarPanel(frame);
		buttonPnl = sideBar.createSidePanel();
		frame.add(buttonPnl, BorderLayout.WEST);
		
		buttonPnl.getComponent(2).setBackground(Color.decode("#3C7E3A"));
		sideBar.removeOrderListener();
		
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
	
	public void orders() {
		oc = new OrderController(frame.getTitle(), frame.getWidth(), frame.getHeight());
		
		mainPnl = new JPanel();
		mainPnl.setBackground(Color.white);
		mainPnl.setBorder(BorderFactory.createEmptyBorder(30, 45, 30, 45)); 
		mainPnl.setLayout(new BorderLayout());
		frame.add(mainPnl, BorderLayout.CENTER);
		
		JPanel headerPnl = new JPanel();
		headerPnl.setLayout(new BorderLayout(20, 15));
		headerPnl.setOpaque(false); 
		mainPnl.add(headerPnl, BorderLayout.NORTH);
		
		JLabel ordersLbl = new JLabel("Órdenes");
		ordersLbl.setFont(new Font("Caladea Bold", Font.BOLD, 36));
		ordersLbl.setForeground(Color.decode("#244E23")); 
		ordersLbl.setHorizontalAlignment(JLabel.LEFT); 
		ordersLbl.setHorizontalAlignment(SwingConstants.LEFT); 
		headerPnl.add(ordersLbl, BorderLayout.NORTH);
		
		RoundPanel searchBarPnl = new RoundPanel(30);  
		searchBarPnl.setBackground(Color.white);
		searchBarPnl.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));  
		searchBarPnl.setForeground(Color.decode("#244E23")); 
		searchBarPnl.setLayout(new BorderLayout(15, 0));
		searchBarPnl.setPreferredSize(new Dimension(300, 30));
		
		headerPnl.add(searchBarPnl, BorderLayout.CENTER);
		
		image = new ImageIcon(OrderView.class.getResource("/images/magnifyingGlass.png")).getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH);
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
				if(searchTxtFld.getText().equals("")) {
					optionPane = new OptionPaneButton("Orden", "Campo sin completar.");
					optionPane.errorOptionPane();
				}
				else{
					loadingOptPn = new OptionPaneButton("Cargando información...", "Por favor espere.");
					loadingOptPn.loadingOptionPane(frame, 3000);
					searchOrder();
				}
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
		
		RoundButton newOrderBttn = new RoundButton(30);
		newOrderBttn.setBackground(Color.white);
		newOrderBttn.setButtonBorder(Color.decode("#244E23"));
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
				loadingOptPn = new OptionPaneButton("Cargando ventana...", "Por favor espere.");
				loadingOptPn.loadingOptionPane(frame, 3000);
				frame.dispose();
				oc.newOrder();
			}
		});
		
		newOrderBttn.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent evt) {
				newOrderBttn.setForeground(Color.decode("#3C7E3A"));
				newOrderBttn.setButtonBorder(Color.decode("#3C7E3A"));
		    }

		    public void mouseExited(MouseEvent evt) {
				newOrderBttn.setForeground(Color.decode("#244E23"));
				newOrderBttn.setButtonBorder(Color.decode("#244E23"));
		    }
		});
		
		
		JPanel ordersPnl = new JPanel();
		ordersPnl.setBorder(BorderFactory.createEmptyBorder(0, 0, 40, 0));
		ordersPnl.setLayout(new GridBagLayout());	
		ordersPnl.setOpaque(false); 
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.BOTH;	
		
		ordersArray = new ArrayList<>();
        for(int i=1; i<=15; i++) {
        	
        	//simular que las ordenes son de tipos diferente
        	if(i%2==0) {
        		OrderCard orderCard = new OrderCard(30, i+"", "$1200.00 MXN", "Luis", 2, 25+"min", frame, mainPnl);
        		RoundPanel order = orderCard.createCard();
        		ordersArray.add(order);
        	}else {
        		OrderCard orderCard = new OrderCard(30, i+"", "$1200.00 MXN", "Luis", 1, 25+"min", frame, mainPnl);
        		RoundPanel order = orderCard.createCard();
        		ordersArray.add(order);
        	}
        }
		
		for(int i=0; i<ordersArray.size(); i++) {
            int row = i/2; 
            int col = i%2;  

            gbc.gridx = col;
            gbc.gridy = row;
            
            gbc.insets = new Insets(30, 40, 30, 40);

            ordersPnl.add(ordersArray.get(i), gbc);
        }
		
		ColoredScrollPaneBar coloredScrollPane = new ColoredScrollPaneBar(ordersPnl, Color.decode("#244E23"));
		JScrollPane scrollPane = coloredScrollPane.createScrollPane();
		scrollPane.setBorder(BorderFactory.createEmptyBorder(30, 0, 0, 0));
		mainPnl.add(scrollPane, BorderLayout.CENTER);
		
		frame.setVisible(true);
	}
	
	public void searchOrder() {
		OptionPaneButton optionPane = new OptionPaneButton("Orden no encontrada", "No se ha encontrado ninguna orden.");
		optionPane.warningOptionPane();
	}
	
	public void newOrder() {
		oc = new OrderController(frame.getTitle(), frame.getWidth(), frame.getHeight());
		
		frame.remove(buttonPnl);
		buttonPnl = sideBar.createReducedSidePanel();
		buttonPnl.getComponent(2).setBackground(Color.decode("#3C7E3A"));
		sideBar.removeOrderListener();
		frame.add(buttonPnl, BorderLayout.WEST);
		
		OrderTabPanel orderTab = new OrderTabPanel(frame, 0);
		JPanel orderTabPnl = orderTab.createNewOrderTab();
		frame.add(orderTabPnl, BorderLayout.EAST);
		
		mainPnl = new JPanel();
		mainPnl.setBackground(Color.white);
		mainPnl.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30)); 
		mainPnl.setLayout(new BorderLayout());
		frame.add(mainPnl, BorderLayout.CENTER);
		
		JPanel headerPnl = new JPanel();
		headerPnl.setLayout(new BorderLayout(20, 15));
		headerPnl.setOpaque(false); 
		mainPnl.add(headerPnl, BorderLayout.NORTH);
		
		JLabel ordersLbl = new JLabel("Ordenes");
		ordersLbl.setFont(new Font("Caladea Bold", Font.BOLD, 36));
		ordersLbl.setForeground(Color.decode("#244E23"));
		ordersLbl.setHorizontalAlignment(JLabel.LEFT); 
		ordersLbl.setHorizontalAlignment(SwingConstants.LEFT); 
		headerPnl.add(ordersLbl, BorderLayout.NORTH);
		
		RoundPanel searchBarPnl = new RoundPanel(30);  
		searchBarPnl.setBackground(Color.white);
		searchBarPnl.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));  
		searchBarPnl.setForeground(Color.decode("#244E23")); 
		searchBarPnl.setLayout(new BorderLayout(15, 0));
		searchBarPnl.setPreferredSize(new Dimension(300, 30));
		headerPnl.add(searchBarPnl, BorderLayout.CENTER);
		
		image = new ImageIcon(OrderView.class.getResource("/images/magnifyingGlass.png")).getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH);
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
		actionPnl.setLayout(new GridLayout(1, 1));
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
				if(searchTxtFld.getText().equals("")) {
					optionPane = new OptionPaneButton("Orden", "Campo sin completar.");
					optionPane.errorOptionPane();
				}
				else{
					loadingOptPn = new OptionPaneButton("Cargando información...", "Por favor espere.");
					loadingOptPn.loadingOptionPane(frame, 3000);
					searchOrder();
				}
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
		
		
		JPanel dishesPnl = new JPanel();
		dishesPnl.setBackground(Color.white);
		dishesPnl.setLayout(new GridBagLayout());	
		
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.BOTH;
		gbc.insets = new Insets(15, 15, 15, 15);
		
		dishesArray = new ArrayList<>();
        for(int i=1; i<=20; i++) {
        	DishCard dishCard = new DishCard(30, OrderView.class.getResource("/images/shrimps.png"), "platillo", "Camarones (sin cabeza)", frame);
			RoundPanel dish = dishCard.createAddableCard(orderTab);
			dishesArray.add(dish);
        }
		
		for(int i=0; i<dishesArray.size(); i++) {
            int row = i/3; 
            int col = i%3;  

            gbc.gridx = col;
            gbc.gridy = row;

            dishesPnl.add(dishesArray.get(i), gbc);
        }
	
		ColoredScrollPaneBar coloredScrollPane = new ColoredScrollPaneBar(dishesPnl, Color.decode("#244E23"));
		JScrollPane scrollPane = coloredScrollPane.createScrollPane();
		scrollPane.setBorder(BorderFactory.createEmptyBorder(30, 0, 0, 0));
		mainPnl.add(scrollPane, BorderLayout.CENTER);
			
		
		frame.setVisible(true);
	}
	
	public void editOrder(int editWindow) {
		oc = new OrderController(frame.getTitle(), frame.getWidth(), frame.getHeight());
		
		frame.remove(buttonPnl);
		buttonPnl = sideBar.createReducedSidePanel();
		buttonPnl.getComponent(2).setBackground(Color.decode("#3C7E3A"));
		sideBar.removeOrderListener();
		frame.add(buttonPnl, BorderLayout.WEST);
		
		OrderTabPanel orderTab = new OrderTabPanel(frame, editWindow);
		JPanel orderTabPnl = orderTab.createNewOrderTab();
		frame.add(orderTabPnl, BorderLayout.EAST);
		
		mainPnl = new JPanel();
		mainPnl.setBackground(Color.white);
		mainPnl.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30)); 
		mainPnl.setLayout(new BorderLayout());
		frame.add(mainPnl, BorderLayout.CENTER);
		
		JPanel headerPnl = new JPanel();
		headerPnl.setLayout(new BorderLayout(20, 15));
		headerPnl.setOpaque(false); 
		mainPnl.add(headerPnl, BorderLayout.NORTH);
		
		JLabel ordersLbl = new JLabel("Ordenes");
		ordersLbl.setFont(new Font("Caladea Bold", Font.BOLD, 36));
		ordersLbl.setForeground(Color.decode("#244E23"));
		ordersLbl.setHorizontalAlignment(JLabel.LEFT); 
		ordersLbl.setHorizontalAlignment(SwingConstants.LEFT); 
		headerPnl.add(ordersLbl, BorderLayout.NORTH);
		
		RoundPanel searchBarPnl = new RoundPanel(30);  
		searchBarPnl.setBackground(Color.white);
		searchBarPnl.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));  
		searchBarPnl.setForeground(Color.decode("#244E23")); 
		searchBarPnl.setLayout(new BorderLayout(15, 0));
		searchBarPnl.setPreferredSize(new Dimension(300, 30));
		headerPnl.add(searchBarPnl, BorderLayout.CENTER);
		
		image = new ImageIcon(OrderView.class.getResource("/images/magnifyingGlass.png")).getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH);
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
		actionPnl.setLayout(new GridLayout(1, 1));
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
				if(searchTxtFld.getText().equals("")) {
					optionPane = new OptionPaneButton("Orden", "Campo sin completar.");
					optionPane.errorOptionPane();
				}
				else{
					loadingOptPn = new OptionPaneButton("Cargando información...", "Por favor espere.");
					loadingOptPn.loadingOptionPane(frame, 3000);
					searchOrder();
				}
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
		
		
		JPanel dishesPnl = new JPanel();
		dishesPnl.setBackground(Color.white);
		dishesPnl.setLayout(new GridBagLayout());	
		
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.BOTH;
		gbc.insets = new Insets(15, 15, 15, 15);
		
		dishesArray = new ArrayList<>();
        for(int i=1; i<=20; i++) {
        	DishCard dishCard = new DishCard(30, OrderView.class.getResource("/images/shrimps.png"), "platillo", "Camarones (sin cabeza)", frame);
			RoundPanel dish = dishCard.createAddableCard(orderTab);
			dishesArray.add(dish);
        }
		
		for(int i=0; i<dishesArray.size(); i++) {
            int row = i/3; 
            int col = i%3;  

            gbc.gridx = col;
            gbc.gridy = row;

            dishesPnl.add(dishesArray.get(i), gbc);
        }
	
		ColoredScrollPaneBar coloredScrollPane = new ColoredScrollPaneBar(dishesPnl, Color.decode("#244E23"));
		JScrollPane scrollPane = coloredScrollPane.createScrollPane();
		scrollPane.setBorder(BorderFactory.createEmptyBorder(30, 0, 0, 0));
		mainPnl.add(scrollPane, BorderLayout.CENTER);
			
		
		frame.setVisible(true);
	}
	
}