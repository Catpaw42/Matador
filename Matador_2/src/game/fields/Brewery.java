package game.fields;

public class Brewery extends Ownable{

	protected Brewery(int nr, String name, int price, int[] rent) {
		super(nr, name, price, rent);
		// TODO Auto-generated constructor stub
	}
	
	public String message (){
		return "You have landed on " + this.getName();
	}

	@Override
	public int getnr()
	{
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int rent(int number, int multiplier)
	{
		// TODO Auto-generated method stub
		return 0;
	}
	

	

}
