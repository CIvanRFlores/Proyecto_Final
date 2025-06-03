package views;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import controllers.ClientController;
import customClasses.*;

public class ClientView {

	public JFrame frame; 
	public JPanel mainPnl;
	public Image image;
	public ImageIcon imageIcon;
	public String message;
	public String currentWindow;
	public int relativeXSize;
	public int relativeYSize;
	public ClientController cc;
	
	public ClientView(String title, int frameWidth, int frameHeight) {
		frame = new JFrame(); 
		imageIcon = new ImageIcon(ClientView.class.getResource("/images/elManglarLogo.png")); //icono de la ventana
		frame.setIconImage(imageIcon.getImage());
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
		frame.setSize(frameWidth, frameHeight); 
		frame.setLocationRelativeTo(null); //colocar la ventana en el centro de la pantalla
		frame.setResizable(true); 
		frame.setMinimumSize(new Dimension(350, 350));
		frame.setTitle(title); 
		
		SideBarPanel sideBar = new SideBarPanel(frame);
		JPanel buttonPanel = sideBar.createSidePanel();
		frame.add(buttonPanel, BorderLayout.WEST);
		
		buttonPanel.getComponent(3).setBackground(Color.decode("#3C7E3A"));
	}
	
	public void clients() {
		cc = new ClientController(frame.getTitle(), frame.getWidth(), frame.getHeight());
		
		mainPnl = new JPanel();
		mainPnl.setBackground(Color.white);
		mainPnl.setBorder(BorderFactory.createEmptyBorder(30, 45, 30, 45)); 
		mainPnl.setLayout(new BorderLayout());
		frame.add(mainPnl, BorderLayout.CENTER);
		
		JPanel headerPnl = new JPanel();
		headerPnl.setLayout(new BorderLayout(20, 15));
		headerPnl.setOpaque(false); 
		mainPnl.add(headerPnl, BorderLayout.NORTH);
		
		JLabel clientsLbl = new JLabel("Clientes");
		clientsLbl.setFont(new Font("Caladea Bold", Font.BOLD, 36));
		clientsLbl.setForeground(Color.decode("#244E23")); 
		clientsLbl.setHorizontalAlignment(JLabel.LEFT); 
		clientsLbl.setHorizontalAlignment(SwingConstants.LEFT); 
		headerPnl.add(clientsLbl, BorderLayout.NORTH);
		
		RoundPanel searchBarPnl = new RoundPanel(30);  
		searchBarPnl.setBackground(Color.white);
		searchBarPnl.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));  
		searchBarPnl.setForeground(Color.decode("#244E23")); 
		searchBarPnl.setLayout(new BorderLayout(15, 0));
		searchBarPnl.setPreferredSize(new Dimension(300, 30));
		headerPnl.add(searchBarPnl, BorderLayout.CENTER);
		
		image = new ImageIcon(ClientView.class.getResource("/images/magnifyingGlass.png")).getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH);
		imageIcon = new ImageIcon(image);
		JLabel logoTextLbl = new JLabel(imageIcon);
		searchBarPnl.add(logoTextLbl, BorderLayout.WEST);
		
		JTextField searchTxtFld = new JTextField();
		searchTxtFld.setBorder(null);
		searchTxtFld.setFont(new Font("Caladea Bold", Font.BOLD, 14)); 
		searchTxtFld.setForeground(Color.decode("#244E23"));
		searchTxtFld.setOpaque(false);
		searchBarPnl.add(searchTxtFld,  BorderLayout.CENTER);
		
		JPanel actionPnl = new JPanel();
		actionPnl.setLayout(new GridLayout(1, 3, 20, 0));
		actionPnl.setOpaque(false); 
		headerPnl.add(actionPnl, BorderLayout.EAST);
		
		RoundButton searchBttn = new RoundButton(30);
		searchBttn.setBackground(Color.decode("#244E23"));
		searchBttn.setFont(new Font("Caladea Bold", Font.BOLD, 20));
		searchBttn.setForeground(Color.white);
		searchBttn.setText("Buscar");
		actionPnl.add(searchBttn);
		
		searchBttn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				cc.searchClient();
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
				
		actionPnl.add(Box.createHorizontalStrut(0));
		
		RoundButton newClient = new RoundButton(30);
		newClient.setBackground(Color.decode("#2EA623"));
		newClient.setFont(new Font("Caladea Bold", Font.BOLD, 20));
		newClient.setForeground(Color.white);
		newClient.setText("Añadir");
		actionPnl.add(newClient); 

		newClient.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				cc.newClient();
			}
		});
		
		newClient.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent evt) {
				newClient.setBackground(Color.decode("#39C82C"));
		    }

		    public void mouseExited(MouseEvent evt) {
		    	newClient.setBackground(Color.decode("#2EA623"));
		    }
		});
		
		JPanel clientsPnl = new JPanel();
		clientsPnl.setBorder(BorderFactory.createEmptyBorder(40, 0, 10, 0));  
		clientsPnl.setLayout(new BorderLayout());	
		clientsPnl.setOpaque(false);
		mainPnl.add(clientsPnl, BorderLayout.CENTER);
		
		String[] column =  {"Nombre", "Dirección", "Número", "Correo", "Acción"};
		/*Object[][] data = {{"José Eduardo Guereque", "Del Árbol 169, col. La fuente", "6128682392", "Gue123@gmail.com", null},
						   {"Ángel Gabriel Mendoza", "Del Árbol 169, col. La fuente", "6151093321", "litrin@gmail.com", null},
						   {"Christian Ivan Rivera", "Del Árbol 169, col. La fuente", "6121761317", "civan@gmail.com", null}, 
						   {"Luis Miguel Pérez", "Del Árbol 169, col. La fuente", "6122170991", "lucatero@gmail.com", null}};*/
		
		DefaultTableModel tableModel = new DefaultTableModel(column, 0);
		
		Object[][] info = cc.clientsTable();
		for(Object[] row : info) {	//Inserta clientes de la base de datos a la tabla
			tableModel.addRow(row);
		}
		
		InformationTable template = new InformationTable(frame, tableModel, Color.decode("#555BF6"));
		JTable clientsTable = template.createTable();
		
		TableActionEvent event = new TableActionEvent() {
            @Override
            public void onEdit(int row) {
            	int selectedRow = row;
            	System.out.println(cc.model.get().get(selectedRow).id);
                System.out.println("Edit row: " + (row+1));
                frame.dispose();
				cc.editClient(selectedRow);
            }

            @Override
            public void onDelete(int row) {
                if(clientsTable.isEditing()) {
                	clientsTable.getCellEditor().stopCellEditing();
                }
                System.out.println("Delete  row : " + (row+1));
                tableModel.removeRow(row);
            }

            @Override
            public void onView(int row) {
                System.out.println("Viewed row : " + (row+1));
                frame.remove(mainPnl);
                frame.dispose();
				cc.clientHistory();
            }
        };
        
        clientsTable.getColumnModel().getColumn(0).setCellRenderer(new TextWrapCellRender());
		clientsTable.getColumnModel().getColumn(1).setCellRenderer(new TextWrapCellRender());
		clientsTable.getColumnModel().getColumn(2).setCellRenderer(new TextWrapCellRender());
		clientsTable.getColumnModel().getColumn(3).setCellRenderer(new TextWrapCellRender());
		
		clientsTable.getColumnModel().getColumn(4).setCellRenderer(new TableActionCellRender());
		clientsTable.getColumnModel().getColumn(4).setCellEditor(new TableActionCellEditor(event));
				
		JScrollPane scrollPane = new JScrollPane(clientsTable);
		scrollPane.setBorder(BorderFactory.createEmptyBorder(1, 1, 1, 1));
		scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		scrollPane.setOpaque(false);
	    scrollPane.getViewport().setOpaque(false);
		clientsPnl.add(scrollPane, BorderLayout.CENTER);
	
		frame.setVisible(true);
	}
	
	public void searchClient() {
		cc = new ClientController(frame.getTitle(), frame.getWidth(), frame.getHeight());
		
		mainPnl = new JPanel();
		mainPnl.setBackground(Color.white);
		mainPnl.setBorder(BorderFactory.createEmptyBorder(30, 45, 30, 45)); 
		mainPnl.setLayout(new BorderLayout());
		frame.add(mainPnl, BorderLayout.CENTER);
		
		JPanel headerPnl = new JPanel();
		headerPnl.setLayout(new GridLayout(2, 1, 0, 15));
		headerPnl.setOpaque(false); 
		mainPnl.add(headerPnl, BorderLayout.NORTH);
		
		JLabel clientsLbl = new JLabel("Cliente");
		clientsLbl.setFont(new Font("Caladea Bold", Font.BOLD, 36));
		clientsLbl.setForeground(Color.decode("#244E23")); 
		clientsLbl.setHorizontalAlignment(JLabel.LEFT); 
		clientsLbl.setHorizontalAlignment(SwingConstants.LEFT); 
		headerPnl.add(clientsLbl);
		
		JPanel clientsPnl = new JPanel();
		clientsPnl.setBorder(BorderFactory.createEmptyBorder(40, 0, 10, 0));  
		clientsPnl.setLayout(new BorderLayout());	
		clientsPnl.setOpaque(false);
		mainPnl.add(clientsPnl, BorderLayout.CENTER);
		
		String[] column =  {"Nombre", "Dirección", "Número", "Correo", "Acción"};
		/*Object[][] data = {{"José Eduardo Guereque", "Del Árbol 169, col. La fuente", "6128682392", "Gue123@gmail.com", null},
						   {"Ángel Gabriel Mendoza", "Del Árbol 169, col. La fuente", "6151093321", "litrin@gmail.com", null},
						   {"Christian Ivan Rivera", "Del Árbol 169, col. La fuente", "6121761317", "civan@gmail.com", null}, 
						   {"Luis Miguel Pérez", "Del Árbol 169, col. La fuente", "6122170991", "lucatero@gmail.com", null}};*/
		
		DefaultTableModel tableModel = new DefaultTableModel(column, 0);
		
		Object[][] info = cc.clientsTable();
		for(Object[] row : info) {	//Inserta clientes de la base de datos a la tabla
			tableModel.addRow(row);
		}
		
		InformationTable template = new InformationTable(frame, tableModel, Color.decode("#555BF6"));
		JTable clientsTable = template.createTable();
		
		TableActionEvent event = new TableActionEvent() {
            @Override
            public void onEdit(int row) {
            	int selectedRow = row;
            	System.out.println(cc.model.get().get(selectedRow).id);
                System.out.println("Edit row: " + (row+1));
                frame.dispose();
				cc.editClient(selectedRow);
            }

            @Override
            public void onDelete(int row) {
                if(clientsTable.isEditing()) {
                	clientsTable.getCellEditor().stopCellEditing();
                }
                System.out.println("Delete  row : " + (row+1));
                tableModel.removeRow(row);
            }

            @Override
            public void onView(int row) {
                System.out.println("Viewed row : " + (row+1));
                frame.remove(mainPnl);
                frame.dispose();
				cc.clientHistory();
            }
        };
        
        clientsTable.getColumnModel().getColumn(0).setCellRenderer(new TextWrapCellRender());
		clientsTable.getColumnModel().getColumn(1).setCellRenderer(new TextWrapCellRender());
		clientsTable.getColumnModel().getColumn(2).setCellRenderer(new TextWrapCellRender());
		clientsTable.getColumnModel().getColumn(3).setCellRenderer(new TextWrapCellRender());
		
		clientsTable.getColumnModel().getColumn(4).setCellRenderer(new TableActionCellRender());
		clientsTable.getColumnModel().getColumn(4).setCellEditor(new TableActionCellEditor(event));
				
		JScrollPane scrollPane = new JScrollPane(clientsTable);
		scrollPane.setBorder(BorderFactory.createEmptyBorder(1, 1, 1, 1));
		scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		scrollPane.setOpaque(false);
	    scrollPane.getViewport().setOpaque(false);
		clientsPnl.add(scrollPane, BorderLayout.CENTER);
		
		frame.setVisible(true);	
	}
	
	public void newClient() {
		cc = new ClientController(frame.getTitle(), frame.getWidth(), frame.getHeight());
		
		mainPnl = new JPanel();
		mainPnl.setBackground(Color.white);
		mainPnl.setBorder(BorderFactory.createEmptyBorder(30, 45, 30, 45)); 
		mainPnl.setLayout(new BorderLayout());
		frame.add(mainPnl, BorderLayout.CENTER);
		
		JPanel headerPnl = new JPanel();
		headerPnl.setLayout(new BorderLayout(20, 15));
		headerPnl.setOpaque(false); 
		mainPnl.add(headerPnl, BorderLayout.NORTH);
		
		JLabel clientsLbl = new JLabel("Añadir cliente");
		clientsLbl.setFont(new Font("Caladea Bold", Font.BOLD, 36));
		clientsLbl.setForeground(Color.decode("#244E23")); 
		clientsLbl.setHorizontalAlignment(JLabel.LEFT);
		clientsLbl.setHorizontalAlignment(SwingConstants.LEFT);  
		headerPnl.add(clientsLbl, BorderLayout.NORTH);
			
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
				Object[] options = {"Volver","Salir"};
				
				image = new ImageIcon(ClientView.class.getResource("/images/errorCircle.png")).getImage().getScaledInstance(45, 45, Image.SCALE_SMOOTH);
				imageIcon = new ImageIcon(image);
				
				message = "Todos los cambios se perderán.";
				int opc = JOptionPane.showOptionDialog(null, message, "Cancelar acción", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE, imageIcon, options, null);
				
				if(opc==1) {
					frame.dispose();
					cc.clients();
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
		
		ClientFormPanel form = new ClientFormPanel(frame);
		JPanel formPanel = form.createClientForm();
		mainPnl.add(formPanel, BorderLayout.CENTER);
		
		frame.setVisible(true);
		
		RoundButton newClient = new RoundButton(30);
		newClient.setBackground(Color.decode("#2EA623"));
		newClient.setFont(new Font("Caladea Bold", Font.BOLD, 20));
		newClient.setForeground(Color.white);
		newClient.setText("Añadir");
		actionPnl.add(newClient); 

		newClient.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(!form.clientFormEmptyFields()) {
					
					//Sentencia para crear nuevo cliente cuando se realize registro
					cc.clientCreate(form.getNameTxtFld(), form.getSurnameTxtFld(), form.getCountryCodeCmbBx(), form.getPhoneTxtFld(), form.getAdressTxtFld(), 
							form.getAdress2TxtFld(), form.getCityTxtFld(), form.getStateTxtFld(), form.getCodeTxtFld(), form.getEmailTxtFld());
					
					image = new ImageIcon(ClientView.class.getResource("/images/checkCircle.png")).getImage().getScaledInstance(45, 45, Image.SCALE_SMOOTH); 
	   				imageIcon = new ImageIcon(image);
	   				
					message = "Cliente creado correctamente.";
					JOptionPane.showMessageDialog(null, message, "Acción exitosa", JOptionPane.INFORMATION_MESSAGE, imageIcon); 
					
					frame.dispose();
					cc.clients();
				}
			}
		});
		
		newClient.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent evt) {
				newClient.setBackground(Color.decode("#39C82C"));
		    }

		    public void mouseExited(MouseEvent evt) {
		    	newClient.setBackground(Color.decode("#2EA623"));
		    }
		});
		
		frame.setVisible(true);
	}

	public void editClient(int selectedRow) {
		cc = new ClientController(frame.getTitle(), frame.getWidth(), frame.getHeight());
		
		mainPnl = new JPanel();
		mainPnl.setBackground(Color.white);
		mainPnl.setBorder(BorderFactory.createEmptyBorder(30, 45, 30, 45)); 
		mainPnl.setLayout(new BorderLayout());
		frame.add(mainPnl, BorderLayout.CENTER);
		
		JPanel headerPnl = new JPanel();
		headerPnl.setLayout(new BorderLayout(20, 15));
		headerPnl.setOpaque(false); 
		mainPnl.add(headerPnl, BorderLayout.NORTH);
		
		JLabel clientsLbl = new JLabel("Editar cliente");
		clientsLbl.setFont(new Font("Caladea Bold", Font.BOLD, 36));
		clientsLbl.setForeground(Color.decode("#244E23")); 
		clientsLbl.setHorizontalAlignment(JLabel.LEFT); 
		clientsLbl.setHorizontalAlignment(SwingConstants.LEFT); 
		headerPnl.add(clientsLbl, BorderLayout.NORTH);
		
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
				Object[] options = {"Volver", "Salir"};
				
				image = new ImageIcon(ActionButtonPanel.class.getResource("/images/errorCircle.png")).getImage().getScaledInstance(45, 45, Image.SCALE_SMOOTH);
				imageIcon = new ImageIcon(image);
				
				String message = "Todos los cambios se perderán.";
				int opc = JOptionPane.showOptionDialog(null, message, "Cancelar acción", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE, imageIcon, options, null);
				
				if(opc==1) {
					frame.dispose();
					cc.clients();
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
			
	
		ClientFormPanel form = new ClientFormPanel(frame);
		JPanel formPanel = form.createClientForm();
		mainPnl.add(formPanel, BorderLayout.CENTER); 
		
		
		RoundButton saveBttn = new RoundButton(30);
		saveBttn.setBackground(Color.decode("#555BF6"));
		saveBttn.setFont(new Font("Caladea Bold", Font.BOLD, 20));
		saveBttn.setForeground(Color.white);
		saveBttn.setText("Guardar");
		actionPnl.add(saveBttn); 

		saveBttn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(!form.clientFormEmptyFields()) {
					cc.clientUpdate(selectedRow, form.getNameTxtFld(), form.getSurnameTxtFld(), form.getCountryCodeCmbBx(), form.getPhoneTxtFld(), form.getAdressTxtFld(), 
							form.getAdress2TxtFld(), form.getCityTxtFld(), form.getStateTxtFld(), form.getCodeTxtFld(), form.getEmailTxtFld());
					
					image = new ImageIcon(ClientView.class.getResource("/images/checkCircle.png")).getImage().getScaledInstance(45, 45, Image.SCALE_SMOOTH); 
	   				imageIcon = new ImageIcon(image);
	   				
					message = "Cliente actualizado correctamente.";
					JOptionPane.showMessageDialog(null, message, "Acción exitosa", JOptionPane.INFORMATION_MESSAGE, imageIcon); 
					
					frame.dispose();
					cc.clients();
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
	
	public void clientHistory() {
		cc = new ClientController(frame.getTitle(), frame.getWidth(), frame.getHeight());
		
		mainPnl = new JPanel();
		mainPnl.setBackground(Color.white);
		mainPnl.setBorder(BorderFactory.createEmptyBorder(30, 45, 30, 45)); 
		mainPnl.setLayout(new BorderLayout());
		frame.add(mainPnl, BorderLayout.CENTER);
		
		JPanel headerPnl = new JPanel();
		headerPnl.setLayout(new BorderLayout(20, 15));
		headerPnl.setOpaque(false); 
		mainPnl.add(headerPnl, BorderLayout.NORTH);
		
		JLabel clientsLbl = new JLabel("Consultar historial de compras");
		clientsLbl.setFont(new Font("Caladea Bold", Font.BOLD, 36));
		clientsLbl.setForeground(Color.decode("#244E23"));
		clientsLbl.setHorizontalAlignment(JLabel.LEFT); 
		clientsLbl.setHorizontalAlignment(SwingConstants.LEFT); 
		headerPnl.add(clientsLbl, BorderLayout.NORTH);
				
		JPanel actionPnl = new JPanel();
		actionPnl.setLayout(new GridLayout(1, 4, 20, 0));
		actionPnl.setOpaque(false); 
		headerPnl.add(actionPnl, BorderLayout.EAST);
		
		actionPnl.add(Box.createHorizontalStrut(0));
		actionPnl.add(Box.createHorizontalStrut(0));
		actionPnl.add(Box.createHorizontalStrut(0));
		
		RoundButton downloadBttn = new RoundButton(30);
		downloadBttn.setBackground(Color.decode("#367181"));
		downloadBttn.setFont(new Font("Caladea Bold", Font.BOLD, 20));
		downloadBttn.setForeground(Color.white);
		downloadBttn.setText("Descargar");
		actionPnl.add(downloadBttn); 

		downloadBttn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				cc.clients();
			}
		});
		
		downloadBttn.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent evt) {
				downloadBttn.setBackground(Color.decode("#264F59"));
		    }

		    public void mouseExited(MouseEvent evt) {
		    	downloadBttn.setBackground(Color.decode("#367181"));
		    }
		});
		
		
		JPanel clientsPnl = new JPanel();
		clientsPnl.setBorder(BorderFactory.createEmptyBorder(20, 0, 10, 0));  
		clientsPnl.setLayout(new GridLayout(2, 1, 0, 30));	
		clientsPnl.setOpaque(false);
		mainPnl.add(clientsPnl, BorderLayout.CENTER);
		
		JPanel clientTablePnl = new JPanel();
		clientTablePnl.setLayout(new BorderLayout(0, 30));
		clientTablePnl.setOpaque(false);
		clientsPnl.add(clientTablePnl);
		
		JLabel clientLbl = new JLabel("Cliente");
		clientLbl.setFont(new Font("Caladea Regular", Font.PLAIN, 24));
		clientLbl.setForeground(Color.decode("#244E23"));
		clientLbl.setHorizontalAlignment(JLabel.LEFT); 
		clientLbl.setVerticalAlignment(JLabel.BOTTOM); 
		clientTablePnl.add(clientLbl, BorderLayout.NORTH);
		
		String[] columnClient =  {"Nombre", "Dirección", "Número", "Correo"};
		
		Object[][] dataClient = {{"Ángel Gabriel", "Del Árbol 169, col. La fuente", "6122170991", "angel@gmail.com"}};
		
		DefaultTableModel clientTableModel = new DefaultTableModel(dataClient, columnClient);
		
		InformationTable clientTemplate = new InformationTable(frame, clientTableModel, Color.decode("#555BF6"));
		JTable clientsTable = clientTemplate.createTable();
				
		clientsTable.getColumnModel().getColumn(0).setCellRenderer(new TextWrapCellRender());
		clientsTable.getColumnModel().getColumn(1).setCellRenderer(new TextWrapCellRender());
		clientsTable.getColumnModel().getColumn(2).setCellRenderer(new TextWrapCellRender());
		clientsTable.getColumnModel().getColumn(3).setCellRenderer(new TextWrapCellRender());
		
		JScrollPane clientScrllPn = new JScrollPane(clientsTable);
		clientScrllPn.setBorder(BorderFactory.createEmptyBorder(1, 1, 1, 1));
		clientScrllPn.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		clientScrllPn.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		clientScrllPn.setOpaque(false);
	    clientScrllPn.getViewport().setOpaque(false);
	    clientTablePnl.add(clientScrllPn, BorderLayout.CENTER);
		
		
	    JPanel ordersTablePnl = new JPanel();
	    ordersTablePnl.setLayout(new BorderLayout(0, 30));
	    ordersTablePnl.setOpaque(false);
		clientsPnl.add(ordersTablePnl);
	    
		JLabel historyLbl = new JLabel("Historial");
		historyLbl.setFont(new Font("Caladea Regular", Font.PLAIN, 24));
		historyLbl.setForeground(Color.decode("#244E23"));
		historyLbl.setHorizontalAlignment(JLabel.LEFT); 
		historyLbl.setVerticalAlignment(JLabel.BOTTOM); 
		ordersTablePnl.add(historyLbl, BorderLayout.NORTH);
		
		String[] columnOrders =  {"Fecha", "Dirección", "Monto", "Órden"};
		
		Object[][] dataOrder = {{"27/05/25", "Del Árbol 169, col. La fuente", "$15.00", "Cacahuates enchilados"}};
		
		DefaultTableModel ordersTableModel = new DefaultTableModel(dataOrder, columnOrders);
		
		InformationTable ordersTemplate = new InformationTable(frame, ordersTableModel, Color.decode("#555BF6"));
		JTable ordersTable = ordersTemplate.createTable();
				
		ordersTable.getColumnModel().getColumn(0).setCellRenderer(new TextWrapCellRender());
		ordersTable.getColumnModel().getColumn(1).setCellRenderer(new TextWrapCellRender());
		ordersTable.getColumnModel().getColumn(2).setCellRenderer(new TextWrapCellRender());
		ordersTable.getColumnModel().getColumn(3).setCellRenderer(new TextWrapCellRender());
		
		JScrollPane historyScrllPn = new JScrollPane(ordersTable);
		historyScrllPn.setBorder(BorderFactory.createEmptyBorder(1, 1, 1, 1));
		historyScrllPn.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		historyScrllPn.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		historyScrllPn.setOpaque(false);
	    historyScrllPn.getViewport().setOpaque(false);
	    ordersTablePnl.add(historyScrllPn, BorderLayout.CENTER);
	
		frame.setVisible(true);
	}
}