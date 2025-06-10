package customClasses;

import java.awt.*;
import java.awt.event.*;
import java.net.URL;

import javax.swing.*;

import controllers.DishController;

public class DishCard {
	
	JFrame frame;
	int radius;
	URL dishImageURL;
	String type;
	String dishName;
	Image image;
	ImageIcon imageIcon;
	int relativeXSize;
	int relativeYSize;
	DishController dc;
	OptionPaneButton loadingOptPn;

	public DishCard(int radius, URL dishImageURL, String type, String dishName, JFrame frame) {
		this.frame = frame;
		this.radius = radius;
		this.dishImageURL = dishImageURL;
		this.type = type;
		this.dishName = dishName;
	}
	
	
	public RoundPanel createCard() {
		dc = new DishController(frame.getTitle(), frame.getWidth(), frame.getHeight());
		
		RoundPanel cardPnl = new RoundPanel(radius);  
		cardPnl.setBackground(Color.decode("#EDEDED"));
		cardPnl.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); 
		cardPnl.setForeground(Color.decode("#EDEDED")); 
		cardPnl.setLayout(new GridLayout(2, 1));
		
		cardPnl.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent evt) {
				loadingOptPn = new OptionPaneButton("Cargando ventana...", "Por favor espere.");
				loadingOptPn.loadingOptionPane(frame, 3000);
				frame.dispose();
				dc.dishPage(type);
			}
		});
		
		
		image = new ImageIcon(dishImageURL).getImage().getScaledInstance(140, 90, Image.SCALE_SMOOTH);
		imageIcon = new ImageIcon(image);
		JLabel dishImage = new JLabel(this.imageIcon);
		cardPnl.add(dishImage);
		
		dishImage.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent evt) {
				loadingOptPn = new OptionPaneButton("Cargando ventana...", "Por favor espere.");
				loadingOptPn.loadingOptionPane(frame, 3000);
				frame.dispose();
				dc.dishPage(type);
			}
		});
		
		JTextArea dishNameTxtArea = new JTextArea(dishName);
		dishNameTxtArea.setAlignmentX(Component.CENTER_ALIGNMENT);
		dishNameTxtArea.setBackground(Color.decode("#EDEDED"));
		dishNameTxtArea.setForeground(Color.decode("#244E23")); 
		dishNameTxtArea.setEnabled(false);
		dishNameTxtArea.setDisabledTextColor(Color.decode("#244E23"));
		dishNameTxtArea.setFont(new Font("Caladea Bold", Font.BOLD, 20));
		dishNameTxtArea.setMargin(new Insets(10, 15, 10, 10));
		dishNameTxtArea.setLineWrap(true);
		dishNameTxtArea.setWrapStyleWord(true);
		dishNameTxtArea.setOpaque(true);
		cardPnl.add(dishNameTxtArea);
		
		dishNameTxtArea.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent evt) {
				loadingOptPn = new OptionPaneButton("Cargando ventana...", "Por favor espere.");
				loadingOptPn.loadingOptionPane(frame, 3000);
				frame.dispose();
				dc.dishPage(type);
			}
		});
		
		dishNameTxtArea.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent evt) {
				dishNameTxtArea.setForeground(Color.decode("#3C7E3A")); 
				dishNameTxtArea.setDisabledTextColor(Color.decode("#3C7E3A"));
		    }

		    public void mouseExited(MouseEvent evt) {
		    	dishNameTxtArea.setForeground(Color.decode("#244E23")); 
		    	dishNameTxtArea.setDisabledTextColor(Color.decode("#244E23"));
		    }
		});
		
		
		/**cuando la ventana es redimensionada, los elementos dentro de ella cambian de tamaño**/
		frame.addComponentListener(new ComponentAdapter() {
            public void componentResized(ComponentEvent e) {
            	relativeXSize = (int) (frame.getHeight()*0.01);
            	cardPnl.setBorder(BorderFactory.createEmptyBorder(relativeXSize, relativeXSize, relativeXSize, relativeXSize)); 
            	
            	relativeXSize = (int) (frame.getWidth()*0.140);
            	relativeYSize = (int) (frame.getWidth()*0.09);
               	image = new ImageIcon(dishImageURL).getImage().getScaledInstance(relativeXSize, relativeYSize, Image.SCALE_SMOOTH);
       			imageIcon = new ImageIcon(image);
       			dishImage.setIcon(imageIcon);  
       		
       			dishNameTxtArea.setFont(new Font("Caladea Bold", Font.BOLD, (int) (frame.getWidth()*0.02)));
       			
       			frame.repaint();
            }
        });
		
		return cardPnl; 
	}
	
	public RoundPanel createAddableCard(OrderTabPanel orderTab) {
		
		RoundPanel cardPnl = new RoundPanel(radius);  
		cardPnl.setBackground(Color.decode("#EDEDED"));
		cardPnl.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); 
		cardPnl.setForeground(Color.decode("#EDEDED")); 
		cardPnl.setLayout(new GridLayout(2, 1));
		
		cardPnl.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent evt) {
				if(!orderTab.duplicateOrder(dishName)) 
					orderTab.addDishOrder(dishName, 365.00); //enviar nombre y precio
			}
		});
		
		
		image = new ImageIcon(dishImageURL).getImage().getScaledInstance(78, 50, Image.SCALE_SMOOTH);
		imageIcon = new ImageIcon(image);
		JLabel dishImage = new JLabel(this.imageIcon);
		cardPnl.add(dishImage);
		
		dishImage.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent evt) {
				if(!orderTab.duplicateOrder(dishName)) 
					orderTab.addDishOrder(dishName, 365.00); //enviar nombre y precio
			}
		});
		
		
		JTextArea dishNameTxtArea = new JTextArea(dishName);
		dishNameTxtArea.setAlignmentX(Component.CENTER_ALIGNMENT);
		dishNameTxtArea.setBackground(Color.decode("#EDEDED"));
		dishNameTxtArea.setForeground(Color.decode("#244E23")); 
		dishNameTxtArea.setEnabled(false);
		dishNameTxtArea.setDisabledTextColor(Color.decode("#244E23"));
		dishNameTxtArea.setFont(new Font("Caladea Bold", Font.BOLD, 20));
		dishNameTxtArea.setMargin(new Insets(10, 15, 10, 10));
		dishNameTxtArea.setLineWrap(true);
		dishNameTxtArea.setWrapStyleWord(true);
		dishNameTxtArea.setOpaque(true);
		cardPnl.add(dishNameTxtArea);
		
		dishNameTxtArea.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent evt) {
				if(!orderTab.duplicateOrder(dishName)) 
					orderTab.addDishOrder(dishName, 365.00); //enviar nombre y precio
			}
		});
		
		dishNameTxtArea.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent evt) {
				dishNameTxtArea.setForeground(Color.decode("#3C7E3A")); 
				dishNameTxtArea.setDisabledTextColor(Color.decode("#3C7E3A"));
		    }

		    public void mouseExited(MouseEvent evt) {
		    	dishNameTxtArea.setForeground(Color.decode("#244E23")); 
		    	dishNameTxtArea.setDisabledTextColor(Color.decode("#244E23"));
		    }
		});
		
		
		/**cuando la ventana es redimensionada, los elementos dentro de ella cambian de tamaño**/
		frame.addComponentListener(new ComponentAdapter() {
            public void componentResized(ComponentEvent e) {
            	relativeXSize = (int) (frame.getHeight()*0.01);
            	cardPnl.setBorder(BorderFactory.createEmptyBorder(relativeXSize, relativeXSize, relativeXSize, relativeXSize)); 
            	
            	relativeXSize = (int) (frame.getWidth()*0.078);
            	relativeYSize = (int) (frame.getWidth()*0.05);
               	image = new ImageIcon(dishImageURL).getImage().getScaledInstance(relativeXSize, relativeYSize, Image.SCALE_SMOOTH);
       			imageIcon = new ImageIcon(image);
       			dishImage.setIcon(imageIcon);  
       		
       			dishNameTxtArea.setFont(new Font("Caladea Bold", Font.BOLD, (int) (frame.getWidth()*0.014)));
       			
       			frame.repaint();
            }
        });
		
		return cardPnl; 
	}
	
	/*public String adjustText(String name) {
		String[] separatedText = name.split(" ");
		String adjustedText = "<html><center>";
		
		for(int i=0; i<separatedText.length; i++) {
			adjustedText += separatedText[i] + "<br>";
		}
		
		adjustedText += "</center></html>";
		
		return adjustedText;
	}*/

	public URL getDishImageURL() {
		return dishImageURL;
	}

	public void setDishImageURL(URL dishImageURL) {
		this.dishImageURL = dishImageURL;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getDishName() {
		return dishName;
	}

	public void setDishName(String dishName) {
		this.dishName = dishName;
	}

}
