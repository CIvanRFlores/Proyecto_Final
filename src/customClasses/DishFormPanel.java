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
	JList<String> ingredientsCmbBx;

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
		            
		            if(getPriceTxtFld().substring(0).equals("0")) 
		            	setPriceTxtFld("");
		           
		        }else 
		        	priceTxtFld.setForeground(Color.decode("#244E23"));
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
		
		String ingredients[] = {"Ingredientes" ,"Maíz", "Camarón", "Papa", "Mantequilla", "Aceite", "Camote", "Tomate", "Agua"};
	
		RoundPanel cmbBxPanel = new RoundPanel(30);  
		cmbBxPanel.setBackground(Color.white);
		cmbBxPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); 
		cmbBxPanel.setForeground(Color.decode("#244E23")); 
		cmbBxPanel.setLayout(new BorderLayout());
		newDishPnl.add(cmbBxPanel, BorderLayout.CENTER);
		
		DefaultListSelectionModel model = new DefaultListSelectionModel() {
			private static final long serialVersionUID = 5772470969986545094L;
			
			private boolean gestureStarted = false;

		    @Override
		    public void setSelectionInterval(int index0, int index1) {
		        if(!gestureStarted) {
		            if(isSelectedIndex(index0)) {
		                super.removeSelectionInterval(index0, index1);
		            }else {
		                super.addSelectionInterval(index0, index1);
		            }
		        }
		        gestureStarted = true;
		    }

		    @Override
		    public void setValueIsAdjusting(boolean isAdjusting) {
		        if(!isAdjusting) {
		        	gestureStarted = false;
		        }
		        super.setValueIsAdjusting(isAdjusting);
		    }
		};
		
		ingredientsCmbBx = new JList<>(ingredients);
		ingredientsCmbBx.setForeground(Color.decode("#244E23")); 
		ingredientsCmbBx.setFont(new Font("Caladea Regular", Font.PLAIN, 30)); 
		ingredientsCmbBx.setOpaque(false);
		ingredientsCmbBx.setVisibleRowCount(ingredients.length);
		ingredientsCmbBx.setSelectionModel(model);
		
		
		coloredScrollPane = new ColoredScrollPaneBar(ingredientsCmbBx, Color.decode("#244E23"));
		JScrollPane topLeftScrollPane = coloredScrollPane.createScrollPane();
		
		cmbBxPanel.add(topLeftScrollPane, BorderLayout.CENTER);
		
		
		/**cuando la ventana es redimensionada, los elementos dentro de ella cambian de tamaño**/
		frame.addComponentListener(new ComponentAdapter() {
            public void componentResized(ComponentEvent e) {
            	relativeXSize = (int) (frame.getHeight()*0.03);
            	font = new Font("Caladea Regular", Font.PLAIN, relativeXSize);
            	ingredientsCmbBx.setFont(font);
            	
            	relativeXSize = (int) (frame.getHeight()*0.01);
       			Border border = BorderFactory.createEmptyBorder(relativeXSize, relativeXSize, relativeXSize, relativeXSize);
       			cmbBxPanel.setBorder(border);
       			
       			frame.repaint();
            }
        });
		
		return newDishPnl; 
	}
	
	
	public boolean dishFormEmptyFields() {
		String name = getNameTxtFld(); 
		String price = getPriceTxtFld();
		String description = getDishDescText(); 
		
		if(price.equals("0")) 
			priceTxtFld.setForeground(Color.decode("#EF2D2D"));
		
		if(name.equals("") || price.equals("") || price.equals("0") || description.equals("")) {
			
			OptionPaneButton option = new OptionPaneButton("Campos vacíos", "Complete los campos para guardar la información.");
			option.warningOptionPane();
			
			return true;
		}else {
			return false;
		}
	}
	
	public boolean ingredientFormEmptyFields() {
		String ingredients = getIngredientsCmbBx(); 
		
		if(ingredients.equals("[]")) {
			OptionPaneButton option = new OptionPaneButton("Campos vacíos", "Seleccione los ingredientes para guardar la información.");
			option.warningOptionPane();
			
			return true;
		}else { 
			System.out.println(ingredients);
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
	
	public String getIngredientsCmbBx() {
		List<String> selectedValues = ingredientsCmbBx.getSelectedValuesList();
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
