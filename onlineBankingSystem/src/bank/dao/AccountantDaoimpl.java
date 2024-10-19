
package bank.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import bank.connection.DatabaseConnection;
import bank.exception.AccountantException;
import bank.exception.CustomerException;
import bank.model.Accountant;
import bank.model.Customer;

public class AccountantDaoimpl implements AccountantDao {

	@Override
	public Accountant LoginAccountant(String accountantUsername, String accountantPassword) throws AccountantException {
		Accountant acc = null;
		
		try(Connection conn = DatabaseConnection.provideConnection()){
			PreparedStatement ps = conn.prepareStatement("select * from accountant where accountantUsername = ? and accountantPassword = ?");
			
			ps.setString(1,accountantUsername);
			ps.setString(2, accountantPassword);
			
			ResultSet rs = ps.executeQuery();
			
			if(rs.next()) {
				
				String n = rs.getString("accountantUsername");
				String e = rs.getString("accountantEmail");
				String p = rs.getString("accountantPassword");
				
				acc = new Accountant(n,e,p);
			}
			
		}catch(Exception e){
			throw new AccountantException("Invalid Username and Password!!");
		}
		return acc;
	}

	@Override
	public int addCustomer(String customerName , String customerMail,String customerPassword,
			String customerMobile,String customerAddress) throws CustomerException {
		
		int cid = -1;
		
		try(Connection conn = DatabaseConnection.provideConnection()){
			

			PreparedStatement ps = conn.prepareStatement("insert into customerinformation (customerName ,customerMail,customerPassword,customerMobile,customerAddress) values(?,?,?,?,?) "); 
			
			ps.setString(1, customerName);
			ps.setString(2, customerMail);
			ps.setString(3, customerPassword);
			ps.setString(4, customerMobile);
			ps.setString(5, customerAddress);
			
			int x = ps.executeUpdate();
			
			if(x>0) {
				PreparedStatement ps2 = conn.prepareStatement("select cid from customerinformation where customerMail = ? and customerMobile = ?");
			
				ps2.setString(1, customerMail);
				ps2.setString(2, customerMobile);
				
				ResultSet rs = ps2.executeQuery();
				
				if(rs.next()) {
					cid = rs.getInt("cid");
				}
				else {
					System.out.println("Inserted data is incorrect");
				}
			
			}
		
			
		}catch(SQLException e ) {
			System.out.println("SQL QUERY RELATED ERROR");
		}
		return cid;
	}

	@Override
	public String addAccount(int customerBalance, int cid) throws CustomerException {
		
		String message = null;
		try(Connection conn = DatabaseConnection.provideConnection()){
			PreparedStatement ps = conn.prepareStatement("insert into account(customerBalance,cid) values(?,?)");
			ps.setInt(1,customerBalance);
			ps.setInt(2, cid);
			
			int x = ps.executeUpdate();
			
			if(x>0) {
				System.out.println("Account Added Successfully");
			}
			else {
				System.out.println("Adding account Failed");
			}
		
		}catch(SQLException e ) {
			System.out.println("SQL Related exception");
		}
		return message;
	}

	@Override
	public String updateCustomer(int customerAccountNumber, String customerAddress) throws CustomerException {
		
		String message = null;

		try(Connection conn = DatabaseConnection.provideConnection()){
			
			PreparedStatement ps = conn.prepareStatement("update customerinformation i inner join account a on i.cid = a.cid and a.customerAccountNumber = ? set i.customerAddress =?");
			ps.setInt(1, customerAccountNumber);
			ps.setString(2, customerAddress);
			
			int x = ps.executeUpdate();
			
			if(x>0) {
				System.out.println("address updated successfully");
				
			}
			else {
				System.out.println("Customer UPDATION IS NOT SUCCESFULL");
			}
		}catch(Exception e) {
			e.printStackTrace();
			message = e.getMessage();
		}
		
		return message;
	}

	@Override
	public String deleteAccount(int customerAccountNumber) throws CustomerException {
		
		String message = null;
		
		try(Connection conn = DatabaseConnection.provideConnection()){
			PreparedStatement ps  = conn.prepareStatement("delete i from customerinformation i inner join account a on i.cid = a.cid where a.customerAccountNumber = ?");
			ps.setInt(1, customerAccountNumber);
			
			int x = ps.executeUpdate();
			
			if(x>0) {
				System.out.println("Account deleted!!");
			}
			else {
				System.out.println("Deletion failed wrong account number");
			}
		
		}catch(SQLException e ) {
			
			e.printStackTrace();
			message = e.getMessage();
		}
		return message;
	}
	
	@Override
	public Customer viewCustomer(String customerAccountNumber) throws CustomerException {
		Customer cu = null;
		
		
		try(Connection conn = DatabaseConnection.provideConnection()){
			PreparedStatement ps = conn.prepareStatement("select * from customerinformation i inner join account a on a.cid = i.cid where customerAccountNumber = ?");
			ps.setString(1, customerAccountNumber);
			
			ResultSet rs = ps.executeQuery();
			
			if(rs.next()) {
				
				int a = rs.getInt("customerAccountNumber");
				String n = rs.getString("customerName");
				int b = rs.getInt("customerBalance");
				String e = rs.getString("customerMail");
				String p = rs.getString("customerPassword");
				String m = rs.getString("customerMobile");
				String ad = rs.getString("customerAddress");
				
				cu = new Customer(a,n,b,e,p,m,ad);
			}
			
			else {
				throw new CustomerException("Invalid Account Number!!");
			}
		}catch(SQLException e ) {
			System.out.println(e.getMessage());
		}
		
		return cu;
	}


	@Override
	public Customer viewAllCustomer() throws CustomerException {
		
		Customer cu = null;
	
		try(Connection conn = DatabaseConnection.provideConnection()){
			PreparedStatement ps = conn.prepareStatement("select * from customerinformation i inner join account a on a.cid = i.cid");
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				int a = rs.getInt("customerAccountNumber");
				String n = rs.getString("customerName");
				int b = rs.getInt("customerBalance");
				String e = rs.getString("customerMail");
				String p = rs.getString("customerPassword");
				String m = rs.getString("customerMobile");
				String ad = rs.getString("customerAddress");
				
				System.err.println("!!!all Customer Details!!!");
				System.out.println(" Account Number: " +a);
				System.out.println(" Name: " +n);
				System.out.println(" Balance: " +b);
				System.out.println(" Email: " +e);
				System.out.println(" Password:  " +p);
				System.out.println(" Mobile: " +m);
				System.out.println(" Address: " +ad);
				
				cu = new Customer(a,n,b,e,p,m,ad);
			}
			
		}catch(SQLException e) {
			throw new CustomerException("Invalid account Number");
		}
		
		
		return cu;
	}

	
	
}

