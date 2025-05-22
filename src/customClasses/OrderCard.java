package customClasses;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class OrderCard {
	
	public int radius;
	public String table;
	public String amount;
	public String clientName;
	public int orderType;
	public String orderTime;
	public Image image;
	public ImageIcon imageIcon;


	public OrderCard(int radius, String table, String amount, String clientName, int orderType, String orderTime) {
		this.radius = radius;
		this.table = table;
		this.amount = amount;
		this.clientName = clientName;
		this.orderType = orderType;
		this.orderTime = orderTime;
	}
	
	public RoundPanel createCard() {
		RoundPanel card = new RoundPanel(this.radius);  
		card.setBackground(Color.white);
		card.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); 
		card.setForeground(Color.decode("#244E23"));
		card.setLayout(new BorderLayout(0, 10));
		card.setPreferredSize(new Dimension(330, 150));
		
		JPanel infoPnl = new JPanel();
		infoPnl.setLayout(new GridLayout(2, 2, 10, 10));
		infoPnl.setOpaque(false);
		card.add(infoPnl, BorderLayout.NORTH);
		
		JLabel tableLbl = new JLabel(this.table);
		tableLbl.setFont(new Font("Caladea Bold", Font.BOLD, 20));
		tableLbl.setForeground(Color.decode("#244E23")); 
		tableLbl.setHorizontalAlignment(JLabel.LEFT); 
		tableLbl.setHorizontalAlignment(SwingConstants.LEFT); 
		infoPnl.add(tableLbl);
		
		JLabel amountLbl = new JLabel(this.amount);
		amountLbl.setFont(new Font("Caladea Bold", Font.BOLD, 13));
		amountLbl.setForeground(Color.decode("#244E23")); 
		amountLbl.setHorizontalAlignment(JLabel.RIGHT); 
		amountLbl.setHorizontalAlignment(SwingConstants.RIGHT); 
		infoPnl.add(amountLbl);
		
		JLabel clientLbl = new JLabel(this.clientName);
		clientLbl.setFont(new Font("Caladea Regular", Font.BOLD, 15));
		clientLbl.setForeground(Color.decode("#244E23")); 
		clientLbl.setHorizontalAlignment(JLabel.LEFT);
		clientLbl.setHorizontalAlignment(SwingConstants.LEFT); 
		infoPnl.add(clientLbl);
		
		
		JPanel actionPnl = new JPanel();
		actionPnl.setLayout(new GridLayout(1, 3, 10, 0));
		actionPnl.setOpaque(false);
		card.add(actionPnl, BorderLayout.CENTER);
		
		RoundButton cancelBttn = new RoundButton(30);
		cancelBttn.setBackground(Color.decode("#EF2D2D"));
		cancelBttn.setFont(new Font("Caladea Regular", Font.BOLD, 12));
		cancelBttn.setForeground(Color.white);
		cancelBttn.setText("Cancelar");
		actionPnl.add(cancelBttn);
		
		RoundButton payBttn = new RoundButton(30);
		payBttn.setBackground(Color.decode("#555BF6"));
		payBttn.setFont(new Font("Caladea Regular", Font.BOLD, 12));
		payBttn.setForeground(Color.white);
		payBttn.setText("Pagar");
		actionPnl.add(payBttn);
		
		image = new ImageIcon(OrderCard.class.getResource("/images/editOrder.png")).getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
		imageIcon = new ImageIcon(image);
		RoundButton editBttn = new RoundButton(30, imageIcon);
		editBttn.setBackground(Color.white);
		card.add(editBttn, BorderLayout.EAST);
		
		
		Color text = null;
		Color footer = null;
		String type = "";
		
		switch(this.orderType) {
			case 1:
				text = Color.decode("#555BF6");
				footer = Color.decode("#BCF0FB");
				image = new ImageIcon(OrderCard.class.getResource("/images/blueClock.png")).getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH);
				imageIcon = new ImageIcon(image);
				type = "Comer aquí";
			break;
			
			case 2:
				text = Color.decode("#EF2D2D");
				footer = Color.decode("#FBBCBC");
				image = new ImageIcon(OrderCard.class.getResource("/images/redClock.png")).getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH);
				imageIcon = new ImageIcon(image);
				type = "Llevar";
			break;
				
			case 3:
				text = Color.decode("#C07A00");
				footer = Color.decode("#FFE8C1");
				image = new ImageIcon(OrderCard.class.getResource("/images/yellowClock.png")).getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH);
				imageIcon = new ImageIcon(image);
				type = "Entregado";
			break;
		}
		
		
		RoundPanel footerPnl = new RoundPanel(30);
		footerPnl.setBackground(footer);
		footerPnl.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5)); 
		footerPnl.setForeground(footer);
		footerPnl.setLayout(new GridLayout(1, 3, 10, 0));
		card.add(footerPnl, BorderLayout.SOUTH);
		
		JLabel orderTypeLbl = new JLabel(type);
		orderTypeLbl.setFont(new Font("Caladea Regular", Font.BOLD, 12));
		orderTypeLbl.setForeground(text); 
		orderTypeLbl.setHorizontalAlignment(JLabel.LEFT); 
		orderTypeLbl.setHorizontalAlignment(SwingConstants.LEFT); 
		footerPnl.add(orderTypeLbl);
		
		JLabel clockIcon = new JLabel(imageIcon);
		footerPnl.add(clockIcon);
		
		JLabel orderTimeLbl = new JLabel(this.orderTime);
		orderTimeLbl.setFont(new Font("Caladea Regular", Font.BOLD, 12));
		orderTimeLbl.setForeground(text);
		orderTimeLbl.setHorizontalAlignment(JLabel.RIGHT); 
		orderTimeLbl.setHorizontalAlignment(SwingConstants.RIGHT); 
		footerPnl.add(orderTimeLbl);
		
		return card; 
	}

}
