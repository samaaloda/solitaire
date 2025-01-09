import java.util.ArrayList;
import java.util.Scanner;
/* 
 * This program contains the main method where the program execution begins
 * The purpose of this project is to play solitaire. The user is able to do a variety of moves.
 * The board is an instance of the class Board.
 */
public class Main {
	
	/*
	 * Method inputChar
	 * This method is used to get a viable character from the user, using an array of viable characters.
	 * Parameters:
	 * 		message: String variable that is displayed to the user to ask for a character
	 * 		allowed: char array used to see if the user entered char is a viable character.
	 *  Return value:
	 *  	char
	 *  Local Variables used:
	 *  	invalidInput = boolean used to continue the loop in case the user entered an inappropriate char
	 *  	i = integer variable used to check each index 
	 *  	userEntered = String used to hold the user's response
	 *  	usersChar = char used to hold the first character of userEntered
	 */
	public static char inputChar (String message, char [] allowed) {
		Scanner userInput = new Scanner(System.in);
		boolean invalidInput = true;
		String userEntered;
		char usersChar = '.';
		
		while (invalidInput) {
			System.out.print(message);
			userEntered = userInput.next();
			usersChar = userEntered.charAt(0);
			for (int i=0;i<allowed.length;i++) {
				if (usersChar == allowed[i]) {
					invalidInput = false;
					break;
				}
			}
			if (invalidInput) 
				System.out.println("Please enter a valid symbol");
		} 
		return usersChar;
	}
	/*
	 * Method addOneToTableau
	 * This method is used to add ONE new Card to a row of cards at the tableau
	 * Parameters:
	 * 		board: Board used to get the fields in the instance of the board
	 * 		removing: Card used to add to a row of cards
	 * 		removedPile: Card used to identify where the removing Card comes from so it can be removed; either tableau (1), foundation (3) and stock (4)
	 * 		rowFrom: int used to identify which row the Card removing came from.
	 *  Return value:
	 *  	void
	 *  Local Variables used:
	 *  	adding = Card used to determine if removing can be added to it
	 *  	addedRow = int that is used to hold the row that the Card removing is being added to
	 *  	
	 *  	
	 */
	
	public static void addOneToTableau(Board board, Card removing, int removedPile, int rowFrom ) {
    	Card adding= new Card();
    	int addedRow = Utils.inputIntegerBetween("Which row would you like to add to? ", 1, 7);
    	// only a card with the symbol K can be added to an empty row at tableau
    		 if(board.getTableau().get(addedRow-1).isEmpty()) {
    			 if(removing.getSymbol()=='K') {
    				 board.getTableau().get(addedRow-1).add(removing);
        			 if (removedPile==1) // below removes removing from the tableau
        				 board.removeOneTableau(removing,rowFrom);
        			 else if(removedPile ==3)// below removes removing from the foundation
        				 board.removeFromFoundation(removing);
        			 else if(removedPile==4)// below removes removing from the stock
        				 board.removeFromStock(removing);
    			 }
    			 else 
    				 System.out.println("Move is not possible, must be a card with the symbol K.");
    		 } 
    		 else {
    			 adding = board.getTableau().get(addedRow-1).get(board.getTableau().get(addedRow-1).size()-1);
    			//checks if move is possible using removing and adding
    			 if (board.movePossibleTableau(removing, adding)==-1) {
    				 board.addOneTableau(removing, addedRow);
    			 
    				 System.out.println("Move possible!");
        			 if (removedPile==1) // below removes removing from the tableau
        				 board.removeOneTableau(removing,rowFrom);
        			 else if(removedPile ==3) // below removes removing from the foundation
        				 board.removeFromFoundation(removing);
        			 else if(removedPile==4) // below removes removing from the stock
        				 board.removeFromStock(removing);
    		 }
    			 else 
    				 System.out.println("Move is not possible; please take a look at the rules.");
    		 }

	}
	/*
	 * Method addToFoundation
	 * This method is used to add ONE new Card to a row of cards at the foundation
	 * Parameters:
	 * 		board: Board used to get the fields in the instance of the board
	 * 		removing: Card used to add to a row of cards
	 * 		removedPile: Card used to identify where the removing Card comes from so it can be removed; either tableau (1), foundation (3) and stock (4)
	 * 		rowFrom: int used to identify which row the Card removing came from.
	 *  Return value:
	 *  	void
	 *  Local Variables used:
	 *  	adding = Card used to determine if removing can be added to it
	 *  	addedRow = int that is used to hold the row that the Card removing is being added to
	 *  	
	 */
	
	public static void addToFoundation(Board board, Card removing, int removedPile, int rowFrom) {
		Card adding = new Card();
		int addedRow = Utils.inputIntegerBetween("Which row would you like to add to? ", 1, 4);
		// only a card with the symbol A can be added to an empty row at foundation
		if(board.getFoundation().get(addedRow-1).isEmpty()) {
			if(removing.getSymbol()== 'A') {
				board.getFoundation().get(addedRow-1).add(removing);
       			 if (removedPile==1)// below removes removing from the tableau
    				 board.removeOneTableau(removing,rowFrom);
    			 else if(removedPile ==3)// below removes removing from the foundation
    				 board.removeFromFoundation(removing);
    			 else if(removedPile==4)// below removes removing from the stock
    				 board.removeFromStock(removing);
    			 else
    				 System.out.println("Move is not possible, must be a card with the symbol A");
			}
		}
		 	else {
			 adding = board.getFoundation().get(addedRow-1).get(board.getFoundation().get(addedRow-1).size()-1);
			 //checks if move is possible between removing and adding
			 if (board.movePossibleFoundation(removing, adding)==1) {
				 board.addToFoundation(removing, addedRow);
				 
				 System.out.println("Move possible!");
    			 if (removedPile==1)// below removes removing from the tableau
    				 board.removeOneTableau(removing,rowFrom);
    			 else if(removedPile ==3)// below removes removing from the foundation
    				 board.removeFromFoundation(removing);
    			 else if(removedPile==4)// below removes removing from the stock
    				 board.removeFromStock(removing);
			 }
			 else 
				 System.out.println("Move is not possible; please take a look at the rules.");
    	}
	}
	/*
	 * Method addManyToTableau
	 * This method is used to add MANY Cards to a row of Cards
	 * Parameters:
	 * 		board: Board used to get the fields in the instance of the board
	 * 		removing: Card used to add to a row of cards
	 *  Return value:
	 *  	void
	 *  Local Variables used:
	 *  	addedRow = int that is used to hold the row that the Card removing is being added to
	 *  	
	 */
	public static void addManyToTableau(Board board, Card removing) {
    	int addedRow =0;
    	addedRow = Utils.inputIntegerBetween("Which row would you like to add to? ", 1, 7);

    		 if(board.getTableau().get(addedRow-1).isEmpty()) {
    			 // only a Card with the symbol K can be added to an empty row
    			 if(removing.getSymbol()=='K') {
 	        		ArrayList <Card> removingRow = board.removeManyTableau(removing);
    				 board.addManyTableau(removingRow, addedRow);
    			 }		 
    			 }
    		 else {
    			 Card adding= board.getTableau().get(addedRow-1).get(board.getTableau().get(addedRow-1).size()-1);
    			  if(board.movePossibleTableau(removing, adding)==-1) {
 	        		 ArrayList <Card> removingRow = board.removeManyTableau(removing);
    				 board.addManyTableau(removingRow, addedRow);
    			 }
    			  else 
    				 System.out.println("Move is not possible; please take a look at the rules.");
    		 }
	}

	public static void main(String [] args) {
		Board board = new Board ();	
		// Below welcomes user and instructs them on the rules
		System.out.println("Hello user! Welcome to Solitaire. Here is how to play:\n"
				+"The cards are labelled as such: HX \nThe suit is the first charcter, indicating whether it is H(hearts), D(diamonds), C(clubs), or S(spades)."
				+ "\nAll hearts and diamonds are red in colour, and all spades and clubs are black in colour.\n"
				+"The next character is the symbol of the card, indicating whether it is a K(king), Q(queen), J(jack), X(ten),9,8,7,6,4,3,2,A(ace).\n"
				+"If a card is labelled as N, this means that there is NO card there, therefore you can add one. "
				+ "\nIf it's labelled N on the tableau, only a card with symbol K can be added, and if it's on the foundation, only a card with the symbol A can be added.\n"
				+"If a card is labelled as O, this means that there is a card there, but you cannot see it as you must 'unlock' it. This can be done by removing the card(s) before it.\n"
				+"One deck of cards is spread randomly amongst all the sections, therby this game may not be solvable.\n"
				+"You can move cards all around the game, from stock to foundation to tableau.\n"
				+"You can add a card to the tableau only if it is a different colour(i.e. H and S), and the symbol is in the order of the sequence K,Q,J,X,9,8,7,6,5,4,3,2,A (i.e. SJ placed ON HQ)\n"
				+"You can add a card to the foundation only if it is the same suit (i.e. S and S) and if it is in the order of the sequence A,2,3,4,5,6,7,8,9,X,J,Q,K (i.e. S6 placed on S5\n"
				+"You can never add a card to the stock, but you can remove one card at a time from the stock."
				+ "\nYou only have access to one card at once, and the stock's position moves as you remove a card or check the next card in stock.\n"
				+"In order to win, you must unlock all cards in the tableau, and have zero cards in the stock.\n"
				+"This isn't traditional, however it's meant to save you time."
				+"Good luck and enjoy!\n");
		
		System.out.println(board);

		// A loop will be sustained until the user wins
        while (true) {
        	System.out.println("What move would you like to do:\n\t"
        			+ "1.Remove a card from Tableau\n\t"
        			+ "2.Remove many cards from Tableau.\n\t"
        			+ "3.Remove from Foundation\n\t"
        			+ "4.Remove from Stock\n\t"
        			+ "5.Next card in Stock");
        	
        	int menuOption = Utils.inputIntegerBetween("Please enter the appropriate number: ", 1, 5);
        	
        	if (menuOption !=5) {
	        	Card removing = new Card();
	        	int rowFrom=0;
	        	if(menuOption ==1) {
	            	rowFrom = Utils.inputIntegerBetween("Which row would you like to remove from? ",1,7);
	        		removing = board.getTableau().get(rowFrom-1).get(board.getTableau().get(rowFrom-1).size()-1);
	        	}
	        	else if (menuOption ==2) {
	            	 char [] allowedSuits = {'S','C','H','D'};
	            	 char [] allowedSymbols = {'K','Q','J','X','9','8','7','6','5','4','3','2','A'};
	            	 char removingSuit;
	            	 char removingSymbol;
	            	 while(true) {
	            	 removingSuit = inputChar("What is the suit of the card you would like to remove? ", allowedSuits);
	            	 removingSymbol = inputChar("What is the symbol of the card you would like to remove? ",allowedSymbols);
	            	if (board.findCard(removingSuit, removingSymbol).getIsVisible())
	            		break;
	            	else
	            		System.out.println("Card must be a visible card.");
	            	 }
	       			 removing = board.findCard(removingSuit, removingSymbol);

	       		 }
	       		 
	        	
	        	else if (menuOption ==3) {
	            	 rowFrom = Utils.inputIntegerBetween("Which row would you like to remove from? ",1,4);
	            	 removing = board.getFoundation().get(rowFrom-1).get(board.getFoundation().get(rowFrom-1).size()-1);
	        	}
	        	
	        	else if (menuOption ==4)
	        		removing = board.getStock().get(board.getIndexOfStock());
	        	
	        	System.out.println("The card removed: "+removing);
        	
	        	System.out.println("From which pile would you like to add to:\n\t1.Tableau\n\t2.Foundation");
	        	int addingMenuOption;
	        		while (true) {
	        			addingMenuOption= Utils.inputIntegerBetween("Please enter the appropriate number: ", 1, 2);
	        			// the user can only add a pile of cards to the tableau
	        			if (menuOption ==2 && addingMenuOption ==2)
	        				System.out.println("You cannot add a pile of cards to anywhere but tableau");
	        			else
	        				break;
	        		}
	
	        	if (addingMenuOption ==1 && menuOption ==2 ) {
	            	addManyToTableau(board,removing);
	        	}
	        	
	        	else if(addingMenuOption ==1)
	        		 addOneToTableau(board, removing,  menuOption,rowFrom);

	        	else if (addingMenuOption ==2) 
	        		addToFoundation(board,removing,menuOption,rowFrom);
        	// game is won if there are no more cards at stock, and all cards in tableau are visible
	        	if (board.gameWon()) {
	        		//sorts all the cards in foundation
	        		board.sortToWin();
	        		break;
	        	}
			}  		
	        
        	else if(menuOption==5)
            	board.nextCardStock();
        	
        	System.out.println(board);
    	
	}
        System.out.println(board);
        System.out.println("Congratulations on winning this game! You're officially extremely cool!");
	}
}

