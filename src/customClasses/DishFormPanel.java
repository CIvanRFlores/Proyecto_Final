package customClasses;

import java.awt.*;
import java.awt.event.*; 
import java.util.List;

import javax.swing.*;
import javax.swing.border.Border;

public class DishFormPanel {
	
	JFrame frame;
	JTextField nameTxtFld;
	JTextField priceTxtFld;
	JTextArea dishDescText;

	ColoredScrollPaneBar coloredScrollPane;
	JList<String> topLeftCmbBx;
	JList<String> topRightCmbBx;
	JList<String> bottomLeftCmbBx;
	JList<String> bottomRightCmbBx;

	ImageIcon imageIcon;
	Image image;
	int relativeXSize;
	int relativeYSize;
	Font font;
	String dishType;
	
	public DishFormPanel(JFrame frame, String dishType) {
		this.frame = frame;
		this.dishType = dishType;
	}
	
	public JPanel createDishForm() {
		JPanel newDishPnl = new JPanel();
		newDishPnl.setBackground(Color.white);
		newDishPnl.setBorder(BorderFactory.createEmptyBorder(40, 0, 40, 0));
		newDishPnl.setLayout(new GridLayout(2, 1, 30, 30));
		
		JPanel infoPnl = new JPanel();
		infoPnl.setBackground(Color.white);
		infoPnl.setLayout(new BorderLayout(0, 20 ));
		newDishPnl.add(infoPnl);
		
		JPanel headerInfoPnl = new JPanel();
		headerInfoPnl.setLayout(new GridLayout(1, 2, 20, 0));
		headerInfoPnl.setOpaque(false);
		infoPnl.add(headerInfoPnl, BorderLayout.NORTH);
		
		JLabel dishNameLbl = new JLabel(dishType.equals("platillo")?"Nombre del "+dishType+":" : "Nombre de la "+dishType+":");
		dishNameLbl.setFont(new Font("Caladea Regular", Font.PLAIN, 30));
		dishNameLbl.setForeground(Color.decode("#244E23")); 
		dishNameLbl.setHorizontalAlignment(SwingConstants.LEFT);
		headerInfoPnl.add(dishNameLbl);
		
		JLabel uploadPhotoLbl = new JLabel("Subir foto:");
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
		
		RoundPanel nameTxtFldPnl = new RoundPanel(30);  
		nameTxtFldPnl.setBackground(Color.white);
		nameTxtFldPnl.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); 
		nameTxtFldPnl.setForeground(Color.decode("#244E23")); 
		nameTxtFldPnl.setLayout(new BorderLayout());
		leftInfoPnl.add(nameTxtFldPnl);
		
		nameTxtFld = new JTextField("");
		nameTxtFld.setBorder(null);
		nameTxtFld.setFont(new Font("Caladea Bold", Font.BOLD, 20)); 
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
		
		
		JLabel priceLbl = new JLabel("Precio:");
		priceLbl.setFont(new Font("Caladea Regular", Font.PLAIN, 30));
		priceLbl.setForeground(Color.decode("#244E23")); 
		priceLbl.setHorizontalAlignment(SwingConstants.LEFT);
		leftInfoPnl.add(priceLbl);
		
		RoundPanel priceTxtFldPnl = new RoundPanel(30);  
		priceTxtFldPnl.setBackground(Color.white);
		priceTxtFldPnl.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); 
		priceTxtFldPnl.setForeground(Color.decode("#244E23")); 
		priceTxtFldPnl.setLayout(new BorderLayout());
		leftInfoPnl.add(priceTxtFldPnl);
		
		priceTxtFld = new JTextField("");
		priceTxtFld.setBorder(null);
		priceTxtFld.setFont(new Font("Caladea Bold", Font.BOLD, 20)); 
		priceTxtFld.setForeground(Color.decode("#244E23"));
		priceTxtFld.setOpaque(false); 
		priceTxtFldPnl.add(priceTxtFld, BorderLayout.CENTER);
		
		priceTxtFld.addKeyListener(new KeyAdapter() {
		    @Override
		    public void keyTyped(KeyEvent e) {
		        char c = e.getKeyChar();
		        if(!Character.isDigit(c) || getPriceTxtFld().substring(0).equals("0")) {
		            e.consume();
		            
		           if(getPriceTxtFld().substring(0).equals("0")) {
		        	   setPriceTxtFld("");
		           }
		        }else {
					priceTxtFld.setForeground(Color.decode("#244E23"));
				}
		    }
		});
		
		
		JPanel rightInfoPnl = new JPanel();
		rightInfoPnl.setLayout(new BorderLayout(0, 20));
		rightInfoPnl.setOpaque(false);
		centerInfoPnl.add(rightInfoPnl);
		
		RoundPanel uploadPhotoPnl = new RoundPanel(30);  
		uploadPhotoPnl.setBackground(Color.white);
		uploadPhotoPnl.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20)); 
		uploadPhotoPnl.setForeground(Color.decode("#244E23")); 
		uploadPhotoPnl.setLayout(new BorderLayout());
		rightInfoPnl.add(uploadPhotoPnl,  BorderLayout.CENTER);
		
		image = new ImageIcon(DishFormPanel.class.getResource("/images/uploadFile.png")).getImage().getScaledInstance(90, 120, Image.SCALE_SMOOTH);
		imageIcon = new ImageIcon(image);
		JLabel uploadImageLbl = new JLabel(imageIcon);
		uploadPhotoPnl.add(uploadImageLbl, BorderLayout.CENTER);
		
		RoundButton uploadFileBttn = new RoundButton(30);
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
		
		JLabel descriptionLbl = new JLabel("Descripción:");
		descriptionLbl.setFont(new Font("Caladea Regular", Font.PLAIN, 30));
		descriptionLbl.setForeground(Color.decode("#244E23")); 
		descriptionLbl.setHorizontalAlignment(SwingConstants.LEFT);
		footerInfoPnl.add(descriptionLbl, BorderLayout.NORTH);
		
		RoundPanel descTxtFldPnl = new RoundPanel(30);  
		descTxtFldPnl.setBackground(Color.white);
		descTxtFldPnl.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20)); 
		descTxtFldPnl.setForeground(Color.decode("#244E23")); 
		descTxtFldPnl.setLayout(new BorderLayout());
		footerInfoPnl.add(descTxtFldPnl, BorderLayout.CENTER);
		
		dishDescText = new JTextArea("");
		dishDescText.setForeground(Color.decode("#244E23"));
		dishDescText.setFont(new Font("Caladea Bold", Font.BOLD, 20));
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
            	
            	relativeXSize = (int) (frame.getHeight()*0.02);
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
		newDishPnl.setBorder(BorderFactory.createEmptyBorder(0, 0, 40, 0));
		newDishPnl.setLayout(new GridLayout(2, 2, 30, 30));
		
		String ingredients[] = { "Maíz", "Camarón", "Papa", "Mantequilla", "Aceite", "Camote", "Tomate", "Agua"};
		
		//panel superior izquierdo
		JPanel topLeftPnl = new JPanel();
		topLeftPnl.setLayout(new BorderLayout(0, 20));
		topLeftPnl.setOpaque(false);
		newDishPnl.add(topLeftPnl);
		
		JLabel topLeftLbl = new JLabel(dishType.equals("platillo")?"Proteínas:" : "Base:");
		topLeftLbl.setFont(new Font("Caladea Regular", Font.PLAIN, 30));
		topLeftLbl.setForeground(Color.decode("#244E23")); 
		topLeftLbl.setHorizontalAlignment(SwingConstants.LEFT);
		topLeftLbl.setVerticalAlignment(SwingConstants.BOTTOM); 
		topLeftPnl.add(topLeftLbl, BorderLayout.NORTH);
		
		RoundPanel topleftCmbBxPanel = new RoundPanel(30);  
		topleftCmbBxPanel.setBackground(Color.white);
		topleftCmbBxPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); 
		topleftCmbBxPanel.setForeground(Color.decode("#244E23")); 
		topleftCmbBxPanel.setLayout(new BorderLayout());
		topLeftPnl.add(topleftCmbBxPanel, BorderLayout.CENTER);
	
		topLeftCmbBx = new JList<>(ingredients);
		topLeftCmbBx.setForeground(Color.decode("#244E23")); 
		topLeftCmbBx.setFont(new Font("Caladea Regular", Font.PLAIN, 30)); 
		topLeftCmbBx.setOpaque(false);
		topLeftCmbBx.setVisibleRowCount(3);
		topLeftCmbBx.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		
		coloredScrollPane = new ColoredScrollPaneBar(topLeftCmbBx, Color.decode("#244E23"));
		JScrollPane topLeftScrollPane = coloredScrollPane.createScrollPane();
		
		topleftCmbBxPanel.add(topLeftScrollPane, BorderLayout.CENTER);
		
		
		//panel superior derecho 
		JPanel topRightPnl = new JPanel();
		topRightPnl.setLayout(new BorderLayout(0, 20));
		topRightPnl.setOpaque(false);
		newDishPnl.add(topRightPnl);
		
		JLabel topRightLbl = new JLabel(dishType.equals("platillo")?"Vegetales:" : "Jugo:");
		topRightLbl.setFont(new Font("Caladea Regular", Font.PLAIN, 30));
		topRightLbl.setForeground(Color.decode("#244E23")); 
		topRightLbl.setHorizontalAlignment(SwingConstants.LEFT);
		topRightLbl.setVerticalAlignment(SwingConstants.BOTTOM);
		topRightPnl.add(topRightLbl, BorderLayout.NORTH);
		
		RoundPanel topRightCmbBxPanel = new RoundPanel(30);  
		topRightCmbBxPanel.setBackground(Color.white);
		topRightCmbBxPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); 
		topRightCmbBxPanel.setForeground(Color.decode("#244E23")); 
		topRightCmbBxPanel.setLayout(new BorderLayout());
		topRightPnl.add(topRightCmbBxPanel, BorderLayout.CENTER);
		
		topRightCmbBx = new JList<>(ingredients);
		topRightCmbBx.setForeground(Color.decode("#244E23")); 
		topRightCmbBx.setFont(new Font("Caladea Regular", Font.PLAIN, 30)); 
		topRightCmbBx.setOpaque(false);
		topRightCmbBx.setVisibleRowCount(3);
		topRightCmbBx.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		
		coloredScrollPane = new ColoredScrollPaneBar(topRightCmbBx, Color.decode("#244E23"));
		JScrollPane topRightScrollPane = coloredScrollPane.createScrollPane();
		
		topRightCmbBxPanel.add(topRightScrollPane, BorderLayout.CENTER);
				
		
		//panel inferior izquierdo
		JPanel bottomLeftPnl = new JPanel();
		bottomLeftPnl.setLayout(new BorderLayout(0, 20));
		bottomLeftPnl.setOpaque(false);
		newDishPnl.add(bottomLeftPnl);
		
		JLabel bottomLeftLbl = new JLabel(dishType.equals("platillo")?"Carbohidratos:" : "Licor:");
		bottomLeftLbl.setFont(new Font("Caladea Regular", Font.PLAIN, 30));
		bottomLeftLbl.setForeground(Color.decode("#244E23")); 
		bottomLeftLbl.setHorizontalAlignment(SwingConstants.LEFT);
		bottomLeftLbl.setVerticalAlignment(SwingConstants.BOTTOM);
		bottomLeftPnl.add(bottomLeftLbl, BorderLayout.NORTH);
		
		RoundPanel bottomLeftCmbBxPnl = new RoundPanel(30);  
		bottomLeftCmbBxPnl.setBackground(Color.white);
		bottomLeftCmbBxPnl.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); 
		bottomLeftCmbBxPnl.setForeground(Color.decode("#244E23")); 
		bottomLeftCmbBxPnl.setLayout(new BorderLayout());
		bottomLeftPnl.add(bottomLeftCmbBxPnl, BorderLayout.CENTER);
		
		bottomLeftCmbBx = new JList<>(ingredients);
		bottomLeftCmbBx.setForeground(Color.decode("#244E23")); 
		bottomLeftCmbBx.setFont(new Font("Caladea Regular", Font.PLAIN, 30)); 
		bottomLeftCmbBx.setOpaque(false);
		bottomLeftCmbBx.setVisibleRowCount(3);
		bottomLeftCmbBx.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		
		coloredScrollPane = new ColoredScrollPaneBar(bottomLeftCmbBx, Color.decode("#244E23"));
		JScrollPane bottomLeftScrollPane = coloredScrollPane.createScrollPane();

		bottomLeftCmbBxPnl.add(bottomLeftScrollPane, BorderLayout.CENTER);
		
		
		//panel inferior derecho
		JPanel bottomRightPnl = new JPanel();
		bottomRightPnl.setLayout(new BorderLayout(0, 20));
		bottomRightPnl.setOpaque(false);
		newDishPnl.add(bottomRightPnl);
		
		JLabel bottomRightLbl = new JLabel(dishType.equals("platillo")?"Especias:" : "Extra:");
		bottomRightLbl.setFont(new Font("Caladea Regular", Font.PLAIN, 30));
		bottomRightLbl.setForeground(Color.decode("#244E23")); 
		bottomRightLbl.setHorizontalAlignment(SwingConstants.LEFT);
		bottomRightLbl.setVerticalAlignment(SwingConstants.BOTTOM);
		bottomRightPnl.add(bottomRightLbl, BorderLayout.NORTH);
		
		RoundPanel bottomRightCmbBxPnl = new RoundPanel(30);  
		bottomRightCmbBxPnl.setBackground(Color.white);
		bottomRightCmbBxPnl.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); 
		bottomRightCmbBxPnl.setForeground(Color.decode("#244E23")); 
		bottomRightCmbBxPnl.setLayout(new BorderLayout());
		bottomRightPnl.add(bottomRightCmbBxPnl, BorderLayout.CENTER);
		
		bottomRightCmbBx = new JList<>(ingredients);
		bottomRightCmbBx.setForeground(Color.decode("#244E23")); 
		bottomRightCmbBx.setFont(new Font("Caladea Regular", Font.PLAIN, 30)); 
		bottomRightCmbBx.setOpaque(false);
		bottomRightCmbBx.setVisibleRowCount(3);
		bottomRightCmbBx.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		
		coloredScrollPane = new ColoredScrollPaneBar(bottomRightCmbBx, Color.decode("#244E23"));
		JScrollPane bottomRightScrollPane = coloredScrollPane.createScrollPane();
		
		bottomRightCmbBxPnl.add(bottomRightScrollPane, BorderLayout.CENTER);
		
		
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
            	
            	relativeXSize = (int) (frame.getHeight()*0.01);
       			Border border = BorderFactory.createEmptyBorder(relativeXSize, relativeXSize, relativeXSize, relativeXSize);
       			topLeftPnl.setBorder(border);
       			topRightPnl.setBorder(border);
       			bottomLeftPnl.setBorder(border);
       			bottomRightPnl.setBorder(border);
       			
       			frame.repaint();
            }
        });
		
		return newDishPnl; 
	}
	
	
	public boolean dishFormEmptyFields() {
		String name = getNameTxtFld(); 
		String price = getPriceTxtFld();
		String description = getDishDescText(); 
		
		if(name.equals("") || price.equals("") || price.equals("0") || description.equals("")) {
			
			if(price.equals("0")) { 
				priceTxtFld.setForeground(Color.decode("#EF2D2D"));
			}
			
			OptionPaneButton option = new OptionPaneButton("Campos vacíos", "Complete los campos para guardar la información.");
			option.warningOptionPane();
			
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
			OptionPaneButton option = new OptionPaneButton("Campos vacíos", "Seleccione los ingredientes para guardar la información.");
			option.warningOptionPane();
			
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

	
	public void setNameTxtFld(String name) {
		this.nameTxtFld.setText(name);
	}

	public void setPriceTxtFld(String price) {
		this.priceTxtFld.setText(price);;
	}

	public void setDishDescText(String description) {
		this.dishDescText.setText(description);
	}
	
}
