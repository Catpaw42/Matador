package game.fields;

public class Jail extends Field {

	private boolean fængslet = false;
	
	public Jail(int nr, String name) {
		super(nr,name);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void message() {
	if(getFieldNumber() == 31) {
	System.out.println("Du er landet på felt nr. 31. De fængles med det samme og transporteres med det samme derover.");
	fængslet = true;
	}
	if(getFieldNumber() == 12 &&  fængslet == true) {
		System.out.println("Du er i fængsel");
		}
	if(getFieldNumber() == 11 &&  fængslet == false) {
		System.out.println("Du er på besøg i fængselet. Slikautomaten er i stykker, øv.");
		}
	}

	public boolean getFængslet() {
		return fængslet;
	}

	public void setFængslet(boolean fængslet) {
		this.fængslet = fængslet;
	}
}