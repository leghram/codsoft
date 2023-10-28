package src.views.frames;

import src.views.panels.MainPanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import src.utils.InfoToolkit;
import src.utils.UISettings;
import src.views.components.BigButton;
import src.views.components.CustomInput;
import src.views.components.CustomLabel;


public class MainFrame extends BaseFrame  {    

    MainPanel mainPanel = new MainPanel();
    MainPanel gamePanel = new MainPanel();
    MainPanel configPanel = new MainPanel();

    private int widthScreen = InfoToolkit.getFullWidth();
    private int heighScreen = InfoToolkit.getFullHeight();

    private MainFrame activeFrame;

    // SETTINGS VALUES
    private boolean gameWon = false;
    private int minValue = 0;
    private int maxValue = 100;
    private int defaultAttemps = 3;
    private int attemps = defaultAttemps;
    private int score = 0;
    private int totalGames = 0;
    private String statusText = "";
    private Random randomGenerator = new Random();
    private int randomNumber;
    
    
    public MainFrame(){
        super();
        activeFrame = this;
        setMainPanelActive();
    }

    ActionListener eventStartGame = new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            resetConfigValues();
            regenerateRandomValue();
            activeFrame.setGamePanelActive();
        }
    };

    ActionListener eventChangeGamePanel = new ActionListener() {
        public void actionPerformed(ActionEvent e) {
           activeFrame.setGamePanelActive();
        }
    };

    public void setMainPanelActive(){
        this.cleanFrame();
        mainPanel.setBackgroundColor(UISettings.getWhiteColor());
        BigButton btnStart = new BigButton("Start" );
        btnStart.setNewLocation(widthScreen/2 - 150, heighScreen/2);  
        btnStart.addActionListener(eventStartGame);
        mainPanel.setFullScreen();
        mainPanel.addButton(btnStart);
        this.addPanel(mainPanel);   
    }

    public void regenerateRandomValue(){
        gameWon = false;
        totalGames = totalGames +1;
        randomNumber = randomGenerator.nextInt(maxValue) + minValue;
    }

    public void resetConfigValues(){
        gameWon = false;
        minValue = 0;
        maxValue = 100;
        attemps = defaultAttemps;
        score = 0;
        totalGames = 0;
        statusText = "";
    }

    public int getCurrenteRate(){
        
        if (totalGames ==0) {
            return 0;
        }
        if(score ==0){
            return 0;
        }
        int result = (score * 100 / totalGames);
        return result;
    }


    public void setGamePanelActive(){
        this.cleanFrame();
        gamePanel.setBackgroundColor(UISettings.getWhiteColor());
        gamePanel.setFullScreen();
        this.addPanel(gamePanel);   
        
        BigButton btnGoMain = new BigButton("Salir");  
        BigButton btnGoSettings = new BigButton("Settings");
        BigButton btnNewGame = new BigButton("New Game");

        btnGoMain.setBounds(60, 50, 150, 50);
        btnGoSettings.setBounds(widthScreen - 210, 50, 150, 50);
        btnNewGame.setBounds(widthScreen/2 - 100,heighScreen/2 + 150,200,50);

        gamePanel.addButton(btnGoMain);
        gamePanel.addButton(btnGoSettings);
        gamePanel.addButton(btnNewGame);

        // INFO PANEL
        CustomLabel scoreText = new CustomLabel("SCORE");
        CustomLabel totalGamesText = new CustomLabel("TOTAL GAMES");
        CustomLabel attempsText = new CustomLabel("INTENTOS");
        CustomLabel ratePercentageText = new CustomLabel("RATE");
        CustomLabel scoreTextValue = new CustomLabel("" + score);
        CustomLabel totalGamesTextValue = new CustomLabel("" + totalGames);
        CustomLabel attempsTextValue = new CustomLabel("" + attemps);
        CustomLabel ratePercentageTextValue = new CustomLabel("" + getCurrenteRate()+ " %");
        gamePanel.add(scoreText);
        gamePanel.add(totalGamesText);
        gamePanel.add(attempsText);
        gamePanel.add(ratePercentageText);
        gamePanel.add(scoreTextValue);
        gamePanel.add(totalGamesTextValue);
        gamePanel.add(attempsTextValue);
        gamePanel.add(ratePercentageTextValue);
        
        scoreText.setBounds(widthScreen/2 - 400, heighScreen/2 -400, 200, 100);
        totalGamesText.setBounds(widthScreen/2 - 200, heighScreen/2 -400, 200, 100);
        attempsText.setBounds(widthScreen/2 , heighScreen/2 -400, 200, 100);
        ratePercentageText.setBounds(widthScreen/2 + 200, heighScreen/2 -400, 200, 100);

        scoreTextValue.setBounds(widthScreen/2 - 400, heighScreen/2 -300, 200, 100);
        totalGamesTextValue.setBounds(widthScreen/2 - 200, heighScreen/2 -300, 200, 100);
        attempsTextValue.setBounds(widthScreen/2 , heighScreen/2 -300, 200, 100);
        ratePercentageTextValue.setBounds(widthScreen/2 + 200, heighScreen/2 -300, 200, 100);




        CustomLabel textGuesNumber = new CustomLabel("Guess The Number");
        textGuesNumber.setBounds(widthScreen/2 - 200,heighScreen/2 - 80,400,50);
        gamePanel.addLabel(textGuesNumber);

        CustomInput inputNumber = new CustomInput();
        inputNumber.setBounds(widthScreen/2 -200,heighScreen/2,200,50);
        gamePanel.addInput(inputNumber);

        BigButton btnVerifyNumber = new BigButton("Verify");
        btnVerifyNumber.setBounds(widthScreen/2,heighScreen/2,200,50);
        gamePanel.addButton(btnVerifyNumber);

        CustomLabel textStatusResult = new CustomLabel(statusText);
        textStatusResult.setBounds(widthScreen/2 - 200,heighScreen/2 + 80,400,50);
        gamePanel.addLabel(textStatusResult);


        ResetValues fnStablishValuesLabelsTexts = ()->{
            scoreTextValue.setText(""+score);
            totalGamesTextValue.setText(""+totalGames);
            attempsTextValue.setText(""+attemps);
            ratePercentageTextValue.setText(""+getCurrenteRate()+" %");
        };

        ResetValues fnResetValuesLabelsTexts = ()->{
            inputNumber.setText("");
            textStatusResult.setText("");
            fnStablishValuesLabelsTexts.resetValues();
        };


        ActionListener eventVerify = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String textoInput = inputNumber.getText();
                if(attemps > 0 && !gameWon){
                    try {
                        int numberInput = Integer.parseInt(textoInput);
                        if(numberInput == randomNumber){
                            gameWon = true;
                            score = score + 1;
                            textStatusResult.setText("WINNER!!!");
                        }else{
                            String clueInfo = numberInput >randomNumber ? "The number is lower" : "The number is higher";
                            textStatusResult.setText(clueInfo);
                        }

                    } catch (Exception exception) {
                        textStatusResult.setText("No valid value");
                    }
                    inputNumber.setText("");
                    attemps = attemps- 1;
                    fnStablishValuesLabelsTexts.resetValues();
                }else{
                    String statusGame = gameWon ? "You Won!!! Game finished" :"Game Lost: 0 attemps available";
                    inputNumber.setText("");
                    textStatusResult.setText(statusGame);
                }

            }
        }; 

        btnVerifyNumber.addActionListener(eventVerify);

        

        ResetValues fnLabelTextResetValues = ()->{
            resetConfigValues();
            regenerateRandomValue();
            fnResetValuesLabelsTexts.resetValues();
        };

       
        

        ActionListener eventChangeMainPanel = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                activeFrame.setMainPanelActive();
                resetConfigValues();
                fnLabelTextResetValues.resetValues();
            }
        };

        ActionListener eventChangeSettingsPanel = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                activeFrame.setSettingsPanelActive();  
                inputNumber.setText(""); 
            }
        };

        ActionListener eventNewGame = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                inputNumber.setText(""); 
                regenerateRandomValue();
                attemps = defaultAttemps;
                fnResetValuesLabelsTexts.resetValues();
            }
        };

        btnGoMain.addActionListener(eventChangeMainPanel);
        btnGoSettings.addActionListener(eventChangeSettingsPanel);
        btnNewGame.addActionListener(eventNewGame);


    }

    public void setSettingsPanelActive(){
        this.cleanFrame();
        configPanel.setBackgroundColor(UISettings.getWhiteColor());
        configPanel.setFullScreen();

        BigButton btnGoGamePanel = new BigButton("Back");  
        btnGoGamePanel.setBounds(60,50,200,50);
        btnGoGamePanel.addActionListener(eventChangeGamePanel);

        CustomInput inputNewAttempsValue = new CustomInput();
        inputNewAttempsValue.setBounds(widthScreen/2-200,heighScreen/2,200, 50);

        BigButton btnSaveChanges = new BigButton("Save");
        btnSaveChanges.setBounds(widthScreen/2,heighScreen/2,200, 50);

        CustomLabel showTextSaved = new CustomLabel("");
        showTextSaved.setBounds(widthScreen/2-250, heighScreen/2 +100, 500 , 50);

        CustomLabel labelTotalAttempsNow = new CustomLabel("ATTEMPS : "+ defaultAttemps);
        labelTotalAttempsNow.setBounds(widthScreen/2 -100, heighScreen/2 -200, 200,50);

        configPanel.addButton(btnGoGamePanel);
        configPanel.add(inputNewAttempsValue);
        configPanel.add(showTextSaved);
        configPanel.add(btnSaveChanges);
        configPanel.add(labelTotalAttempsNow);

        this.addPanel(configPanel);   

        ActionListener updateAttempsValue = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String textoInput = inputNewAttempsValue.getText();
                try {
                    int newAttempsValue = Integer.parseInt(textoInput);
                    if(newAttempsValue > 0 && newAttempsValue<= 20){
                        defaultAttemps = newAttempsValue;
                        showTextSaved.setText("SAVED. It will be applied the next game");
                        inputNewAttempsValue.setText("");
                        labelTotalAttempsNow.setText("ATTEMPS : "+ defaultAttemps);
                    }else{
                        showTextSaved.setText("attemps must be between 1 - 20");
                    }
                    
                } catch (Exception exception) {
                    showTextSaved.setText("Value no valid");
                }
            }
        };

        btnSaveChanges.addActionListener(updateAttempsValue);
    }




}


interface ResetValues{
    void resetValues();
}