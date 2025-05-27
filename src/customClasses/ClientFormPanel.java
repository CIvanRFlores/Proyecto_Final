package customClasses;

import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.plaf.basic.BasicComboBoxUI;

public class ClientFormPanel {
	
	public JFrame frame;
	public JTextField nameTxtFld;
	public JTextField surnameTxtFld;
	public JComboBox<String> countryCodeCmbBx;
	public JTextField phoneTxtFld;
	public JTextField emailTxtFld;
	public JTextField adressTxtFld;
	public JTextField adress2TxtFld;
	public JTextField cityTxtFld;
	public JTextField stateTxtFld;
	public JTextField codeTxtFld;
	public int relativeXSize;
	public Font font; 
	
	
	public ClientFormPanel(JFrame frame) {
		this.frame = frame;
	}
	
	public JPanel createClientForm() {
		JPanel clientsPnl = new JPanel();
		clientsPnl.setBorder(BorderFactory.createEmptyBorder(30, 0, 30, 0));  
		clientsPnl.setLayout(new GridLayout(10, 2, 20, 20));	
		clientsPnl.setOpaque(false);
		
		JLabel nameLbl = new JLabel("Nombre/s");
		nameLbl.setForeground(Color.decode("#244E23")); 
		nameLbl.setFont(new Font("Caladea Bold", Font.BOLD, 16));
		nameLbl.setHorizontalAlignment(SwingConstants.LEFT);  
		nameLbl.setVerticalAlignment(SwingConstants.BOTTOM); 
		nameLbl.setOpaque(false);
		clientsPnl.add(nameLbl);
		
		JLabel surnameLbl = new JLabel("Apellidos");
		surnameLbl.setForeground(Color.decode("#244E23")); 
		surnameLbl.setFont(new Font("Caladea Bold", Font.BOLD, 16));
		surnameLbl.setHorizontalAlignment(SwingConstants.LEFT); 
		surnameLbl.setVerticalAlignment(SwingConstants.BOTTOM); 
		surnameLbl.setOpaque(false);
		clientsPnl.add(surnameLbl);
		
		
		RoundPanel nameTxtFldPnl = new RoundPanel(30);  
		nameTxtFldPnl.setBackground(Color.decode("#EDEDED"));
		nameTxtFldPnl.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));  
		nameTxtFldPnl.setForeground(Color.decode("#244E23")); 
		nameTxtFldPnl.setLayout(new BorderLayout());
		clientsPnl.add(nameTxtFldPnl);
		
		nameTxtFld = new JTextField("");
		nameTxtFld.setBorder(null);
		nameTxtFld.setFont(new Font("Caladea Bold", Font.BOLD, 16)); 
		nameTxtFld.setForeground(Color.decode("#244E23")); 
		nameTxtFld.setOpaque(false);
		nameTxtFldPnl.add(nameTxtFld,  BorderLayout.CENTER);
		
		
		RoundPanel surnameTxtFldPnl = new RoundPanel(30);  
		surnameTxtFldPnl.setBackground(Color.decode("#EDEDED"));
		surnameTxtFldPnl.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));  
		surnameTxtFldPnl.setForeground(Color.decode("#244E23")); 
		surnameTxtFldPnl.setLayout(new BorderLayout());
		clientsPnl.add(surnameTxtFldPnl);
		
		surnameTxtFld = new JTextField("");
		surnameTxtFld.setBorder(null);
		surnameTxtFld.setFont(new Font("Caladea Bold", Font.BOLD, 16)); 
		surnameTxtFld.setForeground(Color.decode("#244E23")); 
		surnameTxtFld.setOpaque(false);
		surnameTxtFldPnl.add(surnameTxtFld,  BorderLayout.CENTER);
		
		
		JLabel phoneLbl = new JLabel("Número de teléfono");
		phoneLbl.setForeground(Color.decode("#244E23")); 
		phoneLbl.setFont(new Font("Caladea Bold", Font.BOLD, 16));
		phoneLbl.setHorizontalAlignment(SwingConstants.LEFT);  
		phoneLbl.setVerticalAlignment(SwingConstants.BOTTOM); 
		phoneLbl.setOpaque(false);
		clientsPnl.add(phoneLbl);
		
		clientsPnl.add(Box.createHorizontalStrut(0));
		
		JPanel phonePnl = new JPanel();
		phonePnl.setLayout(new GridLayout(1, 2, 20, 0));
		phonePnl.setOpaque(false);
		clientsPnl.add(phonePnl);
		
		RoundPanel countryCmbBxPnl = new RoundPanel(30);  
		countryCmbBxPnl.setBackground(Color.decode("#EDEDED"));
		countryCmbBxPnl.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));  
		countryCmbBxPnl.setForeground(Color.decode("#244E23")); 
		countryCmbBxPnl.setLayout(new BorderLayout(15, 0));
		phonePnl.add(countryCmbBxPnl, BorderLayout.WEST);
		
		String ambitos[] = {"+52", "+13", "+44", "+55", "+30"};
		
		countryCodeCmbBx = new JComboBox<>(ambitos);
		countryCodeCmbBx.setBorder(null); 
		countryCodeCmbBx.setForeground(Color.decode("#244E23")); 
		countryCodeCmbBx.setFont(new Font("Caladea Bold", Font.BOLD, 16)); 
		countryCodeCmbBx.setOpaque(false);
		countryCodeCmbBx.setUI(new BasicComboBoxUI());
		countryCmbBxPnl.add(countryCodeCmbBx, BorderLayout.CENTER);
		
		RoundPanel phoneTxtFldPnl = new RoundPanel(30);  
		phoneTxtFldPnl.setBackground(Color.decode("#EDEDED"));
		phoneTxtFldPnl.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));  
		phoneTxtFldPnl.setForeground(Color.decode("#244E23")); 
		phoneTxtFldPnl.setLayout(new BorderLayout(15, 0));
		phonePnl.add(phoneTxtFldPnl, BorderLayout.CENTER);
		
		phoneTxtFld = new JTextField("");
		phoneTxtFld.setBorder(null);
		phoneTxtFld.setFont(new Font("Caladea Bold", Font.BOLD, 16)); 
		phoneTxtFld.setForeground(Color.decode("#244E23")); 
		phoneTxtFld.setOpaque(false);
		phoneTxtFldPnl.add(phoneTxtFld,  BorderLayout.CENTER);
		
		clientsPnl.add(Box.createHorizontalStrut(0));
		
		JLabel emailLbl = new JLabel("Correo");
		emailLbl.setForeground(Color.decode("#244E23")); 
		emailLbl.setFont(new Font("Caladea Bold", Font.BOLD, 16));
		emailLbl.setHorizontalAlignment(SwingConstants.LEFT); 
		emailLbl.setVerticalAlignment(SwingConstants.BOTTOM); 
		emailLbl.setOpaque(false);
		clientsPnl.add(emailLbl);
		
		clientsPnl.add(Box.createHorizontalStrut(0));
		
		RoundPanel emailTxtFldPnl = new RoundPanel(30);  
		emailTxtFldPnl.setBackground(Color.decode("#EDEDED"));
		emailTxtFldPnl.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));  
		emailTxtFldPnl.setForeground(Color.decode("#244E23")); 
		emailTxtFldPnl.setLayout(new BorderLayout());
		clientsPnl.add(emailTxtFldPnl);
		
		emailTxtFld = new JTextField("");
		emailTxtFld.setBorder(null);
		emailTxtFld.setFont(new Font("Caladea Bold", Font.BOLD, 16)); 
		emailTxtFld.setForeground(Color.decode("#244E23")); 
		emailTxtFld.setOpaque(false);
		emailTxtFldPnl.add(emailTxtFld,  BorderLayout.CENTER);
		
		clientsPnl.add(Box.createHorizontalStrut(0));
		
		JLabel adressLbl = new JLabel("Dirección");
		adressLbl.setForeground(Color.decode("#244E23")); 
		adressLbl.setFont(new Font("Caladea Bold", Font.BOLD, 16));
		adressLbl.setHorizontalAlignment(SwingConstants.LEFT);  
		adressLbl.setVerticalAlignment(SwingConstants.BOTTOM); 
		adressLbl.setOpaque(false);
		clientsPnl.add(adressLbl);
		
		clientsPnl.add(Box.createHorizontalStrut(0));
		
		RoundPanel adressTxtFldPnl = new RoundPanel(30);  
		adressTxtFldPnl.setBackground(Color.decode("#EDEDED"));
		adressTxtFldPnl.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));  
		adressTxtFldPnl.setForeground(Color.decode("#244E23")); 
		adressTxtFldPnl.setLayout(new BorderLayout());
		clientsPnl.add(adressTxtFldPnl);
		
		adressTxtFld = new JTextField("");
		adressTxtFld.setBorder(null);
		adressTxtFld.setFont(new Font("Caladea Bold", Font.BOLD, 16)); 
		adressTxtFld.setForeground(Color.decode("#244E23")); 
		adressTxtFld.setOpaque(false);
		adressTxtFldPnl.add(adressTxtFld,  BorderLayout.CENTER);
		
		
		RoundPanel adress2TxtFldPnl = new RoundPanel(30);  
		adress2TxtFldPnl.setBackground(Color.decode("#EDEDED"));
		adress2TxtFldPnl.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));  
		adress2TxtFldPnl.setForeground(Color.decode("#244E23")); 
		adress2TxtFldPnl.setLayout(new BorderLayout());
		clientsPnl.add(adress2TxtFldPnl);
		
		adress2TxtFld = new JTextField("");
		adress2TxtFld.setBorder(null);
		adress2TxtFld.setFont(new Font("Caladea Bold", Font.BOLD, 16)); 
		adress2TxtFld.setForeground(Color.decode("#244E23")); 
		adress2TxtFld.setOpaque(false);
		adress2TxtFldPnl.add(adress2TxtFld,  BorderLayout.CENTER);
		
		
		JLabel cityLbl = new JLabel("Ciudad");
		cityLbl.setForeground(Color.decode("#244E23")); 
		cityLbl.setFont(new Font("Caladea Bold", Font.BOLD, 16));
		cityLbl.setHorizontalAlignment(SwingConstants.LEFT);  
		cityLbl.setVerticalAlignment(SwingConstants.BOTTOM); 
		cityLbl.setOpaque(false);
		clientsPnl.add(cityLbl);
		
		JPanel extraInfoLblPnl = new JPanel();
		extraInfoLblPnl.setLayout(new GridLayout(1, 2, 20, 0));
		extraInfoLblPnl.setOpaque(false);
		clientsPnl.add(extraInfoLblPnl);
		
		JLabel stateLbl = new JLabel("Estado");
		stateLbl.setForeground(Color.decode("#244E23")); 
		stateLbl.setFont(new Font("Caladea Bold", Font.BOLD, 16));
		stateLbl.setHorizontalAlignment(SwingConstants.LEFT);  
		stateLbl.setVerticalAlignment(SwingConstants.BOTTOM); 
		stateLbl.setOpaque(false);
		extraInfoLblPnl.add(stateLbl);
		
		JLabel codeLbl = new JLabel("Código postal");
		codeLbl.setForeground(Color.decode("#244E23")); 
		codeLbl.setFont(new Font("Caladea Bold", Font.BOLD, 16));
		codeLbl.setHorizontalAlignment(SwingConstants.LEFT); 
		stateLbl.setVerticalAlignment(SwingConstants.BOTTOM); 
		codeLbl.setOpaque(false);
		extraInfoLblPnl.add(codeLbl);
		
		
		RoundPanel cityTxtFldPnl = new RoundPanel(30);  
		cityTxtFldPnl.setBackground(Color.decode("#EDEDED"));
		cityTxtFldPnl.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));  
		cityTxtFldPnl.setForeground(Color.decode("#244E23")); 
		cityTxtFldPnl.setLayout(new BorderLayout());
		clientsPnl.add(cityTxtFldPnl);
		
		cityTxtFld = new JTextField("");
		cityTxtFld.setBorder(null);
		cityTxtFld.setFont(new Font("Caladea Bold", Font.BOLD, 16)); 
		cityTxtFld.setForeground(Color.decode("#244E23")); 
		cityTxtFld.setOpaque(false);
		cityTxtFldPnl.add(cityTxtFld,  BorderLayout.CENTER);
		
		
		JPanel extraInfoTxtFld = new JPanel();
		extraInfoTxtFld.setLayout(new GridLayout(1, 2, 20, 0));
		extraInfoTxtFld.setOpaque(false);
		clientsPnl.add(extraInfoTxtFld);
		
		RoundPanel stateTxtFldPnl = new RoundPanel(30);  
		stateTxtFldPnl.setBackground(Color.decode("#EDEDED"));
		stateTxtFldPnl.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));  
		stateTxtFldPnl.setForeground(Color.decode("#244E23")); 
		stateTxtFldPnl.setLayout(new BorderLayout());
		extraInfoTxtFld.add(stateTxtFldPnl);
		
		stateTxtFld = new JTextField("");
		stateTxtFld.setBorder(null);
		stateTxtFld.setFont(new Font("Caladea Bold", Font.BOLD, 16)); 
		stateTxtFld.setForeground(Color.decode("#244E23")); 
		stateTxtFld.setOpaque(false);
		stateTxtFldPnl.add(stateTxtFld,  BorderLayout.CENTER);
		
		RoundPanel codeTxtFldPnl = new RoundPanel(30);  
		codeTxtFldPnl.setBackground(Color.decode("#EDEDED"));
		codeTxtFldPnl.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));  
		codeTxtFldPnl.setForeground(Color.decode("#244E23")); 
		codeTxtFldPnl.setLayout(new BorderLayout());
		extraInfoTxtFld.add(codeTxtFldPnl);
		
		codeTxtFld = new JTextField("");
		codeTxtFld.setBorder(null);
		codeTxtFld.setFont(new Font("Caladea Bold", Font.BOLD, 16)); 
		codeTxtFld.setForeground(Color.decode("#244E23")); 
		codeTxtFld.setOpaque(false);
		codeTxtFldPnl.add(codeTxtFld,  BorderLayout.CENTER);
		
		/**cuando la ventana es redimensionada, los elementos dentro de ella cambian de tamaño**/
		frame.addComponentListener(new ComponentAdapter() {
            public void componentResized(ComponentEvent e) {
            	relativeXSize = (int) (frame.getHeight()*0.016);
            	font = new Font("Caladea Bold", Font.BOLD, relativeXSize);
       			nameLbl.setFont(font);
       			surnameLbl.setFont(font);
       			nameTxtFld.setFont(font);
       			surnameTxtFld.setFont(font);
       			phoneLbl.setFont(font);
       			countryCodeCmbBx.setFont(font); 
       			phoneTxtFld.setFont(font);
       			emailLbl.setFont(font);
       			emailTxtFld.setFont(font);
       			adressLbl.setFont(font);
       			adressTxtFld.setFont(font);
       			adress2TxtFld.setFont(font);
       			cityLbl.setFont(font);
       			stateLbl.setFont(font);
       			codeLbl.setFont(font);
       			cityTxtFld.setFont(font);
       			stateTxtFld.setFont(font);
       			codeTxtFld.setFont(font);
       			
       			relativeXSize = (int) (frame.getHeight()*0.03);
       			clientsPnl.setBorder(BorderFactory.createEmptyBorder(relativeXSize, 0, relativeXSize, 0));
       			
       			relativeXSize = (int) (frame.getHeight()*0.01);
       			Border border = BorderFactory.createEmptyBorder(relativeXSize, relativeXSize, relativeXSize, relativeXSize);
       			nameTxtFldPnl.setBorder(border);  
       			surnameTxtFldPnl.setBorder(border);  
       			countryCmbBxPnl.setBorder(border);  
       			phoneTxtFldPnl.setBorder(border);  
       			emailTxtFldPnl.setBorder(border); 
       			adressTxtFldPnl.setBorder(border); 
       			adress2TxtFldPnl.setBorder(border); 
       			cityTxtFldPnl.setBorder(border); 
       			stateTxtFldPnl.setBorder(border); 
       			codeTxtFldPnl.setBorder(border); 
       			
       			frame.repaint();
            }
        });
		
		return clientsPnl;
	}
	
	public String getNameTxtFld() {
		return nameTxtFld.getText();
	}
	
	public String getSurnameTxtFld() {
		return surnameTxtFld.getText();
	}
	
	public String getCountryCodeCmbBx() {
		return countryCodeCmbBx.getSelectedItem().toString();
	}
	
	public String getPhoneTxtFld() {
		return phoneTxtFld.getText();
	}
	
	public String getEmailTxtFld() {
		return emailTxtFld.getText();
	}
	
	public String getAdressTxtFld() {
		return adressTxtFld.getText();
	}
	
	public String getAdress2TxtFld() {
		return adress2TxtFld.getText();
	}
	
	public String getCityTxtFld() {
		return cityTxtFld.getText();
	}
	
	public String getStateTxtFld() {
		return stateTxtFld.getText();
	}
	
	public String getCodeTxtFld() {
		return codeTxtFld.getText();
	}
	
}
