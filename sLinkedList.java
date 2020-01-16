package project4;

/**
 * Alifa Faruk
 * Project 4
 * Due December 5, 2018
 */

 
public class sLinkedList<E extends Comparable<E>> {
	//Instantiate the head and set it equal to null
	sllNode head=null;
	//have a size variable the increases every time a node is added
	int size=1;   
	
	/**
	 * nested sllNode class that has a generic element
	 * There is a reference variable to the next node in the linkedList
	 * There is a reference variable that has access to the value of the node
	 */
	class sllNode{
		//variable that holds the value of the node
		E value;
		// reference to the next node
		public sllNode next;
		// constructor that takes in a value that is passed in and instantiates the value 
		public sllNode(E value)
		{
			this.value= value;
			
		}
	
		//returns the next node of the linkedList
		public sllNode getNext() 
		{
			return next;
		}
	}  
	
	/** 
	 * @param add Node the linkedList by taking in a value. First check is the head of the linkedList is null. 
	 * If so Instainate a new sllNode and set that equal to the head  
	 * Add to the beginning of the list.
	 * Return nothing
	 */
	public void addFirst(E val){
		//validate that the value entered isn't null. If it its null, stop method
		if (val==null) {
			return;
		}
		//check is head is null. if so set head to a newly instantiated sllNode with the entered val and set it to head
		if (head==null)
		{
			head= new sllNode  (val);
		}
		// instantiate a new sllNode with the entered val. Make the newsllNode.next equal to the head. This puts the value ahead of the list
		//Increase size by 1
		else
		{
			size++;
			sllNode newsllNode = new sllNode (val);
			newsllNode.next=head;
			head=newsllNode ;
		}
	}   
	
	/**
	 * 
	 * @param add to the end of the linkedList. Check if the head empty by checking if the head is null. If so, instantiate a new list with the entered value and set equal to head.
	 * Else, Create two new nodes with the val, and a referance to the head. if the head is null set the val to head. else loop through the new list with the head to find the end of the list 
	 * @return return the new node
	 */
	  sllNode  addLast(E val) {
		  // check is the linkedList is empty or not. if so make the entered value the head/first node of the list
		  if (this.head==null) 
		  	{
			  this.head= new sllNode (val); 
			  }
			
		  //create a new node with the entered val
	       sllNode node = new sllNode (val);
	       //create a new node and set it equal to the head
	       sllNode newsllNode = this.head;
	       
	       //if the newNode is null set the head equal to the Node with the entered val
	       if (newsllNode == null)
	       {
	    	   this.head = node;
	        } 
	       //while the next node isn't null, loop through the list to find the end of the list
	       else 
	       {
	            while (newsllNode.next != null)
	            {
	            	newsllNode = newsllNode.next;
	            }
	            // set the last the new node as the last value
	            newsllNode.next = node;
	        }
			// increase the size by one
	        size++;
	        // return the new node
	        return node;
	    }
	
	  /**
	   * 
	   * printAll the values on the linkedList. This was used to check if code was processing correctly. not used in final main method
	   */
	public void printAll()
	{
		if (head==null) {
			return;
		}
		int counting=0;
		sllNode print= this.head;
		try {
		System.out.println("THE NAME" +print.value.toString());
		}catch(NullPointerException x) {}
		while (true)
		{
			System.out.println(" " + print.value);
			if(print.next!=null) 
			{
				counting++;
				print= print.next;
			}
			else {
				break;
			}
			counting++;
		}
		System.out.println(counting++);
	}
	
	/***
	 * check if the linkdList is empty by checking if the head is empty. Returns true if the head is empty.
	 * @return return false is head not null
	 */
	boolean isEmpty() {
		if (head==null)
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
	/**
	 * 
	 * @param take in an index, that is an integer. First validate if the int is a positive number. if not, return null.
	 * if create a new linkedList set equal to null. if the head of the passed in list isnt null, set the new list equal to head.next.
	 * loop through the list using the parameter index value as a condition.
	 * 
	 * @return return the last value from the loop if found. Else return null.
	 */
	public E get(int index) { 
		// validate the index is a positive number. If not, return null
		if (index<0) 
		{
			return null;
		}
		
		// instantiate a new node and set it equal to null
		sllNode list= null;
		
		// check if the list is empty
		if (this.head!=null) 
		{
			//set the new list equal to the head
			list =head;
			//iterate through the linkedList and getNext value and set equal to list. Use the index value as a condition to stop
			for (int i = 0; i < index; i++)
			{
				 
				//check id the next node in the linkedList is null; return null if true
				if (list.next == null) 
				{
					return null;
				}
				//getNExt node
				list= list.next;
			}
			// return the found value of the index entered
			return list.value;
		}
		// return null if node not found
		return null;
	}
	
	/**
	 * check if the linkedList is sorted or not
	 * Have two generic values, and check if this linkedList is empty or not
	 * than if not empty iterate through the list comparing the values to see if they are sorted
	 * @return false if firstval and secondval are not lexiographically sorted
	 * return true if each value on in the list and its 
	 */
	boolean isSorted() {
		//two generic variables that will be used to caompare
		E firstObj;
		E secondObj;
		
		//check if the list is empty; if not continue
		if (!this.isEmpty()) 
		{
			//iterate through the loop
			for (int i = 1, x=0; i < this.size; i++,x++)
			{
				
				firstObj=this.get(x);
				secondObj=this.get(i);
				// compare the two values next to each other
				try {
				if (firstObj.compareTo(secondObj)>0) 
				{
					return false;
				}
				}catch (NullPointerException e) {
					return false;
				}
			}
		}
		return true;
	}
	
	/**
	 * 
	 * @param check if the linkedList contains the entered targetVal. 
	 * First check if the head or the targetval is null, if so return false.
	 * Loop through the list and use the compareTo to check if the value is in the list.
	 * @return if the compareTo returns 0, return true. 
	 */
	public boolean contains(E targetVal) {
		//data validation. Check if the head or  the target of LinkedList is false. If so, return false. If not, continue in the method.
		if(head==null) 
		{
			return false;
		}
		if (targetVal==null) {
			return false;
		}
		
		//iterate through the linkedList and compare values to check if it is the list. Return true if found
		for (int i=0; i<=this.size-1; i++) 
		{
			if(	this.get(i).compareTo(targetVal)== 0) 	
			{
				return true;
			}
			
		} 
		// if not found return false
		return false;
		 
	}
	
	/**
	 * THis is used to get the length of the linkedLust
	 * have a count val and instantiate a new list (temp) and set it equal to head.
	 * While temp is not null, loop through the linkedList to and increment the count by one each iteration
	 * @return the count value that has the size
	 */
	int getCount() {
		//have a count value that contains size
		int count=0;
		// reference to the list
		sllNode temp= head;
		
		//iterate through the list and keep the count
		while(temp!=null) {
			count++;
			temp=temp.getNext();
		}
		
		//return the size
		return count;
	}
	
	/***
	 *Sort the linkedList if the linkedList isn't sorted. US the isSorted method to validate if the list is sorted.
	 *If sorted, invoke the mergeSort method that takes in the head and returns a sorted list.
	 *Set the returned LinkedList equal to head
	 */
	public void sort(){
		//Check if the list is sorted
		if (this.isSorted()== false)
		{
			// invoke the mergeSort function and set the return value equal to linkedList head
			head=this.mergeSort( head);
		}
	
	}
	
	/***
	 * @param recursive mergeSort: have base cases that would stop the recursive call when it hits that base case
	 * Get the middle of the linkedList. Split the linkedList, where one holds the first half and the second holds the second half
	 * pass in both lists in the mergeSort (recursively call mergeSort)
	 * @return merge the two sorted halves together by calling the merge function
	 */
	public sllNode mergeSort(sllNode header){
		//check if the head value and the head.next value is null. If so, return header.
		//base cases
		if(header==null || header.next==null)
		{   
			return header;
		}
		
		// Get mid value by having two references to the header value, mid, and getMid
		sllNode getMid=header;
		sllNode mid= header;
		
		//get the length of the linkedList by invoking nodeSize method. This returns an integer.
		int count= nodeSize(mid);
		
		// get the mid size of the list by dicing the length by two. 
		count= count/2;
		
		//get the mid of the linkedList by looping through the getMid.next using the mid as iterative condition
		for (int i=0; i<= count-1; i++) {
			getMid=getMid.next;
		}
		
		//create a new node that holds all the middle of the list until the end
		sllNode middle= getMid;
		
		// create a new node 
		sllNode middleNext= middle.next;
		middle.next = null; 
		
		 //recursive call mergeSort
		sllNode list1= mergeSort(header);
		sllNode list2= mergeSort(middleNext);
		
		// merge the two lists together and return the final list
		return merge(list1, list2);
		
	
	}
	
	public E getValue(sllNode x){
	 
		return x.value;
	}
	public int getsize() {
		   int size = 0;
		   for(sllNode n = head; n.next != null; n = n.next)
		       size++;     
		   return size;
		}
	
	/**
	 * 
	 * @param takr in nodes list1 and list2 and merge. 
	 * Check the bases cases; if lists are null return respective lists
	 * Compare if the lists value to see which is lexicographically larger and adjust the final sorted list accordingly
	 * return final sorted list that merged the two nodes together
	 * @param 
	 * @return
	 */
	public  sllNode merge(sllNode list1, sllNode list2){
		// create a finalList that contains the two merged nodes
		 sllNode finalList = null; 
	        //check the bases cases
	        if (list1 == null) {
	        	 
	            return list2 ; 
	        }
	        if (list2 == null) {
	            return list1; 
	        }
	       
	        try 
	        {
	        	//check which node is lexicographically larger
	        if (list1.value.compareTo(list2.value)<=0 )  
	        { 
	        	  
	        	finalList = list1;   
	        	finalList.next=merge(list1.next, list2); 
	        }  
	        else 
	        { 
	        	finalList = list2; 
	        	finalList.next= merge(list1, list2.next); 
	        } 
	        }catch(NullPointerException e) {}
	   //return the finaList merged linkedList   
	  return finalList;
}
	 /**
	  * @param get the size of the linkedList by taking in the sllNode value
	  * @return return the integer that hold the size of the sllNode
	  */
	int nodeSize(sllNode val) {
		//flag to hold the size
		int counter=0;
		
		//iterate through the passed in list until it hits the end. increase the counter each iteration
		while (val.next!= null) {
			val=val.next;
			//System.out.println(mid.value);
			counter++;
		}
		// return the counter
		return counter;
	}
	
	/**
	 * returns the size of the linkedList
	 * @return the size of the linkedLIst
	 */
	int getSize() {
		return size;
	}
	public sllNode getNext() 
	{
		return head.next;
	}
	public void deleteNode(sllNode node) {
	    node.value = node.next.value;
	    node.next = node.next.next;
	}
}