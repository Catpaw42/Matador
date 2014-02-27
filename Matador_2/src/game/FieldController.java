package game;

import game.fields.Chance;
import game.fields.Ownable;
import game.fields.Tax;
import gui.GUI;

public class FieldController
{

	GUI gui = new GUI();
	Board board = new Board();

	public void LandOnField(Player p, int nr)
	{

		if (board.getField(nr).getClass() == Tax.class)
		{
			taxController(p, nr);
		}

		if (board.getField(nr).getClass() == Ownable.class)
		{
			Ownable o = (Ownable) board.getField(nr);

			// someone owns this field
			if (o.getOwner() != null)
			{
				p.getAccount().withdraw(o.getRent());
				o.getOwner().getAccount().deposit(o.getRent());
			}
			// nobody owns this field
			else
			{
				String[] options = { "Buy (" + o.getPrice() + ")", "No" };
				if (gui.getUserButtonPressed(options, "Nobody owns " + o.getName() + ". Would you like to buy it?", "Field for sale!") == 0)
				{
					p.getAccount().withdraw(o.getPrice());
					o.setOwner(p);
				} else
				{

				}

			}

		}

		if (board.getField(nr).getClass() == Chance.class)
		{
			Chance c = (Chance) board.getField(nr);

		}

	}

	private void taxController(Player p, int nr)
	{
		Tax t = (Tax) board.getField(nr); // Type caster field til tax.
		if (p.getPosition() == 5) // hvis players position er 5, har han et valg. Ellers trækkes der bare penge.
		{
			String[] options = { "Pay 4000kr.", "Pay 10%" }; // array with the options thats should be on the buttons.
			int i = gui.getUserButtonPressed(options, "Choose Payment", "Tax"); // show a choice menu, with the above options.

			if (i == 0)
			{
				p.getAccount().setBalance(p.getAccount().getBalance() - t.getRevenueRate()); // update balance
			} else
			{
				p.getAccount().setBalance(p.getAccount().getBalance() * t.getTaxRate()); // update balance
			}
		} else
		{
			p.getAccount().setBalance(p.getAccount().getBalance() - t.getExtraOrdinaryRate());
		}
	}
}
