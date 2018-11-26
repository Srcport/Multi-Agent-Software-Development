package startup;

import java.util.HashMap;
import java.util.Map;

public class Utils 
{
	public static boolean logOutput = true;
	
	public static int maxHashEntry(HashMap<Integer, Integer> map)
	{
		Map.Entry<Integer, Integer> maxEntry = null;

		for (Map.Entry<Integer, Integer> entry : map.entrySet())
		{
		    if (maxEntry == null || entry.getValue().compareTo(maxEntry.getValue()) > 0)
		    {
		        maxEntry = entry;
		    }
		}
		
		return maxEntry.getKey();
	}
	
	public static void log(String text)
	{
		if (logOutput)
			System.out.println(text);
	}

}
