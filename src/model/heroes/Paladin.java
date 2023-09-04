package model.heroes;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

import exceptions.FullFieldException;
import exceptions.FullHandException;
import exceptions.HeroPowerAlreadyUsedException;
import exceptions.NotEnoughManaException;
import exceptions.NotYourTurnException;
import model.cards.Rarity;
import model.cards.minions.Minion;
import model.cards.spells.LevelUp;
import model.cards.spells.SealOfChampions;

public class Paladin extends Hero {
	public Paladin() throws IOException, CloneNotSupportedException
	{
		super("Uther Lightbringer");
	}
	
	public void buildDeck() throws IOException, CloneNotSupportedException {
		ArrayList<Minion> neutrals= getNeutralMinions(getAllNeutralMinions("neutral_minions.csv"),15);
		getDeck().addAll(neutrals);
		for(int i = 0 ; i < 2; i++){
			getDeck().add(new SealOfChampions());
			getDeck().add(new LevelUp());
		}
		Minion tirion=new Minion("Tirion Fordring", 4, Rarity.LEGENDARY, 6, 6, true, true, false);
		tirion.setListener(this);
		getDeck().add(tirion);
		listenToMinions();
		Collections.shuffle(getDeck());
	}
	public void useHeroPower() throws NotEnoughManaException,HeroPowerAlreadyUsedException, NotYourTurnException,FullHandException, FullFieldException, CloneNotSupportedException {
		if(getField().size()>=7){
			throw new FullFieldException("your field is full");
		}
		else{
			super.useHeroPower();
			Minion SilverHandRecruit=new Minion("Silver Hand Recruit", 1,Rarity.BASIC, 1, 1, false, false,false);
			SilverHandRecruit.setListener(this);
			getField().add(SilverHandRecruit);
		}	
	}
}