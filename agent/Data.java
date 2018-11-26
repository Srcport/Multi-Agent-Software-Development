package agent;

public class Data 
{
	public int uid = 0;
	public String name = "";
	public String desc = "";
	public String kw = "";
	protected String effect = "";
	protected Boolean effectBool = null;
	
	public Data(int uid, String name, String desc, String kw, String effect) 
	{
		this.uid = uid;
		this.name = name;
		this.desc = desc;
		this.kw = kw;
		this.effect = effect;
	}
	
	public Data(int uid, String name, String desc, String kw, String effect, boolean effectBool) 
	{
		this.uid = uid;
		this.name = name;
		this.desc = desc;
		this.kw = kw;
		this.effect = effect.toLowerCase();
		this.effectBool = effectBool;
	}
	
	/* Checks whether a condition s is contained in effect */
	public boolean check(String s)
	{
		if (effect.contains(s.toLowerCase()))
			return true;
		else
			return false;
	}
	
	public boolean effectBool()
	{
		return this.effectBool;
	}
	
	public String effect()
	{
		return this.effect;
	}
}
