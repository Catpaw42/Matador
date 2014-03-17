package game;

import game.fields.Brewery;
import game.fields.Chance;
import game.fields.Field;
import game.fields.Ownable;
import game.fields.Refuge;
import game.fields.Shipping;
import game.fields.Street;
import game.fields.Street.Group;
import game.fields.Tax;

import java.util.ArrayList;
import java.util.LinkedList;

import chance.cards.*;

public class Board
{
	private static Field[] fields = new Field[40];
	private static LinkedList<ChanceCard> chanceCards = new LinkedList<ChanceCard>();
	
	public Board(DiceCup dice)
	{//					 --TYPE--  	--NR--	--NAME--			--BASE RENT--	--PRICE--	--GROUP--
		fields[0]  = new Refuge		(1, 	"Start");
		fields[1]  = new Street		(2, 	"Rødovrevej", 			50, 		1200, 		Group.BLUE);
		fields[2]  = new Chance		(3, 	"Prøv Lykken");
		fields[3]  = new Street		(4, 	"Hvidovrevej", 			50, 		1200, 		Group.BLUE);
		fields[4]  = new Tax		(5, 	"SKAT");
		fields[5]  = new Shipping	(6, 	"Scanlines", 			250, 		4000);
		fields[6]  = new Street		(7, 	"Roskildevej", 			100, 		2000, 		Group.PINK);
		fields[7]  = new Chance		(8, 	"Prøv Lykken");
		fields[8]  = new Street		(9, 	"Valby Langgade", 		100, 		2000, 		Group.PINK);
		fields[9]  = new Street		(10, 	"Allégade", 			150, 		2400, 		Group.PINK);
		fields[10] = new Refuge		(11, 	"Fængsel");
		fields[11] = new Street		(12, 	"Frederiksberg Allé", 	200, 		2800, 		Group.GREEN);
		fields[12] = new Brewery	(13, 	"Tuborg", 				100, 		3000, 		dice);
		fields[13] = new Street		(14, 	"Bülowsvej", 			200, 		2800, 		Group.GREEN);
		fields[14] = new Street		(15,	"Gl. Kongevej", 		250, 		3200, 		Group.GREEN);
		fields[15] = new Shipping	(16, 	"Mols Linien", 			250, 		4000);
		fields[16] = new Street		(17, 	"Bernstoftsvej", 		300, 		3600, 		Group.GREY);
		fields[17] = new Chance		(18, 	"Prøv Lykken");
		fields[18] = new Street		(19, 	"Hellerupvej", 			300, 		3600, 		Group.GREY);
		fields[19] = new Street		(20, 	"Strandvejen", 			350, 		4000, 		Group.GREY);
		fields[20] = new Refuge		(21, 	"Helle");
		fields[21] = new Street		(22, 	"Trianglen", 			350, 		4400, 		Group.RED);
		fields[22] = new Chance		(23, 	"Prøv Lykken");
		fields[23] = new Street		(24, 	"Østerbrogade", 		350, 		4400, 		Group.RED);
		fields[24] = new Street		(25, 	"Grønningen", 			400, 		4800, 		Group.RED);
		fields[25] = new Shipping	(26, 	"ColorLine", 			250, 		4000);
		fields[26] = new Street		(27, 	"Bredgade", 			450, 		5200, 		Group.WHITE);
		fields[27] = new Street		(28, 	"Kgs. Nytorv", 			450, 		5200, 		Group.WHITE);
		fields[28] = new Brewery	(29, 	"Coca Cola", 			100, 		3000,		dice);
		fields[29] = new Street		(30, 	"Østergade", 			500, 		5600, 		Group.WHITE);
		fields[30] = new Refuge		(31, 	"Gå i fængsel");
		fields[31] = new Street		(32, 	"Amagertorv", 			550, 		6000, 		Group.YELLOW);
		fields[32] = new Street		(33, 	"VimmelSkaftet", 		550, 		6000, 		Group.YELLOW);
		fields[33] = new Chance		(34, 	"Prøv Lykken");
		fields[34] = new Street		(35, 	"Nygade", 				600, 		6400, 		Group.YELLOW);
		fields[35] = new Shipping	(36, 	"Oslo Færgen", 			250, 		4000);
		fields[36] = new Chance		(37, 	"Prøv Lykken");
		fields[37] = new Street		(38, 	"Frederiksberg gade", 	700, 		7000, 		Group.PURPLE);
		fields[38] = new Tax		(39, 	"Extraordinær skat");
		fields[39] = new Street		(40, 	"Rådhuspladsen", 		1000, 		8000, 		Group.PURPLE);
	
		chanceCards.add(new MoneyGift 		(1,	"De modtager Deres aktieudbytte. Modtag kr. 1000 af banken. ", 1000));
		chanceCards.add(new MovedToField 	(2, "De rykkes til start", 1));
		chanceCards.add( new GoToJail		(3, "Gå i fængsel. Ryk direkte til fængslet. Selv om De passerer Start,"
				+ " indkassere de ikke kr. 4000 "));
		chanceCards.add(new GoToJail		(4, "Gå i fængsel. Ryk direkte til fængslet. Selv om De passerer Start,"
				+ " indkassere de ikke kr. 4000 "));
		chanceCards.add( new Fine 			(5, "De har været en tur i udlandet og haft for mange cigaretter med "
				+ "hjem. Betal told kr. 200", 200));
		chanceCards.add( new Fine			(6, "De har modtaget Deres tandlægeregning. Betal kr. 2000", 2000));
		chanceCards.add( new MoneyGift		(7, "De havde en række med elleve rigtige i tipning. Modtag kr. 1000", 1000));
		chanceCards.add( new MoneyGift		(8, "Deres præmieobligation er kommet ud. De modtager kr. 1000 af banken.", 1000));
		chanceCards.add( new MoneyGift		(9, "Deres præmieobligation er kommet ud. De modtager kr. 1000 af banken.", 1000));
		chanceCards.add( new MoneyGift		(10, "Det er Deres fødselsdag. Modtag af hver medspiller kr. 200", 0));
		chanceCards.add( new MoneyGift		(11, "Værdien af egen avl fra nyttehaven udgør kr. 200, som De modtager af banken. ", 200));
		chanceCards.add( new Fine			(12, "Betal Deres bilforsikring kr. 1000" , 1000));
		chanceCards.add( new Fine			(13, "Ejendomsskatterne er steget, ekstraudgifterne er: kr 800 pr. hus, kr 2300 pr. hotel.", 0));
		chanceCards.add( new MovedToField	(14, "Ryk frem til Grønningen. Hvis De passerer Start inkassér da kr. 4000 ", 25 ));
		chanceCards.add( new MovedToField 	(15, "Ryk brikken frem til det nærmeste rederi og betal ejeren to gange den "
				+ "leje, han ellers er berettiget til. Hvis selskabet ikke ejes af nogen  kan De købe det af banken. ", 0));
		chanceCards.add( new MovedToField 	(16, "Ryk brikken frem til det nærmeste rederi og betal ejeren to gange den "
				+ "leje, han ellers er berettiget til. Hvis selskabet ikke ejes af nogen  kan De købe det af banken. ", 0));
		chanceCards.add( new MovedToField	(17, "Tag med Mols-Linjen --- flyt brikken frem, og hvis De passerer Start"
				+ " inkassér da kr. 4000 ", 16));
		chanceCards.add( new JailSafed 	(18, "I anledning af kongens fødselsdag benådes De herved for fængsel. "
				+ "Dette kort kan opbevares, indtil De får brug for det, eller De kan sælge det."));
		chanceCards.add( new JailSafed 	(19, "I anledning af kongens fødselsdag benådes De herved for fængsel. "
				+ "Dette kort kan opbevares, indtil De får brug for det, eller De kan sælge det"));
		chanceCards.add( new MoneyGift 	(20, "Grundet dyrtiden har De fået gageforhøjelse. Modtag kr. 1000 ", 1000));
		chanceCards.add( new MovedToField	(21, "Ryk frem til Frederiksberg Allé. Hvis De passerer Start inkassér kr. 4000 ", 12));
		chanceCards.add( new MoneyGift		(22, "De har vundet i Klasselotteriet. Modtag kr. 500 ", 500));
		chanceCards.add( new MovedToField	(23, "Tag ind på Rådhuspladsen.", 40 ));
		chanceCards.add( new MovedToField	(24, "Ryk tre felter tilbage.", -3));
		chanceCards.add( new Fine			(25, "Oliepriserne er steget, og De skal betale: kr. 500 pr. hus,  kr. 2000 pr. hotel.", 0));
		chanceCards.add( new Fine			(26, "Betal kr. 3000 for reparation af Deres vogn. ", 3000));
		chanceCards.add( new Fine			(27, "Betal kr. 3000 for reparation af Deres vogn. ", 3000));
		chanceCards.add( new MoneyGift		(28, "De modtager Matador-legatet for værdigt trængende, "
				+ "stort kr. 40000 Ved værdigt trængende forstås, at Deres formue, "
				+ "d.v.s. Deres kontante penge + skøder + bygninger ikke overstiger kr. 15000 ", 0));
		chanceCards.add( new Fine			(29, "Kommunen har eftergivet et kvartals skat. Hæv i banken kr. 3000 ", 3000));
		chanceCards.add( new MoneyGift		(30, "Modtag udbytte af Deres aktier kr. 1000", 1000));
		chanceCards.add( new MoneyGift		(31, "Modtag udbytte af Deres aktier kr. 1000", 1000));
		chanceCards.add( new Fine			(32, "De har kørt frem for Fuld Stop. Betal kr. 1000 i bøde. ", 1000));
		chanceCards.add( new Fine			(33, "De har måttet vedtage en parkeringsbøde. Betal kr. 200 i bøde.", 200));
	}
	
	/**
	 * Gets all the fields owned by a given player.
	 * @param p The player.
	 * @return An array containing all fields that this player owns.
	 */
	public static Ownable[] getFieldsByPlayer(Player p)
	{
		ArrayList<Ownable> temp = new ArrayList<Ownable>();
		
		for (int i = 0; i < fields.length; i++)
		{
			if(fields[i] instanceof Ownable)
				if(((Ownable)fields[i]).getOwner() == p)
					temp.add((Ownable) fields[i]);
		}
		
		Ownable[] tempArr = new Ownable[temp.size()];
		for (int i = 0; i < temp.size(); i++)
		{
			tempArr[i] = temp.get(i);
		}
		return tempArr;
	}
	
	/**
	 * Return the field at position "nr"
	 * @param nr The number of the field [1-40], this method manages the array offset.
	 * @return The field at position "nr" on the board.
	 */
	public Field getField(int nr)
	{
		return fields[nr - 1];
	}
	/**
	 * Returns all the fields.
	 * @return An array containing all fields on the board.
	 */
	public Field[] getAllFields()
	{
		return fields;
	}
	/**
	 * Removes the first element in the list and returns it.
	 * @return The first card in the list.
	 */
	public ChanceCard getChanceCard()
	{
		return chanceCards.remove();
	}
	
	/**
	 * Appends the given card to the end of the list
	 * @param card The card to append.
	 */
	public void appendChanceCard(ChanceCard card)
	{
		chanceCards.add(card);
	}
	
	public LinkedList<ChanceCard> getAllChanceCards()
	{
		return chanceCards;
	}
}
	