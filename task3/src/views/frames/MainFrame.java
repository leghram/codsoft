package src.views.frames;

import src.views.panels.MainPanel;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import src.repository.ATM;
import src.repository.Account;
import src.utils.InfoToolkit;
import src.utils.UISettings;
import src.views.components.BigButton;
import src.views.components.CustomInput;
import src.views.components.CustomLabel;


public class MainFrame extends BaseFrame  {    

    BaseFrame activeFrame;

    int midWidth = InfoToolkit.getFullWidth()/2;
    int midHeigh = InfoToolkit.getFullHeight()/2;

    MainPanel mainPanel = new MainPanel();
    MainPanel withDrawPanel = new MainPanel();
    MainPanel depositPanel = new MainPanel();
    MainPanel checkBalancePanel = new MainPanel();
    MainPanel registerPanel = new MainPanel();


    CustomInput subjectInput = new CustomInput();
    CustomInput markInput = new CustomInput();
    CustomLabel statusAddElement = new CustomLabel("");
    BigButton btnAddSubject = new BigButton("ADD");

    int userTotalSubjects = 0;
    int userPercentageMarks = 0;
    String userGlobalGrade = "-";

   

    public MainFrame(){
        super();
        activeFrame = this;
        this.setBackground(UISettings.getWhiteColor());
        createMainPanel();
    }

    public void createMainPanel(){
        mainPanel.removeAll();
        activeFrame.cleanFrame();

        activeFrame.add(mainPanel);
        mainPanel.setFullScreen();
        mainPanel.setBackground(Color.white);
        
        BigButton btnGoWithDraw = new BigButton("WithDraw");
        BigButton btnGoDeposit = new BigButton("Deposit");
        BigButton btnGoBalance = new BigButton("Check Balance");
        BigButton btnGoRegister = new BigButton("New Account");
        
        mainPanel.add(btnGoWithDraw);
        mainPanel.add(btnGoDeposit);
        mainPanel.add(btnGoBalance);
        mainPanel.add(btnGoRegister); 
        
        btnGoWithDraw.setBounds(midWidth - 200,midHeigh- 50,200,50);
        btnGoDeposit.setBounds(midWidth ,midHeigh- 50,200,50);
        btnGoBalance.setBounds(midWidth - 200,midHeigh,200,50);
        btnGoRegister.setBounds(midWidth ,midHeigh,200,50);

        btnGoWithDraw.addActionListener(eventChangeToWithPanel);
        btnGoDeposit.addActionListener(eventChangeToDepositPanel);
        btnGoBalance.addActionListener(eventChangeToCheckPanel);
        btnGoRegister.addActionListener(eventChangeToRegisterPanel);

    }

    public void createWithDrawPanel(){
        withDrawPanel.removeAll();
        activeFrame.cleanFrame();
        
        activeFrame.add(withDrawPanel);
        withDrawPanel.setFullScreen();
        withDrawPanel.setBackground(Color.white);
        
        BigButton btnBackGoMain = new BigButton("Back");
        withDrawPanel.add(btnBackGoMain);
        btnBackGoMain.setBounds(0,0,200,50);
        btnBackGoMain.addActionListener(eventChangeToMainPanel);

        CustomLabel amountLabel = new CustomLabel("Amount");
        CustomInput amountText = new CustomInput();
        CustomLabel accountLabel = new CustomLabel("Account");
        CustomInput accountText = new CustomInput();
        CustomLabel passwordLabel = new CustomLabel("Password");
        CustomInput passwordText = new CustomInput();

        BigButton btnMakeWithDraw = new BigButton("WithDraw");

        CustomLabel showInfoLabel = new CustomLabel("");

        withDrawPanel.add(amountLabel);
        withDrawPanel.add(amountText);
        withDrawPanel.add(accountLabel);
        withDrawPanel.add(accountText);
        withDrawPanel.add(passwordLabel);
        withDrawPanel.add(passwordText);

        withDrawPanel.add(btnMakeWithDraw);
        withDrawPanel.add(showInfoLabel);

        amountLabel.setBounds(midWidth - 250, midHeigh - 300,200, 50);
        amountText.setBounds(midWidth + 50, midHeigh - 300, 200, 50);
        accountLabel.setBounds(midWidth -250, midHeigh - 200, 200, 50);
        accountText.setBounds(midWidth + 50, midHeigh - 200, 200, 50);
        passwordLabel.setBounds(midWidth - 250, midHeigh - 100,200, 50);
        passwordText.setBounds(midWidth + 50, midHeigh - 100, 200, 50);
        
        btnMakeWithDraw.setBounds(midWidth - 200, midHeigh, 400, 50);

        showInfoLabel.setBounds(midWidth - 300, midHeigh + 100, 600,50);

        ActionListener eventMakeWithDraw = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String inputAmount = amountText.getText();
                String inputAccount = accountText.getText();
                String inputPassword = passwordText.getText();
                boolean validAmount = false;
                Double amountNumber = null;
                if (inputAmount.length() > 0 && inputAccount.length()>0 && inputPassword.length() > 0) {
                    try {
                        amountNumber = Double.parseDouble(inputAmount);
                        validAmount = true;
                    } catch (Exception exception) {
                        showInfoLabel.setText("The amount must be a number...");
                    }
                    if (validAmount) {
                        try {
                            int accountNumber = Integer.parseInt(inputAccount);
                            Account account = ATM.getAccountById(accountNumber);
                            if (account == null) {
                                showInfoLabel.setText("The account doesnt exist...");
                            }else{
                                if (account.getPassword().equals(inputPassword)) {
                                    boolean statusOperation =  account.makeWithDraw(amountNumber);
                                    if (statusOperation) {
                                        amountText.setText("");
                                        accountText.setText("");
                                        passwordText.setText("");
                                        showInfoLabel.setText("Operation completed successfully!!!");
                                    }else{
                                        showInfoLabel.setText("Not enough money in the account...");
                                    }
                                }else{
                                    showInfoLabel.setText("Wrong password...");
                                }
                            }

                        } catch (Exception exception) {
                            showInfoLabel.setText("The account is a number...");
                        }
                    }


                }else{
                    showInfoLabel.setText("All the fields are required...");
                }
            }
        };

        btnMakeWithDraw.addActionListener(eventMakeWithDraw);


    }

    public void createDepositPanel(){
        depositPanel.removeAll();
        activeFrame.cleanFrame();
        
        activeFrame.add(depositPanel);
        depositPanel.setFullScreen();
        depositPanel.setBackground(Color.white);
        
        BigButton btnBackGoMain = new BigButton("Back");
        depositPanel.add(btnBackGoMain);
        btnBackGoMain.setBounds(0,0,200,50);
        btnBackGoMain.addActionListener(eventChangeToMainPanel);


        CustomLabel accountLabel = new CustomLabel("Account");
        CustomInput accountText = new CustomInput();
        CustomLabel amountLabel = new CustomLabel("Amount");
        CustomInput amountText = new CustomInput();

        BigButton btnMakeDeposit = new BigButton("Deposit");

        CustomLabel showInfoLabel = new CustomLabel("");

        depositPanel.add(accountLabel);
        depositPanel.add(accountText);
        depositPanel.add(amountLabel);
        depositPanel.add(amountText);
        depositPanel.add(btnMakeDeposit);
        depositPanel.add(showInfoLabel);

        accountLabel.setBounds(midWidth -250, midHeigh - 200, 200, 50);
        accountText.setBounds(midWidth + 50, midHeigh - 200, 200, 50);
        amountLabel.setBounds(midWidth - 250, midHeigh - 100,200, 50);
        amountText.setBounds(midWidth + 50, midHeigh - 100, 200, 50);
        
        btnMakeDeposit.setBounds(midWidth - 200, midHeigh, 400, 50);

        showInfoLabel.setBounds(midWidth - 300, midHeigh + 100, 600,50);

        ActionListener eventMakeDeposit = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String inputAccount = accountText.getText();
                String inputAmount = amountText.getText();
                if (rootPaneCheckingEnabled) {
                    try {
                        int accountNumber = Integer.parseInt(inputAccount);
                        Double amountNumber = Double.parseDouble(inputAmount);
                        Account account = ATM.getAccountById(accountNumber);
                        if (account == null) {
                            showInfoLabel.setText("The account doesnt exist...");
                        }else{
                            if (amountNumber < 1) {
                                showInfoLabel.setText("The amount must be greater...");
                            }else{
                                account.makeDeposit(amountNumber);
                                showInfoLabel.setText("Operation completed successfully!!!");
                                accountText.setText("");
                                amountText.setText("");
                            }
                        }

                    } catch (Exception exception) {
                        showInfoLabel.setText("Both fields are numbers...");
                    }
                }else{
                    showInfoLabel.setText("Complete the fields...");
                }
            }
        };

        btnMakeDeposit.addActionListener(eventMakeDeposit);


    }

    public void createCheckBalancePanel(){
        checkBalancePanel.removeAll();
        activeFrame.cleanFrame();
        
        activeFrame.add(checkBalancePanel);
        checkBalancePanel.setFullScreen();
        checkBalancePanel.setBackground(Color.white);
        
        BigButton btnBackGoMain = new BigButton("Back");
        checkBalancePanel.add(btnBackGoMain);
        btnBackGoMain.setBounds(0,0,200,50);
        btnBackGoMain.addActionListener(eventChangeToMainPanel);


        CustomLabel accountLabel = new CustomLabel("Account");
        CustomInput accountText = new CustomInput();
        CustomLabel passwordLabel = new CustomLabel("Password");
        CustomInput passwordText = new CustomInput();

        BigButton btnCheckBalance = new BigButton("Check");

        CustomLabel showInfoLabel = new CustomLabel("");

        checkBalancePanel.add(accountLabel);
        checkBalancePanel.add(accountText);
        checkBalancePanel.add(passwordLabel);
        checkBalancePanel.add(passwordText);
        checkBalancePanel.add(btnCheckBalance);
        checkBalancePanel.add(showInfoLabel);

        accountLabel.setBounds(midWidth -250, midHeigh - 200, 200, 50);
        accountText.setBounds(midWidth + 50, midHeigh - 200, 200, 50);
        passwordLabel.setBounds(midWidth - 250, midHeigh - 100,200, 50);
        passwordText.setBounds(midWidth + 50, midHeigh - 100, 200, 50);
        
        btnCheckBalance.setBounds(midWidth - 200, midHeigh, 400, 50);

        showInfoLabel.setBounds(midWidth - 300, midHeigh + 100, 600,50);

        ActionListener eventCheckBalance = new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            String inputAccount = accountText.getText();
            String inputPassword = passwordText.getText();
            if(inputAccount.length()>0 && inputPassword.length() >0){
                try {
                    int inputAccountNumber = Integer.parseInt(inputAccount);
                    Account account = ATM.getAccountById(inputAccountNumber);
                    if(account == null){
                        showInfoLabel.setText("The account doesnt exist...");
                    }else{
                        if (account.getPassword().equals(inputPassword)) {
                            showInfoLabel.setText("Total amount available: " + account.getTotalAmount());
                            accountText.setText("");
                            passwordText.setText("");
                        }else{
                            showInfoLabel.setText("Wrong password...");
                        }
                    }

                } catch (Exception ex) {
                    showInfoLabel.setText("Account is a number...");
                }
                
            }else{
                showInfoLabel.setText("Complete the fields...");
            }
            }
        };

        btnCheckBalance.addActionListener(eventCheckBalance);

    }

    public void createRegisterPanel(){
        registerPanel.removeAll();
        activeFrame.cleanFrame();
        
        activeFrame.add(registerPanel);
        registerPanel.setFullScreen();
        registerPanel.setBackground(Color.white);
        
        BigButton btnBackGoMain = new BigButton("Back");
        registerPanel.add(btnBackGoMain);
        btnBackGoMain.setBounds(0,0,200,50);
        btnBackGoMain.addActionListener(eventChangeToMainPanel);


        CustomLabel accountLabel = new CustomLabel("Account");
        CustomLabel accountText = new CustomLabel(""+ Account.getIdAccount());
        CustomLabel passwordLabel = new CustomLabel("Password");

        CustomInput passwordText = new CustomInput();

        BigButton btnCreateAccount = new BigButton("Create Account");

        registerPanel.add(accountLabel);
        registerPanel.add(accountText);
        registerPanel.add(passwordLabel);
        registerPanel.add(passwordText);
        registerPanel.add(btnCreateAccount);

        accountLabel.setBounds(midWidth -300, midHeigh - 100, 200, 50);
        accountText.setBounds(midWidth + 100, midHeigh - 100, 200, 50);
        passwordLabel.setBounds(midWidth - 300, midHeigh+50,200, 50);
        passwordText.setBounds(midWidth +100, midHeigh + 50, 200, 50);
        btnCreateAccount.setBounds(midWidth - 200, midHeigh +200, 400, 50);

        ActionListener eventSaveAccount = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String inputText = passwordText.getText();
                if (inputText.length() > 0) {
                    Account account = new Account(inputText);
                    ATM.addAccount(account);
                    passwordText.setText("");
                    createMainPanel();
                }
            }
        };

        btnCreateAccount.addActionListener(eventSaveAccount);

    }

    ActionListener eventChangeToMainPanel = new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            createMainPanel();
        }
    };
    ActionListener eventChangeToWithPanel = new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            createWithDrawPanel();
        }
    };
    ActionListener eventChangeToDepositPanel = new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            createDepositPanel();
        }
    };
    ActionListener eventChangeToCheckPanel = new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            createCheckBalancePanel();
        }
    };
    ActionListener eventChangeToRegisterPanel = new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            createRegisterPanel();
        }
    };




}

