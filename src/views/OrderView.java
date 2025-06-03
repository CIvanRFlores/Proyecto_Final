package views;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import customClasses.*;

public class OrderView {

	public JFrame frame;
	public JPanel mainPnl;
	public Image image;
	public ImageIcon imageIcon;
	public String message;
	public int relativeXSize;
	public int relativeYSize;
	
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
		
		SideBarPanel sideBar = new SideBarPanel(frame);
		JPanel buttonPanel = sideBar.createSidePanel();
		frame.add(buttonPanel, BorderLayout.WEST);
	}
	
	public void orders() {
		mainPnl = new JPanel();
		mainPnl.setBackground(Color.white);
		mainPnl.setBorder(BorderFactory.createEmptyBorder(30, 45, 30, 45)); 
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
		actionPnl.setLayout(new GridLayout(1, 3, 20, 0));
		actionPnl.setOpaque(false); 
		headerPnl.add(actionPnl, BorderLayout.EAST);
		
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
				
		
		RoundButton newOrderBttn = new RoundButton(30, Color.decode("#244E23"));
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
		
		OrderCard orderCard = new OrderCard(30, "1", "$1200.00 MXN", "Luis", 1, 25+"min", frame, mainPnl);
		RoundPanel order = orderCard.createCard();
		ordersPnl.add(order);
		
		frame.setVisible(true);
	}
	
	public void newOrder() {
		mainPnl = new JPanel();
		mainPnl.setBackground(Color.white);
		mainPnl.setBorder(BorderFactory.createEmptyBorder(30, 45, 30, 45)); 
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
		actionPnl.setLayout(new GridLayout(1, 3, 20, 0));
		actionPnl.setOpaque(false);
		headerPnl.add(actionPnl, BorderLayout.EAST);
		
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
		
		RoundButton ongoingOrderBttn = new RoundButton(30, Color.decode("#244E23"));
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