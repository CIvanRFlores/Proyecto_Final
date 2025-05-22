package customClasses;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.net.URL;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class DishCard {
	
	public int radius;
	public URL dishImageURL;
	public String text;
	public Image image;
	public ImageIcon imageIcon;

	public DishCard(int radius, URL dishImageURL, String text) {
		this.radius = radius;
		this.dishImageURL = dishImageURL;
		this.text = text;
	}
	
	//crear tarjeta
	public RoundPanel createCard() {
		RoundPanel card = new RoundPanel(this.radius);  
		card.setBackground(Color.decode("#EDEDED"));
		card.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); 
		card.setForeground(Color.decode("#EDEDED")); 
		card.setLayout(new GridLayout(2, 1));
		card.setPreferredSize(new Dimension(170, 200));
		
		image = new ImageIcon(dishImageURL).getImage().getScaledInstance(140, 90, Image.SCALE_SMOOTH);
		imageIcon = new ImageIcon(image);
		JLabel dishImage = new JLabel(this.imageIcon);
		card.add(dishImage);
		
		JLabel dishLbl = new JLabel(this.text);
		dishLbl.setFont(new Font("Caladea Bold", Font.BOLD, 20));
		dishLbl.setForeground(Color.decode("#244E23"));
		dishLbl.setHorizontalAlignment(JLabel.CENTER); 
		dishLbl.setHorizontalAlignment(SwingConstants.CENTER); 
		card.add(dishLbl);
		
		return card; 
	}

}
