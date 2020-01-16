package project4;


/**
 * Alifa Faruk
 * Project 4
 * Due December 5, 2018
 */
public class Character  implements Comparable<Character>  {

	/**
	 * Character class that contains all the attributes of each character including name, allegiance, and battles.
	 * Battles is an arrayList that contains all the battles that character took place in
	 * If battles isEmpty, than no battles were fought and will output "No Battles"
	 * toString overrides battle class toString
	 */
	
//create variables for characters
String name;
String allegiances;
sLinkedList<Battle> battles;

private String fatherName; //Father of this character
private String motherName; //Mother of this character
private boolean isPatriarch; //Am I a patriarch?
private boolean isMatriarch; //Am I a matriarch?

//three arg constructor instantiates variables
public Character (String name, String allegiances, sLinkedList<Battle> battles) 
{ 
	this.name=name;
	this.allegiances=allegiances;
	this.battles= battles;
}
public Character (String name, String allegiances, sLinkedList<Battle> battles,  String fatherName, String motherName, boolean isPatriarch,  boolean isMatriarch)
{ 
	this.name=name;
	this.allegiances=allegiances;
	this.battles= battles;
	this.fatherName=fatherName;
	this.motherName=motherName;
	this.setPatriarch(isPatriarch);
	this.setMatriarch(isMatriarch);
}

//getter to return name of character
public String getName()
{
	return this.name;
}

//compare characters using names
public int compareTo(Character o) 
{
	return (this.getName()).compareToIgnoreCase(o.getName());
}

//return an accurate representation of the character, including battles
public String toString()
{
	//check if the character has battles
	if (!battles.isEmpty())
	{
		String results="\n";
		for (int i=0; i<battles.size; i++)
		{
			results+=battles.get(i).toString();
			results+="\n";
		}
		
		return name + " with allegince to " + allegiances + results ;	
	}
	else
	{
		String results="\n" + "No Battles"+"\n"  ;
		return name + " with allegince to " + allegiances + results ;
	}
}
public String getFatherName() {
	return fatherName;
}
public void setFatherName(String fatherName) {
	this.fatherName = fatherName;
}
public String getMotherName() {
	return motherName;
}
public void setMotherName(String motherName) {
	this.motherName = motherName;
}
public boolean isPatriarch() {
	return isPatriarch;
}
public void setPatriarch(boolean isPatriarch) {
	this.isPatriarch = isPatriarch;
	
}
public boolean isMatriarch() {
	return isMatriarch;
}

public boolean isMother() {
	return isMatriarch;
}
public boolean isFather() {
	return isMatriarch;
}

public void setMatriarch(boolean isMatriarch) {
	this.isMatriarch = isMatriarch;
}
}
