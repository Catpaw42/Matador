package game.fields;

import game.DiceCup;

public class Brewery extends Ownable
{

	protected Brewery(int nr, String name, int rent, int price)
	{
		super(nr, name, rent, price);
	}

	public String message()
	{
		return "You have landed on " + this.getName();
	}

	@Override
	public int getnr()
	{
		return 0;
	}

	@Override
	public int getRent()
	{
		return getRent() * DiceCup.getInstance().getSum();
	}
}
