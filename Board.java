import java.util.ArrayList;
/*
 * This program contains the board class where the Solitaire board is established,
 * and where many moves can be made, such as add a Card from the many fields and remove a Card
 * from many of the fields.
 */
public class Board{
    private ArrayList <ArrayList <Card>> tableau;
    private ArrayList <ArrayList <Card>> foundation;
    private ArrayList <Card> stock;
    private ArrayList <Card> deck;
    int indexOfStock;
    
    public Board (){
        initializeDeck();
        tableau = new ArrayList <>();
        foundation = new ArrayList <>();
        stock = new ArrayList <>();
        for (int i=0;i<7;i++){
            tableau.add(new ArrayList <>());
            for(int j=0;j<i+1;j++){
                int number = Utils.randomNumber(0, deck.size()-1);
                tableau.get(i).add(deck.get(number));
                deck.remove(number);
            }
        }
        for (int i=0;i<4;i++) {
        	foundation.add(new ArrayList<>());
        }
        for(int i=0;i<7;i++){
            tableau.get(i).get(tableau.get(i).size()-1).setIsVisible(true);
        }
        while(!deck.isEmpty()){
            int number = Utils.randomNumber(0, deck.size()-1);
            stock.add(deck.get(number));
            deck.remove(number);
        }
        indexOfStock =0;
        stock.get(indexOfStock).setIsVisible(true);
    }
	/*
	 * Method movePossibleTableau
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
    public int movePossibleTableau(Card from, Card to){
    	
        char [] allowedSymbols =   {'K','Q','J','X','9','8','7','6','5','4','3','2','A'};
        int thisIndex = 0;
        int otherIndex = 0;
        for (int i=0;i<allowedSymbols.length;i++){
            if(from.getSymbol() == allowedSymbols[i])
            	 thisIndex = i;
            else if (to.getSymbol() == allowedSymbols[i])
            	 otherIndex = i;
        }
        if(!(from.getIsVisible() || to.getIsVisible()))
        	return 0;

        if ((from.getSuit() =='H' && to.getSuit() =='D') || (from.getSuit() == 'C' && to.getSuit() == 'S')||
        		(from.getSuit() =='D' && to.getSuit() =='H') || (from.getSuit() =='S' && to.getSuit() =='C'))
        	return 0;
        //if ascending order ex 8 to 9
        if (thisIndex +1 == otherIndex)
            return 1;
        //if descending order ex 9 to 8
        else if (thisIndex == otherIndex +1) 
            return -1;
        else
            return 0;
    }
    public int movePossibleFoundation(Card from, Card to){
    	
        char [] allowedSymbols =   {'K','Q','J','X','9','8','7','6','5','4','3','2','A'};
        int thisIndex = 0;
        int otherIndex = 0;
        for (int i=0;i<allowedSymbols.length;i++){
            if(from.getSymbol() == allowedSymbols[i])
            	 thisIndex = i;
            else if (to.getSymbol() == allowedSymbols[i])
            	 otherIndex = i;
        }


        if (from.getSuit() != to.getSuit())
            return 0;
        //if ascending order ex 8 to 9
        if (thisIndex +1 == otherIndex)
            return 1;
        // if descending order ex 9 to 8
        else if (thisIndex == otherIndex +1) 
            return -1;
        else
            return 0;
    }
    
	/*
	 * Method initializeDeck
	 * This method is used to create a deck of cards with 13 cards of each suit and 4 cards of each symbol, 
	 * to a total of 52 cards, making each Card unique.
	 * Parameters:
	 * 		none
	 *  Return value:
	 *  	void
	 *  Local Variables used:
	 *  	addedSymbols; char array used to add a card of each symbol
	 *  	
	 */    
    public void initializeDeck(){
        deck = new ArrayList <>();
        char [] allowedSymbols = {'K','Q','J','X','9','8','7','6','5','4','3','2','A'};
        for (int i=0;i<13;i++){
            deck.add(new Card('S',allowedSymbols[i],false));
        }
        for (int i=0;i<13;i++){
            deck.add(new Card('D',allowedSymbols[i],false));
        }
        for (int i=0;i<13;i++){
            deck.add(new Card('C',allowedSymbols[i],false));
        }
        for (int i=0;i<13;i++){
            deck.add(new Card('H',allowedSymbols[i],false));
        }
    }
    
	/*
	 * Method gameWon
	 * This method is identify if the game has been won by making sure all Cards in tableau are visible and there are no cards in the stock
	 * Parameters:
	 * 		none
	 *  Return value:
	 *  	boolean
	 *  Local Variables used:
	 *  	none	
	 *  	
	 */
    public boolean gameWon(){
        for (int i=0;i<tableau.size();i++){
            for (int j=0;j<tableau.get(i).size();j++) {
                if (!tableau.get(i).get(j).getIsVisible()) 
                    return false;              
            }
        }
        if (stock.size()!=0)
        	return false;
        else
        	return true;
    }
	/*
	 * Method sortToWin
	 * This method is used to add tableau's cards to foundation separated by their suit.
	 * Parameters:
	 * 		none
	 *  Return value:
	 *  	void
	 *  Local Variables used:
	 *  	none
	 */
    public void sortToWin() {
    	for (int i=0;i<tableau.size();i++) {
    		for(int j=0;j<tableau.get(i).size();j++) {
    			if(tableau.get(i).get(j).getSuit() == 'H')
    				foundation.get(0).add(tableau.get(i).get(j));
    			else if(tableau.get(i).get(j).getSuit() == 'D')
    				foundation.get(1).add(tableau.get(i).get(j));
    			else if(tableau.get(i).get(j).getSuit() == 'S')
    				foundation.get(2).add(tableau.get(i).get(j));
    			else if(tableau.get(i).get(j).getSuit() == 'C')
    				foundation.get(3).add(tableau.get(i).get(j));

    		}
    	}
    	
    	for (int i1=0;i1<foundation.size();i1++) {
    		for(int j=0;j<foundation.get(i1).size();j++) {
    			foundation.get(i1).get(j).setIsVisible(true);
    		}
    	}
    	
    	for (int i1=0;i1<foundation.size();i1++)
    		foundation.get(i1).sort(null);
    	tableau.clear();
    }
    
	/*
	 * Method findCard
	 * This method is used to return a card using the Card's suit and symbol, since each card has its own combination of suit and symbol.
	 * Parameters:
	 * 		suit = char used to hold the Card's suit
	 * 		symbol = char used to hold the Card's symbol
	 *  Return value:
	 *  	Card
	 *  Local Variables used:
	 *  	none
	 *  	
	 *  	
	 */
    public Card findCard(char suit, char symbol) {
        for (int i=0;i<tableau.size();i++){
            for (int j=0;j<tableau.get(i).size();j++) {
                if (tableau.get(i).get(j).getSuit() == (suit) && tableau.get(i).get(j).getSymbol()==(symbol))
                    return tableau.get(i).get(j);
            }
        }
        for (int i=0;i<foundation.size();i++){
            for(int j=0;j<foundation.get(i).size();j++){
                if(foundation.get(i).get(j).getSuit()==(suit) && foundation.get(i).get(j).getSymbol()==(symbol))
                return foundation.get(i).get(j);
            }
        }
        for (int i=0;i<stock.size();i++){
            if (stock.get(i).getSuit()==(suit)&& stock.get(i).getSymbol()==(symbol))
            	System.out.println("iffing");
                return stock.get(i);
        }
		return null;
    }
    
	/*
	 * Method removeFromFoundation
	 * This method is used to remove a Card from the foundation.
	 * Parameters:
	 * 		from: Card used to remove that Card
	 *  Return value:
	 *  	void
	 *  Local Variables used:
	 *  	none
	 *  	
	 *  	
	 */
    public void removeFromFoundation(Card from){
    
            for(int i=0;i<foundation.size();i++){
                if (foundation.get(i).contains((Card)from)){
                    foundation.get(i).remove((Card) from);
                    if(!foundation.get(i).isEmpty()){
                        foundation.get(i).get(foundation.get(i).size()-1).setIsVisible(true);
                    }
                }
            }
        }
	/*
	 * Method addToFoundation
	 * This method is used to add a new Card to the foundation
	 * Parameters:
	 * 		removing: the Card that has been removed will be added here
	 * 		row: the row to which removing will be added to the end of 
	 *  Return value:
	 *  	void
	 *  Local Variables used:
	 *  	none
	 *  	
	 *  	
	 */
    public void addToFoundation(Card removing, int row){
		if(foundation.get(row-1).isEmpty())
			foundation.get(row-1).add(removing);
		else
			foundation.get(row-1).get(foundation.get(row-1).size()-1).setIsVisible(false);
        	foundation.get(row-1).add(removing);
    }
    
	/*
	 * Method removeOneTableau
	 * This method is used remove the last Card from a specified row
	 * Parameters:
	 * 		removing: Card used to hold the Card to be removed
	 * 		row: int value used to hold the row that the user has specified
	 *  Return value:
	 *  	void
	 *  Local Variables used:
	 *  	none
	 *  	
	 *  	
	 */
    public void removeOneTableau(Card removing, int row){
         tableau.get(row-1).remove((Card) removing);
            if(!tableau.get(row-1).isEmpty()){
                 tableau.get(row-1).get(tableau.get(row-1).size()-1).setIsVisible(true);
                    }
                }
	/*
	 * Method addOneToTableau
	 * This method is used to add ONE new Card to a row of cards at the tableau
	 * Parameters:
	 * 		removing: Card used to remove a Card from a row of cards
	 *  Return value:
	 *  	ArrayList <Card>
	 *  Local Variables used:
	 *  	row = int used to identify the row of removing
	 *  	column = int used to identify the column of removing
	 *  	removingColumn = ArrayList <Card> used to hold the array of the cards removed
	 */
    public ArrayList<Card> removeManyTableau(Card removingCard){
 	   int row=0;
 	   int column =0;
 	   for(int j=0;j<tableau.size();j++) { 
 			for(int i=0;i<tableau.get(j).size();i++) {
 			   if(tableau.get(j).get(i).equals(removingCard)) {
 				   row=i;
 				   column =j;
 			   }
 		   }
 	   }
 	   ArrayList <Card> removingColumn = new ArrayList <>();
 	   int size = tableau.get(column).size();
 	   for(int i=row;i<size;i++) {
 		   removingColumn.add(tableau.get(column).get(i));
 	   }
 	   for(int i=size-1;i>row-1;i--) {
 		   tableau.get(column).remove(i);
 	   }
 	   
         if(!tableau.get(column).isEmpty()){
              tableau.get(column).get(tableau.get(column).size()-1).setIsVisible(true);
              }
         return removingColumn;
          }         
     
        
	/*
	 * Method addOneTableau
	 * This method is used to add ONE new Card to a row of cards at the tableau
	 * Parameters:
	 * 		adding: Card used to add a Card to a row in the tableau
	 * 		rowTo: int used to hold the row of which the adding will be added to
	 *  Return value:
	 *  	void
	 *  Local Variables used:
	 *  	none
	 */
    public void addOneTableau(Card adding, int rowTo){    	
        tableau.get(rowTo-1).add(adding);
    }
    
	/*
	 * Method addManyTableau
	 * This method is used to add many Cards to a row in the tableau
	 * Parameters:
	 * 		removing: ArrayList <Card> used to add many Cards to the row
	 * 		rowTo: int used to identify the row of which the Card will be added to
	 *  Return value:
	 *  	void
	 *  Local Variables used:
	 *  	none
	 */
    public void addManyTableau(ArrayList <Card> removing,int rowTo) {
    	for (int i=0;i<removing.size();i++)
    		tableau.get(rowTo-1).add(removing.get(i));
    }
	/*
	 * Method removeFromStock
	 * This method is used to remove the card at stock. Only the card at the indexOfStock can be removed
	 * Parameters:
	 * 		removing: Card used to remove a Card from the stock
	 *  Return value:
	 *  	void
	 *  Local Variables used:
	 *  	none
	 */
    public void removeFromStock(Card removing){
        stock.remove((Card)removing);
        if (indexOfStock ==stock.size())
            indexOfStock =0;
        if (! stock.isEmpty())
        	stock.get(indexOfStock).setIsVisible(true);
    }
	/*
	 * Method nextCardStock
	 * This method is used to get the next Card in stock, by adding to the indexOfStock.
	 * Parameters:
	 * 		none
	 *  Return value:
	 *  	void
	 *  Local Variables used:
	 *  	none
	 */
    public void nextCardStock(){
    	if (!stock.isEmpty()) {
    		stock.get(indexOfStock).setIsVisible(false);
    		indexOfStock ++;
    		// below loops to the beginning of the stock
    		if(indexOfStock ==stock.size())
    			indexOfStock =0;
    		if (! stock.isEmpty())
    			stock.get(indexOfStock).setIsVisible(true);
    	}
    	
    }
    
	/*
	 * Method getTableau
	 * This method is used to return the tableau
	 * Parameters:
	 * 		none
	 *  Return value:
	 *  	ArrayList <ArrayList<Card>>
	 *  Local Variables used:
	 *  	none
	 */
    public ArrayList<ArrayList<Card>> getTableau(){
    	return this.tableau;
    }
	/*
	 * Method getTableau
	 * This method is used to return the foundation
	 * Parameters:
	 * 		none
	 *  Return value:
	 *  	ArrayList <ArrayList<Card>>
	 *  Local Variables used:
	 *  	none
	 */
    public ArrayList<ArrayList<Card>> getFoundation(){
    	return this.foundation;
    }
	/*
	 * Method getTableau
	 * This method is used to return the stock
	 * Parameters:
	 * 		none
	 *  Return value:
	 *  	<ArrayList<Card>
	 *  Local Variables used:
	 *  	none
	 */
    public ArrayList<Card> getStock(){
    	return this.stock;
    }
	/*
	 * Method getIndexOfStock
	 * This method is used to return the indexOfStock
	 * Parameters:
	 * 		none
	 *  Return value:
	 *  	int
	 *  Local Variables used:
	 *  	none
	 */
    public int getIndexOfStock() {
    	return this.indexOfStock;
    }
    
    
	/*
	 * Method toString
	 * This method is used to return the board printed in a uniform way that is easily readable by the user
	 * Parameters:
	 * 		none
	 *  Return value:
	 *  	String
	 *  Local Variables used:
	 *  	printed = String value used to hold the board that would be printed
	 */
    public String toString() {
    	String printed ="\n";
    	printed+="You have "+stock.size()+" cards in the stock.";
    	if (! stock.isEmpty()) {
    		printed+="\nYour stock position is number "+(indexOfStock+1)+"\n";
    		printed +="Stock: "+stock.get(indexOfStock).toString()+"\n";
    	}
    	else 
    		printed += "Stock: N\n";
    	printed += "Foundation:" +"\n";
    	for (int i=0;i<4;i++) {
    		printed +="Row number "+(i+1)+": ";
    		if (foundation.get(i).isEmpty())
    			printed += "N"+"\n";
    		else {
    			for(int j=0;j<foundation.get(i).size();j++)
    				printed += foundation.get(i).get(j) +" ";
    			printed +="\n";
    		}
    	}
    	
    	printed +="\nTableau:"+"\n";
    	if (tableau.isEmpty()){
        	printed += "No items in the tableau!\n";
    	}
    	else {
    	for (int i=0;i<7;i++) {
    		printed +="Row number "+(i+1)+": ";
    		for(int j=0;j<tableau.get(i).size();j++) {
    			printed +=tableau.get(i).get(j).toString()+" ";
    		}
    		
    		if (tableau.get(i).isEmpty())
    			printed += "N";
    		printed +="\n";
    	}
    	}
    	return printed;	
    }
}