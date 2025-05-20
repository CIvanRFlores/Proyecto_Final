package customClasses;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import views.DishView;

public class DishCard {
	
	public int radius;
	public String dishImageURL;
	public String text;
	public Image image;
	public ImageIcon imageIcon;

	public DishCard(int radius, String dishImageURL, String text) {
		this.radius = radius;
		this.dishImageURL = dishImageURL;
		this.text = text;
	}
	
	//crear tarjeta
	public RoundPanel createCard() {
		RoundPanel card = new RoundPanel(this.radius);  
		card.setBackground(Color.decode("#EDEDED"));
		card.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); //borde invisible para centrar elementos que el panel contenga 
		card.setForeground(Color.decode("#EDEDED")); //color del borde
		card.setLayout(new GridLayout(2, 1));
		card.setPreferredSize(new Dimension(170, 200));
		
		//imagen ilustrativa del platillo
		image = new ImageIcon(dishImageURL).getImage().getScaledInstance(140, 90, Image.SCALE_SMOOTH);
		imageIcon = new ImageIcon(image);
		JLabel dishImage = new JLabel(this.imageIcon);
		card.add(dishImage);
		
		//nombre del platillo
		JLabel dishLbl = new JLabel(this.text);
		dishLbl.setFont(new Font("Caladea Bold", Font.BOLD, 20));
		dishLbl.setForeground(Color.decode("#244E23")); //color de letra
		dishLbl.setHorizontalAlignment(JLabel.CENTER); //alinear etiqueta a la izquierda
		dishLbl.setHorizontalAlignment(SwingConstants.CENTER); //centrar texto de la etiqueta 
		card.add(dishLbl);
		
		return card; 
	}

}
