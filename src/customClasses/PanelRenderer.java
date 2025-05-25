package customClasses;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.*;
import javax.swing.table.TableCellRenderer;

public class PanelRenderer extends JPanel implements TableCellRenderer{
	
	private static final long serialVersionUID = 1L;
	
	public Image image;
	public ImageIcon imageIcon;
	
	public PanelRenderer() {
		setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5)); 
		setLayout(new GridLayout(1, 3, 5, 0));
		setOpaque(false);
		
		image = new ImageIcon(OrderCard.class.getResource("/images/deleteClient.png")).getImage().getScaledInstance(25, 25, Image.SCALE_SMOOTH);
		imageIcon = new ImageIcon(image);
		RoundButton deleteBttn = new RoundButton(30, imageIcon);
		deleteBttn.setBackground(Color.decode("#EF2D2D"));
		this.add(deleteBttn);
		
		deleteBttn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "hola", "si", JOptionPane.ERROR_MESSAGE); 
			}
		});
		
		deleteBttn.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent evt) {
				deleteBttn.setBackground(Color.decode("#ED5C5C"));
		    }

		    public void mouseExited(MouseEvent evt) {
		    	deleteBttn.setBackground(Color.decode("#EF2D2D"));
		    }
		});
		
		
		image = new ImageIcon(OrderCard.class.getResource("/images/editClient.png")).getImage().getScaledInstance(25, 25, Image.SCALE_SMOOTH);
		imageIcon = new ImageIcon(image);
		RoundButton editBttn = new RoundButton(30, imageIcon);
		editBttn.setBackground(Color.decode("#C07A00"));
		this.add(editBttn);
		
		editBttn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "hola", "si", JOptionPane.ERROR_MESSAGE); 
			}
		});
		
		editBttn.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent evt) {
				editBttn.setBackground(Color.decode("#FFA200"));
		    }

		    public void mouseExited(MouseEvent evt) {
		    	editBttn.setBackground(Color.decode("#C07A00"));
		    }
		});
		
		
		image = new ImageIcon(OrderCard.class.getResource("/images/seeClient.png")).getImage().getScaledInstance(25, 25, Image.SCALE_SMOOTH);
		imageIcon = new ImageIcon(image);
		RoundButton seeBttn = new RoundButton(30, imageIcon);
		seeBttn.setBackground(Color.decode("#2EA623"));
		this.add(seeBttn);
		
		seeBttn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "hola", "si", JOptionPane.ERROR_MESSAGE); 
			}
		});
		
		seeBttn.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent evt) {
				seeBttn.setBackground(Color.decode("#39C82C"));
		    }

		    public void mouseExited(MouseEvent evt) {
		    	seeBttn.setBackground(Color.decode("#2EA623"));
		    }
		});
	}
	
	@Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
		return this;
	}
	
}
