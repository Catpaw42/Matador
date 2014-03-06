package chance.cards;

import game.fields.Chance;

public class MovedToField extends ChanceCard {

	private int moveAmount;
	
	public MovedToField(int nr, String name, int moveAmount) {
		super(nr, name);
		this.moveAmount = moveAmount;
		// TODO Auto-generated constructor stub
	}

}
