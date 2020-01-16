package project4;

 

/**
 * Alifa Faruk
 * Project 4
 * Due December 5, 2018
 */

public class Battle implements Comparable<Battle>  {
	
	/**
	 *Battle class that contains all the attributes of each battle including name, attackerKings,
	 * defenderKing, attackerOutcome,BattlesType, location, and region
	 */
// create all the variables for battle
String name;
String attackerKing;
String defenderKing;
String attackerOutcome;
String battleType;
String location;
String region;

//seven arg constructor instantiates the variables
public Battle (String name, String attackerKing, String defenderKing, String attackerOutcome, String battleType,
String location, String region) {
	this.name=name;
	this.attackerKing=attackerKing;
	this.defenderKing= defenderKing;
	this.attackerOutcome= attackerOutcome;
	this.battleType= battleType;
	this.location=location;
	this.region=region;
}

//getter that returns the name of the battle
public String getName(){
	return this.name;
}

//getter that returns the attackerKing
public String getAttackerKing() {
	return this.attackerKing;
}
//getter that returns the defenderKing
public String getDefenderKing() {
	return this.defenderKing;
}

@Override
public int compareTo(Battle o) 
{
	if (o!=null) {
	return (this.getName()).compareToIgnoreCase(o.getName());	
}
	else {
		return -1;
	}
}

//return an accurate representation for the battle 
public String toString() 
{
	return  "-" +name+ ", when "+ attackerKing+" attacked "+ defenderKing+ ", resulting in a "+ attackerOutcome +", through a "+ battleType+", at "+ location+", in the region of "+ region;
}

}