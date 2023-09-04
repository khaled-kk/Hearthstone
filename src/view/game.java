 package view;

import javax.swing.JOptionPane;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import engine.Game;
import engine.GameListener;
import exceptions.CannotAttackException;
import exceptions.FullFieldException;
import exceptions.FullHandException;
import exceptions.HeroPowerAlreadyUsedException;
import exceptions.InvalidTargetException;
import exceptions.NotEnoughManaException;
import exceptions.NotSummonedException;
import exceptions.NotYourTurnException;
import exceptions.TauntBypassException;
import model.cards.Card;
import model.cards.Rarity;
import model.cards.minions.Minion;
import model.cards.spells.AOESpell;
import model.cards.spells.CurseOfWeakness;
import model.cards.spells.DivineSpirit;
import model.cards.spells.Flamestrike;
import model.cards.spells.HeroTargetSpell;
import model.cards.spells.HolyNova;
import model.cards.spells.KillCommand;
import model.cards.spells.LeechingSpell;
import model.cards.spells.LevelUp;
import model.cards.spells.MinionTargetSpell;
import model.cards.spells.MultiShot;
import model.cards.spells.Polymorph;
import model.cards.spells.Pyroblast;
import model.cards.spells.SealOfChampions;
import model.cards.spells.ShadowWordDeath;
import model.cards.spells.SiphonSoul;
import model.cards.spells.Spell;
import model.cards.spells.TwistingNether;
import model.heroes.Hero;
import model.heroes.Hunter;
import model.heroes.Mage;
import model.heroes.Paladin;
import model.heroes.Priest;
import model.heroes.Warlock;

@SuppressWarnings("serial")
public class game extends JFrame implements ActionListener, GameListener{
	private static String[] heros = new String[] {"hunter","mage","warlock","paladin","priest"};
	private static JComboBox<String> listofheros1;
	private static JComboBox<String> listofheros2;
	private static JComboBox<String> oppMinion;
	private static JComboBox<String> curMinion2;
	private static JComboBox<String> curMinion;
	private static JComboBox<String> oppMinionwithhero;
	private static JComboBox<String> Oppfield;
	private JButton Attack;
	private JButton button;
	private JButton button1;
	private JButton button2;
	private JButton startGame;
	private JButton useheropower;
	private JLabel backgroundLabel;
	private JLabel label;
	private JLabel label1;
	private JLabel label2;
	private JLabel Curhp;
	private JLabel curhp;
	private JLabel curfield;
	private JLabel cardsleft;
	private JLabel curhero;
	private JLabel name;
	private JLabel manaCrystel;
	private JLabel totmanaCrystel;
	private JLabel curhand ;
	private JLabel curdeck;
	private JLabel Opphp;
	private JLabel opphp;
	private JLabel cardsleft3;
	private JLabel opphero;
	private JLabel name2;
	private JLabel manaCrystel2;
	private JLabel totmanaCrystel2;
	private JLabel opphand;
	private JLabel oppdeck;
	private JLabel oppfield;
	private JLabel Opphand;
	private Hero PlayerA;
	private Hero PlayerB;
	private JPanel gameview;
	private JLabel player;
	private JLabel Player1;
	private JLabel Player2;
	static int key = 0;
	private static Card temp1;
	private Spell tmpSpell;
	private Minion tmpMinion;
	private static JFrame window;
	private static Game gamex;
	private JLabel playerName1, user1;
    private JTextField playerNameInput1;
    private String userName1;
    private JPanel mainPanel1, loginPanel1, gameDataPanel1;
    private CardLayout gameDataLayout1 = new CardLayout();
    private JLabel playerName2, user2;
    private JTextField playerNameInput2;
    private String userName2;
    private JPanel mainPanel2, loginPanel2, gameDataPanel2;
    private CardLayout gameDataLayout2 = new CardLayout();
	Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

	public game(){
		window = new JFrame("Hearthstone");	
		window.setDefaultCloseOperation(EXIT_ON_CLOSE);
		JFrame.setDefaultLookAndFeelDecorated(true);
		backgroundLabel = new JLabel();
		window.setExtendedState(JFrame.MAXIMIZED_BOTH);
		window.setSize((int) screenSize.getWidth(),(int) screenSize.getHeight());
		backgroundLabel.setBounds(0,(int)screenSize.getWidth(),(int) (screenSize.getWidth()),(int)(screenSize.getHeight()));
 		button = new JButton("press here");
		button.setFont(new Font("Arial", Font.PLAIN, ((int) (screenSize.getWidth()*0.014))));
		button.setForeground(Color.black);
		button.setBounds((int) (screenSize.getWidth() * 0.47 ),(int)(screenSize.getHeight() *0.7)-40,(int) (screenSize.getWidth() * 0.1),(int)(screenSize.getHeight() *0.05)-10);
		button.addActionListener(this);
		window.add(button);
		backgroundLabel.setIcon(new ImageIcon(new ImageIcon("images\\hearthstone.png").getImage().getScaledInstance(backgroundLabel.getWidth(), backgroundLabel.getHeight(), Image.SCALE_DEFAULT)));
		window.add(backgroundLabel);
		
	}
	public void menu() {
		button1 = new JButton("Play");
		button2 = new JButton("Exit");
		button1.setFont(new Font("Arial", Font.PLAIN, ((int) (screenSize.getWidth()*0.012))));
		button1.setForeground(Color.black);
		button1.setBounds((int) (screenSize.getWidth() * 0.4 )+90,(int)(screenSize.getHeight() *0.38),(int) (screenSize.getWidth() * 0.2)-160,(int)(screenSize.getHeight() *0.05)-10);
		button1.addActionListener(this);
		button2.setFont(new Font("Arial", Font.PLAIN, ((int) (screenSize.getWidth()*0.012))));
		button2.setForeground(Color.black);
		button2.setBounds((int) (screenSize.getWidth() * 0.4 )+90,(int)(screenSize.getHeight() *0.48),(int) (screenSize.getWidth() * 0.2)-160,(int)(screenSize.getHeight() *0.05)-10);
		button2.addActionListener(this);
		window.add(button1);
		window.add(button2);		
	}
	public void pick()	{
		listofheros1 = new JComboBox<>(heros);
		listofheros1.setBackground(new Color(201,181,154));
		listofheros2 = new JComboBox<>(heros);
		listofheros2.setBackground(new Color(201,181,154));
		listofheros1.setSelectedIndex(0);
		listofheros2.setSelectedIndex(0);
		listofheros1.setBounds((int) (screenSize.getWidth() * 0.22 ),(int)(screenSize.getHeight() *0.685),(int) (screenSize.getWidth() * 0.1),(int)(screenSize.getHeight() *0.04));
		listofheros2.setBounds((int) (screenSize.getWidth() * 0.69 ),(int)(screenSize.getHeight() *0.685),(int) (screenSize.getWidth() * 0.1),(int)(screenSize.getHeight() *0.04));
		listofheros1.setFont(new Font("Arial", Font.PLAIN, ((int) (screenSize.getWidth()*0.014))));
		listofheros2.setFont(new Font("Arial", Font.PLAIN, ((int) (screenSize.getWidth()*0.014))));
		
		mainPanel1 = new JPanel();
	    mainPanel1.setLayout(gameDataLayout1);
	    mainPanel1.setBounds((int) (screenSize.getWidth() * 0.2 )-5,(int)(screenSize.getHeight() *0.5)+110,(int) (screenSize.getWidth() * 0.16),(int)(screenSize.getHeight() *0.05)-20);

	    playerName1 = new JLabel("Enter Player 1 Name: ");
	    playerNameInput1 = new JTextField((int) (screenSize.getWidth() * 0.005 )+4);

	    loginPanel1= new JPanel();
	    loginPanel1.setBackground(new Color(215, 135, 50, 123));
	    loginPanel1.add(playerName1);
	    loginPanel1.add(playerNameInput1);

	    gameDataPanel1 = new JPanel();
	    user1 = new JLabel();
	    user1.setText(userName1);
	    gameDataPanel1.add(user1);

	    mainPanel1.add(loginPanel1, "1");
	    mainPanel1.add(gameDataPanel1, "2");
	    gameDataLayout1.show(mainPanel1, "1");
	        
	    mainPanel2 = new JPanel();
	    mainPanel2.setLayout(gameDataLayout2);
	    mainPanel2.setBounds((int) (screenSize.getWidth() * 0.65 )+20,(int)(screenSize.getHeight() *0.5)+110,(int) (screenSize.getWidth() * 0.16),(int)(screenSize.getHeight() *0.05)-20);

	    playerName2= new JLabel("Enter Player 2 Name: ");
	    playerNameInput2 = new JTextField((int) (screenSize.getWidth() * 0.005 )+3);

	    loginPanel2 = new JPanel();
	    loginPanel2.setBackground(new Color(215, 135, 50, 123));
	    loginPanel2.add(playerName2);
	    loginPanel2.add(playerNameInput2);

	    gameDataPanel2 = new JPanel();
	    user2 = new JLabel();
	    user2.setText(userName2);
	    gameDataPanel2.add(user2);

	    mainPanel2.add(loginPanel2, "1");
	    mainPanel2.add(gameDataPanel2, "2");
	    gameDataLayout2.show(mainPanel2, "1");
		
		
		label = new JLabel("Hero Selection");
		label.setBounds((int) (screenSize.getWidth() * 0.4 ),(int)(screenSize.getHeight() *0.2),(int) (screenSize.getWidth() * 0.2),(int)(screenSize.getHeight() *0.05));
		label.setFont(new Font("Arial", Font.PLAIN, ((int) (screenSize.getWidth()*0.014))));
		label.setHorizontalAlignment(JLabel.CENTER);

		label1 = new JLabel("First Hero");
		label1.setBounds((int) (screenSize.getWidth() * 0.22 ),(int)(screenSize.getHeight() *0.1),(int) (screenSize.getWidth() * 0.2),(int)(screenSize.getHeight() *0.05));
		label1.setFont(new Font("Arial", Font.PLAIN, ((int) (screenSize.getWidth()*0.014))));

		label2 = new JLabel("Second Hero");
		label2.setBounds((int) (screenSize.getWidth() * 0.69 ),(int)(screenSize.getHeight() *0.1),(int) (screenSize.getWidth() * 0.2),(int)(screenSize.getHeight() *0.05));
		label2.setFont(new Font("Arial", Font.PLAIN, ((int) (screenSize.getWidth()*0.014))));

		startGame = new JButton("Start Game");
		startGame.addActionListener(this);
		startGame.setBounds((int) (screenSize.getWidth() * 0.4 )+30,(int)(screenSize.getHeight() *0.7),(int) (screenSize.getWidth() * 0.2)-60,(int)(screenSize.getHeight() *0.05));
		startGame.setFont(new Font("Arial", Font.PLAIN, ((int) (screenSize.getWidth()*0.014))));
		startGame.setBackground(new Color(201,181,154));

		window.add(mainPanel1);
        window.add(mainPanel2);
		window.add(label);
		window.add(label1);
		window.add(label2);
		window.add(listofheros1);
		window.add(listofheros2);
		window.add(startGame);
	}

	public void run() {
		if(gamex.getCurrentHero().getCurrentHP() != 0 || gamex.getOpponent().getCurrentHP() != 0) {
			gameview= new JPanel();
			gameview.setLayout(null);

			curhp = new JLabel();
			curhp.setBounds(0,(int)(screenSize.getHeight() *0.56)-8,(int) (screenSize.getWidth() * 0.105),(int)(screenSize.getHeight() *0.185));
			curhp.setIcon(new ImageIcon(new ImageIcon("images/hp.jpg").getImage().getScaledInstance(curhp.getWidth(), curhp.getHeight(), Image.SCALE_DEFAULT)));
			Curhp = new JLabel();
			Curhp.setText(gamex.getCurrentHero().getCurrentHP() + " HP");
			Curhp.setFont(new Font("Arial", Font.PLAIN, ((int) (screenSize.getWidth()*0.02))));
			Curhp.setForeground(Color.green);
			if(gamex.getCurrentHero().getCurrentHP()<=15){
				Curhp.setForeground(Color.orange);
			}
			if(gamex.getCurrentHero().getCurrentHP()<=5){
				Curhp.setForeground(Color.red);		
			}
			Curhp.setBounds((int) (screenSize.getWidth() * 0.02),0,(int) (screenSize.getWidth() * 0.105),(int)(screenSize.getHeight() *0.185));
			curhp.add(Curhp);
			curhp.setLayout(null);

			useheropower = new JButton();
			useheropower.setText("Use Hero Power");
			useheropower.setFont(new Font("Arial", Font.PLAIN, (int) (screenSize.getWidth()*0.01)));
			useheropower.setForeground(Color.black);
			useheropower.setBackground(Color.LIGHT_GRAY);
			useheropower.setBounds((int)(screenSize.getWidth() *0.895)+8,(int)(screenSize.getHeight() *0.556)-5,(int) (screenSize.getWidth() * 0.105),(int)(screenSize.getHeight() *0.195));
			useheropower.addActionListener(this);

			GridLayout cards2 = new GridLayout(1,10);
			curhand = new JLabel();
			curhand.setBorder(BorderFactory.createLineBorder(Color.BLACK));
			curhand.setBounds((int)(screenSize.getWidth() *0.105),(int)(screenSize.getHeight() *0.72)+6,window.getWidth()-(2*(int) (screenSize.getWidth() * 0.105)),(int)(screenSize.getHeight() *0.195));
			curhand.setIcon(new ImageIcon("images/hand.png"));
			curhand.setLayout(cards2);
			showcard(gamex.getCurrentHero().getHand());

			curdeck=new JLabel();
			curdeck.setBorder(BorderFactory.createLineBorder(Color.BLACK));
			curdeck.setBounds((int)(screenSize.getWidth() *0.895)+1,(int)(screenSize.getHeight() *0.75)-5,(int) (screenSize.getWidth() * 0.105),(int)(screenSize.getHeight() *0.185));
			if(gamex.getCurrentHero()instanceof Hunter){
				curdeck.setIcon(new ImageIcon(new ImageIcon("images/Hunter deck.png").getImage().getScaledInstance(curdeck.getWidth(), curdeck.getHeight(), Image.SCALE_DEFAULT)));
			}
			if(gamex.getCurrentHero()instanceof Mage){
				curdeck.setIcon(new ImageIcon(new ImageIcon("images/mage deck.png").getImage().getScaledInstance(curdeck.getWidth(), curdeck.getHeight(), Image.SCALE_DEFAULT)));
			}
			if(gamex.getCurrentHero()instanceof Paladin){
				curdeck.setIcon(new ImageIcon(new ImageIcon("images/Paladin deck.png").getImage().getScaledInstance(curdeck.getWidth(), curdeck.getHeight(), Image.SCALE_DEFAULT)));
			}
			if(gamex.getCurrentHero()instanceof Priest){
				curdeck.setIcon(new ImageIcon(new ImageIcon("images/priest deck.png").getImage().getScaledInstance(curdeck.getWidth(), curdeck.getHeight(), Image.SCALE_DEFAULT)));
			}
			if(gamex.getCurrentHero()instanceof Warlock){
				curdeck.setIcon(new ImageIcon(new ImageIcon("images/Warlock deck.png").getImage().getScaledInstance(curdeck.getWidth(), curdeck.getHeight(), Image.SCALE_DEFAULT)));
			}
			
			cardsleft = new JLabel();
			cardsleft.setFont(new Font("Arial", Font.PLAIN, (int) (screenSize.getWidth()*0.013)));
			cardsleft.setForeground(Color.white);
			if(gamex.getCurrentHero()instanceof Mage){
				cardsleft.setForeground(Color.yellow);
			}
			cardsleft.setBounds((int)(screenSize.getWidth() *0.895)+26,(int)(screenSize.getHeight() *0.75)+15,(int) (screenSize.getWidth() * 0.105),(int)(screenSize.getHeight() *0.05));
			cardsleft.setText(gamex.getCurrentHero().getDeck().size()+" Cards left");

			curfield = new JLabel();
			curfield.setBorder(BorderFactory.createLineBorder(Color.BLACK));			
			curfield.setBounds((int)(screenSize.getWidth() *0.105),(int)(screenSize.getHeight() *0.47),window.getWidth()-(2*(int) (screenSize.getWidth() * 0.105)),(int)(screenSize.getHeight() *0.29));
			curfield.setLayout(new GridLayout(1,7));
			for(int i = 0;i < gamex.getCurrentHero().getField().size();i++) {
				JLabel minioncard= drawMinion(gamex.getCurrentHero().getField().get(i), i);
				curfield.add(minioncard);
			}
			curfield.setIcon(new ImageIcon(new ImageIcon("images/curfeild.png").getImage().getScaledInstance(curfield.getWidth(), curfield.getHeight(), Image.SCALE_DEFAULT)));
			
			curhero = new  JLabel();
			curhero.setBounds(0,(int)(screenSize.getHeight() *0.73)+5,(int) (screenSize.getWidth() * 0.105),(int)(screenSize.getHeight() *0.185));
			curhero.setBorder(BorderFactory.createLineBorder(Color.BLACK));
			curhero.setLayout(new GridLayout(4,1));
			name = new JLabel(gamex.getCurrentHero().getName());
			name.setFont(new Font("Arial", Font.PLAIN, ((int) (screenSize.getWidth()*0.014))));
			name.setForeground(Color.WHITE);
			if(gamex.getCurrentHero() instanceof Priest){
				name.setForeground(Color.red);
			}
			curhero.add(name);
			manaCrystel = new JLabel(gamex.getCurrentHero().getCurrentManaCrystals() + " current mana");
			manaCrystel.setFont(new Font("Arial", Font.PLAIN, ((int) (screenSize.getWidth()*0.012))));
			manaCrystel.setForeground(Color.cyan);
			curhero.add(manaCrystel);
			totmanaCrystel = new JLabel(gamex.getCurrentHero().getTotalManaCrystals() + " total mana");
			totmanaCrystel.setFont(new Font("Arial", Font.PLAIN, ((int) (screenSize.getWidth()*0.012))));
			totmanaCrystel.setForeground(Color.cyan);
			curhero.add(totmanaCrystel);

			opphp = new JLabel();
			opphp.setBounds(0,(int)(screenSize.getHeight() *0.19),(int) (screenSize.getWidth() * 0.105),(int)(screenSize.getHeight() *0.185));
			opphp.setIcon(new ImageIcon(new ImageIcon("images/hp.jpg").getImage().getScaledInstance(opphp.getWidth(), opphp.getHeight(), Image.SCALE_DEFAULT)));
			Opphp = new JLabel();
			Opphp.setText(gamex.getOpponent().getCurrentHP() + " HP");
			Opphp.setFont(new Font("Arial", Font.PLAIN, ((int) (screenSize.getWidth()*0.02))));
			Opphp.setForeground(Color.green);
			if(gamex.getOpponent().getCurrentHP()<=15){
				Opphp.setForeground(Color.orange);
			}
			if(gamex.getOpponent().getCurrentHP()<=5){
				Opphp.setForeground(Color.red);		
			}
			Opphp.setBounds((int) (screenSize.getWidth() * 0.02),0,(int) (screenSize.getWidth() * 0.105),(int)(screenSize.getHeight() *0.185));
			opphp.add(Opphp);
			opphp.setLayout(null);

			player=new JLabel();
			player.setBorder(BorderFactory.createLineBorder(Color.BLACK));
			player.setBounds((int)(screenSize.getWidth() *0.895)+7,(int)(screenSize.getHeight() *0.19)-11,(int) (screenSize.getWidth() * 0.105),(int)(screenSize.getHeight() *0.185));
			player.setIcon(new ImageIcon(new ImageIcon("images/temp.png").getImage().getScaledInstance(player.getWidth(), player.getHeight(), Image.SCALE_DEFAULT)));

			Player1 =new JLabel();
			Player1.setFont(new Font("Arial", Font.PLAIN, ((int) (screenSize.getWidth()*0.014))-4));
			Player1.setForeground(Color.black);
			Player1.setBounds((int)(screenSize.getWidth() *0.91),(int)(screenSize.getHeight() *0.19)-7,(int) (screenSize.getWidth() * 0.105),(int)(screenSize.getHeight() *0.185));
			Player1.setText(userName1+"'s turn");
			Player2 =new JLabel();	
			Player2.setFont(new Font("Arial", Font.PLAIN, ((int) (screenSize.getWidth()*0.014))-4));
			Player2.setForeground(Color.black);
			Player2.setBounds((int)(screenSize.getWidth() *0.91),(int)(screenSize.getHeight() *0.19)-7,(int) (screenSize.getWidth() * 0.105),(int)(screenSize.getHeight() *0.185));
			Player2.setText(userName2+"'s turn");
			check();

			JLabel temp2 = new JLabel();			
			temp2.setBounds((int) (screenSize.getWidth() * -0.003),(int)(screenSize.getHeight() * 0.37),(int) (screenSize.getWidth() * 0.111),(int)(screenSize.getHeight() *0.185));
			temp2.setIcon(new ImageIcon(new ImageIcon("images/temp.png").getImage().getScaledInstance(temp2.getWidth(), temp2.getHeight(), Image.SCALE_DEFAULT)));

			opphand = new JLabel();
			opphand.setBorder(BorderFactory.createLineBorder(Color.black));
			Opphand = new JLabel();
			Opphand.setForeground(Color.orange);
			Opphand.setText(gamex.getOpponent().getHand().size() + " cards left in hand");
			Opphand.setHorizontalAlignment(JLabel.CENTER);
			Opphand.setFont(new Font("Arial", Font.PLAIN, ((int) (screenSize.getWidth()*0.02))));
			opphand.setIcon(new ImageIcon("images/hand.png"));
			opphand.setBounds((int)(screenSize.getWidth() *0.105),-1,window.getWidth()-(2*(int) (screenSize.getWidth() * 0.105)),(int)(screenSize.getHeight() *0.195));
			opphand.setLayout(new GridLayout(1,1));
			opphand.add(Opphand);

			oppfield = new JLabel();
			oppfield.setBorder(BorderFactory.createLineBorder(Color.BLACK));
			oppfield.setIcon(new ImageIcon(new ImageIcon("images/oppfeild.png").getImage().getScaledInstance(curfield.getWidth(), curfield.getHeight(), Image.SCALE_DEFAULT)));
			oppfield.setBounds((int)(screenSize.getWidth() *0.105),(int)(screenSize.getHeight() *0.19)-1,window.getWidth()-(2*(int) (screenSize.getWidth() * 0.105)),(int)(screenSize.getHeight() *0.29));
			oppfield.setLayout(new GridLayout(1,7));
			for(int i = 0;i < gamex.getOpponent().getField().size();i++) {
				JLabel minioncard= drawMinion2(gamex.getOpponent().getField().get(i), i);
				oppfield.add(minioncard);
			}

			oppdeck=new JLabel();
			oppdeck.setBorder(BorderFactory.createLineBorder(Color.BLACK));
			oppdeck.setBounds((int)(screenSize.getWidth() *0.895)+1,(int)(screenSize.getHeight() *0.002),(int) (screenSize.getWidth() * 0.105),(int)(screenSize.getHeight() *0.185));
			if(gamex.getOpponent()instanceof Hunter){
				oppdeck.setIcon(new ImageIcon(new ImageIcon("images/Hunter deck.png").getImage().getScaledInstance(oppdeck.getWidth(), oppdeck.getHeight(), Image.SCALE_DEFAULT)));
			}
			if(gamex.getOpponent()instanceof Mage){
				oppdeck.setIcon(new ImageIcon(new ImageIcon("images/mage deck.png").getImage().getScaledInstance(oppdeck.getWidth(), oppdeck.getHeight(), Image.SCALE_DEFAULT)));
			}
			if(gamex.getOpponent()instanceof Paladin){
				oppdeck.setIcon(new ImageIcon(new ImageIcon("images/Paladin deck.png").getImage().getScaledInstance(oppdeck.getWidth(), oppdeck.getHeight(), Image.SCALE_DEFAULT)));
			}
			if(gamex.getOpponent()instanceof Priest){
				oppdeck.setIcon(new ImageIcon(new ImageIcon("images/priest deck.png").getImage().getScaledInstance(oppdeck.getWidth(), oppdeck.getHeight(), Image.SCALE_DEFAULT)));
			}
			if(gamex.getOpponent()instanceof Warlock){
				oppdeck.setIcon(new ImageIcon(new ImageIcon("images/Warlock deck.png").getImage().getScaledInstance(oppdeck.getWidth(), oppdeck.getHeight(), Image.SCALE_DEFAULT)));
			}

			cardsleft3 = new JLabel();
			cardsleft3.setFont(new Font("Arial", Font.PLAIN, ((int) (screenSize.getWidth()*0.013))));
			cardsleft3.setForeground(Color.white);
			if(gamex.getOpponent()instanceof Mage){
				cardsleft3.setForeground(Color.yellow);
			}
			cardsleft3.setBounds((int)(screenSize.getWidth() *0.896)+26,(int)(screenSize.getHeight() *0.02),(int) (screenSize.getWidth() * 0.105),(int)(screenSize.getHeight() *0.05));
			cardsleft3.setText(gamex.getOpponent().getDeck().size()+" Cards left");

			opphero = new  JLabel();
			opphero.setBounds(0,0,(int) (screenSize.getWidth() * 0.105),(int)(screenSize.getHeight() *0.195));
			opphero.setBorder(BorderFactory.createLineBorder(Color.BLACK));
			opphero.setLayout(new GridLayout(4,1));
			name2 = new JLabel(gamex.getOpponent().getName());
			name2.setFont(new Font("Arial", Font.PLAIN, ((int) (screenSize.getWidth()*0.014))));
			name2.setForeground(Color.white);
			if(gamex.getOpponent() instanceof Priest){
				name2.setForeground(Color.red);
			}
			opphero.add(name2);
			manaCrystel2 = new JLabel(gamex.getOpponent().getCurrentManaCrystals() + " current mana");
			manaCrystel2.setFont(new Font("Arial", Font.PLAIN, ((int) (screenSize.getWidth()*0.012))));
			manaCrystel2.setForeground(Color.cyan);
			opphero.add(manaCrystel2);
			totmanaCrystel2 = new JLabel(gamex.getOpponent().getTotalManaCrystals() + " total mana");
			totmanaCrystel2.setFont(new Font("Arial", Font.PLAIN, ((int) (screenSize.getWidth()*0.012))));
			totmanaCrystel2.setForeground(Color.cyan);
			opphero.add(totmanaCrystel2);


			JButton turn = new JButton("end turn");
			turn.setFont(new Font("Arial", Font.PLAIN, ((int) (screenSize.getWidth()*0.01))+1));
			turn.setBorder(BorderFactory.createLineBorder(Color.BLACK));
			turn.setBounds((int)(screenSize.getWidth() *0.895)+7,(int)(screenSize.getHeight() * 0.36)-2,(int) (screenSize.getWidth() * 0.105),(int)(screenSize.getHeight() *0.195));
			turn.addActionListener(this);

			gameview.add(turn);
			gameview.add(curhero);
			gameview.add(opphero);
			gameview.add(Player1);
			gameview.add(Player2);
			gameview.add(player);
			gameview.add(useheropower);
			gameview.add(cardsleft);
			gameview.add(cardsleft3);
			gameview.add(oppdeck);
			gameview.add(curdeck);
			gameview.add(opphand);
			gameview.add(curhand);
			gameview.add(curhp);
			gameview.add(opphp);
			gameview.add(curfield);
			gameview.add(oppfield);
			gameview.add(temp2);
			window.add(gameview);
			window.revalidate();
			window.repaint();
		}
	}

	public Hero choosehero(String s) throws IOException, CloneNotSupportedException {
		switch (s){
		case "hunter": return new Hunter();
		case "mage": return new Mage();
		case "paladin" :return new Paladin();
		case "priest":return new Priest();
		case "warlock" : return new Warlock();
		default :return new Warlock();
		}
	}
	public void showcard(ArrayList<Card> deck) {
		for(int i =0; i < deck.size();i++) {
			JLabel temp = new JLabel();
			temp.setEnabled(false);
			temp.setBorder(BorderFactory.createLineBorder(Color.BLACK));
			temp.setLayout(new GridLayout(5,1));
			if(deck.get(i) instanceof Minion) {
				temp.setLayout(new GridLayout(9,1));
			}
			String s = deck.get(i).getName();
			int mc = deck.get(i).getManaCost();
			Rarity r = deck.get(i).getRarity();
			if(deck.get(i) instanceof Spell) {
				JLabel cardName = new JLabel(s);
				cardName.setFont(new Font("Arial", Font.PLAIN, ((int) (screenSize.getWidth()*0.01))+1));
				cardName.setForeground(Color.BLUE);
				cardName.setHorizontalAlignment(JLabel.CENTER);
				temp.add(cardName);
				JLabel manacost = new JLabel(mc + " mana cost");
				manacost.setFont(new Font("Arial", Font.PLAIN, ((int) (screenSize.getWidth()*0.01))));
				manacost.setForeground(Color.CYAN);
				manacost.setHorizontalAlignment(JLabel.CENTER);
				temp.add(manacost);
				if(r.equals(Rarity.BASIC)) {
					JLabel tmp = new JLabel(r + "");
					tmp.setHorizontalAlignment(JLabel.CENTER);
					tmp.setFont(new Font("Arial", Font.PLAIN, ((int) (screenSize.getWidth()*0.01))));
					tmp.setForeground(Color.WHITE);
					temp.add(tmp);
				}
				if(r.equals(Rarity.COMMON)) {
					JLabel tmp = new JLabel(r + "");
					tmp.setHorizontalAlignment(JLabel.CENTER);
					tmp.setFont(new Font("Arial", Font.PLAIN, ((int) (screenSize.getWidth()*0.01))));
					tmp.setForeground(Color.lightGray);
					temp.add(tmp);
				}
				if(r.equals(Rarity.EPIC)) {
					JLabel tmp = new JLabel(r + "");
					tmp.setHorizontalAlignment(JLabel.CENTER);
					tmp.setFont(new Font("Arial", Font.PLAIN, ((int) (screenSize.getWidth()*0.01))));
					tmp.setForeground(Color.MAGENTA);
					temp.add(tmp);
				}
				if(r.equals(Rarity.LEGENDARY)) {
					JLabel tmp = new JLabel(r + "");
					tmp.setHorizontalAlignment(JLabel.CENTER);
					tmp.setFont(new Font("Arial", Font.PLAIN, ((int) (screenSize.getWidth()*0.01))));
					tmp.setForeground(Color.ORANGE);
					temp.add(tmp);
				}
				if(r.equals(Rarity.RARE)) {
					JLabel tmp = new JLabel(r + "");
					tmp.setHorizontalAlignment(JLabel.CENTER);
					tmp.setFont(new Font("Arial", Font.PLAIN, ((int) (screenSize.getWidth()*0.01))));
					tmp.setForeground(Color.YELLOW);
					temp.add(tmp);
				}
				JButton castspell = new JButton("Cast Spell");
				castspell.setHorizontalAlignment(JLabel.CENTER);
				castspell.setFont(new Font("Arial", Font.PLAIN, ((int) (screenSize.getWidth()*0.008))));
				castspell.setActionCommand("Cast Spell "+ i);
				castspell.addActionListener(this);
				temp.add(castspell);
				JLabel hidden = new JLabel();
				temp.add(hidden);
			}
			else{
				JLabel cardName = new JLabel(s);
				cardName.setFont(new Font("Arial", Font.PLAIN, ((int) (screenSize.getWidth()*0.01))+1));
				cardName.setForeground(Color.GREEN);
				cardName.setHorizontalAlignment(JLabel.CENTER);
				temp.add(cardName);
				JLabel manacost = new JLabel(mc + " mana cost");
				manacost.setFont(new Font("Arial", Font.PLAIN,((int) (screenSize.getWidth()*0.01))));
				manacost.setForeground(Color.CYAN);
				manacost.setHorizontalAlignment(JLabel.CENTER);
				temp.add(manacost);
				if(r.equals(Rarity.BASIC)) {
					JLabel tmp = new JLabel(r + "");
					tmp.setHorizontalAlignment(JLabel.CENTER);
					tmp.setFont(new Font("Arial", Font.PLAIN, ((int) (screenSize.getWidth()*0.01))));
					tmp.setForeground(Color.WHITE);
					temp.add(tmp);
				}
				if(r.equals(Rarity.COMMON)) {
					JLabel tmp = new JLabel(r + "");
					tmp.setHorizontalAlignment(JLabel.CENTER);
					tmp.setFont(new Font("Arial", Font.PLAIN, ((int) (screenSize.getWidth()*0.01))));
					tmp.setForeground(Color.lightGray);
					temp.add(tmp);
				}
				if(r.equals(Rarity.EPIC)) {
					JLabel tmp = new JLabel(r + "");
					tmp.setHorizontalAlignment(JLabel.CENTER);
					tmp.setFont(new Font("Arial", Font.PLAIN, ((int) (screenSize.getWidth()*0.01))));
					tmp.setForeground(Color.MAGENTA);
					temp.add(tmp);
				}
				if(r.equals(Rarity.LEGENDARY)) {
					JLabel tmp = new JLabel(r + "");
					tmp.setHorizontalAlignment(JLabel.CENTER);
					tmp.setFont(new Font("Arial", Font.PLAIN, ((int) (screenSize.getWidth()*0.01))));
					tmp.setForeground(Color.ORANGE);
					temp.add(tmp);
				}
				if(r.equals(Rarity.RARE)) {
					JLabel tmp = new JLabel(r + "");
					tmp.setHorizontalAlignment(JLabel.CENTER);
					tmp.setFont(new Font("Arial", Font.PLAIN, ((int) (screenSize.getWidth()*0.01))));
					tmp.setForeground(Color.YELLOW);
					temp.add(tmp);
				}	
				Minion cardminion= (Minion) deck.get(i);
				JLabel attack = new JLabel(cardminion.getAttack() + " damage");
				attack.setHorizontalAlignment(JLabel.CENTER);
				attack.setFont(new Font("Arial", Font.PLAIN, ((int) (screenSize.getWidth()*0.01))-1));
				attack.setForeground(Color.MAGENTA);
				temp.add(attack);
				JLabel Hp = new JLabel(cardminion.getCurrentHP() + " Hp");
				Hp.setHorizontalAlignment(JLabel.CENTER);
				Hp.setFont(new Font("Arial", Font.PLAIN,((int) (screenSize.getWidth()*0.01))-1));
				Hp.setForeground(Color.RED);
				temp.add(Hp);
				if (cardminion.isDivine()) {
					JLabel divine = new JLabel("Has devine sheild");
					divine.setHorizontalAlignment(JLabel.CENTER);
					divine.setFont(new Font("Arial", Font.PLAIN, ((int) (screenSize.getWidth()*0.01))-1));
					divine.setForeground(Color.lightGray);
					temp.add(divine);
				}
				if (cardminion.isTaunt()) {
					JLabel taunt = new JLabel("Has taunt ability");
					taunt.setHorizontalAlignment(JLabel.CENTER);
					taunt.setFont(new Font("Arial", Font.PLAIN, ((int) (screenSize.getWidth()*0.01))-1));
					taunt.setForeground(Color.lightGray);
					temp.add(taunt);
				}
				if (!cardminion.isSleeping()) {
					JLabel charge = new JLabel("Charge");
					charge.setHorizontalAlignment(JLabel.CENTER);
					charge.setFont(new Font("Arial", Font.PLAIN, ((int) (screenSize.getWidth()*0.01))-1));
					charge.setForeground(Color.white);
					temp.add(charge);
				} else {
					JLabel charge = new JLabel("Sleeping");
					charge.setHorizontalAlignment(JLabel.CENTER);
					charge.setFont(new Font("Arial", Font.PLAIN, ((int) (screenSize.getWidth()*0.01))-1));
					charge.setForeground(Color.white);
					temp.add(charge);
				}
				JButton playminion = new JButton("Play minion!");
				playminion.setHorizontalAlignment(JLabel.CENTER);
				playminion.setFont(new Font("Arial", Font.PLAIN, ((int) (screenSize.getWidth()*0.008))));
				playminion.setActionCommand("play minion "+ i);
				playminion.addActionListener(this);
				temp.add(playminion);
			}
			curhand.add(temp);
		}
	}
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("press here")) {
			backgroundLabel.setIcon(new ImageIcon(new ImageIcon("images/hearthstone menu.png").getImage().getScaledInstance(backgroundLabel.getWidth(), backgroundLabel.getHeight(), Image.SCALE_DEFAULT)));			
			window.getContentPane().removeAll();
			window.revalidate();
			window.repaint();
			menu();
			window.add(backgroundLabel); 
		}
		if(e.getActionCommand().equals("Exit")) {
			System.exit(1);
		}
		if(e.getActionCommand().equals("Play")) {
			backgroundLabel.setIcon(new ImageIcon(new ImageIcon("images/hearthstone hero.png").getImage().getScaledInstance(backgroundLabel.getWidth(), backgroundLabel.getHeight(), Image.SCALE_DEFAULT)));
			window.getContentPane().removeAll();
			pick();
			window.add(backgroundLabel);
			window.revalidate();
			window.repaint();
		}
		if(e.getActionCommand().equals("Start Game")) {
			userName1 = playerNameInput1.getText();
			userName2 = playerNameInput2.getText();
			if (userName1.equals("")||userName2.equals("")){
				if (userName1.equals("")&&userName2.equals(""))
					JOptionPane.showMessageDialog(null,"Player 1 and Player 2 have to put a name ","Error", JOptionPane.INFORMATION_MESSAGE);
				else
					if (userName1.equals(""))
						JOptionPane.showMessageDialog(null,"Player 1 have to put a name ","Error", JOptionPane.INFORMATION_MESSAGE);
					else
						JOptionPane.showMessageDialog(null,"Player 2 have to put a name ","Error", JOptionPane.INFORMATION_MESSAGE);
			}
			else{
			try {
				PlayerA = choosehero(listofheros1.getSelectedItem().toString());
			} catch (IOException | CloneNotSupportedException e3) {
				JOptionPane.showMessageDialog(null,e3.getMessage(),"Error", JOptionPane.INFORMATION_MESSAGE);
			}
			try {
				PlayerB = choosehero(listofheros2.getSelectedItem().toString());
			} catch (IOException | CloneNotSupportedException e2) {
				JOptionPane.showMessageDialog(null,e2.getMessage(),"Error", JOptionPane.INFORMATION_MESSAGE);
			}
			try {
				gamex = new Game(PlayerA,PlayerB);
				if(gamex.getCurrentHero().equals(PlayerB)) {
					key++;
				}
				gamex.setListener(this);
			} catch (FullHandException | CloneNotSupportedException e1) {
				JOptionPane.showMessageDialog(null,e1.getMessage(),"Error", JOptionPane.INFORMATION_MESSAGE);
			}
			window.getContentPane().removeAll();
			window.revalidate();
			window.repaint();

			run();
			if(gamex.getCurrentHero()instanceof Hunter){
				curhero.setIcon(new ImageIcon(new ImageIcon("images/Hunter.png").getImage().getScaledInstance(curhero.getWidth(), curhero.getHeight(), Image.SCALE_DEFAULT)));
			}
			if(gamex.getCurrentHero()instanceof Mage){
				curhero.setIcon(new ImageIcon(new ImageIcon("images/mage.png").getImage().getScaledInstance(curhero.getWidth(), curhero.getHeight(), Image.SCALE_DEFAULT)));
			}
			if(gamex.getCurrentHero()instanceof Paladin){
				curhero.setIcon(new ImageIcon(new ImageIcon("images/Paladin.png").getImage().getScaledInstance(curhero.getWidth(), curhero.getHeight(), Image.SCALE_DEFAULT)));
			}
			if(gamex.getCurrentHero()instanceof Priest){
				curhero.setIcon(new ImageIcon(new ImageIcon("images/priest.png").getImage().getScaledInstance(curhero.getWidth(), curhero.getHeight(), Image.SCALE_DEFAULT)));
			}
			if(gamex.getCurrentHero()instanceof Warlock){
				curhero.setIcon(new ImageIcon(new ImageIcon("images/Warlock.png").getImage().getScaledInstance(curhero.getWidth(), curhero.getHeight(), Image.SCALE_DEFAULT)));
			}


			if(gamex.getOpponent()instanceof Hunter){
				opphero.setIcon(new ImageIcon(new ImageIcon("images/Hunter.png").getImage().getScaledInstance(opphero.getWidth(), opphero.getHeight(), Image.SCALE_DEFAULT)));
			}
			if(gamex.getOpponent()instanceof Mage){
				opphero.setIcon(new ImageIcon(new ImageIcon("images/mage.png").getImage().getScaledInstance(opphero.getWidth(), opphero.getHeight(), Image.SCALE_DEFAULT)));
			}
			if(gamex.getOpponent()instanceof Paladin){
				opphero.setIcon(new ImageIcon(new ImageIcon("images/Paladin.png").getImage().getScaledInstance(opphero.getWidth(), opphero.getHeight(), Image.SCALE_DEFAULT)));
			}
			if(gamex.getOpponent()instanceof Priest){
				opphero.setIcon(new ImageIcon(new ImageIcon("images/priest.png").getImage().getScaledInstance(opphero.getWidth(), opphero.getHeight(), Image.SCALE_DEFAULT)));
			}
			if(gamex.getOpponent()instanceof Warlock){
				opphero.setIcon(new ImageIcon(new ImageIcon("images/Warlock.png").getImage().getScaledInstance(opphero.getWidth(), opphero.getHeight(), Image.SCALE_DEFAULT)));
			}
			}
		}
		if(e.getActionCommand().equals("end turn")) {
			if(gamex.getCurrentHero().getDeck().size()!=0){
				temp1=gamex.getCurrentHero().getDeck().get(0);
			}
			try {
				gamex.endTurn();
			} catch (FullHandException | CloneNotSupportedException e1) {
				JFrame burnedcard=new JFrame("burned card");
				burnedcard.setBounds(808,200,310,430);
				burnedcard.setVisible(true);
				JLabel temp = new JLabel();
				temp.setBorder(BorderFactory.createLineBorder(Color.BLACK));
				temp.setLayout(new GridLayout(4,1));
				if(temp1 instanceof Minion) {
					temp.setLayout(new GridLayout(10,1));
				}
				String s = temp1.getName();
				int mc = temp1.getManaCost();
				Rarity r = temp1.getRarity();
				if(temp1 instanceof Spell) {
					JLabel cardName = new JLabel(s);
					cardName.setFont(new Font("Arial", Font.PLAIN, 21));
					cardName.setForeground(Color.BLUE);
					cardName.setHorizontalAlignment(JLabel.CENTER);
					temp.add(cardName);
					JLabel manacost = new JLabel(mc + " mana cost");
					manacost.setFont(new Font("Arial", Font.PLAIN, 21));
					manacost.setForeground(Color.CYAN);
					manacost.setHorizontalAlignment(JLabel.CENTER);
					temp.add(manacost);
					if(r.equals(Rarity.BASIC)) {
						JLabel tmp = new JLabel(r + "");
						tmp.setHorizontalAlignment(JLabel.CENTER);
						tmp.setFont(new Font("Arial", Font.PLAIN, 21));
						tmp.setForeground(Color.WHITE);
						temp.add(tmp);
					}
					if(r.equals(Rarity.COMMON)) {
						JLabel tmp = new JLabel(r + "");
						tmp.setHorizontalAlignment(JLabel.CENTER);
						tmp.setFont(new Font("Arial", Font.PLAIN, 21));
						tmp.setForeground(Color.lightGray);
						temp.add(tmp);
					}
					if(r.equals(Rarity.EPIC)) {
						JLabel tmp = new JLabel(r + "");
						tmp.setHorizontalAlignment(JLabel.CENTER);
						tmp.setFont(new Font("Arial", Font.PLAIN, 21));
						tmp.setForeground(Color.MAGENTA);
						temp.add(tmp);
					}
					if(r.equals(Rarity.LEGENDARY)) {
						JLabel tmp = new JLabel(r + "");
						tmp.setHorizontalAlignment(JLabel.CENTER);
						tmp.setFont(new Font("Arial", Font.PLAIN, 21));
						tmp.setForeground(Color.ORANGE);
						temp.add(tmp);
					}
					if(r.equals(Rarity.RARE)) {
						JLabel tmp = new JLabel(r + "");
						tmp.setHorizontalAlignment(JLabel.CENTER);
						tmp.setFont(new Font("Arial", Font.PLAIN, 21));
						tmp.setForeground(Color.YELLOW);
						temp.add(tmp);
					}
				}
				else{
					JLabel cardName = new JLabel(s);
					cardName.setFont(new Font("Arial", Font.PLAIN, 21));
					cardName.setForeground(Color.GREEN);
					cardName.setHorizontalAlignment(JLabel.CENTER);
					temp.add(cardName);
					JLabel manacost = new JLabel(mc + " mana cost");
					manacost.setFont(new Font("Arial", Font.PLAIN, 21));
					manacost.setForeground(Color.CYAN);
					manacost.setHorizontalAlignment(JLabel.CENTER);
					temp.add(manacost);
					if(r.equals(Rarity.BASIC)) {
						JLabel tmp = new JLabel(r + "");
						tmp.setHorizontalAlignment(JLabel.CENTER);
						tmp.setFont(new Font("Arial", Font.PLAIN, 21));
						tmp.setForeground(Color.WHITE);
						temp.add(tmp);
					}
					if(r.equals(Rarity.COMMON)) {
						JLabel tmp = new JLabel(r + "");
						tmp.setHorizontalAlignment(JLabel.CENTER);
						tmp.setFont(new Font("Arial", Font.PLAIN, 21));
						tmp.setForeground(Color.lightGray);
						temp.add(tmp);
					}
					if(r.equals(Rarity.EPIC)) {
						JLabel tmp = new JLabel(r + "");
						tmp.setHorizontalAlignment(JLabel.CENTER);
						tmp.setFont(new Font("Arial", Font.PLAIN, 21));
						tmp.setForeground(Color.MAGENTA);
						temp.add(tmp);
					}
					if(r.equals(Rarity.LEGENDARY)) {
						JLabel tmp = new JLabel(r + "");
						tmp.setHorizontalAlignment(JLabel.CENTER);
						tmp.setFont(new Font("Arial", Font.PLAIN, 21));
						tmp.setForeground(Color.ORANGE);
						temp.add(tmp);
					}
					if(r.equals(Rarity.RARE)) {
						JLabel tmp = new JLabel(r + "");
						tmp.setHorizontalAlignment(JLabel.CENTER);
						tmp.setFont(new Font("Arial", Font.PLAIN, 21));
						tmp.setForeground(Color.YELLOW);
						temp.add(tmp);
					}	
					Minion cardminion= (Minion) temp1;
					JLabel attack = new JLabel(cardminion.getAttack() + " damage");
					attack.setHorizontalAlignment(JLabel.CENTER);
					attack.setFont(new Font("Arial", Font.PLAIN, 21));
					attack.setForeground(Color.MAGENTA);
					temp.add(attack);
					JLabel Hp = new JLabel(cardminion.getCurrentHP() + " Hp");
					Hp.setHorizontalAlignment(JLabel.CENTER);
					Hp.setFont(new Font("Arial", Font.PLAIN, 21));
					Hp.setForeground(Color.RED);
					temp.add(Hp);
					if (cardminion.isDivine()) {
						JLabel divine = new JLabel("Has devine sheild");
						divine.setHorizontalAlignment(JLabel.CENTER);
						divine.setFont(new Font("Arial", Font.PLAIN, 21));
						divine.setForeground(Color.lightGray);
						temp.add(divine);
					}
					if (cardminion.isTaunt()) {
						JLabel taunt = new JLabel("Has taunt ability");
						taunt.setHorizontalAlignment(JLabel.CENTER);
						taunt.setFont(new Font("Arial", Font.PLAIN, 21));
						taunt.setForeground(Color.lightGray);
						temp.add(taunt);
					}
					if (!cardminion.isSleeping()) {
						JLabel charge = new JLabel("Charge");
						charge.setHorizontalAlignment(JLabel.CENTER);
						charge.setFont(new Font("Arial", Font.PLAIN, 21));
						charge.setForeground(Color.white);
						temp.add(charge);
					} else {
						JLabel charge = new JLabel("Sleeping");
						charge.setHorizontalAlignment(JLabel.CENTER);
						charge.setFont(new Font("Arial", Font.PLAIN, 21));
						charge.setForeground(Color.white);
						temp.add(charge);
					}
				}
				burnedcard.add(temp);
				if(gamex.getCurrentHero()instanceof Hunter){
					temp.setIcon(new ImageIcon("images/Hunter burnd card.png"));
				}
				if(gamex.getCurrentHero()instanceof Mage){
					temp.setIcon(new ImageIcon("images/mage burnd card.png"));
				}
				if(gamex.getCurrentHero()instanceof Paladin){
					temp.setIcon(new ImageIcon("images/Paladin burnd card.png"));
				}
				if(gamex.getCurrentHero()instanceof Priest){
					temp.setIcon(new ImageIcon("images/priest burnd card.png"));
				}
				if(gamex.getCurrentHero()instanceof Warlock){
					temp.setIcon(new ImageIcon("images/Warlock burnd card.png"));
				}
				JOptionPane.showMessageDialog(null,e1.getMessage(),"Error", JOptionPane.INFORMATION_MESSAGE);
			}
			if(gamex.getCurrentHero().getCurrentHP() != 0 || gamex.getOpponent().getCurrentHP() != 0) {
				window.getContentPane().removeAll();
				window.revalidate();
				window.repaint();
				key++;
				run();

				if(gamex.getCurrentHero()instanceof Hunter){
					curhero.setIcon(new ImageIcon(new ImageIcon("images/Hunter.png").getImage().getScaledInstance(curhero.getWidth(), curhero.getHeight(), Image.SCALE_DEFAULT)));
				}
				if(gamex.getCurrentHero()instanceof Mage){
					curhero.setIcon(new ImageIcon(new ImageIcon("images/mage.png").getImage().getScaledInstance(curhero.getWidth(), curhero.getHeight(), Image.SCALE_DEFAULT)));
				}
				if(gamex.getCurrentHero()instanceof Paladin){
					curhero.setIcon(new ImageIcon(new ImageIcon("images/Paladin.png").getImage().getScaledInstance(curhero.getWidth(), curhero.getHeight(), Image.SCALE_DEFAULT)));
				}
				if(gamex.getCurrentHero()instanceof Priest){
					curhero.setIcon(new ImageIcon(new ImageIcon("images/priest.png").getImage().getScaledInstance(curhero.getWidth(), curhero.getHeight(), Image.SCALE_DEFAULT)));
				}
				if(gamex.getCurrentHero()instanceof Warlock){
					curhero.setIcon(new ImageIcon(new ImageIcon("images/Warlock.png").getImage().getScaledInstance(curhero.getWidth(), curhero.getHeight(), Image.SCALE_DEFAULT)));
				}


				if(gamex.getOpponent()instanceof Hunter){
					opphero.setIcon(new ImageIcon(new ImageIcon("images/Hunter.png").getImage().getScaledInstance(opphero.getWidth(), opphero.getHeight(), Image.SCALE_DEFAULT)));
				}
				if(gamex.getOpponent()instanceof Mage){
					opphero.setIcon(new ImageIcon(new ImageIcon("images/mage.png").getImage().getScaledInstance(opphero.getWidth(), opphero.getHeight(), Image.SCALE_DEFAULT)));
				}
				if(gamex.getOpponent()instanceof Paladin){
					opphero.setIcon(new ImageIcon(new ImageIcon("images/Paladin.png").getImage().getScaledInstance(opphero.getWidth(), opphero.getHeight(), Image.SCALE_DEFAULT)));
				}
				if(gamex.getOpponent()instanceof Priest){
					opphero.setIcon(new ImageIcon(new ImageIcon("images/priest.png").getImage().getScaledInstance(opphero.getWidth(), opphero.getHeight(), Image.SCALE_DEFAULT)));
				}
				if(gamex.getOpponent()instanceof Warlock){
					opphero.setIcon(new ImageIcon(new ImageIcon("images/Warlock.png").getImage().getScaledInstance(opphero.getWidth(), opphero.getHeight(), Image.SCALE_DEFAULT)));
				}
			}
		}
		if(e.getActionCommand().equals("heropower")) {
			if(Oppfield.getSelectedItem().toString().equals(gamex.getOpponent().getName())) {
				Mage m = (Mage) gamex.getCurrentHero();
				try {
					m.useHeroPower(gamex.getOpponent());
				} catch (NotEnoughManaException | HeroPowerAlreadyUsedException | NotYourTurnException
						| FullHandException | FullFieldException | CloneNotSupportedException e1) {
					JOptionPane.showMessageDialog(null,e1.getMessage(),"Error", JOptionPane.INFORMATION_MESSAGE);
				}
			}
			else {
				Mage m = (Mage) gamex.getCurrentHero();
				int t = Oppfield.getSelectedIndex();
				try {
					m.useHeroPower(gamex.getOpponent().getField().get(t));
				} catch (NotEnoughManaException | HeroPowerAlreadyUsedException | NotYourTurnException
						| FullHandException | FullFieldException | CloneNotSupportedException e1) {
					JOptionPane.showMessageDialog(null,e1.getMessage(),"Error", JOptionPane.INFORMATION_MESSAGE);
				}
			}
			window.getContentPane().removeAll();
			window.revalidate();
			window.repaint();
			((JFrame) SwingUtilities.getWindowAncestor(((JButton)e.getSource()))).dispose();
			window.setEnabled(true);
			window.show();
			run();
			if(gamex.getCurrentHero().isHeroPowerUsed()){
				useheropower.setBackground(Color.red);
			}
			if(gamex.getCurrentHero()instanceof Hunter){
				curhero.setIcon(new ImageIcon(new ImageIcon("images/Hunter.png").getImage().getScaledInstance(curhero.getWidth(), curhero.getHeight(), Image.SCALE_DEFAULT)));
			}
			if(gamex.getCurrentHero()instanceof Mage){
				curhero.setIcon(new ImageIcon(new ImageIcon("images/mage.png").getImage().getScaledInstance(curhero.getWidth(), curhero.getHeight(), Image.SCALE_DEFAULT)));
			}
			if(gamex.getCurrentHero()instanceof Paladin){
				curhero.setIcon(new ImageIcon(new ImageIcon("images/Paladin.png").getImage().getScaledInstance(curhero.getWidth(), curhero.getHeight(), Image.SCALE_DEFAULT)));
			}
			if(gamex.getCurrentHero()instanceof Priest){
				curhero.setIcon(new ImageIcon(new ImageIcon("images/priest.png").getImage().getScaledInstance(curhero.getWidth(), curhero.getHeight(), Image.SCALE_DEFAULT)));
			}
			if(gamex.getCurrentHero()instanceof Warlock){
				curhero.setIcon(new ImageIcon(new ImageIcon("images/Warlock.png").getImage().getScaledInstance(curhero.getWidth(), curhero.getHeight(), Image.SCALE_DEFAULT)));
			}


			if(gamex.getOpponent()instanceof Hunter){
				opphero.setIcon(new ImageIcon(new ImageIcon("images/Hunter.png").getImage().getScaledInstance(opphero.getWidth(), opphero.getHeight(), Image.SCALE_DEFAULT)));
			}
			if(gamex.getOpponent()instanceof Mage){
				opphero.setIcon(new ImageIcon(new ImageIcon("images/mage.png").getImage().getScaledInstance(opphero.getWidth(), opphero.getHeight(), Image.SCALE_DEFAULT)));
			}
			if(gamex.getOpponent()instanceof Paladin){
				opphero.setIcon(new ImageIcon(new ImageIcon("images/Paladin.png").getImage().getScaledInstance(opphero.getWidth(), opphero.getHeight(), Image.SCALE_DEFAULT)));
			}
			if(gamex.getOpponent()instanceof Priest){
				opphero.setIcon(new ImageIcon(new ImageIcon("images/priest.png").getImage().getScaledInstance(opphero.getWidth(), opphero.getHeight(), Image.SCALE_DEFAULT)));
			}
			if(gamex.getOpponent()instanceof Warlock){
				opphero.setIcon(new ImageIcon(new ImageIcon("images/Warlock.png").getImage().getScaledInstance(opphero.getWidth(), opphero.getHeight(), Image.SCALE_DEFAULT)));
			}
		}
		if(e.getActionCommand().equals("heropower1")) {
			if(Oppfield.getSelectedItem().toString().equals(gamex.getCurrentHero().getName())) {
				Priest p = (Priest) gamex.getCurrentHero();
				try {
					p.useHeroPower(gamex.getCurrentHero());
				} catch (NotEnoughManaException | HeroPowerAlreadyUsedException | NotYourTurnException
						| FullHandException | FullFieldException | CloneNotSupportedException e1) {
					JOptionPane.showMessageDialog(null,e1.getMessage(),"Error", JOptionPane.INFORMATION_MESSAGE);
				}
			}
			else {
				Priest p = (Priest) gamex.getCurrentHero();
				int t = Oppfield.getSelectedIndex();
				try {
					p.useHeroPower(gamex.getCurrentHero().getField().get(t));
				} catch (NotEnoughManaException | HeroPowerAlreadyUsedException | NotYourTurnException
						| FullHandException | FullFieldException | CloneNotSupportedException e1) {
					JOptionPane.showMessageDialog(null,e1.getMessage(),"Error", JOptionPane.INFORMATION_MESSAGE);
				}
			}
			window.getContentPane().removeAll();
			window.revalidate();
			window.repaint();
			((JFrame) SwingUtilities.getWindowAncestor(((JButton)e.getSource()))).dispose();
			window.setEnabled(true);
			window.show();
			run();
			if(gamex.getCurrentHero().isHeroPowerUsed()){
				useheropower.setBackground(Color.red);
			}
			if(gamex.getCurrentHero()instanceof Hunter){
				curhero.setIcon(new ImageIcon(new ImageIcon("images/Hunter.png").getImage().getScaledInstance(curhero.getWidth(), curhero.getHeight(), Image.SCALE_DEFAULT)));
			}
			if(gamex.getCurrentHero()instanceof Mage){
				curhero.setIcon(new ImageIcon(new ImageIcon("images/mage.png").getImage().getScaledInstance(curhero.getWidth(), curhero.getHeight(), Image.SCALE_DEFAULT)));
			}
			if(gamex.getCurrentHero()instanceof Paladin){
				curhero.setIcon(new ImageIcon(new ImageIcon("images/Paladin.png").getImage().getScaledInstance(curhero.getWidth(), curhero.getHeight(), Image.SCALE_DEFAULT)));
			}
			if(gamex.getCurrentHero()instanceof Priest){
				curhero.setIcon(new ImageIcon(new ImageIcon("images/priest.png").getImage().getScaledInstance(curhero.getWidth(), curhero.getHeight(), Image.SCALE_DEFAULT)));
			}
			if(gamex.getCurrentHero()instanceof Warlock){
				curhero.setIcon(new ImageIcon(new ImageIcon("images/Warlock.png").getImage().getScaledInstance(curhero.getWidth(), curhero.getHeight(), Image.SCALE_DEFAULT)));
			}


			if(gamex.getOpponent()instanceof Hunter){
				opphero.setIcon(new ImageIcon(new ImageIcon("images/Hunter.png").getImage().getScaledInstance(opphero.getWidth(), opphero.getHeight(), Image.SCALE_DEFAULT)));
			}
			if(gamex.getOpponent()instanceof Mage){
				opphero.setIcon(new ImageIcon(new ImageIcon("images/mage.png").getImage().getScaledInstance(opphero.getWidth(), opphero.getHeight(), Image.SCALE_DEFAULT)));
			}
			if(gamex.getOpponent()instanceof Paladin){
				opphero.setIcon(new ImageIcon(new ImageIcon("images/Paladin.png").getImage().getScaledInstance(opphero.getWidth(), opphero.getHeight(), Image.SCALE_DEFAULT)));
			}
			if(gamex.getOpponent()instanceof Priest){
				opphero.setIcon(new ImageIcon(new ImageIcon("images/priest.png").getImage().getScaledInstance(opphero.getWidth(), opphero.getHeight(), Image.SCALE_DEFAULT)));
			}
			if(gamex.getOpponent()instanceof Warlock){
				opphero.setIcon(new ImageIcon(new ImageIcon("images/Warlock.png").getImage().getScaledInstance(opphero.getWidth(), opphero.getHeight(), Image.SCALE_DEFAULT)));
			}
		}
		if(e.getActionCommand().equals("Use Hero Power")){
			if(gamex.getCurrentHero() instanceof Hunter || gamex.getCurrentHero() instanceof Paladin || gamex.getCurrentHero() instanceof Warlock) {
				try {
					gamex.getCurrentHero().useHeroPower();
				} catch (NotEnoughManaException | HeroPowerAlreadyUsedException
						| NotYourTurnException | FullHandException
						| FullFieldException | CloneNotSupportedException e1) {
					JOptionPane.showMessageDialog(null,e1.getMessage(),"Error", JOptionPane.INFORMATION_MESSAGE);
				}
			}
			else {
				if(gamex.getCurrentHero() instanceof Mage) {
					window.setEnabled(false);
					JFrame x1 = new JFrame("Use Hero Power");
					JFrame.setDefaultLookAndFeelDecorated(true);
					x1.setVisible(true);
					x1.addWindowListener(new java.awt.event.WindowAdapter() {
						@Override
						public void windowClosing(java.awt.event.WindowEvent windowEvent) {
							window.setEnabled(true);
							x1.dispose();
						}
					});
					x1.setAlwaysOnTop(true);
					x1.setBounds(0,(int)(screenSize.getHeight() * 0.4),(int) (screenSize.getWidth() * 0.105),(int)(screenSize.getHeight() *0.185));
					Oppfield = new JComboBox<String>();
					JButton heropower = new JButton("Use hero power!");
					JLabel lb = new JLabel("Attack Hero or Minion");
					x1.setLayout(new GridLayout(3,1));
					Oppfield.setSize(100, 50);
					for(int j = 0; j < gamex.getOpponent().getField().size();j++) {
						Oppfield.addItem(gamex.getOpponent().getField().get(j).getName());
					}
					Oppfield.addItem(gamex.getOpponent().getName());
					x1.add(lb);
					x1.add(Oppfield);
					x1.add(heropower);
					heropower.setActionCommand("heropower");
					heropower.addActionListener(this);
				}
				else {
					window.setEnabled(false);
					JFrame x1 = new JFrame("Use Hero Power");
					JFrame.setDefaultLookAndFeelDecorated(true);
					x1.setVisible(true);
					x1.addWindowListener(new java.awt.event.WindowAdapter() {
						@Override
						public void windowClosing(java.awt.event.WindowEvent windowEvent) {
							window.setEnabled(true);
							x1.dispose();
						}
					});
					x1.setAlwaysOnTop(true);
					x1.setBounds(0,(int)(screenSize.getHeight() * 0.4),(int) (screenSize.getWidth() * 0.105),(int)(screenSize.getHeight() *0.185));
					Oppfield = new JComboBox<String>();
					JButton heropower = new JButton("Use hero power!");
					JLabel lb = new JLabel("Attack Hero or Minion");
					x1.setLayout(new GridLayout(3,1));
					Oppfield.setSize(100, 50);
					for(int j = 0; j < gamex.getCurrentHero().getField().size();j++) {
						Oppfield.addItem(gamex.getCurrentHero().getField().get(j).getName());
					}
					Oppfield.addItem(gamex.getCurrentHero().getName());
					x1.add(lb);
					x1.add(Oppfield);
					x1.add(heropower);
					heropower.setActionCommand("heropower1");
					heropower.addActionListener(this);
				}
			}
			window.revalidate();
			window.repaint();
			window.getContentPane().removeAll();
			run();
			if(gamex.getCurrentHero().isHeroPowerUsed()){
				useheropower.setBackground(Color.red);
			}
			if(gamex.getCurrentHero()instanceof Hunter){
				curhero.setIcon(new ImageIcon(new ImageIcon("images/Hunter.png").getImage().getScaledInstance(curhero.getWidth(), curhero.getHeight(), Image.SCALE_DEFAULT)));
			}
			if(gamex.getCurrentHero()instanceof Mage){
				curhero.setIcon(new ImageIcon(new ImageIcon("images/mage.png").getImage().getScaledInstance(curhero.getWidth(), curhero.getHeight(), Image.SCALE_DEFAULT)));
			}
			if(gamex.getCurrentHero()instanceof Paladin){
				curhero.setIcon(new ImageIcon(new ImageIcon("images/Paladin.png").getImage().getScaledInstance(curhero.getWidth(), curhero.getHeight(), Image.SCALE_DEFAULT)));
			}
			if(gamex.getCurrentHero()instanceof Priest){
				curhero.setIcon(new ImageIcon(new ImageIcon("images/priest.png").getImage().getScaledInstance(curhero.getWidth(), curhero.getHeight(), Image.SCALE_DEFAULT)));
			}
			if(gamex.getCurrentHero()instanceof Warlock){
				curhero.setIcon(new ImageIcon(new ImageIcon("images/Warlock.png").getImage().getScaledInstance(curhero.getWidth(), curhero.getHeight(), Image.SCALE_DEFAULT)));
			}


			if(gamex.getOpponent()instanceof Hunter){
				opphero.setIcon(new ImageIcon(new ImageIcon("images/Hunter.png").getImage().getScaledInstance(opphero.getWidth(), opphero.getHeight(), Image.SCALE_DEFAULT)));
			}
			if(gamex.getOpponent()instanceof Mage){
				opphero.setIcon(new ImageIcon(new ImageIcon("images/mage.png").getImage().getScaledInstance(opphero.getWidth(), opphero.getHeight(), Image.SCALE_DEFAULT)));
			}
			if(gamex.getOpponent()instanceof Paladin){
				opphero.setIcon(new ImageIcon(new ImageIcon("images/Paladin.png").getImage().getScaledInstance(opphero.getWidth(), opphero.getHeight(), Image.SCALE_DEFAULT)));
			}
			if(gamex.getOpponent()instanceof Priest){
				opphero.setIcon(new ImageIcon(new ImageIcon("images/priest.png").getImage().getScaledInstance(opphero.getWidth(), opphero.getHeight(), Image.SCALE_DEFAULT)));
			}
			if(gamex.getOpponent()instanceof Warlock){
				opphero.setIcon(new ImageIcon(new ImageIcon("images/Warlock.png").getImage().getScaledInstance(opphero.getWidth(), opphero.getHeight(), Image.SCALE_DEFAULT)));
			}
		}
		if(e.getActionCommand().startsWith("play minion ")) {
			int x = Integer.parseInt(e.getActionCommand().charAt(e.getActionCommand().length()-1) + "");
			Minion tmp = (Minion) gamex.getCurrentHero().getHand().get(x);
			try {
				gamex.getCurrentHero().playMinion(tmp);
			} catch (NotYourTurnException | NotEnoughManaException | FullFieldException e1) {
				JOptionPane.showMessageDialog(null,e1.getMessage(),"Error", JOptionPane.INFORMATION_MESSAGE);
			}
			window.getContentPane().removeAll();
			window.revalidate();
			window.repaint();
			run();
			if(gamex.getCurrentHero().isHeroPowerUsed()){
				useheropower.setBackground(Color.red);
			}
			if(gamex.getCurrentHero()instanceof Hunter){
				curhero.setIcon(new ImageIcon(new ImageIcon("images/Hunter.png").getImage().getScaledInstance(curhero.getWidth(), curhero.getHeight(), Image.SCALE_DEFAULT)));
			}
			if(gamex.getCurrentHero()instanceof Mage){
				curhero.setIcon(new ImageIcon(new ImageIcon("images/mage.png").getImage().getScaledInstance(curhero.getWidth(), curhero.getHeight(), Image.SCALE_DEFAULT)));
			}
			if(gamex.getCurrentHero()instanceof Paladin){
				curhero.setIcon(new ImageIcon(new ImageIcon("images/Paladin.png").getImage().getScaledInstance(curhero.getWidth(), curhero.getHeight(), Image.SCALE_DEFAULT)));
			}
			if(gamex.getCurrentHero()instanceof Priest){
				curhero.setIcon(new ImageIcon(new ImageIcon("images/priest.png").getImage().getScaledInstance(curhero.getWidth(), curhero.getHeight(), Image.SCALE_DEFAULT)));
			}
			if(gamex.getCurrentHero()instanceof Warlock){
				curhero.setIcon(new ImageIcon(new ImageIcon("images/Warlock.png").getImage().getScaledInstance(curhero.getWidth(), curhero.getHeight(), Image.SCALE_DEFAULT)));
			}


			if(gamex.getOpponent()instanceof Hunter){
				opphero.setIcon(new ImageIcon(new ImageIcon("images/Hunter.png").getImage().getScaledInstance(opphero.getWidth(), opphero.getHeight(), Image.SCALE_DEFAULT)));
			}
			if(gamex.getOpponent()instanceof Mage){
				opphero.setIcon(new ImageIcon(new ImageIcon("images/mage.png").getImage().getScaledInstance(opphero.getWidth(), opphero.getHeight(), Image.SCALE_DEFAULT)));
			}
			if(gamex.getOpponent()instanceof Paladin){
				opphero.setIcon(new ImageIcon(new ImageIcon("images/Paladin.png").getImage().getScaledInstance(opphero.getWidth(), opphero.getHeight(), Image.SCALE_DEFAULT)));
			}
			if(gamex.getOpponent()instanceof Priest){
				opphero.setIcon(new ImageIcon(new ImageIcon("images/priest.png").getImage().getScaledInstance(opphero.getWidth(), opphero.getHeight(), Image.SCALE_DEFAULT)));
			}
			if(gamex.getOpponent()instanceof Warlock){
				opphero.setIcon(new ImageIcon(new ImageIcon("images/Warlock.png").getImage().getScaledInstance(opphero.getWidth(), opphero.getHeight(), Image.SCALE_DEFAULT)));
			}
		}
		if(e.getActionCommand().startsWith("Cast Spell ")) {
			int x = Integer.parseInt(e.getActionCommand().charAt(e.getActionCommand().length()-1) + "");
			tmpSpell = (Spell) gamex.getCurrentHero().getHand().get(x);
			if(tmpSpell instanceof LevelUp) {
				try {
					gamex.getCurrentHero().castSpell((LevelUp)tmpSpell);
				} catch (NotYourTurnException | NotEnoughManaException e1) {
					JOptionPane.showMessageDialog(null,e1.getMessage(),"Error", JOptionPane.INFORMATION_MESSAGE);
				}
				window.getContentPane().removeAll();
				window.revalidate();
				window.repaint();
				run();
				if(gamex.getCurrentHero().isHeroPowerUsed()){
					useheropower.setBackground(Color.red);
				}
				if(gamex.getCurrentHero()instanceof Hunter){
					curhero.setIcon(new ImageIcon(new ImageIcon("images/Hunter.png").getImage().getScaledInstance(curhero.getWidth(), curhero.getHeight(), Image.SCALE_DEFAULT)));
				}
				if(gamex.getCurrentHero()instanceof Mage){
					curhero.setIcon(new ImageIcon(new ImageIcon("images/mage.png").getImage().getScaledInstance(curhero.getWidth(), curhero.getHeight(), Image.SCALE_DEFAULT)));
				}
				if(gamex.getCurrentHero()instanceof Paladin){
					curhero.setIcon(new ImageIcon(new ImageIcon("images/Paladin.png").getImage().getScaledInstance(curhero.getWidth(), curhero.getHeight(), Image.SCALE_DEFAULT)));
				}
				if(gamex.getCurrentHero()instanceof Priest){
					curhero.setIcon(new ImageIcon(new ImageIcon("images/priest.png").getImage().getScaledInstance(curhero.getWidth(), curhero.getHeight(), Image.SCALE_DEFAULT)));
				}
				if(gamex.getCurrentHero()instanceof Warlock){
					curhero.setIcon(new ImageIcon(new ImageIcon("images/Warlock.png").getImage().getScaledInstance(curhero.getWidth(), curhero.getHeight(), Image.SCALE_DEFAULT)));
				}


				if(gamex.getOpponent()instanceof Hunter){
					opphero.setIcon(new ImageIcon(new ImageIcon("images/Hunter.png").getImage().getScaledInstance(opphero.getWidth(), opphero.getHeight(), Image.SCALE_DEFAULT)));
				}
				if(gamex.getOpponent()instanceof Mage){
					opphero.setIcon(new ImageIcon(new ImageIcon("images/mage.png").getImage().getScaledInstance(opphero.getWidth(), opphero.getHeight(), Image.SCALE_DEFAULT)));
				}
				if(gamex.getOpponent()instanceof Paladin){
					opphero.setIcon(new ImageIcon(new ImageIcon("images/Paladin.png").getImage().getScaledInstance(opphero.getWidth(), opphero.getHeight(), Image.SCALE_DEFAULT)));
				}
				if(gamex.getOpponent()instanceof Priest){
					opphero.setIcon(new ImageIcon(new ImageIcon("images/priest.png").getImage().getScaledInstance(opphero.getWidth(), opphero.getHeight(), Image.SCALE_DEFAULT)));
				}
				if(gamex.getOpponent()instanceof Warlock){
					opphero.setIcon(new ImageIcon(new ImageIcon("images/Warlock.png").getImage().getScaledInstance(opphero.getWidth(), opphero.getHeight(), Image.SCALE_DEFAULT)));
				}
			}
			if(tmpSpell instanceof SiphonSoul) {
				window.setEnabled(false);
				JFrame x1 = new JFrame("Cast Spell");
				JFrame.setDefaultLookAndFeelDecorated(true);
				x1.setVisible(true);
				x1.addWindowListener(new java.awt.event.WindowAdapter() {
					@Override
					public void windowClosing(java.awt.event.WindowEvent windowEvent) {
						window.setEnabled(true);
						x1.dispose();
						x1.setAlwaysOnTop(true);
					}
				});
				x1.setAlwaysOnTop(true);
				x1.setBounds(0,(int)(screenSize.getHeight() * 0.4),(int) (screenSize.getWidth() * 0.105),(int)(screenSize.getHeight() *0.185));
				oppMinion = new JComboBox<String>();
				JButton castspell = new JButton("Cast the Spell");
				JLabel lb = new JLabel("Cast a Spell to a Minion");
				x1.setLayout(new GridLayout(3,1));
				oppMinion.setSize(100, 50);
				for(int j = 0; j < gamex.getOpponent().getField().size();j++) {
					oppMinion.addItem(gamex.getOpponent().getField().get(j).getName());
				}
				castspell.setActionCommand("cast1");
				castspell.addActionListener(this);
				x1.add(lb);
				x1.add(oppMinion);
				x1.add(castspell);
			}
			if(tmpSpell instanceof CurseOfWeakness || tmpSpell instanceof Flamestrike  || tmpSpell instanceof HolyNova ||tmpSpell instanceof MultiShot || tmpSpell instanceof TwistingNether) {
				try {
					gamex.getCurrentHero().castSpell((AOESpell)tmpSpell, gamex.getOpponent().getField());
				} catch (NotYourTurnException | NotEnoughManaException e1) {
					JOptionPane.showMessageDialog(null,e1.getMessage(),"Error", JOptionPane.INFORMATION_MESSAGE);
				}
				window.getContentPane().removeAll();
				window.revalidate();
				window.repaint();
				run();
				if(gamex.getCurrentHero().isHeroPowerUsed()){
					useheropower.setBackground(Color.red);
				}
				if(gamex.getCurrentHero().isHeroPowerUsed()){
					useheropower.setBackground(Color.red);
				}
				if(gamex.getCurrentHero()instanceof Hunter){
					curhero.setIcon(new ImageIcon(new ImageIcon("images/Hunter.png").getImage().getScaledInstance(curhero.getWidth(), curhero.getHeight(), Image.SCALE_DEFAULT)));
				}
				if(gamex.getCurrentHero()instanceof Mage){
					curhero.setIcon(new ImageIcon(new ImageIcon("images/mage.png").getImage().getScaledInstance(curhero.getWidth(), curhero.getHeight(), Image.SCALE_DEFAULT)));
				}
				if(gamex.getCurrentHero()instanceof Paladin){
					curhero.setIcon(new ImageIcon(new ImageIcon("images/Paladin.png").getImage().getScaledInstance(curhero.getWidth(), curhero.getHeight(), Image.SCALE_DEFAULT)));
				}
				if(gamex.getCurrentHero()instanceof Priest){
					curhero.setIcon(new ImageIcon(new ImageIcon("images/priest.png").getImage().getScaledInstance(curhero.getWidth(), curhero.getHeight(), Image.SCALE_DEFAULT)));
				}
				if(gamex.getCurrentHero()instanceof Warlock){
					curhero.setIcon(new ImageIcon(new ImageIcon("images/Warlock.png").getImage().getScaledInstance(curhero.getWidth(), curhero.getHeight(), Image.SCALE_DEFAULT)));
				}


				if(gamex.getOpponent()instanceof Hunter){
					opphero.setIcon(new ImageIcon(new ImageIcon("images/Hunter.png").getImage().getScaledInstance(opphero.getWidth(), opphero.getHeight(), Image.SCALE_DEFAULT)));
				}
				if(gamex.getOpponent()instanceof Mage){
					opphero.setIcon(new ImageIcon(new ImageIcon("images/mage.png").getImage().getScaledInstance(opphero.getWidth(), opphero.getHeight(), Image.SCALE_DEFAULT)));
				}
				if(gamex.getOpponent()instanceof Paladin){
					opphero.setIcon(new ImageIcon(new ImageIcon("images/Paladin.png").getImage().getScaledInstance(opphero.getWidth(), opphero.getHeight(), Image.SCALE_DEFAULT)));
				}
				if(gamex.getOpponent()instanceof Priest){
					opphero.setIcon(new ImageIcon(new ImageIcon("images/priest.png").getImage().getScaledInstance(opphero.getWidth(), opphero.getHeight(), Image.SCALE_DEFAULT)));
				}
				if(gamex.getOpponent()instanceof Warlock){
					opphero.setIcon(new ImageIcon(new ImageIcon("images/Warlock.png").getImage().getScaledInstance(opphero.getWidth(), opphero.getHeight(), Image.SCALE_DEFAULT)));
				}
			}
			if(tmpSpell instanceof ShadowWordDeath) {
				window.setEnabled(false);
				JFrame x1 = new JFrame("Cast Spell");
				JFrame.setDefaultLookAndFeelDecorated(true);
				x1.setVisible(true);
				x1.addWindowListener(new java.awt.event.WindowAdapter() {
					@Override
					public void windowClosing(java.awt.event.WindowEvent windowEvent) {
						window.setEnabled(true);
						x1.dispose();
						x1.setAlwaysOnTop(true);
					}
				});
				x1.setAlwaysOnTop(true);
				x1.setBounds(0,(int)(screenSize.getHeight() * 0.4),(int) (screenSize.getWidth() * 0.105),(int)(screenSize.getHeight() *0.185));
				curMinion2 = new JComboBox<String>();
				JButton castspell = new JButton("Cast the Spell");
				JLabel lb = new JLabel("Cast a Spell to a Minion");
				x1.setLayout(new GridLayout(3,1));
				curMinion2.setSize(100, 50);
				for(int j = 0; j < gamex.getOpponent().getField().size();j++) {
					curMinion2.addItem(gamex.getOpponent().getField().get(j).getName());
				}
				castspell.setActionCommand("cast3");
				castspell.addActionListener(this);
				x1.add(lb);
				x1.add(curMinion2);
				x1.add(castspell);
			}
			if(tmpSpell instanceof Polymorph) {
				window.setEnabled(false);
				JFrame x1 = new JFrame("Cast Spell");
				JFrame.setDefaultLookAndFeelDecorated(true);
				x1.setVisible(true);
				x1.addWindowListener(new java.awt.event.WindowAdapter() {
					@Override
					public void windowClosing(java.awt.event.WindowEvent windowEvent) {
						window.setEnabled(true);
						x1.dispose();
						x1.setAlwaysOnTop(true);
					}
				});
				x1.setAlwaysOnTop(true);
				x1.setBounds(0,(int)(screenSize.getHeight() * 0.4),(int) (screenSize.getWidth() * 0.105),(int)(screenSize.getHeight() *0.185));
				curMinion = new JComboBox<String>();
				JButton castspell = new JButton("Cast the Spell");
				JLabel lb = new JLabel("Cast a Spell to a Minion");
				x1.setLayout(new GridLayout(3,1));
				curMinion.setSize(100, 50);
				for(int j = 0; j < gamex.getCurrentHero().getField().size();j++) {
					curMinion.addItem(gamex.getCurrentHero().getField().get(j).getName());
				}
				for(int j = 0; j < gamex.getOpponent().getField().size();j++) {
					curMinion.addItem(gamex.getOpponent().getField().get(j).getName());
				}
				castspell.setActionCommand("cast4");
				castspell.addActionListener(this);
				x1.add(lb);
				x1.add(curMinion);
				x1.add(castspell);
			}
			if(tmpSpell instanceof DivineSpirit|| tmpSpell instanceof SealOfChampions) {
				window.setEnabled(false);
				JFrame x1 = new JFrame("Cast Spell");
				JFrame.setDefaultLookAndFeelDecorated(true);
				x1.setVisible(true);
				x1.addWindowListener(new java.awt.event.WindowAdapter() {
					@Override
					public void windowClosing(java.awt.event.WindowEvent windowEvent) {
						window.setEnabled(true);
						x1.dispose();
						x1.setAlwaysOnTop(true);
					}
				});
				x1.setAlwaysOnTop(true);
				x1.setBounds(0,(int)(screenSize.getHeight() * 0.4),(int) (screenSize.getWidth() * 0.105),(int)(screenSize.getHeight() *0.185));
				curMinion = new JComboBox<String>();
				JButton castspell = new JButton("Cast the Spell");
				JLabel lb = new JLabel("Cast a Spell to a Minion");
				x1.setLayout(new GridLayout(3,1));
				curMinion.setSize(100, 50);
				for(int j = 0; j < gamex.getCurrentHero().getField().size();j++) {
					curMinion.addItem(gamex.getCurrentHero().getField().get(j).getName());
				}
				castspell.setActionCommand("cast");
				castspell.addActionListener(this);
				x1.add(lb);
				x1.add(curMinion);
				x1.add(castspell);
			}
			if(tmpSpell instanceof KillCommand || tmpSpell instanceof Pyroblast) {
				window.setEnabled(false);
				JFrame x1 = new JFrame("Cast Spell");
				JFrame.setDefaultLookAndFeelDecorated(true);
				x1.setVisible(true);
				x1.addWindowListener(new java.awt.event.WindowAdapter() {
					@Override
					public void windowClosing(java.awt.event.WindowEvent windowEvent) {
						window.setEnabled(true);
						x1.dispose();
						x1.setAlwaysOnTop(true);
					}
				});
				x1.setAlwaysOnTop(true);
				x1.setBounds(0,(int)(screenSize.getHeight() * 0.4),(int) (screenSize.getWidth() * 0.105),(int)(screenSize.getHeight() *0.185));
				oppMinionwithhero = new JComboBox<String>();
				JButton castspell = new JButton("Cast the Spell");
				JLabel lb = new JLabel("Cast a Spell to a Minion");
				x1.setLayout(new GridLayout(3,1));
				oppMinionwithhero.setSize(100, 50);
				for(int j = 0; j < gamex.getOpponent().getField().size();j++) {
					oppMinionwithhero.addItem(gamex.getOpponent().getField().get(j).getName());
				}
				oppMinionwithhero.addItem(gamex.getOpponent().getName());
				castspell.setActionCommand("cast2");
				castspell.addActionListener(this);
				x1.add(lb);
				x1.add(oppMinionwithhero);
				x1.add(castspell);
			}
		}
		if(e.getActionCommand().equals("cast")) {
			try {
				gamex.getCurrentHero().castSpell((MinionTargetSpell) tmpSpell , gamex.getCurrentHero().getField().get(curMinion.getSelectedIndex()));
			} catch (NotYourTurnException | NotEnoughManaException | InvalidTargetException e1) {
				JOptionPane.showMessageDialog(null,e1.getMessage(),"Error", JOptionPane.INFORMATION_MESSAGE);
			}
			window.getContentPane().removeAll();
			window.revalidate();
			window.repaint();
			((JFrame) SwingUtilities.getWindowAncestor(((JButton)e.getSource()))).dispose();
			window.setEnabled(true);
			window.show();
			run();
			if(gamex.getCurrentHero().isHeroPowerUsed()){
				useheropower.setBackground(Color.red);
			}
			if(gamex.getCurrentHero()instanceof Hunter){
				curhero.setIcon(new ImageIcon(new ImageIcon("images/Hunter.png").getImage().getScaledInstance(curhero.getWidth(), curhero.getHeight(), Image.SCALE_DEFAULT)));
			}
			if(gamex.getCurrentHero()instanceof Mage){
				curhero.setIcon(new ImageIcon(new ImageIcon("images/mage.png").getImage().getScaledInstance(curhero.getWidth(), curhero.getHeight(), Image.SCALE_DEFAULT)));
			}
			if(gamex.getCurrentHero()instanceof Paladin){
				curhero.setIcon(new ImageIcon(new ImageIcon("images/Paladin.png").getImage().getScaledInstance(curhero.getWidth(), curhero.getHeight(), Image.SCALE_DEFAULT)));
			}
			if(gamex.getCurrentHero()instanceof Priest){
				curhero.setIcon(new ImageIcon(new ImageIcon("images/priest.png").getImage().getScaledInstance(curhero.getWidth(), curhero.getHeight(), Image.SCALE_DEFAULT)));
			}
			if(gamex.getCurrentHero()instanceof Warlock){
				curhero.setIcon(new ImageIcon(new ImageIcon("images/Warlock.png").getImage().getScaledInstance(curhero.getWidth(), curhero.getHeight(), Image.SCALE_DEFAULT)));
			}


			if(gamex.getOpponent()instanceof Hunter){
				opphero.setIcon(new ImageIcon(new ImageIcon("images/Hunter.png").getImage().getScaledInstance(opphero.getWidth(), opphero.getHeight(), Image.SCALE_DEFAULT)));
			}
			if(gamex.getOpponent()instanceof Mage){
				opphero.setIcon(new ImageIcon(new ImageIcon("images/mage.png").getImage().getScaledInstance(opphero.getWidth(), opphero.getHeight(), Image.SCALE_DEFAULT)));
			}
			if(gamex.getOpponent()instanceof Paladin){
				opphero.setIcon(new ImageIcon(new ImageIcon("images/Paladin.png").getImage().getScaledInstance(opphero.getWidth(), opphero.getHeight(), Image.SCALE_DEFAULT)));
			}
			if(gamex.getOpponent()instanceof Priest){
				opphero.setIcon(new ImageIcon(new ImageIcon("images/priest.png").getImage().getScaledInstance(opphero.getWidth(), opphero.getHeight(), Image.SCALE_DEFAULT)));
			}
			if(gamex.getOpponent()instanceof Warlock){
				opphero.setIcon(new ImageIcon(new ImageIcon("images/Warlock.png").getImage().getScaledInstance(opphero.getWidth(), opphero.getHeight(), Image.SCALE_DEFAULT)));
			}
		}
		if(e.getActionCommand().equals("cast1")) {
			try {
				gamex.getCurrentHero().castSpell((LeechingSpell) tmpSpell , gamex.getOpponent().getField().get(oppMinion.getSelectedIndex()));
			} catch (NotYourTurnException | NotEnoughManaException e1) {
				JOptionPane.showMessageDialog(null,e1.getMessage(),"Error", JOptionPane.INFORMATION_MESSAGE);
			}
			window.getContentPane().removeAll();
			window.revalidate();
			window.repaint();
			((JFrame) SwingUtilities.getWindowAncestor(((JButton)e.getSource()))).dispose();
			window.setEnabled(true);
			window.show();
			run();
			if(gamex.getCurrentHero().isHeroPowerUsed()){
				useheropower.setBackground(Color.red);
			}
			if(gamex.getCurrentHero()instanceof Hunter){
				curhero.setIcon(new ImageIcon(new ImageIcon("images/Hunter.png").getImage().getScaledInstance(curhero.getWidth(), curhero.getHeight(), Image.SCALE_DEFAULT)));
			}
			if(gamex.getCurrentHero()instanceof Mage){
				curhero.setIcon(new ImageIcon(new ImageIcon("images/mage.png").getImage().getScaledInstance(curhero.getWidth(), curhero.getHeight(), Image.SCALE_DEFAULT)));
			}
			if(gamex.getCurrentHero()instanceof Paladin){
				curhero.setIcon(new ImageIcon(new ImageIcon("images/Paladin.png").getImage().getScaledInstance(curhero.getWidth(), curhero.getHeight(), Image.SCALE_DEFAULT)));
			}
			if(gamex.getCurrentHero()instanceof Priest){
				curhero.setIcon(new ImageIcon(new ImageIcon("images/priest.png").getImage().getScaledInstance(curhero.getWidth(), curhero.getHeight(), Image.SCALE_DEFAULT)));
			}
			if(gamex.getCurrentHero()instanceof Warlock){
				curhero.setIcon(new ImageIcon(new ImageIcon("images/Warlock.png").getImage().getScaledInstance(curhero.getWidth(), curhero.getHeight(), Image.SCALE_DEFAULT)));
			}


			if(gamex.getOpponent()instanceof Hunter){
				opphero.setIcon(new ImageIcon(new ImageIcon("images/Hunter.png").getImage().getScaledInstance(opphero.getWidth(), opphero.getHeight(), Image.SCALE_DEFAULT)));
			}
			if(gamex.getOpponent()instanceof Mage){
				opphero.setIcon(new ImageIcon(new ImageIcon("images/mage.png").getImage().getScaledInstance(opphero.getWidth(), opphero.getHeight(), Image.SCALE_DEFAULT)));
			}
			if(gamex.getOpponent()instanceof Paladin){
				opphero.setIcon(new ImageIcon(new ImageIcon("images/Paladin.png").getImage().getScaledInstance(opphero.getWidth(), opphero.getHeight(), Image.SCALE_DEFAULT)));
			}
			if(gamex.getOpponent()instanceof Priest){
				opphero.setIcon(new ImageIcon(new ImageIcon("images/priest.png").getImage().getScaledInstance(opphero.getWidth(), opphero.getHeight(), Image.SCALE_DEFAULT)));
			}
			if(gamex.getOpponent()instanceof Warlock){
				opphero.setIcon(new ImageIcon(new ImageIcon("images/Warlock.png").getImage().getScaledInstance(opphero.getWidth(), opphero.getHeight(), Image.SCALE_DEFAULT)));
			}
		}
		if(e.getActionCommand().equals("cast3")){

			try {
				gamex.getCurrentHero().castSpell((ShadowWordDeath) tmpSpell , gamex.getOpponent().getField().get(curMinion2.getSelectedIndex()));
			} catch (NotYourTurnException | NotEnoughManaException | InvalidTargetException e1) {
				JOptionPane.showMessageDialog(null,e1.getMessage(),"Error", JOptionPane.INFORMATION_MESSAGE);
			}

			window.getContentPane().removeAll();
			window.revalidate();
			window.repaint();
			((JFrame) SwingUtilities.getWindowAncestor(((JButton)e.getSource()))).dispose();
			window.setEnabled(true);
			window.show();
			run();
			if(gamex.getCurrentHero().isHeroPowerUsed()){
				useheropower.setBackground(Color.red);
			}
			if(gamex.getCurrentHero()instanceof Hunter){
				curhero.setIcon(new ImageIcon(new ImageIcon("images/Hunter.png").getImage().getScaledInstance(curhero.getWidth(), curhero.getHeight(), Image.SCALE_DEFAULT)));
			}
			if(gamex.getCurrentHero()instanceof Mage){
				curhero.setIcon(new ImageIcon(new ImageIcon("images/mage.png").getImage().getScaledInstance(curhero.getWidth(), curhero.getHeight(), Image.SCALE_DEFAULT)));
			}
			if(gamex.getCurrentHero()instanceof Paladin){
				curhero.setIcon(new ImageIcon(new ImageIcon("images/Paladin.png").getImage().getScaledInstance(curhero.getWidth(), curhero.getHeight(), Image.SCALE_DEFAULT)));
			}
			if(gamex.getCurrentHero()instanceof Priest){
				curhero.setIcon(new ImageIcon(new ImageIcon("images/priest.png").getImage().getScaledInstance(curhero.getWidth(), curhero.getHeight(), Image.SCALE_DEFAULT)));
			}
			if(gamex.getCurrentHero()instanceof Warlock){
				curhero.setIcon(new ImageIcon(new ImageIcon("images/Warlock.png").getImage().getScaledInstance(curhero.getWidth(), curhero.getHeight(), Image.SCALE_DEFAULT)));
			}


			if(gamex.getOpponent()instanceof Hunter){
				opphero.setIcon(new ImageIcon(new ImageIcon("images/Hunter.png").getImage().getScaledInstance(opphero.getWidth(), opphero.getHeight(), Image.SCALE_DEFAULT)));
			}
			if(gamex.getOpponent()instanceof Mage){
				opphero.setIcon(new ImageIcon(new ImageIcon("images/mage.png").getImage().getScaledInstance(opphero.getWidth(), opphero.getHeight(), Image.SCALE_DEFAULT)));
			}
			if(gamex.getOpponent()instanceof Paladin){
				opphero.setIcon(new ImageIcon(new ImageIcon("images/Paladin.png").getImage().getScaledInstance(opphero.getWidth(), opphero.getHeight(), Image.SCALE_DEFAULT)));
			}
			if(gamex.getOpponent()instanceof Priest){
				opphero.setIcon(new ImageIcon(new ImageIcon("images/priest.png").getImage().getScaledInstance(opphero.getWidth(), opphero.getHeight(), Image.SCALE_DEFAULT)));
			}
			if(gamex.getOpponent()instanceof Warlock){
				opphero.setIcon(new ImageIcon(new ImageIcon("images/Warlock.png").getImage().getScaledInstance(opphero.getWidth(), opphero.getHeight(), Image.SCALE_DEFAULT)));
			}
		}
		if(e.getActionCommand().equals("cast4")) {
			if(curMinion.getSelectedIndex() < gamex.getCurrentHero().getField().size()) {
				try {
					gamex.getCurrentHero().castSpell((MinionTargetSpell) tmpSpell, gamex.getCurrentHero().getField().get(curMinion.getSelectedIndex()));
				} catch (NotYourTurnException | NotEnoughManaException | InvalidTargetException e1) {
					JOptionPane.showMessageDialog(null,e1.getMessage(),"Error", JOptionPane.INFORMATION_MESSAGE);
				}
				window.getContentPane().removeAll();
				window.revalidate();
				window.repaint();
				((JFrame) SwingUtilities.getWindowAncestor(((JButton)e.getSource()))).dispose();
				window.setEnabled(true);
				window.show();
				run();
				if(gamex.getCurrentHero().isHeroPowerUsed()){
					useheropower.setBackground(Color.red);
				}
				if(gamex.getCurrentHero()instanceof Hunter){
					curhero.setIcon(new ImageIcon(new ImageIcon("images/Hunter.png").getImage().getScaledInstance(curhero.getWidth(), curhero.getHeight(), Image.SCALE_DEFAULT)));
				}
				if(gamex.getCurrentHero()instanceof Mage){
					curhero.setIcon(new ImageIcon(new ImageIcon("images/mage.png").getImage().getScaledInstance(curhero.getWidth(), curhero.getHeight(), Image.SCALE_DEFAULT)));
				}
				if(gamex.getCurrentHero()instanceof Paladin){
					curhero.setIcon(new ImageIcon(new ImageIcon("images/Paladin.png").getImage().getScaledInstance(curhero.getWidth(), curhero.getHeight(), Image.SCALE_DEFAULT)));
				}
				if(gamex.getCurrentHero()instanceof Priest){
					curhero.setIcon(new ImageIcon(new ImageIcon("images/priest.png").getImage().getScaledInstance(curhero.getWidth(), curhero.getHeight(), Image.SCALE_DEFAULT)));
				}
				if(gamex.getCurrentHero()instanceof Warlock){
					curhero.setIcon(new ImageIcon(new ImageIcon("images/Warlock.png").getImage().getScaledInstance(curhero.getWidth(), curhero.getHeight(), Image.SCALE_DEFAULT)));
				}


				if(gamex.getOpponent()instanceof Hunter){
					opphero.setIcon(new ImageIcon(new ImageIcon("images/Hunter.png").getImage().getScaledInstance(opphero.getWidth(), opphero.getHeight(), Image.SCALE_DEFAULT)));
				}
				if(gamex.getOpponent()instanceof Mage){
					opphero.setIcon(new ImageIcon(new ImageIcon("images/mage.png").getImage().getScaledInstance(opphero.getWidth(), opphero.getHeight(), Image.SCALE_DEFAULT)));
				}
				if(gamex.getOpponent()instanceof Paladin){
					opphero.setIcon(new ImageIcon(new ImageIcon("images/Paladin.png").getImage().getScaledInstance(opphero.getWidth(), opphero.getHeight(), Image.SCALE_DEFAULT)));
				}
				if(gamex.getOpponent()instanceof Priest){
					opphero.setIcon(new ImageIcon(new ImageIcon("images/priest.png").getImage().getScaledInstance(opphero.getWidth(), opphero.getHeight(), Image.SCALE_DEFAULT)));
				}
				if(gamex.getOpponent()instanceof Warlock){
					opphero.setIcon(new ImageIcon(new ImageIcon("images/Warlock.png").getImage().getScaledInstance(opphero.getWidth(), opphero.getHeight(), Image.SCALE_DEFAULT)));
				}
			}
			else {
				try {
					gamex.getCurrentHero().castSpell((MinionTargetSpell) tmpSpell, gamex.getOpponent().getField().get(curMinion.getSelectedIndex()-gamex.getCurrentHero().getField().size()));
				} catch (NotYourTurnException | NotEnoughManaException | InvalidTargetException e1) {
					JOptionPane.showMessageDialog(null,e1.getMessage(),"Error", JOptionPane.INFORMATION_MESSAGE);
				}
				window.getContentPane().removeAll();
				window.revalidate();
				window.repaint();
				((JFrame) SwingUtilities.getWindowAncestor(((JButton)e.getSource()))).dispose();
				window.setEnabled(true);
				window.show();
				run();
				if(gamex.getCurrentHero().isHeroPowerUsed()){
					useheropower.setBackground(Color.red);
				}
				if(gamex.getCurrentHero()instanceof Hunter){
					curhero.setIcon(new ImageIcon(new ImageIcon("images/Hunter.png").getImage().getScaledInstance(curhero.getWidth(), curhero.getHeight(), Image.SCALE_DEFAULT)));
				}
				if(gamex.getCurrentHero()instanceof Mage){
					curhero.setIcon(new ImageIcon(new ImageIcon("images/mage.png").getImage().getScaledInstance(curhero.getWidth(), curhero.getHeight(), Image.SCALE_DEFAULT)));
				}
				if(gamex.getCurrentHero()instanceof Paladin){
					curhero.setIcon(new ImageIcon(new ImageIcon("images/Paladin.png").getImage().getScaledInstance(curhero.getWidth(), curhero.getHeight(), Image.SCALE_DEFAULT)));
				}
				if(gamex.getCurrentHero()instanceof Priest){
					curhero.setIcon(new ImageIcon(new ImageIcon("images/priest.png").getImage().getScaledInstance(curhero.getWidth(), curhero.getHeight(), Image.SCALE_DEFAULT)));
				}
				if(gamex.getCurrentHero()instanceof Warlock){
					curhero.setIcon(new ImageIcon(new ImageIcon("images/Warlock.png").getImage().getScaledInstance(curhero.getWidth(), curhero.getHeight(), Image.SCALE_DEFAULT)));
				}


				if(gamex.getOpponent()instanceof Hunter){
					opphero.setIcon(new ImageIcon(new ImageIcon("images/Hunter.png").getImage().getScaledInstance(opphero.getWidth(), opphero.getHeight(), Image.SCALE_DEFAULT)));
				}
				if(gamex.getOpponent()instanceof Mage){
					opphero.setIcon(new ImageIcon(new ImageIcon("images/mage.png").getImage().getScaledInstance(opphero.getWidth(), opphero.getHeight(), Image.SCALE_DEFAULT)));
				}
				if(gamex.getOpponent()instanceof Paladin){
					opphero.setIcon(new ImageIcon(new ImageIcon("images/Paladin.png").getImage().getScaledInstance(opphero.getWidth(), opphero.getHeight(), Image.SCALE_DEFAULT)));
				}
				if(gamex.getOpponent()instanceof Priest){
					opphero.setIcon(new ImageIcon(new ImageIcon("images/priest.png").getImage().getScaledInstance(opphero.getWidth(), opphero.getHeight(), Image.SCALE_DEFAULT)));
				}
				if(gamex.getOpponent()instanceof Warlock){
					opphero.setIcon(new ImageIcon(new ImageIcon("images/Warlock.png").getImage().getScaledInstance(opphero.getWidth(), opphero.getHeight(), Image.SCALE_DEFAULT)));
				}
			}
		}
		if(e.getActionCommand().equals("cast2")) {
			if(oppMinionwithhero.getSelectedIndex() == gamex.getOpponent().getField().size()) {
				try {
					gamex.getCurrentHero().castSpell((HeroTargetSpell) tmpSpell , gamex.getOpponent());
				} catch (NotYourTurnException | NotEnoughManaException e1) {
					JOptionPane.showMessageDialog(null,e1.getMessage(),"Error", JOptionPane.INFORMATION_MESSAGE);
				}
				window.getContentPane().removeAll();
				window.revalidate();
				window.repaint();
				((JFrame) SwingUtilities.getWindowAncestor(((JButton)e.getSource()))).dispose();
				window.setEnabled(true);
				window.show();
				run();
				if(gamex.getCurrentHero().isHeroPowerUsed()){
					useheropower.setBackground(Color.red);
				}
				if(gamex.getCurrentHero()instanceof Hunter){
					curhero.setIcon(new ImageIcon(new ImageIcon("images/Hunter.png").getImage().getScaledInstance(curhero.getWidth(), curhero.getHeight(), Image.SCALE_DEFAULT)));
				}
				if(gamex.getCurrentHero()instanceof Mage){
					curhero.setIcon(new ImageIcon(new ImageIcon("images/mage.png").getImage().getScaledInstance(curhero.getWidth(), curhero.getHeight(), Image.SCALE_DEFAULT)));
				}
				if(gamex.getCurrentHero()instanceof Paladin){
					curhero.setIcon(new ImageIcon(new ImageIcon("images/Paladin.png").getImage().getScaledInstance(curhero.getWidth(), curhero.getHeight(), Image.SCALE_DEFAULT)));
				}
				if(gamex.getCurrentHero()instanceof Priest){
					curhero.setIcon(new ImageIcon(new ImageIcon("images/priest.png").getImage().getScaledInstance(curhero.getWidth(), curhero.getHeight(), Image.SCALE_DEFAULT)));
				}
				if(gamex.getCurrentHero()instanceof Warlock){
					curhero.setIcon(new ImageIcon(new ImageIcon("images/Warlock.png").getImage().getScaledInstance(curhero.getWidth(), curhero.getHeight(), Image.SCALE_DEFAULT)));
				}


				if(gamex.getOpponent()instanceof Hunter){
					opphero.setIcon(new ImageIcon(new ImageIcon("images/Hunter.png").getImage().getScaledInstance(opphero.getWidth(), opphero.getHeight(), Image.SCALE_DEFAULT)));
				}
				if(gamex.getOpponent()instanceof Mage){
					opphero.setIcon(new ImageIcon(new ImageIcon("images/mage.png").getImage().getScaledInstance(opphero.getWidth(), opphero.getHeight(), Image.SCALE_DEFAULT)));
				}
				if(gamex.getOpponent()instanceof Paladin){
					opphero.setIcon(new ImageIcon(new ImageIcon("images/Paladin.png").getImage().getScaledInstance(opphero.getWidth(), opphero.getHeight(), Image.SCALE_DEFAULT)));
				}
				if(gamex.getOpponent()instanceof Priest){
					opphero.setIcon(new ImageIcon(new ImageIcon("images/priest.png").getImage().getScaledInstance(opphero.getWidth(), opphero.getHeight(), Image.SCALE_DEFAULT)));
				}
				if(gamex.getOpponent()instanceof Warlock){
					opphero.setIcon(new ImageIcon(new ImageIcon("images/Warlock.png").getImage().getScaledInstance(opphero.getWidth(), opphero.getHeight(), Image.SCALE_DEFAULT)));
				}
			}
			else {
				try {
					gamex.getCurrentHero().castSpell((MinionTargetSpell) tmpSpell , gamex.getOpponent().getField().get(oppMinionwithhero.getSelectedIndex()));
				}catch (NotYourTurnException | NotEnoughManaException | InvalidTargetException e1) {
					JOptionPane.showMessageDialog(null,e1.getMessage(),"Error", JOptionPane.INFORMATION_MESSAGE);
				}
				window.getContentPane().removeAll();
				window.revalidate();
				window.repaint();
				((JFrame) SwingUtilities.getWindowAncestor(((JButton)e.getSource()))).dispose();
				window.setEnabled(true);
				window.show();
				run();
				if(gamex.getCurrentHero().isHeroPowerUsed()){
					useheropower.setBackground(Color.red);
				}
				if(gamex.getCurrentHero()instanceof Hunter){
					curhero.setIcon(new ImageIcon(new ImageIcon("images/Hunter.png").getImage().getScaledInstance(curhero.getWidth(), curhero.getHeight(), Image.SCALE_DEFAULT)));
				}
				if(gamex.getCurrentHero()instanceof Mage){
					curhero.setIcon(new ImageIcon(new ImageIcon("images/mage.png").getImage().getScaledInstance(curhero.getWidth(), curhero.getHeight(), Image.SCALE_DEFAULT)));
				}
				if(gamex.getCurrentHero()instanceof Paladin){
					curhero.setIcon(new ImageIcon(new ImageIcon("images/Paladin.png").getImage().getScaledInstance(curhero.getWidth(), curhero.getHeight(), Image.SCALE_DEFAULT)));
				}
				if(gamex.getCurrentHero()instanceof Priest){
					curhero.setIcon(new ImageIcon(new ImageIcon("images/priest.png").getImage().getScaledInstance(curhero.getWidth(), curhero.getHeight(), Image.SCALE_DEFAULT)));
				}
				if(gamex.getCurrentHero()instanceof Warlock){
					curhero.setIcon(new ImageIcon(new ImageIcon("images/Warlock.png").getImage().getScaledInstance(curhero.getWidth(), curhero.getHeight(), Image.SCALE_DEFAULT)));
				}


				if(gamex.getOpponent()instanceof Hunter){
					opphero.setIcon(new ImageIcon(new ImageIcon("images/Hunter.png").getImage().getScaledInstance(opphero.getWidth(), opphero.getHeight(), Image.SCALE_DEFAULT)));
				}
				if(gamex.getOpponent()instanceof Mage){
					opphero.setIcon(new ImageIcon(new ImageIcon("images/mage.png").getImage().getScaledInstance(opphero.getWidth(), opphero.getHeight(), Image.SCALE_DEFAULT)));
				}
				if(gamex.getOpponent()instanceof Paladin){
					opphero.setIcon(new ImageIcon(new ImageIcon("images/Paladin.png").getImage().getScaledInstance(opphero.getWidth(), opphero.getHeight(), Image.SCALE_DEFAULT)));
				}
				if(gamex.getOpponent()instanceof Priest){
					opphero.setIcon(new ImageIcon(new ImageIcon("images/priest.png").getImage().getScaledInstance(opphero.getWidth(), opphero.getHeight(), Image.SCALE_DEFAULT)));
				}
				if(gamex.getOpponent()instanceof Warlock){
					opphero.setIcon(new ImageIcon(new ImageIcon("images/Warlock.png").getImage().getScaledInstance(opphero.getWidth(), opphero.getHeight(), Image.SCALE_DEFAULT)));
				}
			}
		}
		if(e.getActionCommand().startsWith("attack ")) {
			window.setEnabled(false);
			JFrame x1 = new JFrame("Attack");
			JFrame.setDefaultLookAndFeelDecorated(true);
			x1.setVisible(true);
			x1.addWindowListener(new java.awt.event.WindowAdapter() {
				@Override
				public void windowClosing(java.awt.event.WindowEvent windowEvent) {
					window.setEnabled(true);
					x1.dispose();
					x1.setAlwaysOnTop(true);
				}
			});
			x1.setAlwaysOnTop(true);
			x1.setBounds(0,(int)(screenSize.getHeight() * 0.4),(int) (screenSize.getWidth() * 0.105),(int)(screenSize.getHeight() *0.185));
			tmpMinion = gamex.getCurrentHero().getField().get(Integer.parseInt(e.getActionCommand().substring(7)));
			Oppfield = new JComboBox<String>();
			JButton Attack = new JButton("Attack!");
			JLabel lb = new JLabel("Attack Hero or Minion");
			x1.setLayout(new GridLayout(3,1));
			Oppfield.setSize(100, 50);
			for(int j = 0; j < gamex.getOpponent().getField().size();j++) {
				Oppfield.addItem(gamex.getOpponent().getField().get(j).getName());
			}
			Oppfield.addItem(gamex.getOpponent().getName());
			x1.add(lb);
			x1.add(Oppfield);
			x1.add(Attack);
			Attack.setActionCommand("attack2");
			Attack.addActionListener(this);
		}
		if(e.getActionCommand().equals("attack2")) {
			if(Oppfield.getSelectedIndex() == Oppfield.getItemCount()-1) {
				try {
					if(!tmpMinion.getName().equals("Icehowl")) {
						gamex.getCurrentHero().attackWithMinion(tmpMinion, gamex.getOpponent());
					}
					else {
						JOptionPane.showMessageDialog(null,"you can not attack with this minion","Error", JOptionPane.INFORMATION_MESSAGE);
					}
				} catch (CannotAttackException | NotYourTurnException | TauntBypassException | NotSummonedException
						| InvalidTargetException e1) {
					JOptionPane.showMessageDialog(null,e1.getMessage(),"Error", JOptionPane.INFORMATION_MESSAGE);
				}
				window.getContentPane().removeAll();
				window.revalidate();
				window.repaint();
				((JFrame) SwingUtilities.getWindowAncestor(((JButton)e.getSource()))).dispose();
				window.setEnabled(true);
				window.show();
				run();
				if(gamex.getCurrentHero().isHeroPowerUsed()){
					useheropower.setBackground(Color.red);
				}
				if(gamex.getCurrentHero()instanceof Hunter){
					curhero.setIcon(new ImageIcon(new ImageIcon("images/Hunter.png").getImage().getScaledInstance(curhero.getWidth(), curhero.getHeight(), Image.SCALE_DEFAULT)));
				}
				if(gamex.getCurrentHero()instanceof Mage){
					curhero.setIcon(new ImageIcon(new ImageIcon("images/mage.png").getImage().getScaledInstance(curhero.getWidth(), curhero.getHeight(), Image.SCALE_DEFAULT)));
				}
				if(gamex.getCurrentHero()instanceof Paladin){
					curhero.setIcon(new ImageIcon(new ImageIcon("images/Paladin.png").getImage().getScaledInstance(curhero.getWidth(), curhero.getHeight(), Image.SCALE_DEFAULT)));
				}
				if(gamex.getCurrentHero()instanceof Priest){
					curhero.setIcon(new ImageIcon(new ImageIcon("images/priest.png").getImage().getScaledInstance(curhero.getWidth(), curhero.getHeight(), Image.SCALE_DEFAULT)));
				}
				if(gamex.getCurrentHero()instanceof Warlock){
					curhero.setIcon(new ImageIcon(new ImageIcon("images/Warlock.png").getImage().getScaledInstance(curhero.getWidth(), curhero.getHeight(), Image.SCALE_DEFAULT)));
				}


				if(gamex.getOpponent()instanceof Hunter){
					opphero.setIcon(new ImageIcon(new ImageIcon("images/Hunter.png").getImage().getScaledInstance(opphero.getWidth(), opphero.getHeight(), Image.SCALE_DEFAULT)));
				}
				if(gamex.getOpponent()instanceof Mage){
					opphero.setIcon(new ImageIcon(new ImageIcon("images/mage.png").getImage().getScaledInstance(opphero.getWidth(), opphero.getHeight(), Image.SCALE_DEFAULT)));
				}
				if(gamex.getOpponent()instanceof Paladin){
					opphero.setIcon(new ImageIcon(new ImageIcon("images/Paladin.png").getImage().getScaledInstance(opphero.getWidth(), opphero.getHeight(), Image.SCALE_DEFAULT)));
				}
				if(gamex.getOpponent()instanceof Priest){
					opphero.setIcon(new ImageIcon(new ImageIcon("images/priest.png").getImage().getScaledInstance(opphero.getWidth(), opphero.getHeight(), Image.SCALE_DEFAULT)));
				}
				if(gamex.getOpponent()instanceof Warlock){
					opphero.setIcon(new ImageIcon(new ImageIcon("images/Warlock.png").getImage().getScaledInstance(opphero.getWidth(), opphero.getHeight(), Image.SCALE_DEFAULT)));
				}
			}
			else {
				int k = Oppfield.getSelectedIndex();
				try {
					gamex.getCurrentHero().attackWithMinion(tmpMinion, gamex.getOpponent().getField().get(k));
				} catch (CannotAttackException | NotYourTurnException | TauntBypassException | InvalidTargetException
						| NotSummonedException e1) {
					JOptionPane.showMessageDialog(null,e1.getMessage(),"Error", JOptionPane.INFORMATION_MESSAGE);
				}
				window.getContentPane().removeAll();
				window.revalidate();
				window.repaint();
				((JFrame) SwingUtilities.getWindowAncestor(((JButton)e.getSource()))).dispose();
				window.setEnabled(true);
				window.show();
				run();
				if(gamex.getCurrentHero().isHeroPowerUsed()){
					useheropower.setBackground(Color.red);
				}
				if(gamex.getCurrentHero()instanceof Hunter){
					curhero.setIcon(new ImageIcon(new ImageIcon("images/Hunter.png").getImage().getScaledInstance(curhero.getWidth(), curhero.getHeight(), Image.SCALE_DEFAULT)));
				}
				if(gamex.getCurrentHero()instanceof Mage){
					curhero.setIcon(new ImageIcon(new ImageIcon("images/mage.png").getImage().getScaledInstance(curhero.getWidth(), curhero.getHeight(), Image.SCALE_DEFAULT)));
				}
				if(gamex.getCurrentHero()instanceof Paladin){
					curhero.setIcon(new ImageIcon(new ImageIcon("images/Paladin.png").getImage().getScaledInstance(curhero.getWidth(), curhero.getHeight(), Image.SCALE_DEFAULT)));
				}
				if(gamex.getCurrentHero()instanceof Priest){
					curhero.setIcon(new ImageIcon(new ImageIcon("images/priest.png").getImage().getScaledInstance(curhero.getWidth(), curhero.getHeight(), Image.SCALE_DEFAULT)));
				}
				if(gamex.getCurrentHero()instanceof Warlock){
					curhero.setIcon(new ImageIcon(new ImageIcon("images/Warlock.png").getImage().getScaledInstance(curhero.getWidth(), curhero.getHeight(), Image.SCALE_DEFAULT)));
				}


				if(gamex.getOpponent()instanceof Hunter){
					opphero.setIcon(new ImageIcon(new ImageIcon("images/Hunter.png").getImage().getScaledInstance(opphero.getWidth(), opphero.getHeight(), Image.SCALE_DEFAULT)));
				}
				if(gamex.getOpponent()instanceof Mage){
					opphero.setIcon(new ImageIcon(new ImageIcon("images/mage.png").getImage().getScaledInstance(opphero.getWidth(), opphero.getHeight(), Image.SCALE_DEFAULT)));
				}
				if(gamex.getOpponent()instanceof Paladin){
					opphero.setIcon(new ImageIcon(new ImageIcon("images/Paladin.png").getImage().getScaledInstance(opphero.getWidth(), opphero.getHeight(), Image.SCALE_DEFAULT)));
				}
				if(gamex.getOpponent()instanceof Priest){
					opphero.setIcon(new ImageIcon(new ImageIcon("images/priest.png").getImage().getScaledInstance(opphero.getWidth(), opphero.getHeight(), Image.SCALE_DEFAULT)));
				}
				if(gamex.getOpponent()instanceof Warlock){
					opphero.setIcon(new ImageIcon(new ImageIcon("images/Warlock.png").getImage().getScaledInstance(opphero.getWidth(), opphero.getHeight(), Image.SCALE_DEFAULT)));
				}
			}
		}
	}
	public JLabel drawMinion2(Minion m, int i) {
		JLabel temp = new JLabel();
		temp.setEnabled(false);
		temp.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		temp.setLayout(new GridLayout(10,1));
		String s = m.getName();
		int mc = m.getManaCost();
		Rarity r = m.getRarity();
		JLabel cardName = new JLabel(s);
		cardName.setFont(new Font("Arial", Font.PLAIN, 21));
		cardName.setForeground(Color.GREEN);
		cardName.setHorizontalAlignment(JLabel.CENTER);
		temp.add(cardName);
		JLabel manacost = new JLabel(mc + " mana cost");
		manacost.setFont(new Font("Arial", Font.PLAIN, 21));
		manacost.setForeground(Color.CYAN);
		manacost.setHorizontalAlignment(JLabel.CENTER);
		temp.add(manacost);
		if(r.equals(Rarity.BASIC)) {
			JLabel tmp = new JLabel(r + "");
			tmp.setHorizontalAlignment(JLabel.CENTER);
			tmp.setFont(new Font("Arial", Font.PLAIN, 21));
			tmp.setForeground(Color.WHITE);
			temp.add(tmp);
		}
		if(r.equals(Rarity.COMMON)) {
			JLabel tmp = new JLabel(r + "");
			tmp.setHorizontalAlignment(JLabel.CENTER);
			tmp.setFont(new Font("Arial", Font.PLAIN, 21));
			tmp.setForeground(Color.lightGray);
			temp.add(tmp);
		}
		if(r.equals(Rarity.EPIC)) {
			JLabel tmp = new JLabel(r + "");
			tmp.setHorizontalAlignment(JLabel.CENTER);
			tmp.setFont(new Font("Arial", Font.PLAIN, 21));
			tmp.setForeground(Color.MAGENTA);
			temp.add(tmp);
		}
		if(r.equals(Rarity.LEGENDARY)) {
			JLabel tmp = new JLabel(r + "");
			tmp.setHorizontalAlignment(JLabel.CENTER);
			tmp.setFont(new Font("Arial", Font.PLAIN, 21));
			tmp.setForeground(Color.ORANGE);
			temp.add(tmp);
		}
		if(r.equals(Rarity.RARE)) {
			JLabel tmp = new JLabel(r + "");
			tmp.setHorizontalAlignment(JLabel.CENTER);
			tmp.setFont(new Font("Arial", Font.PLAIN, 21));
			tmp.setForeground(Color.YELLOW);
			temp.add(tmp);
		}	
		JLabel attack = new JLabel(m.getAttack() + " damage");
		attack.setHorizontalAlignment(JLabel.CENTER);
		attack.setFont(new Font("Arial", Font.PLAIN, 21));
		attack.setForeground(Color.MAGENTA);
		temp.add(attack);
		JLabel Hp = new JLabel(m.getCurrentHP() + " Hp");
		Hp.setHorizontalAlignment(JLabel.CENTER);
		Hp.setFont(new Font("Arial", Font.PLAIN, 21));
		Hp.setForeground(Color.RED);
		temp.add(Hp);
		if (m.isDivine()) {
			JLabel divine = new JLabel("Has devine sheild");
			divine.setHorizontalAlignment(JLabel.CENTER);
			divine.setFont(new Font("Arial", Font.PLAIN, 21));
			divine.setForeground(Color.lightGray);
			temp.add(divine);
		}
		if (m.isTaunt()) {
			JLabel taunt = new JLabel("Has taunt ability");
			taunt.setHorizontalAlignment(JLabel.CENTER);
			taunt.setFont(new Font("Arial", Font.PLAIN, 21));
			taunt.setForeground(Color.black);
			temp.add(taunt);
		}
		if (!m.isSleeping()) {
			JLabel charge = new JLabel("Charge");
			charge.setHorizontalAlignment(JLabel.CENTER);
			charge.setFont(new Font("Arial", Font.PLAIN, 21));
			charge.setForeground(Color.white);
			temp.add(charge);
		} else {
			JLabel charge = new JLabel("Sleeping");
			charge.setHorizontalAlignment(JLabel.CENTER);
			charge.setFont(new Font("Arial", Font.PLAIN, 21));
			charge.setForeground(Color.white);
			temp.add(charge);
		}
		return temp;
	}
	public JLabel drawMinion(Minion m, int i) {
		JLabel temp = new JLabel();
		temp.setEnabled(false);
		temp.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		temp.setLayout(new GridLayout(10,1));
		String s = m.getName();
		int mc = m.getManaCost();
		Rarity r = m.getRarity();
		JLabel cardName = new JLabel(s);
		cardName.setFont(new Font("Arial", Font.PLAIN, 21));
		cardName.setForeground(Color.GREEN);
		cardName.setHorizontalAlignment(JLabel.CENTER);
		temp.add(cardName);
		JLabel manacost = new JLabel(mc + " mana cost");
		manacost.setFont(new Font("Arial", Font.PLAIN, 21));
		manacost.setForeground(Color.CYAN);
		manacost.setHorizontalAlignment(JLabel.CENTER);
		temp.add(manacost);
		if(r.equals(Rarity.BASIC)) {
			JLabel tmp = new JLabel(r + "");
			tmp.setHorizontalAlignment(JLabel.CENTER);
			tmp.setFont(new Font("Arial", Font.PLAIN, 21));
			tmp.setForeground(Color.WHITE);
			temp.add(tmp);
		}
		if(r.equals(Rarity.COMMON)) {
			JLabel tmp = new JLabel(r + "");
			tmp.setHorizontalAlignment(JLabel.CENTER);
			tmp.setFont(new Font("Arial", Font.PLAIN, 21));
			tmp.setForeground(Color.lightGray);
			temp.add(tmp);
		}
		if(r.equals(Rarity.EPIC)) {
			JLabel tmp = new JLabel(r + "");
			tmp.setHorizontalAlignment(JLabel.CENTER);
			tmp.setFont(new Font("Arial", Font.PLAIN, 21));
			tmp.setForeground(Color.MAGENTA);
			temp.add(tmp);
		}
		if(r.equals(Rarity.LEGENDARY)) {
			JLabel tmp = new JLabel(r + "");
			tmp.setHorizontalAlignment(JLabel.CENTER);
			tmp.setFont(new Font("Arial", Font.PLAIN, 21));
			tmp.setForeground(Color.ORANGE);
			temp.add(tmp);
		}
		if(r.equals(Rarity.RARE)) {
			JLabel tmp = new JLabel(r + "");
			tmp.setHorizontalAlignment(JLabel.CENTER);
			tmp.setFont(new Font("Arial", Font.PLAIN, 21));
			tmp.setForeground(Color.YELLOW);
			temp.add(tmp);
		}	
		JLabel attack = new JLabel(m.getAttack() + " damage");
		attack.setHorizontalAlignment(JLabel.CENTER);
		attack.setFont(new Font("Arial", Font.PLAIN, 21));
		attack.setForeground(Color.MAGENTA);
		temp.add(attack);
		JLabel Hp = new JLabel(m.getCurrentHP() + " Hp");
		Hp.setHorizontalAlignment(JLabel.CENTER);
		Hp.setFont(new Font("Arial", Font.PLAIN, 21));
		Hp.setForeground(Color.RED);
		temp.add(Hp);
		if (m.isDivine()) {
			JLabel divine = new JLabel("Has devine sheild");
			divine.setHorizontalAlignment(JLabel.CENTER);
			divine.setFont(new Font("Arial", Font.PLAIN, 21));
			divine.setForeground(Color.lightGray);
			temp.add(divine);
		}
		if (m.isTaunt()) {
			JLabel taunt = new JLabel("Has taunt ability");
			taunt.setHorizontalAlignment(JLabel.CENTER);
			taunt.setFont(new Font("Arial", Font.PLAIN, 21));
			taunt.setForeground(Color.black);
			temp.add(taunt);
		}
		if (!m.isSleeping()) {
			JLabel charge = new JLabel("ready to attack");
			charge.setHorizontalAlignment(JLabel.CENTER);
			charge.setFont(new Font("Arial", Font.PLAIN, 21));
			charge.setForeground(Color.white);
			if (m.isAttacked()) {
				charge.setText("has attacked");
			}
			temp.add(charge);
			Attack = new JButton("attack");
			Attack.setActionCommand("attack " + i);
			Attack.addActionListener(this);
			temp.add(Attack);
			if (m.isAttacked()) {
				Attack.setEnabled(false);
			}
		} else {
			JLabel charge = new JLabel("not ready to attack");
			charge.setHorizontalAlignment(JLabel.CENTER);
			charge.setFont(new Font("Arial", Font.PLAIN, 21));
			charge.setForeground(Color.white);
			temp.add(charge);
		}
		return temp;
	}
	public boolean checktaunt(ArrayList<Minion> m) {
		Boolean flag = false;
		for(int i = 0; i < m.size();i++) {
			if(m.get(i).isTaunt())
				return true;
		}
		return flag;
	}

	public void check() {
		if(key%2 == 0) {
			Player1.setVisible(true);
			Player2.setVisible(false);
		}
		else {
			Player1.setVisible(false);
			Player2.setVisible(true);
		}
	}
	
	public static void main(String[] args) {
		new game();
		window.setVisible(true);
	}
	public void onGameOver() {
		if(Player1.isVisible()){
			JOptionPane.showConfirmDialog(null,userName1+" Won The Game","congratulations",JOptionPane.DEFAULT_OPTION);
			System.exit(1);
		}
		else{
			JOptionPane.showConfirmDialog(null,userName2+" Won The Game","congratulations", JOptionPane.DEFAULT_OPTION);
			System.exit(1);

		}
	}
}
