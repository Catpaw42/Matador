package game.fields;

import game.Board;

public class Shipping extends Ownable
{	
	public Shipping(int nr, String name, int rent, int price)
	{
		super(nr, name, rent, nr);;
	}

	@Override
	public String getMessage()
	{
		return "You have landed on " + this.getName();
	}

	@Override
	public int getRent()
	{
		return (int)(Math.pow(2, getNumberOfShippings()) * getBaseRent());
	}
	
	private int getNumberOfShippings()
	{
		Field[] fields = Board.getFieldsByPlayer(this.getOwner());
		int count = 0;
		for (int i = 0; i < fields.length; i++)
		{
			if(fields[i] instanceof Shipping)
				count++;			
		}
		return count;
	}
}
