package game.fields;

import game.Player;

public abstract class Ownable extends Field {

	protected static int price;
	protected static int[] rent;
	protected Player owner = null;
	protected Ownable[] serie;
	protected int nr;

	protected Ownable(int nr, String name, int[] rent, int price) {
		super(nr, name);
		
		
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


	private int AntalGrunde() {
		// TODO Auto-generated method stub
		return 0;
	}
	public abstract int rent(int number,  int multiplier);

	public int getRent(int multiplier) {
		return rent(AntalGrunde() ,  multiplier);
	}



	public String toString() {
		return toString();
	}
}
