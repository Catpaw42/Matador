package game.fields;

import game.Player;

public abstract class Ownable extends Field {

	private int price;
	private int rent;
	private Player owner = null;
	private Ownable[] serie;

	protected Ownable(int nr, String name, int rent, int price) {
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

	public void setOwner(Player owner) {
		this.owner = owner;
	}


	private int AntalGrunde() {
		return 0;
	}

	public abstract int getRent();

	protected int getBaseRent()
	{
		return rent;
	}

	public String toString() {
		return toString();
	}
}
