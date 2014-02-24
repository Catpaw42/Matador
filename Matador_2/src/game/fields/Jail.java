package game.fields;

public class Jail extends Field {
	
	public Jail(int nr, String name) {
		super(nr,name);
		// TODO Auto-generated constructor stub
	}
	
	private int fængselstakst = 1000;
	
	@Override
	public void message() {
	if(getFieldNumber() == 31) {
	System.out.println("Du er landet på felt nr. 31. De fængles med det samme og transporteres med det samme derover.");
	}
	if(getFieldNumber() == 11 ) {
		System.out.println("Du er på besøg i fængselet. Slikautomaten er i stykker, øv.");
		}
	}

	public int getFængselstakst() {
		return fængselstakst;
	}

}