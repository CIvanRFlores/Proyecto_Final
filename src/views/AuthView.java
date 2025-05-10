package views;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import javax.swing.*;

import application.RoundButton;
import application.RoundPanel;

public class AuthView {

	private boolean passwordVisible = false;
	private JFrame frame;
	private Image image;
	private ImageIcon imageIcon;
	private int relativeXSize;
	private int relativeYSize;
	
	public AuthView(String title, int frameWidth, int frameHeight) {
		frame = new JFrame(); //crear JFrame	
		imageIcon = new ImageIcon("src/images/elManglarLogo.png");
		frame.setIconImage(imageIcon.getImage());
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //cerrar ventana si se presiona la X
		frame.setSize(frameWidth, frameHeight); //tamaño de la ventana
		frame.setLocationRelativeTo(null); //colocar la ventana en el centro de la pantalla
		frame.setResizable(true); //redimensionar la ventana
		frame.setMinimumSize(new Dimension(350, 350));
		frame.setTitle(title); //colocar título a la ventana
	}
	
	public void login() {
		
		usingCustomFonts();
		
		/**PANEL DE FONDO**/
		JPanel backgroundPnl = new JPanel();
		backgroundPnl.setBackground(Color.white);
		backgroundPnl.setLayout(new BorderLayout());
		frame.setContentPane(backgroundPnl); //colocar como contenido del frame al panel de fondo
		
		
		
		/**IMAGEN LATERAL IZQUIERDA DE DECORACIÓN**/
		JPanel imagePnl = new JPanel();
		imagePnl.setLayout(new BorderLayout());
		imagePnl.setOpaque(false); //tiene fondo o no
		backgroundPnl.add(imagePnl, BorderLayout.WEST );
		
		//icono de fondo 
		image = new ImageIcon("src/images/mangroveImage.png").getImage().getScaledInstance(500, 1000, Image.SCALE_SMOOTH);
		imageIcon = new ImageIcon(image);
		JLabel backgroundDeco = new JLabel(imageIcon);
		imagePnl.add(backgroundDeco, BorderLayout.CENTER);
		
		
		
		/**BARRA LATERAL DERECHA DE LOGIN**/
		JPanel sidePnl = new JPanel();
		sidePnl.setBackground(Color.white);
		sidePnl.setBorder(BorderFactory.createEmptyBorder(60, 60, 60, 60)); 
		sidePnl.setLayout(new GridLayout(6, 1, 0, 25));
		backgroundPnl.add(sidePnl, BorderLayout.CENTER );
		
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


		
		/**PANELES CON JTEXTFIELD DE NOMBRE DE USUARIO**/
		//panel que funciona como borde decorativo
		RoundPanel usernameBorderPnl = new RoundPanel(); 
		usernameBorderPnl.setBackground(Color.decode("#8A5627"));
		usernameBorderPnl.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5)); //borde invisible para centrar elementos que el panel contenga 
		usernameBorderPnl.setLayout(new BorderLayout());
		usernameBorderPnl.setRoundTopLeft(30);
		usernameBorderPnl.setRoundTopRight(30);
		usernameBorderPnl.setRoundBottomLeft(30);
		usernameBorderPnl.setRoundBottomRight(30);
		sidePnl.add(usernameBorderPnl);
		
		//panel que contiene a JTextField
		RoundPanel usernamePnl = new RoundPanel(); 
		usernamePnl.setBackground(Color.white);
		usernamePnl.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5)); //borde invisible para centrar elementos que el panel contenga 
		usernamePnl.setLayout(new BorderLayout(15, 0));
		usernamePnl.setRoundTopLeft(30);
		usernamePnl.setRoundTopRight(30);
		usernamePnl.setRoundBottomLeft(30);
		usernamePnl.setRoundBottomRight(30);
		usernameBorderPnl.add(usernamePnl, BorderLayout.CENTER);
		
		//texto que alude al valor del campo de texto
		JLabel usernameLbl = new JLabel("Nombre de usuario");
		usernameLbl.setFont(new Font("Caladea Bold", Font.BOLD, 18));
		usernameLbl.setForeground(Color.decode("#999999")); //color de letra
		usernameLbl.setHorizontalAlignment(JLabel.CENTER); //centrar etiqueta
		usernameLbl.setHorizontalAlignment(SwingConstants.LEFT); //centrar texto de la etiqueta
		usernamePnl.add(usernameLbl, BorderLayout.NORTH);
		
		//icono de usuario
		image = new ImageIcon("src/images/users.png").getImage().getScaledInstance(22, 22, Image.SCALE_SMOOTH);
		imageIcon = new ImageIcon(image);
		JLabel usersIconLbl = new JLabel(imageIcon);
		usernamePnl.add(usersIconLbl, BorderLayout.WEST);
		
		//textfield
		JTextField usernameTxtFld = new JTextField();
		usernameTxtFld.setBorder(null);
		usernameTxtFld.setFont(new Font("Caladea Bold", Font.BOLD, 14)); 
		usernameTxtFld.setForeground(Color.decode("#999999")); //color de letra
		usernameTxtFld.setOpaque(false); //tiene fondo o no
		usernamePnl.add(usernameTxtFld, BorderLayout.CENTER);
		
		
		
		/**PANELES CON JTEXTFIELD DE CONTRASEÑA DE USUARIO**/
		//panel que funciona como borde decorativo
		RoundPanel passwordBorderPnl = new RoundPanel(); 
		passwordBorderPnl.setBackground(Color.decode("#8A5627"));
		passwordBorderPnl.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5)); //borde invisible para centrar elementos que el panel contenga 
		passwordBorderPnl.setLayout(new BorderLayout());
		passwordBorderPnl.setRoundTopLeft(30);
		passwordBorderPnl.setRoundTopRight(30);
		passwordBorderPnl.setRoundBottomLeft(30);
		passwordBorderPnl.setRoundBottomRight(30);
		sidePnl.add(passwordBorderPnl);
		
		//panel que a los elementos de contraseña
		RoundPanel passwordPnl = new RoundPanel(); 
		passwordPnl.setBackground(Color.white);
		passwordPnl.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5)); //borde invisible para centrar elementos que el panel contenga 
		passwordPnl.setLayout(new BorderLayout(15, 0));
		passwordPnl.setRoundTopLeft(30);
		passwordPnl.setRoundTopRight(30);
		passwordPnl.setRoundBottomLeft(30);
		passwordPnl.setRoundBottomRight(30);
		passwordBorderPnl.add(passwordPnl, BorderLayout.CENTER);
		
		//texto que alude al valor del campo de texto
		JLabel passwordLbl = new JLabel("Contraseña");
		passwordLbl.setFont(new Font("Caladea Bold", Font.BOLD, 18));
		passwordLbl.setForeground(Color.decode("#999999")); //color de letra
		passwordLbl.setHorizontalAlignment(JLabel.CENTER); //centrar etiqueta
		passwordLbl.setHorizontalAlignment(SwingConstants.LEFT); //centrar texto de la etiqueta
		passwordPnl.add(passwordLbl, BorderLayout.NORTH);
		
		//icono de llave 
		image = new ImageIcon("src/images/key.png").getImage().getScaledInstance(22, 22, Image.SCALE_SMOOTH);
		imageIcon = new ImageIcon(image);
		JLabel keyIconLbl = new JLabel(imageIcon);
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
		RoundButton loginBttn = new RoundButton();
		loginBttn.setBackground(Color.decode("#306572"));
		loginBttn.setFont(new Font("Caladea Bold", Font.BOLD, 30));
		loginBttn.setForeground(Color.white);
		loginBttn.setRoundTopLeft(30);
		loginBttn.setRoundTopRight(30);
		loginBttn.setRoundBottomLeft(30);
		loginBttn.setRoundBottomRight(30);
		loginBttn.setText("Iniciar sesión");
		sidePnl.add(loginBttn);
		
		loginBttn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				boolean flag1 = false, flag2 = false;
				
				//campo de usuario 
				String username = usernameTxtFld.getText();
				if(!usernameTxtFld.getText().equals("")) { //si se encuentra vacío
					flag1 = true;
				}
				
				//campo de contraseña 
				String passTxt = new String(passwordFld.getPassword());
				if(!passTxt.equals("")) { //si se encuentra vacía 
					flag2 = true;
				}
				
				//validar si los dos campos han sido completados
				if(flag1 && flag2) {
					//cuando ambos campos coinciden con los datos de la cuenta del usuario
					if(username.equals("admin") && passTxt.equals("1234") ) {
						image = new ImageIcon("src/images/checkCircle.png").getImage().getScaledInstance(45, 45, Image.SCALE_SMOOTH); //imagen
	       				imageIcon = new ImageIcon(image);
						String message = "Sesión iniciada correctamente.";
						JOptionPane.showMessageDialog(null, message, "Datos correctos", JOptionPane.INFORMATION_MESSAGE, imageIcon); //ventana emergente
					}
					else {
						//cuando alguno de los dos campos no es correcto
						String message = "Datos erróneos. Por favor, inténtelo otra vez.";
						JOptionPane.showMessageDialog(null, message, "Datos incorrectos", JOptionPane.ERROR_MESSAGE); //ventana emergente
					}
				}else { 
					//uno o los dos componentes se encuentran vacíos
					String message = "Uno o más campos se encuentran vacíos.";
					JOptionPane.showMessageDialog(null, message, "Campos vacíos", JOptionPane.WARNING_MESSAGE); //ventana emergente
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
       			sidePnl.setBorder(BorderFactory.createEmptyBorder(relativeXSize, relativeXSize, relativeXSize, relativeXSize)); 
       			
       			relativeXSize = (int) (frame.getHeight()*0.005);
       			usernameBorderPnl.setBorder(BorderFactory.createEmptyBorder(relativeXSize, relativeXSize, relativeXSize, relativeXSize)); 
       			passwordBorderPnl.setBorder(BorderFactory.createEmptyBorder(relativeXSize, relativeXSize, relativeXSize, relativeXSize)); 
       			usernamePnl.setBorder(BorderFactory.createEmptyBorder(relativeXSize, relativeXSize, relativeXSize, relativeXSize)); 
       			passwordPnl.setBorder(BorderFactory.createEmptyBorder(relativeXSize, relativeXSize, relativeXSize, relativeXSize));
 
       			frame.repaint();
            }
        });
		
		frame.setVisible(true);
	}
	
	public void usingCustomFonts() {
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
	
}
