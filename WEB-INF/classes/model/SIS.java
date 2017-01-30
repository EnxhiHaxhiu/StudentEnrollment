package model;

import java.util.Map;

import dao.EnrollmentDAO;
import dao.StudentDAO;
import bean.EnrollmentBean;
import bean.StudentBean;

public class SIS 
{
	public StudentDAO student;
	public EnrollmentDAO enrollment;
	
	public SIS()
	{
		try{
			student = new StudentDAO();
			enrollment = new EnrollmentDAO();
		}
		catch (ClassNotFoundException e)
		{
			e.printStackTrace();
		}
	}
	
	public Map<String, StudentBean> retriveStudent(String namePrefix, int credit_taken) throws Exception
	{
		return student.retrieve(namePrefix, credit_taken);
	}
	
	public Map<String, EnrollmentBean> retriveEnrollment(String cid) throws Exception
	{
		return enrollment.retrieve(cid);
	}
}
