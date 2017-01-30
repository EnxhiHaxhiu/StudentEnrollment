package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import bean.EnrollmentBean;

public class EnrollmentDAO 
{
	public DataSource ds;
	
	public EnrollmentDAO() throws ClassNotFoundException
	{
		try
		{
			ds = (DataSource)(new InitialContext()).lookup("java:/com/env/jdbc/EECS");
		}
		catch(NamingException e)
		{
			e.printStackTrace();
		}
	}
	
	public Map<String, EnrollmentBean> retrieve(String cid) throws SQLException
	{
		String query = "select * from ENROLLMENT where cid like '%" + cid;
		Map<String, EnrollmentBean> rv = new HashMap<String, EnrollmentBean>();
		Connection con = this.ds.getConnection();
		PreparedStatement p = con.prepareStatement(query);
		ResultSet r = p.executeQuery();
		
		while (r.next())
		{
			String name = r.getString("GIVENNAME") + ", " + r.getString("SURNAME");
			int credit = r.getInt("CREDIT");
			rv.put(name, new EnrollmentBean(cid, name, credit));
		}
		r.close();
		p.close();
		con.close();
		return rv;


	}
}
