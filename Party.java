import java.util.Scanner;
import java.util.ArrayList;
import java.io.File;
import java.io.FileNotFoundException;
import java.lang.ArrayIndexOutOfBoundsException;

/**
* Party.java
* @author Isabella Wang
* @since 11/27/2023
* This class loads the files, lets user manually add guests, sorts the guests and assigns seats;
*/

public class Party
{
	int guestMax, tableNum, seatNum; //avoid magic numbers;
	ArrayList<String> compNames = new ArrayList<String>();
	ArrayList<Guest> unsortGuests = new ArrayList<Guest>();
	ArrayList<Guest> sortedGuests = new ArrayList<Guest>();
	Guest[][] tables;
	public static Scanner inputter = new Scanner(System.in);
	
	public Party() //avoid magiNum + calc guestMax + create the tables array
	{	
		System.out.print("Number of Tables:");
		tableNum = inputter.nextInt();
		
		System.out.print("Number of Seats per Table:");
		seatNum = inputter.nextInt();
		
		guestMax = seatNum * tableNum;
		tables = new Guest[tableNum][seatNum];
		String clearBuff = inputter.nextLine();
	}
	
	public boolean loadComps(File inComp)//return false if error, loads company list into ArrayList<String> 
	{
		try
		{
			Scanner readComp = new Scanner(inComp);
			String[] compArr;

			while(readComp.hasNextLine())
			{
				try
				{
					if (compArr.length == 2)
					{
					compArr = readComp.nextLine().split(",");
					//System.out.println(compArr[1]);
					compNames.add(compArr[1]); //only need string
					}
				}
				catch (ArrayIndexOutOfBoundsException C)
				{
					System.out.println("Info missing in company list.");
					C.printStackTrace();
					readComp.close();
					return false;
				}
			}
			
			System.out.println("Company list successfully loaded.");
			readComp.close();
			return true;
		}
		catch (FileNotFoundException c)
		{
			System.out.println("Company list not found.");
			c.printStackTrace();
			return false;
		}
	}
	
	public boolean loadGuests(File inGuest)//return false if error, load guests from csv file
	{
		try
		{
			Scanner readGuest = new Scanner(inGuest);
			String[] guestArr;
			String comp;
			
			while(readGuest.hasNextLine())
			{
				try
				{
					guestArr = readGuest.nextLine().split(",");
					comp = compNames.get(Integer.parseInt(guestArr[3]) - 1); //account for the fact that comp IDs start from 1 while index of arraylists start from 0
					unsortGuests.add(new Guest(guestArr[2] + " " + guestArr[1], comp)); //concatenate the last name,first name of csv file
				}
				catch (ArrayIndexOutOfBoundsException G)
				{
					System.out.println("Info missing in guest list.");
					G.printStackTrace();
					readGuest.close();
					return false;
				}
			}
			
			readGuest.close();
			System.out.println("Pre-registered guests successfully loaded.");
			return true;
		}
		catch (FileNotFoundException g)
		{
			System.out.println("Pre-registered guest list not found.");
			g.printStackTrace();
			return false;
		}
	}
	
	public void addGuests () //manually adds guests before sorting
	{
		String inName;
		
		while (unsortGuests.size() < guestMax) //originally had this in its own method before realized could just slap here
		{
			System.out.print("Guest's full name:");
			inName = inputter.nextLine();
			System.out.print("Guest's company name:");
			unsortGuests.add(new Guest(inName, inputter.nextLine())); //dont need another variable, just do next line
		}
		
		//guestList.sort(); //turns out, arraylist has sort, but only works for int and string. test to see if toString can do it for me/update: it does not AHHHHHH*/
		sortGuests();
		return;
	}
	
	public void sortGuests() //sorts them by adding them to new arraylist in alphabetical order
	{
		for (Guest z : unsortGuests)
			sortedGuests.add(insertGuest(z.getComp()), z);
			
		unsortGuests.clear(); //save memroy
	}
	
	public int insertGuest(String theirCom) //finds alphabetical position in new arraylist
	{
		int LEN = sortedGuests.size();
		int index = LEN - 1;
		
		if (LEN == 0)
			return 0;
		else
		{
			while (index > 0 && sortedGuests.get(index).getComp().compareTo(theirCom) > 0)
				index--;
			
			return index;
		}
	}
	
	public void assignSeats() //cycles thru the tables for the same company, resets when diff company
	{
		String holdComp = sortedGuests.get(0).getComp(); //if guest.getComp isn't equal, the company changed;
		int empTable = 0; //index of the lowest empty table
		int tableCur = 0;
		int seatCur = 0;
		int lIMit = sortedGuests.size();
		for (int x = 0; x < lIMit; x++)
		{
			//System.out.println("did if work?? \n"+holdComp+"\n"+sortedGuests.get(x).getComp()+"\n\n\n\n\n\n\n\n");
			if (!sortedGuests.get(x).getComp().equals(holdComp))//if new company
			{
				//System.out.println("resetting Table");
				tableCur = empTable; //set to the earliest empty table
				holdComp = sortedGuests.get(x).getComp(); 
			}
			//System.out.println("did if work?? \n"+holdComp+"\n"+sortedGuests.get(x).getComp()+"\n\n\n\n\n\n\n\n");
			seatCur = findSeat(tableCur);
			//sortedGuests.get(x).printTable();
			if (seatCur == 9) //if at max index
			{
				empTable++; //the table is filled up.
				//System.out.println("fill table " + empTable);
			}
			
			if (empTable >= 10) //if every table is filled up, stp[ sorting;
			{
				break;
			}
			
			tables[tableCur][seatCur] = sortedGuests.get(x);
			sortedGuests.get(x).giveSeat(tableCur +1,seatCur+1);
			tableCur++;
			
			if (tableCur == 10)
			{
				while (sortedGuests.get(x).getComp().equals(holdComp))
				{
					x++;
					if (x>guestMax)
						break;
				}
				
				if (x>guestMax)
						break;
			}
			//sortedGuests.get(x).printComp();
		}
	}
	
	public int findSeat(int inTable) //return -1 if no seat found (everything has an object/not null), else return index for seat
	{//why do you keep on returning 10???
		for (int j = 0; j < seatNum; j++)
		{
			//System.out.println("run loop " + inTable); //why is intable becoming 10??
			
			if (tables[inTable][j] == null)
			{
				//System.out.println("returning");
				return j;
			}
		}
		
		return -1;
	}
	
	public void printTables () //print by tables
	{
		for (int k = 0; k < tableNum; k++)
		{
			System.out.println("TABLE " + (k+1)); //+1 to adjust for computer vs human counting
			
			for (int l = 0; l < seatNum; l++)
			{
				if (tables[k][l] != null)
					tables[k][l].printTable();
			}
			System.out.println();
		}
	}
	
	public void printCompanies() //prints by company
	{
		String prevComp = "";
		
		for (Guest y : sortedGuests)
		{
			if (!prevComp.equals(y.getComp()))
			{
				System.out.println();
				prevComp = y.getComp();
				System.out.println(prevComp.toUpperCase());
			}
			
			y.printComp();
		}
	}
	
	public void findPerson() //find person by first finding the first instance of the comp ind then cycling thru until name is found or it doesn't exist in that company
	{	
		System.out.print("Enter the full name of the guest:");
		String findName = inputter.nextLine();
		System.out.print("Enter the company the guest works for:");
		String findComp = inputter.nextLine();
		
		int searchFrom = compInd(findComp);
		
		if (searchFrom < 0)
		{
			System.out.println("This person could not be found.");
			return;
		}
		
		while (sortedGuests.get(searchFrom).getComp().equals(findComp))
		{
			if (sortedGuests.get(searchFrom).getName().equals(findName))
			{
				sortedGuests.get(searchFrom).printComp();
				return;
			}
			searchFrom++;
		}
		
		System.out.println("This person could not be found.");
		return;
	}
	
	public int compInd(String searchFor) //find first instance of a company
	{
		int siZe = sortedGuests.size();
		
		for (int m = 0; m < siZe; m++)
		{
			if(sortedGuests.get(m).getComp().equals(searchFor))
				return m;
		}
		
		return -1;//not found
	}
}
