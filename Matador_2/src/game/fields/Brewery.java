package game.fields;

import game.DiceCup;

public class Brewery extends Ownable
{
	DiceCup diceCup;
	public Brewery(int nr, String name, int rent, int price, DiceCup dice)
	{
		super(nr, name, rent, price);
		diceCup = dice;
	}

	public String getMessage()
	{
		return "have landed on field " + this.getFieldNumber() + "  " + this.getName();
	}

	@Override
	public int getRent()
	{
		return getBaseRent() * diceCup.getSum();
	}
}
