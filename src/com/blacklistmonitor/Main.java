package com.blacklistmonitor;

import java.text.ParseException;

public class Main
{
	private static final String VERSION = "1.0.0";
	
	public static void printMenu()
	{
		System.out.println("BlackList Checker " + VERSION + "\n"
				+ "Usage: java -jar <program> <option> <args...>\n"
				+ "\tadd <IP>		: The IP to Add to our DB\n"
				+ "\tdel <IP>		: The IP to Remove from our DB\n"
				+ "\tprint			: List all IP's to Monitor\n"
				+ "\tcheck <ip>		: The IP to Check\n"
				+ "\tcheckall		: Check all IP's in DB\n");
				
	}
	
	public static void requireNumberOfArguments(String args[], int required)
	{
		if(args.length != required)
		{
			System.out.println("Invalid Number of Arguments!");
			System.exit(1);;
		}
	}
	
	public static void main(String[] args) throws ParseException
	{
		//ENSURE ARGUMENTS PASSED
		if(args.length < 1)
		{
			printMenu();
			System.exit(-1);
		}
		//VARIABLES + DECARED OBJECTS
		BlackListChecker check = new BlackListChecker();
		FileDB db = new FileDB();		
		String ip = "";
		
		//HANDLE BASED ON COMMAND
		switch(args[0])
		{
			case "add":
				//HAVE REQUIRED ARGS OR DIE
				requireNumberOfArguments(args, 2);

				//ASSIGN VARIABLES
				ip = args[1];

				//ADD IP
				db.addIP(ip);
				
				System.out.println(String.format("The IP [%s] has been Added!", ip));
				
				break;
				
			case "del":
				//HAVE REQUIRED ARGS OR DIE
				requireNumberOfArguments(args, 2);
				
				//ASSIGN VARIABLES
				ip = args[1];
				
				//DEL IP
				boolean status = db.delIP(ip);
				
				if(status)
					System.out.println(String.format("The IP [%s] has been Deleted!", ip));
				else
					System.out.println(String.format("The IP [%s] is not in our Monitoring List!", ip));
				
				break;

			case "print":
				//HAVE REQUIRED ARGS OR DIE
				requireNumberOfArguments(args, 1);
				
				String[] ipItems1 = db.displayAll();
				System.out.println("----Displaying IP's in DB----");
				for(String item : ipItems1)
				{
					System.out.println(item);
				}
				System.out.println("-----------------------------");
				
				break;
				
			case "check":
				//HAVE REQUIRED ARGS OR DIE
				requireNumberOfArguments(args, 2);
				
				//ASSIGN VARIABLES
				ip = args[1];
				
				//CHECK IP
				check.checkIP(ip);
				
				//RESULT
				if(check.isIPInAnyBlackLists())
				{
					System.out.print(String.format("The IP [%s] is BlackListed! -> ", ip));
					System.out.println(check.getIPBlackListedBy());
				}
				else
				{
					System.out.println(String.format("The IP [%s] is NOT BlackListed!", ip));
				}
				
				break;
				
			case "checkall":
				//HAVE REQUIRED ARGS OR DIE
				requireNumberOfArguments(args, 1);
				
				//GET ALL IPS IN DB
				String[] ipItems2 = db.displayAll();
				
				for(String tempIP : ipItems2)
				{
					//CHECK IP
					check.checkIP(tempIP);
					
					if(check.isIPInAnyBlackLists())
					{
						System.out.println(String.format("The IP [%s] is BlackListed!", tempIP));
						System.out.println(check.getIPBlackListedBy());
					}
					else
					{
						System.out.println(String.format("The IP [%s] is NOT BlackListed!", tempIP));
					}
				}
				
				break;
			default:
				printMenu();
		}
		
		
		//UPDATE FILEDB
		db.saveDataToFile();
		
	}

}
