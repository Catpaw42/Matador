package game;

import game.chance_cards.ChanceCard;
import game.chance_cards.Fine;
import game.chance_cards.GoToJailCard;
import game.chance_cards.JailSafed;
import game.chance_cards.MoneyGift;
import game.chance_cards.MovedToField;
import game.fields.Brewery;
import game.fields.Chance;
import game.fields.Field;
import game.fields.Refuge;
import game.fields.Shipping;
import game.fields.Street;
import game.fields.Street.Group;
import game.fields.Tax;

public class GameData
{
	private int startingCapital;
	private Player[] players;
	private Field[] fields;
	private ChanceCard[] cards;
	private DiceCup dice;

	public GameData()
	{
		this.startingCapital = 30000;
		this.players = null;
		this.fields = generateStandardFieldList();
		this.cards = generateStandartCardList();
		this.dice = new DiceCup();
	}

	private Field[] generateStandardFieldList()
	{
		Field[] fields = new Field[40];
		
	//					 --TYPE--     --NR--	--NAME--			--BASE RENT--	--PRICE--	--GROUP--
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
		fields[30] = new game.fields.GoToJail(31, 	"Gå i fængsel");
		fields[31] = new Street		(32, 	"Amagertorv", 			550, 		6000, 		Group.YELLOW);
		fields[32] = new Street		(33, 	"VimmelSkaftet", 		550, 		6000, 		Group.YELLOW);
		fields[33] = new Chance		(34, 	"Prøv Lykken");
		fields[34] = new Street		(35, 	"Nygade", 				600, 		6400, 		Group.YELLOW);
		fields[35] = new Shipping	(36, 	"Oslo Færgen", 			250, 		4000);
		fields[36] = new Chance		(37, 	"Prøv Lykken");
		fields[37] = new Street		(38, 	"Frederiksberg gade", 	700, 		7000, 		Group.PURPLE);
		fields[38] = new Tax		(39, 	"Extraordinær skat");
		fields[39] = new Street		(40, 	"Rådhuspladsen", 		1000, 		8000, 		Group.PURPLE);
			
		return fields;
	}
	
	private ChanceCard[] generateStandartCardList()
	{
		ChanceCard[] cards = new ChanceCard[33];
		
		cards[0] = new MoneyGift 	(1,  "De modtager Deres aktieudbytte. Modtag kr. 1000 af banken. ", 1000);
		cards[1] = new MovedToField (2,  "De rykkes til start", 1);
		cards[2] = new GoToJailCard		(3,  "Gå i fængsel. Ryk direkte til fængslet. Selv om De passerer Start,"
										+ " indkassere de ikke kr. 4000 ");
		cards[3] = new GoToJailCard		(4,  "Gå i fængsel. Ryk direkte til fængslet. Selv om De passerer Start,"
										+ " indkassere de ikke kr. 4000 ");
		cards[4] = new Fine 		(5,  "De har været en tur i udlandet og haft for mange cigaretter med "
										+ "hjem. Betal told kr. 200", 200);
		cards[5] = new Fine			(6,  "De har modtaget Deres tandlægeregning. Betal kr. 2000", 2000);
		cards[6] = new MoneyGift	(7,  "De havde en række med elleve rigtige i tipning. Modtag kr. 1000", 1000);
		cards[7] = new MoneyGift	(8,  "Deres præmieobligation er kommet ud. De modtager kr. 1000 af banken.", 1000);
		cards[8] = new MoneyGift	(9,  "Deres præmieobligation er kommet ud. De modtager kr. 1000 af banken.", 1000);
		cards[9] = new MoneyGift	(10, "Det er Deres fødselsdag. Modtag af hver medspiller kr. 200", 0);
		cards[10] = new MoneyGift	(11, "Værdien af egen avl fra nyttehaven udgør kr. 200, som De modtager af banken. ", 200);
		cards[11] = new Fine		(12, "Betal Deres bilforsikring kr. 1000" , 1000);
		cards[12] = new Fine		(13, "Ejendomsskatterne er steget, ekstraudgifterne er: kr 800 pr. hus, kr 2300 pr. hotel.", 0);
		cards[13] = new MovedToField(14, "Ryk frem til Grønningen. Hvis De passerer Start inkassér da kr. 4000 ", 25 );
		cards[14] = new MovedToField(15, "Ryk brikken frem til det nærmeste rederi og betal ejeren to gange den "
										+ "leje, han ellers er berettiget til. Hvis selskabet ikke ejes af nogen  kan De købe det af banken. ", 0);
		cards[15] = new MovedToField(16, "Ryk brikken frem til det nærmeste rederi og betal ejeren to gange den "
										+ "leje, han ellers er berettiget til. Hvis selskabet ikke ejes af nogen  kan De købe det af banken. ", 0);
		cards[16] = new MovedToField	(17, "Tag med Mols-Linjen --- flyt brikken frem, og hvis De passerer Start"
										+ " inkassér da kr. 4000 ", 16);
		cards[17] = new JailSafed 	(18, "I anledning af kongens fødselsdag benådes De herved for fængsel. "
										+ "Dette kort kan opbevares, indtil De får brug for det, eller De kan sælge det.");
		cards[18] = new JailSafed 	(19, "I anledning af kongens fødselsdag benådes De herved for fængsel. "
										+ "Dette kort kan opbevares, indtil De får brug for det, eller De kan sælge det");
		cards[19] = new MoneyGift 	(20, "Grundet dyrtiden har De fået gageforhøjelse. Modtag kr. 1000 ", 1000);
		cards[20] = new MovedToField(21, "Ryk frem til Frederiksberg Allé. Hvis De passerer Start inkassér kr. 4000 ", 12);
		cards[21] = new MoneyGift	(22, "De har vundet i Klasselotteriet. Modtag kr. 500 ", 500);
		cards[22] = new MovedToField(23, "Tag ind på Rådhuspladsen.", 40 );
		cards[23] = new MovedToField(24, "Ryk tre felter tilbage.", -3);
		cards[24] = new Fine		(25, "Oliepriserne er steget, og De skal betale: kr. 500 pr. hus,  kr. 2000 pr. hotel.", 0);
		cards[25] = new Fine		(26, "Betal kr. 3000 for reparation af Deres vogn. ", 3000);
		cards[26] = new Fine		(27, "Betal kr. 3000 for reparation af Deres vogn. ", 3000);
		cards[27] = new MoneyGift	(28, "De modtager Matador-legatet for værdigt trængende, "
										+ "stort kr. 40000 Ved værdigt trængende forstås, at Deres formue, "
										+ "d.v.s. Deres kontante penge + skøder + bygninger ikke overstiger kr. 15000 ", 0);
		cards[28] = new Fine		(29, "Kommunen har eftergivet et kvartals skat. Hæv i banken kr. 3000 ", 3000);
		cards[29] = new MoneyGift	(30, "Modtag udbytte af Deres aktier kr. 1000", 1000);
		cards[30] = new MoneyGift	(31, "Modtag udbytte af Deres aktier kr. 1000", 1000);
		cards[31] = new Fine		(32, "De har kørt frem for Fuld Stop. Betal kr. 1000 i bøde. ", 1000);
		cards[32] = new Fine		(33, "De har måttet vedtage en parkeringsbøde. Betal kr. 200 i bøde.", 200);
		
		return cards;
	}

	public ChanceCard[] getCards()
	{
		return cards;
	}

	public void setCards(ChanceCard[] cards)
	{
		this.cards = cards;
	}

	public Field[] getFields()
	{
		return fields;
	}

	public void setFields(Field[] fields)
	{
		this.fields = fields;
	}

	public int getStartingCapital()
	{
		return startingCapital;
	}

	public void setStartingCapital(int startingCapital)
	{
		this.startingCapital = startingCapital;
	}
	
	public Player[] getPlayers()
	{
		return players;
	}

	public void setPlayers(Player[] players)
	{
		this.players = players;
	}
	
	public DiceCup getDice()
	{
		return dice;
	}

	public void setDice(DiceCup dice)
	{
		this.dice = dice;
	}
}
