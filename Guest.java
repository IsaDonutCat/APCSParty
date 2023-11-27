/**
* Guest.java
* @author Isabella Wang
* @since 11/27/2023
* This class mainly works as a data struct
*/

public class Guest
{
	String fullName, compName;
	int seatNum = 0;
	int tableNum = 0;
	
	public Guest(String inName, String inComp) //constructor
	{
		fullName = inName;
		compName = inComp;
	}
	
	public String getComp()
	{
		//.out.println("getting Comp = " + compName);
		return compName;
	}
	
	public String getName() 
	{
		return fullName;
	}
	
	public void giveSeat(int inTable, int inSeat)
	{
		tableNum = inTable;
		seatNum = inSeat;
	}
	
	public String toString()
	{
		return compName;
	}
	
	public void printTable()//when printing by table
	{
		System.out.println(compName + " | " + fullName + ", Seat " + seatNum);
	}
	
	public void printComp() //when printing by company
	{
		System.out.println(fullName + " | Table " + tableNum + ", Seat " + seatNum);
	}
}
