package project4;

 
/**
 * Alifa Faruk
 * Project 4
 * Due December 5, 2018
 */
public class pqLinkedList< E extends Comparable<E>> extends sLinkedList<E>  {

class sllNode1 extends sLinkedList<E>.sllNode {
		float priority;
		
		public sllNode1(E value) {
			super(value);
		
	}
}
		pqLinkedList(){
			super();
		}
		/*
		 * Inserts a user entered value into the linked list. 
		 * The methods takes in  parameters data of type E. 
		 * Places node after or before a node by comparing their priority.
		 * 
		 */

		public void insert(E data ){
			//create a new sllnode that holds the new data value
 			sllNode node= new sllNode1 (data);
			 
			//if the head value is null, the linkedlist does not have any values. enter the the new data value into the list and exit the method
			if (head==null) 
			{
				head=node;
				size++;
				return;
			} 
			
			       //flag used for testing. The flag is set to true if the value was added into the priority queue
			       boolean flag=false;
			       sllNode newsllNode = head;
			       sllNode headVal = head;
			       
			       sllNode1  nodePQ =(pqLinkedList.sllNode1)newsllNode;
			       if(compare(nodePQ.value,node.value)>=0) { 
 			    	   node.next=head;
			    	   head=node;
			    	   flag=true;
					}
			       while (flag!=true)
			       {
			    
			    	//through testing, the value is placed into the correct position
			    	// if there are values after the current head value, compare the two values until it finds the right position and switch 
			    	if(newsllNode.next!=null) 
			    	{
			    		sllNode1 f= ((pqLinkedList.sllNode1)newsllNode.next);
			    	   if(compare(f.value,node.value)>0) 
			    	   {
			    		   node.next=nodePQ.next;
			    		   nodePQ.next=node;
 			    		   flag=true;
 			    		   size++;
			    		   
			    	   }
			    	   
			    	}
			    // if there are no values after the current head value and the new value entered is greater than the head, place after; if not go to the next value in the list
			    	if (newsllNode.next==null&& compare(nodePQ.value,data )<=0) {
			    		newsllNode.next=node;
			    		flag=true;
			    		super.size++;
			    	}
			    	else
			    	{
			    		newsllNode= newsllNode.next;
			    		nodePQ=(pqLinkedList<E>.sllNode1) nodePQ.next;
			    	}
			    	
			    	   
			    	 
			    }
			    	  if (flag==false) 
			    	  {
			    		  while (headVal.next!= null) 
			    		  {
			    			  headVal = headVal.next;
			    		  }
			    		  headVal.next=node;
			    		  flag=true;
			    		  size++;
			          }
			
			} 
		/*
		 * Helper method that compares the two values and returns an integer
		 */
		private int compare(E value, E target) {
			return value.compareTo(target);
		}
			/*
			 * returns the minimum value in the linked list without removing
			 */
			public E peekMin() 
			{
				return this.head.value;
			}
			
			/*
			 * Removes the top (minnimum value) in the linked list.
			 */
			public E remove() {
				sllNode tempValue=head;
				this.head=this.head.next;
				
				return tempValue.value;
			}
			
			
			/*
			 * Helper method that returns that size of the linked list. 
			 * 
			 */
			
			public int index() 
			{
				if(head!=null) 
				{
					int count=0;
					pqLinkedList<E>.sllNode head3 = (pqLinkedList<E>.sllNode) head;
					pqLinkedList<E>.sllNode head2 = head3;
					sllNode current=head2;
					while (current!=null) 
					{
						current=(pqLinkedList<E>.sllNode) current.next;
						count++;
					}
					return count;
				}
				return 0;
				
			}
			
			/*
			 *get the size of the linkedlist by copying the head to another local variable and traversing through until the head hits null
			 */
			public int getSize() 
			{
				sllNode headSize= head;
				int count=0;
				while(headSize!=null) 
				{
					headSize=headSize.next;
					count++;
				}
				return count;
			}
			
			
			
		}

