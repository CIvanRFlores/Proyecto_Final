package customClasses;

import java.awt.*;

import javax.swing.*;
import javax.swing.table.TableCellRenderer;

public class RoundedHeaderRender extends JLabel implements TableCellRenderer {
	
	private static final long serialVersionUID = 1558131913243611445L;
	
	public Color tableHeader;
	public boolean firstColumn = false;
	public boolean lastColumn = false;
	
    public RoundedHeaderRender(Color tableHeader) {
        setOpaque(false);
        setHorizontalAlignment(CENTER);
        setVerticalAlignment(CENTER);
        
        this.tableHeader = tableHeader;
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
    	setText(value != null ? value.toString() : "");
        if(column==0) {
        	firstColumn = true;
        	lastColumn = false;
        }
        if(column == table.getColumnCount()-1) {
        	lastColumn = true;
        	firstColumn = false;
        }
        if(column>0 && column<table.getColumnCount()-1) {
        	lastColumn = false;
        	firstColumn = false;
        }
        
        setBackground(table.getTableHeader().getBackground());
        setForeground(table.getTableHeader().getForeground());
		setFont(table.getFont());
		setText(value.toString());
		
		setHorizontalAlignment(SwingConstants.CENTER);
		
        return this;
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON); //suavizar bordes

        g2.setColor(tableHeader);
        
        if(firstColumn) {
        	g2.fillRoundRect(0, 0, getWidth(), getHeight(), 30, 30);
        	g2.fillRect(getX()+getWidth()-30, 0, 30, 30);
        }
        if(lastColumn){
        	g2.fillRoundRect(0, 0, getWidth(), getHeight(), 30, 30);
        	g2.fillRect(0, 0, 30, 30);
        }
        if(!firstColumn && !lastColumn){
        	g2.fillRect(0, 0, getWidth(), getHeight());
        }
        
        super.paintComponent(g);
        g2.dispose();
    }
}
