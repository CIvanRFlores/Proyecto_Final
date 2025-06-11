package views;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.plaf.basic.BasicComboBoxUI;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import controllers.InventoryController;
import customClasses.*;
import models.Ingredient;

public class InventoryView {

	JFrame frame;
	JPanel mainPnl;
	Image image;
	ImageIcon imageIcon;
	String message;
    int relativeXSize;
	int relativeYSize;
	
	int selectedRow = -1;
	
	InventoryController ic;
	OptionPaneButton optionPane;
	OptionPaneButton loadingOptPn;
	int opt;
	
	public InventoryView(String title, int frameWidth, int frameHeight) {
		frame = new JFrame(); //crear JFrame	
		imageIcon = new ImageIcon(InventoryView.class.getResource("/images/elManglarLogo.png")); //icono de la ventana
		frame.setIconImage(imageIcon.getImage());
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
		frame.setSize(frameWidth, frameHeight); 
		frame.setLocationRelativeTo(null); //colocar la ventana en el centro de la pantalla
		frame.setResizable(true);
		frame.setMinimumSize(new Dimension(350, 350));
		frame.setTitle(title); 
		
		SideBarPanel sideBar = new SideBarPanel(frame);
		JPanel buttonPnl = sideBar.createSidePanel();
		frame.add(buttonPnl, BorderLayout.WEST);
		
		buttonPnl.getComponent(4).setBackground(Color.decode("#3C7E3A"));
		sideBar.removeInventoryListener();
		
		frame.addComponentListener(new ComponentAdapter() {
            public void componentResized(ComponentEvent e) {
            	double aspectRatio = 1.25/1;
            	
            	Dimension newSize = frame.getSize();
                int newWidth = newSize.width;
                int newHeight = (int) (newWidth/aspectRatio);

                frame.setSize(newWidth, newHeight);
       			frame.repaint();
            }
        });
		
	}
	
	public void inventory() {
		ic = new InventoryController(frame.getTitle(), frame.getWidth(), frame.getHeight());
		
		mainPnl = new JPanel();
		mainPnl.setBackground(Color.white);
		mainPnl.setBorder(BorderFactory.createEmptyBorder(30, 45, 30, 45)); 
		mainPnl.setLayout(new BorderLayout());
		frame.add(mainPnl, BorderLayout.CENTER);
		
		JPanel headerPnl = new JPanel();
		headerPnl.setLayout(new BorderLayout(20, 15));
		headerPnl.setOpaque(false); 
		mainPnl.add(headerPnl, BorderLayout.NORTH);
		
		JLabel inventoryLbl = new JLabel("Inventario");
		inventoryLbl.setFont(new Font("Caladea Bold", Font.BOLD, 36));
		inventoryLbl.setForeground(Color.decode("#244E23")); 
		inventoryLbl.setHorizontalAlignment(JLabel.LEFT); 
		inventoryLbl.setHorizontalAlignment(SwingConstants.LEFT); 
		headerPnl.add(inventoryLbl, BorderLayout.NORTH);
		
		
		JPanel searchPnl = new JPanel();
		searchPnl.setLayout(new GridLayout(1, 2, 20, 0));
		searchPnl.setOpaque(false); 
		headerPnl.add(searchPnl, BorderLayout.CENTER);
		
		RoundPanel searchBarPnl = new RoundPanel(30);  
		searchBarPnl.setBackground(Color.white);
		searchBarPnl.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5)); 
		searchBarPnl.setForeground(Color.decode("#244E23")); 
		searchBarPnl.setLayout(new BorderLayout(15, 0));
		searchPnl.add(searchBarPnl);
		
		image = new ImageIcon(InventoryView.class.getResource("/images/magnifyingGlass.png")).getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH);
		imageIcon = new ImageIcon(image);
		JLabel logoTextLbl = new JLabel(imageIcon);
		searchBarPnl.add(logoTextLbl, BorderLayout.WEST);
		
		JTextField searchTxtFld = new JTextField();
		searchTxtFld.setBorder(null);
		searchTxtFld.setFont(new Font("Caladea Bold", Font.BOLD, 14)); 
		searchTxtFld.setForeground(Color.decode("#244E23"));
		searchTxtFld.setOpaque(false); 
		searchBarPnl.add(searchTxtFld, BorderLayout.CENTER);
		

		JPanel searchBarBttnPnl = new JPanel();
		searchBarBttnPnl.setLayout(new GridLayout(1, 3, 20, 0));
		searchBarBttnPnl.setOpaque(false); 
		searchPnl.add(searchBarBttnPnl, BorderLayout.EAST);
		
		RoundButton searchBttn = new RoundButton(30);
		searchBttn.setBackground(Color.decode("#244E23"));
		searchBttn.setFont(new Font("Caladea Bold", Font.BOLD, 20));
		searchBttn.setForeground(Color.white);
		searchBttn.setText("Buscar");
		searchBarBttnPnl.add(searchBttn);
		
		searchBttn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(searchTxtFld.getText().equals("")) {
					optionPane = new OptionPaneButton("Inventario", "Campo sin completar.");
					optionPane.errorOptionPane();
				}
				else{
					Object[][] ingredients = ic.searchIngredientTable(searchTxtFld.getText());	//Realiza busqueda de ingredientes
					if(ingredients.length == 0)	{ //Condicional que verifica si se encontraron ingredientes o no
						optionPane = new OptionPaneButton("Ingrediente no encontrado", "No se ha encontrado ningún ingrediente.");
						optionPane.warningOptionPane();
					}
					else	//else en caso de que si encuentre
					{
						loadingOptPn = new OptionPaneButton("Cargando información...", "Por favor espere.");
						loadingOptPn.loadingOptionPane(frame, 3000);
						frame.dispose();
						ic.searchIngredient(ingredients);
					}
					
				}
			}
		});
		
		searchBttn.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent evt) {
				searchBttn.setBackground(Color.decode("#3C7E3A"));
		    }

		    public void mouseExited(MouseEvent evt) {
		    	searchBttn.setBackground(Color.decode("#244E23"));
		    }
		});
		
		RoundPanel searchByBttnPnl = new RoundPanel(30);  
		searchByBttnPnl.setBackground(Color.white);
		searchByBttnPnl.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10)); 
		searchByBttnPnl.setForeground(Color.decode("#244E23")); 
		searchByBttnPnl.setLayout(new BorderLayout());
		searchBarBttnPnl.add(searchByBttnPnl);
		
		String type[] = {"Código", "Nombre", "Cantidad"};
		
		JComboBox<String> searchByCmbBx = new JComboBox<>(type);
		searchByCmbBx.setBorder(null); 
		searchByCmbBx.setBackground(Color.white);
		searchByCmbBx.setForeground(Color.decode("#244E23")); 
		searchByCmbBx.setFont(new Font("Caladea Bold", Font.BOLD, 14)); 
		searchByCmbBx.setOpaque(false);
		searchByCmbBx.setUI(new BasicComboBoxUI());
		searchByBttnPnl.add(searchByCmbBx, BorderLayout.CENTER);
		
		searchBarBttnPnl.add(Box.createHorizontalStrut(0));
		
		
		JPanel actionPnl = new JPanel();
		actionPnl.setLayout(new GridLayout(1, 2, 20, 0));
		actionPnl.setOpaque(false); 
		headerPnl.add(actionPnl, BorderLayout.SOUTH);
		
		actionPnl.add(Box.createHorizontalStrut(0), BorderLayout.CENTER);
		
		JPanel buttonPnl = new JPanel();
		buttonPnl.setLayout(new GridLayout(1, 3, 20, 0));
		buttonPnl.setOpaque(false); 
		actionPnl.add(buttonPnl, BorderLayout.EAST);
		
		RoundButton deleteBttn = new RoundButton(30);
		deleteBttn.setBackground(Color.decode("#EF2D2D"));
		deleteBttn.setFont(new Font("Caladea Bold", Font.BOLD, 20));
		deleteBttn.setForeground(Color.white);
		deleteBttn.setText("Eliminar");
		buttonPnl.add(deleteBttn);
		
		deleteBttn.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent evt) {
				deleteBttn.setBackground(Color.decode("#ED5C5C"));
		    }

		    public void mouseExited(MouseEvent evt) {
		    	deleteBttn.setBackground(Color.decode("#EF2D2D"));
		    }
		});
		
		RoundButton editBttn = new RoundButton(30);
		editBttn.setBackground(Color.decode("#367181"));
		editBttn.setFont(new Font("Caladea Bold", Font.BOLD, 20));
		editBttn.setForeground(Color.white);
		editBttn.setText("Editar");
		buttonPnl.add(editBttn); 
		
		editBttn.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent evt) {
				editBttn.setBackground(Color.decode("#264F59"));
		    }

		    public void mouseExited(MouseEvent evt) {
		    	editBttn.setBackground(Color.decode("#367181"));
		    }
		});
		
		RoundButton newInv = new RoundButton(30);
		newInv.setBackground(Color.decode("#2EA623"));
		newInv.setFont(new Font("Caladea Bold", Font.BOLD, 20));
		newInv.setForeground(Color.white);
		newInv.setText("Nuevo");
		buttonPnl.add(newInv); 

		newInv.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				loadingOptPn = new OptionPaneButton("Cargando ventana...", "Por favor espere.");
				loadingOptPn.loadingOptionPane(frame, 1000);
				frame.dispose();
				ic.newInventory();
			}
		});
		
		newInv.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent evt) {
				newInv.setBackground(Color.decode("#39C82C"));
		    }

		    public void mouseExited(MouseEvent evt) {
		    	newInv.setBackground(Color.decode("#2EA623"));
		    }
		});
		
		
		JPanel inventoryPnl = new JPanel();
		inventoryPnl.setBorder(BorderFactory.createEmptyBorder(40, 0, 40, 0)); 
		inventoryPnl.setLayout(new BorderLayout(30, 30));	
		inventoryPnl.setOpaque(false); 
		mainPnl.add(inventoryPnl, BorderLayout.CENTER);
		
		JLabel inventoryTableLbl = new JLabel("Inventario");
		inventoryTableLbl.setFont(new Font("Caladea Regular", Font.PLAIN, 24));
		inventoryTableLbl.setForeground(Color.decode("#244E23"));
		inventoryTableLbl.setHorizontalAlignment(JLabel.LEFT); 
		inventoryTableLbl.setVerticalAlignment(JLabel.BOTTOM); 
		inventoryPnl.add(inventoryTableLbl, BorderLayout.NORTH);
		
		String[] tableColumns = {"Nombre", "Cantidad", "Código"};
		
		DefaultTableModel invTableModel = new DefaultTableModel(tableColumns, 0);

		Object[][] data= ic.ingredientsTable();
		for(Object[] row : data) {	//Inserta ingredientes de la base de datos a la tabla
			invTableModel.addRow(row);
		}
		
		InformationTable invTemplate = new InformationTable(frame, invTableModel, Color.decode("#555BF6"));
		JScrollPane invScrollPane = invTemplate.createTable();
		JTable inventoryTable = invTemplate.getTable();
		
		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
				
		inventoryTable.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
		inventoryTable.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
		inventoryTable.getColumnModel().getColumn(2).setCellRenderer(centerRenderer);
		
		inventoryTable.getSelectionModel().addListSelectionListener(e -> {
			if(!e.getValueIsAdjusting())
			{
				selectedRow = inventoryTable.getSelectedRow();
				
				System.out.println(selectedRow);
			}
		});
		
		inventoryPnl.add(invScrollPane, BorderLayout.CENTER);
	
		
		deleteBttn.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent evt) {
				if(selectedRow!=-1) {
					optionPane = new OptionPaneButton("Borrar inventario", "Esta acción no se puede deshacer.", "Eliminar");
					opt = optionPane.destructiveOptionPane();
					
					if(opt==1) {
						loadingOptPn = new OptionPaneButton("Cargando información...", "Por favor espere.");
						loadingOptPn.loadingOptionPane(frame, 3000);
						
						ic.ingredientDelete(selectedRow);
						System.out.println(selectedRow);
						invTableModel.removeRow(selectedRow);
						System.out.println("Registro eliminado");
					}
				}else {
					optionPane = new OptionPaneButton("Fila sin seleccionar", "Seleccione la fila de una tabla.");
					optionPane.warningOptionPane();
				}
			}
		});
		
		editBttn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
				if(selectedRow != -1) {
					loadingOptPn = new OptionPaneButton("Cargando ventana...", "Por favor espere.");
					loadingOptPn.loadingOptionPane(frame, 3000);
					frame.dispose();
					ic.editInventory(selectedRow);
				}else {
					optionPane = new OptionPaneButton("Fila sin seleccionar", "Seleccione la fila de una tabla.");
					optionPane.warningOptionPane();
				}
			}
		});
		
		
		frame.setVisible(true);
	}
	
	public void searchIngredient(Object[][] ingredients) {
		ic = new InventoryController(frame.getTitle(), frame.getWidth(), frame.getHeight());
		
		mainPnl = new JPanel();
		mainPnl.setBackground(Color.white);
		mainPnl.setBorder(BorderFactory.createEmptyBorder(30, 45, 30, 45)); 
		mainPnl.setLayout(new BorderLayout());
		frame.add(mainPnl, BorderLayout.CENTER);
		
		JPanel headerPnl = new JPanel();
		headerPnl.setLayout(new BorderLayout(20, 15));
		headerPnl.setOpaque(false); 
		mainPnl.add(headerPnl, BorderLayout.NORTH);
		
		JLabel inventoryLbl = new JLabel("Inventario");
		inventoryLbl.setFont(new Font("Caladea Bold", Font.BOLD, 36));
		inventoryLbl.setForeground(Color.decode("#244E23")); 
		inventoryLbl.setHorizontalAlignment(JLabel.LEFT); 
		inventoryLbl.setHorizontalAlignment(SwingConstants.LEFT); 
		headerPnl.add(inventoryLbl, BorderLayout.NORTH);
		
		
		JPanel searchPnl = new JPanel();
		searchPnl.setLayout(new GridLayout(1, 2, 20, 0));
		searchPnl.setOpaque(false); 
		headerPnl.add(searchPnl, BorderLayout.CENTER);
		
		RoundPanel searchBarPnl = new RoundPanel(30);  
		searchBarPnl.setBackground(Color.white);
		searchBarPnl.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5)); 
		searchBarPnl.setForeground(Color.decode("#244E23")); 
		searchBarPnl.setLayout(new BorderLayout(15, 0));
		searchPnl.add(searchBarPnl);
		
		image = new ImageIcon(InventoryView.class.getResource("/images/magnifyingGlass.png")).getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH);
		imageIcon = new ImageIcon(image);
		JLabel logoTextLbl = new JLabel(imageIcon);
		searchBarPnl.add(logoTextLbl, BorderLayout.WEST);
		
		JTextField searchTxtFld = new JTextField();
		searchTxtFld.setBorder(null);
		searchTxtFld.setFont(new Font("Caladea Bold", Font.BOLD, 14)); 
		searchTxtFld.setForeground(Color.decode("#244E23"));
		searchTxtFld.setOpaque(false); 
		searchBarPnl.add(searchTxtFld, BorderLayout.CENTER);
		

		JPanel searchBarBttnPnl = new JPanel();
		searchBarBttnPnl.setLayout(new GridLayout(1, 3, 20, 0));
		searchBarBttnPnl.setOpaque(false); 
		searchPnl.add(searchBarBttnPnl, BorderLayout.EAST);
		
		RoundButton searchBttn = new RoundButton(30);
		searchBttn.setBackground(Color.decode("#244E23"));
		searchBttn.setFont(new Font("Caladea Bold", Font.BOLD, 20));
		searchBttn.setForeground(Color.white);
		searchBttn.setText("Buscar");
		searchBarBttnPnl.add(searchBttn);
		
		searchBttn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(searchTxtFld.getText().equals("")) {
					optionPane = new OptionPaneButton("Inventario", "Campo sin completar.");
					optionPane.errorOptionPane();
				}
				else{
					Object[][] ingredients = ic.searchIngredientTable(searchTxtFld.getText());	//Realiza busqueda de ingredientes
					if(ingredients.length == 0)	{ //Condicional que verifica si se encontraron ingredientes o no
						optionPane = new OptionPaneButton("Ingrediente no encontrado", "No se ha encontrado ningún ingrediente.");
						optionPane.warningOptionPane();
					}
					else	//else en caso de que si encuentre
					{
						loadingOptPn = new OptionPaneButton("Cargando información...", "Por favor espere.");
						loadingOptPn.loadingOptionPane(frame, 3000);
						frame.dispose();
						searchIngredient(ingredients);
					}
					
				}
			}
		});
		
		searchBttn.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent evt) {
				searchBttn.setBackground(Color.decode("#3C7E3A"));
		    }

		    public void mouseExited(MouseEvent evt) {
		    	searchBttn.setBackground(Color.decode("#244E23"));
		    }
		});
		
		RoundPanel searchByBttnPnl = new RoundPanel(30);  
		searchByBttnPnl.setBackground(Color.white);
		searchByBttnPnl.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10)); 
		searchByBttnPnl.setForeground(Color.decode("#244E23")); 
		searchByBttnPnl.setLayout(new BorderLayout());
		searchBarBttnPnl.add(searchByBttnPnl);
		
		String type[] = {"Código", "Nombre", "Cantidad"};
		
		JComboBox<String> searchByCmbBx = new JComboBox<>(type);
		searchByCmbBx.setBorder(null); 
		searchByCmbBx.setBackground(Color.white);
		searchByCmbBx.setForeground(Color.decode("#244E23")); 
		searchByCmbBx.setFont(new Font("Caladea Bold", Font.BOLD, 14)); 
		searchByCmbBx.setOpaque(false);
		searchByCmbBx.setUI(new BasicComboBoxUI());
		searchByBttnPnl.add(searchByCmbBx, BorderLayout.CENTER);
		
		searchBarBttnPnl.add(Box.createHorizontalStrut(0));
		
		
		JPanel actionPnl = new JPanel();
		actionPnl.setLayout(new GridLayout(1, 2, 20, 0));
		actionPnl.setOpaque(false); 
		headerPnl.add(actionPnl, BorderLayout.SOUTH);
		
		actionPnl.add(Box.createHorizontalStrut(0), BorderLayout.CENTER);
		
		JPanel buttonPnl = new JPanel();
		buttonPnl.setLayout(new GridLayout(1, 3, 20, 0));
		buttonPnl.setOpaque(false); 
		actionPnl.add(buttonPnl, BorderLayout.EAST);
		
		RoundButton deleteBttn = new RoundButton(30);
		deleteBttn.setBackground(Color.decode("#EF2D2D"));
		deleteBttn.setFont(new Font("Caladea Bold", Font.BOLD, 20));
		deleteBttn.setForeground(Color.white);
		deleteBttn.setText("Eliminar");
		buttonPnl.add(deleteBttn);
		
		deleteBttn.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent evt) {
				deleteBttn.setBackground(Color.decode("#ED5C5C"));
		    }

		    public void mouseExited(MouseEvent evt) {
		    	deleteBttn.setBackground(Color.decode("#EF2D2D"));
		    }
		});
		
		RoundButton editBttn = new RoundButton(30);
		editBttn.setBackground(Color.decode("#367181"));
		editBttn.setFont(new Font("Caladea Bold", Font.BOLD, 20));
		editBttn.setForeground(Color.white);
		editBttn.setText("Editar");
		buttonPnl.add(editBttn); 
		
		editBttn.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent evt) {
				editBttn.setBackground(Color.decode("#264F59"));
		    }

		    public void mouseExited(MouseEvent evt) {
		    	editBttn.setBackground(Color.decode("#367181"));
		    }
		});
		
		RoundButton newInv = new RoundButton(30);
		newInv.setBackground(Color.decode("#2EA623"));
		newInv.setFont(new Font("Caladea Bold", Font.BOLD, 20));
		newInv.setForeground(Color.white);
		newInv.setText("Nuevo");
		buttonPnl.add(newInv); 

		newInv.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				loadingOptPn = new OptionPaneButton("Cargando ventana...", "Por favor espere.");
				loadingOptPn.loadingOptionPane(frame, 1000);
				frame.dispose();
				ic.newInventory();
			}
		});
		
		newInv.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent evt) {
				newInv.setBackground(Color.decode("#39C82C"));
		    }

		    public void mouseExited(MouseEvent evt) {
		    	newInv.setBackground(Color.decode("#2EA623"));
		    }
		});
		
		
		JPanel inventoryPnl = new JPanel();
		inventoryPnl.setBorder(BorderFactory.createEmptyBorder(40, 0, 40, 0)); 
		inventoryPnl.setLayout(new BorderLayout(30, 30));	
		inventoryPnl.setOpaque(false); 
		mainPnl.add(inventoryPnl, BorderLayout.CENTER);
		
		JLabel inventoryTableLbl = new JLabel("Inventario");
		inventoryTableLbl.setFont(new Font("Caladea Regular", Font.PLAIN, 24));
		inventoryTableLbl.setForeground(Color.decode("#244E23"));
		inventoryTableLbl.setHorizontalAlignment(JLabel.LEFT); 
		inventoryTableLbl.setVerticalAlignment(JLabel.BOTTOM); 
		inventoryPnl.add(inventoryTableLbl, BorderLayout.NORTH);
		
		String[] tableColumns = {"Nombre", "Cantidad", "Código"};
		
		DefaultTableModel invTableModel = new DefaultTableModel(tableColumns, 0);

		for(Object[] row : ingredients) {	//Inserta ingredientes de la base de datos a la tabla
			invTableModel.addRow(row);
		}
		
		InformationTable invTemplate = new InformationTable(frame, invTableModel, Color.decode("#555BF6"));
		JScrollPane invScrollPane = invTemplate.createTable();
		JTable inventoryTable = invTemplate.getTable();
		
		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
				
		inventoryTable.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
		inventoryTable.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
		inventoryTable.getColumnModel().getColumn(2).setCellRenderer(centerRenderer);
		
		inventoryTable.getSelectionModel().addListSelectionListener(e -> {
			if(!e.getValueIsAdjusting())
			{
				selectedRow = inventoryTable.getSelectedRow();
				
				System.out.println(selectedRow);
			}
		});
		
		inventoryPnl.add(invScrollPane, BorderLayout.CENTER);
	
		
		deleteBttn.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent evt) {
				if(selectedRow!=-1) {
					optionPane = new OptionPaneButton("Borrar inventario", "Esta acción no se puede deshacer.", "Eliminar");
					opt = optionPane.destructiveOptionPane();
					
					if(opt==1) {
						loadingOptPn = new OptionPaneButton("Cargando información...", "Por favor espere.");
						loadingOptPn.loadingOptionPane(frame, 3000);
						
						ic.ingredientDelete(selectedRow);
						System.out.println(selectedRow);
						invTableModel.removeRow(selectedRow);
						System.out.println("Registro eliminado");
					}
				}else {
					optionPane = new OptionPaneButton("Fila sin seleccionar", "Seleccione la fila de una tabla.");
					optionPane.warningOptionPane();
				}
			}
		});
		
		editBttn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
				if(selectedRow != -1) {
					loadingOptPn = new OptionPaneButton("Cargando ventana...", "Por favor espere.");
					loadingOptPn.loadingOptionPane(frame, 3000);
					frame.dispose();
					ic.editInventory(selectedRow);
				}else {
					optionPane = new OptionPaneButton("Fila sin seleccionar", "Seleccione la fila de una tabla.");
					optionPane.warningOptionPane();
				}
			}
		});
		
		
		frame.setVisible(true);
//		optionPane = new OptionPaneButton("Inventario no encontrado", "No se ha encontrado ningún Inventario.");
//		optionPane.warningOptionPane();
	}
	
	public void newInventory() {
		ic = new InventoryController(frame.getTitle(), frame.getWidth(), frame.getHeight());
		
		mainPnl = new JPanel();
		mainPnl.setBackground(Color.white);
		mainPnl.setBorder(BorderFactory.createEmptyBorder(30, 45, 30, 45));   
		mainPnl.setLayout(new BorderLayout());
		frame.add(mainPnl, BorderLayout.CENTER);
		
		JPanel headerPnl = new JPanel();
		headerPnl.setLayout(new BorderLayout(20, 15));
		headerPnl.setOpaque(false); 
		mainPnl.add(headerPnl, BorderLayout.NORTH);
		
		JLabel inventoryLbl = new JLabel("Añadir inventario");
		inventoryLbl.setFont(new Font("Caladea Bold", Font.BOLD, 36));
		inventoryLbl.setForeground(Color.decode("#244E23")); 
		inventoryLbl.setHorizontalAlignment(JLabel.LEFT); 
		inventoryLbl.setHorizontalAlignment(SwingConstants.LEFT); 
		headerPnl.add(inventoryLbl, BorderLayout.NORTH);
				
		JPanel actionPnl = new JPanel();
		actionPnl.setLayout(new GridLayout(1, 4, 20, 0));
		actionPnl.setOpaque(false); 
		headerPnl.add(actionPnl, BorderLayout.EAST);
		
		actionPnl.add(Box.createHorizontalStrut(0));
		actionPnl.add(Box.createHorizontalStrut(0));
		
		RoundButton cancelBttn = new RoundButton(30);
		cancelBttn.setBackground(Color.decode("#EF2D2D"));
		cancelBttn.setFont(new Font("Caladea Bold", Font.BOLD, 20));
		cancelBttn.setForeground(Color.white);
		cancelBttn.setText("Cancelar");
		actionPnl.add(cancelBttn);
		
		cancelBttn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				optionPane = new OptionPaneButton("Volver a menú", "Todos los cambios se perderán.", "  Salir  ");
				opt = optionPane.destructiveOptionPane();
				
				if(opt==1) {
					loadingOptPn = new OptionPaneButton("Cargando ventana...", "Por favor espere.");
					loadingOptPn.loadingOptionPane(frame, 3000);
					frame.dispose();
					ic.inventory();
				}
			}
		});
		
		cancelBttn.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent evt) {
				cancelBttn.setBackground(Color.decode("#ED5C5C"));
		    }

		    public void mouseExited(MouseEvent evt) {
		    	cancelBttn.setBackground(Color.decode("#EF2D2D"));
		    }
		});
		
		
		InventoryFormPanel form = new InventoryFormPanel(frame);
		JPanel formPanel = form.createInventoryForm();
		mainPnl.add(formPanel, BorderLayout.CENTER); 
		
		
		RoundButton newInv = new RoundButton(30);
		newInv.setBackground(Color.decode("#2EA623"));
		newInv.setFont(new Font("Caladea Bold", Font.BOLD, 20));
		newInv.setForeground(Color.white);
		newInv.setText("Añadir");
		actionPnl.add(newInv); 

		newInv.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(!form.invFormEmptyFields()) {
					loadingOptPn = new OptionPaneButton("Cargando información...", "Por favor espere.");
					loadingOptPn.loadingOptionPane(frame, 3000);
					
					//Sentencia para crear nuevo cliente cuando se realize registro
					ic.ingredientCreate(form.getCodeTxtFld(), form.getNameTxtFld(), Integer.parseInt(form.getQuantityTxtFld()));	//parseInt convierte un String en Int
					
					optionPane = new OptionPaneButton("Acción exitosa", "Inventario creado correctamente.");
					optionPane.checkOptionPane();
					
					loadingOptPn = new OptionPaneButton("Cargando ventana...", "Por favor espere.");
					loadingOptPn.loadingOptionPane(frame, 3000);
					frame.dispose();
					ic.inventory();
				}
			}
		});
		
		newInv.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent evt) {
				newInv.setBackground(Color.decode("#39C82C"));
		    }

		    public void mouseExited(MouseEvent evt) {
		    	newInv.setBackground(Color.decode("#2EA623"));
		    }
		});
	
		frame.setVisible(true);
	}
	
	
	public void editInventory(int selectedRow) {
		ic = new InventoryController(frame.getTitle(), frame.getWidth(), frame.getHeight());
		
		mainPnl = new JPanel();
		mainPnl.setBackground(Color.white);
		mainPnl.setBorder(BorderFactory.createEmptyBorder(30, 45, 30, 45)); 
		mainPnl.setLayout(new BorderLayout());
		frame.add(mainPnl, BorderLayout.CENTER);
		
		JPanel headerPnl = new JPanel();
		headerPnl.setLayout(new BorderLayout(20, 15));
		headerPnl.setOpaque(false); 
		mainPnl.add(headerPnl, BorderLayout.NORTH);
		
		JLabel inventoryLbl = new JLabel("Editar inventario");
		inventoryLbl.setFont(new Font("Caladea Bold", Font.BOLD, 36));
		inventoryLbl.setForeground(Color.decode("#244E23")); 
		inventoryLbl.setHorizontalAlignment(JLabel.LEFT); 
		inventoryLbl.setHorizontalAlignment(SwingConstants.LEFT); 
		headerPnl.add(inventoryLbl, BorderLayout.NORTH);
				
		JPanel actionPnl = new JPanel();
		actionPnl.setLayout(new GridLayout(1, 4, 20, 0));
		actionPnl.setOpaque(false); 
		headerPnl.add(actionPnl, BorderLayout.EAST);
		
		actionPnl.add(Box.createHorizontalStrut(0));
		actionPnl.add(Box.createHorizontalStrut(0));
		
		RoundButton cancelBttn = new RoundButton(30);
		cancelBttn.setBackground(Color.decode("#EF2D2D"));
		cancelBttn.setFont(new Font("Caladea Bold", Font.BOLD, 20));
		cancelBttn.setForeground(Color.white);
		cancelBttn.setText("Cancelar");
		actionPnl.add(cancelBttn);
		
		cancelBttn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				optionPane = new OptionPaneButton("Volver a menú", "Todos los cambios se perderán.", "  Salir  ");
				opt = optionPane.destructiveOptionPane();
				
				if(opt==1) {
					loadingOptPn = new OptionPaneButton("Cargando ventana...", "Por favor espere.");
					loadingOptPn.loadingOptionPane(frame, 3000);
					frame.dispose();
					ic.inventory();
				}
			}
		});
		
		cancelBttn.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent evt) {
				cancelBttn.setBackground(Color.decode("#ED5C5C"));
		    }

		    public void mouseExited(MouseEvent evt) {
		    	cancelBttn.setBackground(Color.decode("#EF2D2D"));
		    }
		});
		
		
		InventoryFormPanel form = new InventoryFormPanel(frame);
		JPanel formPanel = form.createInventoryForm();
		mainPnl.add(formPanel, BorderLayout.CENTER); 
		System.out.println(selectedRow);
		Ingredient i = ic.ingredientRead(selectedRow);
		//Formulario lleno
		form.setCodeTxtFld(i.code_Ingredient);
		form.setNameTxtFld(i.name);
		form.setQuantityTxtFld(String.valueOf(i.ammount));
		
		RoundButton saveBttn = new RoundButton(30);
		saveBttn.setBackground(Color.decode("#555BF6"));
		saveBttn.setFont(new Font("Caladea Bold", Font.BOLD, 20));
		saveBttn.setForeground(Color.white);
		saveBttn.setText("Guardar");
		actionPnl.add(saveBttn); 

		saveBttn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(!form.invFormEmptyFields()) {
					loadingOptPn = new OptionPaneButton("Cargando información...", "Por favor espere.");
					loadingOptPn.loadingOptionPane(frame, 3000);
					
					System.out.println(selectedRow);
					ic.ingredientUpdate(selectedRow, form.getCodeTxtFld(), form.getNameTxtFld(), Integer.parseInt(form.getQuantityTxtFld()));
					
					optionPane = new OptionPaneButton("Acción exitosa", "Inventario actualizado correctamente.");
	   				optionPane.checkOptionPane();
					
	   				loadingOptPn = new OptionPaneButton("Cargando ventana...", "Por favor espere.");
					loadingOptPn.loadingOptionPane(frame, 3000);
					frame.dispose();
					ic.inventory();
				}
			}
		});
		
		saveBttn.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent evt) {
				saveBttn.setBackground(Color.decode("#767AEC"));
		    }

		    public void mouseExited(MouseEvent evt) {
		    	saveBttn.setBackground(Color.decode("#555BF6"));
		    }
		});
		
		frame.setVisible(true);
	}
	
}
