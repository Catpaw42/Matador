package game;

import static org.junit.Assert.fail;
import game.chance_cards.ChanceCard;
import game.fields.Chance;
import game.fields.Field;

import java.awt.Color;

import org.junit.Test;

public class ChanceCardTest {
	
	Player player = new Player("TESTPLAYER", Color.blue, 2);
	Field[] Testfield = 
	{
	new Chance(1, "TestChancecard"),
	new Chance(2, "TestChancecard"),
	new Chance(3, "TestChancecard"),
	new Chance(4, "TestChancecard"),
	new Chance(5, "TestChancecard"),
	new Chance(6, "TestChancecard"),
	new Chance(7, "TestChancecard"),
	new Chance(8, "TestChancecard"),
	new Chance(9, "TestChancecard"),
	new Chance(10, "TestChancecard"),
	new Chance(11, "TestChancecard"),
	new Chance(12, "TestChancecard"),
	new Chance(13, "TestChancecard"),
	new Chance(14, "TestChancecard"),
	new Chance(15, "TestChancecard"),
	new Chance(16, "TestChancecard"),
	new Chance(17, "TestChancecard"),
	new Chance(18, "TestChancecard"),
	new Chance(19, "TestChancecard"),
	new Chance(20, "TestChancecard"),
	new Chance(21, "TestChancecard"),
	new Chance(22, "TestChancecard"),
	new Chance(23, "TestChancecard"),
	new Chance(24, "TestChancecard"),
	new Chance(25, "TestChancecard"),
	new Chance(26, "TestChancecard"),
	new Chance(27, "TestChancecard"),
	new Chance(28, "TestChancecard"),
	new Chance(29, "TestChancecard"),
	new Chance(30, "TestChancecard"),
	new Chance(31, "TestChancecard"),
	new Chance(32, "TestChancecard"),
	new Chance(33, "TestChancecard"),
	new Chance(34, "TestChancecard"),
	new Chance(35, "TestChancecard"),
	new Chance(36, "TestChancecard"),
	new Chance(37, "TestChancecard"),
	new Chance(38, "TestChancecard"),
	new Chance(39, "TestChancecard"),
	new Chance(40, "TestChancecard"),
	new Chance(41, "TestChancecard"),
	new Chance(42, "TestChancecard"),
	};
	GameData gamedata = new GameData();
	Board board = new Board(Testfield, gamedata.getCards());
	FieldController fieldcontroller = new FieldController(board);
	
	@Test
	public void ChanceCardTest() 
	{
	for (int i = 0; i < gamedata.getCards().length; i++) {
	ChanceCard chanceCard = board.getChanceCard();
	fieldcontroller.LandOnField(player, 1);
	
	System.out.println("Test number" + (i+1) + "/" + gamedata.getCards().length);
	System.out.println("Player position:" + player.getPosition());
	System.out.println("Player Balance:" + player.getAccount().getBalance());
	System.out.println("Player get out of jail cards:" + player.getGetOutOfJailCards());
	System.out.println("Chance card number:" + chanceCard.getCardNumber());
	System.out.println("Chance card name:" + chanceCard.getCardName());
	System.out.println();

	}
		
	}
	

}
