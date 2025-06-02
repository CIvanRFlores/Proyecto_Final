package customClasses;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import controllers.*;

public class SideBarPanel {

	public JFrame frame;
	public Image image;
	public ImageIcon imageIcon;
	public String message;
	public int relativeXSize;
	public int relativeYSize;
	public AuthController ac;
	public DishController dc;
	public OrderController oc;
	public ClientController cc;
	public InventoryController ic;

	public SideBarPanel(JFrame frame) {
		this.frame = frame;
	}

	public JPanel createSidePanel() {
		JPanel buttonPnl = new JPanel();
		buttonPnl.setBackground(Color.decode("#DEFFDB"));
		buttonPnl.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); 
		buttonPnl.setLayout(new GridLayout(6, 1, 0, 10));
		
		image = new ImageIcon(SideBarPanel.class.getResource("/images/elManglarName.png")).getImage().getScaledInstance(200, 40, Image.SCALE_SMOOTH);
		imageIcon = new ImageIcon(image);
		JLabel restaurantNameLbl = new JLabel(imageIcon);
		buttonPnl.add(restaurantNameLbl);
		
		image = new ImageIcon("src/images/food.png").getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
		imageIcon = new ImageIcon(image);
		RoundButton foodBttn = new RoundButton(30); 
		foodBttn.setBackground(Color.decode("#244E23"));
		foodBttn.setFont(new Font("Caladea Bold", Font.BOLD, 28)); 
		foodBttn.setForeground(Color.white);
		foodBttn.setIcon(imageIcon);
		foodBttn.setText("Platillos");
		foodBttn.setHorizontalAlignment(SwingConstants.LEFT);
		buttonPnl.add(foodBttn);
		
		foodBttn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				dc = new DishController("Platillos", frame.getWidth(), frame.getHeight()); 
				dc.dishes(); 
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
		
		
		image = new ImageIcon(SideBarPanel.class.getResource("/images/order.png")).getImage().getScaledInstance(30, 35, Image.SCALE_SMOOTH);
		imageIcon = new ImageIcon(image);
		RoundButton orderBttn = new RoundButton(30); 
		orderBttn.setBackground(Color.decode("#244E23"));
		orderBttn.setFont(new Font("Caladea Bold", Font.BOLD, 28)); 
		orderBttn.setForeground(Color.white);
		orderBttn.setIcon(imageIcon);
		orderBttn.setText("Ordenes");
		orderBttn.setHorizontalAlignment(SwingConstants.LEFT);
		buttonPnl.add(orderBttn);
		
		orderBttn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				oc = new OrderController("Ordenes", frame.getWidth(), frame.getHeight()); 
				oc.orders(); 
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
		
		
		image = new ImageIcon(SideBarPanel.class.getResource("/images/client.png")).getImage().getScaledInstance(30, 35, Image.SCALE_SMOOTH);
		imageIcon = new ImageIcon(image);
		RoundButton clientBttn = new RoundButton(30); 
		clientBttn.setBackground(Color.decode("#244E23"));
		clientBttn.setFont(new Font("Caladea Bold", Font.BOLD, 28)); 
		clientBttn.setForeground(Color.white);
		clientBttn.setIcon(imageIcon);
		clientBttn.setText("Clientes");
		clientBttn.setHorizontalAlignment(SwingConstants.LEFT);
		buttonPnl.add(clientBttn);
		
		clientBttn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				cc = new ClientController("Clientes", frame.getWidth(), frame.getHeight()); 
				cc.clients(); 
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
		
		
		image = new ImageIcon(SideBarPanel.class.getResource("/images/inventory.png")).getImage().getScaledInstance(30, 28, Image.SCALE_SMOOTH);
		imageIcon = new ImageIcon(image);
		RoundButton inventoryBttn = new RoundButton(30); 
		inventoryBttn.setBackground(Color.decode("#244E23"));
		inventoryBttn.setFont(new Font("Caladea Bold", Font.BOLD, 28)); 
		inventoryBttn.setForeground(Color.white);
		inventoryBttn.setIcon(imageIcon);
		inventoryBttn.setText("Inventario");
		inventoryBttn.setHorizontalAlignment(SwingConstants.LEFT);
		buttonPnl.add(inventoryBttn);
		
		inventoryBttn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				ic = new InventoryController("Inventario", frame.getWidth(), frame.getHeight()); 
				ic.inventory(); 
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
		
		image = new ImageIcon(SideBarPanel.class.getResource("/images/return.png")).getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
		imageIcon = new ImageIcon(image);
		RoundButton returnBttn = new RoundButton(30); 
		returnBttn.setBackground(Color.decode("#EF2D2D"));
		returnBttn.setFont(new Font("Caladea Bold", Font.BOLD, 28)); 
		returnBttn.setForeground(Color.white);
		returnBttn.setText("Salir"); 
		returnBttn.setIcon(imageIcon);
		returnBttn.setHorizontalAlignment(SwingConstants.LEFT);
		buttonPnl.add(returnBttn);
		
		returnBttn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Object[] options = {"Volver", "Salir"};
				
				image = new ImageIcon(SideBarPanel.class.getResource("/images/questionMark.png")).getImage().getScaledInstance(25, 45, Image.SCALE_SMOOTH);
				imageIcon = new ImageIcon(image);
				
				message = "¿Desea cerrar sesión?";
				int opc = JOptionPane.showOptionDialog(null, message, "Salir", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE, imageIcon, options, null);
				
				if(opc==1) {
					frame.dispose();
					ac = new AuthController("Login", frame.getWidth(), frame.getHeight()); 
					ac.login(); 
				}
			}
		});
		
		returnBttn.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent evt) {
				returnBttn.setBackground(Color.decode("#ED5C5C"));
		    }

		    public void mouseExited(MouseEvent evt) {
				returnBttn.setBackground(Color.decode("#EF2D2D"));
		    }
		});
		
		/**cuando la ventana es redimensionada, los elementos dentro de ella cambian de tamaño**/
		frame.addComponentListener(new ComponentAdapter() {
            public void componentResized(ComponentEvent e) {
            	relativeXSize = (int) (frame.getWidth()*0.2);
            	relativeYSize = (int) (frame.getWidth()*0.04);
               	image = new ImageIcon(SideBarPanel.class.getResource("/images/elManglarName.png")).getImage().getScaledInstance(relativeXSize, relativeYSize, Image.SCALE_SMOOTH);
       			imageIcon = new ImageIcon(image);
       			restaurantNameLbl.setIcon(imageIcon);  
       			
       			relativeXSize = (int) (frame.getWidth()*0.03);
            	relativeYSize = (int) (frame.getWidth()*0.03);
       			image = new ImageIcon(SideBarPanel.class.getResource("/images/food.png")).getImage().getScaledInstance(relativeXSize, relativeYSize, Image.SCALE_SMOOTH);
       			imageIcon = new ImageIcon(image);
       			foodBttn.setFont(new Font("Caladea Bold", Font.BOLD, ((int) (frame.getWidth()*0.028) + (int) (frame.getHeight()*0.028))/2 ));
       			foodBttn.setIcon(imageIcon);
       			
       			relativeXSize = (int) (frame.getWidth()*0.03);
            	relativeYSize = (int) (frame.getWidth()*0.035);
       			image = new ImageIcon(SideBarPanel.class.getResource("/images/order.png")).getImage().getScaledInstance(relativeXSize, relativeYSize, Image.SCALE_SMOOTH);
       			imageIcon = new ImageIcon(image);
       			orderBttn.setFont(new Font("Caladea Bold", Font.BOLD, ((int) (frame.getWidth()*0.028) + (int) (frame.getHeight()*0.028))/2 ));
       			orderBttn.setIcon(imageIcon);
       			
       			relativeXSize = (int) (frame.getWidth()*0.03);
            	relativeYSize = (int) (frame.getWidth()*0.035);
       			image = new ImageIcon(SideBarPanel.class.getResource("/images/client.png")).getImage().getScaledInstance(relativeXSize, relativeYSize, Image.SCALE_SMOOTH);
       			imageIcon = new ImageIcon(image);
       			clientBttn.setFont(new Font("Caladea Bold", Font.BOLD, ((int) (frame.getWidth()*0.028) + (int) (frame.getHeight()*0.028))/2 ));
       			clientBttn.setIcon(imageIcon);
       			
       			relativeXSize = (int) (frame.getWidth()*0.03);
            	relativeYSize = (int) (frame.getWidth()*0.028);
            	image = new ImageIcon(SideBarPanel.class.getResource("/images/inventory.png")).getImage().getScaledInstance(relativeXSize, relativeYSize, Image.SCALE_SMOOTH);
            	imageIcon = new ImageIcon(image);
            	inventoryBttn.setFont(new Font("Caladea Bold", Font.BOLD, ((int) (frame.getWidth()*0.028) + (int) (frame.getHeight()*0.028))/2 ));
       			inventoryBttn.setIcon(imageIcon);
       			
       			relativeXSize = (int) (frame.getWidth()*0.03);
            	relativeYSize = (int) (frame.getWidth()*0.03);
            	image = new ImageIcon(SideBarPanel.class.getResource("/images/return.png")).getImage().getScaledInstance(relativeXSize, relativeYSize, Image.SCALE_SMOOTH);
            	imageIcon = new ImageIcon(image);
            	returnBttn.setFont(new Font("Caladea Bold", Font.BOLD, ((int) (frame.getWidth()*0.028) + (int) (frame.getHeight()*0.028))/2 ));
            	returnBttn.setIcon(imageIcon);
 
       			frame.repaint();
            }
        });
		
		return buttonPnl;
	}
	
}
