package game;

import java.awt.Color;
import java.util.ArrayList;

import game.fields.*;
import gui.FieldPanel;
import gui.FieldPanel.Builder;

public class Board
{
	private static Field[] fields = new Field[40];;
	
	public Board()
	{
		fields[0]  = new Refuge		(1, "Start");
		fields[1]  = new Street		(2, "Rødovrevej");
		fields[2]  = new Chance		(3, "Prøv Lykken");
		fields[3]  = new Street		(4, "Hvidovrevej");
		fields[4]  = new Tax		(5, "SKAT");
		fields[5]  = new Shipping	(6, "Scanlines");
		fields[6]  = new Street		(7, "Roskildevej");
		fields[7]  = new Chance		(8, "Prøv Lykken");
		fields[8]  = new Street		(9, "Valby Langgade");
		fields[9]  = new Street		(10, "Allégade");
		fields[10] = new Jail		(11, "Fængsel");
		fields[11] = new Street		(12, "Frederiksberg Allé");
		fields[12] = new Brewery	(13, "Tuborg", price, rent);
		fields[13] = new Street		(14, "Bülowsvej");
		fields[14] = new Street		(15,"Gl. Kongevej");
		fields[15] = new Shipping	(16, "Mols Linien");
		fields[16] = new Street		(17, "Bernstoftsvej");
		fields[17] = new Chance		(18, "Prøv Lykken");
		fields[18] = new Street		(19, "Hellerupvej");
		fields[19] = new Street		(20, "Strandvejen");
		fields[20] = new Refuge		(21, "Helle");
		fields[21] = new Street		(22, "Trianglen");
		fields[22] = new Chance		(23, "Prøv Lykken");
		fields[23] = new Street		(24, "Østerbrogade");
		fields[24] = new Street		(25, "Grønningen");
		fields[25] = new Shipping	(26, "ColorLine");
		fields[26] = new Street		(27, "Bredgade");
		fields[27] = new Street		(28, "Kgs. Nytorv");
		fields[28] = new Brewery	(13, "Coca Cola", price, rent);
		fields[29] = new Street		(30, "Østergade");
		fields[30] = new Jail		(31, "Gå i fængsel");
		fields[31] = new Street		(32, "Amagertorv");
		fields[32] = new Street		(33, "VimmelSkaftet");
		fields[33] = new Chance		(34, "Prøv Lykken");
		fields[34] = new Street		(35, "Nygade");
		fields[35] = new Shipping	(36, "Oslo Færgen");
		fields[36] = new Chance		(37, "Prøv Lykken");
		fields[37] = new Street		(38, "Frederiksberg gade");
		fields[38] = new Tax		(39, "Extraordinær skat");
		fields[39] = new Street		(40, "Rådhuspladsen");
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
	