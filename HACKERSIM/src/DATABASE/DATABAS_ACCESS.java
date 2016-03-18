package DATABASE;


public class DATABAS_ACCESS extends DATABASE{
	
	public static boolean correctSessionKey = false;
	
	/** Used to grant access to methods within this class(portion of the simulated server)
	 *  @param password is the password for this user
	 *  Was replaced by login() in src/TERMINAL/TERMINAL */
	public static void login(String password) {
		if(password.equals(psswrd)) { correctSessionKey = true; }
	}
	
	
	/** Searches for a user by name in a user bank
	 * @param NAME is the array to search for the name in
	 * @param input is the name to search for
	 * @return true if name is found. false if not found */
	public static boolean seqNameSearch(String input) {
		String temp = input;
		String[] NAME = DATABASE.names;
		for (int i = 0; i < NAME.length; i++) {
			/* Search for the given name within the database */
			if (NAME[i] == temp) { return true; }
			if (NAME[i] != temp) { NAME[i] = NAME[i + 1]; }
		}
		return false;
	}
	
	
	/** Returns the password of a given user ID
	 *  @deprecated
	 *  @param password is the password a user inputs to obtain the password
	 *  @param ID is the index location of the user
	 *  @return the password of a given user */
	public static String getPsswrd(String password, int ID) {
		if(correctSessionKey) {
			if(password.equals(DATABASE.psswrds[ID]))     { return DATABASE.psswrds[ID]; }
			else                                          { kickUser(); return null; }
		}
		else { return null; }
		
	}
	
	/** Returns the user in a specific location of the database
	 *  @param ID is the location in the database
	 *  @return the username in a given location in the database */
	public static String grabUsername(int ID) {
		if(ID <= names.length && ID >= 0)                 { return names[ID - 1]; }
		else                                              { return null; }
		
	}
	
	/** Was used to kick a player if they had too many failed attempts
	 * @deprecated Was replaced by the timer function */
	public static void kickUser() {
		failedAttempts++; correctSessionKey = false;
		System.out.print("You've been kicked for suspicious activity.");
	}
	
	/** @depreciated Was replaced by timer function */
	private static int failedAttempts = 0;
	
	/** Modifier password */
	private static String psswrd = "USR1296";
	
}
