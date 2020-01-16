package project4;
/**
 * Alifa Faruk
 * Project 4
 * Due December 5, 2018
 */

public class Prediction implements Comparable<Prediction>{
	private float plod;
	private Character character;

	/*
	 * Predication constructor that takes in two paraaameters, of type  float (plod) and Character 
	 */
	Prediction(float plod, Character character){
		this.character=character;
		this.plod=plod;
		
	}
	/*
	 * Compare to function that is used during sorting. the compare to compares the objects based on their probaility plod values
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	@Override
	public int compareTo(Prediction o) {
	//	System.out.println("HELLO ENTER");
		
		 if (this.plod==o.plod){
			return 0;
		}
		 else if(this.plod>=o.plod){
				return 1;
			}
		else{
			return -1;
		}
		
		
	}
	/*
	 * healper methods that returns the plod and characters
	 */
	public float getPlod() {
		return plod;
	}
	public Character getCharacter() {
		return this.character;
	}
	public String toString() {
		return "Character: " + character.name + " PLOD" +plod;
	}
}
