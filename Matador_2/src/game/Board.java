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

public class Board
{
	private static Field[] fields = new Field[40];;
	
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
		fields[28] = new Brewery	(13, 	"Coca Cola", 			100, 		3000,		dice);
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
}
	