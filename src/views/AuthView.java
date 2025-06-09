package views;

import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;
import javax.swing.*;

import controllers.DishController;
import customClasses.OptionPaneButton;
import customClasses.RoundButton;
import customClasses.RoundPanel;
import models.AuthModel;

public class AuthView {

	boolean passwordVisible = false;
	JFrame frame;
	Image image;
	ImageIcon imageIcon;
	ActionListener validate;
	int relativeXSize;
	int relativeYSize;
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
		frame.setMinimumSize(new Dimension(352, 300));
		frame.setTitle(title); 
		
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
		sidePnl.setBorder(BorderFactory.createEmptyBorder(120, 30, 0, 60)); 
		sidePnl.setLayout(new BorderLayout(0, 20));
		frame.add(sidePnl, BorderLayout.CENTER );
		
		image = new ImageIcon(AuthView.class.getResource("/images/elManglarLogoText.png")).getImage().getScaledInstance(142, 162, Image.SCALE_SMOOTH);
		imageIcon = new ImageIcon(image);
		JLabel logoTextLbl = new JLabel(imageIcon);
		logoTextLbl.setHorizontalAlignment(JLabel.CENTER); 
		logoTextLbl.setVerticalAlignment(JLabel.BOTTOM);
		logoTextLbl.setOpaque(false);
		sidePnl.add(logoTextLbl, BorderLayout.NORTH);
		
		
		JPanel infoPnl = new JPanel();
		infoPnl.setBackground(Color.white);
		infoPnl.setLayout(new GridLayout(5, 1, 0, 20));
		sidePnl.add(infoPnl, BorderLayout.CENTER);
		
		JLabel welcomeLbl = new JLabel("Bienvenido");
		welcomeLbl.setFont(new Font("Caladea Bold", Font.BOLD, 50));
		welcomeLbl.setForeground(Color.decode("#244E23")); 
		welcomeLbl.setHorizontalAlignment(JLabel.CENTER); 
		welcomeLbl.setHorizontalAlignment(SwingConstants.CENTER); 
		welcomeLbl.setOpaque(false);
		infoPnl.add(welcomeLbl);

	
		RoundPanel usernamePnl = new RoundPanel(30);  
		usernamePnl.setBackground(Color.white); 
		usernamePnl.setForeground(Color.decode("#8A5627")); 
		usernamePnl.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5)); 
		usernamePnl.setLayout(new BorderLayout());
		infoPnl.add(usernamePnl);
		
		JLabel usernameLbl = new JLabel("Nombre de usuario");
		usernameLbl.setBorder(BorderFactory.createEmptyBorder(5, 5, 0, 5)); 
		usernameLbl.setFont(new Font("Caladea Bold", Font.BOLD, 18));
		usernameLbl.setForeground(Color.decode("#999999")); 
		usernameLbl.setHorizontalAlignment(JLabel.CENTER);
		usernameLbl.setHorizontalAlignment(SwingConstants.LEFT);
		usernamePnl.add(usernameLbl, BorderLayout.NORTH);
		
		image = new ImageIcon(AuthView.class.getResource("/images/user.png")).getImage().getScaledInstance(26, 26, Image.SCALE_SMOOTH);
		imageIcon = new ImageIcon(image);
		JLabel usersIconLbl = new JLabel(imageIcon);
		usersIconLbl.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5)); 
		usernamePnl.add(usersIconLbl, BorderLayout.WEST);
		
		JTextField usernameTxtFld = new JTextField();
		usernameTxtFld.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 5)); 
		usernameTxtFld.setFont(new Font("Caladea Bold", Font.BOLD, 16)); 
		usernameTxtFld.setForeground(Color.decode("#999999")); 
		usernameTxtFld.setOpaque(false);
		usernamePnl.add(usernameTxtFld, BorderLayout.CENTER);
	
		
		RoundPanel passwordPnl = new RoundPanel(30); 
		passwordPnl.setBackground(Color.white); 
		passwordPnl.setForeground(Color.decode("#8A5627"));
		passwordPnl.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5)); 
		passwordPnl.setLayout(new BorderLayout());
		infoPnl.add(passwordPnl);
		
		JLabel passwordLbl = new JLabel("Contraseña");
		passwordLbl.setFont(new Font("Caladea Bold", Font.BOLD, 18));
		passwordLbl.setForeground(Color.decode("#999999"));
		passwordLbl.setBorder(BorderFactory.createEmptyBorder(5, 5, 0, 5));
		passwordLbl.setHorizontalAlignment(JLabel.CENTER); 
		passwordLbl.setHorizontalAlignment(SwingConstants.LEFT); 
		passwordPnl.add(passwordLbl, BorderLayout.NORTH);
		
		image = new ImageIcon(AuthView.class.getResource("/images/key.png")).getImage().getScaledInstance(26, 26, Image.SCALE_SMOOTH);
		imageIcon = new ImageIcon(image);
		JLabel keyIconLbl = new JLabel(imageIcon);
		keyIconLbl.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5)); 
		passwordPnl.add(keyIconLbl, BorderLayout.WEST);
	
		//panel con el campo para escribir la contraseña y el botón para mostrarla/ocultarla
		JPanel jPssWrdFldPnl = new JPanel();
		jPssWrdFldPnl.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 5)); 
		jPssWrdFldPnl.setOpaque(false);
		jPssWrdFldPnl.setLayout(new BorderLayout());
		passwordPnl.add(jPssWrdFldPnl, BorderLayout.CENTER);
		
		JPasswordField passwordFld = new JPasswordField();
		passwordFld.setEchoChar('*'); //caracteres
		passwordFld.setBorder(null);
		passwordFld.setForeground(Color.decode("#999999"));
		passwordFld.setOpaque(false);
		passwordFld.setFont(new Font("Caladea Bold", Font.BOLD, 16)); 
		jPssWrdFldPnl.add(passwordFld, BorderLayout.CENTER);
		
		image = new ImageIcon(AuthView.class.getResource("/images/eyeOff.png")).getImage().getScaledInstance(26, 26, Image.SCALE_SMOOTH);
		imageIcon = new ImageIcon(image);
		JButton seeBttn = new JButton();
		seeBttn.setBorder(BorderFactory.createEmptyBorder(0, 0, 5, 5));
		seeBttn.setIcon(imageIcon);
		seeBttn.setFocusPainted(false); 
		seeBttn.setBorderPainted(false); 
		seeBttn.setContentAreaFilled(false); 
		jPssWrdFldPnl.add(seeBttn, BorderLayout.EAST);
		
		seeBttn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				relativeXSize = (int) (frame.getWidth()*0.026);
       			relativeYSize = relativeXSize;
				if(passwordVisible) {
					passwordFld.setEchoChar((char)0); //representar los caracteres de la contraseña con letras 'ABCdario'	
					passwordFld.setFont(new Font("Caladea Bold", Font.BOLD, (int) (frame.getWidth()*0.016))); 
					image = new ImageIcon(AuthView.class.getResource("/images/eye.png")).getImage().getScaledInstance(relativeXSize, relativeYSize, Image.SCALE_SMOOTH);
					imageIcon = new ImageIcon(image);
					seeBttn.setIcon(imageIcon);
					passwordVisible = false;
				}else {
					passwordFld.setEchoChar('*'); //representar los caracteres de la contraseña con asteriscos '*'				
					passwordFld.setFont(new Font("Caladea Bold", Font.BOLD, (int) (frame.getWidth()*0.015))); 
					image = new ImageIcon(AuthView.class.getResource("/images/eyeOff.png")).getImage().getScaledInstance(relativeXSize, relativeYSize, Image.SCALE_SMOOTH);
					imageIcon = new ImageIcon(image);
					seeBttn.setIcon(imageIcon);
					passwordVisible = true;
				}
				
			}
			
		});
		
		
		RoundButton loginBttn = new RoundButton(30);
		loginBttn.setBackground(Color.decode("#306572"));
		loginBttn.setFont(new Font("Caladea Bold", Font.BOLD, 30));
		loginBttn.setForeground(Color.white);
		loginBttn.setText("Iniciar sesión");
		infoPnl.add(loginBttn);
		
		validate = new ActionListener() {
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
				
				//validar si los dos campos han sido completados
				if(flag1 && flag2) {
					AuthModel am = new AuthModel();
					//cuando ambos campos coinciden con los datos de la cuenta del usuario
					if(am.login(username, passTxt)) {
						OptionPaneButton option = new OptionPaneButton("Datos correctos", "Sesión iniciada correctamente.");
						option.checkOptionPane();
						
						frame.dispose(); //destruir ventana actual
						dc = new DishController(frame.getTitle(), frame.getWidth(), frame.getHeight()); 
						dc.dishes(); 
					}
					else { //cuando alguno de los dos campos no es correcto
						OptionPaneButton option = new OptionPaneButton("Error al iniciar sesión", "Datos erróneos. Por favor, inténtelo otra vez.");
						option.errorOptionPane();
					}
					
				}else { //uno o los dos componentes se encuentran vacíos
					OptionPaneButton option = new OptionPaneButton("Campos vacíos", "Uno o más campos se encuentran vacíos.");
					option.warningOptionPane();
				}
				
			}
			
		};
		
		loginBttn.addActionListener(validate);
		
		//efecto hover
		loginBttn.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent evt) {
				loginBttn.setBackground(Color.decode("#264F59"));
		    }

		    public void mouseExited(MouseEvent evt) {
		    	loginBttn.setBackground(Color.decode("#306572"));
		    }
		});
		
		
		//dar enter a campos de texto
		usernameTxtFld.addKeyListener(new KeyAdapter() {
		    @Override
		    public void keyTyped(KeyEvent e) {
		        char c = e.getKeyChar();
		        if(c=='\n') {
		        	passwordFld.requestFocus();
		        	passwordFld.setFocusable(true);
		        }
		    }
		});
		
		passwordFld.addKeyListener(new KeyAdapter() {
		    @Override
		    public void keyTyped(KeyEvent e) {
		        char c = e.getKeyChar();
		        if(c=='\n') {
		        	loginBttn.doClick();
			        usernameTxtFld.requestFocus();
			        usernameTxtFld.setFocusable(true);
		        }
		    }
		});
		
		infoPnl.add(Box.createHorizontalStrut(0));
		
		
		/**cuando la ventana es redimensionada, los elementos dentro de ella cambian de tamaño**/
		frame.addComponentListener(new ComponentAdapter() {
            public void componentResized(ComponentEvent e) {
            	relativeXSize = frame.getWidth()/2;
            	relativeYSize = frame.getHeight();
               	image = new ImageIcon(AuthView.class.getResource("/images/mangroveImage.png")).getImage().getScaledInstance(relativeXSize, relativeYSize, Image.SCALE_SMOOTH);
       			imageIcon = new ImageIcon(image);
       			backgroundDeco.setIcon(imageIcon);  
       			
       			relativeXSize = (int) (frame.getHeight()*0.142) - (int) (frame.getHeight()*0.015);
       			relativeYSize = (int) (frame.getHeight()*0.162) - (int) (frame.getWidth()*0.02);
       			image = new ImageIcon(AuthView.class.getResource("/images/elManglarLogoText.png")).getImage().getScaledInstance(relativeXSize, relativeYSize, Image.SCALE_SMOOTH);
       			imageIcon = new ImageIcon(image);
       			logoTextLbl.setIcon(imageIcon); 
       			
       			relativeXSize = (int) (frame.getWidth()*0.026);
       			image = new ImageIcon(AuthView.class.getResource("/images/user.png")).getImage().getScaledInstance(relativeXSize, relativeXSize, Image.SCALE_SMOOTH);
       			imageIcon = new ImageIcon(image);
       			usersIconLbl.setIcon(imageIcon);
       			
       			image = new ImageIcon(AuthView.class.getResource("/images/key.png")).getImage().getScaledInstance(relativeXSize, relativeXSize, Image.SCALE_SMOOTH);
       			imageIcon = new ImageIcon(image);
       			keyIconLbl.setIcon(imageIcon);
       			
       			if(passwordVisible) { 
       				image = new ImageIcon(AuthView.class.getResource("/images/eye.png")).getImage().getScaledInstance(relativeXSize, relativeXSize, Image.SCALE_SMOOTH);
       				imageIcon = new ImageIcon(image);
       				seeBttn.setIcon(imageIcon);
       				passwordFld.setFont(new Font("Caladea Bold", Font.BOLD, (int) (frame.getWidth()*0.016)));;
       			}else {
       				image = new ImageIcon(AuthView.class.getResource("/images/eyeOff.png")).getImage().getScaledInstance(relativeXSize, relativeXSize, Image.SCALE_SMOOTH);
       				imageIcon = new ImageIcon(image);
       				seeBttn.setIcon(imageIcon);
       				passwordFld.setFont(new Font("Caladea Bold", Font.BOLD, (int) (frame.getWidth()*0.015)));
       			}
       			
       			welcomeLbl.setFont(new Font("Caladea Bold", Font.BOLD, (int) (frame.getWidth()*0.04)));
       			loginBttn.setFont(new Font("Caladea Bold", Font.BOLD, (int) (frame.getWidth()*0.03)));
       			passwordLbl.setFont(new Font("Caladea Bold", Font.BOLD, (int) (frame.getWidth()*0.018)));
       			usernameLbl.setFont(new Font("Caladea Bold", Font.BOLD, (int) (frame.getWidth()*0.018)));
       			usernameTxtFld.setFont(new Font("Caladea Bold", Font.BOLD, (int) (frame.getWidth()*0.016)));
       			
       			relativeXSize = (int) (frame.getHeight()*0.06);
       			imagePnl.setBorder(BorderFactory.createEmptyBorder(relativeXSize, relativeXSize, relativeXSize, relativeXSize/2)); 
       			sidePnl.setBorder(BorderFactory.createEmptyBorder(relativeXSize*2, relativeXSize/2, 0, relativeXSize)); 
       			
       			relativeXSize = (int) (frame.getHeight()*0.005);
       			usernamePnl.setBorder(BorderFactory.createEmptyBorder(relativeXSize, relativeXSize, relativeXSize, relativeXSize)); 
       			usernameLbl.setBorder(BorderFactory.createEmptyBorder(relativeXSize, relativeXSize, 0, relativeXSize)); 
       			usersIconLbl.setBorder(BorderFactory.createEmptyBorder(relativeXSize, relativeXSize, relativeXSize, relativeXSize)); 
       			usernameTxtFld.setBorder(BorderFactory.createEmptyBorder(relativeXSize, relativeXSize*2, relativeXSize, relativeXSize)); 
       			
       			passwordPnl.setBorder(BorderFactory.createEmptyBorder(relativeXSize, relativeXSize, relativeXSize, relativeXSize));
       			passwordLbl.setBorder(BorderFactory.createEmptyBorder(relativeXSize, relativeXSize, 0, relativeXSize));
       			keyIconLbl.setBorder(BorderFactory.createEmptyBorder(relativeXSize, relativeXSize, relativeXSize, relativeXSize));
       			jPssWrdFldPnl.setBorder(BorderFactory.createEmptyBorder(relativeXSize, relativeXSize*2, relativeXSize, relativeXSize));
       			seeBttn.setBorder(BorderFactory.createEmptyBorder(0, 0, relativeXSize, relativeXSize));
       			
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
