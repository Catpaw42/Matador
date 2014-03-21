package game.chance_cards;

public class MovedToField extends ChanceCard {

	private int moveAmount;
	
	public MovedToField(int nr, String name, int moveAmount) {
		super(nr, name);
		this.setMoveAmount(moveAmount);
		// TODO Auto-generated constructor stub
	}

	public int getMoveAmount() {
		return moveAmount;
	}

	public void setMoveAmount(int moveAmount) {
		this.moveAmount = moveAmount;
	}

}
