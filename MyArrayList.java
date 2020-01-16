package project4;
/**
 * Alifa Faruk
 * Project 4
 * Due December 5, 2018
 */
import java.util.ArrayList;
import java.util.Collections;
public class MyArrayList<E extends Comparable<E>> extends ArrayList<E>{

	private static final long serialVersionUID = 1L;
	
	/**
	 *Check if the arrayList is sort or not. 
	 *Done by looping through the arrayList and comparing the values next to each other. 
	 *Return true if it is lexicographical order
	 *Return false if not in lexicographical order
	 */
	
	
	public boolean isSorted() {
		E nextObj;
		//check if arrayList is empty.. get first value 
		if (!this.isEmpty()) 
		{
			for (int i = 1, x=0; i < this.size(); i++,x++)
			{
				nextObj=this.get(x);
				E secondObj = this.get(i);
				//get second value and compareTo first
				if (nextObj.compareTo(secondObj) > 0) 
				{
					return false;
				} 
				//nextObj = secondObj;
			}
		} 
		return true;
	}
	/**
	 * Overload Sort method
	 * Compare objects and sort by natural order by compareTo method 
	 */
	
	public void sort() {
		Collections.sort(this);
	}
	
	/**
	 *Determine if the arrayList is sort, if so invoke the binarySearch method to search if it contains a specified value
	 * If not sorted, invoke linear search using the parent classes function contains.
	 */

	public boolean contains(E obj) 
	{
		if (isSorted())
		{
			// binary search
			int firstVal=0;
			int endVal=this.size()-1;
			boolean result=binarySearch(firstVal,endVal, obj);
			return result;
		}
		// linear search
		return super.contains(obj); 
	}

	
	/**
	 *BinarySearch method that has three parameters.
	 *Loops through the arrayList comparing values to the next.
	 */
	public  boolean binarySearch(int firstVal, int endVal, E obj) 
	{
		int midPoint;
		E e;
		while (firstVal <= endVal ) 
		{	
			midPoint = (firstVal + endVal) / 2;
			e = this.get(midPoint);
			
			if (e.compareTo(obj)==0) {
				System.out.println("FOUND");
				System.out.println(e);
				System.out.println("TEST " + obj);
				
				return true;
			}
			if (e.compareTo(obj) > 0) {
				endVal = midPoint - 1;
			} 
			else {
				firstVal = midPoint + 1;
			}
		}
		return false;
	}
}


