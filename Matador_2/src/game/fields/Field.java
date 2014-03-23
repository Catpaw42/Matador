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

	public abstract String getMessage();
	
	public int getFieldNumber()
	{
		return this.fieldNumber;
	}

	public void setFieldNumber(int fieldNumber)
	{
		this.fieldNumber = fieldNumber;
	}


	public String getName()
	{
		return this.name;
	}

	public void setName(String name)
	{
		this.name = name;
	}
}
