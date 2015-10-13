package com.blacklistmonitor.blacklists;

public interface IBlackList
{
	public String getBlackListName();
	public String getRBLDomain();
	public boolean isBlackListed(int result, String ipReturned);
}
