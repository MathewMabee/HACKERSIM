package SIMULATION;

import java.util.Random;
import TERMINAL.TERMINAL;

/** #     #    #     #####  #    # ####### ######   #####  ### #     # 
 *  #     #   # #   #     # #   #  #       #     # #     #  #  ##   ## 
 *  #     #  #   #  #       #  #   #       #     # #        #  # # # # 
 *  ####### #     # #       ###    #####   ######   #####   #  #  #  # 
 *  #     # ####### #       #  #   #       #   #         #  #  #     # 
 *  #     # #     # #     # #   #  #       #    #  #     #  #  #     # 
 *  #     # #     #  #####  #    # ####### #     #  #####  ### #     # 
 *  ------------------------------------------------------------------
 *  A very basic hacking simulation written in Java
 * @author Mathew Mabee
 * @version 0.0.0.1
 */
public class HACK {
	
	/* Alarmed, Ale Arme alarmed alarms: "alarm the arms." Ale learned of harm. */
	
	/** Begin the program by calling the terminal
	 *  @param args default JRE requirement */
	public static void main(String[] args) {
		Random rnd = new Random(); int tmp = rnd.nextInt(2);
		
		/* Set up the mission to complete and begin the terminal simulation */
		setMission(tmp); mission = tmp;
		TERMINAL.basic();
	}
	
	/** Display to the user the task they must complete
	 *  @param mission is the mission chosen in the main method */
	private static void setMission(int mission) {
		switch(mission) {
			case 1: mission = 1; System.err.print("MISSION: HACK AND OPEN THE VAULT IN 4 MINUTES\n"); break;
			case 2: mission = 2; System.err.print("MISSION: HACK IN AND TRANSFER THE VAULT MONEY IN 4 MINUTES\n"); break;
		}
	}
	
	/** Uses backwards alphabet code to encrypt a given string.
	 * @param input is the string to be encrypted
	 * @return the encrypted value of a given string */
	public static String encrypt(String input) {
		String output = "";
		for(int i = 0; i <= input.length() - 1; i++) {
			String ltr = input.substring(i, i+1).toLowerCase();
			switch(ltr) {
			
			/* Replace a with z, z with a, etc */
				case "a": output += "z"; break;
				case "b": output += "y"; break;
				case "c": output += "x"; break;
				case "d": output += "w"; break;
				case "e": output += "v"; break;
				case "f": output += "u"; break;
				case "g": output += "t"; break;
				case "h": output += "s"; break;
				case "i": output += "r"; break;
				case "j": output += "q"; break;
				case "k": output += "p"; break;
				case "l": output += "o"; break;
				case "m": output += "n"; break;
				case "n": output += "m"; break;
				case "o": output += "l"; break;
				case "p": output += "k"; break;
				case "q": output += "j"; break;
				case "r": output += "i"; break;
				case "s": output += "h"; break;
				case "t": output += "g"; break;
				case "u": output += "f"; break;
				case "v": output += "e"; break;
				case "w": output += "d"; break;
				case "x": output += "c"; break;
				case "y": output += "b"; break;
				case "z": output += "a"; break;
				case "!": output += ">"; break;
				case "?": output += "<"; break;
				case "1": output += "9"; break;
				case "2": output += "8"; break;
				case "3": output += "7"; break;
				case "4": output += "6"; break;
				case "5": output += "5"; break;
				case "6": output += "4"; break;
				case "7": output += "3"; break;
				case "8": output += "2"; break;
				case "9": output += "1"; break;
				case "0": output += "."; break;
				
			}
		}
		return output;
	}
	
	
	/** Uses backwards alphabet code to decrypt a given string
	 *  @param input is the input to be decrypted
	 *  @return the decrypted string value */
	public static String decrypt(String input) {
		String output = "";
		for(int i = 0; i <= input.length() - 1; i++) {
			String ltr = input.substring(i, i+1).toLowerCase();
			switch(ltr) {
			
			/* Replace z with a, a with z, etc */
				case "z": output += "a"; break;
				case "y": output += "b"; break;
				case "x": output += "c"; break;
				case "w": output += "d"; break;
				case "v": output += "e"; break;
				case "u": output += "f"; break;
				case "t": output += "g"; break;
				case "s": output += "h"; break;
				case "r": output += "i"; break;
				case "q": output += "j"; break;
				case "p": output += "k"; break;
				case "o": output += "l"; break;
				case "n": output += "m"; break;
				case "m": output += "n"; break;
				case "l": output += "o"; break;
				case "k": output += "p"; break;
				case "j": output += "q"; break;
				case "i": output += "r"; break;
				case "h": output += "s"; break;
				case "g": output += "t"; break;
				case "f": output += "u"; break;
				case "e": output += "v"; break;
				case "d": output += "w"; break;
				case "c": output += "x"; break;
				case "b": output += "y"; break;
				case "a": output += "z"; break;
				case ">": output += "!"; break;
				case "<": output += "?"; break;
				case "9": output += "1"; break;
				case "8": output += "2"; break;
				case "7": output += "3"; break;
				case "6": output += "4"; break;
				case "5": output += "5"; break;
				case "4": output += "6"; break;
				case "3": output += "7"; break;
				case "2": output += "8"; break;
				case "1": output += "9"; break;
				case ".": output += "0"; break;
			}
		} return output;
	}
	
	
	/* Achievement booleans for detecting whether a user
	 * has accomplished the given goal */
	public static boolean userUnlockedVault = false;
	public static boolean userWithdrawMoney = false;
	public static int mission;
}
















