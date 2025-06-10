package customClasses;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.plaf.basic.BasicComboBoxUI;

import controllers.OrderController;

public class OrderTabPanel {
	
	JFrame frame;
	Image image;
	ImageIcon imageIcon;
	String message;
	int relativeXSize;
	Font font;
	Border border;

	JComboBox<String> tableNumberCmbBx;
	JTextField nameTxtFld;
	JTextField messageTxtFld;
	JLabel subtotalLbl, subtotalValueLbl;
	JLabel discountLbl, discountValueLbl;
	JLabel totalLbl, totalValueLbl;
	JPanel centerDishesPnl;
	GridBagConstraints gbc;
	OrderCard orderCard;
	ArrayList<JPanel> dishesArray;
	
	OrderController oc;
	int orderType;
	OptionPaneButton optionPane;
	int opt;
	OptionPaneButton loadingOptPn;
	
	public OrderTabPanel(JFrame frame) {
		this.frame = frame;
		
		dishesArray = new ArrayList<>();
		gbc = new GridBagConstraints();
		centerDishesPnl = new JPanel();
	}
	
	public JPanel createNewOrderTab() {
		oc = new OrderController(frame.getTitle(), frame.getWidth(), frame.getHeight());
		
		JPanel backgroundPnl = new JPanel();
		backgroundPnl.setBackground(Color.white);
		backgroundPnl.setBorder(BorderFactory.createEmptyBorder(20, 10, 20, 20)); 
		backgroundPnl.setLayout(new BorderLayout());
		
		RoundPanel tabPnl = new RoundPanel(30);  
		tabPnl.setBackground(Color.white);
		tabPnl.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20)); 
		tabPnl.setForeground(Color.decode("#244E23")); 
		tabPnl.setLayout(new BorderLayout(0, 20));
		backgroundPnl.add(tabPnl);
		
		JPanel headerPnl = new JPanel();
		headerPnl.setBorder(BorderFactory.createEmptyBorder(20, 40, 0, 40)); 
		headerPnl.setLayout(new GridLayout(2, 2, 20, 10));
		headerPnl.setOpaque(false);
		tabPnl.add(headerPnl, BorderLayout.NORTH);
		
		RoundButton newOrderBttn = new RoundButton(30);
		newOrderBttn.setBackground(Color.decode("#3C7E3A"));
		newOrderBttn.setFont(new Font("Caladea Bold", Font.BOLD, 20));
		newOrderBttn.setForeground(Color.white);
		newOrderBttn.setText("Nuevo");
		headerPnl.add(newOrderBttn); 
		
		RoundButton ongoingOrderBttn = new RoundButton(30);
		ongoingOrderBttn.setBackground(Color.white);
		ongoingOrderBttn.setButtonBorder(Color.decode("#244E23"));
		ongoingOrderBttn.setFont(new Font("Caladea Bold", Font.BOLD, 20));
		ongoingOrderBttn.setForeground(Color.decode("#244E23"));
		ongoingOrderBttn.setText("En curso");
		headerPnl.add(ongoingOrderBttn); 

		ongoingOrderBttn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				loadingOptPn = new OptionPaneButton("Cargando ventana...", "Por favor espere.");
				loadingOptPn.loadingOptionPane(frame, 3000);
				frame.dispose();
				oc.orders();
			}
		});
	
		
		ongoingOrderBttn.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent evt) {
				ongoingOrderBttn.setForeground(Color.decode("#3C7E3A"));
				ongoingOrderBttn.setButtonBorder(Color.decode("#3C7E3A"));
		    }

		    public void mouseExited(MouseEvent evt) {
				ongoingOrderBttn.setForeground(Color.decode("#244E23"));
				ongoingOrderBttn.setButtonBorder(Color.decode("#244E23"));
		    }
		});
		
		RoundButton cancelBttn = new RoundButton(30);
		cancelBttn.setBackground(Color.decode("#EF2D2D"));
		cancelBttn.setFont(new Font("Caladea Bold", Font.PLAIN, 20));
		cancelBttn.setForeground(Color.white);
		cancelBttn.setText("Cancelar");
		headerPnl.add(cancelBttn);
		
		cancelBttn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				OptionPaneButton option = new OptionPaneButton("Eliminar pedido", "Esta acción no se puede deshacer.", "Eliminar");
				int opt = option.destructiveOptionPane();
				
				if(opt==1) {
					loadingOptPn = new OptionPaneButton("Cargando ventana...", "Por favor espere.");
					loadingOptPn.loadingOptionPane(frame, 3000);
					frame.dispose();
					oc.orders();
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
		
		RoundButton saveBttn = new RoundButton(30);
		saveBttn.setBackground(Color.decode("#555BF6"));
		saveBttn.setFont(new Font("Caladea Bold", Font.BOLD, 20));
		saveBttn.setForeground(Color.white);
		saveBttn.setText("Guardar");
		headerPnl.add(saveBttn); 

		saveBttn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				//if(!form.invFormEmptyFields()) {
					optionPane = new OptionPaneButton("Acción exitosa", "Orden creada correctamente.");
	   				optionPane.checkOptionPane();
					
	   				loadingOptPn = new OptionPaneButton("Cargando ventana...", "Por favor espere.");
					loadingOptPn.loadingOptionPane(frame, 3000);
					frame.dispose();
					oc.orders();
				//}
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
		
		
		JPanel centerPnl = new JPanel();
		centerPnl.setLayout(new BorderLayout(0, 20));
		centerPnl.setOpaque(false);
		tabPnl.add(centerPnl, BorderLayout.CENTER);
		
		
		JPanel centerHeaderPnl = new JPanel();
		centerHeaderPnl.setLayout(new GridLayout(4, 1, 0, 15));
		centerHeaderPnl.setOpaque(false);
		centerPnl.add(centerHeaderPnl, BorderLayout.NORTH);
		
		RoundPanel tableNumberPnl = new RoundPanel(30);  
		tableNumberPnl.setBackground(Color.white);
		tableNumberPnl.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10)); 
		tableNumberPnl.setForeground(Color.decode("#244E23")); 
		tableNumberPnl.setLayout(new BorderLayout());
		centerHeaderPnl.add(tableNumberPnl);
		
		String tableNumbers[] = {"Número de mesa", "1", "2", "3", "4", "5", "6"};
		
		tableNumberCmbBx = new JComboBox<>(tableNumbers);
		tableNumberCmbBx.setBorder(null); 
		tableNumberCmbBx.setBackground(Color.white);
		tableNumberCmbBx.setForeground(Color.decode("#244E23")); 
		tableNumberCmbBx.setFont(new Font("Caladea Bold", Font.BOLD, 14)); 
		tableNumberCmbBx.setOpaque(false);
		tableNumberCmbBx.setUI(new BasicComboBoxUI());
		tableNumberPnl.add(tableNumberCmbBx, BorderLayout.CENTER);
		
		tableNumberCmbBx.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				if(e.getStateChange()==ItemEvent.SELECTED) { //si se cambia el elemento seleccionado
					if(tableNumberCmbBx.getSelectedItem().toString().equals(tableNumbers[0])) {
						tableNumberCmbBx.setSelectedItem(tableNumbers[1]);
					}
				}
			}
			
		});
		
		RoundPanel nameTxtFldPnl = new RoundPanel(30);  
		nameTxtFldPnl.setBackground(Color.white);
		nameTxtFldPnl.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10)); 
		nameTxtFldPnl.setForeground(Color.decode("#244E23")); 
		nameTxtFldPnl.setLayout(new BorderLayout(5, 0));
		centerHeaderPnl.add(nameTxtFldPnl);
		
		JLabel nameLbl = new JLabel("Nombre:");
		nameLbl.setFont(new Font("Caladea Bold", Font.BOLD, 14));
		nameLbl.setForeground(Color.decode("#244E23")); 
		nameLbl.setHorizontalAlignment(SwingConstants.LEFT); 
		nameTxtFldPnl.add(nameLbl, BorderLayout.WEST);
		
		nameTxtFld = new JTextField();
		nameTxtFld.setBorder(null);
		nameTxtFld.setFont(new Font("Caladea Bold", Font.BOLD, 14)); 
		nameTxtFld.setForeground(Color.decode("#244E23"));
		nameTxtFld.setFocusable(true);
		nameTxtFld.setOpaque(false); 
		nameTxtFldPnl.add(nameTxtFld, BorderLayout.CENTER);
		
		JPanel buttonPnl = new JPanel();
		buttonPnl.setLayout(new GridLayout(1, 2, 10, 0));
		buttonPnl.setOpaque(false);
		centerHeaderPnl.add(buttonPnl);
		
		RoundButton eatHereBttn = new RoundButton(30);
		eatHereBttn.setBackground(Color.decode("#2EA623"));
		eatHereBttn.setFont(new Font("Caladea Bold", Font.BOLD, 16));
		eatHereBttn.setForeground(Color.white);
		eatHereBttn.setText("Comer aquí");
		buttonPnl.add(eatHereBttn); 
		
		RoundButton toGoBttn = new RoundButton(30);
		toGoBttn.setBackground(Color.decode("#D9D9D9"));
		toGoBttn.setFont(new Font("Caladea Bold", Font.BOLD, 16));
		toGoBttn.setForeground(Color.decode("#999999"));
		toGoBttn.setText("LLevar");
		buttonPnl.add(toGoBttn); 

		eatHereBttn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				orderType = 1;
				toGoBttn.setBackground(Color.decode("#D9D9D9"));
				toGoBttn.setForeground(Color.decode("#999999"));
				eatHereBttn.setBackground(Color.decode("#2EA623"));
				eatHereBttn.setForeground(Color.white);
			}
		});
		
		toGoBttn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				orderType = 2;
				eatHereBttn.setBackground(Color.decode("#D9D9D9"));
				eatHereBttn.setForeground(Color.decode("#999999"));
				toGoBttn.setBackground(Color.decode("#2EA623"));
				toGoBttn.setForeground(Color.white);
			}
		});
		
		eatHereBttn.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent evt) {
				if(eatHereBttn.getForeground().equals(Color.white)) {
					eatHereBttn.setBackground(Color.decode("#39C82C"));
					eatHereBttn.setForeground(Color.white);
				}else {
					eatHereBttn.setBackground(Color.decode("#EDEDED"));
					eatHereBttn.setForeground(Color.decode("#999999"));
				}
				
		    }

		    public void mouseExited(MouseEvent evt) {
		    	if(eatHereBttn.getForeground().equals(Color.white)) {
		    		eatHereBttn.setBackground(Color.decode("#2EA623"));
		    		eatHereBttn.setForeground(Color.white);
		    	}else {
		    		eatHereBttn.setBackground(Color.decode("#D9D9D9"));
					eatHereBttn.setForeground(Color.decode("#999999"));
		    	}
		    	
		    }
		});
		
		toGoBttn.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent evt) {
				if(toGoBttn.getForeground().equals(Color.white)) {
					toGoBttn.setBackground(Color.decode("#39C82C"));
					toGoBttn.setForeground(Color.white);
				}else {
					toGoBttn.setBackground(Color.decode("#EDEDED"));
					toGoBttn.setForeground(Color.decode("#999999"));
				}
				
		    }

		    public void mouseExited(MouseEvent evt) {
		    	if(toGoBttn.getForeground().equals(Color.white)) {
		    		toGoBttn.setBackground(Color.decode("#2EA623"));
		    		toGoBttn.setForeground(Color.white);
		    	}else {
		    		toGoBttn.setBackground(Color.decode("#D9D9D9"));
		    		toGoBttn.setForeground(Color.decode("#999999"));
		    	}
		    	
		    }
		});
		
		JPanel titlePnl = new JPanel();
		titlePnl.setLayout(new GridLayout(1, 4, 5, 0));
		titlePnl.setOpaque(false);
		centerHeaderPnl.add(titlePnl);
		
		JLabel productLbl = new JLabel("Producto(s)");
		productLbl.setFont(new Font("Caladea Bold", Font.BOLD, 16));
		productLbl.setForeground(Color.decode("#244E23")); 
		productLbl.setHorizontalAlignment(SwingConstants.CENTER); 
		titlePnl.add(productLbl);
		
		JLabel quantityLbl = new JLabel("Cantidad");
		quantityLbl.setFont(new Font("Caladea Bold", Font.BOLD, 16));
		quantityLbl.setForeground(Color.decode("#244E23")); 
		quantityLbl.setHorizontalAlignment(SwingConstants.CENTER); 
		titlePnl.add(quantityLbl);
		
		JLabel priceLbl = new JLabel("Precio");
		priceLbl.setFont(new Font("Caladea Bold", Font.BOLD, 16));
		priceLbl.setForeground(Color.decode("#244E23")); 
		priceLbl.setHorizontalAlignment(SwingConstants.CENTER); 
		titlePnl.add(priceLbl);
		
		titlePnl.add(Box.createHorizontalStrut(0));
		
	
		centerDishesPnl.setLayout(new GridBagLayout());	
		centerDishesPnl.setOpaque(false); 
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.BOTH;	
	
		ColoredScrollPaneBar coloredScrollPane = new ColoredScrollPaneBar(centerDishesPnl, Color.decode("#244E23"));
		JScrollPane scrollPane = coloredScrollPane.createScrollPane();
		
		centerPnl.add(scrollPane, BorderLayout.CENTER);
		
		
		JPanel centerFooterPnl = new JPanel();
		centerFooterPnl.setLayout(new BorderLayout(0, 10));	
		centerFooterPnl.setOpaque(false);
		centerPnl.add(centerFooterPnl, BorderLayout.SOUTH);
		
		JLabel messageLbl = new JLabel("Agregar instrucciones al cocinar (recados):");
		messageLbl.setFont(new Font("Caladea Bold", Font.BOLD, 14));
		messageLbl.setForeground(Color.decode("#244E23")); 
		messageLbl.setHorizontalAlignment(SwingConstants.LEFT); 
		centerFooterPnl.add(messageLbl, BorderLayout.NORTH);
		
		RoundPanel messageTxtFldPnl = new RoundPanel(30);  
		messageTxtFldPnl.setBackground(Color.white);
		messageTxtFldPnl.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10)); 
		messageTxtFldPnl.setForeground(Color.decode("#244E23")); 
		messageTxtFldPnl.setLayout(new BorderLayout(5, 0));
		centerFooterPnl.add(messageTxtFldPnl);
		
		messageTxtFld = new JTextField();
		messageTxtFld.setBorder(null);
		messageTxtFld.setFont(new Font("Caladea Bold", Font.BOLD, 14)); 
		messageTxtFld.setForeground(Color.decode("#244E23"));
		messageTxtFld.setOpaque(false); 
		messageTxtFldPnl.add(messageTxtFld, BorderLayout.CENTER);
		
		
		RoundPanel footerPnl = new RoundPanel(30);  
		footerPnl.setBackground(Color.decode("#E6F6FF"));
		footerPnl.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20)); 
		footerPnl.setForeground(Color.decode("#E6F6FF")); 
		footerPnl.setLayout(new GridLayout(3, 2, 20, 10));
		tabPnl.add(footerPnl, BorderLayout.SOUTH);
		
		subtotalLbl = new JLabel("Subtotal...");
		subtotalLbl.setFont(new Font("Caladea Regular", Font.PLAIN, 16));
		subtotalLbl.setForeground(Color.decode("#244E23")); 
		subtotalLbl.setHorizontalAlignment(SwingConstants.LEFT); 
		footerPnl.add(subtotalLbl);
		
		subtotalValueLbl = new JLabel("$00.00 MXN");
		subtotalValueLbl.setFont(new Font("Caladea Regular", Font.PLAIN, 16));
		subtotalValueLbl.setForeground(Color.decode("#244E23")); 
		subtotalValueLbl.setHorizontalAlignment(SwingConstants.LEFT); 
		footerPnl.add(subtotalValueLbl);
		
		discountLbl = new JLabel("Descuento...");
		discountLbl.setFont(new Font("Caladea Regular", Font.PLAIN, 16));
		discountLbl.setForeground(Color.decode("#244E23")); 
		discountLbl.setHorizontalAlignment(SwingConstants.LEFT); 
		footerPnl.add(discountLbl);
		
		discountValueLbl = new JLabel("$00.00 MXN");
		discountValueLbl.setFont(new Font("Caladea Regular", Font.PLAIN, 16));
		discountValueLbl.setForeground(Color.decode("#244E23")); 
		discountValueLbl.setHorizontalAlignment(SwingConstants.LEFT); 
		footerPnl.add(discountValueLbl);
		
		totalLbl = new JLabel("TOTAL");
		totalLbl.setFont(new Font("Caladea Bold", Font.BOLD, 20));
		totalLbl.setForeground(Color.decode("#244E23")); 
		totalLbl.setHorizontalAlignment(SwingConstants.LEFT); 
		footerPnl.add(totalLbl);
		
		totalValueLbl = new JLabel("<html><u> $00.00 MXN </u></html>");
		totalValueLbl.setFont(new Font("Caladea Bold", Font.BOLD, 20));
		totalValueLbl.setForeground(Color.decode("#244E23")); 
		totalValueLbl.setHorizontalAlignment(SwingConstants.LEFT); 
		footerPnl.add(totalValueLbl);
		
		/**cuando la ventana es redimensionada, los elementos dentro de ella cambian de tamaño**/
		frame.addComponentListener(new ComponentAdapter() {
            public void componentResized(ComponentEvent e) {
            	
            	relativeXSize = (int) (frame.getWidth()*0.02);
            	font = new Font("Caladea Bold", Font.BOLD, relativeXSize);
            	newOrderBttn.setFont(font);
            	ongoingOrderBttn.setFont(font);
            	cancelBttn.setFont(font);
            	saveBttn.setFont(font);
            	totalLbl.setFont(font);
            	totalValueLbl.setFont(font);
            	
            	relativeXSize = (int) (frame.getWidth()*0.016);
            	font = new Font("Caladea Bold", Font.BOLD, relativeXSize);
            	eatHereBttn.setFont(font);
            	toGoBttn.setFont(font);
            	productLbl.setFont(font);
            	quantityLbl.setFont(font);
            	priceLbl.setFont(font);
            	
            	relativeXSize = (int) (frame.getWidth()*0.016);
            	font = new Font("Caladea Regular", Font.PLAIN, relativeXSize);
            	subtotalLbl.setFont(font);
            	subtotalValueLbl.setFont(font);
            	discountLbl.setFont(font);
            	discountValueLbl.setFont(font);
            	
            	relativeXSize = (int) (frame.getWidth()*0.014);
            	font = new Font("Caladea Bold", Font.BOLD, relativeXSize);
            	tableNumberCmbBx.setFont(font); 
            	nameLbl.setFont(font);
            	nameTxtFld.setFont(font); 
            	messageLbl.setFont(font);
            	messageTxtFld.setFont(font); 
            	
            	relativeXSize = (int) (frame.getWidth()*0.02);
            	backgroundPnl.setBorder(BorderFactory.createEmptyBorder(relativeXSize, relativeXSize/2, relativeXSize, relativeXSize)); 
            	tabPnl.setBorder(BorderFactory.createEmptyBorder(relativeXSize, relativeXSize, relativeXSize, relativeXSize)); 
            	footerPnl.setBorder(BorderFactory.createEmptyBorder(relativeXSize, relativeXSize, relativeXSize, relativeXSize));
            	headerPnl.setBorder(BorderFactory.createEmptyBorder(relativeXSize, relativeXSize*2, 0, relativeXSize*2)); 
            	
            	relativeXSize = (int) (frame.getWidth()*0.01);
            	border = BorderFactory.createEmptyBorder(relativeXSize/2, relativeXSize, relativeXSize/2, relativeXSize);
            	tableNumberPnl.setBorder(border); 
            	nameTxtFldPnl.setBorder(border); 
            	messageTxtFldPnl.setBorder(border); 
            	
       			frame.repaint();
            }
        });
		
		return backgroundPnl;
	}
	
	
	public boolean duplicateOrder(String name) {
		boolean validate = true;
		
		if(dishesArray.size()==0) {
			return false;
		}else {
			//no repetir órdenes
			for(int i=0; i<dishesArray.size(); i++) { 
				JTextArea label = (JTextArea) dishesArray.get(i).getComponent(0);
				
				if(!name.equals(label.getText())) {
					validate = false;
				}else {
					validate = true;
				}
			}
			
			return validate;
		}
	}
	
	public void addDishOrder(String name, double price) {
	
		orderCard = new OrderCard(frame, centerDishesPnl, name, price);
		dishesArray.add(orderCard.createDescriptiveCard(dishesArray));
		
		System.out.println(dishesArray.size());
		
		//añadir paneles al panel principal
		for(int i=0; i<dishesArray.size(); i++) { 
            gbc.gridx = 1;
            gbc.gridy = i;

            centerDishesPnl.add(dishesArray.get(i), gbc);
        }
		
		centerDishesPnl.repaint();
		centerDishesPnl.revalidate();
		
	}

}
