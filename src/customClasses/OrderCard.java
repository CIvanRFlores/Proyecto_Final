package customClasses;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

import javax.swing.*;

import controllers.OrderController;

public class OrderCard {
	
	JFrame frame;
	JPanel mainPnl;
	int radius;
	String table;
	String amount;
	String clientName;
	int orderType;
	String orderTime;
	Image image;
	ImageIcon imageIcon;
	int relativeXSize;
	int relativeYSize;
	String message;
	
	OrderController oc;
	JPanel centerDishesPnl;
	String dishName;
	int quantity;
	double price;
	OptionPaneButton loadingOptPn;

	public OrderCard(int radius, String table, String amount, String clientName, int orderType, String orderTime, JFrame frame, JPanel mainPnl) {
		this.frame = frame;
		this.mainPnl= mainPnl;
		this.radius = radius;
		this.table = table;
		this.amount = amount;
		this.clientName = clientName;
		this.orderType = orderType;
		this.orderTime = orderTime;
	}
	
	public OrderCard(JFrame frame, JPanel centerDishesPnl, String dishName, double price) {
		this.frame = frame;
		this.centerDishesPnl = centerDishesPnl;
		this.dishName = dishName;
		this.price = price;
		quantity = 1;
	}
	
	
	public RoundPanel createCard() {
		oc = new OrderController(frame.getTitle(), frame.getWidth(), frame.getHeight());
		
		RoundPanel cardPnl = new RoundPanel(radius);  
		cardPnl.setBackground(Color.white);
		cardPnl.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); 
		cardPnl.setForeground(Color.decode("#244E23"));
		cardPnl.setLayout(new BorderLayout(0, 10));
		
		JPanel infoPnl = new JPanel();
		infoPnl.setLayout(new GridLayout(2, 2, 10, 10));
		infoPnl.setOpaque(false);
		cardPnl.add(infoPnl, BorderLayout.NORTH);
		
		JLabel tableLbl = new JLabel("Mesa " + table);
		tableLbl.setFont(new Font("Caladea Bold", Font.BOLD, 20));
		tableLbl.setForeground(Color.decode("#244E23")); 
		tableLbl.setHorizontalAlignment(JLabel.LEFT); 
		tableLbl.setHorizontalAlignment(SwingConstants.LEFT); 
		infoPnl.add(tableLbl);
		
		JLabel amountLbl = new JLabel("<html><u>" + amount + "</u></html>");
		amountLbl.setFont(new Font("Caladea Bold", Font.BOLD, 12));
		amountLbl.setForeground(Color.decode("#244E23")); 
		amountLbl.setHorizontalAlignment(JLabel.RIGHT); 
		amountLbl.setHorizontalAlignment(SwingConstants.RIGHT); 
		infoPnl.add(amountLbl);
		
		JLabel clientLbl = new JLabel("Cliente:  " + clientName);
		clientLbl.setFont(new Font("Caladea Regular", Font.BOLD, 15));
		clientLbl.setForeground(Color.decode("#244E23")); 
		clientLbl.setHorizontalAlignment(JLabel.LEFT);
		clientLbl.setHorizontalAlignment(SwingConstants.LEFT); 
		infoPnl.add(clientLbl);
		

		JPanel actionPnl = new JPanel();
		actionPnl.setLayout(new GridLayout(1, 3, 10, 0));
		actionPnl.setOpaque(false);
		cardPnl.add(actionPnl, BorderLayout.CENTER);
		
		RoundButton cancelBttn = new RoundButton(radius/2);
		cancelBttn.setBackground(Color.decode("#EF2D2D"));
		cancelBttn.setFont(new Font("Caladea Regular", Font.PLAIN, 12));
		cancelBttn.setForeground(Color.white);
		cancelBttn.setText("Cancelar");
		actionPnl.add(cancelBttn);
		
		cancelBttn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				OptionPaneButton option = new OptionPaneButton("Eliminar pedido", "Esta acción no se puede deshacer.", "Eliminar");
				int opt = option.destructiveOptionPane();
				
				if(opt==1) {
					loadingOptPn = new OptionPaneButton("Cargando información...", "Por favor espere.");
					loadingOptPn.loadingOptionPane(frame, 3000);
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
		
		
		RoundButton payBttn = new RoundButton(radius/2);
		payBttn.setBackground(Color.decode("#555BF6"));
		payBttn.setFont(new Font("Caladea Regular", Font.PLAIN, 12));
		payBttn.setForeground(Color.white);
		payBttn.setText("Pagar");
		actionPnl.add(payBttn);
		
		payBttn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				OptionPaneButton option = new OptionPaneButton("Pago exitoso", "¿Desea descargar detalles de la órden?");
				int opt = option.downloadOptionPane();
				
				if(opt==1) {
					loadingOptPn = new OptionPaneButton("Cargando información...", "Por favor espere.");
					loadingOptPn.loadingOptionPane(frame, 3000);
				}
			}
		});
		
		payBttn.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent evt) {
				payBttn.setBackground(Color.decode("#767AEC"));
		    }

		    public void mouseExited(MouseEvent evt) {
		    	payBttn.setBackground(Color.decode("#555BF6"));
		    }
		});
		
		
		image = new ImageIcon(OrderCard.class.getResource("/images/editOrder.png")).getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH);
		imageIcon = new ImageIcon(image);
		RoundButton editBttn = new RoundButton(30, imageIcon);
		editBttn.setBackground(Color.white);
		cardPnl.add(editBttn, BorderLayout.EAST);
		
		editBttn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				loadingOptPn = new OptionPaneButton("Cargando ventana...", "Por favor espere.");
				loadingOptPn.loadingOptionPane(frame, 3000); 
				frame.dispose();
				oc.editOrder(1);
			}
		});
		
		editBttn.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent evt) {
				image = new ImageIcon(OrderCard.class.getResource("/images/editOrderClick.png")).getImage().getScaledInstance(relativeXSize, relativeXSize, Image.SCALE_SMOOTH);
				imageIcon = new ImageIcon(image);
				editBttn.setIcon(imageIcon);
		    }

		    public void mouseExited(MouseEvent evt) {
		    	image = new ImageIcon(OrderCard.class.getResource("/images/editOrder.png")).getImage().getScaledInstance(relativeXSize, relativeXSize, Image.SCALE_SMOOTH);
				imageIcon = new ImageIcon(image);
				editBttn.setIcon(imageIcon);
		    }
		});
		
		
		Color text = null;
		Color footer = null;
		String type = "";
		
		if(this.orderType==1) {
			text = Color.decode("#555BF6");
			footer = Color.decode("#BCF0FB");
			image = new ImageIcon(OrderCard.class.getResource("/images/blueClock.png")).getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH);
			imageIcon = new ImageIcon(image);
			type = "Comer aquí";
		}else {
			text = Color.decode("#EF2D2D");
			footer = Color.decode("#FBBCBC");
			image = new ImageIcon(OrderCard.class.getResource("/images/redClock.png")).getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH);
			imageIcon = new ImageIcon(image);
			type = "Llevar";
		}
		
		
		RoundPanel footerPnl = new RoundPanel(radius/2);
		footerPnl.setBackground(footer);
		footerPnl.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5)); 
		footerPnl.setForeground(footer);
		footerPnl.setLayout(new GridLayout(1, 3, 10, 0));
		cardPnl.add(footerPnl, BorderLayout.SOUTH);
		
		JLabel orderTypeLbl = new JLabel(type);
		orderTypeLbl.setFont(new Font("Caladea Regular", Font.PLAIN, 12));
		orderTypeLbl.setForeground(text); 
		orderTypeLbl.setHorizontalAlignment(JLabel.LEFT); 
		orderTypeLbl.setHorizontalAlignment(SwingConstants.LEFT); 
		footerPnl.add(orderTypeLbl);
		
		JLabel clockIcon = new JLabel(imageIcon);
		footerPnl.add(clockIcon);
		
		JLabel orderTimeLbl = new JLabel(orderTime);
		orderTimeLbl.setFont(new Font("Caladea Regular", Font.PLAIN, 12));
		orderTimeLbl.setForeground(text);
		orderTimeLbl.setHorizontalAlignment(JLabel.RIGHT); 
		orderTimeLbl.setHorizontalAlignment(SwingConstants.RIGHT); 
		footerPnl.add(orderTimeLbl);
		
		/**cuando la ventana es redimensionada, los elementos dentro de ella cambian de tamaño**/
		frame.addComponentListener(new ComponentAdapter() {
            public void componentResized(ComponentEvent e) {
            	tableLbl.setFont(new Font("Caladea Bold", Font.BOLD, (int) (frame.getWidth()*0.02)));
            	amountLbl.setFont(new Font("Caladea Bold", Font.BOLD, (int) (frame.getWidth()*0.012)));
            	clientLbl.setFont(new Font("Caladea Regular", Font.BOLD, (int) (frame.getWidth()*0.015)));
            	
            	cancelBttn.setFont(new Font("Caladea Regular", Font.PLAIN, (int) (frame.getWidth()*0.012)));
            	payBttn.setFont(new Font("Caladea Regular", Font.PLAIN, (int) (frame.getWidth()*0.012)));
            	
            	relativeXSize = (int) (frame.getWidth()*0.02);
            	image = new ImageIcon(OrderCard.class.getResource("/images/editOrder.png")).getImage().getScaledInstance(relativeXSize, relativeXSize, Image.SCALE_SMOOTH);
        		imageIcon = new ImageIcon(image);
       			editBttn.setIcon(imageIcon);  
       		
    			if(orderType==1) {
    				image = new ImageIcon(OrderCard.class.getResource("/images/blueClock.png")).getImage().getScaledInstance(relativeXSize, relativeXSize, Image.SCALE_SMOOTH);
    				imageIcon = new ImageIcon(image);
    				clockIcon.setIcon(imageIcon);
    			}else {
    				image = new ImageIcon(OrderCard.class.getResource("/images/redClock.png")).getImage().getScaledInstance(relativeXSize, relativeXSize, Image.SCALE_SMOOTH);
    				imageIcon = new ImageIcon(image);
    				clockIcon.setIcon(imageIcon);
    			}
    			
    			orderTypeLbl.setFont(new Font("Caladea Regular", Font.BOLD, (int) (frame.getWidth()*0.012)));
    			orderTimeLbl.setFont(new Font("Caladea Regular", Font.BOLD, (int) (frame.getWidth()*0.012)));
       			
       			frame.repaint();
            }
        });
		
		return cardPnl; 
	}
	
	public JPanel createDescriptiveCard(OrderTabPanel orderTab, ArrayList<JPanel> dishesArray) {
		
		JPanel backgroundPnl = new JPanel();
		backgroundPnl.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); 
		backgroundPnl.setLayout(new GridLayout(1, 4, 10, 0));
		backgroundPnl.setOpaque(false);
		backgroundPnl.setPreferredSize(new Dimension(centerDishesPnl.getWidth(), (int) (frame.getHeight()*0.07)));
		
		JTextArea dishNameTxtArea = new JTextArea(dishName);
		dishNameTxtArea.setBackground(Color.white);
		dishNameTxtArea.setForeground(Color.decode("#244E23")); 
		dishNameTxtArea.setEnabled(false);
		dishNameTxtArea.setDisabledTextColor(Color.decode("#244E23"));
		dishNameTxtArea.setFont(new Font("Caladea Regular", Font.PLAIN, 14));
		dishNameTxtArea.setLineWrap(true);
		dishNameTxtArea.setWrapStyleWord(true);
		dishNameTxtArea.setOpaque(true);
		backgroundPnl.add(dishNameTxtArea);
		
		RoundPanel quantityPnl = new RoundPanel(30);  
		quantityPnl.setBackground(Color.decode("#555BF6"));
		quantityPnl.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5)); 
		quantityPnl.setForeground(Color.decode("#555BF6")); 
		quantityPnl.setLayout(new GridLayout(1, 3, 5, 0));
		backgroundPnl.add(quantityPnl);
		
		
		image = new ImageIcon(OrderCard.class.getResource("/images/removeOrderDish.png")).getImage().getScaledInstance(18, 18, Image.SCALE_SMOOTH);
		imageIcon = new ImageIcon(image);
		RoundButton removeBttn = new RoundButton(20); 
		removeBttn.setBackground(Color.decode("#555BF6"));
		removeBttn.setIcon(imageIcon);
		quantityPnl.add(removeBttn);
		
		JLabel quantityLbl = new JLabel(quantity+"");
		quantityLbl.setFont(new Font("Caladea Bold", Font.BOLD, 14));
		quantityLbl.setForeground(Color.white); 
		quantityLbl.setHorizontalAlignment(SwingConstants.CENTER); 
		quantityPnl.add(quantityLbl);
		
		image = new ImageIcon(OrderCard.class.getResource("/images/addOrderDish.png")).getImage().getScaledInstance(18, 18, Image.SCALE_SMOOTH);
		imageIcon = new ImageIcon(image);
		RoundButton addBttn = new RoundButton(20); 
		addBttn.setBackground(Color.decode("#555BF6"));
		addBttn.setIcon(imageIcon);
		quantityPnl.add(addBttn);
		
		removeBttn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(quantity>1) {
					quantity--;
					quantityLbl.setText(quantity+"");
					
					orderTab.setSubtotalValueLbl("$"+(price*quantity)+"0 MXN");
					orderTab.setTotalValueLbl("<html><u>"+orderTab.getSubtotalValueLbl()+"</u></html>");
					
					frame.repaint();
					frame.revalidate();
				}
			}
		});	
		
		addBttn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				quantity++;
				quantityLbl.setText(quantity+"");
				
				orderTab.setSubtotalValueLbl("$"+(price*quantity)+"0 MXN");
				orderTab.setTotalValueLbl("<html><u>"+orderTab.getSubtotalValueLbl()+"</u></html>");
				
				frame.repaint();
				frame.revalidate();
			}
		});	
		
		
		JLabel priceLbl = new JLabel("$"+price+" MXN");
		priceLbl.setFont(new Font("Caladea Regular", Font.PLAIN, 14));
		priceLbl.setForeground(Color.decode("#244E23")); 
		priceLbl.setHorizontalAlignment(SwingConstants.RIGHT); 
		backgroundPnl.add(priceLbl);
		
		
		image = new ImageIcon(OrderCard.class.getResource("/images/deleteOrderDish.png")).getImage().getScaledInstance(18, 18, Image.SCALE_SMOOTH);
		imageIcon = new ImageIcon(image);
		RoundButton deleteSelectionBttn = new RoundButton(20); 
		deleteSelectionBttn.setBackground(Color.white);
		deleteSelectionBttn.setIcon(imageIcon);
		backgroundPnl.add(deleteSelectionBttn);
		
		deleteSelectionBttn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				OptionPaneButton optionPane = new OptionPaneButton("Eliminar selección", "Esta acción no se puede deshacer.", "Eliminar");
				int opt = optionPane.destructiveOptionPane();
				
				if(opt==1) {
					dishesArray.remove(backgroundPnl);
					centerDishesPnl.remove(backgroundPnl);
					
					orderTab.setSubtotalValueLbl("$00.00 MXN");
					orderTab.setDiscountValueLbl("$00.00 MXN");
					orderTab.setTotalValueLbl("<html><u>"+orderTab.getSubtotalValueLbl()+"</u></html>");
					
					System.out.println(dishesArray.size());
					
					frame.repaint();
					frame.revalidate();
				}
			}
		});	
		
		
		/**cuando la ventana es redimensionada, los elementos dentro de ella cambian de tamaño**/
		frame.addComponentListener(new ComponentAdapter() {
            public void componentResized(ComponentEvent e) {
            	backgroundPnl.setPreferredSize(new Dimension(centerDishesPnl.getWidth(), (int) (frame.getHeight()*0.07)));
            	
            	dishNameTxtArea.setFont(new Font("Caladea Regular", Font.PLAIN, (int) (frame.getWidth()*0.014)));
            	quantityLbl.setFont(new Font("Caladea Bold", Font.BOLD, (int) (frame.getWidth()*0.014)));
            	priceLbl.setFont(new Font("Caladea Regular", Font.PLAIN, (int) (frame.getWidth()*0.014)));
            	
            	relativeXSize = (int) (frame.getWidth()*0.018);
            	image = new ImageIcon(OrderCard.class.getResource("/images/removeOrderDish.png")).getImage().getScaledInstance(relativeXSize, relativeXSize, Image.SCALE_SMOOTH);
        		imageIcon = new ImageIcon(image);
       			removeBttn.setIcon(imageIcon);  
       			
       			image = new ImageIcon(OrderCard.class.getResource("/images/addOrderDish.png")).getImage().getScaledInstance(relativeXSize, relativeXSize, Image.SCALE_SMOOTH);
        		imageIcon = new ImageIcon(image);
       			addBttn.setIcon(imageIcon);  
       			
       			image = new ImageIcon(OrderCard.class.getResource("/images/deleteOrderDish.png")).getImage().getScaledInstance(relativeXSize, relativeXSize, Image.SCALE_SMOOTH);
        		imageIcon = new ImageIcon(image);
        		deleteSelectionBttn.setIcon(imageIcon);  
        		
        		relativeXSize = (int) (frame.getWidth()*0.01);
        		quantityPnl.setBorder(BorderFactory.createEmptyBorder(relativeXSize/2, relativeXSize/2, relativeXSize/2, relativeXSize/2));
        		backgroundPnl.setBorder(BorderFactory.createEmptyBorder(relativeXSize, relativeXSize, relativeXSize, relativeXSize));
        		
       			frame.repaint();
            }
        });
		
		return backgroundPnl;
	}

	
	public String getTable() {
		return table;
	}

	public void setTable(String table) {
		this.table = table;
	}

	public String getClientName() {
		return clientName;
	}

	public void setClientName(String clientName) {
		this.clientName = clientName;
	}

	public int getOrderType() {
		return orderType;
	}

	public void setOrderType(int orderType) {
		this.orderType = orderType;
	}

	public String getOrderTime() {
		return orderTime;
	}

	public void setOrderTime(String orderTime) {
		this.orderTime = orderTime;
	}

	public String getDishName() {
		return dishName;
	}

	public void setDishName(String dishName) {
		this.dishName = dishName;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}
	
}
