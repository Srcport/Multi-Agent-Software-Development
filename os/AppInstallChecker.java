package os;

import java.io.File;
import java.util.Arrays;

import agent.Agent;
import agent.Data;

public class AppInstallChecker extends Agent
{

	public AppInstallChecker() 
	{
		this.setDetails("App Install Checker", "Checks whether an application is installed or uninstalled.");
		this.setConditions("os version");
		this.setKeywords("check application program installed uninstalled found device");
	}

	@Override
	public void run(String optional) 
	{	
		Data d = this.kwSearch("operating system version");
		
		if (d.check("mac"))
		{
			optional = this.kwSearch("capitalise text:" + optional).effect();
			File f = new File("/Applications/");
			
			if (!optional.endsWith(".app"))
				optional = optional + ".app";

			if (Arrays.asList(f.list()).contains(optional))
				this.setEffect(optional + " installed", true);
			else
				this.setEffect(optional + " uninstalled", false);
		}
	}
}
