package game;

public class DiceCup
{	
	private Die die1;
	private Die die2;
	private int dieSum;

	public DiceCup()
	{
		die1 = new Die();
		die2 = new Die();
	}

	/**
	 * Roll the dice and return the new die-sum.
	 * @return The sum of the new facevalues after the dice have been rolled.
	 */
	public int rollDice()
	{
		dieSum = (die1.roll() + die2.roll());
		return dieSum;
	}

	/**
	 * Get the sum of the two dice
	 * @return an integer equal to the sum of the dice
	 */
	public int getSum()
	{
		return dieSum;
	}

	/**
	 * Get the current facevalues of the dice.
	 * @return An integer array with the two facevalues.
	 */
	public int[] getDiceFaceValues()
	{
		int[] DiceFaceValues = new int[2];
		DiceFaceValues[0] = die1.getFaceValue();
		DiceFaceValues[1] = die2.getFaceValue();
		return DiceFaceValues;
	}

	/**
	 * Checks if the current roll is a two of a kind roll.
	 * @return An integer equal to the facevalue [1-6] of the dice IF they are the same, or 0 if they are not the same.
	 */
	public int getTwoOfAKind()
	{
		int twoOfaKind = 0;
		if (die1.getFaceValue() == die2.getFaceValue())
			twoOfaKind = die1.getFaceValue();
		return twoOfaKind;
	}
}
