/* 
 * This program contains the Card class where the characteristics of a Card will be stored, such as its
 * suit, symbol and if it is visible.
 */
public class Card implements Comparable <Card>{
    private char symbol;
    public char suit;
    private boolean isVisible;
    
    public Card(char suit,char symbol, boolean isVisible){
    	setSuit(suit); //D S C H
        setSymbol(symbol); // K, Q, J, X,9,8,7,6,5,4,3,2,A
        setIsVisible(isVisible);
    }
    
    public Card() {
	}
	/*
	 * Method setSymbol
	 * This method is used to set the symbol of the Card
	 * Parameters:
	 * 		symbol : char value used to store the symbol that is to be stored
	 *  Return value:
	 *  	void
	 *  Local Variables used:
	 *  	none
	 */
	public void setSymbol(char symbol){
		//throws an error if an invalid symbol is used
        if (findSymbol(symbol)==-1)
            throw new IllegalArgumentException("Symbol is not an allowed symbol.");
        this.symbol = symbol;
    }
	/*
	 * Method setIsVisible
	 * This method is used to set the Card as either visible or not
	 * Parameters:
	 * 		isVisible: boolean used to store whether the Card is visible or not
	 *  Return value:
	 *  	void
	 *  Local Variables used:
	 *  	none
	 */
    public void setIsVisible(boolean isVisible){
        this.isVisible = isVisible;
    }
	/*
	 * Method setSuit
	 * This method is used to set the suit of the Card
	 * Parameters:
	 * 		symbol : char value used to store the suit that is to be stored
	 *  Return value:
	 *  	void
	 *  Local Variables used:
	 *  	none
	 */
    public void setSuit(char suit){
    	// throws an error if an invalid suit is used
        if (suit == 'S'|| suit == 'H' || suit == 'D' || suit == 'C')
        	this.suit = suit;
        else
            throw new IllegalArgumentException("A suit can only be S (spades), H (hearts), D (diamond) or C (clubs)");
        
        
    }
	/*
	 * Method getSymbol
	 * This method is used to get the symbol of the Card
	 * Parameters:
	 * 		none
	 *  Return value:
	 *  	char
	 *  Local Variables used:
	 *  	none
	 */
    public char getSymbol() {
    	return this.symbol;
    }
	/*
	 * Method getSuit
	 * This method is used to get the suit of the Card
	 * Parameters:
	 * 		none
	 *  Return value:
	 *  	char
	 *  Local Variables used:
	 *  	none
	 */
    public char getSuit() {
    	return this.suit;
    }
	/*
	 * Method getIsVisible
	 * This method is used to get the whether the Card is visible or not
	 * Parameters:
	 * 		none
	 *  Return value:
	 *  	boolean
	 *  Local Variables used:
	 *  	none
	 */
    public boolean getIsVisible() {
    	return this.isVisible;
    }
	/*
	 * Method findSymbol
	 * This method is used to get the index of the symbol if it can be found in the array of allowedSymbols
	 * Parameters:
	 * 		symbol = char used to hold the symbol 
	 *  Return value:
	 *  	int 
	 *  Local Variables used:
	 *  	i = int used to iterate over every index to find the index number
	 */
    private int findSymbol(char symbol){
        char [] allowedSymbols =   {'K','Q','J','X','9','8','7','6','5','4','3','2','A'};
        for (int i=0;i<allowedSymbols.length;i++){
            if(symbol == allowedSymbols[i])
                return i;
        }
        // if not found, it will return a -1
        return -1;
    }
	/*
	 * Method toString
	 * This method is used to return the Card as a String
	 * Parameters:
	 * 		none
	 *  Return value:
	 *  	String 
	 *  Local Variables used:
	 *  	none
	 */
    public String toString(){
        if (this.getIsVisible())
            return ""+this.suit + this.symbol ;
        else
           return "O";
    }
	/*
	 * Method equals
	 * This method is used to return whether the two Cards are equal or not. Two Cards are equal or not if the symbol and the suits are the same.
	 * Parameters:
	 * 		other = Card used to check if equal to the Card of this instance
	 *  Return value:
	 *  	boolean 
	 *  Local Variables used:
	 *  	none
	 */
    public boolean equals(Card other) {
    	if ((this.getSymbol()== other.getSymbol()) && (this.getSuit() == other.getSuit()))
    		return true;
    	else
    		return false;
    }
	/*
	 * Method compareTo
	 * This method is used to compare the Card of this instance to another instance in the parameters. A Card is bigger
	 * than the other if it is earlier in the sequence K,Q,J,X,9,8,7,6,5,4,3,2,A than the other 
	 * Parameters:
	 * 		other = Card used to compare to the Card of this instance
	 *  Return value:
	 *  	int 
	 *  Local Variables used:
	 *  	thisIndex = the index of the Card's symbol in the sequence K,Q,J,X,9,8,7,6,5,4,3,2,A
	 *  	otherIndex = the index of the other Card's symbol in the sequence K,Q,J,X,9,8,7,6,5,4,3,2,A
	 */
    public int compareTo(Card other) {
        int thisIndex = findSymbol(this.getSymbol());
        int otherIndex = findSymbol(other.getSymbol());
        
        // if thisIndex is smaller, that means it is earlier in the sequence therefore it is bigger
    	if(thisIndex <otherIndex) 
    		return -1;
    	// if thisIndex is larger, that means it is later in the sequence therefore it is smaller
    	else if (thisIndex>otherIndex)
    		return -1;
    	else
    		return 0;
    }
}