package customClasses;

import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.*;
import javax.swing.border.Border;

public class InventoryFormPanel {
	
	JFrame frame;
	JTextField nameTxtFld;
	JTextField codeTxtFld;
	JTextField quantityTxtFld;
	
	ImageIcon imageIcon;
	Image image;
	int relativeXSize;
	Font font;
	
	public InventoryFormPanel(JFrame frame) {
		this.frame = frame;
	}
	
	public JPanel createInventoryForm() {
		JPanel inventoryPnl = new JPanel();
		inventoryPnl.setBorder(BorderFactory.createEmptyBorder(20, 0, 40, 0)); 
		inventoryPnl.setLayout(new GridLayout(6, 2, 30, 30));	
		inventoryPnl.setOpaque(false); 
		
		
		JLabel nameLbl = new JLabel("Nombre del inventario:");
		nameLbl.setForeground(Color.decode("#244E23")); 
		nameLbl.setFont(new Font("Caladea Bold", Font.BOLD, 20));
		nameLbl.setHorizontalAlignment(SwingConstants.LEFT); 
		nameLbl.setVerticalAlignment(SwingConstants.BOTTOM); 
		nameLbl.setOpaque(false);
		inventoryPnl.add(nameLbl);
		
		JLabel codeLbl = new JLabel("Código de inventario:");
		codeLbl.setForeground(Color.decode("#244E23")); 
		codeLbl.setFont(new Font("Caladea Bold", Font.BOLD, 20));
		codeLbl.setHorizontalAlignment(SwingConstants.LEFT); 
		codeLbl.setVerticalAlignment(SwingConstants.BOTTOM); 
		codeLbl.setOpaque(false);
		inventoryPnl.add(codeLbl);
		
		RoundPanel nameTxtFldPnl = new RoundPanel(30);  
		nameTxtFldPnl.setBackground(Color.decode("#EDEDED"));
		nameTxtFldPnl.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));  
		nameTxtFldPnl.setForeground(Color.decode("#244E23")); 
		nameTxtFldPnl.setLayout(new BorderLayout());
		inventoryPnl.add(nameTxtFldPnl);
		
		nameTxtFld = new JTextField("");
		nameTxtFld.setBorder(null);
		nameTxtFld.setFont(new Font("Caladea Bold", Font.BOLD, 20)); 
		nameTxtFld.setForeground(Color.decode("#244E23")); 
		nameTxtFld.setOpaque(false);
		nameTxtFldPnl.add(nameTxtFld, BorderLayout.CENTER);
		
		nameTxtFld.addKeyListener((KeyListener) new KeyAdapter() {
		    @Override
		    public void keyTyped(KeyEvent e) {
		        char c = e.getKeyChar();
		        if (!Character.isLetter(c) && !Character.isWhitespace(c)) 
		            e.consume();
		    }
		});
		
		
		RoundPanel codeTxtFldPnl = new RoundPanel(30);  
		codeTxtFldPnl.setBackground(Color.decode("#EDEDED"));
		codeTxtFldPnl.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));  
		codeTxtFldPnl.setForeground(Color.decode("#244E23")); 
		codeTxtFldPnl.setLayout(new BorderLayout());
		inventoryPnl.add(codeTxtFldPnl);
		
		codeTxtFld = new JTextField("");
		codeTxtFld.setBorder(null);
		codeTxtFld.setFont(new Font("Caladea Bold", Font.BOLD, 20)); 
		codeTxtFld.setForeground(Color.decode("#244E23")); 
		codeTxtFld.setOpaque(false);
		codeTxtFldPnl.add(codeTxtFld, BorderLayout.CENTER);
		
		codeTxtFld.addKeyListener((KeyListener) new KeyAdapter() {
		    @Override
		    public void keyTyped(KeyEvent e) {
		        char c = e.getKeyChar();
		        if (!Character.isLetter(c) && !Character.isDigit(c)) 
		            e.consume();
		    }
		});
		
		
		JLabel quantityLbl = new JLabel("Cantidad:");
		quantityLbl.setForeground(Color.decode("#244E23")); 
		quantityLbl.setFont(new Font("Caladea Bold", Font.BOLD, 20));
		quantityLbl.setHorizontalAlignment(SwingConstants.LEFT); 
		quantityLbl.setVerticalAlignment(SwingConstants.BOTTOM); 
		quantityLbl.setOpaque(false);
		inventoryPnl.add(quantityLbl);
		
		inventoryPnl.add(Box.createHorizontalStrut(0));
		
		
		RoundPanel quantityTxtFldPnl = new RoundPanel(30);  
		quantityTxtFldPnl.setBackground(Color.decode("#EDEDED"));
		quantityTxtFldPnl.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));  
		quantityTxtFldPnl.setForeground(Color.decode("#244E23")); 
		quantityTxtFldPnl.setLayout(new BorderLayout());
		inventoryPnl.add(quantityTxtFldPnl);
		
		quantityTxtFld = new JTextField("");
		quantityTxtFld.setBorder(null);
		quantityTxtFld.setFont(new Font("Caladea Bold", Font.BOLD, 20)); 
		quantityTxtFld.setForeground(Color.decode("#244E23")); 
		quantityTxtFld.setOpaque(false);
		quantityTxtFldPnl.add(quantityTxtFld, BorderLayout.CENTER);
		
		quantityTxtFld.addKeyListener((KeyListener) new KeyAdapter() {
		    @Override
		    public void keyTyped(KeyEvent e) {
		        char c = e.getKeyChar();
		        if(!Character.isDigit(c) || getQuantityTxtFld().substring(0).equals("0")) {
		            e.consume();
		            
		            if(getQuantityTxtFld().substring(0).equals("0")) 
		            	setQuantityTxtFld("");
		           
		        }else
		        	quantityTxtFld.setForeground(Color.decode("#244E23")); 
		    }
		});
		
		inventoryPnl.add(Box.createHorizontalStrut(0));
		
		inventoryPnl.add(Box.createHorizontalStrut(0));
		inventoryPnl.add(Box.createHorizontalStrut(0));
		
		inventoryPnl.add(Box.createHorizontalStrut(0));
		inventoryPnl.add(Box.createHorizontalStrut(0));
		
		/**cuando la ventana es redimensionada, los elementos dentro de ella cambian de tamaño**/
		frame.addComponentListener(new ComponentAdapter() {
            public void componentResized(ComponentEvent e) {
            	relativeXSize = (int) (frame.getHeight()*0.02);
            	font = new Font("Caladea Bold", Font.BOLD, relativeXSize);
       			nameLbl.setFont(font);
       			codeLbl.setFont(font);
       			quantityLbl.setFont(font);
       			nameTxtFld.setFont(font);
       			codeTxtFld.setFont(font);
       			quantityTxtFld.setFont(font);
       			
       			relativeXSize = (int) (frame.getHeight()*0.01);
       			Border border = BorderFactory.createEmptyBorder(relativeXSize, relativeXSize, relativeXSize, relativeXSize);
       			nameTxtFldPnl.setBorder(border);  
       			codeTxtFldPnl.setBorder(border);  
       			quantityTxtFldPnl.setBorder(border); 
       			
       			frame.repaint();
            }
        });
		
		return inventoryPnl;
	}
	
	
	public boolean invFormEmptyFields() {
		String name = getNameTxtFld(); 
		String code = getCodeTxtFld();
		String quantity = getQuantityTxtFld(); 
		
		if(quantity.equals("0")) 
			quantityTxtFld.setForeground(Color.decode("#EF2D2D"));
		
		
		if(name.equals("") || code.equals("") || quantity.equals("") || quantity.equals("0")) {
			OptionPaneButton option = new OptionPaneButton("Campos vacíos", "Complete los campos para guardar la información.");
			option.warningOptionPane();
			
			return true;
		}else {
			return false;
		}
	}
	
	
	public String getNameTxtFld() {
		return nameTxtFld.getText();
	}
	
	public String getCodeTxtFld() {
		return codeTxtFld.getText();
	}
	
	public String getQuantityTxtFld() {
		return quantityTxtFld.getText();
	}
	
	
	public void setNameTxtFld(String name) {
		this.nameTxtFld.setText(name);
	}
	
	public void setCodeTxtFld(String code) {
		this.codeTxtFld.setText(code);
	}
	
	public void setQuantityTxtFld(String quantity) {
		this.quantityTxtFld.setText(quantity);
	}
	
}
