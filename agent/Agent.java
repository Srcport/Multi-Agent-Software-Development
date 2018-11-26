package agent;

import java.util.HashMap;

import startup.Start;
import startup.Utils;

public abstract class Agent
{
	private String conditions = "";
	private String effect = "";
	private Boolean effectBool = null;
	public String keywords = "";
	public int agentID = Start.agents.size() + 1;
	private String name = "";
	private String description = "";
	
	/** All agents must register their keywords and ID with other agents */
	HashMap<Integer, String> keywordLog = new HashMap<Integer, String>();
			
	/* Executes the agent's workload */
	public abstract void run(String optional);
	
	public Agent()
	{
		registerAgent();
		System.out.println("[+] Agent registered (" + agentID + ")");
	}
	
	private void registerAgent()
	{
		Start.agents.add(this);
	}
	
	public void setDetails(String name, String description)
	{
		this.name = name;
		this.description = description;
	}
	
	public Data prepareData(String optional)
	{	
		System.out.println(this.name + " Agent excuting on data: " + optional);
		this.run(optional);
				
		if (this.effectBool == null)
			return new Data(this.agentID, this.keywords, this.name, this.description, this.effect);
		else
			return new Data(this.agentID, this.keywords, this.name, this.description, this.effect, this.effectBool);
	}
	
	/* Perform a keyword search of other agents */
	protected Data kwSearch(String kws)
	{
		String[] split = new String[2];
		
		if (kws.contains(":"))
			split = kws.split(":");
		else
			split[0] = kws;
		
		HashMap<Integer, Integer> totals = compareKeywordSimilarity(split[0]);
		
		return requestData(Utils.maxHashEntry(totals), split[1]);
	}
	
	/* Find the most suitable agent given a set of keywords */
	private HashMap<Integer, Integer> compareKeywordSimilarity(String kws)
	{
		HashMap<Integer, HashMap<String, Integer>> histogram = new HashMap<Integer, HashMap<String, Integer>>();
		
		for (int i : keywordLog.keySet())
		{
			for (String localKeyword : kws.toLowerCase().split(" "))
			{
				for (String remoteKeyword : keywordLog.get(i).split(" "))
				{
					if (remoteKeyword.equals(localKeyword))
					{
						if (histogram.containsKey(i))
						{
							if (histogram.get(i).containsKey(remoteKeyword))
							{
								histogram.get(i).put(remoteKeyword, histogram.get(i).get(remoteKeyword) + 1);
							}
							else
							{
								histogram.get(i).put(keywordLog.get(i), 1);
							}
						}
						else
						{
							histogram.put(i, new HashMap<String, Integer>());
							histogram.get(i).put(remoteKeyword, 1);
						}
					}
				}
			}
		}
		
		HashMap<Integer, Integer> totals = new HashMap<Integer, Integer>();
		
		for (int i : histogram.keySet())
		{
			//Utils.log("UID: " + i);
			
			for (String s : histogram.get(i).keySet())
			{
				//Utils.log("    " + s + " : " + histogram.get(i).get(s));
				
				if (totals.containsKey(i))
					totals.put(i, totals.get(i) + 1);
				else
					totals.put(i, 1);	
			}
		}
		
		return totals;
	}
	
	public void setKeywords(String keywords)
	{
		this.registerKeywords(agentID, keywords);
		this.keywords = keywords;
	}
	
	public void setConditions(String value)
	{
		this.conditions = value;		
	}
	
	public void setEffect(String value)
	{
		this.effect = value;		
	}
	
	public void setEffect(String value, boolean eBool)
	{
		this.effect = value;
		this.effectBool = eBool;		
	}
	
	/* Accept keywords & ID from an agent */
	public void acceptKeywords(int agentID, String keywords)
	{
		this.keywordLog.put(agentID, keywords.toLowerCase());
	}
	
	/* Reigster your keywords with other agents */
	public void registerKeywords(int agentID, String keywords)
	{
		for (int i = 0; i < Start.agents.size(); i++)
		{
			Start.agents.get(i).acceptKeywords(agentID, keywords);
		}
	}
	
	private Data requestData(int agentID, String optional)
	{
		for (int i = 0; i < Start.agents.size(); i++)
		{
			if (Start.agents.get(i).agentID == agentID)
			{
				return Start.agents.get(i).prepareData(optional);
			}
		}
		
		return null;
	}
	
	protected Data satisfyConditions()
	{
		return this.kwSearch(this.conditions);
	}
}
