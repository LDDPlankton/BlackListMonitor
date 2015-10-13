package com.blacklistmonitor;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Set;
import java.util.TreeSet;

public class FileDB
{
	private Set<String> monitoredIPSet;
	
	public FileDB()
	{
		this.monitoredIPSet = new TreeSet<String>();
		this.loadDataFromDB();
	}
	
	/*
	 * This function will open our monitorDB file and load the IP's into our List.
	 * 
	 * @return	Boolean
	 */
	public boolean loadDataFromDB()
	{
		try
		{
			BufferedReader br = new BufferedReader(new FileReader("monitorDB.txt") );
			String line = "";
			while( (line=br.readLine()) != null)
			{
				line = line.trim();
				if(!line.equals(""))
					this.monitoredIPSet.add(line);
			}
			br.close();
			return true;
		}
		catch (Exception e)
		{
			return false;
		}
	}
	
	/*
	 * This function will save our monitoredIPSet to a File.
	 * 
	 * @return	Boolean
	 */
	public boolean saveDataToFile()
	{
		try
		{
			FileWriter wr = new FileWriter("monitorDB.txt");
			for(String item : this.monitoredIPSet)
			{
				wr.write(item + "\n");
			}
			wr.close();
			return true;
		}
		catch (Exception e)
		{
			System.out.println("Unable to save file: " + e.getMessage() );
			return false;
		}
	}
	
	public void addIP(String ip)
	{
		this.monitoredIPSet.add(ip);
	}
	
	public boolean delIP(String ip)
	{
		if(this.monitoredIPSet.contains(ip))
		{
			this.monitoredIPSet.remove(ip);
			return true;
		}
		return false;	//COULD NOT ADD
	}
	
	public String[] displayAll()
	{
		String[] ret = new String[this.monitoredIPSet.size()];
		int pos = 0;
		for(String item : this.monitoredIPSet)
		{
			ret[pos++] = item;
		}
		return ret;
	}

}
