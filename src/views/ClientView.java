package views;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

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
	}
	
	public void clients() {
		mainPnl = new JPanel();
		mainPnl.setBackground(Color.white);
		mainPnl.setBorder(BorderFactory.createEmptyBorder(30, 45, 30, 45)); 
		mainPnl.setLayout(new BorderLayout());
		frame.add(mainPnl, BorderLayout.CENTER);
		
		JPanel headerPnl = new JPanel();
		headerPnl.setLayout(new GridLayout(2, 1, 0, 15));
		headerPnl.setOpaque(false); 
		mainPnl.add(headerPnl, BorderLayout.NORTH);
		
		JLabel clientsLbl = new JLabel("Clientes");
		clientsLbl.setFont(new Font("Caladea Bold", Font.BOLD, 36));
		clientsLbl.setForeground(Color.decode("#244E23")); 
		clientsLbl.setHorizontalAlignment(JLabel.LEFT); 
		clientsLbl.setHorizontalAlignment(SwingConstants.LEFT); 
		headerPnl.add(clientsLbl);
		
		JPanel actionPnl = new JPanel();
		actionPnl.setLayout(new GridLayout(1, 4, 20, 0));
		//actionPnl.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		actionPnl.setOpaque(false); 
		headerPnl.add(actionPnl);
		
		RoundPanel searchBarPnl = new RoundPanel(30);  
		searchBarPnl.setBackground(Color.white);
		searchBarPnl.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));  
		searchBarPnl.setForeground(Color.decode("#244E23")); 
		searchBarPnl.setLayout(new BorderLayout(15, 0));
		searchBarPnl.setPreferredSize(new Dimension(300, 30));
		actionPnl.add(searchBarPnl);
		
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
		
		RoundButton searchBttn = new RoundButton(30);
		searchBttn.setBackground(Color.decode("#244E23"));
		searchBttn.setFont(new Font("Caladea Bold", Font.BOLD, 20));
		searchBttn.setForeground(Color.white);
		searchBttn.setText("Buscar");
		actionPnl.add(searchBttn);
		
		searchBttn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				frame.remove(mainPnl);
				searchClient();
				frame.repaint();
				frame.revalidate();
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
				frame.remove(mainPnl);
				frame.repaint();
				frame.revalidate();
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
		//clientsPnl.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		mainPnl.add(clientsPnl, BorderLayout.CENTER);
		
		String[] column =  {"Nombre", "Dirección", "Número", "Correo", "Acción"};
		Object[][] data = {{"José Eduardo Guereque", "Del Árbol 169, col. La fuente", "6128682392", "Gue123@gmail.com", null},
						   {"Ángel Gabriel Mendoza", "Del Árbol 169, col. La fuente", "6151093321", "litrin@gmail.com", null},
						   {"Christian Ivan Rivera", "Del Árbol 169, col. La fuente", "6121761317", "civan@gmail.com", null}, 
						   {"Luis Miguel Pérez", "Del Árbol 169, col. La fuente", "6122170991", "lucatero@gmail.com", null}};
		
		DefaultTableModel tableModel = new DefaultTableModel(data, column);
		JTable clientsTable = new JTable(tableModel);
		
		TableActionEvent event = new TableActionEvent() {
            @Override
            public void onEdit(int row) {
                System.out.println("Edit row: " + (row+1));
                frame.remove(mainPnl);
				editClient();
				frame.repaint();
				frame.revalidate();
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
                clientHistory();
				frame.repaint();
				frame.revalidate();
            }
        };
		
		clientsTable.getColumn("Acción").setCellRenderer(new TableActionCellRender());
		clientsTable.getColumn("Acción").setCellEditor(new TableActionCellEditor(event));
		
		clientsTable.setFont(new Font("Caladea Bold", Font.BOLD, 16));
		clientsTable.setDefaultEditor(Object.class, null);
		clientsTable.setRowHeight(40);
		clientsTable.setShowGrid(false);
		clientsTable.setShowHorizontalLines(true);
		clientsTable.setShowVerticalLines(false);
		
		clientsTable.getTableHeader().setBackground(Color.decode("#555BF6"));
		clientsTable.getTableHeader().setForeground(Color.white);
		clientsTable.getTableHeader().setFont(new Font("Caladea Bold", Font.BOLD, 16));
		clientsTable.getTableHeader().setReorderingAllowed(false);
		clientsTable.getTableHeader().setResizingAllowed(false);
		
		clientsTable.setBackground(Color.white);
		clientsTable.setForeground(Color.decode("#244E23"));
		clientsTable.setSelectionBackground(Color.white);
		clientsTable.setSelectionForeground(Color.decode("#2EA623"));
		clientsTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
				
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
		
		frame.setVisible(true);	
	}
	
	public void editClient() {
		mainPnl = new JPanel();
		mainPnl.setBackground(Color.white);
		mainPnl.setBorder(BorderFactory.createEmptyBorder(30, 45, 30, 45)); 
		mainPnl.setLayout(new BorderLayout());
		frame.add(mainPnl, BorderLayout.CENTER);
		
		JPanel headerPnl = new JPanel();
		headerPnl.setLayout(new GridLayout(2, 1, 0, 15));
		headerPnl.setOpaque(false); 
		mainPnl.add(headerPnl, BorderLayout.NORTH);
		
		JLabel clientsLbl = new JLabel("Editar cliente");
		clientsLbl.setFont(new Font("Caladea Bold", Font.BOLD, 36));
		clientsLbl.setForeground(Color.decode("#244E23")); 
		clientsLbl.setHorizontalAlignment(JLabel.LEFT); 
		clientsLbl.setHorizontalAlignment(SwingConstants.LEFT); 
		headerPnl.add(clientsLbl);
		
		JPanel actionPnl = new JPanel();
		actionPnl.setLayout(new GridLayout(1, 4, 20, 0));
		actionPnl.setOpaque(false); 
		headerPnl.add(actionPnl);
		
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
				frame.remove(mainPnl);
				clients();
				frame.repaint();
				frame.revalidate();
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
				
		
		RoundButton saveBttn = new RoundButton(30);
		saveBttn.setBackground(Color.decode("#555BF6"));
		saveBttn.setFont(new Font("Caladea Bold", Font.BOLD, 20));
		saveBttn.setForeground(Color.white);
		saveBttn.setText("Guardar");
		actionPnl.add(saveBttn); 

		saveBttn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				frame.remove(mainPnl);
				clients();
				frame.repaint();
				frame.revalidate();
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
	
		ClientFormPanel form = new ClientFormPanel(frame);
		JPanel formPanel = form.createClientForm();
		mainPnl.add(formPanel, BorderLayout.CENTER); 
		
		frame.setVisible(true);
	}
	
	public void clientHistory() {
		mainPnl = new JPanel();
		mainPnl.setBackground(Color.white);
		mainPnl.setBorder(BorderFactory.createEmptyBorder(30, 45, 30, 45)); 
		mainPnl.setLayout(new BorderLayout());
		frame.add(mainPnl, BorderLayout.CENTER);
		
		JPanel headerPnl = new JPanel();
		headerPnl.setLayout(new GridLayout(2, 1, 0, 15));
		headerPnl.setOpaque(false); 
		mainPnl.add(headerPnl, BorderLayout.NORTH);
		
		JLabel clientsLbl = new JLabel("Consultar historial de compras");
		clientsLbl.setFont(new Font("Caladea Bold", Font.BOLD, 36));
		clientsLbl.setForeground(Color.decode("#244E23"));
		clientsLbl.setHorizontalAlignment(JLabel.LEFT); 
		clientsLbl.setHorizontalAlignment(SwingConstants.LEFT); 
		headerPnl.add(clientsLbl);
				
		JPanel actionPnl = new JPanel();
		actionPnl.setLayout(new GridLayout(1, 4, 20, 0));
		actionPnl.setOpaque(false); 
		headerPnl.add(actionPnl);
		
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
				frame.remove(mainPnl);
				clients();
				frame.repaint();
				frame.revalidate();
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
		clientsPnl.setBorder(BorderFactory.createEmptyBorder(40, 0, 10, 0));  
		clientsPnl.setLayout(new BorderLayout());	
		clientsPnl.setOpaque(false);
		mainPnl.add(clientsPnl, BorderLayout.CENTER);
		
		String[] column =  {"Fecha", "Dirección", "Monto", "Órden"};
		
		Object[][] data = {{"27/05/25", "Del Árbol 169, col. La fuente", "$15.00", "Cacahuates enchilados"}};
		
		DefaultTableModel tableModel = new DefaultTableModel(data, column);
		JTable clientsTable = new JTable(tableModel);
		
		clientsTable.setFont(new Font("Caladea Bold", Font.BOLD, 16));
		clientsTable.setDefaultEditor(Object.class, null);
		clientsTable.setRowHeight(40);
		clientsTable.setShowGrid(false);
		clientsTable.setShowHorizontalLines(true);
		clientsTable.setShowVerticalLines(false);
		
		clientsTable.getTableHeader().setBackground(Color.decode("#555BF6"));
		clientsTable.getTableHeader().setForeground(Color.white);
		clientsTable.getTableHeader().setFont(new Font("Caladea Bold", Font.BOLD, 16));
		clientsTable.getTableHeader().setReorderingAllowed(false);
		clientsTable.getTableHeader().setResizingAllowed(false);
		
		clientsTable.setBackground(Color.white);
		clientsTable.setForeground(Color.decode("#244E23"));
		clientsTable.setSelectionBackground(Color.white);
		clientsTable.setSelectionForeground(Color.decode("#2EA623"));
		clientsTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
				
		JScrollPane scrollPane = new JScrollPane(clientsTable);
		scrollPane.setBorder(BorderFactory.createEmptyBorder(1, 1, 1, 1));
		scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		scrollPane.setOpaque(false);
	    scrollPane.getViewport().setOpaque(false);
		clientsPnl.add(scrollPane, BorderLayout.CENTER);
	
		frame.setVisible(true);
	}
}