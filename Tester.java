import java.io.File;
import java.util.Scanner;
/**
* Tester.java
* @author Isabella Wang
* @since 11/27/2023
* This class contains main and calls methods of planner, which is a Party object
*/
public class Tester
{
	public static void main(String[] args) //counts the stuff AFTER the class name
	{
	
		if (args.length != 2) 
		{
			System.out.println("Incorrect Number of Arguments");
			return;
		}
		
		File preGuests = new File(args[0]);
		File compList = new File(args[1]);
		
		if (!preGuests.exists() || !compList.exists())
		{
			System.out.println("File not found.");
			return;
		}
		
		//this section is to get inputs to avoid magiNum
		int tableCt;
		
		Party planner = new Party();
		if (!planner.loadComps(compList) || !planner.loadGuests(preGuests)) //comps  must be loaded before guests
			return;
		System.out.println("Add remaining guests: \n");
		planner.addGuests();
		planner.assignSeats();
		planner.printCompanies();
		planner.printTables();
		
		System.out.println("Find guest's seat: \n");
		planner.findPerson();
		
		planner.inputter.close(); //close scanner since i can't have multiple so made this public static scanner
	}
}
