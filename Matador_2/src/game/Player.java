package game;

import java.awt.Color;

public class Player {

	private String name;
	private Color carColor;
	private int carType;
	private int position;
	private Account accountOb = new Account();
	private boolean isBroke;
	
	public Player(String name, Color carColor, int carType)
	{
		this.name = name;
		this.carColor = carColor;
		this.carType = carType;
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
	public Color getCarColour() {
		return carColor;
	}
	
	public void setCarColour(Color carColor) {
		this.carColor = carColor;
	}
	
	public int getCarType() {
		return carType;
	}
	
	public void setCarType(int carType) {
		this.carType = carType;
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
