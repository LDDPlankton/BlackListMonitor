package com.blacklistmonitor;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class SimpleResolver
{
	public static final int LOOKUP_NOT_INITIALIZED = -1;
	public static final int LOOKUP_NETWORK_ERROR = 0;
	public static final int LOOKUP_SUCCESSFUL = 1;
	private int result;
	
	public SimpleResolver()
	{
		this.reset();
	}
	
	public void reset()
	{
		this.result = LOOKUP_NOT_INITIALIZED;
	}
	
	public int getResult()
	{
		return this.result;
	}
	
	/*
	 * This function will reverse a given IP Address.
	 * 
	 * @param	ip		The IP to Reverse
	 * @return	String
	 */
	public String reverseIP(String ip)
	{
		String ipParts[] = ip.split("\\.");
		String ipRev[] = new String[ipParts.length];
		String newIP = "";
		
		//REVERSE
		for(int i = 0; i < ipParts.length/2; i++)
		{
			String end = ipParts[ipParts.length-i-1];
			ipRev[ipParts.length-i-1] = ipParts[i];		//MOVE CURRENT ELEMENT TO END HALF
			ipRev[i] = end;
		}
		for(String i : ipRev)
			newIP += i + ".";
		newIP = newIP.substring(0, newIP.length()-1);		//REMOVE ENDING .
		return newIP;
	}
	
	/*
	 * This function will resolve the hostname, getting the IP in the blacklist that corresponds with a value to indicate if it's in the Blacklist.
	 * 
	 * @param	reverse_hostname	The ip_hostname to query the blacklist for
	 * @return	String
	 */
	public String resolve(String reverse_hostname)
	{
		this.reset();
		String ipResult = "";
		try
		{
			InetAddress addr = InetAddress.getByName(reverse_hostname);
			ipResult = addr.getHostAddress();
			this.result = LOOKUP_SUCCESSFUL;
		}
		catch (UnknownHostException e)
		{
			this.result = LOOKUP_NETWORK_ERROR;
			return null;
		}
		return ipResult;
	}
	
}
