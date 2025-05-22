package views;

import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;
import javax.swing.*;

import controllers.DishController;
import customClasses.RoundButton;
import customClasses.RoundPanel;
import models.AuthModel;

public class AuthView {

	public boolean passwordVisible = false;
	public JFrame frame;
	public Image image;
	public ImageIcon imageIcon;
	public int relativeXSize;
	public int relativeYSize;
	DishController dc;
	
	
	public AuthView(String title, int frameWidth, int frameHeight) {
		frame = new JFrame(); 
		imageIcon = new ImageIcon(AuthView.class.getResource("/images/elManglarLogo.png")); //icono de la ventana
		frame.setBackground(Color.pink);
		frame.setIconImage(imageIcon.getImage());
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
		frame.setSize(frameWidth, frameHeight); 
		frame.setLocationRelativeTo(null); //colocar la ventana en el centro de la pantalla
		frame.setResizable(true); 
		frame.setMinimumSize(new Dimension(350, 350));
		frame.setTitle(title); 
	}
	
	public void login() {
		//importar el método que permite utilizar fuentes personalizadas
		boldCustomFont();
		regularCustomFont();
	
		JPanel imagePnl = new JPanel();
		imagePnl.setBackground(Color.white);
		imagePnl.setBorder(BorderFactory.createEmptyBorder(60, 60, 60, 30)); 
		imagePnl.setLayout(new BorderLayout());
		frame.add(imagePnl, BorderLayout.WEST);
		
		image = new ImageIcon(AuthView.class.getResource("/images/mangroveImage.png")).getImage().getScaledInstance(500, 1000, Image.SCALE_SMOOTH);
		imageIcon = new ImageIcon(image);
		JLabel backgroundDeco = new JLabel(imageIcon);
		imagePnl.add(backgroundDeco, BorderLayout.CENTER);
		
		
		JPanel sidePnl = new JPanel();
		sidePnl.setBackground(Color.white);
		sidePnl.setBorder(BorderFactory.createEmptyBorder(60, 30, 60, 60)); 
		sidePnl.setLayout(new GridLayout(6, 1, 0, 25));
		frame.add(sidePnl, BorderLayout.CENTER );
		
		JLabel welcomeLbl = new JLabel("Bienvenido");
		welcomeLbl.setFont(new Font("Caladea Bold", Font.BOLD, 50));
		welcomeLbl.setForeground(Color.decode("#244E23")); 
		welcomeLbl.setHorizontalAlignment(JLabel.CENTER); 
		welcomeLbl.setHorizontalAlignment(SwingConstants.CENTER); 
		sidePnl.add(welcomeLbl);
		
	
		image = new ImageIcon(AuthView.class.getResource("/images/elManglarLogoText.png")).getImage().getScaledInstance(72, 86, Image.SCALE_SMOOTH);
		imageIcon = new ImageIcon(image);
		JLabel logoTextLbl = new JLabel(imageIcon);
		sidePnl.add(logoTextLbl);


		RoundPanel usernamePnl = new RoundPanel(30);  
		usernamePnl.setBackground(Color.white);
		usernamePnl.setForeground(Color.decode("#8A5627")); 
		usernamePnl.setLayout(new BorderLayout(15, 0));
		sidePnl.add(usernamePnl);
		
		JLabel usernameLbl = new JLabel("Nombre de usuario");
		usernameLbl.setBorder(BorderFactory.createEmptyBorder(0, 15, 0, 0)); 
		usernameLbl.setFont(new Font("Caladea Bold", Font.BOLD, 18));
		usernameLbl.setForeground(Color.decode("#999999")); 
		usernameLbl.setHorizontalAlignment(JLabel.CENTER);
		usernameLbl.setHorizontalAlignment(SwingConstants.LEFT);
		usernamePnl.add(usernameLbl, BorderLayout.NORTH);
		
		image = new ImageIcon(AuthView.class.getResource("/images/users.png")).getImage().getScaledInstance(22, 22, Image.SCALE_SMOOTH);
		imageIcon = new ImageIcon(image);
		JLabel usersIconLbl = new JLabel(imageIcon);
		usersIconLbl.setBorder(BorderFactory.createEmptyBorder(0, 15, 0, 0)); 
		usernamePnl.add(usersIconLbl, BorderLayout.WEST);
		
		JTextField usernameTxtFld = new JTextField();
		usernameTxtFld.setBorder(null);
		usernameTxtFld.setFont(new Font("Caladea Bold", Font.BOLD, 14)); 
		usernameTxtFld.setForeground(Color.decode("#999999")); 
		usernameTxtFld.setOpaque(false);
		usernamePnl.add(usernameTxtFld, BorderLayout.CENTER);
		
		
		/**PANEL CON JTEXTFIELD DE CONTRASEÑA DE USUARIO**/
		RoundPanel passwordPnl = new RoundPanel(30); 
		passwordPnl.setBackground(Color.white); 
		passwordPnl.setForeground(Color.decode("#8A5627"));
		passwordPnl.setLayout(new BorderLayout(15, 0));
		sidePnl.add(passwordPnl);
		
		JLabel passwordLbl = new JLabel("Contraseña");
		passwordLbl.setBorder(BorderFactory.createEmptyBorder(0, 15, 0, 0)); 
		passwordLbl.setFont(new Font("Caladea Bold", Font.BOLD, 18));
		passwordLbl.setForeground(Color.decode("#999999"));
		passwordLbl.setHorizontalAlignment(JLabel.CENTER); 
		passwordLbl.setHorizontalAlignment(SwingConstants.LEFT); 
		passwordPnl.add(passwordLbl, BorderLayout.NORTH);
		
		image = new ImageIcon(AuthView.class.getResource("/images/key.png")).getImage().getScaledInstance(22, 22, Image.SCALE_SMOOTH);
		imageIcon = new ImageIcon(image);
		JLabel keyIconLbl = new JLabel(imageIcon);
		keyIconLbl.setBorder(BorderFactory.createEmptyBorder(0, 15, 0, 0)); 
		passwordPnl.add(keyIconLbl, BorderLayout.WEST);
	
		//panel con el campo para escribir la contraseña y el botón para mostrarla/ocultarla
		JPanel jPssWrdFldPnl = new JPanel();
		jPssWrdFldPnl.setOpaque(false);
		jPssWrdFldPnl.setLayout(new BorderLayout());
		passwordPnl.add(jPssWrdFldPnl, BorderLayout.CENTER);
		
		JPasswordField passwordFld = new JPasswordField();
		passwordFld.setEchoChar('*'); //caracteres
		passwordFld.setBorder(null);
		passwordFld.setForeground(Color.decode("#999999"));
		passwordFld.setOpaque(false);
		passwordFld.setFont(new Font("Caladea Bold", Font.BOLD, 14)); 
		jPssWrdFldPnl.add(passwordFld, BorderLayout.CENTER);
		
		image = new ImageIcon(AuthView.class.getResource("/images/eyeOff.png")).getImage().getScaledInstance(22, 22, Image.SCALE_SMOOTH);
		imageIcon = new ImageIcon(image);
		JButton seeBttn = new JButton();
		seeBttn.setIcon(imageIcon);
		seeBttn.setFocusPainted(false); //hace invisible el recuadro blanco al presionar el botón
		seeBttn.setBorderPainted(false); //hace invisible el borde por defecto de los botones   
		seeBttn.setContentAreaFilled(false); //hace invisible la animacion al presionar el botón
		jPssWrdFldPnl.add(seeBttn, BorderLayout.EAST);
		
		seeBttn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				relativeXSize = (int) (frame.getWidth()*0.022);
       			relativeYSize = relativeXSize;
				if(passwordVisible) {
					passwordFld.setEchoChar((char)0); //representar los caracteres de la contraseña con letras 'ABCdario'	
					passwordFld.setFont(new Font("Caladea Bold", Font.BOLD, (int) (frame.getWidth()*0.012))); 
					image = new ImageIcon(AuthView.class.getResource("/images/eye.png")).getImage().getScaledInstance(relativeXSize, relativeYSize, Image.SCALE_SMOOTH);
					imageIcon = new ImageIcon(image);
					seeBttn.setIcon(imageIcon);
					passwordVisible = false;
				}else {
					passwordFld.setEchoChar('*'); //representar los caracteres de la contraseña con asteriscos '*'				
					passwordFld.setFont(new Font("Caladea Bold", Font.BOLD, (int) (frame.getWidth()*0.014))); 
					image = new ImageIcon(AuthView.class.getResource("/images/eyeOff.png")).getImage().getScaledInstance(relativeXSize, relativeYSize, Image.SCALE_SMOOTH);
					imageIcon = new ImageIcon(image);
					seeBttn.setIcon(imageIcon);
					passwordVisible = true;
				}
				
			}
			
		});

		JCheckBox rememberUserChckBx = new JCheckBox("Recordar usuario");
		rememberUserChckBx.setFont(new Font("Caladea Bold", Font.BOLD, 16));
		rememberUserChckBx.setForeground(Color.decode("#244E23"));
		rememberUserChckBx.setOpaque(false); 
		
		sidePnl.add(rememberUserChckBx);
		
		RoundButton loginBttn = new RoundButton(30);
		loginBttn.setBackground(Color.decode("#306572"));
		loginBttn.setFont(new Font("Caladea Bold", Font.BOLD, 30));
		loginBttn.setForeground(Color.white);
		loginBttn.setText("Iniciar sesión");
		sidePnl.add(loginBttn);
		
		loginBttn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
				boolean flag1 = false, flag2 = false;
				
				String username = usernameTxtFld.getText();
				if(!usernameTxtFld.getText().equals("")) { 
					flag1 = true;
				}
				
				String passTxt = new String(passwordFld.getPassword());
				if(!passTxt.equals("")) { 
					flag2 = true;
				}
				
				String message;
				//validar si los dos campos han sido completados
				if(flag1 && flag2) {
					AuthModel am = new AuthModel();
					//cuando ambos campos coinciden con los datos de la cuenta del usuario
					if(am.login(username, passTxt)) {
						image = new ImageIcon(AuthView.class.getResource("/images/checkCircle.png")).getImage().getScaledInstance(45, 45, Image.SCALE_SMOOTH); 
	       				imageIcon = new ImageIcon(image);
	       				
						message = "Sesión iniciada correctamente.";
						JOptionPane.showMessageDialog(null, message, "Datos correctos", JOptionPane.INFORMATION_MESSAGE, imageIcon); 
						
						frame.dispose(); //destruir ventana actual y llevar al usuario a la ventana de platillos
						dc = new DishController("Platillos", frame.getWidth(), frame.getHeight()); //crear controlador de platillos y asignar parámetros a la ventana
						dc.dishes(); //llamar al método que crea y muestra la ventana de platillos
					}
					else { //cuando alguno de los dos campos no es correcto
						image = new ImageIcon(AuthView.class.getResource("/images/errorCircle.png")).getImage().getScaledInstance(45, 45, Image.SCALE_SMOOTH); 
	       				imageIcon = new ImageIcon(image);
	       				
						message = "Datos erróneos. Por favor, inténtelo otra vez.";
						JOptionPane.showMessageDialog(null, message, "Error al iniciar sesión", JOptionPane.ERROR_MESSAGE, imageIcon); 
					}
					
				}else { //uno o los dos componentes se encuentran vacíos
					
					image = new ImageIcon(AuthView.class.getResource("/images/warning.png")).getImage().getScaledInstance(45, 45, Image.SCALE_SMOOTH); 
       				imageIcon = new ImageIcon(image);
					
					message = "Uno o más campos se encuentran vacíos.";
					JOptionPane.showMessageDialog(null, message, "Campos vacíos", JOptionPane.WARNING_MESSAGE, imageIcon); 
				}
				
			}
			
		});
		
		//efecto hover
		loginBttn.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent evt) {
				loginBttn.setBackground(Color.decode("#264F59"));
		    }

		    public void mouseExited(MouseEvent evt) {
		    	loginBttn.setBackground(Color.decode("#367181"));
		    }
		});
		
		
		/**cuando la ventana es redimensionada, los elementos dentro de ella cambian de tamaño**/
		frame.addComponentListener(new ComponentAdapter() {
            public void componentResized(ComponentEvent e) {
            	relativeXSize = frame.getWidth()/2;
            	relativeYSize = frame.getHeight();
               	image = new ImageIcon(AuthView.class.getResource("/images/mangroveImage.png")).getImage().getScaledInstance(relativeXSize, relativeYSize, Image.SCALE_SMOOTH);
       			imageIcon = new ImageIcon(image);
       			backgroundDeco.setIcon(imageIcon);  
       			
       			relativeXSize = (int) (frame.getHeight()*0.086)-15;
       			relativeYSize = (int) (frame.getHeight()*0.086);
       			image = new ImageIcon(AuthView.class.getResource("/images/elManglarLogoText.png")).getImage().getScaledInstance(relativeXSize, relativeYSize, Image.SCALE_SMOOTH);
       			imageIcon = new ImageIcon(image);
       			logoTextLbl.setIcon(imageIcon); 
       			
       			relativeXSize = (int) (frame.getWidth()*0.022);
       			relativeYSize = relativeXSize;
       			image = new ImageIcon(AuthView.class.getResource("/images/users.png")).getImage().getScaledInstance(relativeXSize, relativeYSize, Image.SCALE_SMOOTH);
       			imageIcon = new ImageIcon(image);
       			usersIconLbl.setIcon(imageIcon);
       			
       			image = new ImageIcon(AuthView.class.getResource("/images/key.png")).getImage().getScaledInstance(relativeXSize, relativeYSize, Image.SCALE_SMOOTH);
       			imageIcon = new ImageIcon(image);
       			keyIconLbl.setIcon(imageIcon);
       			
       			if(passwordVisible) { 
       				image = new ImageIcon(AuthView.class.getResource("/images/eye.png")).getImage().getScaledInstance(relativeXSize, relativeYSize, Image.SCALE_SMOOTH);
       				imageIcon = new ImageIcon(image);
       				seeBttn.setIcon(imageIcon);
       				passwordFld.setFont(new Font("Caladea Bold", Font.BOLD, (int) (frame.getWidth()*0.012)));;
       			}else {
       				image = new ImageIcon(AuthView.class.getResource("/images/eyeOff.png")).getImage().getScaledInstance(relativeXSize, relativeYSize, Image.SCALE_SMOOTH);
       				imageIcon = new ImageIcon(image);
       				seeBttn.setIcon(imageIcon);
       				passwordFld.setFont(new Font("Caladea Bold", Font.BOLD, (int) (frame.getWidth()*0.014)));
       			}
       			
       			welcomeLbl.setFont(new Font("Caladea Bold", Font.BOLD, (int) (frame.getWidth()*0.04)));
       			loginBttn.setFont(new Font("Caladea Bold", Font.BOLD, (int) (frame.getWidth()*0.03)));
       			passwordLbl.setFont(new Font("Caladea Bold", Font.BOLD, (int) (frame.getWidth()*0.018)));
       			usernameLbl.setFont(new Font("Caladea Bold", Font.BOLD, (int) (frame.getWidth()*0.018)));
       			usernameTxtFld.setFont(new Font("Caladea Bold", Font.BOLD, (int) (frame.getWidth()*0.014)));;
       			rememberUserChckBx.setFont(new Font("Caladea Bold", Font.BOLD, (int) (frame.getWidth()*0.016)));
       			
       			relativeXSize = (int) (frame.getHeight()*0.06);
       			imagePnl.setBorder(BorderFactory.createEmptyBorder(relativeXSize, relativeXSize, relativeXSize, relativeXSize/2)); 
       			sidePnl.setBorder(BorderFactory.createEmptyBorder(relativeXSize, relativeXSize/2, relativeXSize, relativeXSize)); 
       			
       			relativeXSize = (int) (frame.getHeight()*0.005);
       			usernamePnl.setBorder(BorderFactory.createEmptyBorder(relativeXSize, relativeXSize, relativeXSize, relativeXSize)); 
       			passwordPnl.setBorder(BorderFactory.createEmptyBorder(relativeXSize, relativeXSize, relativeXSize, relativeXSize));
 
       			frame.repaint();
            }
        });
		
		frame.setVisible(true);
	}
	
	//método que importa fuente personalizada
	public void boldCustomFont() {
	    GraphicsEnvironment GE = GraphicsEnvironment.getLocalGraphicsEnvironment();
	    List<String> AVAILABLE_FONT_FAMILY_NAMES = Arrays.asList(GE.getAvailableFontFamilyNames());
	    
	    try {
	    	InputStream fontStream = getClass().getResourceAsStream("/files/Caladea-Bold.ttf"); //leer información del archivo
	    	
	        if(fontStream!=null) {
	            Font font = Font.createFont(Font.TRUETYPE_FONT, fontStream);
	            if(!AVAILABLE_FONT_FAMILY_NAMES.contains(font.getFontName())) {
	                GE.registerFont(font);
	            }
	        }else {
	            JOptionPane.showMessageDialog(null, "No se encontró la fuente.");
	        }
	    }catch(FontFormatException | IOException exception) {
	        JOptionPane.showMessageDialog(null, exception.getMessage());
	    }
	}
	
	public void regularCustomFont() {
	    GraphicsEnvironment GE = GraphicsEnvironment.getLocalGraphicsEnvironment();
	    List<String> AVAILABLE_FONT_FAMILY_NAMES = Arrays.asList(GE.getAvailableFontFamilyNames());
	    
	    try {
	    	InputStream fontStream = getClass().getResourceAsStream("/files/Caladea-Regular.ttf"); //leer información del archivo
	    	
	        if(fontStream!=null) {
	            Font font = Font.createFont(Font.TRUETYPE_FONT, fontStream);
	            if(!AVAILABLE_FONT_FAMILY_NAMES.contains(font.getFontName())) {
	                GE.registerFont(font);
	            }
	        }else {
	            JOptionPane.showMessageDialog(null, "No se encontró la fuente.");
	        }
	    }catch(FontFormatException | IOException exception) {
	        JOptionPane.showMessageDialog(null, exception.getMessage());
	    }
	}
}
