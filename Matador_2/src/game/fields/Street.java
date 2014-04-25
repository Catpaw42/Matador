package game.fields;

import game.Board;

public class Street extends Ownable
{
	public enum Group
	{
		BLUE(2),
		PINK(3),
		GREEN(3),
		GREY(3),
		RED(3),
		WHITE(3),
		YELLOW(3),
		PURPLE(2);

		private int groupSize;

		Group(int size)	
		{
			this.groupSize = size;
		}

		int getGroupSize()
		{
			return this.groupSize;
		}
	}

	private Group group;
	private int houses = 0;

	public Street(int nr, String name, int rent[], int price, Group group)
	{
		super(nr, name, rent, price);
		this.group = group;
	}

	@Override
	public String getMessage()
	{
		return "have landed on field " + this.getFieldNumber() + "  " + this.getName();
	}

	@Override
	public int getRent()
	{
		if (houses == 0)
			return getSeriesMultiplier() * getBaseRent()[0] ;

		else 
			return getBaseRent()[houses];

	}

	/**
	 * Returns a multiplier 1 or 2 depending on how many fields of a color a player owns.
	 * @return 2 if the player owns all fields in this color, 1 otherwise.
	 */
	public int getSeriesMultiplier()
	{
		int count = 0;
		Field[] ownersFields = Board.getFieldsByPlayer(this.getOwner());
		for (int i = 0; i < ownersFields.length; i++)
		{
			if(ownersFields[i].getClass() == Street.class)
				if(((Street) ownersFields[i]).getGroup() == this.group)
					count++;
		}
		return count == this.group.getGroupSize() ? 2 : 1;
	}

	private Group getGroup()
	{
		return this.group;
	}

	public int getHouses()
	{
		return this.houses;
	}

	public void setHouses(int houses)
	{
		this.houses = houses;
	}
}
