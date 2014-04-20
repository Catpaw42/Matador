package game;

import game.fields.Ownable;
import java.awt.Color;

public class Player
{
	private int id;
	private String name;
	private Color carColor;
	private int carType;
	private int position = 1;
	private Account account = new Account();
	private boolean isBroke = false;
	private boolean inPrisson = false;
	private int twoOfAKindRollCount = 0;
	private int prisonTurnCount = 0;
	private int getOutOfJailCards = 0;

	public Player(String name, Color carColor, int carType)
	{
		this.name = name;
		this.carColor = carColor;
		this.carType = carType;
		
	}

	public int getid()
	{
		return id;
	}
	
	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public int getPosition()
	{
		return position;
	}

	public void setPlayerPosition(int position)
	{
		this.position = position;
	}

	public Account getAccount()
	{
		return account;
	}

	public void setAccount(Account account)
	{
		this.account = account;
	}

	public Color getCarColour()
	{
		return carColor;
	}

	public void setCarColour(Color carColor)
	{
		this.carColor = carColor;
	}

	public int getCarType()
	{
		return carType;
	}

	public void setCarType(int carType)
	{
		this.carType = carType;
	}

	public void setBroke(boolean broke)
	{
		this.isBroke = broke;
	}

	public boolean isBroke()
	{
		return isBroke;
	}

	public boolean isInPrisson()
	{
		return inPrisson;
	}

	public void setInPrisson(boolean inPrisson)
	{
		this.inPrisson = inPrisson;
	}

	public int getTotaltAssets()
	{
		Ownable[] o = Board.getFieldsByPlayer(this);
		int priceSum = 0;
		for (int i = 0; i < o.length; i++)
		{
			priceSum = priceSum + o[i].getPrice();
		}

		return getAccount().getBalance() + priceSum;
	}

	public int getTwoOfAKindRollCount()
	{
		return twoOfAKindRollCount;
	}

	public void setTwoOfAKindRollCount(int twoOfAKindRollCount)
	{
		this.twoOfAKindRollCount = twoOfAKindRollCount;
	}

	public int getPrisonTurnCount()
	{
		return prisonTurnCount;
	}

	public void setPrisonTurnCount(int prisonTurnCount)
	{
		this.prisonTurnCount = prisonTurnCount;
	}

	public int getGetOutOfJailCards()
	{
		return getOutOfJailCards;
	}

	public void setGetOutOfJailCards(int getOutOfJailCards)
	{
		this.getOutOfJailCards = getOutOfJailCards;
	}
	
}
