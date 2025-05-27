package customClasses;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

public class ActionButtonPanel extends JPanel{
	
	private static final long serialVersionUID = -689991868671728381L;
	
	public RoundButton deleteBttn, editBttn, seeBttn;
	public Image image;
	public ImageIcon imageIcon;
	
	public ActionButtonPanel() {
		setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5)); 
		setLayout(new GridLayout(1, 3, 5, 0));
		setOpaque(false);
		
		image = new ImageIcon(ActionButtonPanel.class.getResource("/images/deleteClient.png")).getImage().getScaledInstance(25, 25, Image.SCALE_SMOOTH);
		imageIcon = new ImageIcon(image);
		deleteBttn = new RoundButton(15, imageIcon);
		deleteBttn.setBackground(Color.decode("#EF2D2D"));
		this.add(deleteBttn);
		
		deleteBttn.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent evt) {
				deleteBttn.setBackground(Color.decode("#ED5C5C"));
		    }

		    public void mouseExited(MouseEvent evt) {
		    	deleteBttn.setBackground(Color.decode("#EF2D2D"));
		    }
		});
		
		
		image = new ImageIcon(ActionButtonPanel.class.getResource("/images/editClient.png")).getImage().getScaledInstance(25, 25, Image.SCALE_SMOOTH);
		imageIcon = new ImageIcon(image);
		editBttn = new RoundButton(15, imageIcon);
		editBttn.setBackground(Color.decode("#C07A00"));
		this.add(editBttn);
		
		editBttn.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent evt) {
				editBttn.setBackground(Color.decode("#FFA200"));
		    }

		    public void mouseExited(MouseEvent evt) {
		    	editBttn.setBackground(Color.decode("#C07A00"));
		    }
		});
		
		
		image = new ImageIcon(ActionButtonPanel.class.getResource("/images/seeClient.png")).getImage().getScaledInstance(25, 25, Image.SCALE_SMOOTH);
		imageIcon = new ImageIcon(image);
		seeBttn = new RoundButton(15, imageIcon);
		seeBttn.setBackground(Color.decode("#2EA623"));
		this.add(seeBttn);
		
		seeBttn.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent evt) {
				seeBttn.setBackground(Color.decode("#39C82C"));
		    }

		    public void mouseExited(MouseEvent evt) {
		    	seeBttn.setBackground(Color.decode("#2EA623"));
		    }
		});
	}
	
	//dar click a celda
	public void initEvent(TableActionEvent event, int row) {
		deleteBttn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	Object[] options = {"Volver", "Eliminar"};
				
				image = new ImageIcon(ActionButtonPanel.class.getResource("/images/errorCircle.png")).getImage().getScaledInstance(45, 45, Image.SCALE_SMOOTH);
				imageIcon = new ImageIcon(image);
				
				String message = "Esta acción no se puede deshacer.";
				int opc = JOptionPane.showOptionDialog(null, message, "Borrar cliente", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE, imageIcon, options, null);
				
				if(opc==1)
					event.onDelete(row);
            }
        });
		
        editBttn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                event.onEdit(row);
            }
        });
        
        seeBttn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                event.onView(row);
            }
        });
    }
}
