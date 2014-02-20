package game.fields;

public abstract class Field
{
	private int fieldNumber;
	private String name;
	
	public Field(int nr, String name)
	{
		this.fieldNumber = nr;
		this.setName(name);
	}
	
	
	public abstract void message();


	public int getFieldNumber() {
		return fieldNumber;
	}

	public void setFieldNumber(int fieldNumber) {
		this.fieldNumber = fieldNumber;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}
}
