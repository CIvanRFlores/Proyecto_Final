package customClasses;

import javax.swing.JButton;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import javax.swing.ImageIcon;

public class RoundButton extends JButton {
	
	/**
	 * 
	 */
	public static final long serialVersionUID = 1499719972644048438L;
	public int radius;
	public ImageIcon imageIcon;
	
	public RoundButton(int radius, ImageIcon imageIcon) { //si se asigna un icono al botón
		this.imageIcon = imageIcon;
		this.radius = radius; 
		setOpaque(false); 
        setFocusPainted(false); 
        setBorderPainted(false); 
        setContentAreaFilled(false); 
	}
	
	public RoundButton(int radius) { 
		this.radius = radius; 
		setOpaque(false); 
        setFocusPainted(false); 
        setBorderPainted(false); 
        setContentAreaFilled(false); 
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g.create();
		
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON); //suavizar bordes
		
        g2.setColor(getBackground());
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), radius, radius);
		
		if(imageIcon!=null) { 
			
		    int iconWidth = imageIcon.getIconWidth(); 
		    int iconHeight = imageIcon.getIconHeight();
		
		    int iconX = (getWidth()-iconWidth-g2.getFontMetrics().stringWidth(getText()))/2; 
		    int iconY = (getHeight()-iconHeight)/2;
		
		    g2.drawImage(imageIcon.getImage(), iconX, iconY, null); //dibujar imagen
		    
		    int textX = iconX+iconWidth+10; 
		 	int textY = (getHeight()+g2.getFontMetrics().getAscent())/2;
		 	
		    g2.setColor(getForeground()); 
		    g2.drawString(getText(), textX, textY); //dibujar texto
		    
		}else {	    
			int textX = (getWidth()-g2.getFontMetrics().stringWidth(getText()))/2;  
			int textY = (getHeight()+g2.getFontMetrics().getAscent())/2; 	    
			
			g2.setColor(getForeground());    
			g2.drawString(getText(), textX, textY); //dibujar texto		 
		}

	}

	public void setIcon(ImageIcon imageIcon) {
		this.imageIcon = imageIcon;
	}
	
	

}