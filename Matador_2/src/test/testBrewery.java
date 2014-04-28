package test;

import game.Board;
import game.DiceCup;
import game.GameData;
import game.Player;
import game.fields.Brewery;
import game.fields.Field;

import java.awt.Color;

import org.junit.Test;

public class testBrewery
{
	@Test
	public void test()
	{
		int[] rent = {200};
		DiceCup dice = new DiceCup();
		dice.rollDice();
		Player p = new Player("hej", Color.RED, 2);
		Brewery b = new Brewery(0, "lala", rent, 200, dice);
		b.setOwner(p);
		Field[] fields = new Field[1];
		fields[0] = b;	
		
		Board board = new Board(fields, new GameData().getCards());
		
		System.out.println(Board.getFieldsByPlayer(p)[0].getName());
		System.out.println(b.getNumberOfBreweries());
		System.out.println(dice.getSum());
		System.out.println(b.getRent());		
	}

}
