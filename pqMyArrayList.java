package project4;
/**
 * Alifa Faruk
 * Project 4
 * Due December 5, 2018
 */
import java.util.Collections;

public class pqMyArrayList<E extends Comparable<E>>{
	private MyArrayList<E> heap; //The core heap array
	int count=0;
	float priority;
	pqMyArrayList(){
		heap= new MyArrayList<E>();
	 
	}
	
	// helper functions that makes main functions easier and cleaner
	protected int parent (int j) {
		 
		return (j-1)/2;
	}
	protected int leftChild (int j) {
		return (2*j)+1;
	}
	protected int rightChild (int j) {
		return (2*j)+2;
	}
	protected boolean hasLeft(int j) {
		return leftChild(j)<heap.size();
	}
	protected boolean hasRight(int j) {
		return rightChild(j)<heap.size();
	}
	
	//swaps the two indeices with each other
	protected void swap(int i, int j) {
		E temp = heap.get(i);
		
		heap.set(i, heap.get(j));
		heap.set(j, temp);
		}
	
	/*
	 * up heap method that retains the heap structure. Implemented recursivly
	 * takes in an index 'index'. tests are run on that index to see if its parent value is larger or not and peforms actions accordingly
	 */
	protected void upheap(int index) {
		//base case
		  if (index== 0)
		    return;
		 // instaintate a vairable that will hold the parent index of the user index
		  int par = parent(index);
		  // if the index of the parent is smaller, return and exit the function
		  if (compare(heap.get(index), heap.get(par)) >0) {
			  upheap(par);
			   return;
		  }
		 // if the value entered is smaller swap that parent and the child index value then recursively upheap to do further checks.
		  else {
			  count++;
			 

			  swap(index, par);
			  upheap(par);
			  
		  }
		}
	  
	   /*
	    * a method that is for testing purposes. checks is the heap is still in proper heap structure. if the check fails, is adjusts the structure to make it a heap again
	    * used in the down heap method
	    */
	  public boolean checkHeap() {
		    boolean isheap = true;
		    
		    for (int i=1; i<heap.size(); i++) {
		      E heapVal = heap.get(i);
		      if (compare(heapVal, heap.get(parent(i)))<0) {
		    	  isheap=false;
		    	 
		    	  swap(i,parent(i));
		      }
		    }
		   
		    return isheap ;
		  }
	//print all function. used for testing purposes
	public void printAll() {
		for(int i=0; i< heap.size();i++) {
			System.out.println(heap.get(i).toString());
		}
		System.out.println();
		
	}
	
	private int compare(E value, E target) {
		return value.compareTo(target);
	}
	public int getSize() {
		
		return heap.size();
	}
	/*
	 * downheap function that retains the heaps structuraly integrety when a value is removed
	 */
	public void downheap(int index) {
		while (hasLeft(index)) {
			int left=leftChild(index);
			int leftVAL=left;
			if(hasRight(index)) {
				int right=rightChild(index);
				if (getPriority(heap.get(left),(heap.get(right))) >0){
					leftVAL=right;
				}
			}
			if(getPriority(heap.get(left),(heap.get(index))) >=0) {
				break;
			}
			swap(index, leftVAL);
			index=leftVAL;
		}
		checkHeap();
	}
	
	/*
	 * insert function that insert the new value into the myarraylist, then upheaps
	 */
	public void insert(E key) {
	heap.add(key);
 
	upheap(heap.size()-1);
	
	}

	/*
	 * removes that lowest min value, and returns it 
	 */
	public E remove() {
		if (heap.isEmpty()) {
			return null;
		}
		E value=heap.get(0);
		swap(0,heap.size()-1);
		heap.remove(heap.size()-1);
		downheap(0);
		return value;
	}
	 /*
	  * peeks at the min value without removing
	  */
	public E peek() {
		return heap.get(0);
	}
	//helper function that gets the value in the index provided
	public E get(int index) {
		
		return heap.get(index);
	}
	//helper function get priority (same as compare)
	public int getPriority(E value, E target) {
		return value.compareTo(target);
	}
	public boolean isEmpty() {
		if (this.getSize()>0) {
			return false;
		}
		return true;
	}
	
	
	
}