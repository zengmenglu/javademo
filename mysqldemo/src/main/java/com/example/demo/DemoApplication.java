package com.example.demo;

import java.sql.*;

//@SpringBootApplication
public class DemoApplication {

	static final String DB_DRIVER="com.mysql.cj.jdbc.Driver";
	static final String DB_URL="jdbc:mysql://localhost:3306/testdb?useSSL=false&serverTimezone=UTC";
	static final String USER="root";
	static final String PWD="";
	static final String SQL="select * from testtable";
	private static void showmsg(String s) {
		System.out.println(s);
	}

	// 查数据
	public static void Retrieve(String sql) {
		Connection conn=null;
		Statement stmt=null;
		try {
			conn=DriverManager.getConnection(DB_URL, USER, PWD);
			stmt=conn.createStatement();
			ResultSet rs=stmt.executeQuery(sql);
			while(rs.next())
			{
				String name=rs.getString("Name");
				int age=rs.getInt("Age");

				String value=String.format("Name:%s--Age:%d", name,age);
				showmsg(value);
			}
			showmsg("");
			rs.close();
			stmt.close();
		} catch (Exception e) {
			showmsg(e.getMessage());
		}finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	// 写数据
	public static void Create(String sql, String name, int age) {
		Connection conn=null;
		PreparedStatement stmt=null;
		try {
			conn=DriverManager.getConnection(DB_URL, USER, PWD);
			stmt=conn.prepareStatement(sql);

			stmt.setString(1,name);
			stmt.setInt(2,age);
			stmt.executeUpdate();
			showmsg("insert success");

			stmt.close();
		} catch (Exception e) {
			showmsg(e.getMessage());
		}finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	// 修改数据
	public static void Update(String sql, String name, int age) {
		Connection conn=null;
		PreparedStatement stmt=null;
		try {
			conn=DriverManager.getConnection(DB_URL, USER, PWD);
			stmt=conn.prepareStatement(sql);

			stmt.setInt(1,age);
			stmt.setString(2,name);
			stmt.executeUpdate();
			showmsg("alt success");

			stmt.close();
		} catch (Exception e) {
			showmsg(e.getMessage());
		}finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	// 删除数据
	public static void Delete(String sql, String name) {
		Connection conn=null;
		PreparedStatement stmt=null;
		try {
			conn=DriverManager.getConnection(DB_URL, USER, PWD);
			stmt=conn.prepareStatement(sql);
			stmt.setString(1,name);
			stmt.executeUpdate();
			showmsg("delete success");

			stmt.close();
		} catch (Exception e) {
			showmsg(e.getMessage());
		}finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public static void main(String[] args) {
		Connection conn=null;
		Statement stmt=null;
		try {
			Class.forName(DB_DRIVER);
			showmsg("load mysql driver success");
		} catch (Exception e) {
			showmsg(e.getMessage());
			return;
		}
		Create("insert into testtable (Name, Age) values (?,?)","testname",3);
		Retrieve(SQL);

		Update("update testtable set age=? where name=?","testname",5);
		Retrieve(SQL);

		Delete("delete from testtable where name=?","testname");
		Retrieve(SQL);
	}


}