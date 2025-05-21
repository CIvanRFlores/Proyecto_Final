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
	
	//crear tarjeta
	public RoundPanel createCard() {
		RoundPanel card = new RoundPanel(this.radius);  
		card.setBackground(Color.white);
		card.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); //borde invisible para centrar elementos que el panel contenga 
		card.setForeground(Color.decode("#244E23")); //color del borde
		card.setLayout(new BorderLayout(0, 10));
		card.setPreferredSize(new Dimension(330, 150));
		
		/*CABECERA*/
		JPanel infoPnl = new JPanel();
		infoPnl.setLayout(new GridLayout(2, 2, 10, 10));
		infoPnl.setOpaque(false);
		card.add(infoPnl, BorderLayout.NORTH);
		
		//nombre de la mesa
		JLabel tableLbl = new JLabel(this.table);
		tableLbl.setFont(new Font("Caladea Bold", Font.BOLD, 20));
		tableLbl.setForeground(Color.decode("#244E23")); //color de letra
		tableLbl.setHorizontalAlignment(JLabel.LEFT); //alinear etiqueta a la izquierda
		tableLbl.setHorizontalAlignment(SwingConstants.LEFT); //centrar texto de la etiqueta 
		infoPnl.add(tableLbl);
		
		//monto a pagar
		JLabel amountLbl = new JLabel(this.amount);
		amountLbl.setFont(new Font("Caladea Bold", Font.BOLD, 13));
		amountLbl.setForeground(Color.decode("#244E23")); //color de letra
		amountLbl.setHorizontalAlignment(JLabel.RIGHT); //alinear etiqueta a la izquierda
		amountLbl.setHorizontalAlignment(SwingConstants.RIGHT); //centrar texto de la etiqueta 
		infoPnl.add(amountLbl);
		
		//nombre del cliente
		JLabel clientLbl = new JLabel(this.clientName);
		clientLbl.setFont(new Font("Caladea Regular", Font.BOLD, 15));
		clientLbl.setForeground(Color.decode("#244E23")); //color de letra
		clientLbl.setHorizontalAlignment(JLabel.LEFT); //alinear etiqueta a la izquierda
		clientLbl.setHorizontalAlignment(SwingConstants.LEFT); //centrar texto de la etiqueta 
		infoPnl.add(clientLbl);
		
		
		/*BOTONES*/
		JPanel actionPnl = new JPanel();
		actionPnl.setLayout(new GridLayout(1, 3, 10, 0));
		actionPnl.setOpaque(false);
		card.add(actionPnl, BorderLayout.CENTER);
		
		//botón de cancelar órden
		RoundButton cancelBttn = new RoundButton(30);
		cancelBttn.setBackground(Color.decode("#EF2D2D"));
		cancelBttn.setFont(new Font("Caladea Regular", Font.BOLD, 12));
		cancelBttn.setForeground(Color.white);
		cancelBttn.setText("Cancelar");
		actionPnl.add(cancelBttn);
		
		//botón de pagar órden 
		RoundButton payBttn = new RoundButton(30);
		payBttn.setBackground(Color.decode("#555BF6"));
		payBttn.setFont(new Font("Caladea Regular", Font.BOLD, 12));
		payBttn.setForeground(Color.white);
		payBttn.setText("Pagar");
		actionPnl.add(payBttn);
		
		//botón de editar órden
		image = new ImageIcon(OrderCard.class.getResource("/images/editOrder.png")).getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
		imageIcon = new ImageIcon(image);
		RoundButton editBttn = new RoundButton(30, imageIcon); //botón
		editBttn.setBackground(Color.white);
		card.add(editBttn, BorderLayout.EAST);
		
		
		/*PIE DE PÁGINA*/
		//dependiendo el tipo de órden, colocar los colores adecuados e iconos
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
		footerPnl.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5)); //borde invisible para centrar elementos que el panel contenga 
		footerPnl.setForeground(footer);
		footerPnl.setLayout(new GridLayout(1, 3, 10, 0));
		card.add(footerPnl, BorderLayout.SOUTH);
		
		//tipo de órden
		JLabel orderTypeLbl = new JLabel(type);
		orderTypeLbl.setFont(new Font("Caladea Regular", Font.BOLD, 12));
		orderTypeLbl.setForeground(text); //coluor de letra
		orderTypeLbl.setHorizontalAlignment(JLabel.LEFT); //alinear etiqueta a la izquierda
		orderTypeLbl.setHorizontalAlignment(SwingConstants.LEFT); //alinear texto de la etiqueta 
		footerPnl.add(orderTypeLbl);
		
		//icono de reloj
		JLabel clockIcon = new JLabel(imageIcon);
		footerPnl.add(clockIcon);
		
		//tiempo estimado de la órden
		JLabel orderTimeLbl = new JLabel(this.orderTime);
		orderTimeLbl.setFont(new Font("Caladea Regular", Font.BOLD, 12));
		orderTimeLbl.setForeground(text); //color de letra
		orderTimeLbl.setHorizontalAlignment(JLabel.RIGHT); //alinear etiqueta a la derecha
		orderTimeLbl.setHorizontalAlignment(SwingConstants.RIGHT); //alinear texto de la etiqueta 
		footerPnl.add(orderTimeLbl);
		
		return card; 
	}

}
