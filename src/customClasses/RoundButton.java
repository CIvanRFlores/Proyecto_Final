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
		this.radius = radius; //radio de las esquinas
		setOpaque(false); //hacer que el botón real no se muestre
        setFocusPainted(false); //hacer que el borde cuando se presiona el botón no aparezca
        setBorderPainted(false); //hacer que el botón no tenga borde
        setContentAreaFilled(false); //hace que el área del botón no sea visible
	}
	
	public RoundButton(int radius) { //si solo se asignó texto al botón
		this.radius = radius; //radio de las esquinas
		setOpaque(false); //hacer que el botón real no se muestre
        setFocusPainted(false); //hacer que el borde cuando se presiona el botón no aparezca
        setBorderPainted(false); //hacer que el botón no tenga borde
        setContentAreaFilled(false); //hace que el área del botón no sea visible
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g.create();
		
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON); //suavizar bordes
		
		//fondo de color
        g2.setColor(getBackground());
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), radius, radius);
		
		//si se asignó un icono al botón
		if(imageIcon!=null) { 
			
		    int iconWidth = imageIcon.getIconWidth(); //longitudes de la imagen
		    int iconHeight = imageIcon.getIconHeight();
		
		    int iconX = (getWidth()-iconWidth-g2.getFontMetrics().stringWidth(getText()))/2; //posición del icono
		    int iconY = (getHeight()-iconHeight)/2;
		
		    g2.drawImage(imageIcon.getImage(), iconX, iconY, null); //dibujar imagen
		    
		    int textX = iconX+iconWidth+10; //posición del texto
		 	int textY = (getHeight()+g2.getFontMetrics().getAscent())/2;
		 	
		    g2.setColor(getForeground()); //dibujar texto
		    g2.drawString(getText(), textX, textY);
		    
		}else {	    
			int textX = (getWidth()-g2.getFontMetrics().stringWidth(getText()))/2; //posición del texto 
			int textY = (getHeight()+g2.getFontMetrics().getAscent())/2; 	    
			
			g2.setColor(getForeground()); //dibujar texto	   
			g2.drawString(getText(), textX, textY); 	 
		}

	}

}