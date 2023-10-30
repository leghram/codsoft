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
        mainPanel.setBackground(Color.green);
        
        BigButton btnGoWithDraw = new BigButton("WithDraw");
        BigButton btnGoDeposit = new BigButton("Deposit");
        BigButton btnGoBalance = new BigButton("Balance");
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
        withDrawPanel.setBackground(Color.red);
        
        BigButton btnBackGoMain = new BigButton("Back");

        
        withDrawPanel.add(btnBackGoMain);


        btnBackGoMain.setBounds(0,0,200,50);
        btnBackGoMain.addActionListener(eventChangeToMainPanel);
    }

    public void createDepositPanel(){
        depositPanel.removeAll();
        activeFrame.cleanFrame();
        
        activeFrame.add(depositPanel);
        depositPanel.setFullScreen();
        depositPanel.setBackground(Color.orange);
        
        BigButton btnBackGoMain = new BigButton("Back");

        
        depositPanel.add(btnBackGoMain);


        btnBackGoMain.setBounds(0,0,200,50);
        btnBackGoMain.addActionListener(eventChangeToMainPanel);
    }

    public void createCheckBalancePanel(){
        checkBalancePanel.removeAll();
        activeFrame.cleanFrame();
        
        activeFrame.add(checkBalancePanel);
        checkBalancePanel.setFullScreen();
        checkBalancePanel.setBackground(Color.yellow);
        
        BigButton btnBackGoMain = new BigButton("Back");
        checkBalancePanel.add(btnBackGoMain);
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




    }

    public void createRegisterPanel(){
        registerPanel.removeAll();
        activeFrame.cleanFrame();
        
        activeFrame.add(registerPanel);
        registerPanel.setFullScreen();
        registerPanel.setBackground(Color.blue);
        
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

