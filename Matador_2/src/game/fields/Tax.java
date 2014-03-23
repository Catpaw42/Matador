package game.fields;

public class Tax extends Field {

	private final int taxRate = 1/10;
	private final int revenueRate = 4000;
	private final int extraOrdinaryRate = 2000;
	
	public Tax(int nr, String name)
	{
		super(nr,name);
	}
	
	public int getTaxRate()
	{
		return this.taxRate;
	}
	
	public int getRevenueRate()
	{
		return this.revenueRate;
	}
	
	public int getExtraOrdinaryRate()
	{
		return this.extraOrdinaryRate;
	}

	@Override
	public String getMessage()
	{
		return "have landed on field " + this.getFieldNumber() + "  " + this.getName();
	}

}
