package customClasses;

import java.awt.*;
import java.awt.Dialog.ModalityType;
import java.awt.event.*;

import javax.swing.*;

public class OptionPaneButton {
	
	int option;
	String dialogTitle;
	String optionPaneMessage;
	RoundButton okBttn;
	String buttonText = "";
	int type = 0;
	ImageIcon imageIcon;
	Image image;
	
	public OptionPaneButton(String dialogTitle, String optionPaneMessage, String buttonText) {
		this.dialogTitle = dialogTitle;
		this.optionPaneMessage = optionPaneMessage;
		this.buttonText = buttonText;
	}
	
	public OptionPaneButton(String dialogTitle, String optionPaneMessage) {
		this.dialogTitle = dialogTitle;
		this.optionPaneMessage = optionPaneMessage;
		
		okBttn = new RoundButton(30);
		okBttn.setBackground(Color.decode("#999999"));
		okBttn.setFont(new Font("Caladea Bold", Font.BOLD, 12));
		okBttn.setForeground(Color.white);
		okBttn.setText("Ok");
		
		okBttn.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent evt) {
				okBttn.setBackground(Color.decode("#B3B1B1"));
		    }

		    public void mouseExited(MouseEvent evt) {
		    	okBttn.setBackground(Color.decode("#999999"));
		    }
		});
	}
	
	public int destructiveOptionPane() {
		image = new ImageIcon(OptionPaneButton.class.getResource("/images/errorCircle.png")).getImage().getScaledInstance(45, 45, Image.SCALE_SMOOTH);
		imageIcon = new ImageIcon(image);
		
		JOptionPane optionPane = new JOptionPane(optionPaneMessage, JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, imageIcon);
		JDialog dialog = optionPane.createDialog(null, dialogTitle);

		dialog.requestFocus();
		dialog.setFocusable(true);
		dialog.addKeyListener(new KeyAdapter() {
		    @Override
		    public void keyTyped(KeyEvent e) {
		        char c = e.getKeyChar();
		        if (c=='\n'){
		        	dialog.dispose();
		    }
		}});
		
		
		RoundButton cancelBttn = new RoundButton(30);
		cancelBttn.setBackground(Color.decode("#999999"));
		cancelBttn.setFont(new Font("Caladea Bold", Font.BOLD, 12));
		cancelBttn.setForeground(Color.white);
		cancelBttn.setText("Cancelar");
		
		cancelBttn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				option = 0;
				dialog.dispose();
			}
		});
		
		cancelBttn.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent evt) {
				cancelBttn.setBackground(Color.decode("#B3B1B1"));
		    }

		    public void mouseExited(MouseEvent evt) {
		    	cancelBttn.setBackground(Color.decode("#999999"));
		    }
		});
		
		
		RoundButton deleteBttn = new RoundButton(30);
		deleteBttn.setBackground(Color.decode("#EF2D2D"));
		deleteBttn.setFont(new Font("Caladea Bold", Font.BOLD, 12));
		deleteBttn.setForeground(Color.white);
		deleteBttn.setText(buttonText);
		
		deleteBttn.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent evt) {
				option = 1;
				dialog.dispose();
			}
		});
		
		deleteBttn.addMouseListener(new MouseAdapter() { 
			public void mouseEntered(MouseEvent evt) {
				deleteBttn.setBackground(Color.decode("#ED5C5C"));
		    }

		    public void mouseExited(MouseEvent evt) {
		    	deleteBttn.setBackground(Color.decode("#EF2D2D"));
		    }
		});
		
		
		Object[] options = {cancelBttn, deleteBttn};
		optionPane.setOptions(options);

        dialog.setVisible(true);
		
		if(option==1) {
			return 1;
		}else {
			return 0;
		}
	}
	
	public void warningOptionPane() {
		image = new ImageIcon(OptionPaneButton.class.getResource("/images/warning.png")).getImage().getScaledInstance(45, 45, Image.SCALE_SMOOTH);
		imageIcon = new ImageIcon(image);
		
		JOptionPane optionPane = new JOptionPane(optionPaneMessage, JOptionPane.INFORMATION_MESSAGE, JOptionPane.PLAIN_MESSAGE, imageIcon);
		JDialog dialog = optionPane.createDialog(null, dialogTitle);

		dialog.requestFocus();
		dialog.setFocusable(true);
		dialog.addKeyListener(new KeyAdapter() {
		    @Override
		    public void keyTyped(KeyEvent e) {
		        char c = e.getKeyChar();
		        if (c=='\n'){
		        	dialog.dispose();
		    }
		}});
		
		
		okBttn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dialog.dispose();
			}
		});
		
		
		Object[] options = {okBttn};
		optionPane.setOptions(options);

        dialog.setVisible(true);
	}
	
	public int dishOptionPane() {
		image = new ImageIcon(OptionPaneButton.class.getResource("/images/questionMark.png")).getImage().getScaledInstance(25, 45, Image.SCALE_SMOOTH); 
		imageIcon = new ImageIcon(image);
		
		JOptionPane optionPane = new JOptionPane(optionPaneMessage, JOptionPane.INFORMATION_MESSAGE, JOptionPane.PLAIN_MESSAGE, imageIcon);
		JDialog dialog = optionPane.createDialog(null, dialogTitle);

		dialog.requestFocus();
		dialog.setFocusable(true);
		dialog.addKeyListener(new KeyAdapter() {
		    @Override
		    public void keyTyped(KeyEvent e) {
		        char c = e.getKeyChar();
		        if (c=='\n'){
		        	dialog.dispose();
		    }
		}});
		
		
		RoundButton dishBttn = new RoundButton(30);
		dishBttn.setBackground(Color.decode("#244E23"));
		dishBttn.setFont(new Font("Caladea Bold", Font.BOLD, 12));
		dishBttn.setForeground(Color.white);
		dishBttn.setText("Platillo");
		
		dishBttn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				type = 1;
				dialog.dispose();
			}
		});
		
		dishBttn.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent evt) {
				dishBttn.setBackground(Color.decode("#3C7E3A"));
		    }

		    public void mouseExited(MouseEvent evt) {
		    	dishBttn.setBackground(Color.decode("#244E23"));
		    }
		});
		
		
		RoundButton drinkBttn = new RoundButton(30);
		drinkBttn.setBackground(Color.decode("#306572"));
		drinkBttn.setFont(new Font("Caladea Bold", Font.BOLD, 12));
		drinkBttn.setForeground(Color.white);
		drinkBttn.setText(" Bebida ");
		
		drinkBttn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				type = 2;
				dialog.dispose();
			}
		});
		
		drinkBttn.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent evt) {
				drinkBttn.setBackground(Color.decode("#264F59"));
		    }

		    public void mouseExited(MouseEvent evt) {
		    	drinkBttn.setBackground(Color.decode("#306572"));
		    }
		});
		
		
		Object[] options = {dishBttn, drinkBttn};
		optionPane.setOptions(options);

        dialog.setVisible(true);
        
        return type;
	}
	
	public int logoutOptionPane() {
		image = new ImageIcon(OptionPaneButton.class.getResource("/images/questionMark.png")).getImage().getScaledInstance(25, 45, Image.SCALE_SMOOTH); 
		imageIcon = new ImageIcon(image);
		
		JOptionPane optionPane = new JOptionPane(optionPaneMessage, JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, imageIcon);
		JDialog dialog = optionPane.createDialog(null, dialogTitle);

		dialog.requestFocus();
		dialog.setFocusable(true);
		dialog.addKeyListener(new KeyAdapter() {
		    @Override
		    public void keyTyped(KeyEvent e) {
		        char c = e.getKeyChar();
		        if (c=='\n'){
		        	dialog.dispose();
		    }
		}});
		
		
		RoundButton cancelBttn = new RoundButton(30);
		cancelBttn.setBackground(Color.decode("#999999"));
		cancelBttn.setFont(new Font("Caladea Bold", Font.BOLD, 12));
		cancelBttn.setForeground(Color.white);
		cancelBttn.setText("Cancelar");
		
		cancelBttn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				option = 0;
				dialog.dispose();
			}
		});
		
		cancelBttn.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent evt) {
				cancelBttn.setBackground(Color.decode("#B3B1B1"));
		    }

		    public void mouseExited(MouseEvent evt) {
		    	cancelBttn.setBackground(Color.decode("#999999"));
		    }
		});
		
		
		RoundButton logoutBttn = new RoundButton(30);
		logoutBttn.setBackground(Color.decode("#EF2D2D"));
		logoutBttn.setFont(new Font("Caladea Bold", Font.BOLD, 12));
		logoutBttn.setForeground(Color.white);
		logoutBttn.setText(buttonText);
		
		logoutBttn.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent evt) {
				option = 1;
				dialog.dispose();
			}
		});
		
		logoutBttn.addMouseListener(new MouseAdapter() { 
			public void mouseEntered(MouseEvent evt) {
				logoutBttn.setBackground(Color.decode("#ED5C5C"));
		    }

		    public void mouseExited(MouseEvent evt) {
		    	logoutBttn.setBackground(Color.decode("#EF2D2D"));
		    }
		});
		
		
		Object[] options = {cancelBttn, logoutBttn};
		optionPane.setOptions(options);

        dialog.setVisible(true);
		
		if(option==1) {
			return 1;
		}else {
			return 0;
		}
	}
	
	public int downloadOptionPane() {
		image = new ImageIcon(OptionPaneButton.class.getResource("/images/checkCircle.png")).getImage().getScaledInstance(45, 45, Image.SCALE_SMOOTH); 
		imageIcon = new ImageIcon(image);
		
		JOptionPane optionPane = new JOptionPane(optionPaneMessage, JOptionPane.INFORMATION_MESSAGE, JOptionPane.PLAIN_MESSAGE, imageIcon);
		JDialog dialog = optionPane.createDialog(null, dialogTitle);

		dialog.requestFocus();
		dialog.setFocusable(true);
		dialog.addKeyListener(new KeyAdapter() {
		    @Override
		    public void keyTyped(KeyEvent e) {
		        char c = e.getKeyChar();
		        if (c=='\n'){
		        	dialog.dispose();
		    }
		}});
		
		
		RoundButton noBttn = new RoundButton(30);
		noBttn.setBackground(Color.decode("#999999"));
		noBttn.setFont(new Font("Caladea Bold", Font.BOLD, 12));
		noBttn.setForeground(Color.white);
		noBttn.setText("No");
		
		noBttn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				option = 0;
				dialog.dispose();
			}
		});
		
		noBttn.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent evt) {
				noBttn.setBackground(Color.decode("#B3B1B1"));
		    }

		    public void mouseExited(MouseEvent evt) {
		    	noBttn.setBackground(Color.decode("#999999"));
		    }
		});
		
		
		RoundButton downloadBttn = new RoundButton(30);
		downloadBttn.setBackground(Color.decode("#306572"));
		downloadBttn.setFont(new Font("Caladea Bold", Font.BOLD, 12));
		downloadBttn.setForeground(Color.white);
		downloadBttn.setText("Descargar");
		
		downloadBttn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				option = 1;
				dialog.dispose();
			}
		});
		
		downloadBttn.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent evt) {
				downloadBttn.setBackground(Color.decode("#264F59"));
		    }

		    public void mouseExited(MouseEvent evt) {
		    	downloadBttn.setBackground(Color.decode("#306572"));
		    }
		});
		
		
		Object[] options = {noBttn, downloadBttn};
		optionPane.setOptions(options);

        dialog.setVisible(true);
        
        if(option==1) {
			return 1;
		}else {
			return 0;
		}
	}
	
	public void checkOptionPane() {
		image = new ImageIcon(OptionPaneButton.class.getResource("/images/checkCircle.png")).getImage().getScaledInstance(45, 45, Image.SCALE_SMOOTH);
		imageIcon = new ImageIcon(image);
		
		JOptionPane optionPane = new JOptionPane(optionPaneMessage, JOptionPane.INFORMATION_MESSAGE, JOptionPane.PLAIN_MESSAGE, imageIcon);
		JDialog dialog = optionPane.createDialog(null, dialogTitle);

		dialog.requestFocus();
		dialog.setFocusable(true);
		dialog.addKeyListener(new KeyAdapter() {
		    @Override
		    public void keyTyped(KeyEvent e) {
		        char c = e.getKeyChar();
		        if (c=='\n'){
		        	dialog.dispose();
		    }
		}});
		
		
		okBttn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dialog.dispose();
			}
		});
		
		
		Object[] options = {okBttn};
		optionPane.setOptions(options);

        dialog.setVisible(true);
	}
	
	public void errorOptionPane() {
		image = new ImageIcon(OptionPaneButton.class.getResource("/images/errorCircle.png")).getImage().getScaledInstance(45, 45, Image.SCALE_SMOOTH);
		imageIcon = new ImageIcon(image);
		
		JOptionPane optionPane = new JOptionPane(optionPaneMessage, JOptionPane.INFORMATION_MESSAGE, JOptionPane.PLAIN_MESSAGE, imageIcon);
		JDialog dialog = optionPane.createDialog(null, dialogTitle);

		dialog.requestFocus();
		dialog.setFocusable(true);
		dialog.addKeyListener(new KeyAdapter() {
		    @Override
		    public void keyTyped(KeyEvent e) {
		        char c = e.getKeyChar();
		        if (c=='\n'){
		        	dialog.dispose();
		    }
		}});
		
		
		okBttn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dialog.dispose();
			}
		});
		
		
		Object[] options = {okBttn};
		optionPane.setOptions(options);

        dialog.setVisible(true);
	}
	
	public void loadingOptionPane(JFrame frame, int time) {
		image = new ImageIcon(OptionPaneButton.class.getResource("/images/elManglarLogo.png")).getImage().getScaledInstance(45, 40, Image.SCALE_SMOOTH);
		imageIcon = new ImageIcon(image);
		
		JOptionPane optionPane = new JOptionPane(optionPaneMessage, JOptionPane.INFORMATION_MESSAGE, JOptionPane.PLAIN_MESSAGE, imageIcon);
        JDialog dialog = optionPane.createDialog(null, dialogTitle);
        dialog.setModalityType(ModalityType.MODELESS);
        dialog.setLocation((frame.getX()+frame.getWidth())/2, (frame.getY()+frame.getHeight())/2);
        
		Object[] options = {okBttn};
		optionPane.setOptions(options);
		
		optionPane.repaint();
		optionPane.revalidate();
		dialog.setVisible(true);
		dialog.setAlwaysOnTop(true);
		
        new Timer(time, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dialog.dispose();
            }
        }).start();
	}
	
}
