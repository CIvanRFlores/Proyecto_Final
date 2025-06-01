package customClasses;

import java.awt.*;
import java.awt.event.*;
import java.net.URL;

import javax.swing.*;

import controllers.DishController;

public class DishCard {
	
	public JFrame frame;
	public int radius;
	public URL dishImageURL;
	public String text;
	public Image image;
	public ImageIcon imageIcon;
	public int relativeXSize;
	public int relativeYSize;
	public DishController dc;

	public DishCard(int radius, URL dishImageURL, String text, JFrame frame) {
		this.frame = frame;
		this.radius = radius;
		this.dishImageURL = dishImageURL;
		this.text = text;
	}
	
	public RoundPanel createCard() {
		dc = new DishController(frame.getTitle(), frame.getWidth(), frame.getHeight());
		
		RoundPanel cardPnl = new RoundPanel(radius);  
		cardPnl.setBackground(Color.decode("#EDEDED"));
		cardPnl.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); 
		cardPnl.setForeground(Color.decode("#EDEDED")); 
		cardPnl.setLayout(new GridLayout(2, 1));
		
		image = new ImageIcon(dishImageURL).getImage().getScaledInstance(140, 90, Image.SCALE_SMOOTH);
		imageIcon = new ImageIcon(image);
		JLabel dishImage = new JLabel(this.imageIcon);
		cardPnl.add(dishImage);
		
		JLabel dishNameLbl = new JLabel(adjustText(text));
		dishNameLbl.setFont(new Font("Caladea Bold", Font.BOLD, 20));
		dishNameLbl.setForeground(Color.decode("#244E23"));
		dishNameLbl.setHorizontalAlignment(JLabel.CENTER); 
		dishNameLbl.setHorizontalAlignment(SwingConstants.CENTER); 
		cardPnl.add(dishNameLbl);
		
		dishNameLbl.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent evt) {
				frame.dispose();
				dc.dishPage("Platillo");
			}
		});
		
		dishNameLbl.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent evt) {
				dishNameLbl.setForeground(Color.decode("#3C7E3A"));
				String underlined = "<html><u>" + dishNameLbl.getText() + "</u></html>";
				dishNameLbl.setText(underlined);
		    }

		    public void mouseExited(MouseEvent evt) {
		    	dishNameLbl.setForeground(Color.decode("#244E23"));
				dishNameLbl.setText(adjustText(text));
		    }
		});
		
		
		/**cuando la ventana es redimensionada, los elementos dentro de ella cambian de tamaño**/
		frame.addComponentListener(new ComponentAdapter() {
            public void componentResized(ComponentEvent e) {
            	relativeXSize = (int) (frame.getHeight()*0.01);
            	cardPnl.setBorder(BorderFactory.createEmptyBorder(relativeXSize, relativeXSize, relativeXSize, relativeXSize)); 
            	
            	relativeXSize = (int) (frame.getWidth()*0.140);
            	relativeYSize = (int) (frame.getWidth()*0.09);
               	image = new ImageIcon(dishImageURL).getImage().getScaledInstance(relativeXSize, relativeYSize, Image.SCALE_SMOOTH);
       			imageIcon = new ImageIcon(image);
       			dishImage.setIcon(imageIcon);  
       		
       			dishNameLbl.setFont(new Font("Caladea Bold", Font.BOLD, (int) (frame.getWidth()*0.02)));
       			
       			frame.repaint();
            }
        });
		
		return cardPnl; 
	}
	
	public String adjustText(String name) {
		String[] separatedText = name.split(" ");
		String adjustedText = "<html><center>";
		
		for(int i=0; i<separatedText.length; i++) {
			adjustedText += separatedText[i] + "<br>";
		}
		
		adjustedText += "</center></html>";
		
		return adjustedText;
	}

}
