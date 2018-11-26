package skype;

import agent.Agent;

public class ChatLogCollector extends Agent
{
	public ChatLogCollector() 
	{
		this.setConditions("skype installed");
		this.setKeywords("skype log parser collector");	
		this.setDetails("Skype Chat Log Collector", "Collects skype log from file system.");
	}

	@Override
	public void run(String optional) 
	{	
		if (this.kwSearch("check application installed:" + optional).effectBool())
		{
			if (this.kwSearch("os version").check("mac"))
			{
				
			}
		}
	}
}
