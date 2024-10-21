package bank.model;

import java.time.LocalDateTime;

public class Transaction {

	private int transactionId; 
    private int accountNumber;    
    private String transactionType;  
    private double amount;       
    private Integer targetAccount;         
    private LocalDateTime transactionDate;
	public int getTransactionId() {
		return transactionId;
	}
	public void setTransactionId(int transactionId) {
		this.transactionId = transactionId;
	}
	public int getAccountNumber() {
		return accountNumber;
	}
	public void setAccountNumber(int accountNumber) {
		this.accountNumber = accountNumber;
	}
	public String getTransactionType() {
		return transactionType;
	}
	public void setTransactionType(String transactionType) {
		this.transactionType = transactionType;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public Integer getTargetAccount() {
		return targetAccount;
	}
	public void setTargetAccount(Integer targetAccount) {
		this.targetAccount = targetAccount;
	}
	public LocalDateTime getTransactionDate() {
		return transactionDate;
	}
	public void setTransactionDate(LocalDateTime transactionDate) {
		this.transactionDate = transactionDate;
	}
	public Transaction(int transactionId, int accountNumber, String transactionType, double amount,
			Integer targetAccount, LocalDateTime transactionDate) {
		super();
		this.transactionId = transactionId;
		this.accountNumber = accountNumber;
		this.transactionType = transactionType;
		this.amount = amount;
		this.targetAccount = targetAccount;
		this.transactionDate = transactionDate;
	}
	public Transaction() {
		super();
	}
	@Override
	public String toString() {
		return "Transaction [transactionId=" + transactionId + ", accountNumber=" + accountNumber + ", transactionType="
				+ transactionType + ", amount=" + amount + ", targetAccount=" + targetAccount + ", transactionDate="
				+ transactionDate + "]";
	}
	
 
    
    
}
