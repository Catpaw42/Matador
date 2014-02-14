package game.fields;

public class Tax extends Field {

	private int TaxRate;
	private boolean Choise;
	
	public Tax(int nr) {
		super(nr);
		// TODO Auto-generated constructor stub
	}
	
	public int getTaxRate() {
		return TaxRate;
	}

	@Override
	public void message() {
		// TODO Auto-generated method stub
		
	}

}
