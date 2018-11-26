package agent;

import java.util.Scanner;

public class Test extends Agent
{

	public Test() 
	{
		this.setDetails("Test Agent", "Agent used for testing the system");
		this.setKeywords("test");
	}

	@Override
	public void run(String optional) 
	{
		Scanner s = new Scanner(System.in);
		System.out.println("Please enter command:");
		
		this.kwSearch(s.nextLine());
	}

}
