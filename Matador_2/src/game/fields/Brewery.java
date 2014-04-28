package game.fields;

import game.Board;
import game.DiceCup;

public class Brewery extends Ownable
{
	private DiceCup diceCup;
	
	public Brewery(int nr, String name, int rent[], int price, DiceCup dice)
	{
		super(nr, name, rent, price);
		this.diceCup = dice;
	}

	@Override
	public String getMessage()
	{
		return "have landed on field " + this.getFieldNumber() + "  " + this.getName();
	}

	@Override
	public int getRent()
	{
		int dieSum = this.diceCup.getSum();
		
		return getBaseRent()[0] * getNumberOfBreweries() * dieSum;
	}
	
	public int getNumberOfBreweries()
	{
		Field[] fields = Board.getFieldsByPlayer(this.getOwner());
		int count = 0;
		for (int i = 0; i < fields.length; i++)
		{
			if(fields[i] instanceof Brewery)
				count++;			
		}
		return count;
	}
}
