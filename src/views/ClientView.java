package views;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import controllers.ClientController;
import customClasses.*;
import models.Client;

public class ClientView {

	JFrame frame; 
	JPanel mainPnl;
	Image image;
	ImageIcon imageIcon;
	String message;
	String currentWindow;
	int relativeXSize;
	int relativeYSize;
	ClientController cc;
	OptionPaneButton optionPane;
	OptionPaneButton loadingOptPn;
	int opt;
	
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
		JPanel buttonPnl = sideBar.createSidePanel();
		frame.add(buttonPnl, BorderLayout.WEST);
		
		buttonPnl.getComponent(3).setBackground(Color.decode("#3C7E3A"));
		sideBar.removeClientListener();
		
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
				if(searchTxtFld.getText().equals("")) {
					optionPane = new OptionPaneButton("Cliente", "Campo sin completar.");
					optionPane.errorOptionPane();
				}
				else{
					loadingOptPn = new OptionPaneButton("Cargando información...", "Por favor espere.");
					loadingOptPn.loadingOptionPane(frame, 3000);
					Object[][] clients = cc.searchClientsTable(searchTxtFld.getText());	//Realiza busqueda de clientes
					if(clients.length == 0)	{ //Condicional que verifica si se encontraron clientes o no
						optionPane = new OptionPaneButton("Cliente no encontrado", "No se ha encontrado ningún cliente.");
						optionPane.warningOptionPane();
					}else {
						loadingOptPn = new OptionPaneButton("Cargando información...", "Por favor espere.");
						loadingOptPn.loadingOptionPane(frame, 3000);
						frame.dispose();
						cc.searchClient(clients);						
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
				loadingOptPn = new OptionPaneButton("Cargando ventana...", "Por favor espere.");
				loadingOptPn.loadingOptionPane(frame, 1000);
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
		JScrollPane scrollPane = template.createTable();
		JTable clientsTable = template.getTable() ;
		
		TableActionEvent event = new TableActionEvent() {
            @Override
            public void onEdit(int row) {
            	System.out.println(cc.model.get().get(row).id);
                System.out.println("Edit row: " + (row+1));
                
                loadingOptPn = new OptionPaneButton("Cargando ventana...", "Por favor espere.");
				loadingOptPn.loadingOptionPane(frame, 3000);
                frame.dispose();
				cc.editClient(row);
            }

            @Override
            public void onDelete(int row) {
                if(clientsTable.isEditing()) {
                	clientsTable.getCellEditor().stopCellEditing();
                }
                System.out.println("Delete  row : " + (row+1));
                
                loadingOptPn = new OptionPaneButton("Cargando información...", "Por favor espere.");
				loadingOptPn.loadingOptionPane(frame, 3000);
                cc.clientDelete(row);
                tableModel.removeRow(row);
            }

            @Override
            public void onView(int row) {
                System.out.println("Viewed row : " + (row+1));
                
                loadingOptPn = new OptionPaneButton("Cargando ventana...", "Por favor espere.");
				loadingOptPn.loadingOptionPane(frame, 3000);
                frame.dispose();
				cc.clientHistory(row);
            }

        };
        
        clientsTable.getColumnModel().getColumn(0).setCellRenderer(new TextWrapCellRender());
		clientsTable.getColumnModel().getColumn(1).setCellRenderer(new TextWrapCellRender());
		clientsTable.getColumnModel().getColumn(2).setCellRenderer(new TextWrapCellRender());
		clientsTable.getColumnModel().getColumn(3).setCellRenderer(new TextWrapCellRender());
		
		clientsTable.getColumnModel().getColumn(4).setCellRenderer(new TableActionCellRender());
		clientsTable.getColumnModel().getColumn(4).setCellEditor(new TableActionCellEditor(event));
		
		clientsPnl.add(scrollPane, BorderLayout.CENTER);
	
		frame.setVisible(true);
	}
	
	public void searchClient(Object[][] clients) {
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
		DefaultTableModel tableModel = new DefaultTableModel(column, 0);
		
		for(Object[] row : clients) {	//Inserta clientes de la base de datos a la tabla
			tableModel.addRow(row);
		}

		InformationTable clientsTemplate = new InformationTable(frame, tableModel, Color.decode("#555BF6"));
		JScrollPane clientScrollPane = clientsTemplate.createTable();
		JTable clientsTable = clientsTemplate.getTable() ;
		
		TableActionEvent event = new TableActionEvent() {
            @Override
            public void onEdit(int row) {
            	System.out.println(cc.model.get().get(row).id);
                System.out.println("Edit row: " + (row+1));
                
                loadingOptPn = new OptionPaneButton("Cargando ventana...", "Por favor espere.");
				loadingOptPn.loadingOptionPane(frame, 3000);
                frame.dispose();
				cc.editClient(row);
            }

            @Override
            public void onDelete(int row) {
                if(clientsTable.isEditing()) {
                	clientsTable.getCellEditor().stopCellEditing();
                }
                System.out.println("Delete  row : " + (row+1));
                
                loadingOptPn = new OptionPaneButton("Cargando información...", "Por favor espere.");
				loadingOptPn.loadingOptionPane(frame, 3000);
                cc.clientDelete(row);
                tableModel.removeRow(row);
            }

            @Override
            public void onView(int row) {
                System.out.println("Viewed row : " + (row+1));
                
                loadingOptPn = new OptionPaneButton("Cargando información...", "Por favor espere.");
				loadingOptPn.loadingOptionPane(frame, 3000);
                frame.dispose();
				cc.clientHistory(row);
            }

        };
        
        clientsTable.getColumnModel().getColumn(0).setCellRenderer(new TextWrapCellRender());
		clientsTable.getColumnModel().getColumn(1).setCellRenderer(new TextWrapCellRender());
		clientsTable.getColumnModel().getColumn(2).setCellRenderer(new TextWrapCellRender());
		clientsTable.getColumnModel().getColumn(3).setCellRenderer(new TextWrapCellRender());
		
		clientsTable.getColumnModel().getColumn(4).setCellRenderer(new TableActionCellRender());
		clientsTable.getColumnModel().getColumn(4).setCellEditor(new TableActionCellEditor(event));
	
		clientsPnl.add(clientScrollPane, BorderLayout.CENTER);
		
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
				optionPane = new OptionPaneButton("Volver a menú", "Todos los cambios se perderán.", "  Salir  ");
				opt = optionPane.destructiveOptionPane();
				
				if(opt==1) {
					loadingOptPn = new OptionPaneButton("Cargando ventana...", "Por favor espere.");
					loadingOptPn.loadingOptionPane(frame, 3000);
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
					loadingOptPn = new OptionPaneButton("Cargando información...", "Por favor espere.");
					loadingOptPn.loadingOptionPane(frame, 3000);
					
					//Sentencia para crear nuevo cliente cuando se realize registro
					cc.clientCreate(form.getNameTxtFld(), form.getSurnameTxtFld(), form.getCountryCodeCmbBx(), form.getPhoneTxtFld(), form.getAdressTxtFld(), 
							form.getAdress2TxtFld(), form.getCityTxtFld(), form.getStateTxtFld(), form.getCodeTxtFld(), form.getEmailTxtFld());
		
					optionPane = new OptionPaneButton("Acción exitosa", "Cliente creado correctamente.");
					optionPane.checkOptionPane();
					
					loadingOptPn = new OptionPaneButton("Cargando ventana...", "Por favor espere.");
					loadingOptPn.loadingOptionPane(frame, 3000);
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
				optionPane = new OptionPaneButton("Volver a menú", "Todos los cambios se perderán.", "  Salir  ");
				opt = optionPane.destructiveOptionPane();
				
				if(opt==1) {
					loadingOptPn = new OptionPaneButton("Cargando ventana...", "Por favor espere.");
					loadingOptPn.loadingOptionPane(frame, 3000);
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
		
		Client c = cc.clientRead(selectedRow);
		//Formulario lleno
		form.setNameTxtFld(c.name);
		form.setSurnameTxtFld(c.last_Name);
		form.setPhoneTxtFld(c.phone_Number.substring(3));	//Corta el numero de telefono los primeros 3 digitos
		form.setEmailTxtFld(c.email);
		form.setAdressTxtFld(c.address_1);
		form.setAdress2TxtFld(c.address_2);
		form.setCityTxtFld(c.city);
		form.setStateTxtFld(c.state);
		form.setCodeTxtFld(String.valueOf(c.postal_Code));	//Convierte el codigo postal en un String
		
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
					loadingOptPn = new OptionPaneButton("Cargando información...", "Por favor espere.");
					loadingOptPn.loadingOptionPane(frame, 3000);
					cc.clientUpdate(selectedRow, form.getNameTxtFld(), form.getSurnameTxtFld(), form.getCountryCodeCmbBx(), form.getPhoneTxtFld(), form.getAdressTxtFld(), 
							form.getAdress2TxtFld(), form.getCityTxtFld(), form.getStateTxtFld(), form.getCodeTxtFld(), form.getEmailTxtFld());
	   				
	   				optionPane = new OptionPaneButton("Acción exitosa", "Cliente actualizado correctamente.");
	   				optionPane.checkOptionPane();
					
	   				loadingOptPn = new OptionPaneButton("Cargando ventana...", "Por favor espere.");
					loadingOptPn.loadingOptionPane(frame, 3000);
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
	
	public void clientHistory(int selectedRow) {
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
				
				PdfGenerate pdf = new PdfGenerate();
				pdf.pdfGenerating(cc.clientRead(selectedRow));
				
				loadingOptPn = new OptionPaneButton("Cargando ventana...", "Por favor espere.");
				loadingOptPn.loadingOptionPane(frame, 3000);
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
		
		System.out.println(selectedRow);
		Client c = cc.clientRead(selectedRow);
		
		Object[] info = 
			{
					c.name + " " + c.last_Name,
					c.address_1,
					c.phone_Number,
					c.email
			};
		
		DefaultTableModel clientTableModel = new DefaultTableModel(columnClient, 0);
		
		clientTableModel.addRow(info);
		
		InformationTable clientsTemplate = new InformationTable(frame, clientTableModel, Color.decode("#555BF6"));
		JScrollPane clientsScrollPane = clientsTemplate.createTable();
		JTable clientsTable = clientsTemplate.getTable() ;
				
		clientsTable.getColumnModel().getColumn(0).setCellRenderer(new TextWrapCellRender());
		clientsTable.getColumnModel().getColumn(1).setCellRenderer(new TextWrapCellRender());
		clientsTable.getColumnModel().getColumn(2).setCellRenderer(new TextWrapCellRender());
		clientsTable.getColumnModel().getColumn(3).setCellRenderer(new TextWrapCellRender());
		
	    clientTablePnl.add(clientsScrollPane, BorderLayout.CENTER);
		
		
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
		DefaultTableModel ordersTableModel = new DefaultTableModel(columnOrders, 0);
		
		InformationTable ordersTemplate = new InformationTable(frame, ordersTableModel, Color.decode("#555BF6"));
		JScrollPane ordersScrollPane = ordersTemplate.createTable();
		JTable ordersTable = ordersTemplate.getTable() ;
				
		ordersTable.getColumnModel().getColumn(0).setCellRenderer(new TextWrapCellRender());
		ordersTable.getColumnModel().getColumn(1).setCellRenderer(new TextWrapCellRender());
		ordersTable.getColumnModel().getColumn(2).setCellRenderer(new TextWrapCellRender());
		ordersTable.getColumnModel().getColumn(3).setCellRenderer(new TextWrapCellRender());
		
	    ordersTablePnl.add(ordersScrollPane, BorderLayout.CENTER);
	
		frame.setVisible(true);
	}
}