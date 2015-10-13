package com.blacklistmonitor.blacklists;

public class Sorbs extends BlackListBase implements IBlackList
{
	public Sorbs()
	{
		this.dnsblDomain = "dnsbl.sorbs.net";
		this.blacklistName = "Sorbs";
		this.blockedIPRange = new String[] {"127.0.0.2", "127.0.0.3", "127.0.0.4", "127.0.0.5", "127.0.0.6", "127.0.0.7", "127.0.0.8", 
											"127.0.0.9", "127.0.0.10", "127.0.0.11", "127.0.0.12", "127.0.0.14"};	
	}
}
