public class Guest
{
	//construct with firstname, lastname, company, tablenum - CHECK
	//getters for everything  - CHECK (at least partially)
	String lastName, firstName, compName;
	int tableNum;
	
	public Guest(String inLast, String inFirst, String inComp)
	{
		lastName = inLast;
		firstName = inFirst;
		compName = inComp;
	}
	
	public void setTable(int assigned)
	{
		tableNum = assigned;
	}
	
	public int getTable()
	{
		return tableNum;
	}
	
	public int getComp()
	{
		return compId;
	}
	
	public String getName()
	{
		return firstName + lastName;
	}
}
