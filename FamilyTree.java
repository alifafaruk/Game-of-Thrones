package project4;

/**
 * Alifa Faruk
 * Project 4
 * Due December 5, 2018
 */
public class FamilyTree {
	//Create variables for the patriarch and matriarch
	private CharacterNode patriarch; //Patriarch of the House
	private CharacterNode matriarch; //Matriarch of the House
	
	//Inner nested class. 
	public class CharacterNode implements Comparable<CharacterNode>{
		 
			private  Character character; //character payload
			private CharacterNode father; //ref to father of character
			private CharacterNode mother; //ref to mother of character
			public  sLinkedList<CharacterNode> children= new sLinkedList<CharacterNode>(); //sLinkedList of the children of character
	 
			// the constuctor of the nested class; takes in a characterNode
			CharacterNode( Character character){
				this.character=character;
				 
			}

			//have a compareTO method that compares the characters
			@Override
			public int compareTo(CharacterNode o) {
				return (this.character).compareTo(o.character);
			}
			//getters for mother, father, children, and the character
			public CharacterNode getMother() {
				return mother;
			}
			public CharacterNode getFather() {
				return father;
			}
			public Character getChar() {
				return character;
			}
			public sLinkedList<CharacterNode> getChildren() {
				return children;
			}
			
			//setters to get the mother, and father
			public void setMother(CharacterNode mother) {
				this.mother = mother;
			}
			public void setFather(CharacterNode father) {
				this.father = father;
			}
			 
		}
	public CharacterNode getMatriarch() {
		return matriarch;
	}

	public void setMatriarch(CharacterNode matriarch) {
		this.matriarch = matriarch;
	}

	public CharacterNode getPatriarch() {
		return patriarch;
	}

	public void setPatriarch(CharacterNode patriarch) {
		this.patriarch = patriarch;
	}
			 
			/*
			 * The buildFamilytree function takes in a House variable. This method takes in this House variable that builds the family tree and returns the familytree. 
			 * It intailizes a new FamilyTree that will build out the tree of that house and returns that familytree. 
			 * Get the members of the house, put it in a linkedList and start to match parent with child
			 */
			public FamilyTree buildFamilyTree (House house){
				//Create familyTree that will build the tree structure and will be returned at the end
				FamilyTree tester= new FamilyTree();
				
				//put all of the members of the House into a linkedList
				sLinkedList<CharacterNode> membersOfHouse= new sLinkedList<CharacterNode>();
				
				//get the matriarch and patriach of the family and set to the familyTree patriarch and matriarch.
				tester.matriarch =new CharacterNode(house.getMatriarch());
				tester.patriarch=new CharacterNode(house.getPatriarch());
				
				//Created character variables for the matriarch and patriarch. This will be used to set the patriacrch and matriaches parents.
				Character matFather=new Character("Matriarch",null,null);
				Character patFather=new Character("Patriarch",null,null);
				
				//Set the familytrees patriarch and matriachs "parents" to the character variables created
				tester.matriarch.father= new CharacterNode(matFather);
				tester.matriarch.mother= new CharacterNode(matFather);
				tester.patriarch.father=new CharacterNode(patFather);
				tester.patriarch.mother=new CharacterNode(patFather);
				
				//Putting all members into a linkedList
				for(int i=0;i<house.members.size;i++)
				{
					CharacterNode charAtPosition= new CharacterNode(house.members.get(i));
					membersOfHouse.addFirst(charAtPosition);	
				}
				//seeing if the patriarch of matriarch have children.
				//Start matching child to parent
				for(int i=0; i<=membersOfHouse.size;i++) 
				{
					//father and mother variables
					Character fat;
					Character mat;
					 
					
					try {
					//if the character has a mother or father intialize the fat or mat variables using the members linkedlist. Else, indicate that there is not mat or fat
						//This will be used to set the child to parent
					if(membersOfHouse.get(i).character.getFatherName()!=null) 
					{
					 
					 fat= new Character(membersOfHouse.get(i).character.getFatherName(), membersOfHouse.get(i).character.allegiances,null) ;
					}
					else 
					{
						fat= new Character(null, membersOfHouse.get(i).character.allegiances,null) ;
					}
					if(membersOfHouse.get(i).character.getMotherName()!=null)
					{
					 mat= new Character(membersOfHouse.get(i).character.getMotherName(), membersOfHouse.get(i).character.allegiances,null) ;
					}
					else
					{
						mat= new Character(null, membersOfHouse.get(i).character.allegiances,null) ;	 
					}
					
					//inner loop that loops through the members of the house to see which is the child of the father and mother that was recently intialized
					for (int x=0; x<membersOfHouse.size;x++) 
					{
					 	//if the fathers name equals the names of the character, set add that child to that memberofHouse.children linkedLIst. Then set the father of the child to the fat
						if (fat.name.equals(membersOfHouse.get(x).character.name))
						{
							membersOfHouse.get(x).children.addFirst(membersOfHouse.get(i));
							membersOfHouse.get(i).father=membersOfHouse.get(x);		
						}
					 	//if the mother name equals the names of the character, set add that child to that memberofHouse.children linkedLIst. Then set the father of the child to the mat

						if (mat.name.equals(membersOfHouse.get(x).character.name)) 
						{
							membersOfHouse.get(x).children.addFirst(membersOfHouse.get(i));
							membersOfHouse.get(i).mother=membersOfHouse.get(x);
							//membersOfHouse.get(x).mother=motherOfChar;
						}
					}
					}catch(NullPointerException x) {}
				}
				// here we finilize the familytree structure by getting the matriarch and patriach kids and putting in their respective children linkedlist
				try {
				//Loop through all the members again
				for(int i=0; i<=membersOfHouse.size;i++) 
				{
					 //check if the patriach is the father. If so, add to the children linkedlist of the  familytree patriach. Set the father of the child to the patriach
						if (membersOfHouse.get(i).character.getFatherName().equals(tester.patriarch.character.name))
						{
							 tester.patriarch.children.addFirst(membersOfHouse.get(i));
							 membersOfHouse.get(i).father= tester.patriarch;
						}
						 //check if the matriach is the father. If so, add to the children linkedlist of the  familytree matriach. Set the mother child to the matriarch 
						if (membersOfHouse.get(i).character.getMotherName().equals(tester.matriarch.character.name)) 
						{
							 tester.matriarch.children.addFirst(membersOfHouse.get(i));
							 membersOfHouse.get(i).mother= tester.matriarch;
						}
				}
				}catch(NullPointerException x) {}
				  
			return tester;
					
		}
			/**
			 * Used to print the family tree in the game of thrones class
			 * Recursive function that takes in the characterNode. This is a recursive t=function that calls two other recursive function to retrive data.
			 * The printTree calls the printer function. It passes in the characters linkedlist of children to the printer function. The printer function loops through the children to see if the children have children by calling the get children function.
			 * The getchildren function returns that children of that character
			 * 
			 * @return: String with all the characterNames and the familytree of the family
			 */
	 
				public String printTree(CharacterNode characterNames){
				try {
						//base case. Return null if characterNames passed in is null
					if (characterNames==null)
					{
						return "";
					}
				//Check if it is a matriach or patriach
					if (characterNames.character.isMatriarch()) 
					{
						System.out.println("Matriarch: " + characterNames.character.name);
					}
				 
					if (characterNames.character.isPatriarch()) 
					{
						System.out.println("Patriarch: " + characterNames.character.name);
					}
				}catch(NullPointerException b) {}
				//call printer function
				String indent="";
					return printer(characterNames.children, indent);
				}
				
				/*
				 * the printer function takes in the list of children and loops through the list to see if the children have shildren.
				 * if it does, it calls the getChild function which return the string that holds the data of that child
				 * Returns the string containing all that data of the kids
				 */
				public String printer(sLinkedList<CharacterNode> x, String indent ) {
					x.sort();
					boolean flag=false;
			 
					String all="";
					
					//base cases: x is empty indicates no more kids
					if (x.isEmpty()) 
					{
						return all=""; 
					}
					//base case; x.head null indicates no kids
					if(x.head==null) 
					{
						return all;
					}
					indent+="	";
					//loop through the child and check if the children have children
					 for(int i=0; i<x.size;i++) 
					 {
						flag=true;
						all+=printChild(x.get(i), indent);
					}
					 
					 //return statements
					 if (flag==true) 
					 {
						 return all;
					 }
					 else 
					 {
						 return  all;
					 } 
				}
				/*
				 *The printChild method takes in the characterNode and retrieves all the data of that child. 
				 *this method recursively calls the printer method if the child has more kids, this repeats the process of finding the characters children
				 * Also takes in an indent value. For each generation, the indent increases
				 */
				
				public String printChild(CharacterNode x, String indent) {
					String str="";
					boolean flag=false;
				 
					String data="";
					//Base case; if null return no data
					if (x==null) 
					{
						return"";
					}
					
					// retrieve all the data of the character. GEt the mother name, father name.
					data+=indent+ x.getChar().name + " : " +"Father:" +x.getChar().getFatherName() +"  " + "Mother: " +x.getChar().getMotherName()+ "\n";
					
					//check if the child has kids; if so recursively call the printer function
					if(!x.children.isEmpty()) {
						flag=true; 
						indent+="    ";
						 str= printer(x.children, indent); 
					}
					//if the child had kids return statement
					if(flag==true) 
					{
						 if (!x.children.isEmpty()) 
						 {
							 return (data+str);
						 }
					}
					
					//return the child's family tree
				
					return data +str;
				 
				}
				
			/*
			 * 
			 * findCharacterNode searches the unsorted tree to see if the character is in the familytree. 
			 * It takes in a starting point and the target variable. This is done in a preorder manner, where the characterNode before its children
			 * 
			 * 
			 */
				
				 
			public CharacterNode findCharacterNode(CharacterNode startingPoint, CharacterNode ch){
				//Base Case: Check if the character entered is null; 
					if(ch==null) 
					{
						return null;
					}
					//Base Case: Check if the startingpoint is null
					if (startingPoint== null) 
					{
						return null;
					}
					
					//base case: if the startingPoint has no kids, return the character
					if (startingPoint.children.isEmpty()) 
					{
						return ch ;
					}
					//loop through the children of the startingpoint
					for (int i=0; i<startingPoint.children.size;i++)
					{
						 //check if member equals the target character; if so return the child
						if(	startingPoint.children.get(i).equals(ch))
						{
							 
							return startingPoint.children.get(i);
						}
						//check if the characters child has children, recursivly call the findCharacterNode function
						if(!startingPoint.children.get(i).children.isEmpty()) 
						{
							 
							return findCharacterNode(startingPoint.children.get(i),ch);
						}
					}
					//if not found return null
					return null;
			}
			
			/**
			 * This method is used in the game of thrones class in order to get the CharacterNode of character entered by the user from the familytree
			 * Works the same as the FindCharacterNode function
			 * takes in a CharacterNode (Either the patriarch or matriarch) and a character x. 
			 * recursively calls the children of the mat or pat and check every generation to the find the characterNode of the character
			 */
			
			public CharacterNode findNode(CharacterNode charName, Character x) {
				//base cases; if null return null
				if (x==null|| charName==null) 
				{
					return null;
				}
				
				//if the characterNode entered is a mat or pat, return the  charname
				if (x.isMatriarch()||x.isPatriarch()) 
				{
					return charName;
				}
				
				//if the characterNode does not have children, return null
				if (charName.children.isEmpty()) 
				{
					return null;
				}
				
				//loop through the  charnames children and check if the name match the character; else recursively call the findNode method if the characterNode.child has chihldren
				for (int i=0; i<charName.children.size;i++) 
				{
					//check if it is a match
					if(	charName.children.get(i).getChar().equals(x))
					{
						 
						return charName.children.get(i);
					}
					//else check the other generations of childrens/members
					if(!charName.children.get(i).children.isEmpty())
					{
						 
						return findNode(charName.children.get(i),x);
					}
			 
				
				}
				
				//if not found return null
				return null;
				
				
			}
			/**
			 * 
			 * lineageTOString convert the lineage of the characterNoe passed in to a string (done recursively)
			 * It utilizes two more methods to get the mother and fathers lineage.
			 * Takes in a characterNode, checks if it is a matriacrch or patriarch, if so returns the string indicating that.
			 * Else it call the motherLin method to get the lineage of the mothers side. And the fatherlin method to get the lineage of the father.
			 * 
			 * @param c
			 * @return
			 */
			
			public String lineageToString (CharacterNode c){
				 // Base case: if the character node is null return empty string
				if (c==null) 
				{
					return "";
				}
				//check if the characternode is a patriarch or a matriach. If so, we dont need to call the matherLin or fatherlin methods.
				// return the character name and indicate that it is a matriarch or patriach
				if (c.character.isPatriarch())
				{
					return c.character.name+" ---> " +"Father:" +"Patriarch - Mother:  Patriarch" ;
				}
				else if (c.character.isMatriarch())
				{
					return c.character.name+" ---> " +"Father:" +"Matriarch - Mother:  Matriarch" ;
				}
				// call the motherLin and fatherLin to get the lineage of both sides
				else 
				{
				return c.character.name+" ---> " +"Father:" +c.getChar().getFatherName() +" - " + "Mother: " +c.getChar().getMotherName() + "\n\n"+ "MOTHER SIDE: " +"\n"+ motherlin(c.mother) + "\n\n" + "Father SIDE: " +"\n"+ fatherLin(c.father);
				}
			}
			/**
			 * 
			 * this method return the lineage of the fathers side of the character.
			 * It takes in the fathers CharacterNode, check if they are a patriarch. if so reuturn info that conrrespondes to that.
			 * Else, it get the data of the father, including htier parents names. Then check if the father has parents. 
			 * If so recursively call the fatherlin method to get more generations data. Recursvly call on the mother of the father and the father of the father
			 * return Strings that contain this data
			 * 
			 * @param fatherLineage
			 * @return STRING WITH FATHERS LINEAGE
			 */
		public String fatherLin(CharacterNode fatherLineage) {
			//Base case. check if the father entered is null, if so reutrn null
			if (fatherLineage==null) 
			{
				return "";
			}
			
			//Create strings that will contin the infor of the characters fathers side and mothers side
			String fatherSide="";
			String motherSide="";
			//Check if the character is a matriarch of patriarch; if so return corresponding information
			if(fatherLineage.getChar().isPatriarch()) 
			{
				// System.out.println("HEREEE");
				fatherSide+=fatherLineage.character.name + " --> " +"Father: " +"Patriarch"+" - " +    " Mother: Patriarch" ;
				return fatherSide;
			}
			if(fatherLineage.getChar().isMatriarch())
			{
				motherSide+=fatherLineage.character.name + " ---> " +"Father:" +" - " +" Matriacrh "+ "Mother:  MAtriach" ;
				return motherSide;
			}
		 //if the father has a father, get more information of characters lineage
			if(fatherLineage.father!=null) 
			{
				 //create variabeles of the characters father and mother. this makes the parameters of the methods eaiser to understand
				CharacterNode tempFather=fatherLineage.father;
				CharacterNode tempMother=fatherLineage.mother;
				 // Get the data of the charact, including the mother and father
				fatherSide+=fatherLineage.character.name+" ---> " +"Father:" +fatherLineage.getChar().getFatherName() +" - " + "Mother: " +fatherLineage.getChar().getMotherName() ;
				//Resursively call the fatherLin to get the characters fathers lineage
				fatherSide+="\n"+fatherLin(tempFather);
				//Resursively call the fatherLin to get the characters mothers lineage
				if(tempMother!=null) 
				{
					motherSide+="\n"+fatherLin(tempMother);	
				}
				
			}
			
			// return the characters father and mothers lineage
			return fatherSide +motherSide;
		}
		/**
		 * 
		 * this method return the lineage of the mothers side of the character.
		 * It takes in the fathers CharacterNode, check if they are a patriarch. if so reuturn info that conrrespondes to that.
		 * Else, it get the data of the mother, including their parents names. Then check if the father has parents. 
		 * If so recursively call the motherlin method to get more generations data. Recursvly call on the mother of the mother and the father of the mother
		 * return Strings that contain this data
		 * 
		 * @param motherLineage
		 * @return STRING WITH FATHERS LINEAGE
		 */
		 
		public String motherlin(CharacterNode motherLineage) {
			//BAse Case; if the character entered is null, retun empty string
			if (motherLineage==null)
			{
				return "";
			}
			//Create strings that will contin the infor of the characters fathers side and mothers side
			String fatherSide="";
			String motherSide="";
			
			//Check if the character is a matriarch of patriarch; if so return corresponding information
			if(motherLineage.getChar().isPatriarch()) 
			{
				// System.out.println("HEREEE");
				fatherSide+=motherLineage.character.name + "--> " +"Father: " +"Patriarch"+" - " +  " Mother: Patriarch" ;
				return fatherSide;
			}
			if(motherLineage.getChar().isMatriarch())
			{
				motherSide+=motherLineage.character.name + " ---> " +"Father:" +" Matriarch "+" - " + "Mother:  Matriarch" ;
				return motherSide;
			}
		 
			//Check if the mother of the character entered has parents; if so get more information of their lineage
			if(motherLineage.father!=null) 
			{
				//Create temporary variable for the characters father and mother. Makes entering paramenters easier.
				CharacterNode tempFather=motherLineage.father;
				CharacterNode tempMother=motherLineage.mother;
				
				//Get information of the characters lineage, including mother and father
				fatherSide+=motherLineage.character.name+" ---> " +"Father:" +motherLineage.getChar().getFatherName()  +" - " + "Mother: " +motherLineage.getChar().getMotherName() ;
				//Recursively call the motherLin function to get more infor about the characters fathers lineage.
				fatherSide+="\n"+motherlin(tempFather);
				//Recursively call the motherLin function to get more information about the characters mothers lineage.
				if(tempMother!=null) 
				{
					motherSide+="\n"+motherlin(tempMother);	
				}
				
			}
			//return the data of the characters mother and father.
			return fatherSide +motherSide;
		}
}