package engine;

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
import model.cards.minions.Minion;
import model.heroes.Hero;
import model.heroes.HeroListener;

public class Game implements ActionValidator,HeroListener {
	private Hero firstHero;
	private Hero secondHero;
	private Hero currentHero;
	private Hero opponent;
	private GameListener listener;

	public Game(Hero p1, Hero p2) throws FullHandException, CloneNotSupportedException{
		firstHero=p1;
		secondHero=p2;
		
		int coin = (int) (Math.random()*2);
		currentHero= coin==0?firstHero:secondHero;
		opponent= currentHero==firstHero?secondHero:firstHero;
		currentHero.setTotalManaCrystals(1);
		currentHero.setCurrentManaCrystals(1);
		currentHero.setValidator(this);
		opponent.setValidator(this);
		currentHero.setListener(this);
		opponent.setListener(this);
		for (int i=0;i<3;i++){
			currentHero.drawCard();
		}
		for (int j=0;j<4;j++){
			opponent.drawCard();
		}
	}
	//--------------------------Getters and Setters------------------------
		public Hero getCurrentHero() {
			return currentHero;
		}
		public Hero getOpponent() {
			return opponent;
		}
		public void setListener(GameListener listener) {
			this.listener = listener;
		}
	//----------------------------------------------------------------------
		public void onHeroDeath() {
			listener.onGameOver();
		}
		
		public void damageOpponent(int amount) {
			opponent.setCurrentHP(opponent.getCurrentHP()-amount);
		}
		
		public void endTurn() throws FullHandException,CloneNotSupportedException {
			Hero temp=currentHero;
			currentHero=opponent;
			opponent=temp;
			currentHero.setTotalManaCrystals(currentHero.getTotalManaCrystals()+1);
			currentHero.setCurrentManaCrystals(currentHero.getTotalManaCrystals());
			currentHero.setHeroPowerUsed(false);
			for (Minion m : currentHero.getField()) {
				m.setAttacked(false);
				m.setSleeping(false);
			}
			currentHero.drawCard();
		}
		
		public void validateTurn(Hero user) throws NotYourTurnException {	
			if(user!=currentHero){
				throw new NotYourTurnException("this is not your turn");
			}
		}
		
		public void validateAttack(Minion attacker, Minion target)throws CannotAttackException, NotSummonedException,TauntBypassException, InvalidTargetException {
			if(attacker.getAttack()==0){
				throw new CannotAttackException("you cant attack wiht this minion it has 0 power");
			}
			if (attacker.isAttacked()){
				throw new CannotAttackException("you cant attack wiht this minion it attacked already ");
			}
			if(attacker.isSleeping()){
				throw new CannotAttackException("you cant attack wiht this minion it is sleep");
			}
			if(!currentHero.getField().contains(attacker)){
				throw new NotSummonedException("the card is not summoned on your field");
			}
			if (currentHero.getField().contains(target)){
				throw new InvalidTargetException("this is your minion");
			}
			if(!opponent.getField().contains(target)){
				throw new NotSummonedException("the card is not on the opponent fild");
			}
			for (int i=0; i<opponent.getField().size();i++){
				if (opponent.getField().get(i).isTaunt()&&!target.isTaunt()){
					throw new TauntBypassException("there are taunt");
				}
			}
		}
		
		public void validateAttack(Minion attacker, Hero target)throws CannotAttackException, NotSummonedException,TauntBypassException, InvalidTargetException {
			if(attacker.getAttack()<=0){
				throw new CannotAttackException("you cant attack wiht this minion it has 0 power");
			}
			if (attacker.isAttacked()==true){
				throw new CannotAttackException("you cant attack wiht this minion it attacked already ");
			}
			if(attacker.isSleeping()==true){
				throw new CannotAttackException("you cant attack wiht this minion it is sleep");
			}
			if (!currentHero.getField().contains(attacker)){
			    throw new NotSummonedException("the card is not on your field");
			}
			if (currentHero==target){
				throw new InvalidTargetException("you can't attack yourself ");
			}
			for (int i=0; i<opponent.getField().size();i++){
				if (opponent.getField().get(i).isTaunt()==true){
					throw new TauntBypassException("there are taunt");				}
			}
		}
		
		public void validateManaCost(Card card) throws NotEnoughManaException {
			if (currentHero.getCurrentManaCrystals()<card.getManaCost()){
				throw new NotEnoughManaException("you don't have enough mana");
			}
		}
		
		public void validatePlayingMinion(Minion minion)throws FullFieldException {
			if (currentHero.getField().size()==7){
				throw new FullFieldException("your field is full");
			}
		}
		
		public void validateUsingHeroPower(Hero hero)throws NotEnoughManaException, HeroPowerAlreadyUsedException {
			if(hero.isHeroPowerUsed()==true){
				throw new HeroPowerAlreadyUsedException("you cant use hero power it already used ");
			}
			if(hero.getCurrentManaCrystals()<2){
				throw new NotEnoughManaException("you don't have enough mana");
			}
		}
}