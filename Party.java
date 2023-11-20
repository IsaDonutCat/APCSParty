import java.io.File;
import java.util.Scanner;
import java.lang.ArrayList;
public class Party
{
	//have array of arraylists of guests
	//get files as constructor, read into
	//add people to arrayList if not already full
	//include print functions here
	ArrayList<String> comps = new ArrayList<String>;
	ArrayList<Guest> preSort = new ArrayList<Guest>;
	ArrayList<Table>[] tables = new ArrayList<Table>[10];
	ArrayList<Guest>[] sortedGuests = new ArrayList<Guest>[16];
	
	public Party(File guests, File companies) //takes companies and creates an array so printing is easier and referring is easier. 
	{
		Scanner fileReader = new Scanner(companies);//compId, companyName
		String[] parts;
		
		while (fileReader.hasNextLine())
		{
			parts = fileReader.nextLine().split(",");
			comps.add(parts[1]);//1 is the company name
		}
		
		fileReader.close();
		
		
		fileReader = new Scanner(guests);
		
		while (fileReader.hasNextLine())
		{
			parts = fileREader.nextLine().split(",");
			preSort.add(new Guest (parts[1], parts[2], comps.get(Integer.parseInt(parts[3]) - 1))); //lastname, firstname, get corresponding string from arraylist at compId(human counting)-1
		}
	}
	
	public boolean maxSize()
	{
		int sum = 0;
		int len = tables.length;
		
		for (int j = 0; j < len ; j++)
		{
			sum+= tables[j].size();
		}
		
		if (size >= 100)
			return true;
		else
			return false;
	}
	
	public void assignGuests(String addFirst, String addLast, String addComp)
	{
		
	}
	
	public void sortGuests()
	{
		int len = guestList.size();
		int tempComp;
		
		for (int i = len - 1; i >= 0; i--)
		{
				tempComp = guestList.get(i).getCompId();
				tables[tempComp].addGuest();
		}
	}
}
