package os;

import agent.Agent;

public class IdentifyOS extends Agent
{
	public IdentifyOS() 
	{
		this.setKeywords("operating system os version");
		this.setDetails("OS ID", "Identifies OS in use.");
	}
	
	@Override
	public void run(String optional)
	{
		String osName = System.getProperty("os.name").toLowerCase();
		
		if (osName.contains("mac"))
		{
			this.setEffect("mac osx");
		}
		else
		{
			this.setEffect("unknown");
		}
	}
}
