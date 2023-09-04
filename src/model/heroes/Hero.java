package model.heroes;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import model.cards.minions.Minion;
import engine.ActionValidator;
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
import model.cards.minions.Icehowl;
import model.cards.minions.MinionListener;
import model.cards.spells.AOESpell;
import model.cards.spells.FieldSpell;
import model.cards.spells.HeroTargetSpell;
import model.cards.spells.LeechingSpell;
import model.cards.spells.MinionTargetSpell;
import model.cards.spells.Spell;

public abstract class Hero implements MinionListener{
	private String name;
	private int currentHP;
	private boolean heroPowerUsed;
	private int totalManaCrystals;
	private int currentManaCrystals;
	private ArrayList<Card> deck;
	private ArrayList<Minion> field;
	private ArrayList<Card> hand;
	private int fatigueDamage;
	private HeroListener listener;
	private ActionValidator validator;
	
	public Hero(String name) throws IOException, CloneNotSupportedException {
		this.name = name;
		currentHP = 30;
		deck = new ArrayList<Card>();
		field = new ArrayList<Minion>(7);
		hand = new ArrayList<Card>(10);
		buildDeck();
	} 
	//--------------------------Getters and Setters------------------------
	public int getCurrentHP() {
		return currentHP;
	}

	public void setCurrentHP(int hp) {
		this.currentHP = hp;
		if (this.currentHP > 30)
			this.currentHP = 30;
		else if (this.currentHP <= 0) {
			this.currentHP = 0;
			listener.onHeroDeath();
		}
	}

	public int getTotalManaCrystals() {
		return totalManaCrystals;
	}

	public void setTotalManaCrystals(int totalManaCrystals) {
		this.totalManaCrystals = totalManaCrystals;
		if (this.totalManaCrystals > 10)
			this.totalManaCrystals = 10;
	}

	public int getCurrentManaCrystals() {
		return currentManaCrystals;
	}

	public void setCurrentManaCrystals(int currentManaCrystals) {
		this.currentManaCrystals = currentManaCrystals;
		if (this.currentManaCrystals > 10)
			this.currentManaCrystals = 10;
	}

	public ArrayList<Minion> getField() {
		return field;
	}
	public ArrayList<Card> getHand() {
		return hand;
	}

	public boolean isHeroPowerUsed() {
		return heroPowerUsed;
	}

	public ArrayList<Card> getDeck() {
		return deck;
	}

	public void setHeroPowerUsed(boolean powerUsed) {
		this.heroPowerUsed = powerUsed;
	}

	public String getName() {
		return name;
	}
	public HeroListener getListener() {
		return listener;
	}

	public void setListener(HeroListener listener) {
		this.listener = listener;
	}

	public void setValidator(ActionValidator validator) {
		this.validator = validator;
	}
	//----------------------------------------------------------------------
	public abstract void buildDeck() throws IOException, CloneNotSupportedException;

	 public static final ArrayList<Minion> getAllNeutralMinions(String filePath) throws IOException {
			
		BufferedReader br;
		String currentLine = "";
		ArrayList<Minion> MinionList = new ArrayList<Minion>();
		FileReader fileReader= new FileReader(filePath);
		br = new BufferedReader(fileReader);
		
		while ((currentLine = br.readLine()) != null) {
			String[] minionParameters = currentLine.split(",");
			String minionName = minionParameters[0];
			int minionManaCost = Integer.parseInt(minionParameters[1]);
			Rarity minionRarity;
			switch(minionParameters[2].charAt(0)) {
			case 'b':
				minionRarity = Rarity.BASIC;
				break;

			case 'c':
				minionRarity = Rarity.COMMON;
				break;

			case 'r':
				minionRarity = Rarity.RARE;
				break;

			case 'e':
				minionRarity = Rarity.EPIC;
				break;

			case 'l':
				minionRarity = Rarity.LEGENDARY;
				break;

			default:
				minionRarity = Rarity.BASIC;
				break;
			}
			int minionAttack = Integer.parseInt(minionParameters[3]);
			int minionMaxHP = Integer.parseInt(minionParameters[4]);
			boolean minionTaunt = (minionParameters[5].equals("TRUE"))?true :false;
			boolean minionDivine = (minionParameters[6].equals("TRUE"))?true :false;
			boolean minionCharge = (minionParameters[7].equals("TRUE"))?true :false;
			if(minionName.equals("Icehowl")) {
				MinionList.add(new Icehowl());
			}
			else {
				MinionList.add(new Minion(minionName, minionManaCost, minionRarity, minionAttack, minionMaxHP,
					minionTaunt, minionDivine, minionCharge));
			}
		}
		br.close();
		
		return MinionList;
		
	}

	public static final ArrayList<Minion> getNeutralMinions(ArrayList<Minion> minions, int count) throws CloneNotSupportedException {
		ArrayList<Minion> res = new ArrayList<Minion>();
		int i = 0;
		while (i < count) {
			
			int index = (int) (Math.random() * minions.size());
			Minion minion = minions.get(index);
			int occ = 0;
			for (int j = 0; j < res.size(); j++) {
				if (res.get(j).getName().equals(minion.getName()))
					occ++;
			}
			if (occ == 0)
			{
				res.add(minion.clone());
				i++;
			}
			else if(occ==1 && minion.getRarity()!=Rarity.LEGENDARY)
			{
				res.add(minion.clone());
				i++;
			}
		}
		return res;
	}
	public void listenToMinions() {
		for (Card c : deck) {
			if (c instanceof Minion)
				((Minion) c).setListener(this);
		}
	}
	public void useHeroPower() throws NotEnoughManaException,HeroPowerAlreadyUsedException, NotYourTurnException, FullHandException,FullFieldException, CloneNotSupportedException {
		validator.validateTurn(this);
		validator.validateUsingHeroPower(this);
		heroPowerUsed=true;
		currentManaCrystals-=2;
	}
	
	public void playMinion(Minion m) throws NotYourTurnException,NotEnoughManaException, FullFieldException{
		validator.validateTurn(this);
		validator.validateManaCost(m);
		validator.validatePlayingMinion(m);
		currentManaCrystals -= m.getManaCost();
		field.add(m);
		hand.remove(m);
	}
	
	public void attackWithMinion(Minion attacker, Minion target) throws CannotAttackException, NotYourTurnException, TauntBypassException,InvalidTargetException, NotSummonedException{
		validator.validateTurn(this);
		validator.validateAttack(attacker, target);
		attacker.attack(target);
	}
	
	public void attackWithMinion(Minion attacker, Hero target) throws CannotAttackException, NotYourTurnException, TauntBypassException,NotSummonedException, InvalidTargetException{
		validator.validateTurn(this);
		validator.validateAttack(attacker, target);
		attacker.attack(target);
	}
	
	public void castSpell(FieldSpell s) throws NotYourTurnException,NotEnoughManaException {
		validator.validateTurn(this);
		validator.validateManaCost((Spell) s);
		s.performAction(field);
		currentManaCrystals -=((Spell)s).getManaCost();
		hand.remove((Spell)s);
	}
	
	public void castSpell(AOESpell s, ArrayList<Minion >oppField) throws NotYourTurnException, NotEnoughManaException{
		if (this instanceof Mage){
			for (int i=0;i<field.size();i++){
				if(field.get(i).getName()=="Kalycgos"){
					((Spell)s).setManaCost(((Spell)s).getManaCost()-4);
				}
			}
		}
		validator.validateTurn(this);
		validator.validateManaCost((Spell) s);
		s.performAction(oppField,field);
		currentManaCrystals -=((Spell)s).getManaCost();
		hand.remove((Spell)s);
	}
	
	public void castSpell(MinionTargetSpell s, Minion m) throws NotYourTurnException,NotEnoughManaException, InvalidTargetException{
		if (this instanceof Mage){
			for (int i=0;i<field.size();i++){
				if(field.get(i).getName()=="Kalycgos"){
						((Spell)s).setManaCost(((Spell)s).getManaCost()-4);
				}
			}
		}
		validator.validateTurn(this);
		validator.validateManaCost((Spell) s);
		s.performAction(m);
		currentManaCrystals -=((Spell)s).getManaCost();
		hand.remove((Spell)s);
	}
	
	public void castSpell(HeroTargetSpell s, Hero h) throws NotYourTurnException,NotEnoughManaException {
		if (this instanceof Mage){
			for (int i=0;i<field.size();i++){
				if(field.get(i).getName()=="Kalycgos"){
						((Spell)s).setManaCost(((Spell)s).getManaCost()-4);
				}
			}
		}
		validator.validateTurn(this);
		validator.validateManaCost((Spell) s);
		s.performAction(h);
		currentManaCrystals -=((Spell)s).getManaCost();
		hand.remove((Spell)s);
	}
	
	public void castSpell(LeechingSpell s, Minion m) throws NotYourTurnException,NotEnoughManaException {
		validator.validateTurn(this);
		validator.validateManaCost((Spell) s);
		setCurrentHP(currentHP+s.performAction(m));
		currentManaCrystals -=((Spell)s).getManaCost();
		hand.remove((Spell)s);
	}
	public void endTurn() throws FullHandException, CloneNotSupportedException{
		listener.endTurn();
	}

	public Card drawCard() throws FullHandException, CloneNotSupportedException {
		if (fatigueDamage > 0) {
			setCurrentHP(currentHP - fatigueDamage);
			fatigueDamage++;
			return null;
		}

		Card burn = deck.remove(0);

		if (deck.size() == 0)
			fatigueDamage = 1;
		if (hand.size() == 10)
			throw new FullHandException("yor hand is full", burn);
		hand.add(burn);
		for (Minion m : field) {
			if (m.getName().equals("Chromaggus")&&hand.size()<10){
					hand.add(burn.clone());
			}
		}
		return burn;

	}
	
	public void onMinionDeath(Minion m) {
		field.remove(m);
	}
}