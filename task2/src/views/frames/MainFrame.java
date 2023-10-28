package src.views.frames;

import src.views.panels.MainPanel;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;

import src.repository.Course;
import src.utils.InfoToolkit;
import src.utils.UISettings;
import src.views.components.BigButton;
import src.views.components.CustomInput;
import src.views.components.CustomLabel;


public class MainFrame extends BaseFrame  {    

    MainPanel subjectsPanel = new MainPanel();
    MainPanel infoPanel = new MainPanel();

    private int widthScreen = InfoToolkit.getFullWidth();
    private int heighScreen = InfoToolkit.getFullHeight();


    private LinkedList<Course> coursesList =new LinkedList<>(); 

    CustomInput subjectInput = new CustomInput();
    CustomInput markInput = new CustomInput();
    CustomLabel statusAddElement = new CustomLabel("");
    BigButton btnAddSubject = new BigButton("ADD");

    int userTotalSubjects = 0;
    int userPercentageMarks = 0;
    String userGlobalGrade = "-";


    public MainFrame(){
        super();
        this.setBackground(UISettings.getWhiteColor());
        addOptionsToAddCourse();
        addPanels();
        setDimensionesPanel();
    }

    private void setDimensionesPanel(){
        subjectsPanel.setBounds(50,200,widthScreen/2 - 100, heighScreen-200);
        infoPanel.setBounds(widthScreen/2 + 50, 200, widthScreen/2 -100, heighScreen-200);
    }

    private void addPanels(){
        subjectsPanel.setBackground(UISettings.getWhiteColor());
        infoPanel.setBackground(UISettings.getWhiteColor());
        this.add(subjectsPanel);
        this.add(infoPanel);
        createSubjectsPanelComponents();
        createInfoPanelComponents();
    }

    ActionListener eventAddCourse = new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            String inputMarkText = markInput.getText();
            String inputSubjectText = subjectInput.getText();
            if(!markInput.getText().isEmpty() && !subjectInput.getText().isEmpty()){
                try {
                    int markValue= Integer.parseInt(inputMarkText);
                    if(markValue > 0 && markValue <=100){
                        Course newCourse = new Course(inputSubjectText, markValue);
                        coursesList.add(newCourse);
                        statusAddElement.setText("");
                        markInput.setText("");
                        subjectInput.setText("");
                        
                        createSubjectsPanelComponents();
                        createInfoPanelComponents();
                    }else{
                        statusAddElement.setText("Mark must be between [ 0 - 100 ]");
                    }
                } catch (Exception exception) {
                    statusAddElement.setText("Mark must be a number");
                }
            }else{
                statusAddElement.setText("Fields must be completed");
            }
        }
    };

    private void addOptionsToAddCourse(){
        CustomLabel subjectLabel = new CustomLabel("Subject");
        CustomLabel markLabel = new CustomLabel("Mark");

        btnAddSubject.addActionListener(eventAddCourse);

        this.add(subjectLabel);        
        this.add(markLabel);  
        this.add(subjectInput);  
        this.add(markInput);  
        this.add(btnAddSubject); 
        this.add(statusAddElement);

        double sizeItem = (widthScreen/2 - 100)/3;
        int newSizeItem = (int)sizeItem;

        subjectLabel.setBounds(50,50,newSizeItem,50);
        markLabel.setBounds(newSizeItem +50,50,newSizeItem,50);
        subjectInput.setBounds(50,100,newSizeItem,50);
        markInput.setBounds(newSizeItem +50,100,newSizeItem,50);
        btnAddSubject.setBounds(2*newSizeItem+50,100,newSizeItem,50);
        statusAddElement.setBounds(50,150,widthScreen/2 -100, 50);
    }

    public void createSubjectsPanelComponents(){
        subjectsPanel.removeAll();
        int widthItemSub = (int)((widthScreen/2-100)*0.7);
        int widthItemMark = (int)((widthScreen/2-100)*0.3);
        CustomLabel subjectTitle = new CustomLabel("SUBJECTS");
        CustomLabel markTitle = new CustomLabel("MARKS");

        subjectTitle.setBounds(0,0,widthItemSub,50);
        subjectTitle.setBackground(UISettings.getGrayClearColor());
        subjectTitle.setForeground(UISettings.getWhiteColor());
        markTitle.setBounds(widthItemSub,0,widthItemMark,50);
        markTitle.setBackground(UISettings.getGrayClearColor());
        markTitle.setForeground(UISettings.getWhiteColor());
        
        subjectsPanel.add(subjectTitle);
        subjectsPanel.add(markTitle);

        int position= 50;

        for (Course course : coursesList) {
            CustomLabel itemName = new CustomLabel(course.getName());
            CustomLabel itemMark = new CustomLabel(""+course.getMark());
            subjectsPanel.add(itemName);
            subjectsPanel.add(itemMark);

            itemName.setBounds(0,position,widthItemSub,40);
            itemMark.setBounds(widthItemSub, position, widthItemMark, 40);
            position= position +40;
        }
    }

    public void calculateValues(){
        userTotalSubjects = coursesList.size();
        int totalSum = 0;
        for (Course course : coursesList) {
            totalSum = totalSum + course.getMark();
        }
        userPercentageMarks = userTotalSubjects == 0 ? 0 : (int)(totalSum/userTotalSubjects);
        if(userPercentageMarks == 0){
            userGlobalGrade = "-";
        }else if (userPercentageMarks < 20) {
            userGlobalGrade = "E";
        }else if (userPercentageMarks < 40) {
            userGlobalGrade = "D";
        }else if (userPercentageMarks < 60) {
            userGlobalGrade = "C";
        }else if (userPercentageMarks < 80) {
            userGlobalGrade = "B";
        }else{
            userGlobalGrade = "A";
        }
    }

    public void createInfoPanelComponents(){
        int widthPanel = widthScreen/2 -100;
        infoPanel.removeAll();
        calculateValues();
        CustomLabel totalSubjectsText = new CustomLabel("Total Subjects");
        CustomLabel percentageMarksText = new CustomLabel("Average Percentage Marks");
        CustomLabel globalGradeText = new CustomLabel("Global Student Grade");

        CustomLabel totalSubjectsValue = new CustomLabel("" + userTotalSubjects);
        CustomLabel percentageMarksValue = new CustomLabel(""+userPercentageMarks + " %");
        CustomLabel globalGradeValue = new CustomLabel(""+userGlobalGrade);

        infoPanel.add(totalSubjectsText);
        infoPanel.add(percentageMarksText);
        infoPanel.add(globalGradeText);

        infoPanel.add(totalSubjectsValue);
        infoPanel.add(percentageMarksValue);
        infoPanel.add(globalGradeValue);

        totalSubjectsText.setBounds(widthPanel/2-200,100,400,50);
        totalSubjectsValue.setBounds(widthPanel/2-200,160,400,50);

        percentageMarksText.setBounds(widthPanel/2-200,230,400,50);
        percentageMarksValue.setBounds(widthPanel/2-200,290,400,50);

        globalGradeText.setBounds(widthPanel/2-200,360,400,50);
        globalGradeValue.setBounds(widthPanel/2-200,420,400,50);

    }


}

