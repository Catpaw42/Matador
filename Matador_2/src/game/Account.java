package game;

public class Account {
	private int balance;
	private final static int DEFAULT_START_BALANCE = 5000;

	public Account()
	{
		this(DEFAULT_START_BALANCE);
	}

	public Account(int startBalance)
	{
		this.balance = startBalance;
	}

	/**
	 * Adds to the balance of this account
	 * @param amount The amount to add, must be > 0
	 * @throws IllegalAmountException If the amount was below than 0
	 */
	public void deposit (int amount) throws IllegalAmountException
	{
		if (amount < 0)  // deposit value is negative
			throw new IllegalAmountException("Cannot deposit negative integers");
		else
			balance += amount;
	}

	/**
	 * Withdraws an amount from this accounts balance, must be smaller or equal to the total balance of the account
	 * @param amount The amount to withdraw
	 * @throws InsufficientFundsException If there were to few funds to withdraw amount
	 * @throws IllegalAmountException If amount was a negative number.
	 */
	public void withdraw (int amount) throws InsufficientFundsException, IllegalAmountException
	{
		if (amount > balance)  //limits to not withdraw more than there is
			throw new InsufficientFundsException("Inssuficient funds to withdraw: " + amount);
		else if (amount < 0) //prevents calling incorrectly with negative integers
			throw new IllegalAmountException("Cannot withdraw a negative amount");
		else //withdraw amount from the balance
			balance -= amount;
	}

	public int getBalance ()
	{
		return balance;
	}

	/**
	 * Changes the balance.
	 * @param newBalance The new balance
	 * @deprecated Don't use this, use {@link Account#withdraw(int)} and {@link Account#deposit(int)} instead
	 */
	@Deprecated
	public void setBalance (int newBalance)
	{
		balance = newBalance;
	}

	//----------------------------------------------------------------
	//custom exceptions for this account
	//----------------------------------------------------------------
	
	@SuppressWarnings("serial")
	public class IllegalAmountException extends Exception
	{
		public IllegalAmountException(String string)
		{
			super(string);
		}
	}

	@SuppressWarnings("serial")
	public class InsufficientFundsException extends Exception
	{
		public InsufficientFundsException(String string)
		{
			super(string);
		}
	}
}
