package game.fields;

public abstract class Field
{
	private int fieldNumber;
	private String name;
	
	public Field(int nr, String name)
	{
		this.fieldNumber = nr;
		this.name = name;
	}
	
	public abstract void message();
}
