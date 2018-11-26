package startup;

import java.util.ArrayList;
import java.util.LinkedHashMap;

import agent.Agent;
import agent.Test;
import os.AppInstallChecker;
import os.IdentifyOS;
import skype.ChatLogCollector;
import text.CapitaliseText;

public class Start 
{
	public static LinkedHashMap<String, Object> condLog = new LinkedHashMap<String, Object>();
	public static LinkedHashMap<String, Object> effLog = new LinkedHashMap<String, Object>();
	public static ArrayList<Agent> agents = new ArrayList<Agent>();
	
	public static void main(String[] args) 
	{
		new IdentifyOS();
		ChatLogCollector c = new ChatLogCollector();
		new AppInstallChecker();
		CapitaliseText ct = new CapitaliseText();
		Test t = new Test();
		
		/* Update the keywords after agents initialised */
		for (Agent a : agents)
		{
			a.registerKeywords(a.agentID, a.keywords);
		}
		
		t.run(null);
		
		System.out.println("\n[+]Conditions & Effects");
		System.out.println(" | " + agents.size() + " agents registered.");
		System.out.println(" | " + condLog.size() + " conditions registered.");
		System.out.println(" | " + effLog.size() + " effects registered.");
	}
}
