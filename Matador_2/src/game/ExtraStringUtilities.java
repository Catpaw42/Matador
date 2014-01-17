package game;

public class ExtraStringUtilities
{
	/**
	 * Used to fit a text-string into a given container.
	 * @param text The string to format.
	 */
	public static String fitToWidth(String text, int maxLength, int endTollerance)
	{
		String[] words = text.split(" ");
		String result = "";
		String rowBuilder = "";
		int i = 0;
		while(i < words.length)
		{
			int x = maxLength - rowBuilder.length();
			//hvis der er mere end en plads
			if (x > 1)
			{
				//hvis length af ordet er større end den tilbageværende plads
				if (words[i].length() > x)
				{
					//hvis den tilbageværende plads er større end tolerencen
					if (x > endTollerance)
					{
						rowBuilder = rowBuilder + " " + words[i].substring(0, endTollerance - 2) + "-";
						//TODO: setup special cases for punktuation
						result = result + rowBuilder + "\n";
						rowBuilder = words[i].substring(endTollerance - 2);
						i++;
					}
					//hvis den tilbageværende plads er mindre end tollerencen
					else
					{
						//tilføj et linebreak, øg IKKE i
						result = result + rowBuilder + "\n";
						rowBuilder = "";
					}
				}
				//hvis længden af ordet er mindre end en tilbageværende plads
				else
				{
					//tilføj mellemrum og ord.
					rowBuilder = rowBuilder + (rowBuilder.length() == 0 ? "" : " ") + words[i]; 
					i++;
				}
			} 
			//hvis der ikke er mere end en plads
			else
			{
				//tilføj row til result, tilføj <br>, og reset row
				result = result + rowBuilder + "\n";
				rowBuilder = "";
			}
		}
		result = result + rowBuilder;
		return result;
	}
	public static void main(String[] args)
	{
		String t = "hej jeg er en ny testmetode, lad os lege teste leg i haven!! Her i havernes have har vi i dag ikke særlig mange folk at lege med";
		String t2 = fitToWidth(t, 35, 4);
		System.out.println(t2);
	}
}

