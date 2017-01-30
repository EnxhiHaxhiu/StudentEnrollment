package bean;

public class EnrollmentBean 
{
	private String cid;
	private String student;
	private int credit;
	
	public EnrollmentBean(String cid, String student, int credit)
	{
		this.cid = cid;
		this.credit = credit;
		this.student = student;
	}
	public String getCID()
	{
		return this.cid;
	}

	public String getStudent()
	{
		return this.student;
	}
	
	public int getCredit()
	{
		return this.credit;
	}
	
	public void setCID(String cid)
	{
		this.cid = cid;
	}
	
	public void setStudent(String student)
	{
		this.student = student;
	}
	
	public void setCredit(int credit)
	{
		this.credit = credit;
	}
}
