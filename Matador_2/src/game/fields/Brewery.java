package game.fields;

public class Brewery extends Ownable{

	protected Brewery(String name, int price, int[] rent, int nr) {
		super(name, price, rent, nr);
		// TODO Auto-generated constructor stub
	}
	
	public void message (){
		if(getFieldNumber() == 12){
			System.out.println("Du er landet på Tuborg");
			
		}
		if(owner)
	}
	

	

}
