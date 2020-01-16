package project4;
import java.io.*; 
import java.util.*;
/**
 * Alifa Faruk
 * Project 4
 * Due December 5, 2018
 */

public class GameOfThrones {
private static Scanner inputBattle;

public static void main (String[] args) throws FileNotFoundException {
	/**
	 *Open csv files and parse the data into its corresponding list .
	 *Will have a total of two main arrayLists, Battles that contain all the battles from its csv file, and Characters that contains all the characters 
	 *and the battles that correspond to each character.
	 */
		String userChar="";
		Scanner userData= new Scanner(System.in);
		//instantiate battles and characters linkedList to hold data
		sLinkedList<Battle> battles= new sLinkedList<Battle>();
		sLinkedList<Character> characters= new sLinkedList<Character>();
		
		java.io.File batFiles= new java.io.File("data/battles.csv");
		inputBattle = new Scanner(batFiles);
	 
  	//put battles into an arrayList
	 while(inputBattle.hasNext())
	 {
		 String charBattle= inputBattle.nextLine();
		 String[] bat= charBattle.split(",");
		 Battle obj2= new Battle(bat[0],bat[1],bat[2],bat[3],bat[4],bat[5],bat[6]);		 
		 battles.addFirst(obj2);
	
		 
	 }

 //sort battles linkedList
battles.sort();


/**
 * split the charactersLineage csv file into a array
 * put the array into a linkedList the will hold the characters
 * 
 * 
 */
	//Scanner to hold the info of the charact_lineage csv
	Scanner inputLineage;
	java.io.File lineageFiles= new java.io.File("data/characters_lineage.csv");
	inputLineage = new Scanner(lineageFiles);
	
	//create and instaintate variable that will hold the father and mother characters and a house variable/
	Character fatherpat=null;
	Character motherMat = null;
	//Create a house variable that will hold the house of that character.
	House house=null;
	
	//create and arraylist that will hold all the house names (used for testing purposes).THis will indicate if the house was seen earlier in the csv file or not
	ArrayList<String> all=new ArrayList<String>() ;
	
	//create a linkedList of characters that will hold all the members
	sLinkedList<Character> allMembers= new sLinkedList<Character>();
	//creat a linkedlist that will hold the different houses that are in the characterLInage csv
	sLinkedList<House> houses= new sLinkedList<House>();

	String pat="";
 //
	
	
	//Read all the data of the charactersLineage file. Repeativly read the file until there is no more lines
	while(inputLineage.hasNext()) 
	{
		//boolean indicaters; 
		//Check if the character is a patriarch
		boolean ispat=false;
		//Check if the character is a matriarch
		boolean ismat=false;
		
		//the flag is used to see/ indicate if the house of the character was previously seen in the file or not
		boolean flag=false;
		
		//get the next line of the file and spilt using the commas and put in to an array of strings
		String inputLineage1= inputLineage.nextLine();
		String[] characterList= inputLineage1.split(",");
	 ;
       //Create a linkedList of type battle that will hold the battles of the character
		sLinkedList<Battle> battleChar= new sLinkedList<Battle>();
		
		//loop through the list containing the battles to check if the character was in that battles. If so put that battle into battlECHAR LINKED LIST
		for(int i =0;i<battles.getCount()-1; i++)
		{
			//get the name of the defender king and attacker king of the battle
			String defender=battles.get(i).getDefenderKing();
			String  nameAttack=battles.get(i).getAttackerKing();
			//check if the attacker of defender is that character; if so add to the list
			if (defender.equals((characterList[0])) || nameAttack.equals((characterList[0])) )
			{
				battleChar.addLast(battles.get(i));
			 }
		}	
		
		//loop through the charactersList to see if the character is a patriarch or matriach or niether
		for(int i=0;i<characterList.length;i++) {
			 
			if(characterList[i].equals("PATRIARCH") ) { 
				ispat=true;
			}
			 else if(characterList[i].equals("MATRIARCH") ){
				ismat=true;
				}
		}
			//create and intantiate a character using the info gathered; name,mother, father, battes, and alliengce 
			Character allCharacter= new Character(characterList[0],characterList[3], battleChar,characterList[1],characterList[2],ispat,ismat);
			
			//Create and intantiate a mother and father of tyoe character; put the mother and father of that character into their respective variables
			Character mother= new Character(characterList[2],characterList[3], battleChar);
			Character father= new Character(characterList[1],characterList[3], battleChar);
	 
			//add that character to the characters linkedlist 
			characters.addFirst(allCharacter);
			
			//add something to the all arraylist if empty. This variable is meant to hold the names of the houses. If the first house wasnt entered, add tester.
			if(all.isEmpty()) 
			{
			all.add("tester");
			}
			//loop through all to see if the characters house was seen before or not
			for(int i=0;i<all.size();i++) 
			{
				//if so set flag to true
				if(all.get(i).equals(characterList[3])) 
				{
					flag=true;
				}
				
			}
			// if the house was seen enter the loop to find the correct house of this character
			if(flag==true) {
		 
			
			//loop through the names of the house (which is in the all list) to match the house with the character.
			 for (int i=0;i<all.size();i++) 
			 {
					if(houses.size+1>=i) 
					{
						// check if the house  in the all list matched the alleignce of the character
						if (all.get(i).equalsIgnoreCase(characterList[3]))
						{
							//loop through the linkedalist of house to match with the character
							for (int x=0;x<=houses.size-1;x++) 
							{
								//check if the house is the same as the characters alliegence
								if (houses.get(x).getName().equalsIgnoreCase(characterList[3])) 
								{   
								//if so, add to the members of that house
									houses.get(x).members.addFirst(allCharacter);
									//check if the character is the matriarch or patriarch. If so set the mat and pat of the house to that character
									if(allCharacter.isMatriarch())
									{
										houses.get(x).setMatriarch(allCharacter);
									}
									else if(allCharacter.isPatriarch())
									{
										houses.get(x).setPatriarch(allCharacter);
									}
									//add character to the linkedlist that contains all the characters
									allMembers.addFirst(allCharacter);
								}
							 
							}
						}		 
					}
				}
			}
		//if the house of the character was not prevously mentioned in the csv file create a new house.		
		else {
			 //linkedlist that will contian the members of the house
				sLinkedList<Character> membersHouse= new sLinkedList<Character>();
				//create a family tree that will have the familytree of the character
				FamilyTree newTree= new FamilyTree();
				//create and instantiate a characterNode of the mother and father of that character along with the character
				FamilyTree.CharacterNode mother1= newTree.new CharacterNode(mother);
				FamilyTree.CharacterNode father1= newTree.new CharacterNode(father);
				FamilyTree.CharacterNode newHouse= newTree.new CharacterNode(allCharacter);
				
				//set characters mother and father to the respective characterNode 
				newHouse.setMother(mother1);
				newHouse.setFather(father1);
				
				//loop that charactersList to find the patriarch and matriarch of the house
				for(int i=0;i<characterList.length;i++)
				{
				 //check if the character at that position is a patriarch or matriarch; if so set fatherpat or motherpat to that character
					if(characterList[i].equals("PATRIARCH") )
					{
						pat=characterList[0];
						fatherpat= new Character(characterList[0],characterList[3], battleChar,null,null,true,false);
					}
					 else if(characterList[i].equals("MATRIARCH"))
					 {
						 motherMat= new Character(characterList[0],characterList[3], battleChar,null,null,false,true);
					 }
				}
				//create a house using the information just gathered; house name, members, fatherpat/mothermat, and the familytree created
				house= new House(characterList[3], membersHouse, fatherpat, motherMat, newTree );
				 
				//add the character to the linkedlist with all the characters
				membersHouse.addFirst(allCharacter);
				 
				//add this new house to the linkedlist of houses
				houses.addFirst(house);
				 //add the name of the house to the list of house names
				all.add(house.getName());
			 
			}
		}
	
	//create a linkedlist that will hold the updated houses
	sLinkedList<House> updatedHouses=new sLinkedList<House>() ;
	//loop through the houses in order to build out the familytree of the houses
	for(int i=0; i<houses.size;i++) 
	{
		//get house name
		House houseName=houses.get(i);
		//get the familytee of the house
		FamilyTree newTree=houseName.familyTree;
		//use the buildFamilytree method to build out the family tree structre of the house
		newTree=newTree.buildFamilyTree(houses.get(i));
		//instiate a new house with the updated familytree
		houseName= new House(houseName.getName(), houseName.members, houseName.getPatriarch(),houseName.getMatriarch(),newTree);
		// add house to the updated linkedlist of houes
		updatedHouses.addFirst(houseName);
		
	}
	//sort the updated houses and characters
	updatedHouses.sort();
	characters.sort();
	
	//instantiate a linkedList priority queue and and myarraylist that will hold data from the Prediction csv
	pqLinkedList<Prediction> predicationLL= new pqLinkedList<Prediction>();
	pqMyArrayList<Prediction> predictionsAL= new pqMyArrayList<Prediction>();
	
	//open the predications csv and parse the data into pqLinkedList and pqMyArrayList
	Scanner inputPredictions;
	java.io.File predicationsFiles= new java.io.File("data/predictions.csv");
	inputPredictions = new Scanner(predicationsFiles);
 
 	//iterate through the predications file.
	while(inputPredictions.hasNext()){
	 
	String predicationLine=inputPredictions.nextLine();
		
		String[] predictionList= predicationLine.split(",");
		//variable that will hold  the new data 
		Prediction newChar=null;
	
		// plod will check if the data in the file is actually a float
		 float plod= -1;
		try {
			plod=Float.parseFloat(predictionList[0]);
			}catch(NumberFormatException x) {}
		if(plod!=-1) {
			boolean flagers=false;
		 
	try {
		//find the character in all characters list
		for(int i=0;i<characters.size;i++){
		 
			if (predictionList[1]==characters.get(i).name) {
			 
				newChar	= new Prediction(plod, characters.get(i));
			 
				flagers=true;
			
			}
			 
		}
		//if character not found in the characters list, instantiate a new character
		if (flagers==false) {
			Character newCharFound= new Character(predictionList[1],null,null);
			newChar=new Prediction(plod, newCharFound);
		}
	 
			}catch(NullPointerException x) {}
			try {
				// insert new predication into predictionsAL and predicationLL
				predicationLL.insert(newChar);
				predictionsAL.insert(newChar);
	
		}catch(NullPointerException x) {}
	}
	}


	// get the character that is least likely to die and save into a vairable.
	Prediction lltd=predicationLL.peekMin();
	String LLTD="LEAST LIKELY TO DIE IN THE ENTIRE SERIES: " + lltd.getCharacter().name+ " with PLOD of "+ lltd.getPlod();

	/*
	 * Enter loop that will continuously ask the user to enter a character and will display that character, allegiance, and battles if there were any
	 * If no battles were fought, "No battles" will output
	 * If user enters "all" all characters and battles that corresponds will output.
	 * To exit loop, user needs to enter "exit"
	 */

 
	do  
	{
		boolean noBattles=false;
		//UPDATED OUTPUT FOR THE USER
		System.out.println("Enter a character name (or type \"all\" for all characters, \"family tree\" for a family tree of all houses"+ "\nOr  to remove next, or remove all type"+
				"\"use sLinkedList\" to use the linked list heap, \"use MyArrayList\" to use the MyArrayList heap,\n" + 
				"\"LLTD\" to see which character is the least likely to die,\n" + 
				"or \"exit\" to exit):\n" + 
				"");
		userChar= userData.nextLine().trim();
		
		if(userChar.equalsIgnoreCase("LLTD")) {
			
			System.out.println(LLTD +"\n");
		}
		
		//if user enters all print all characters and battles
		if(userChar.equalsIgnoreCase("all")) 
		{
			for (int i=0; i<characters.size-1;i++) 
			{
				System.out.println(characters.get(i).toString());
				
				if(characters.get(i).battles.isEmpty()) 
				{
					noBattles=true;
				}
			}
		}
		// if the user enters familytree, print the familytree of all the houses using the printTree function
		if (userChar.equalsIgnoreCase("family Tree"))
		{
			for (int i =1;i<updatedHouses.size;i++) 
			{
				String value=""; 
				//SORTING THE CHILDREN OF THE PAT AND MAT	
				updatedHouses.get(i).members.sort();
				updatedHouses.get(i).familyTree.getMatriarch().children.sort();
				updatedHouses.get(i).familyTree.getPatriarch().children.sort();
				//output the house
				System.out.println("HOUSE :" +updatedHouses.get(i).getName());
				try {
					//send in the patriarch of the familytree; print the value
					value= updatedHouses.get(i).familyTree.printTree(updatedHouses.get(i).familyTree.getMatriarch());
					 System.out.println(value);
				}catch(NullPointerException c) {}
				try {
					//send in the patriarch of the familytree; print the value
				value= updatedHouses.get(i).familyTree.printTree(updatedHouses.get(i).familyTree.getPatriarch());
				System.out.println(value);
				}catch(NullPointerException c) {}
				
			}
		}
		 
			
		
		
		//validate user data to check if the character entered is in the list
		Character dataValidation= new Character(userChar, "test", characters.get(0).battles);
		
		//check if the user data entered is in the linkedList
		boolean validCharacter= characters.contains(dataValidation);
		
		//if the linkedLIst contains the userValue print the character and corresponding battles 
		if (validCharacter==true )
		{		
			//Create a new House object 
			House houseName=new House();
			
			 //loop through the characters to see if the name of the character is equal to user input
			for( int i=0; i<=characters.size-1; i++)
			{		
				 //loop through the characters to see if the name of the character is equal to user input
				if (characters.get(i).getName().equalsIgnoreCase(userChar)) 
				{
					//Print the toString of the character
					System.out.println(characters.get(i).toString());	
					//loop through the houses to find the user entered characters house 
					for (int x =0;x<updatedHouses.size;x++) 
					{
						//check if the house of the character matches the house in that position
						if (updatedHouses.get(x).getName().equalsIgnoreCase((characters.get(i).allegiances) ))
						{
						 //if so, set intatiated houseName to that house
							houseName=updatedHouses.get(x);
							//print the house name
							System.out.println(houseName.getName());
							
							//use the finNode method to get the Characternode of the character. USe the characternode and pass it into the lineageToString to get the lineage of the character
							if (houseName.familyTree.findNode(houseName.familyTree.getMatriarch(), characters.get(i))!=null) 
							{
								System.out.println(houseName.familyTree.lineageToString(houseName.familyTree.findNode(houseName.familyTree.getMatriarch(), characters.get(i))));
								System.out.println();
							}
							else if (houseName.familyTree.findNode(houseName.familyTree.getPatriarch(), characters.get(i))!=null)
							{
							System.out.println(houseName.familyTree.lineageToString(houseName.familyTree.findNode(houseName.familyTree.getPatriarch(), characters.get(i))));
							System.out.println();
							}
						}
					}
				}
			}
		}
	
	//if the user indicates to use myarraylist, prompt the user to select a command, such as LLTD, remove all, remove next characrater. or they can indicate to go to original options by selecting return	
		if(userChar.equalsIgnoreCase("use myArrayList")) {
			System.out.println("Now using an ArrayList \n");
			//run this loop until the user states return or exit
			do {
			System.out.println("Enter a comand 'remove all' to remove all characters, 'remove next' to remove the next character," + "\n" +  
					"'LLTD'" +" to see which character is the least likely to die, or type return to return to character options like see battles of all characters or family trees etc or type 'exit' to exit the program" );
			
			userChar= userData.nextLine().trim();
			//remove all of the characters in least likely to die order
			if (userChar.equalsIgnoreCase("remove all")){
				 
				int count=0;
				while(!predictionsAL.isEmpty()) {
					
					Prediction removed= predictionsAL.remove();
					 
					System.out.println(count+ " Removed: " + removed.getCharacter().name+ " with PLOD of "+ removed.getPlod() );
					count++;
				}
			}
			//LLTD: display the character least likely to die in the entire series. use the heap that was selected. if the heap selected is empty, then use LLTD variable
			else if(userChar.equalsIgnoreCase("LLTD")) {
				System.out.println("The character least likely to die in the Entire Series is: ");
				System.out.println(LLTD );
				//check if empty, if not display what character is next to die in the list
				if (!predictionsAL.isEmpty()) {
				Prediction pred=predictionsAL.peek();
				System.out.println("LEAST LIKELY TO DIE in your ARRAYLIST: " + pred.getCharacter().name+ " with PLOD of "+ pred.getPlod() );
			}
				else {
					System.out.println("The ARRAY List is empty... there is no character least likely to die in your arrayList ");
					
				}
			}
			//remove the next character least likely to die. if no more characters left, indicate
			else if(userChar.equalsIgnoreCase("remove next")){
				if (!predictionsAL.isEmpty()) {
					
					Prediction removed= predictionsAL.remove();
					System.out.println("Removed: " + removed.getCharacter().name+ " with PLOD of "+ removed.getPlod() );
				}
				else {
					System.err.println( "No more characters left to remove \n" );

				}
			}
			// if wrong command was enterd, inform the user
			if(!userChar.equalsIgnoreCase("remove all") && !userChar.equalsIgnoreCase("exit") &&  !userChar.equalsIgnoreCase("return") && !userChar.equalsIgnoreCase("LLTD") && !userChar.equalsIgnoreCase("remove next") && !userChar.equalsIgnoreCase("family tree") && !userChar.equalsIgnoreCase("remove next") && validCharacter==false && noBattles==false){
				System.err.println("WRONGE ENTRY: Please select on of the provided commands \n");
			}
			}while(!userChar.equalsIgnoreCase("exit")&& !userChar.equalsIgnoreCase("return")) ;
		}
		
		//if the user indicates to use myarraylist, prompt the user to select a command, such as LLTD, remove all, remove next characrater. or they can indicate to go to original options by selecting return	
		 if(userChar.equalsIgnoreCase("use sLinkedList")) 
		 {
			 System.out.println("Now using an LinkedList \n");
			//run this loop until the user states return or exit
				do {
			//use ljnked list structure until the user types return to go to original options or exit to exit entire program
			System.out.println("Enter a comand 'remove all' to remove all characters, 'remove next' to remove the next character" + "\n" +  
				"'LLTD' to see which character is the least likely to die or type return to return to character options like see battles of all characters or family trees etc or type 'return' to return to the original options or 'exit' to exit the enitre program" );
			userChar= userData.nextLine().trim();
			//remove all characters in priority queue
			if (userChar.equalsIgnoreCase("remove all")){
				int count=0;
		
				while(!predicationLL.isEmpty()) {
					
					Prediction removed= predicationLL.remove();
					System.out.println(count+ " Removed: " + removed.getCharacter().name+ " with PLOD of "+ removed.getPlod() );
					count++;
				}
				
			}
			//LLTD: display the character least likely to die. use the heap that was selected to display the next character likely to die in the heap. if the heap selected is empty, then use LLTD variable
			else if(userChar.equalsIgnoreCase("LLTD")) {
					//displays least likely to die character in entire series
					System.out.println("The character least likely  to die in the Entire Series is: ");
					System.out.println(LLTD );
			
				if (!predicationLL.isEmpty()) {
					//displays least likely to die in the list
					
				Prediction pred=predicationLL.peekMin();
				System.out.println("LEAST LIKELY TO DIE in your linkedList: " + pred.getCharacter().name+ " with PLOD of "+ pred.getPlod() );
				}	
				else {
					System.out.println("The Linked List is empty... there is no character least likely to die in your linkedList ");
					 
				}
			}
			//remove the next character that is least likely to die
			else if(userChar.equalsIgnoreCase("remove next")){
				if (!predicationLL.isEmpty()) {
					
					Prediction removed= predicationLL.remove();
					System.out.println(" Removed: " + removed.getCharacter().name+ " with PLOD of "+ removed.getPlod() );
				}
				else {
					System.err.println( "No more characters left to remove \n" );

				}
			}
			//if wring entry was submitted, then indicate so
			if(!userChar.equalsIgnoreCase("remove all") && !userChar.equalsIgnoreCase("return") && !userChar.equalsIgnoreCase("exit") && !userChar.equalsIgnoreCase("LLTD") && !userChar.equalsIgnoreCase("remove next") && !userChar.equalsIgnoreCase("family tree") &&!userChar.equalsIgnoreCase("remove next") && validCharacter==false && noBattles==false){
				System.err.println("WRONGE ENTRY. Please select one of the provided commadns. To RETURN to original hompage options type 'Return' ");
			}// exit loop
			} while(!userChar.equalsIgnoreCase("exit") && !userChar.equalsIgnoreCase("return")) ;
		
		 }
	
		
		 //if character not found, or wrong commmand selected, inform the user
		if (!userChar.equalsIgnoreCase("return")&& !userChar.equalsIgnoreCase("exit") && !userChar.equalsIgnoreCase("family tree") && !userChar.equalsIgnoreCase("use myArrayList")&& !userChar.equalsIgnoreCase("use sLinkedList")&& validCharacter==false && noBattles==false && !userChar.equalsIgnoreCase("lltd"))
		{
		 
			 System.err.print("Command Not Found or Character not found \n" + "To remove next or remove all type 'use myarrylist' or 'use slinkedlist' \n");	
		 }
	} while (! userChar.equalsIgnoreCase("exit" ));
	//close the scanner input	
	userData.close();
	// exit the loop	
	
	
	 //Indicate That program has ended 
 
	System.out.println("Logging Off...");
	inputLineage.close();
	inputPredictions.close();
}



		
	}



