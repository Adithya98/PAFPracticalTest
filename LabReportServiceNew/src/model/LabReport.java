package model;

import java.sql.Statement;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.SimpleDateFormat;



import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class LabReport {

	private Connection connect() {
		Connection con = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");

			// Provide the correct details: DBServer/DBName, username, password
			con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/labreport?useTimezone=true&serverTimezone=UTC","root", "");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return con;
	}

	public String insertLabReport( String num, String name, String det) {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for inserting.";
			}
			// create a prepared statement
			String query = " insert into labreports(`lReportID`,`lReportNum`,`lReportName`,`lReportDet`)"
					+ " values (?, ?, ?, ?)";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			// binding values
			preparedStmt.setInt(1, 0);
			preparedStmt.setString(2, num);
			preparedStmt.setString(3, name);
			preparedStmt.setString(4, det);
			// execute the statement
			preparedStmt.execute();
			con.close();
			//output = "Inserted successfully";
			String newHospitals = readLabReports();
			 output = "{\"status\":\"success\", \"data\": \"" +newHospitals + "\"}";
			
		} catch (Exception e) {
			//output = "Error while inserting the user.";
			output = "{\"status\":\"error\", \"data\":\"Error while inserting the Lab Report.\"}";
			System.err.println(e.getMessage());
		}
		return output;
	}

	public String readLabReports() {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for reading.";
			}
			// Prepare the html table to be displayed
			output = "<table border=\'1\'><tr><th>Lab Report Number</th><th>Lab Report Name</th><th>Lab Report Details</th><th>Update</th><th>Remove</th></tr>";
			String query = "select * from labreports";
			Statement stmt = (Statement) con.createStatement();
			ResultSet rs = ((java.sql.Statement) stmt).executeQuery(query);
			// iterate through the rows in the result set
			while (rs.next()) {
				String lReportID = Integer.toString(rs.getInt("lReportID"));
				String lReportNum = rs.getString("lReportNum");
				String lReportName = rs.getString("lReportName");
				
				String lReportDet = rs.getString("lReportDet");
				// Add into the html table
				output += "<tr><td><input id='hidHopsitalIDUpdate' name='hidHopsitalIDUpdate' type='hidden' value='" + lReportID + "'>" + lReportNum + "</td>";
				
				/*output += "<tr><td><input id=\"hidUserIDUpdate\"name=\"hidUserIDUpdate\"type=\"hidden\" value=\""
						+ userID + "\">" + userName + "</td>";*/
				
//				output += "<tr><td>" + Hospital_ID + "</td>";
//				output += "<td>" + H_Name + "</td>";
//				output += "<tr><td>" + lReportNum + "</td>";
				output += "<td>" + lReportName + "</td>";
				
				output += "<td>" + lReportDet + "</td>";
				// buttons
				output += "<td><input name='btnUpdate' type='button' value='Update' class='btnUpdate btn btn-secondary'></td><td><input name='btnRemove' type='button' value='Remove' class='btnRemove btn btn-danger' data-hospitalid='"
						 + lReportID + "'>" + "</td></tr>";
				
				/*output += "<td><input name=\"btnUpdate\"type=\"button\" value=\"Update\"class=\" btnUpdate btn btn-secondary\"></td><td><form method=\"post\" action=\"User.jsp\"><input name=\"btnRemove\" type=\"submit\"value=\"Remove\" class=\"btn btn-danger\"><input name=\"hidUserIDDelete\" type=\"hidden\"value=\""
						+ userID + "\">" + "</form></td></tr>";*/
				
				/*
				 * output +=
				 * "<td><input name=\"btnUpdate\" type=\"button\"value=\"Update\" class=\"btn btn-secondary\"></td>"
				 * + "<td><form method=\"post\" action=\"items.jsp\">" +
				 * "<input name=\"btnRemove\" type=\"submit\" value=\"Remove\"class=\"btn btn-danger\">"
				 * + "<input name=\"userID\" type=\"hidden\" value=\"" + userID + "\">" +
				 * "</form></td></tr>";
				 */
			}
			con.close();
			// Complete the html table
			output += "</table>";
		} catch (Exception e) {
			output = "Error while reading the users.";
			System.err.println(e.getMessage());
		}
		return output;
	}

	public String updateLabReport(String ID, String num, String name, String det) {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for updating.";
			}
			// create a prepared statement
			String query = "UPDATE labreports SET lReportNum=?,lReportName=?,lReportDet=?WHERE lReportID=?";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			// binding values
			preparedStmt.setString(1, num);
			preparedStmt.setString(2, name);
			
			preparedStmt.setString(3, det);
			preparedStmt.setInt(4, Integer.parseInt(ID));
			// execute the statement
			preparedStmt.execute();
			con.close();
			//output = "Updated successfully";
			
			String newUsers = readLabReports();
			 output = "{\"status\":\"success\", \"data\": \"" +newUsers + "\"}";
			
		} catch (Exception e) {
			//output = "Error while updating the user.";
			output = "{\"status\":\"error\", \"data\":\"Error while updating the user.\"}";
			System.err.println(e.getMessage());
		}
		return output;
	}

	public String deleteLabReport(String lReportID) {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for deleting.";
			}
			// create a prepared statement
			String query = "delete from labreports where lReportID=?";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			// binding values
			preparedStmt.setInt(1, Integer.parseInt(lReportID));
			// execute the statement
			preparedStmt.execute();
			con.close();
			//output = "Deleted successfully";
			String newUsers = readLabReports();
			 output = "{\"status\":\"success\", \"data\": \"" +newUsers + "\"}";
			
		} catch (Exception e) {
			//output = "Error while deleting the user.";
			output = "{\"status\":\"error\", \"data\":\"Error while deleting the Hospital.\"}";
			System.err.println(e.getMessage());
		}
		return output;
	}



	
}
