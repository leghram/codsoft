package src.repository;

public class Account {
    
    private String password;
    private Double totalAmount = 0.0;
    private static int idAccount = 1001;
    private int account;

    public Account(String password){
        this.account = idAccount;
        this.password = password;
        idAccount = idAccount + 1;
    }

    public int getAccount() {
        return account;
    }
    public String getPassword() {
        return password;
    }

    public Double getTotalAmount() {
        return totalAmount;
    }

    public static int getIdAccount() {
        return idAccount;
    }

    public boolean makeDeposit(Double amount){
        this.totalAmount = this.totalAmount + amount;
        return true;
    }

    public boolean makeWithDraw(Double amount){
        if(this.totalAmount >= amount){
            this.totalAmount = this.totalAmount - amount;
            return true; 
        }else{
            return false;
        }
    }

}
