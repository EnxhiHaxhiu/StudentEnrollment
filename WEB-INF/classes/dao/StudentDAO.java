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

import bean.StudentBean;

public class StudentDAO {
	DataSource ds;
	
	public StudentDAO() throws ClassNotFoundException
	{
		try {
			 ds = (DataSource) (new InitialContext()).lookup("java:/com/env/jdbc/EECS");
		}
		catch(NamingException e)
		{
			e.printStackTrace();
		}
	}
	
	public Map<String, StudentBean> retrieve(String namePrefix, int credit_taken) throws SQLException {
		String query = "select * from students where surname like '%" + namePrefix + "%' and credit_taken >= "
				+ credit_taken;
	
		Map<String, StudentBean> rv = new HashMap<String, StudentBean>();
		Connection con = this.ds.getConnection();
		PreparedStatement p = con.prepareStatement(query);
		ResultSet r = p.executeQuery();
		
		while (r.next()) {

			String name = r.getString("GIVENNAME") + ", " + r.getString("SURNAME");
			String sid = r.getString("sid");
			int credit = r.getInt("credit_taken");
			int credit_grad = r.getInt("credit_graduate");
			rv.put(name, new StudentBean(sid, name, credit, credit_grad));

			
		}

		r.close();
		p.close();
		con.close();
		return rv;
	}
}


