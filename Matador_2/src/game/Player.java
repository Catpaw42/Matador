package game;

public class Player {

	private String name;
	private String carColour;
	private String carType;
	private int position;
	private Account accountOb = new Account();

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getPosition() {
		return position;
	}
	public void setFieldPlace(int position) {
		this.position = position;
	}
	public Account getAccountOb() {
		return accountOb;
	}
	public void setAccountOb(Account accountOb) {
		this.accountOb = accountOb;
	}
	public String getcarColour() {
		return carColour;
	}
	
	public void setcarColour(String carColour) {
		this.carColour = carColour;
	}
	
	public String getcarType() {
		return carType;
	}
	
	public void setcarType(String carType) {
		this.carColour = carType;
	}
}
