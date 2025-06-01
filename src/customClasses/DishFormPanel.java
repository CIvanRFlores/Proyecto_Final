package customClasses;

import java.awt.*;
import java.awt.event.*;
import java.util.List;

import javax.swing.*;
import javax.swing.border.Border;

import views.ClientView;

public class DishFormPanel {
	
	public JFrame frame;
	public JLabel dishNameLbl;
	public JLabel uploadPhotoLbl;
	public RoundPanel nameTxtFldPnl;
	public JTextField nameTxtFld;
	public JLabel priceLbl;
	public RoundPanel priceTxtFldPnl;
	public JTextField priceTxtFld;
	public RoundPanel uploadPhotoPnl;
	public JLabel uploadImageLbl;
	public RoundButton uploadFileBttn;
	public JLabel descriptionLbl;
	public RoundPanel descTxtFldPnl;
	public JTextArea dishDescText;
	
	public JLabel topLeftLbl;
	public JList<String> topLeftCmbBx;
	public JLabel topRightLbl;
	public JList<String> topRightCmbBx;
	public JLabel bottomLeftLbl;
	public JList<String> bottomLeftCmbBx;
	public JLabel bottomRightLbl;
	public JList<String> bottomRightCmbBx;

	public ImageIcon imageIcon;
	public Image image;
	public int relativeXSize;
	public int relativeYSize;
	public Font font;
	public String dishType;
	
	public DishFormPanel(JFrame frame, String dishType) {
		this.frame = frame;
		this.dishType = dishType;
	}
	
	public JPanel createDishForm() {
		JPanel newDishPnl = new JPanel();
		newDishPnl.setBackground(Color.white);
		newDishPnl.setLayout(new GridLayout(2, 1, 30, 30));
		
		JPanel infoPnl = new JPanel();
		infoPnl.setBackground(Color.white);
		infoPnl.setLayout(new BorderLayout(0, 20 ));
		newDishPnl.add(infoPnl);
		
		JPanel headerInfoPnl = new JPanel();
		headerInfoPnl.setLayout(new GridLayout(1, 2, 20, 0));
		headerInfoPnl.setOpaque(false);
		infoPnl.add(headerInfoPnl, BorderLayout.NORTH);
		
		dishNameLbl = new JLabel(dishType.equals("platillo")?"Nombre del "+dishType+":" : "Nombre de la "+dishType+":");
		dishNameLbl.setFont(new Font("Caladea Regular", Font.PLAIN, 30));
		dishNameLbl.setForeground(Color.decode("#244E23")); 
		dishNameLbl.setHorizontalAlignment(SwingConstants.LEFT);
		headerInfoPnl.add(dishNameLbl);
		
		uploadPhotoLbl = new JLabel("Subir foto:");
		uploadPhotoLbl.setFont(new Font("Caladea Regular", Font.PLAIN, 30));
		uploadPhotoLbl.setForeground(Color.decode("#244E23")); 
		uploadPhotoLbl.setHorizontalAlignment(SwingConstants.LEFT);
		headerInfoPnl.add(uploadPhotoLbl);
		
		JPanel centerInfoPnl = new JPanel();
		centerInfoPnl.setLayout(new GridLayout(1, 2, 30, 0));
		centerInfoPnl.setOpaque(false);
		infoPnl.add(centerInfoPnl, BorderLayout.CENTER);
		
		JPanel leftInfoPnl = new JPanel();
		leftInfoPnl.setLayout(new GridLayout(3, 1));
		leftInfoPnl.setOpaque(false);
		centerInfoPnl.add(leftInfoPnl);
		
		nameTxtFldPnl = new RoundPanel(30);  
		nameTxtFldPnl.setBackground(Color.white);
		nameTxtFldPnl.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); 
		nameTxtFldPnl.setForeground(Color.decode("#244E23")); 
		nameTxtFldPnl.setLayout(new BorderLayout());
		leftInfoPnl.add(nameTxtFldPnl);
		
		nameTxtFld = new JTextField("");
		nameTxtFld.setBorder(null);
		nameTxtFld.setFont(new Font("Caladea Bold", Font.BOLD, 16)); 
		nameTxtFld.setForeground(Color.decode("#244E23"));
		nameTxtFld.setOpaque(false); 
		nameTxtFldPnl.add(nameTxtFld, BorderLayout.CENTER);
		
		nameTxtFld.addKeyListener(new KeyAdapter() {
		    @Override
		    public void keyTyped(KeyEvent e) {
		        char c = e.getKeyChar();
		        if(!Character.isLetter(c) && !Character.isWhitespace(c)) {
		            e.consume();
		    }
		}});
		
		
		priceLbl = new JLabel("Precio:");
		priceLbl.setFont(new Font("Caladea Regular", Font.PLAIN, 30));
		priceLbl.setForeground(Color.decode("#244E23")); 
		priceLbl.setHorizontalAlignment(SwingConstants.LEFT);
		leftInfoPnl.add(priceLbl);
		
		priceTxtFldPnl = new RoundPanel(30);  
		priceTxtFldPnl.setBackground(Color.white);
		priceTxtFldPnl.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); 
		priceTxtFldPnl.setForeground(Color.decode("#244E23")); 
		priceTxtFldPnl.setLayout(new BorderLayout());
		leftInfoPnl.add(priceTxtFldPnl);
		
		priceTxtFld = new JTextField("");
		priceTxtFld.setBorder(null);
		priceTxtFld.setFont(new Font("Caladea Bold", Font.BOLD, 16)); 
		priceTxtFld.setForeground(Color.decode("#244E23"));
		priceTxtFld.setOpaque(false); 
		priceTxtFldPnl.add(priceTxtFld, BorderLayout.CENTER);
		
		priceTxtFld.addKeyListener(new KeyAdapter() {
		    @Override
		    public void keyTyped(KeyEvent e) {
		        char c = e.getKeyChar();
		        if (!Character.isDigit(c)) {
		            e.consume();
		    }
		}});
		
		
		JPanel rightInfoPnl = new JPanel();
		rightInfoPnl.setLayout(new BorderLayout(0, 20));
		rightInfoPnl.setOpaque(false);
		centerInfoPnl.add(rightInfoPnl);
		
		uploadPhotoPnl = new RoundPanel(30);  
		uploadPhotoPnl.setBackground(Color.white);
		uploadPhotoPnl.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20)); 
		uploadPhotoPnl.setForeground(Color.decode("#244E23")); 
		uploadPhotoPnl.setLayout(new BorderLayout());
		rightInfoPnl.add(uploadPhotoPnl,  BorderLayout.CENTER);
		
		image = new ImageIcon(DishFormPanel.class.getResource("/images/uploadFile.png")).getImage().getScaledInstance(90, 120, Image.SCALE_SMOOTH);
		imageIcon = new ImageIcon(image);
		uploadImageLbl = new JLabel(imageIcon);
		uploadPhotoPnl.add(uploadImageLbl, BorderLayout.CENTER);
		
		uploadFileBttn = new RoundButton(30);
		uploadFileBttn.setBackground(Color.decode("#244E23"));
		uploadFileBttn.setFont(new Font("Caladea Bold", Font.BOLD, 30));
		uploadFileBttn.setForeground(Color.white);
		uploadFileBttn.setText("Examinar");
		uploadPhotoPnl.add(uploadFileBttn, BorderLayout.SOUTH); 
		
		uploadFileBttn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		
		uploadFileBttn.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent evt) {
				uploadFileBttn.setBackground(Color.decode("#3C7E3A"));
		    }

		    public void mouseExited(MouseEvent evt) {
		    	uploadFileBttn.setBackground(Color.decode("#244E23"));
		    }
		});
		
		
		JPanel footerInfoPnl = new JPanel();
		footerInfoPnl.setLayout(new BorderLayout(0, 20));
		footerInfoPnl.setOpaque(false);
		newDishPnl.add(footerInfoPnl);
		
		descriptionLbl = new JLabel("Descripción:");
		descriptionLbl.setFont(new Font("Caladea Regular", Font.PLAIN, 30));
		descriptionLbl.setForeground(Color.decode("#244E23")); 
		descriptionLbl.setHorizontalAlignment(SwingConstants.LEFT);
		footerInfoPnl.add(descriptionLbl, BorderLayout.NORTH);
		
		descTxtFldPnl = new RoundPanel(30);  
		descTxtFldPnl.setBackground(Color.white);
		descTxtFldPnl.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20)); 
		descTxtFldPnl.setForeground(Color.decode("#244E23")); 
		descTxtFldPnl.setLayout(new BorderLayout());
		footerInfoPnl.add(descTxtFldPnl, BorderLayout.CENTER);
		
		dishDescText = new JTextArea("");
		dishDescText.setForeground(Color.decode("#244E23"));
		dishDescText.setFont(new Font("Caladea Bold", Font.BOLD, 16));
		dishDescText.setLineWrap(true); 
		dishDescText.setWrapStyleWord(true); 
		dishDescText.setOpaque(false);
		descTxtFldPnl.add(dishDescText, BorderLayout.CENTER);
		
		dishDescText.addKeyListener(new KeyAdapter() {
		    @Override
		    public void keyTyped(KeyEvent e) {
		        char c = e.getKeyChar();
		        if(!Character.isLetter(c) && !Character.isWhitespace(c)) {
		            e.consume();
		    }
		}});
		
		
		/**cuando la ventana es redimensionada, los elementos dentro de ella cambian de tamaño**/
		frame.addComponentListener(new ComponentAdapter() {
            public void componentResized(ComponentEvent e) {
            	relativeXSize = (int) (frame.getHeight()*0.03);
            	font = new Font("Caladea Regular", Font.PLAIN, relativeXSize);
            	dishNameLbl.setFont(font);
            	uploadPhotoLbl.setFont(font);
            	priceLbl.setFont(font);
            	descriptionLbl.setFont(font);
            	
            	relativeXSize = (int) (frame.getHeight()*0.016);
            	font = new Font("Caladea Bold", Font.BOLD, relativeXSize);
            	nameTxtFld.setFont(font); 
            	priceTxtFld.setFont(font); 
            	dishDescText.setFont(font);
            	
            	relativeXSize = (int) (frame.getHeight()*0.03);
            	uploadFileBttn.setFont(new Font("Caladea Bold", Font.BOLD, relativeXSize));
            	
            	relativeXSize = (int) (frame.getHeight()*0.09);
            	relativeYSize = (int) (frame.getHeight ()*0.120);
            	image = new ImageIcon(DishFormPanel.class.getResource("/images/uploadFile.png")).getImage().getScaledInstance(relativeXSize, relativeYSize, Image.SCALE_SMOOTH);
        		imageIcon = new ImageIcon(image);
        		uploadImageLbl.setIcon(imageIcon);
            	
            	
       			relativeXSize = (int) (frame.getHeight()*0.02);
       			uploadPhotoPnl.setBorder(BorderFactory.createEmptyBorder(relativeXSize, relativeXSize, relativeXSize, relativeXSize));
       			descTxtFldPnl.setBorder(BorderFactory.createEmptyBorder(relativeXSize, relativeXSize, relativeXSize, relativeXSize));
       			
       			relativeXSize = (int) (frame.getHeight()*0.01);
       			Border border = BorderFactory.createEmptyBorder(relativeXSize, relativeXSize, relativeXSize, relativeXSize);
       			nameTxtFldPnl.setBorder(border);  
       			priceTxtFldPnl.setBorder(border);    
       			
       			frame.repaint();
            }
        });
		
		return newDishPnl;
	}
	
	public JPanel createIngredientsForm() {
		JPanel newDishPnl = new JPanel();
		newDishPnl.setBackground(Color.white);
		newDishPnl.setLayout(new GridLayout(2, 2, 30, 30));
		
		
		JPanel topLeftPnl = new JPanel();
		topLeftPnl.setLayout(new BorderLayout(0, 20));
		topLeftPnl.setOpaque(false);
		newDishPnl.add(topLeftPnl);
		
		topLeftLbl = new JLabel(dishType.equals("platillo")?"Proteínas:" : "Base:");
		topLeftLbl.setFont(new Font("Caladea Regular", Font.PLAIN, 30));
		topLeftLbl.setForeground(Color.decode("#244E23")); 
		topLeftLbl.setHorizontalAlignment(SwingConstants.LEFT);
		topLeftPnl.add(topLeftLbl, BorderLayout.NORTH);
		
		String ingredients[] = { "Maíz", "Camarón", "Papa", "Mantequilla", "Aceite", "Camote", "Tomate", "Agua"};
		
		topLeftCmbBx = new JList<>(ingredients);
		topLeftCmbBx.setBorder(BorderFactory.createLineBorder(Color.decode("#244E23"), 2));
		topLeftCmbBx.setForeground(Color.decode("#244E23")); 
		topLeftCmbBx.setFont(new Font("Caladea Regular", Font.PLAIN, 30)); 
		topLeftCmbBx.setOpaque(false);
		topLeftCmbBx.setVisibleRowCount(3);
		topLeftCmbBx.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		topLeftPnl.add(new JScrollPane(topLeftCmbBx), BorderLayout.CENTER);
		
		
		JPanel topRightPnl = new JPanel();
		topRightPnl.setLayout(new BorderLayout(0, 20));
		topRightPnl.setOpaque(false);
		newDishPnl.add(topRightPnl);
		
		topRightLbl = new JLabel(dishType.equals("platillo")?"Vegetales:" : "Jugo:");
		topRightLbl.setFont(new Font("Caladea Regular", Font.PLAIN, 30));
		topRightLbl.setForeground(Color.decode("#244E23")); 
		topRightLbl.setHorizontalAlignment(SwingConstants.LEFT);
		topRightPnl.add(topRightLbl, BorderLayout.NORTH);
		
		topRightCmbBx = new JList<>(ingredients);
		topRightCmbBx.setBorder(BorderFactory.createLineBorder(Color.decode("#244E23"), 2));
		topRightCmbBx.setForeground(Color.decode("#244E23")); 
		topRightCmbBx.setFont(new Font("Caladea Regular", Font.PLAIN, 30)); 
		topRightCmbBx.setOpaque(false);
		topRightCmbBx.setVisibleRowCount(3);
		topRightCmbBx.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		topRightPnl.add(new JScrollPane(topRightCmbBx), BorderLayout.CENTER);
		
		
		JPanel bottomLeftPnl = new JPanel();
		bottomLeftPnl.setLayout(new BorderLayout(0, 20));
		bottomLeftPnl.setOpaque(false);
		newDishPnl.add(bottomLeftPnl);
		
		bottomLeftLbl = new JLabel(dishType.equals("platillo")?"Carbohidratos:" : "Licor:");
		bottomLeftLbl.setFont(new Font("Caladea Regular", Font.PLAIN, 30));
		bottomLeftLbl.setForeground(Color.decode("#244E23")); 
		bottomLeftLbl.setHorizontalAlignment(SwingConstants.LEFT);
		bottomLeftPnl.add(bottomLeftLbl, BorderLayout.NORTH);
		
		bottomLeftCmbBx = new JList<>(ingredients);
		bottomLeftCmbBx.setBorder(BorderFactory.createLineBorder(Color.decode("#244E23"), 2));
		bottomLeftCmbBx.setForeground(Color.decode("#244E23")); 
		bottomLeftCmbBx.setFont(new Font("Caladea Regular", Font.PLAIN, 30)); 
		bottomLeftCmbBx.setOpaque(false);
		bottomLeftCmbBx.setVisibleRowCount(3);
		bottomLeftCmbBx.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		bottomLeftPnl.add(new JScrollPane(bottomLeftCmbBx), BorderLayout.CENTER);
		
		
		JPanel bottomRightPnl = new JPanel();
		bottomRightPnl.setLayout(new BorderLayout(0, 20));
		bottomRightPnl.setOpaque(false);
		newDishPnl.add(bottomRightPnl);
		
		bottomRightLbl = new JLabel(dishType.equals("platillo")?"Especias:" : "Extra:");
		bottomRightLbl.setFont(new Font("Caladea Regular", Font.PLAIN, 30));
		bottomRightLbl.setForeground(Color.decode("#244E23")); 
		bottomRightLbl.setHorizontalAlignment(SwingConstants.LEFT);
		bottomRightPnl.add(bottomRightLbl, BorderLayout.NORTH);
		
		bottomRightCmbBx = new JList<>(ingredients);
		bottomRightCmbBx.setBorder(BorderFactory.createLineBorder(Color.decode("#244E23"), 2));
		bottomRightCmbBx.setForeground(Color.decode("#244E23")); 
		bottomRightCmbBx.setFont(new Font("Caladea Regular", Font.PLAIN, 30)); 
		bottomRightCmbBx.setOpaque(false);
		bottomRightCmbBx.setVisibleRowCount(3);
		bottomRightCmbBx.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		bottomRightPnl.add(new JScrollPane(bottomRightCmbBx), BorderLayout.CENTER);
		
		/**cuando la ventana es redimensionada, los elementos dentro de ella cambian de tamaño**/
		frame.addComponentListener(new ComponentAdapter() {
            public void componentResized(ComponentEvent e) {
            	relativeXSize = (int) (frame.getHeight()*0.03);
            	font = new Font("Caladea Regular", Font.PLAIN, relativeXSize);
            	topLeftLbl.setFont(font);
            	topRightLbl.setFont(font);
            	bottomLeftLbl.setFont(font);
            	bottomRightLbl.setFont(font);
            	
            	topLeftCmbBx.setFont(font);
            	topRightCmbBx.setFont(font);
            	bottomLeftCmbBx.setFont(font);
            	bottomRightCmbBx.setFont(font);    
       			
       			frame.repaint();
            }
        });
		
		return newDishPnl; 
	}
	
	
	public boolean dishFormEmptyFields() {
		String name = getNameTxtFld(); 
		String price = getPriceTxtFld();
		String description = getDishDescText(); 
		
		if(name.equals("") || price.equals("") || description.equals("")) {
			image = new ImageIcon(ClientView.class.getResource("/images/warning.png")).getImage().getScaledInstance(45, 45, Image.SCALE_SMOOTH); 
			imageIcon = new ImageIcon(image);
				
			String message = "Complete los campos para guardar la información.";
			JOptionPane.showMessageDialog(null, message, "Campos vacíos", JOptionPane.INFORMATION_MESSAGE, imageIcon);
			return true;
		}else { 
			return false;
		}
	}
	
	public boolean ingredientFormEmptyFields() {
		String topLeft = getTopLeftCmbBx(); 
		String topRight = getTopRightCmbBx();
		String bottomLeft = getBottomLeftCmbBx(); 
		String bottomRight = getBottomRightCmbBx(); 
		
		if(topLeft.equals("[]") || topRight.equals("[]") || bottomLeft.equals("[]") || bottomRight.equals("[]")) {
			image = new ImageIcon(DishFormPanel.class.getResource("/images/warning.png")).getImage().getScaledInstance(45, 45, Image.SCALE_SMOOTH); 
			imageIcon = new ImageIcon(image);
				
			String message = "Seleccione los ingredientes para guardar la información.";
			JOptionPane.showMessageDialog(null, message, "Campos vacíos", JOptionPane.INFORMATION_MESSAGE, imageIcon);
			
			return true;
		}else { 
			return false;
		}
	}
	
	
	public String getNameTxtFld() {
		return nameTxtFld.getText();
	}
	
	public String getPriceTxtFld() {
		return priceTxtFld.getText(); 
	}
	
	public String getDishDescText() {
		return dishDescText.getText(); 
	}
	
	public String getTopLeftCmbBx() {
		List<String> selectedValues = topLeftCmbBx.getSelectedValuesList();
		return selectedValues.toString(); 
	}
	
	public String getTopRightCmbBx() {
		List<String> selectedValues = topRightCmbBx.getSelectedValuesList();
		return selectedValues.toString(); 
	}
	
	public String getBottomLeftCmbBx() {
		List<String> selectedValues = bottomLeftCmbBx.getSelectedValuesList();
		return selectedValues.toString(); 
	}
	
	public String getBottomRightCmbBx() {
		List<String> selectedValues = bottomRightCmbBx.getSelectedValuesList();
		return selectedValues.toString(); 
	}
}
