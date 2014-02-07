package game;

public class Dice {
			
			int dice1, dice2;
			
			// konstrukt�r
			public void dice()
			{
			    roll(); // kalder kast() og s�tter v�rdien af terningerne til mellem 1-6
			}
		
			// metode til at kaste terningen
			public void roll()
			{
			// find en tilf�ldig side for begge terninger
			double randomnumber = Math.random();
			dice1 = (int) (randomnumber * 6 + 1);
			randomnumber = Math.random();
			dice2 = (int) (randomnumber * 6 + 1);
			}
	}


