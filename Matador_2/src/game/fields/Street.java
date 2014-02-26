package game.fields;

public class Street extends Ownable {

	private int houses, number = 0;
	private int housePrice;
	
	public Street(int nr, String name, int housePrice, int number)
	{
		super(nr, name, rent, price);
		// TODO Auto-generated constructor stub
		this.number = number;
		this.housePrice = housePrice;
	}

	@Override
	public String message() {
		return "You have landed on " + this.getName();
	}

	@Override
	public int getnr() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int rent(int number, int multiplier) {
		// TODO Auto-generated method stub
		return 0;
	}
	public int getHouses(){
		return houses;
	}
	
	public void setHouses(int houses){
		this.houses = houses;
	}
	
	public int getHousePrice(){
		return housePrice;
	}
	
	

}
