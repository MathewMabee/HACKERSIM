package TERMINAL;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import javax.swing.Timer;
import DATABASE.DATABAS_ACCESS;
import SIMULATION.HACK;
import VAULT_CONTROLLER.CONTROL_VAULT;

public class TERMINAL {
	
	
	/** Grabs basic input from the user and translates it into commands */
	public static void basic() {
		timer();
		try { boot(); } 
		catch (InterruptedException e) { e.printStackTrace(); System.gc(); }
		
		while (running) {
			String temp = input.next();
			
			/* Basic switch case for calling methods */
			switch (temp) {
				case "decrypt":				getParams("decrypt");		break;
				case "time":				getTime();					break;
				case "VAULT.sh":			vaultInterpreter(-1);		break;
				case "login":				login();					break;
				case "dir":					dir();						break;
				case "cd":					getParams("cd");			break;
				case "help":				/* ?????(); */				break;
				case "edit":				getParams("edit");			break;
				case "setPsswrd":			getParams("setPsswrd");		break;
				case "getPsswrd":			getParams("getPsswrd");		break;
				case "grabUsername":		getParams("grabUsername");	break;
				case "VAULTLOGIN.sh":		vaultInterpreter(0);		break;
				case "OPENVAULT.sh":		vaultInterpreter(1);		break;
				case "WITHDRAWCURRENCY.sh":	vaultInterpreter(2);		break;
				default: System.out.print(temp.toUpperCase() + " IS NOT RECOGNIZED AS A COMMAND\n");
			}
			
			
			/* Switch case for printing the current location before user input */
			if(!DATABAS_ACCESS.correctSessionKey) {
				switch(currentDirLvl[0][0]) {
					case 0: System.out.print("$Z:/ ");					break;
					case 1: System.out.print("$Z:/ETC/ ");				break;
					case 2: System.out.print("$Z:/ETC/DATABASE/ ");		break;
				}
			} else {
				
				/* If the user is "root" */
				switch(currentDirLvl[1][0]) {
					case 0: System.out.print("$X:/ ");					break;
					case 1: System.out.print("$X:/BIN/ ");				break;
					case 2: System.out.print("$X:/BIN/PSSWRD/ ");		break;
				}	
			}
		}
	}
	
	
	/** Print a realistic "boot screen" for the simulation server
	 *  @throws InterruptedException used for the sleep method */
	private static void boot() throws InterruptedException {
		System.out.println("© MEGASERVER 2032 - SERVER CONFIDENTIAL");
		System.out.println("MEGASERVER SHELL OS IS BOOTING...");
		Thread.sleep(300);
		System.out.println("SYSTEMS CHECK OK");
		Thread.sleep(213);
		System.out.println("MEMORY CHECK OK");
		Thread.sleep(78);
		System.out.print("\n$Z:/ ");
	}
	
	
	/** Allow the user to "log-in" to the server as root
	 * Hard-coded user LISA is root. The password is found decrypted in src/DATABASE/UNSECURED_PASSWORD.TXT */
	private static void login() {
		System.out.print("\n$USR/LISA~ LOGIN PASSWORD REQUIRED: "); String pass = input.next();
		if(pass.equalsIgnoreCase("lisa92"))		{ DATABAS_ACCESS.correctSessionKey = true;           }
		else									{ System.out.print("\nINCORRECT USER PASSWORD\n\n"); }
	}
	
	
	/** Used for controlling certain methods of the CONTROL_VAULT class
	 *  @param call "locked" methods within the CONTROL_VAULT */
	private static void vaultInterpreter(int call) {
		switch(call) {
		
			case 0: System.out.print("SUDO PASSWORD REQUIRED: "); CONTROL_VAULT.vaultLogin(input.next());					break;
			case 1: try { CONTROL_VAULT.openVault(); } catch (InterruptedException e) { e.printStackTrace(); }				break;
			case 2: System.out.print("WITHDRAW AMMOUNT (FORMAT: 00.00) ");	CONTROL_VAULT.withdrawCurrency(input.next());	break;
		
			default: if(DATABAS_ACCESS.correctSessionKey && currentDirLvl[1][0] == 2) { System.out.print("\nVAULT COMMANDS:\n    VAULTLOGIN.sh\n    OPENVAULT.sh\n    [DEPRECIATED] WITHDRAWCURRENCY.sh\n\n"); } break;
		}
		
	}
	

	
	
	/** Lists the "contents" of the fake directory the user is in. */
	private static void dir() {
		if(!DATABAS_ACCESS.correctSessionKey) {
			if(currentDirLvl[0][0] == 0) { System.out.println("DRIVE Z:/\n ETC..................(directory)\n"); }
			if(currentDirLvl[0][0] == 1) { System.out.println("DRIVE Z:/ETC/\n DATABASE.........(directory)\n"); }
			if(currentDirLvl[0][0] == 2) { System.out.println("DRIVE Z:/ETC/DATABASE\n     DATABASE.xll.........(file)"+
																				   "\n     DATABAS_ACCESS.xll...(file)" + 
																				   "\n     PASSWORDS.xll........(file)\n\n"); }
		} else {
			if(currentDirLvl[1][0] == 0) { System.out.println("DRIVE X:/\n BIN..................(directory)\n"); }
			if(currentDirLvl[1][0] == 1) { System.out.println("DRIVE X:/BIN/\n PSSWRD...........(directory)\n"); }
			if(currentDirLvl[1][0] == 2) { System.out.println("DRIVE X:/BIN/PSSWRD\n     PASS.xll........(file)" + 
																			     "\n     VAULT.sh........(executable)\n\n"); }
		}
	}
	
	
	/** Helps the user traverse the fake server
	 *  @param location is the location within the folder the user wants to go to. */
	private static void cd(String location) {
		
		/* If the user is in Z:, go to ETC */
		if(location.equalsIgnoreCase(directory[1][0]) && currentDirLvl[0][0] == 0)					{ currentDirLvl[0][0]++; }
		
		/* If the user is in Z:/ETC, go to DATABASE */
		if(location.equalsIgnoreCase(directory[2][0]) && currentDirLvl[0][0] == 1)					{ currentDirLvl[0][0]++; }
		
		/* ".." goes down one level as with all shells */
		if(location.equals("..") && currentDirLvl[0][0] > 0)										{ currentDirLvl[0][0]--; }
		
		
		/*Display the help dialog for the command*/
		if(location.equalsIgnoreCase("help"))														{ help("cd"); }
		
		
		/* CD commands for if user is "ROOT" */
		if(location.equals("BIN") && currentDirLvl[1][0] == 0) { 
			if(DATABAS_ACCESS.correctSessionKey) { currentDirLvl[1][0]++; }
			else { System.out.print("SUDO ACCESS REQUIRED TO ACCESS X:/BIN/n"); }
		}
		
		if(location.equals("PSSWRD") && currentDirLvl[1][0] == 1) { 
			if(DATABAS_ACCESS.correctSessionKey) { currentDirLvl[1][0]++; }
			else { System.out.print("SUDO ACCESS REQUIRED TO ACCESS X:/BIN/PSSWRD/n"); }
		}
		
		if(location.equals("..") && currentDirLvl[1][0] > 0 && DATABAS_ACCESS.correctSessionKey)	{ currentDirLvl[1][0]--; }
		
		
		
	}
	
	
	/** Print the contents of a file for the user to read
	 *  @param file is the file inputed by the user and not the actual file in the package */
	public static void printFile(String file) {
		Scanner output = null;
		if (file.equals("PASSWORDS.xll") && currentDirLvl[0][0] == 2) {
			try { output = new Scanner(new File("src/DATABASE/UNSECURED_PASSWORD.TXT"));									 	} 
			catch (FileNotFoundException e)										{ e.printStackTrace();                          }
			while (output.hasNextLine())										{ System.out.print(output.nextLine() + "\n");   }
		}
		
		else if(file.equals("DATABASE.xll") && currentDirLvl[0][0] == 2)		{ System.err.print("INVALID FILE TYPE\n");		}
		else if(file.equals("DATABASE_ACCESS.xll") && currentDirLvl[0][0] == 2) { System.err.print("INVALID FILE TYPE\n");		}
		
		else if (file.equals("PASS.xll") && currentDirLvl[1][0] == 2) {
			
			try { output = new Scanner(new File("src/DATABASE/PASS.txt")); } 
			catch (FileNotFoundException e)                                     { e.printStackTrace();							}
			while (output.hasNextLine())                                        { System.out.print(output.nextLine() + "\n");	}
		}
		
		else																	{ System.err.print("FILE DOES NOT EXIST");		}
	}
	
	
	/** Shows the user a tip for a given command
	 * @param input is the method to show a tip for */
	protected static void help(String input) {
		if(input.equals("grabUsername"))		{ System.err.print("\ngrabUsername USAGE:\nMUST INTEGER INPUT FOR USER ID");   }
		if(input.equals("cd"))					{ System.err.print("\nCD USAGE: Used to navigate to a location. i.e. cd ETC"); }
	}
	
	
	/** After calling a method, the user must input the desired parameters
	 *  @param method is the method name that is being called */
	private static void getParams(String method) {
		switch(method) {
			case "edit": printFile(input.next()); 															break;	// Get the next word after the edit command
			case "cd": cd(input.next()); 																	break;	// Get the location entered after the CD command
			case "decrypt": System.out.print("DECRYPTED VALUE: " + HACK.decrypt(input.next()) + "\n\n");	break;	// Get the text to decrypt in the decrypt command
			case "grabUsername": 
				try {System.out.print(DATABAS_ACCESS.grabUsername(Integer.valueOf(input.next())));			break; } 
				catch(NumberFormatException e) { help("grabUsername");										break; }									
			
		}
		
		
	}
	
	/** Output the remaining time for the user */
	private static void getTime() { System.err.print("\n" + timeRemaining + " seconds remaining.\n"); }
	
	/** Hard-coded four minute count down */
	public static double timeRemaining = 245;
	
	/** Remove one second from the remaining time every second */
	public static void timer() {
		int delay = 1000;
		/* Count down every second in background */
		  ActionListener update = new ActionListener() {
		      public void actionPerformed(ActionEvent evt) {
		    	  /* Close the program when time runs out */
		    	  if(timeRemaining <= 0) { System.err.print("\n\n\n\n\n\n\n\nBREACH DETECTED.. SHUTTING DOWN ALL ACCESS.\nYOU'VE LOST THE SIMULATION. \n\n\n\n\n"); running = false; System.gc(); System.exit(0); }
		    	  
		    	  timeRemaining--;
		      }
		  };
		  new Timer(delay, update).start();
	}
	
	
	/** True when program is active */
	private static boolean running = true;
	
	/** Used for keeping track of what directory the user is in */
	private static int[][] currentDirLvl = { {0, 1}, {0, 0} };
	
	/** Class-wide scanner to grab user input for the terminal */
	final static Scanner input = new Scanner(System.in);
	
	/** Layout of the simulated file system (mostly for organization while writing) */
	private static String[][] directory = {	{"Z:", "X:"},
											{"ETC", "BIN"},
											{"DATABASE", "PSSWRD"},
											{"DATABASE.xll", "DATABASE_ACCESS.xll", "PASSWORDS.xll" } };
	
}

