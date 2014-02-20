package game;

import java.awt.Color;

public class Player {

	private String name;
	private String carColour;
	private String carType;
	private int position;
	private Account accountOb = new Account();
	private boolean isBroke;
	
	public Player(String name, Color carColor, int carType)
	{
		this.name = name;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getPosition() {
		return position;
	}
	public void setPlayerPosition(int position) {
		this.position = position;
	}
	public Account getAccount() {
		return accountOb;
	}
	public void setAccount(Account account) {
		this.accountOb = account;
	}
	public String getCarColour() {
		return carColour;
	}
	
	public void setCarColour(String carColour) {
		this.carColour = carColour;
	}
	
	public String getCarType() {
		return carType;
	}
	
	public void setCarType(String carType) {
		this.carColour = carType;
	}
	public void setBroke(boolean broke)
	{
		this.isBroke = broke;
	}
	
	public boolean isBroke()
	{
		return isBroke;
	}
}
