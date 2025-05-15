package customClasses;

import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

public class RoundPanel extends JPanel {

    /**
	 * 
	 */
	private static final long serialVersionUID = 7129786242148060747L;
	private int radius;

    public RoundPanel(int radius) {
        this.radius = radius; //radio de las esquinas
        setOpaque(false); //hacer que el panel real no se muestre
    }

    @Override
    protected void paintComponent(Graphics g) {
    	super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g.create();
        
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON); //suavizar bordes

        //borde de color
        g2.setColor(getForeground()); // Use foreground color for border
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), radius, radius);
        
        //fondo de color
        g2.setColor(getBackground());
        g2.fillRoundRect(+4, +4, getWidth()-8, getHeight()-8, radius, radius);
    
        g2.dispose();
    }
    
}