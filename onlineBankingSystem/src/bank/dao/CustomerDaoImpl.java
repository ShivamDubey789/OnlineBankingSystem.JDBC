package bank.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bank.connection.DatabaseConnection;
import bank.exception.CustomerException;
import bank.model.Customer;
import bank.model.Transaction;

public class CustomerDaoImpl implements CustomerDao{

	@Override
	public Customer LoginCustomer(String customerUsername, String customerPassword,
			int customerAccountNumber) throws CustomerException {
			
		Customer customer = null;
		try (Connection conn = DatabaseConnection.provideConnection()){
			
			PreparedStatement ps = conn.prepareStatement("select * from customerinformation i inner join account a on i.cid = a.cid where customerName = ? and customerPassword = ? and customerAccountNumber = ? ");
			
			ps.setString(1, customerUsername);
			ps.setString(2, customerPassword);
			ps.setInt(3, customerAccountNumber);
			
			ResultSet rs = ps.executeQuery();
			
			if(rs.next()) {
				int ac = rs.getInt("customerAccountNumber");
				String n = rs.getString("customerName");
				int b = rs.getInt("customerBalance");
				String e = rs.getString("customerMail");
				String p = rs.getString("customerPassword");
				String m = rs.getString("customerMobile");
				String ad = rs.getString("customerAddress");
				
				customer = new Customer(ac,n,b,e,p,m,ad);
			}
			
			else {
				throw new CustomerException("Incorrect Email and Password!!!");
			}
			
		}catch(SQLException e) {
			
			throw new CustomerException(e.getMessage());
		}
		return customer;
	}

	@Override
	public int viewBalance(int customerAccountNumber) throws CustomerException {
		
		int b= -1;
		
		try (Connection conn = DatabaseConnection.provideConnection()){
			
			PreparedStatement ps = conn.prepareStatement("select customerBalance from account where customerAccountNumber = ?");
			ps.setInt(1,customerAccountNumber);
			
			ResultSet rs = ps.executeQuery();
			
			if(rs.next()) {
				b = rs.getInt("customerBalance");
			}
		}catch(SQLException e) {
			
			throw new CustomerException(e.getMessage());
		}
			
		
		return b;
	}

	@Override
	public int Deposit(int customerAccountNumber, int amount) throws CustomerException {
		
		int b = -1;
		try (Connection conn = DatabaseConnection.provideConnection()){
			PreparedStatement ps = conn.prepareStatement("update account set customerBalance = customerBalance+ ? where customerAccountNumber = ?");
			
			ps.setInt(1, amount);
			ps.setInt(2,customerAccountNumber);
			
			int rs = ps.executeUpdate();
			
			
		}catch(SQLException e) {
			throw new CustomerException(e.getMessage());
		}
		return b;
	}

	@Override
	public int Withdraw(int customerAccountNumber, int amount) throws CustomerException {
		int vb = viewBalance(customerAccountNumber);
		
		if(vb> amount) {
			
			try (Connection conn = DatabaseConnection.provideConnection()){
				PreparedStatement ps = conn.prepareStatement("update account set customerBalance = customerBalance - ? where customerAccountNumber = ?");
				
				ps.setInt(1, amount);
				ps.setInt(2,customerAccountNumber);
				
				int rs = ps.executeUpdate();
				
			}catch(SQLException e) {
				
				throw new CustomerException(e.getMessage());
			}
		}
		
		else {
			throw new CustomerException("Insufficient Balance");
		}
		
		
	
		
		return vb + amount;
		
	}

	@Override
	public int Transfer(int customerAccountNumber, int amount, int customerAccountNumber2) throws CustomerException {
		int vb = viewBalance(customerAccountNumber);
		
		if(vb> amount && checkAccount(customerAccountNumber2)) {
			
			int wit = Withdraw(customerAccountNumber,amount);
			int dep = Deposit(customerAccountNumber2,amount);
			
		}
		else {
			throw new CustomerException("Insufficient Balance");
		}
		return 0;
	}

	private boolean checkAccount(int customerAccountNumber2) {
		
		try (Connection conn = DatabaseConnection.provideConnection()){
			PreparedStatement ps = conn.prepareStatement("select * from account where customerAccountNumber = ?");
			
			ps.setInt(1, customerAccountNumber2);
			
			ResultSet rs = ps.executeQuery();
			
			if(rs.next()) {
				return true;
			}
			
		}catch(SQLException e) {
			System.out.println(e.getMessage());
		}
			
		
		return false;
	}

    @Override
    public void logTransaction(Transaction transaction) throws CustomerException {
        String query = "INSERT INTO transactions (account_number, transaction_type, amount, target_account) VALUES (?, ?, ?, ?)";

        try (Connection conn = DatabaseConnection.provideConnection()
             ) {
        	PreparedStatement preparedStatement = conn.prepareStatement(query);
            preparedStatement.setInt(1, transaction.getAccountNumber());
            preparedStatement.setString(2, transaction.getTransactionType());
            preparedStatement.setDouble(3, transaction.getAmount());
            preparedStatement.setObject(4, transaction.getTargetAccount(), java.sql.Types.INTEGER); // Use Types.INTEGER for nullable
            
            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected == 0) {
                throw new CustomerException("Failed to log transaction.");
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    @Override
    public List<Transaction> getTransactionHistory(int accountNumber) throws CustomerException {
        // Initialize the list to hold transaction records
        List<Transaction> transactions = new ArrayList<>();
        
        // Correct SQL query to match your database column names
        String sql = "SELECT * FROM transactions WHERE account_number = ?"; // Ensure the column name is account_number
        try (Connection conn = DatabaseConnection.provideConnection(); // Use your existing DatabaseConnection class
             PreparedStatement preparedStatement = conn.prepareStatement(sql)) {
             
            // Set the account number parameter
            preparedStatement.setInt(1, accountNumber);
            
            // Execute the query and obtain results
            ResultSet resultSet = preparedStatement.executeQuery();
            
            // Process the results
            while (resultSet.next()) {
                Transaction transaction = new Transaction();
                transaction.setTransactionId(resultSet.getInt("transaction_Id")); // Use correct column name
                transaction.setAccountNumber(resultSet.getInt("account_number")); // Ensure to use account_number
                transaction.setTransactionType(resultSet.getString("transaction_type")); // Ensure to use transaction_type
                transaction.setAmount(resultSet.getDouble("amount")); // Column name for amount
                transaction.setTargetAccount(resultSet.getObject("target_account", Integer.class)); // Column for target_account
                transaction.setTransactionDate(resultSet.getTimestamp("transaction_date").toLocalDateTime()); // Column for transaction_date
                
                // Add the transaction to the list
                transactions.add(transaction);
            }
        } catch (SQLException e) {
            // Wrap and throw a custom exception
           System.out.println(e); 
           }
        
        // Return the list of transactions
        return transactions;
    }

    
}


