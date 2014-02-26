package game.fields;

public abstract class Field
{
	private int fieldNumber;
	private String name;
	protected boolean Ownable = false;

	public Field(int nr, String name)
	{
		this.fieldNumber = nr;
		this.setName(name);
	}

	public abstract String message();


	public int getFieldNumber() {
		return fieldNumber;
	}

	public void setFieldNumber(int fieldNumber) {
		this.fieldNumber = fieldNumber;
	}


	public String getName() {
		return name;
	}

	//Metode til at se om et felt er ledigt, js
	protected Field(String name, boolean isOwnable){
		this.name = name;
		Ownable = isOwnable;
	}

	public void setName(String name) {
		this.name = name;
	}
}
