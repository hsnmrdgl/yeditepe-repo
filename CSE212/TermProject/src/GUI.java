import java.awt.*;
import java.awt.Color;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JTextField;


public class GUI extends JFrame implements ActionListener{
	
	private JMenuBar menuBar;
	private JMenu menuFile, menuHelp;
	private JMenuItem newGame, quit, resetGame, scoreTbl, about;
	
	public JLabel tipText, notInStr, statusText, countdownText, hangmanImg;
    public JTextField txtLabel1, txtLabel2, txtLabel3, txtLabel4, txtLabel5, txtLabel6,
    					txtLabel7, txtLabel8, txtLabel9, txtLabel10;
    public JButton btnA, btnB, btnC, btnD, btnE, btnF, btnG, btnH, btnI, btnJ, btnK, btnL,
    				btnM, btnN, btnO, btnP, btnQ, btnR, btnS, btnT, btnU, btnV, btnW, btnX,
    				btnY, btnZ;
    
    public ImageIcon hangmanIcon;
    public Image newimg;
	
	public GUI(){
		super("CSE 212 - Hangman Game");
		setLayout (null);
		
		//MENU BAR
		menuBar = new JMenuBar();
		
		menuFile = new JMenu("File");
		menuBar.add(menuFile);
		menuHelp = new JMenu("Help");
		menuBar.add(menuHelp);
		
		newGame = new JMenuItem("New Game");
		menuFile.add(newGame);
		newGame.addActionListener(this);
		resetGame = new JMenuItem("Reset Game");
		menuFile.add(resetGame);
		resetGame.addActionListener(this);
		scoreTbl = new JMenuItem("Score Table");
		menuFile.add(scoreTbl);
		scoreTbl.addActionListener(this);
		quit = new JMenuItem("Quit");
		menuFile.add(quit);
		quit.addActionListener(this);
		
		about = new JMenuItem("About");
		about.addActionListener(this);
		menuHelp.add(about);
		
		setJMenuBar(menuBar);
		
		
		//LABELS
		tipText = new JLabel ("Tip : ");
        notInStr = new JLabel ("Not in string : ");
        statusText = new JLabel ("Status : In Progress");
        countdownText = new JLabel ("Countdown :");
        
        //HANGMAN ICON
        hangmanImg = new JLabel ();
        hangmanIcon = new ImageIcon("resource/1.png");
        newimg = hangmanIcon.getImage().getScaledInstance(300, 500,  java.awt.Image.SCALE_SMOOTH);
        hangmanIcon = new ImageIcon(newimg);
        hangmanImg.setIcon(hangmanIcon);
        
        tipText.setBounds (25, 20, 350, 30);
        notInStr.setBounds (25, 50, 350, 30);
        statusText.setBounds (25, 100, 200, 25);
        countdownText.setBounds (250, 100, 200, 25);
        hangmanImg.setBounds (650, 25, 400, 500);
        
        
        //TEXT AREAS
        txtLabel1 = new JTextField();
        txtLabel2 = new JTextField();
        txtLabel3 = new JTextField();
        txtLabel4 = new JTextField();
        txtLabel5 = new JTextField();
        txtLabel6 = new JTextField();
        txtLabel7 = new JTextField();
        txtLabel8 = new JTextField();
        txtLabel9 = new JTextField();
        txtLabel10 = new JTextField();
        
        txtLabel1.setFont(new Font(null, Font.BOLD, 20));
        txtLabel2.setFont(new Font(null, Font.BOLD, 20));
        txtLabel3.setFont(new Font(null, Font.BOLD, 20));
        txtLabel4.setFont(new Font(null, Font.BOLD, 20));
        txtLabel5.setFont(new Font(null, Font.BOLD, 20));
        txtLabel6.setFont(new Font(null, Font.BOLD, 20));
        txtLabel7.setFont(new Font(null, Font.BOLD, 20));
        txtLabel8.setFont(new Font(null, Font.BOLD, 20));
        txtLabel9.setFont(new Font(null, Font.BOLD, 20));
        txtLabel10.setFont(new Font(null, Font.BOLD, 20));
        
        txtLabel1.setHorizontalAlignment(JTextField.CENTER);
        txtLabel2.setHorizontalAlignment(JTextField.CENTER);
        txtLabel3.setHorizontalAlignment(JTextField.CENTER);
        txtLabel4.setHorizontalAlignment(JTextField.CENTER);
        txtLabel5.setHorizontalAlignment(JTextField.CENTER);
        txtLabel6.setHorizontalAlignment(JTextField.CENTER);
        txtLabel7.setHorizontalAlignment(JTextField.CENTER);
        txtLabel8.setHorizontalAlignment(JTextField.CENTER);
        txtLabel9.setHorizontalAlignment(JTextField.CENTER);
        txtLabel10.setHorizontalAlignment(JTextField.CENTER);
        
        txtLabel1.setBounds (25, 150, 50, 40);
        txtLabel2.setBounds (80, 150, 50, 40);
        txtLabel3.setBounds (135, 150, 50, 40);
        txtLabel4.setBounds (190, 150, 50, 40);
        txtLabel5.setBounds (245, 150, 50, 40);
        txtLabel6.setBounds (300, 150, 50, 40);
        txtLabel7.setBounds (355, 150, 50, 40);
        txtLabel8.setBounds (410, 150, 50, 40);
        txtLabel9.setBounds (465, 150, 50, 40);
        txtLabel10.setBounds (520, 150, 50, 40);
        
        txtLabel1.setEnabled (false);
        txtLabel2.setEnabled (false);
        txtLabel3.setEnabled (false);
        txtLabel4.setEnabled (false);
        txtLabel5.setEnabled (false);
        txtLabel6.setEnabled (false);
        txtLabel7.setEnabled (false);
        txtLabel8.setEnabled (false);
        txtLabel9.setEnabled (false);
        txtLabel10.setEnabled (false);
        
        txtLabel1.setDisabledTextColor(Color.BLACK);
        txtLabel2.setDisabledTextColor(Color.BLACK);
        txtLabel3.setDisabledTextColor(Color.BLACK);
        txtLabel4.setDisabledTextColor(Color.BLACK);
        txtLabel5.setDisabledTextColor(Color.BLACK);
        txtLabel6.setDisabledTextColor(Color.BLACK);
        txtLabel7.setDisabledTextColor(Color.BLACK);
        txtLabel8.setDisabledTextColor(Color.BLACK);
        txtLabel9.setDisabledTextColor(Color.BLACK);
        txtLabel10.setDisabledTextColor(Color.BLACK);
        
        txtLabel1.setVisible (false);
        txtLabel2.setVisible (false);
        txtLabel3.setVisible (false);
        txtLabel4.setVisible (false);
        txtLabel5.setVisible (false);
        txtLabel6.setVisible (false);
        txtLabel7.setVisible (false);
        txtLabel8.setVisible (false);
        txtLabel9.setVisible (false);
        txtLabel10.setVisible (false);
        
        //BUTTONS
        btnA = new JButton ("A");
        btnB = new JButton ("B");
        btnC = new JButton ("C");
        btnD = new JButton ("D");
        btnE = new JButton ("E");
        btnF = new JButton ("F");
        btnG = new JButton ("G");
        btnH = new JButton ("H");
        btnI = new JButton ("I");
        btnJ = new JButton ("J");
        btnK = new JButton ("K");
        btnL = new JButton ("L");
        btnM = new JButton ("M");
        btnN = new JButton ("N");
        btnO = new JButton ("O");
        btnP = new JButton ("P");
        btnQ = new JButton ("Q");
        btnR = new JButton ("R");
        btnS = new JButton ("S");
        btnT = new JButton ("T");
        btnU = new JButton ("U");
        btnV = new JButton ("V");
        btnW = new JButton ("W");
        btnX = new JButton ("X");
        btnY = new JButton ("Y");
        btnZ = new JButton ("Z");
        
        
        btnA.setBounds (25, 250, 50, 40);
        btnB.setBounds (80, 250, 50, 40);
        btnC.setBounds (135, 250, 50, 40);
        btnD.setBounds (190, 250, 50, 40);
        btnE.setBounds (245, 250, 50, 40);
        btnF.setBounds (300, 250, 50, 40);
        btnG.setBounds (355, 250, 50, 40);
        btnH.setBounds (410, 250, 50, 40);
        btnI.setBounds (465, 250, 50, 40);
        btnJ.setBounds (520, 250, 50, 40);
        btnK.setBounds (25, 300, 50, 40);
        btnL.setBounds (80, 300, 50, 40);
        btnM.setBounds (135, 300, 50, 40);
        btnN.setBounds (190, 300, 50, 40);
        btnO.setBounds (245, 300, 50, 40);
        btnP.setBounds (300, 300, 50, 40);
        btnQ.setBounds (355, 300, 50, 40);
        btnR.setBounds (410, 300, 50, 40);
        btnS.setBounds (465, 300, 50, 40);
        btnT.setBounds (520, 300, 50, 40);
        btnU.setBounds (135, 350, 50, 40);
        btnV.setBounds (190, 350, 50, 40);
        btnW.setBounds (245, 350, 50, 40);
        btnX.setBounds (300, 350, 50, 40);
        btnY.setBounds (355, 350, 50, 40);
        btnZ.setBounds (410, 350, 50, 40);
        

        //ADD
        add (tipText);
        add (notInStr);
        add (statusText);
        add (countdownText);
        add (txtLabel1);
        add (txtLabel2);
        add (txtLabel3);
        add (txtLabel4);
        add (txtLabel5);
        add (txtLabel6);
        add (hangmanImg);
        add (txtLabel7);
        add (txtLabel8);
        add (txtLabel9);
        add (txtLabel10);
        add (btnA);
        add (btnB);
        add (btnC);
        add (btnD);
        add (btnE);
        add (btnF);
        add (btnG);
        add (btnH);
        add (btnI);
        add (btnJ);
        add (btnK);
        add (btnL);
        add (btnM);
        add (btnN);
        add (btnN);
        add (btnO);
        add (btnP);
        add (btnQ);
        add (btnR);
        add (btnS);
        add (btnT);
        add (btnU);
        add (btnV);
        add (btnW);
        add (btnX);
        add (btnY);
        add (btnZ);
        
	}
	
	public JMenuItem getNewGame() {
		return newGame;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource() == newGame) {
			try {
				GameHandler newGame = new GameHandler();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		
		if(e.getSource() == scoreTbl) {
			
		}
		
		if(e.getSource() == about) {
			JOptionPane.showMessageDialog(null, "Made by Hasan Caglar Muradoglu\n\t20160702053");
		}
		
		if(e.getSource() == quit) {
			int dialogButton = JOptionPane.showConfirmDialog(null, "Are you sure?","Message",JOptionPane.YES_OPTION);

			if(dialogButton == JOptionPane.YES_OPTION) {
				System.exit(0);
			}
		}
	}
}