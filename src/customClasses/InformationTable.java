package customClasses;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class InformationTable {
	
	public JFrame frame;
	public JTable table;
	public JScrollPane scrollPane;
	public Font font;
	public DefaultTableModel tableModel;
	public Color tableHeader;
	
	public InformationTable(JFrame frame, DefaultTableModel tablemodel, Color tableHeader) {
		this.frame = frame;
		this.tableModel = tablemodel;
		this.tableHeader = tableHeader;
	}
	
	public JScrollPane createTable() {
		font = new Font("Caladea Bold", Font.BOLD, 20);
		table = new JTable(tableModel);
	
		table.setFont(font);
		table.setDefaultEditor(Object.class, null);
		table.setRowHeight(40);
		table.setShowGrid(false);
		table.setShowHorizontalLines(true);
		table.setShowVerticalLines(false);
		
		table.getTableHeader().setDefaultRenderer(new RoundedHeaderRender(tableHeader));
		table.getTableHeader().setBackground(Color.white);
		table.getTableHeader().setForeground(Color.white);
		table.getTableHeader().setFont(font);
		table.getTableHeader().setReorderingAllowed(false);
		table.getTableHeader().setResizingAllowed(false);
		
		table.setOpaque(false);
		table.setForeground(Color.decode("#244E23"));
		table.setSelectionBackground(Color.white);
		table.setSelectionForeground(Color.decode("#2EA623"));
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		ColoredScrollPaneBar coloredScrollPane = new ColoredScrollPaneBar(table, tableHeader);
	
		scrollPane = coloredScrollPane.createScrollPane();
	    
		
		/**cuando la ventana es redimensionada, los elementos dentro de ella cambian de tamaño**/
		frame.addComponentListener(new ComponentAdapter() {
            public void componentResized(ComponentEvent e) {
            	table.getTableHeader().setFont(new Font("Caladea Bold", Font.BOLD, ((int) (frame.getHeight()*0.02))));
            	table.setFont(new Font("Caladea Bold", Font.BOLD, ((int) (frame.getHeight()*0.02))));
            	
       			frame.repaint();
            }
        });
		
		return scrollPane;
	}
	
	public JTable getTable() {
		return table;
	}

}
