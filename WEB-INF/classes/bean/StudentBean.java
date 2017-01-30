package bean;

public class StudentBean 
{
	private String sid;
	private String name;
	private int credit_taken;
	private int credit_graduate;
	
	public StudentBean(String sid, String name, int credit_taken, int credit_graduate)
	{
		this.name = name;
		this.sid = sid;
		this.credit_taken= credit_taken;
		this.credit_graduate= credit_graduate;
	}
	
	public String getSID()
	{
		return this.sid;
	}
	public String getNAME()
	{
		return this.name;
	}
	public int get_CREDIT_TAKEN()
	{
		return this.credit_taken;
	}
	public int get_CREDIT_GRADUATE()
	{
		return this.credit_graduate;
	}
	
	public void setSID(String sid)
	{
		this.sid = sid;
	}
	public void setNAME(String name)
	{
		this.name = name;
	}
	public void set_CREDIT_TAKEN(int credit_taken)
	{
		this.credit_taken = credit_taken;
	}
	public void set_CREDIT_GRADUATE(int credit_graduate)
	{
		this.credit_graduate = credit_graduate;
	}
	
}
