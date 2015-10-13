package com.blacklistmonitor;

import java.util.ArrayList;
import java.util.List;

import com.blacklistmonitor.blacklists.IBlackList;
import com.blacklistmonitor.blacklists.Sorbs;
import com.blacklistmonitor.blacklists.SpamHaus;

public class BlackListChecker
{
	private List<IBlackList> blackList;
	private List<String> ipInBlackList;
	private SimpleResolver simple;
	
	public BlackListChecker()
	{
		this.blackList = new ArrayList<IBlackList>();
		this.ipInBlackList = new ArrayList<String>();
		this.simple = new SimpleResolver();
		
		this.blackList.add( new SpamHaus() );
		this.blackList.add( new Sorbs() );
	}
	
	/*
	 * This function will determine if IP is in Any BlackLists
	 * 
	 * @return	Boolean
	 */
	public boolean isIPInAnyBlackLists()
	{
		if(this.ipInBlackList.size() > 0)
			return true;
		else
			return false;
	}
	
	public String getIPBlackListedBy()
	{
		return this.ipInBlackList.toString();
	}
	
	/*
	 * This function will take an IP, reverse it, check it against each RBL
	 * 
	 * @param	ip		The IP to determine if is in a BlackList
	 */
	public void checkIP(String ip)
	{
		//RESET
		this.ipInBlackList.clear();
		
		//REVERSE IP
		String ipReversed = this.simple.reverseIP(ip);
		
		for(IBlackList rbl : this.blackList)
		{
			//BUILD QUERY
			String query = ipReversed + "." + rbl.getRBLDomain();
			
			//RESOLVE AGAINST BLACKLIST
			String ipReturned = this.simple.resolve(query);
		
			//GET RESULT
			int result = this.simple.getResult();
			
			//CHECK BLACKLISTED + ADD IF NEEDED
			if(rbl.isBlackListed(result, ipReturned))
				this.ipInBlackList.add(rbl.getBlackListName());
		}
	}
	
}
