import java.io.File;
import java.util.Scanner;
import java.io.FileNotFoundException;

public class partyTester
{
	public static void main (String[] args)
	{
		String[] test;
		
		if (args.length != 3)//not enough arguments
		{
			return;
		}
		
		Scanner checker;
		
		try //this entire try catch is to test if files can be opened and at least have right number of columns
		{
			File guests = new File(args[1]);
			File companies = new File(args[2]);
			
			checker = new Scanner(guests);
			if (!checker.hasNextLine())//got an empty file
			{
				checker.close();
				return;
			}
			else
			{
				test = checker.nextLine().split(",");
				if (test.size != 4) //impropery formated guest list
				{
					checker.close();
					return;
				}
			}
			checker.close();
			
			checker = new Scanner(companies);
			if (!checker.hasNextLine())//got an empty file
			{
				checker.close();
				return;
			}
			else
			{
				test = checker.nextLine().split(",");
				if (test.size != 2) //impropery formated guest list
				{
					checker.close();
					return;
				}
				checker.close();
			}
		}
		catch (FileNotFoundException e)
		{
			return;
		} //end of try-catch for opening files
		
		Party createParty = new Party(File guests, File companies);
		
		while (!createParty.atMax())
		{
			createParty.addGuest();
		}
		
		createParty.assignGuests();
		createParty.printCompRoster();
		createParty.printSeatChart();
	}
}
