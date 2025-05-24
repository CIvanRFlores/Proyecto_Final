package customClasses;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class OrderCard {
	
	public JFrame frame;
	public int radius;
	public String table;
	public String amount;
	public String clientName;
	public int orderType;
	public String orderTime;
	public Image image;
	public ImageIcon imageIcon;
	public int relativeXSize;
	public int relativeYSize;


	public OrderCard(int radius, String table, String amount, String clientName, int orderType, String orderTime, JFrame frame) {
		this.frame = frame;
		this.radius = radius;
		this.table = table;
		this.amount = amount;
		this.clientName = clientName;
		this.orderType = orderType;
		this.orderTime = orderTime;
	}
	
	public RoundPanel createCard() {
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
		
		JLabel clientLbl = new JLabel("Cliente " + clientName);
		clientLbl.setFont(new Font("Caladea Regular", Font.PLAIN, 15));
		clientLbl.setForeground(Color.decode("#244E23")); 
		clientLbl.setHorizontalAlignment(JLabel.LEFT);
		clientLbl.setHorizontalAlignment(SwingConstants.LEFT); 
		infoPnl.add(clientLbl);
		
		
		JPanel actionPnl = new JPanel();
		actionPnl.setLayout(new GridLayout(1, 3, 10, 0));
		actionPnl.setOpaque(false);
		cardPnl.add(actionPnl, BorderLayout.CENTER);
		
		RoundButton cancelBttn = new RoundButton(radius);
		cancelBttn.setBackground(Color.decode("#EF2D2D"));
		cancelBttn.setFont(new Font("Caladea Regular", Font.PLAIN, 12));
		cancelBttn.setForeground(Color.white);
		cancelBttn.setText("Cancelar");
		actionPnl.add(cancelBttn);
		
		cancelBttn.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent evt) {
				cancelBttn.setBackground(Color.decode("#ED5C5C"));
		    }

		    public void mouseExited(MouseEvent evt) {
		    	cancelBttn.setBackground(Color.decode("#EF2D2D"));
		    }
		});
		
		
		RoundButton payBttn = new RoundButton(radius);
		payBttn.setBackground(Color.decode("#555BF6"));
		payBttn.setFont(new Font("Caladea Regular", Font.PLAIN, 12));
		payBttn.setForeground(Color.white);
		payBttn.setText("Pagar");
		actionPnl.add(payBttn);
		
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
		
		
		RoundPanel footerPnl = new RoundPanel(radius);
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
            	clientLbl.setFont(new Font("Caladea Bold", Font.PLAIN, (int) (frame.getWidth()*0.015)));
            	
            	cancelBttn.setFont(new Font("Caladea Regular", Font.PLAIN, (int) (frame.getWidth()*0.012)));
            	payBttn.setFont(new Font("Caladea Regular", Font.PLAIN, (int) (frame.getWidth()*0.012)));
            	
            	relativeXSize = (int) (frame.getWidth()*0.02);
            	image = new ImageIcon(OrderCard.class.getResource("/images/editOrder.png")).getImage().getScaledInstance(relativeXSize, relativeXSize, Image.SCALE_SMOOTH);
        		imageIcon = new ImageIcon(image);
       			editBttn.setIcon(imageIcon);  
       			
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

}
