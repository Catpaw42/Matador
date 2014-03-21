package game.chance_cards;

public class MoneyGift extends ChanceCard{
	
	private int reward;

	public MoneyGift(int nr, String name, int reward) {
		super(nr, name);
		this.setReward(reward);
		// TODO Auto-generated constructor stub
	}

	public int getReward() {
		return reward;
	}

	public void setReward(int reward) {
		this.reward = reward;
	}

}
