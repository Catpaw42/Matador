package game.fields;

public abstract class Field
{
	public Field(int nr)
	{
		this.fieldNumber = nr;
	}
	
	int fieldNumber;
	public abstract void message();
}
