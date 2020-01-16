package project4;


/**
 * Alifa Faruk
 * Project 4
 * Due December 5, 2018
 */
public class House implements  Comparable<House> {
	private String name; //name of the house
	protected sLinkedList<Character> members; //characters with allegiance to this house
	private Character patriarch; //patriarch of the house
	private Character matriarch; //matriarch of the house
	public FamilyTree familyTree;
	int count=0;
	
	//House constructor; take in the name of the house, members, pat and mat, and the familytree
	House( String name, sLinkedList<Character> members, Character patriarch, Character matriarch, FamilyTree familyTree){
		this.name=name;
		this.members=members;
		this.patriarch=patriarch;
		this.matriarch=matriarch;
		this.familyTree=familyTree;
		count++;
	}
	//default constructor
	House(){}
	
	//getters to retrieve name, members, patriarch, and matriarch
	public String getName()
	{
		return this.name;
	}
	public  sLinkedList<Character> getMembers(){
		return members;
	}
	
	public Character getMatriarch() {
		return matriarch;
	}
	
	public Character getPatriarch() {
		return patriarch;
	}

	//setters to retrieve name, patriarch, and matriarch
	public void setPatriarch(Character patriarch) {
		this.patriarch = patriarch;
	}


	public void setMatriarch(Character matriarch) {
		this.matriarch = matriarch;
	}
	
	//compareto function that overrides. compares the name of the house
	@Override
	public int compareTo(House o) {
		
			return (this.getName()).compareToIgnoreCase(o.getName());
		}
}
