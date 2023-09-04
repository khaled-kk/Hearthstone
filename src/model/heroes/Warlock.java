package model.heroes;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

import exceptions.FullFieldException;
import exceptions.FullHandException;
import exceptions.HeroPowerAlreadyUsedException;
import exceptions.NotEnoughManaException;
import exceptions.NotYourTurnException;
import model.cards.Card;
import model.cards.Rarity;
import model.cards.minions.Minion;
import model.cards.spells.CurseOfWeakness;
import model.cards.spells.SiphonSoul;
import model.cards.spells.TwistingNether;

public class Warlock extends Hero {

	public Warlock() throws IOException, CloneNotSupportedException {
		super("Gul'dan");
	}
	
	public void buildDeck() throws IOException, CloneNotSupportedException {
		ArrayList<Minion> neutrals= getNeutralMinions(getAllNeutralMinions("neutral_minions.csv"),13);
		getDeck().addAll(neutrals);
		for	(int i = 0 ; i < 2; i++) {
			getDeck().add(new CurseOfWeakness());
			getDeck().add(new SiphonSoul());
			getDeck().add(new TwistingNether());
		}
		Minion wilfred=new Minion("Wilfred Fizzlebang",6,Rarity.LEGENDARY,4,4,false,false,false);
		wilfred.setListener(this);
		getDeck().add(wilfred);
		listenToMinions();
		Collections.shuffle(getDeck());

	}
	public void useHeroPower() throws NotEnoughManaException,HeroPowerAlreadyUsedException, NotYourTurnException, FullHandException,FullFieldException, CloneNotSupportedException {
		super.useHeroPower();
		setCurrentHP(getCurrentHP()-2);
		Card secard=drawCard();
		for (int i=0;i<getField().size();i++){
			Minion m=getField().get(i);
			if(m.getName().equals("Wilfred Fizzlebang")&&secard instanceof Minion){
					secard.setManaCost(0);
					for (int j=0;j<getField().size();j++){
						Minion m1=getField().get(j);
						if(m1.getName()=="Chromaggus"){
							getHand().get(getHand().size() - 1).setManaCost(0);
						}
					}
			}			
		}
	}
}