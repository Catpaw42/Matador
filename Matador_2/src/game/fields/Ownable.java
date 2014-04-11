package game.fields;

import game.Player;

public abstract class Ownable extends Field
{
	private int price;
	private int rent[];
	private Player owner = null;

	protected Ownable(int nr, String name, int[] rent, int price)
	{
		super(nr, name);
		this.rent = rent;
		this.price = price;
	}
	
	public abstract int getRent();

	public Player getOwner()
	{
		return this.owner;
	}

	public int getPrice()
	{
		return this.price;
	}

	public void setOwner(Player owner)
	{
		this.owner = owner;
	}

	protected int[] getBaseRent()
	{
		return rent;
	}
}
