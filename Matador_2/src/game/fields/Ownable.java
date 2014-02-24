package game.fields;

import game.Player;

public abstract class Ownable extends Field {

	protected int price;
	protected int[] rent;
	protected Player owner = null;
	protected Ownable[] serie;
	protected int nr;

	protected Ownable(int nr, String name, int price, int[] rent) {
		super(nr, name);
		this.price = price;
		this.rent = rent;
	}
	public abstract int getnr();
	
	public Ownable[] getSerie() {
		return serie;
	}
	
	public Player getOwner() {
		return owner;
	}
	
	public int getPrice() {
		return price;
	}
	
	public boolean getStreet() {
		return false;
	}
	
	public void setOwner(Player ownerac) {
		this.owner = ownerac;
	}
	
	public char landOnField(Player player, int slag) {
		
		if(owner == null)
			return 'u';
		
		else if(owner == player) {
			return 'n';
		}
		else {
			
			int amount = rent(AntalGrunde(),slag);
			if(player.changeBalance(amount,0))
			{
				owner.changeBalance(amount, 1);
				return 'n';
			}
			
			return 'f';
		}
	}
	public abstract int rent(int number,  int multiplier);
	
	public int getRent(int multiplier) {
		return rent(AntalGrunde() ,  multiplier);
	}
	
	
	
	
	
	public String toString() {
		return toString();
	}
}
