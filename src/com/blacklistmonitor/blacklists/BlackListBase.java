package com.blacklistmonitor.blacklists;

import com.blacklistmonitor.SimpleResolver;

public class BlackListBase
{
	protected String[] blockedIPRange;
	protected String dnsblDomain;
	protected String blacklistName;
	
	public BlackListBase()
	{
		
	}
	
	/*
	 * This function will return the Name of the BlackList we are in.
	 * 
	 * @return	String
	 */
	public String getBlackListName()
	{
		return this.blacklistName;
	}
	
	/*
	 * This function will return the Domain of the BlackList we are querying.
	 * 
	 * @return	String
	 */
	public String getRBLDomain()
	{
		return this.dnsblDomain;
	}
	
	/*
	 * This function will determine if the IP is BlackListed
	 * 
	 * @return	Boolean
	 */
	public boolean isBlackListed(int result, String ipReturned)
	{
		if(result == SimpleResolver.LOOKUP_SUCCESSFUL)
		{
			//IF RETURNED IP IS IN OUR BLOCKED IP RANGE ... RETURN TRUE/IS BLOCKED
			for(String blockedIP : this.blockedIPRange)
				if( blockedIP.equals(ipReturned) )
					return true;
			return true;
		}
		else
			return false;
	}
}
