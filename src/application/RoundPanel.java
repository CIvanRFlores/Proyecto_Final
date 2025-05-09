package application;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.geom.Area;
import java.awt.geom.Rectangle2D;
import java.awt.geom.RoundRectangle2D;
import javax.swing.JPanel;

public class RoundPanel extends JPanel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -5855872630980507652L;
	
	private int roundTopLeft = 0;
	private int roundTopRight = 0;
	private int roundBottomLeft = 0;
	private int roundBottomRight = 0;
	
	public RoundPanel() {
		setOpaque(false); //hacer que el panel real no se muestre
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g.create();
		
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON); //suavizar bordes
		g2.setColor(getBackground());
		
		Area area = new Area(createRoundTopLeft());
		
		if(roundTopLeft>0) {
			area.intersect(new Area(createRoundTopLeft()));
		}
		if(roundTopRight>0) {
			area.intersect(new Area(createRoundTopRight()));
		}
		if(roundBottomLeft>0) {
			area.intersect(new Area(createRoundBottomLeft()));
		}
		if(roundBottomRight>0) {
			area.intersect(new Area(createRoundBottomRight()));
		}
		
		g2.fill(area);
		g2.dispose();
	}
	
	public Shape createRoundTopLeft() {
		int height = getHeight();
		int width = getWidth();
		
		int roundX = Math.min(width, roundTopLeft); //se asigna a la variable el valor que sea menor dentro de los parámetros
		int roundY = Math.min(height, roundTopLeft); //se asigna a la variable el valor que sea menor dentro de los parámetros
		
		Area area = new Area(new RoundRectangle2D.Double(0, 0, width, height, roundX, roundY));
		area.add(new Area(new Rectangle2D.Double(roundX/2, 0, width-roundX/2, height)));
		area.add(new Area(new Rectangle2D.Double(0, roundY/2, width, height-roundY/2)));
		
		return area;
	}
	
	public Shape createRoundTopRight() {
		int height = getHeight();
		int width = getWidth();
		
		int roundX = Math.min(width, roundTopRight); //se asigna a la variable el valor que sea menor dentro de los parámetros
		int roundY = Math.min(height, roundTopRight); //se asigna a la variable el valor que sea menor dentro de los parámetros
		
		Area area = new Area(new RoundRectangle2D.Double(0, 0, width, height, roundX, roundY));
		area.add(new Area(new Rectangle2D.Double(0, 0, width-roundX/2, height)));
		area.add(new Area(new Rectangle2D.Double(0, roundY/2, width, height-roundY/2)));
		
		return area;
	}
	
	public Shape createRoundBottomLeft() {
		int height = getHeight();
		int width = getWidth();
		
		int roundX = Math.min(width, roundBottomLeft); //se asigna a la variable el valor que sea menor dentro de los parámetros
		int roundY = Math.min(height, roundBottomLeft); //se asigna a la variable el valor que sea menor dentro de los parámetros
		
		Area area = new Area(new RoundRectangle2D.Double(0, 0, width, height, roundX, roundY));
		area.add(new Area(new Rectangle2D.Double(roundX/2, 0, width-roundX/2, height)));
		area.add(new Area(new Rectangle2D.Double(0, 0, width, height-roundY/2)));
		
		return area;
	}
	
	public Shape createRoundBottomRight() {
		int height = getHeight();
		int width = getWidth();
		
		int roundX = Math.min(width, roundBottomRight); //se asigna a la variable el valor que sea menor dentro de los parámetros
		int roundY = Math.min(height, roundBottomRight); //se asigna a la variable el valor que sea menor dentro de los parámetros
		
		Area area = new Area(new RoundRectangle2D.Double(0, 0, width, height, roundX, roundY));
		area.add(new Area(new Rectangle2D.Double(0, 0, width-roundX/2, height)));
		area.add(new Area(new Rectangle2D.Double(0, 0, width, height-roundY/2)));
		
		return area;
	}

	public int getRoundTopLeft() {
		return roundTopLeft;
	}

	public void setRoundTopLeft(int roundTopLeft) {
		this.roundTopLeft = roundTopLeft;
		repaint();
	}

	public int getRoundTopRight() {
		return roundTopRight;
	}

	public void setRoundTopRight(int roundTopRight) {
		this.roundTopRight = roundTopRight;
		repaint();
	}

	public int getRoundBottomLeft() {
		return roundBottomLeft;
	}

	public void setRoundBottomLeft(int roundBottomLeft) {
		this.roundBottomLeft = roundBottomLeft;
		repaint();
	}

	public int getRoundBottomRight() {
		return roundBottomRight;
	}

	public void setRoundBottomRight(int roundBottomRight) {
		this.roundBottomRight = roundBottomRight;
		repaint();
	}

}
