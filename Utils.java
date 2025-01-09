import java.util.Random;
import java.util.Scanner;

public class Utils {
	public static double roundDouble(double value, int decimalPlaces) {
		if(decimalPlaces<0)
			throw new IllegalArgumentException("Invalid Number of Decimal Places");
		return Math.round(value+Math.pow(10, decimalPlaces))/Math.pow(10,decimalPlaces);
	}
	
	public static int[] randomArrayNumbers(int [] myArray,
										   int lowBound,
										   int highBound) {
		if (highBound<lowBound)
			throw new IllegalArgumentException("Low bound must be lower than high bound.");
		for (int x=0; x<myArray.length;x++) 
	myArray[x]= randomNumber(lowBound,highBound);
	return myArray;

	}
	public static int biggestNumber(int [] myArray) {
	int biggest = myArray[0];
	for(int x=0;x<myArray.length;x++) {
	if (myArray[x]>biggest) 
	biggest = myArray[x];
	}
	return biggest;
	}
	public static int smallestNumber(int [] myArray) {
		int smallest = myArray[0];
		for(int x=0;x<myArray.length;x++) {
			if (myArray[x]<smallest) 
				smallest = myArray[x];
		}
		return smallest;
	}
	public static String newPassword(int value) {
		String newString = "";
		char [] characters = new char [(int)'~'-(int)'!'+1];
		for (int x=0;x<characters.length;x++) {
			characters[x]=(char)(x+(int) '!');
		}
		for (int i=0;i<value;i++) {
			int random = randomNumber((int)'!',characters.length+(int)'!');
			newString += (char)random;
		}
		return newString;
	}
	public static String justWords(int value) {
		String newString = "";
		char [] characters = new char [(int)'Z'-(int)'A'+1];
		for (int x=0;x<characters.length;x++) {
			characters[x]=(char)(x+(int) 'A');
		}
		for (int i=0;i<value;i++) {
			int random = randomNumber((int)'A',characters.length+(int)'A'-1);
			newString += (char)random;
		}
		return newString;
	}
	
	public static int randomNumber(int lowBound, int highBound) {
		Random rand = new Random();
		if (highBound<lowBound)
			throw new IllegalArgumentException("Low bound must be lower than high bound.");
		
			return rand.nextInt(highBound-lowBound+1)+lowBound;
	}
	public static String convertArrayToString(int [] myArray) {
		String result = "";
		for (int i=0; i<myArray.length;i++)
			result += (myArray[i])+"\n";
		return result;
	}
	public static String convertArrayToString(String [] myArray) {
		String result = "";
		for (int i=0; i<myArray.length;i++)
			result += (myArray[i])+"\n";
		return result;
	}
	public static String convertArrayToString(double [] myArray) {
		String result = "";
		for (int i=0; i<myArray.length;i++)
			result += (myArray[i])+"\n";
		return result;
	}
	public static String convertArrayToString(char [] myArray) {
		String result = "";
		for (int i=0; i<myArray.length;i++)
			result += (myArray[i])+"\n";
		return result;
	}
	public static void initializeArray(int [] myArray,int value) {
		for (int i=0;i<myArray.length;i++) {
			myArray[i]=value;
		}
	}
	public static void initializeArray(String [] myArray,String value) {
		for (int i=0;i<myArray.length;i++) {
			myArray[i]=value;
		}
	}
	public static void initializeArray(double [] myArray,double value) {
		for (int i=0;i<myArray.length;i++) {
			myArray[i]=value;
		}
	}
	
	public static boolean isInteger (String inputtedString) {
		try {
			Integer.parseInt(inputtedString);
			return true;
		}
		catch (NumberFormatException e) {
			return false;
		}
	}
	
	public static int inputInteger (String message) {
		Scanner userInput = new Scanner(System.in);
		boolean invalidInput = true;
		String userEntered;
		int result = 0;
		
		while (invalidInput) {
			System.out.print(message);
			userEntered = userInput.nextLine();
			if (isInteger(userEntered)) {
				result = Integer.parseInt(userEntered);
				invalidInput = false;
			}
			else
				System.out.println("Please enter a valid integer value");
			
	}
		return result;
	}
		
	
public static int inputIntegerBetween(String message, int lowBound, int highBound) {

	Scanner userInput = new Scanner(System.in);
	boolean invalidInput = true;
	int result=0;

	do {
		result = inputInteger(message);
		if (result > highBound)
			System.out.println("Please enter a number lower than "+highBound);
		else if (result <lowBound)
			System.out.println("Please enter a number larger than "+lowBound);
		else
			invalidInput = false;
	} while (invalidInput);
	return result;
}


	public static boolean isDouble(String inputtedString) {
		try {
			Double.parseDouble(inputtedString);
			return true;
		}
		catch (NumberFormatException e) {
			return false;
		}
	}

	public static double obtainDouble(String message) {
		Scanner userInput = new Scanner(System.in);
		String userEntered;
		while (true) {
			System.out.print(message);
			userEntered = userInput.nextLine();
			if (isDouble(userEntered))
				break;
			else
				System.out.println("You must enter a decimal number.");
		}
		return (double) Double.parseDouble(userEntered);
	}
	

	
}