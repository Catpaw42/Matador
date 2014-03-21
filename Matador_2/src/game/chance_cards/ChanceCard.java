package game.chance_cards;

import game.Player;

public class ChanceCard 
{
	private int cardNumber;
	private String cardName;
	private Player holder = null;
	
	public ChanceCard(int nr, String name)
	{
		this.cardNumber = nr;
		this.setCardName(name);
	}

	public String getCardName() {
		return cardName;
	}

	public void setCardName(String cardName) {
		this.cardName = cardName;
	}

	public int getCardNumber() {
		return cardNumber;
	}

	public void setCardNumber(int cardNumber) {
		this.cardNumber = cardNumber;
	}

	public Player getHolder() {
		return holder;
	}

	public void setHolder(Player holder) {
		this.holder = holder;
	}
	
}
