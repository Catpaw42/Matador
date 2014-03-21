package test;

import game.DiceCup;

public class Test2
{
	public static void main(String[] args)
	{
		DiceCup dice = new DiceCup();
		
		for (int i = 0; i < 100; i++)
		{
			dice.rollDice();
			System.out.println(dice.getDiceFaceValues()[0] + " ," + dice.getDiceFaceValues()[1]);
			System.out.println(dice.isTwoOfAKind());
		}
	}
}
