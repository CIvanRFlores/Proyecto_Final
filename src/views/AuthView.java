package views;

import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
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
		frame = new JFrame(); //crear JFrame	
		imageIcon = new ImageIcon("src/images/elManglarLogo.png"); //icono de la ventana
		frame.setBackground(Color.pink);
		frame.setIconImage(imageIcon.getImage());
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //cerrar ventana si se presiona la X
		frame.setSize(frameWidth, frameHeight); //tamaño de la ventana
		frame.setLocationRelativeTo(null); //colocar la ventana en el centro de la pantalla
		frame.setResizable(true); //redimensionar la ventana
		frame.setMinimumSize(new Dimension(350, 350));
		frame.setTitle(title); //colocar título a la ventana
	}
	
	public void login() {
		
		//importar el método que permite utilizar fuentes personalizadas
		boldCustomFont();
		regularCustomFont();
	
		/**IMAGEN LATERAL IZQUIERDA DE DECORACIÓN**/
		JPanel imagePnl = new JPanel();
		imagePnl.setBackground(Color.white);
		imagePnl.setBorder(BorderFactory.createEmptyBorder(60, 60, 60, 30)); 
		imagePnl.setLayout(new BorderLayout());
		frame.add(imagePnl, BorderLayout.WEST);
		
		//icono de fondo 
		image = new ImageIcon("src/images/mangroveImage.png").getImage().getScaledInstance(500, 1000, Image.SCALE_SMOOTH);
		imageIcon = new ImageIcon(image);
		JLabel backgroundDeco = new JLabel(imageIcon);
		imagePnl.add(backgroundDeco, BorderLayout.CENTER);
		
		
		/**BARRA LATERAL DERECHA DE LOGIN**/
		JPanel sidePnl = new JPanel();
		sidePnl.setBackground(Color.white);
		sidePnl.setBorder(BorderFactory.createEmptyBorder(60, 30, 60, 60)); 
		sidePnl.setLayout(new GridLayout(6, 1, 0, 25));
		frame.add(sidePnl, BorderLayout.CENTER );
		
		JLabel welcomeLbl = new JLabel("Bienvenido");
		welcomeLbl.setFont(new Font("Caladea Bold", Font.BOLD, 50));
		welcomeLbl.setForeground(Color.decode("#244E23")); //color de letra
		welcomeLbl.setHorizontalAlignment(JLabel.CENTER); //centrar etiqueta
		welcomeLbl.setHorizontalAlignment(SwingConstants.CENTER); //centrar texto de la etiqueta
		sidePnl.add(welcomeLbl);
		
		
		/**IMAGEN DEL LOGO CON TEXTO**/
		//imagen de logo 
		image = new ImageIcon("src/images/elManglarLogoText.png").getImage().getScaledInstance(72, 86, Image.SCALE_SMOOTH);
		imageIcon = new ImageIcon(image);
		JLabel logoTextLbl = new JLabel(imageIcon);
		sidePnl.add(logoTextLbl);


		/**PANEL CON JTEXTFIELD DE NOMBRE DE USUARIO**/
		RoundPanel usernamePnl = new RoundPanel(30);  
		usernamePnl.setBackground(Color.white);
		usernamePnl.setForeground(Color.decode("#8A5627")); //color del borde
		usernamePnl.setLayout(new BorderLayout(15, 0));
		sidePnl.add(usernamePnl);
		
		//texto que alude al valor del campo de texto
		JLabel usernameLbl = new JLabel("Nombre de usuario");
		usernameLbl.setBorder(BorderFactory.createEmptyBorder(0, 15, 0, 0)); //borde invisible para centrar elementos
		usernameLbl.setFont(new Font("Caladea Bold", Font.BOLD, 18));
		usernameLbl.setForeground(Color.decode("#999999")); //color de letra
		usernameLbl.setHorizontalAlignment(JLabel.CENTER); //centrar etiqueta
		usernameLbl.setHorizontalAlignment(SwingConstants.LEFT); //centrar texto de la etiqueta
		usernamePnl.add(usernameLbl, BorderLayout.NORTH);
		
		//icono de usuario
		image = new ImageIcon("src/images/users.png").getImage().getScaledInstance(22, 22, Image.SCALE_SMOOTH);
		imageIcon = new ImageIcon(image);
		JLabel usersIconLbl = new JLabel(imageIcon);
		usersIconLbl.setBorder(BorderFactory.createEmptyBorder(0, 15, 0, 0)); //borde invisible para centrar elementos
		usernamePnl.add(usersIconLbl, BorderLayout.WEST);
		
		//textfield
		JTextField usernameTxtFld = new JTextField();
		usernameTxtFld.setBorder(null);
		usernameTxtFld.setFont(new Font("Caladea Bold", Font.BOLD, 14)); 
		usernameTxtFld.setForeground(Color.decode("#999999")); //color de letra
		usernameTxtFld.setOpaque(false); //tiene fondo o no
		usernamePnl.add(usernameTxtFld, BorderLayout.CENTER);
		
		
		/**PANEL CON JTEXTFIELD DE CONTRASEÑA DE USUARIO**/
		RoundPanel passwordPnl = new RoundPanel(30); 
		passwordPnl.setBackground(Color.white); 
		passwordPnl.setForeground(Color.decode("#8A5627")); //color del borde
		passwordPnl.setLayout(new BorderLayout(15, 0));
		sidePnl.add(passwordPnl);
		
		//texto que alude al valor del campo de texto
		JLabel passwordLbl = new JLabel("Contraseña");
		passwordLbl.setBorder(BorderFactory.createEmptyBorder(0, 15, 0, 0)); //borde invisible para centrar elementos
		passwordLbl.setFont(new Font("Caladea Bold", Font.BOLD, 18));
		passwordLbl.setForeground(Color.decode("#999999")); //color de letra
		passwordLbl.setHorizontalAlignment(JLabel.CENTER); //centrar etiqueta
		passwordLbl.setHorizontalAlignment(SwingConstants.LEFT); //centrar texto de la etiqueta
		passwordPnl.add(passwordLbl, BorderLayout.NORTH);
		
		//icono de llave 
		image = new ImageIcon("src/images/key.png").getImage().getScaledInstance(22, 22, Image.SCALE_SMOOTH);
		imageIcon = new ImageIcon(image);
		JLabel keyIconLbl = new JLabel(imageIcon);
		keyIconLbl.setBorder(BorderFactory.createEmptyBorder(0, 15, 0, 0)); //borde invisible para centrar elementos
		passwordPnl.add(keyIconLbl, BorderLayout.WEST);
	
		//panel con el campo para escribir la contraseña y el botón para mostrarla/ocultarla
		JPanel jPssWrdFldPnl = new JPanel();
		jPssWrdFldPnl.setOpaque(false);
		jPssWrdFldPnl.setLayout(new BorderLayout());
		passwordPnl.add(jPssWrdFldPnl, BorderLayout.CENTER);
		
		//textfield
		JPasswordField passwordFld = new JPasswordField();
		passwordFld.setEchoChar('*'); //caracteres
		passwordFld.setBorder(null); //quitar borde por defecto
		passwordFld.setForeground(Color.decode("#999999")); //color de letra
		passwordFld.setOpaque(false); //tiene fondo o no
		passwordFld.setFont(new Font("Caladea Bold", Font.BOLD, 14)); 
		jPssWrdFldPnl.add(passwordFld, BorderLayout.CENTER);
		
		//icono de ojo 
		image = new ImageIcon("src/images/eyeOff.png").getImage().getScaledInstance(22, 22, Image.SCALE_SMOOTH);
		imageIcon = new ImageIcon(image);
		JButton seeBttn = new JButton();
		seeBttn.setIcon(imageIcon);
		seeBttn.setFocusPainted(false); //hace invisible el recuadro blanco al presionar el botón
		seeBttn.setBorderPainted(false); //hace invisible el borde por defecto de los botones   
		seeBttn.setContentAreaFilled(false); //hace invisible la animacion al presionar el botón
		jPssWrdFldPnl.add(seeBttn, BorderLayout.EAST);
		
		//botón para mostrar u ocultar la contraseña
		seeBttn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				/**cambiar tamaño de iconos a porcentajes del ancho y alto de la ventana**/
				relativeXSize = (int) (frame.getWidth()*0.022);
       			relativeYSize = relativeXSize;
				//mostrar contraseña
				if(passwordVisible) {
					passwordFld.setEchoChar((char)0); //representar los caracteres de la contraseña con letras 'ABCdario'	
					passwordFld.setFont(new Font("Caladea Bold", Font.BOLD, (int) (frame.getWidth()*0.012))); //fuente, tipo y tamaño
					image = new ImageIcon("src/images/eye.png").getImage().getScaledInstance(relativeXSize, relativeYSize, Image.SCALE_SMOOTH);
					imageIcon = new ImageIcon(image);
					seeBttn.setIcon(imageIcon);
					passwordVisible = false;
				}else {
					//ocultar contraseña
					passwordFld.setEchoChar('*'); //representar los caracteres de la contraseña con asteriscos '*'				
					passwordFld.setFont(new Font("Caladea Bold", Font.BOLD, (int) (frame.getWidth()*0.014))); //fuente, tipo y tamaño
					image = new ImageIcon("src/images/eyeOff.png").getImage().getScaledInstance(relativeXSize, relativeYSize, Image.SCALE_SMOOTH);
					imageIcon = new ImageIcon(image);
					seeBttn.setIcon(imageIcon);
					passwordVisible = true;
				}
				
			}
			
		});

		
		/**CHECKBOX DE RECORDAR USUARIO**/
		JCheckBox rememberUserChckBx = new JCheckBox("Recordar usuario");
		rememberUserChckBx.setFont(new Font("Caladea Bold", Font.BOLD, 16));
		rememberUserChckBx.setForeground(Color.decode("#244E23"));
		rememberUserChckBx.setOpaque(false); //tiene fondo o no
		
		sidePnl.add(rememberUserChckBx);
		
		
		/**BOTÓN DE INICIAR SESIÓN**/
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
				
				//campo de usuario 
				String username = usernameTxtFld.getText();
				if(!usernameTxtFld.getText().equals("")) { //si no se encuentra vacío
					flag1 = true;
				}
				
				//campo de contraseña 
				String passTxt = new String(passwordFld.getPassword());
				if(!passTxt.equals("")) { //si no se encuentra vacía 
					flag2 = true;
				}
				
				String message;
				//validar si los dos campos han sido completados
				if(flag1 && flag2) {
					AuthModel am = new AuthModel();
					//cuando ambos campos coinciden con los datos de la cuenta del usuario
					if(am.login(username, passTxt)) {	//el metodo recibe parametros de usuario y contraseña
						image = new ImageIcon("src/images/checkCircle.png").getImage().getScaledInstance(45, 45, Image.SCALE_SMOOTH); //imagen
	       				imageIcon = new ImageIcon(image);
	       				
						message = "Sesión iniciada correctamente.";
						JOptionPane.showMessageDialog(null, message, "Datos correctos", JOptionPane.INFORMATION_MESSAGE, imageIcon); //ventana emergente
						
						frame.dispose(); //destruir ventana actual y llevar al usuario a la ventana de platillos
						dc = new DishController("Platillos", frame.getWidth(), frame.getHeight()); //crear controlador de platillos y asignar parámetros a la ventana
						dc.dishes(); //llamar al método que crea y muestra la ventana de platillos
					}
					else { //cuando alguno de los dos campos no es correcto
						image = new ImageIcon("src/images/errorCircle.png").getImage().getScaledInstance(45, 45, Image.SCALE_SMOOTH); //imagen
	       				imageIcon = new ImageIcon(image);
	       				
						message = "Datos erróneos. Por favor, inténtelo otra vez.";
						JOptionPane.showMessageDialog(null, message, "Error al iniciar sesión", JOptionPane.ERROR_MESSAGE, imageIcon); //ventana emergente
					}
					
				}else { //uno o los dos componentes se encuentran vacíos
					
					image = new ImageIcon("src/images/warning.png").getImage().getScaledInstance(45, 45, Image.SCALE_SMOOTH); //imagen
       				imageIcon = new ImageIcon(image);
					
					message = "Uno o más campos se encuentran vacíos.";
					JOptionPane.showMessageDialog(null, message, "Campos vacíos", JOptionPane.WARNING_MESSAGE, imageIcon); //ventana emergente
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
            	/**cambiar tamaño de imagenes a porcentajes del ancho y alto de la ventana**/
            	relativeXSize = frame.getWidth()/2;
            	relativeYSize = frame.getHeight();
               	image = new ImageIcon("src/images/mangroveImage.png").getImage().getScaledInstance(relativeXSize, relativeYSize, Image.SCALE_SMOOTH);
       			imageIcon = new ImageIcon(image);
       			backgroundDeco.setIcon(imageIcon);  
       			
       			relativeXSize = (int) (frame.getHeight()*0.086)-15;
       			relativeYSize = (int) (frame.getHeight()*0.086);
       			image = new ImageIcon("src/images/elManglarLogoText.png").getImage().getScaledInstance(relativeXSize, relativeYSize, Image.SCALE_SMOOTH);
       			imageIcon = new ImageIcon(image);
       			logoTextLbl.setIcon(imageIcon); 
       			
       			/**cambiar tamaño de iconos a porcentajes del ancho y alto de la ventana**/
       			relativeXSize = (int) (frame.getWidth()*0.022);
       			relativeYSize = relativeXSize;
       			image = new ImageIcon("src/images/users.png").getImage().getScaledInstance(relativeXSize, relativeYSize, Image.SCALE_SMOOTH);
       			imageIcon = new ImageIcon(image);
       			usersIconLbl.setIcon(imageIcon);
       			
       			image = new ImageIcon("src/images/key.png").getImage().getScaledInstance(relativeXSize, relativeYSize, Image.SCALE_SMOOTH);
       			imageIcon = new ImageIcon(image);
       			keyIconLbl.setIcon(imageIcon);
       			
       			/**según el estado de visibilidad de contraseña colocar el icono correspondiente y tamaño de letra**/
       			if(passwordVisible) { 
       				image = new ImageIcon("src/images/eye.png").getImage().getScaledInstance(relativeXSize, relativeYSize, Image.SCALE_SMOOTH);
       				imageIcon = new ImageIcon(image);
       				seeBttn.setIcon(imageIcon);
       				passwordFld.setFont(new Font("Caladea Bold", Font.BOLD, (int) (frame.getWidth()*0.012)));;
       			}else {
       				image = new ImageIcon("src/images/eyeOff.png").getImage().getScaledInstance(relativeXSize, relativeYSize, Image.SCALE_SMOOTH);
       				imageIcon = new ImageIcon(image);
       				seeBttn.setIcon(imageIcon);
       				passwordFld.setFont(new Font("Caladea Bold", Font.BOLD, (int) (frame.getWidth()*0.014)));
       			}
       			
       			/**cambiar tamaño de letras a porcentajes del ancho y alto de la ventana**/
       			welcomeLbl.setFont(new Font("Caladea Bold", Font.BOLD, (int) (frame.getWidth()*0.04)));
       			loginBttn.setFont(new Font("Caladea Bold", Font.BOLD, (int) (frame.getWidth()*0.03)));
       			passwordLbl.setFont(new Font("Caladea Bold", Font.BOLD, (int) (frame.getWidth()*0.018)));
       			usernameLbl.setFont(new Font("Caladea Bold", Font.BOLD, (int) (frame.getWidth()*0.018)));
       			usernameTxtFld.setFont(new Font("Caladea Bold", Font.BOLD, (int) (frame.getWidth()*0.014)));;
       			rememberUserChckBx.setFont(new Font("Caladea Bold", Font.BOLD, (int) (frame.getWidth()*0.016)));
       			
       			/**cambiar tamaño de bordes a porcentajes del ancho y alto de la ventana**/
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
	
	public void boldCustomFont() {
	    GraphicsEnvironment GE = GraphicsEnvironment.getLocalGraphicsEnvironment();
	    List<String> AVAILABLE_FONT_FAMILY_NAMES = Arrays.asList(GE.getAvailableFontFamilyNames());
	    
	    try {
	        List<File> LIST = Arrays.asList(
	          new File("src/files/Caladea-Bold.ttf")
	        );
	        for (File LIST_ITEM : LIST) {
	            if (LIST_ITEM.exists()) {
	                Font FONT = Font.createFont(Font.TRUETYPE_FONT, LIST_ITEM);
	                if (!AVAILABLE_FONT_FAMILY_NAMES.contains(FONT.getFontName())) {
	                    GE.registerFont(FONT);
	                }
	            }
	        }
	    }catch(FontFormatException | IOException exception) {
	        JOptionPane.showMessageDialog(null, exception.getMessage());
	    }
	}
	
	public void regularCustomFont() {
	    GraphicsEnvironment GE = GraphicsEnvironment.getLocalGraphicsEnvironment();
	    List<String> AVAILABLE_FONT_FAMILY_NAMES = Arrays.asList(GE.getAvailableFontFamilyNames());
	    
	    try {
	        List<File> LIST = Arrays.asList(
	          new File("src/files/Caladea-Regular.ttf")
	        );
	        for (File LIST_ITEM : LIST) {
	            if (LIST_ITEM.exists()) {
	                Font FONT = Font.createFont(Font.TRUETYPE_FONT, LIST_ITEM);
	                if (!AVAILABLE_FONT_FAMILY_NAMES.contains(FONT.getFontName())) {
	                    GE.registerFont(FONT);
	                }
	            }
	        }
	    }catch(FontFormatException | IOException exception) {
	        JOptionPane.showMessageDialog(null, exception.getMessage());
	    }
	}
}
