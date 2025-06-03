package customClasses;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class InformationTable extends JTable {
	
	private static final long serialVersionUID = -5017157673091151929L;
	
	public JFrame frame;
	public DefaultTableModel tableModel;
	public Color tableHeader;
	
	public InformationTable(JFrame frame, DefaultTableModel tablemodel, Color tableHeader) {
		this.frame = frame;
		this.tableModel = tablemodel;
		this.tableHeader = tableHeader;
	}
	
	public JTable createTable() {
		Font font = new Font("Caladea Bold", Font.BOLD, 20);
		
		setModel(tableModel);
		setFont(font);
		setDefaultEditor(Object.class, null);
		setRowHeight(40);
		setShowGrid(false);
		setShowHorizontalLines(true);
		setShowVerticalLines(false);
		
		getTableHeader().setDefaultRenderer(new RoundedHeaderRender(tableHeader));
		getTableHeader().setBackground(Color.white);
		getTableHeader().setForeground(Color.white);
		getTableHeader().setFont(font);
		getTableHeader().setReorderingAllowed(false);
		getTableHeader().setResizingAllowed(false);
		
		setOpaque(false);
		setForeground(Color.decode("#244E23"));
		setSelectionBackground(Color.white);
		setSelectionForeground(Color.decode("#2EA623"));
		setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		/**cuando la ventana es redimensionada, los elementos dentro de ella cambian de tamaño**/
		frame.addComponentListener(new ComponentAdapter() {
            public void componentResized(ComponentEvent e) {
            	getTableHeader().setFont(new Font("Caladea Bold", Font.BOLD, ((int) (frame.getHeight()*0.02))));
            	setFont(new Font("Caladea Bold", Font.BOLD, ((int) (frame.getHeight()*0.02))));
            	
       			frame.repaint();
            }
        });
		
		return this;
	}

}
