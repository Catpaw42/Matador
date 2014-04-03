package game;

import game.chance_cards.ChanceCard;
import game.fields.Field;
import game.fields.Ownable;

import java.util.ArrayList;
import java.util.LinkedList;

public class Board
{
	private static Field[] fields;
	private LinkedList<ChanceCard> chanceCards = new LinkedList<ChanceCard>();
	
	public Board(Field[] fields, ChanceCard[] cards)
	{
		for (int i = 0; i < cards.length; i++)
		{
			chanceCards.add(cards[i]);
		}
		
		Board.fields = fields;
	}
	
	/**
	 * Gets all the fields owned by a given player.
	 * @param p The player.
	 * @return An array containing all fields that this player owns.
	 */
	public static Ownable[] getFieldsByPlayer(Player p)
	{
		ArrayList<Ownable> temp = new ArrayList<Ownable>();
		
		for (int i = 0; i < fields.length; i++)
		{
			if(fields[i] instanceof Ownable)
				if(((Ownable)fields[i]).getOwner() == p)
					temp.add((Ownable) fields[i]);
		}
		
		Ownable[] tempArr = new Ownable[temp.size()];
		for (int i = 0; i < temp.size(); i++)
		{
			tempArr[i] = temp.get(i);
		}
		return tempArr;
	}
	
	/**
	 * Return the field at position "nr"
	 * @param nr The number of the field [1-40], this method manages the array offset.
	 * @return The field at position "nr" on the board.
	 */
	public Field getField(int nr)
	{
		return fields[nr - 1];
	}
	/**
	 * Returns all the fields.
	 * @return An array containing all fields on the board.
	 */
	public Field[] getAllFields()
	{
		return fields;
	}
	/**
	 * Removes the first element in the list and returns it.
	 * @return The first card in the list.
	 */
	public ChanceCard getChanceCard()
	{
		return chanceCards.remove();
	}
	
	/**
	 * Appends the given card to the end of the list
	 * @param card The card to append.
	 */
	public void appendChanceCard(ChanceCard card)
	{
		chanceCards.add(card);
	}
	
	public LinkedList<ChanceCard> getAllChanceCards()
	{
		return chanceCards;
	}
}
	