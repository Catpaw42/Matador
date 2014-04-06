package db_communication;

import java.awt.Color;

public class PlayerDTO {
	@Min(1)
	@Max(6)
	private int id; // i omraadet 1-6
	@Size(min = 2, max = 20)
	private String name; // 2-20 karakterer
	
	private int fieldPosition; // felt 1-40
	private int accountBalance;
	private Color carColor;
	private int carType;
	
	public PlayerDTO(){
		
	}
	
	public PlayerDTO(int id, String name) {
		this.id = id;
		this.name = name;

	}

	public int getid() {
		return id;
	}

	public void setid(int id) {
		this.id = id;
	}

	public String name() {
		return name;
	}

	public void setname(String name) {
		this.name = name;
	}

	public int getAccountBalance() {
		return accountBalance;
	}

	public void setAccountBalance(int accountBalance) {
		this.accountBalance = accountBalance;
	}

	public Color getCarColor() {
		return carColor;
	}

	public void setCarColor(Color carColor) {
		this.carColor = carColor;
	}

	public int getCarType() {
		return carType;
	}

	public void setCarType(int carType) {
		this.carType = carType;
	}
	
	@Override
	public String toString() {
		return id + "\t" + name + "\n" ;
	}

	public int getFieldPosition() {
		return fieldPosition;
	}

	public void setFieldPosition(int fieldPosition) {
		this.fieldPosition = fieldPosition;
	}
}
