package bank.Usecase;


import java.util.Scanner;



import bank.dao.AccountantDaoimpl;
import bank.exception.AccountantException;
import bank.exception.CustomerException;
import bank.model.Accountant;
import bank.model.Customer;
import bank.dao.AccountantDao;
import bank.dao.CustomerDao;
import bank.dao.CustomerDaoImpl;

public class App {

	public static void main(String[] args) {
		
		
		Scanner sc = new Scanner(System.in);
		
		boolean f = true;
		System.out.println("Welcome to Kanera Bank\n");
		
		while(f) {
			
			
			
			System.out.println("!! Choose an Option !!");
			System.out.println("1. Accountant Login\n"
					+ "2. Customer Login");
			System.out.println("Enter Value : ");
			
			
			
			int choice = sc.nextInt();
			
			switch(choice) {
			
				case 1 :
					System.out.println("!! Accountant Login!! /n");
					System.out.println("Enter Username: ");
					String username = sc.next();
					System.out.println("Enter Password: ");
					String pass = sc.next();
			
					AccountantDao ad = new AccountantDaoimpl();
			
					
			
					try {
					
						Accountant a = ad.LoginAccountant(username, pass);
					
						if(a==null) {
						
							System.out.println("wrong username and password");
							break;
						}
					
						System.out.println("Login SuccessFully");
						System.out.println("Welcome " + a.getAccountantUsername()+" Accountant of Kanera Bank");
				
						boolean y = true;
						
						while(y) {
							
							System.out.println("\n"
									+"   !! Choose an Option !!  "
									+"1. add new Customer Account\n"
									+"2. Update Customer Address\n"
									+"3. Delete Account \n"
									+"4. view particular account details\n"
									+"5. view all Customer Details\n"
									+"6. Account Logout\n");
									
							
							int x = sc.nextInt();
							
							if(x==1) {
								System.out.println("!! New Account !!");
								System.out.println("Enter CustomerName: ");
								String a1 = sc.next();
								
								System.out.println("Enter Account Opening Balance");
								int a2 = sc.nextInt();
								System.out.println("Enter CustomerMail: ");
								String a3 = sc.next();
								System.out.println("Enter CustomerPassword: ");
								String a4 = sc.next();
								System.out.println("Enter CustomerMobile: ");
								String a5 = sc.next();
								System.out.println("Enter CustomerAddress: ");
								String a6 = sc.next();
								
								int s1 = -1;
								
								try {
									s1 = ad.addCustomer(a1,a3,a4,a5,a6);
									
									
									try {
										
										ad.addAccount(a2, s1);
										
									}catch(CustomerException e){
										
										e.printStackTrace();
									}
									
									
								}catch(CustomerException e ) {
									System.out.println(e.getMessage());
								}
								
								System.out.println("\n");
							}
							
							if(x==2) {
								System.out.println("!! update customer adresss !!");
								System.out.println("Enter Customer Account Number: ");
								int u = sc.nextInt();
								System.out.println("Enter new address: ");
								String u2 = sc.next();
								
								try {
										ad.updateCustomer(u, u2);
								} catch (CustomerException e) {
									
									e.printStackTrace();
								}
								
								
							}
						
							if(x==3) {
								System.out.println("!! Remove Account !!");
								System.out.println("Enter Account Number: ");
								int ac = sc.nextInt();
								String s = null;
								try {
									s = ad.deleteAccount(ac);
								}catch(CustomerException e) {
									
									e.printStackTrace();
									
								}
								
						
							}
							
							if(x == 4) {
								
								System.out.println("!! Customer Details !!");
								System.out.println("Enter Customer account number :");
								String ac = sc.next();
								
								try {
									Customer cus = ad.viewCustomer(ac);
								
									if(cus != null) {
										System.out.println(" Customer Account Number : "+cus.getCustomerAccountNumber());
										System.out.println(" Name: "+cus.getCustomerName());
										System.out.println(" Balance: "+cus.getCustomerBalance());
										System.out.println(" Email: "+cus.getCustomerMail());
										System.out.println(" Password: " +cus.getCustomerPassword());
										System.out.println(" Mobile Number: "+cus.getCustomerMobile());
										System.out.println(" Address: "+ cus.getCustomerAddress());
									}
									else {
										System.out.println("Account Does Not Exist!!!");
									}
								
								}catch(CustomerException e){
									e.printStackTrace();
									
								}
							}
							
							
							
							
							
							
							if(x==5) {
								
								try {
									System.out.println("!! All Customer List !!");
									System.out.println("/n");
									Customer cus = ad.viewAllCustomer();
								}catch(CustomerException e ) {
									
									e.printStackTrace();
								}
							}
							
							if(x==6) {
								System.out.println("Account logut Successfully");
								y = false;
							}
							
						}
					} catch (AccountantException e) {
						System.out.println(e.getMessage());
				
					}
					
					break;
					
				case 2:
					System.out.println("!! Customer Login !! ");
					System.out.println("");
					System.out.println("Enter User Name: ");
					String customerUsername = sc.next();
					System.out.println("Enter Password: ");
					String customerPassword = sc.next();
					System.out.println("Enter Account Number: ");
					int accountNumber = sc.nextInt();
					System.out.println("");
					
					CustomerDao cd = new CustomerDaoImpl();
					
					try {
						
						Customer cus = cd.LoginCustomer(customerUsername, customerPassword, accountNumber);
						System.out.println("Welcome "+ cus.getCustomerName());
						System.out.println("");
					
						boolean m = true;
						
						while(m) {
							
							System.out.println("!! Choose an Option !!\n"
									+"1. View Balance \n"
									+"2. Deposite Money \n"
									+"3. Withdraw Money \n"
									+"4. Transfer Money \n"
									+"5. Logout \n");
							
						
							int x = sc.nextInt();
							
							if(x == 1 ) {
								System.out.println("!! Current Balance !!");
								System.out.println(cd.viewBalance(accountNumber));
								
							}
							
							if(x == 2 ) {
								System.out.println("!! Deposit !!");
								System.out.println("Enter Deposit Amount: ");
								int am = sc.nextInt();
								
								cd.Deposit(accountNumber, am);
								
								System.out.println("Your Balance after Deposit");
								System.out.println(cd.viewBalance(accountNumber));
								System.out.println("");
							}
						
							if(x == 3) {
								
								System.out.println("!! Withdraw Money !!");
								System.out.println("Enter Withdrawal Amount: ");
								int am = sc.nextInt();
								
								try {
									cd.Withdraw(accountNumber, am);
									System.out.println("Your Balance after Withdrawal");
									System.out.println(cd.viewBalance(accountNumber));
									System.out.println("");
								} catch (CustomerException e) {
									// TODO Auto-generated catch block
									System.out.println(e.getMessage());
								}
							}
							
							if(x == 4) {
								System.out.println("!! Amount Transfer !!");
								System.out.println("Enter Transfer Amount: ");
								int t = sc.nextInt();
								System.out.println("Enter debit Account Number:  ");
								int ac = sc.nextInt();
								
								try {
									cd.Transfer(accountNumber, t, ac);
									System.out.println("Amount Transfered Successfully");
									System.out.println("");
								}catch(Exception e) {
									
									System.out.println(e.getMessage());
								}
							}
							
							if(x == 5) {
								
								System.out.println("Customer Logout Successfully");
								System.out.println("thank you for using our services");
								m = false;
							}
							
							break;
							
						}
					}catch(CustomerException e) {
						System.out.println(e.getMessage());
					}
			
			}
		}
	}
}
