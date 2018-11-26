package text;

import java.util.ArrayList;
import java.util.Arrays;

import agent.Agent;

public class CapitaliseText extends Agent
{

	public CapitaliseText() 
	{
		this.setDetails("Capitalise Text", "Capitalised Text");
		this.setKeywords("capitalise text string");
	}

	@Override
	public void run(String optional) 
	{
		if (optional == null)
			this.setEffect("");
		
		ArrayList<String> s = new ArrayList<String>();

		if (optional.contains(" "))
			s.addAll(Arrays.asList(optional.trim().toLowerCase().split("\\s+")));
		else
			s.add(optional.trim().toLowerCase());
		
		optional = "";
			    
		for (String i : s)
	    {
	        optional+= i.substring(0, 1).toUpperCase() + i.substring(1) + " "; 
	    }
		
	    this.setEffect(optional.trim());	    
	}

}
