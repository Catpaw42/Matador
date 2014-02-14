package game.fields;

public abstract class Field
{
	private int fieldNumber;
	
	public Field(int nr)
	{
		this.fieldNumber = nr;
	}
	
	public abstract void message();
}
