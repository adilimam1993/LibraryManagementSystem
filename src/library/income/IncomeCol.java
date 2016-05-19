/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package library.income;
import java.util.ArrayList;
import library.jdbc.IncomeJDBC;

/**
 *Income Collection Class <BR>
 * <p>This class is used to insert into a library income table</p> 
 * @author Elbin Martinez, Brett Landau, Khizar Alvi, Adnan Alam
 */
public class IncomeCol {

	private ArrayList<Income> incomeList;
	private IncomeJDBC c1; 


	/**
	 * IncomeCol Default Constructor
	 */
	public IncomeCol()
	{
		incomeList = new ArrayList<Income>();
		c1 = new IncomeJDBC();
	}

	/**
         * @Author Adnan Alam
	 * Insert Method-- <BR>
	 * insert a income to the collection
	 * @param payment, Income object to be inserted
	 * @return true, if the object was inserted
	 */
	public boolean insert(Income payment)
	{
		incomeList.add(payment);
		c1.insert(payment);
		return true; 
	}

	/**
         * @Author Adnan Alam
	 * view method-- <BR>
	 * will return a ArrayList of Incomes with the matched
	 * type wanted (* for all, l for late fee, d for damages, g for donations)
	 * @param typeOfI, char used to identify what you want to view
	 * @return ArrayList, Holding Income object that matched the type 
	 */
	public ArrayList<Income> view(char typeOfI)
	{
		return c1.view(typeOfI);
		/*ArrayList<Income> list = new ArrayList<Income>();
		
		if (typeOfI == '*')
		{
			return incomeList;
		}
		else 
			if (typeOfI == 'l')
			{
				for (int i = 0; i < incomeList.size(); i++)
				{
					if (incomeList.get(i).getType() == 'l')
						list.add(incomeList.get(i));
				}
			}
			else 
				if (typeOfI == 'd')
				{
					for (int i = 0; i < incomeList.size(); i++)
					{
						if (incomeList.get(i).getType() == 'd')
							list.add(incomeList.get(i));
					}
				}
				else 
					if (typeOfI == 'g')
					{
						for (int i = 0; i < incomeList.size(); i++)
						{
							if (incomeList.get(i).getType() == 'g')
								list.add(incomeList.get(i));
						}
					}
		return list;*/
	}
	
	/**
         * @Author Elbin Martinez
	 * toString Method-- <BR>
	 * this method shows the status of the collection
	 * @return String, holding status of the object
	 */
	public String toString()
	{
		String str = "";
		str = "Income Collection:" + incomeList + "\n";
		return str; 
	}


}