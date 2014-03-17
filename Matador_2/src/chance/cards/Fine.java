package chance.cards;

public class Fine extends ChanceCard {
	
	private int fine;

	public Fine(int nr, String name, int fine) {
		super(nr, name);
		this.setFine(fine);
		// TODO Auto-generated constructor stub
	}

	public int getFine() {
		return fine;
	}

	public void setFine(int fine) {
		this.fine = fine;
	}

}
