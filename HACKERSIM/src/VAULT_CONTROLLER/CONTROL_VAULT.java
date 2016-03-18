package VAULT_CONTROLLER;

import SIMULATION.HACK;
import TERMINAL.TERMINAL;

public class CONTROL_VAULT {

	
	/** Method for a user to call which will allow them to use openVault() and withdrawCurrency()
	 *  @param SUDO_PASSWORD is the password given by a previous hint */
	public static void vaultLogin(String SUDO_PASSWORD) {
		if(SUDO_PASSWORD.equalsIgnoreCase(vaultPassword)) { loggedIn = true; }
		else { System.err.print("\nINVALID SUDO PASSWORD\n\n"); }
	}
	
	
	/** "opens" the vault and checks if the user has completed the task according to the given mission
	 *  @throws InterruptedException is for the Thread.sleep calls */
	public static void openVault() throws InterruptedException {
		if(loggedIn) {
			
			/* Display stuff */
			Thread.sleep(400); System.err.print("\nVAULT OPENING");
			Thread.sleep(270); System.err.print("\nINITIALIZING UNLOCK SYSTEM");
			Thread.sleep(170); System.err.print("\nVAULT OPENED\n\n");
			
			/* Detect if the user has completed the objective according to the chosen mission */
			if(HACK.mission == 1) { System.out.print("\n\n\n\n\n\n\n\nFIN. YOU COMPLETED THE MISSION WITH " + TERMINAL.timeRemaining + " SECONDS REMAINING.\n\n\n\n\n"); System.exit(0); }
			
		}
		else { System.out.print("\nERR: NO USER LOGGED IN\n\n"); }
	}
	
	
	/** Withdraws a given amount from the "vault" and checks if the user has won
	 *  @param AMMT is the amount to subtract from the "money" value. Will be converted to double */
	public static void withdrawCurrency(String AMMT) {
		double temp;
		try {
			
			
			temp = Double.valueOf(AMMT);
			
			/* If the user is root, ake away the given amount value from the "money" in the vault */
			if(loggedIn) { if (temp <= CURRENCY) { CURRENCY -= temp; } }
			System.out.print("\n$" + AMMT + " HAS BEEN WITHDRAWN\n\n");
			
			/* Detect if the user has completed the objective according to the chosen mission */
			if(HACK.mission == 2) { System.out.print("\n\n\n\n\n\n\n\nFIN. YOU COMPLETED THE MISSION WITH " + TERMINAL.timeRemaining + " SECONDS REMAINING.\n\n\n\n\n"); System.exit(0); }
			
		}
		
		catch(Exception e) { System.err.print("\nIMPROPER USAGE SYNTAX\n\n"); temp = -1; }
	}
	
	
	/** Used to determine if the user can use the two vault methods */
	private static boolean loggedIn = false;
	
	/** "Sudo" password that the user must input */
	final static String vaultPassword = "USSR1XXR12";
	
	/* "Money" in the vault */
	private static double CURRENCY = 12000000000.01;
	
}
