package customClasses;

import javax.swing.JPanel;
import java.awt.*;

public class RoundPanel extends JPanel {

    /**
	 * 
	 */
	public static final long serialVersionUID = 7129786242148060747L;
	public int radius;

    public RoundPanel(int radius) {
        this.radius = radius; 
        setOpaque(false); 
    }

    @Override
    protected void paintComponent(Graphics g) {
    	super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g.create();
        
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON); //suavizar bordes

        //borde de color
        g2.setColor(getForeground());
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), radius, radius);
        
        //fondo de color
        g2.setColor(getBackground());
        g2.fillRoundRect(+2, +2, getWidth()-4, getHeight()-4, radius, radius);
    
        g2.dispose();
    }
    
}