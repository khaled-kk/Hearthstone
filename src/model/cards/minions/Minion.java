package model.cards.minions;

import exceptions.InvalidTargetException;
import model.cards.Card;
import model.cards.Rarity;
import model.heroes.Hero;

public class Minion extends Card implements Cloneable {
	private int attack;
	private int maxHP;
	private int currentHP;
	private boolean taunt;
	private boolean divine;
	private boolean sleeping;
	private boolean attacked;
	private MinionListener listener;

	public Minion(String name, int manaCost, Rarity rarity, int attack, int maxHP, boolean taunt, boolean divine,boolean charge) {
		super(name, manaCost, rarity);
		setAttack(attack);
		this.maxHP = maxHP;
		this.currentHP = maxHP;
		this.taunt = taunt;
		this.divine = divine;
		if (!charge)
			this.sleeping = true;
	}
	//--------------------------Getters and Setters------------------------
	public boolean isTaunt() {
		return taunt;
	}

	public int getMaxHP() {
		return maxHP;
	}

	public void setMaxHP(int maxHp) {
		this.maxHP = maxHp;
	}

	public int getCurrentHP() {
		return currentHP;
	}

	public void setCurrentHP(int currentHP) {
		this.currentHP = currentHP;
		if (this.currentHP > maxHP)
			this.currentHP = maxHP;
		else if (this.currentHP <= 0) {
				this.currentHP = 0;
				listener.onMinionDeath(this);
		}
	}

	public int getAttack() {
		return attack;
	}

	public void setAttack(int attack) {
		this.attack = attack;
		if (this.attack <= 0)
			this.attack = 0;
	}

	public void setTaunt(boolean isTaunt) {
		this.taunt = isTaunt;
	}

	public void setDivine(boolean divine) {
		this.divine = divine;
	}

	public boolean isAttacked() {
		return attacked;
	}

	public void setAttacked(boolean attacked) {
		this.attacked = attacked;
	}

	public boolean isSleeping() {
		return sleeping;
	}

	public void setSleeping(boolean sleeping) {
		this.sleeping = sleeping;
	}

	public boolean isDivine() {
		return divine;
	}	
	public void setListener(MinionListener listener) {
		this.listener = listener;
	}
	//----------------------------------------------------------------------
	public void attack(Minion target) {
		attacked=true;
		if(divine){
			if(target.divine){
				if(attack>0){
					target.divine=false;
				}
				if(target.attack>0){
					divine=false;
				}
			}
			else{
				if(attack>0){
					target.setCurrentHP(target.currentHP-this.attack);
				}
				if(target.attack>0){
					divine=false;
				}
			}
		}
		else{
			if(target.divine){
				if(attack>0){
					target.divine=false;
				}
				if(target.attack>0){
					setCurrentHP(currentHP-target.attack);
				}
			}
			else{
				setCurrentHP(currentHP-target.attack);
				target.setCurrentHP(target.currentHP-attack);
			}
		}
	}
	public void attack(Hero target) throws InvalidTargetException {
		attacked=true;
		target.setCurrentHP(target.getCurrentHP()-attack);
	}
	public Minion clone() throws CloneNotSupportedException {
		return (Minion) super.clone();
	}
}