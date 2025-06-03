package customClasses;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class InformationTable {
	
	public JFrame frame;
	public DefaultTableModel tableModel;
	public Color tableHeader;
	
	public InformationTable(JFrame frame, DefaultTableModel tablemodel, Color tableHeader) {
		this.frame = frame;
		this.tableModel = tablemodel;
		this.tableHeader = tableHeader;
	}
	
	public JTable createTable() {
		JTable table = new JTable(tableModel);
		
		table.setFont(new Font("Caladea Bold", Font.BOLD, 16));
		table.setDefaultEditor(Object.class, null);
		table.setRowHeight(40);
		table.setShowGrid(false);
		table.setShowHorizontalLines(true);
		table.setShowVerticalLines(false);
		
		table.getTableHeader().setBackground(tableHeader);
		table.getTableHeader().setForeground(Color.white);
		table.getTableHeader().setFont(new Font("Caladea Bold", Font.BOLD, 16));
		table.getTableHeader().setReorderingAllowed(false);
		table.getTableHeader().setResizingAllowed(false);
		
		table.setBackground(Color.white);
		table.setForeground(Color.decode("#244E23"));
		table.setSelectionBackground(Color.white);
		table.setSelectionForeground(Color.decode("#2EA623"));
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		/**cuando la ventana es redimensionada, los elementos dentro de ella cambian de tamaño**/
		frame.addComponentListener(new ComponentAdapter() {
            public void componentResized(ComponentEvent e) {
            	table.getTableHeader().setFont(new Font("Caladea Bold", Font.BOLD, ((int) (frame.getWidth()*0.016))));
            	table.setFont(new Font("Caladea Bold", Font.BOLD, ((int) (frame.getWidth()*0.016))));
            	
       			frame.repaint();
            }
        });
		
		return table;
	}
	
}
