package game;

import java.util.Random;
/**
 * A class representing a single D6 die. 
 * @author 
 *
 */
public class Die {
	
	private int faceValue;
	private Random rand;
	
	//constructor
	public Die()
	{
		//creates a random object with the seed 42.
		rand = new Random(42);
	}

	// metode til at kaste terningen
	public int roll()
	{
		//gets a number between 0 and 5, then offsets it by 1 to matcha  regular die.
		this.faceValue = rand.nextInt(6) + 1;
		return this.faceValue;
	}

	public int getFaceValue()
	{
		return faceValue;
	}

	public void setFaceValue(int faceValue)
	{
		this.faceValue = faceValue;
	}
	
	@Override
	public String toString()
	{
		// TODO Auto-generated method stub
		return "facevalue: " + this.faceValue;
	}
}


