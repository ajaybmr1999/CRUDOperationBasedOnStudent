package com.techpalle;

import java.sql.*;
import java.util.Scanner;

public class CRUDOperation {
	private static final String url ="jdbc:mysql://localhost:3306/jdbc1";
	private static final String un ="root";
	private static final String pwd ="ajaybr";
	
	private static Connection con = null;
	private static Statement s = null;
	private static PreparedStatement ps = null;
	private static ResultSet rs = null;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection(url,un,pwd);
			boolean  b = true;
			while(b) {
				System.out.println("Press 1 to Create. Press 2 to Insert. Press 3 to Update. Press 4 to Delete. Press 5 to Retrieve. Press 6 to Retrieve row. Press 7 to Exit ");
				int a = sc.nextInt();
				switch(a){
				case 1:{
					s = con.createStatement();
					s.executeUpdate("create table emp(eno int primary key, ename varchar(40),esal int)");
					System.out.println("Table Created Successfully");
					break;
				}
				case 2:{
					String query = "insert into emp(eno,ename,esal) values(?,?,?)";
					ps = con.prepareStatement(query);
					System.out.println("Enter the eno: ");
					int e = sc.nextInt();
					System.out.println("Enter the name: ");
					String n = sc.next();
					System.out.println("Enter the Salary: ");
					int s = sc.nextInt();
					 
					ps.setInt(1, e);
					ps.setString(2, n);
					ps.setInt(3, s);
					ps.executeUpdate();
					System.out.println("Inserted the values Successfully");
					
					break;
				}
				case 3:{
					String query = "update emp set ename=?, esal = ? where eno =?";
					ps = con.prepareStatement(query);
					System.out.println("Enter the name: ");
					String n = sc.next();
					System.out.println("Enter the salary: ");
					int s = sc.nextInt();
					System.out.println("Enter the eno: ");
					int e = sc.nextInt();
					
					ps.setString(1, n);
					ps.setInt(2, s);
					ps.setInt(3, e);
					ps.executeUpdate();
					System.out.println("Updated Succesfully");
					break;
				}
				case 4:{
					String query = "delete from emp where eno =?";
					ps = con.prepareStatement(query);
					System.out.println("Enter the deleted eno: ");
					int e = sc.nextInt();
					
					ps.setInt(1, e);
					ps.executeUpdate();
					System.out.println("deleted Successfully");
					break;	
				}
				case 5:{
					Statement s = con.createStatement();
					String qry = "select * from emp";
					rs = s.executeQuery(qry);
					
					while(rs.next()) {
						System.out.println(rs.getInt(1)+ " " +rs.getString(2)+ " " +rs.getInt(3));
					}
					break;
				}
				case 6:{
					PreparedStatement ps = con.prepareStatement("select * from emp where eno = ?");
					System.out.println("Enter the eno:");
					int e = sc.nextInt();
					ps.setInt(1, e);
					rs = ps.executeQuery();
					rs.next();
					System.out.println(rs.getInt(1)+ " " +rs.getString(2)+ " " +rs.getString(3));
					System.out.println("Deatails fetched Successfully");
					break;
					
				}
				case 7:{
					System.out.println("<-----------THANK YOU------------>");
					b = false;
					break;
					
				}
				}
			}
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			try {
				if(rs != null)
					rs.close();
				if(s != null)
					s.close();
				if(ps != null)
					ps.close();
				if(con != null)
					con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
	}
}

