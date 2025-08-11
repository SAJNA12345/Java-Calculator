import java.awt.*;
import java.awt.event.*;
import java.util.Arrays;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
public class calculator {
//size of the window

int boardWidth=360;
int boardHeight=540;

//adding colours to the buttons
Color customLightGrey=new Color(212,212,210);
Color customDarkGrey=new Color(80,80,80);
Color customBlack=new Color(28,28,28);
Color customOrange=new Color(255,149,0);

String[] buttonValues = {
        "AC", "+/-", "%", "÷", 
        "7", "8", "9", "×", 
        "4", "5", "6", "-",
        "1", "2", "3", "+",
        "0", ".", "√", "="
    };

String[] rightSymbols = {"÷", "×", "-", "+", "="};
String[] topSymbols = {"AC", "+/-", "%"};

JFrame frame=new JFrame("Calculator");
JLabel displayLabel=new JLabel();
JPanel displayPanel=new JPanel();
JPanel buttonsPanel=new JPanel();

String A="0";
String op=null;
String B=null;


calculator()
{
    
    frame.setSize(boardWidth, boardHeight);
    frame.setLocationRelativeTo(null);
    frame.setResizable(false);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setLayout(new BorderLayout());

    displayLabel.setBackground(customBlack);
    displayLabel.setForeground(Color.white);
    displayLabel.setFont(new Font("Arial",Font.PLAIN,80));
    displayLabel.setHorizontalAlignment(JLabel.RIGHT);
    displayLabel.setText("0");
    displayLabel.setOpaque(true);

    displayPanel.setLayout(new BorderLayout());
    displayPanel.add(displayLabel);         //label is added to displayPanel
    frame.add(displayPanel,BorderLayout.NORTH); // displayPanel is added to the window that is frame.

    buttonsPanel.setLayout(new GridLayout(5,4,5,5));
    buttonsPanel.setBackground(customBlack);
    frame.add(buttonsPanel,BorderLayout.CENTER);

    for(int i=0;i<buttonValues.length;i++)
    {
        JButton button=new JButton();
        String buttonValue=buttonValues[i];
        button.setFont(new Font("Arial",Font.PLAIN,30));
        button.setText(buttonValue);
        button.setFocusable(false);
        button.setBorder(new LineBorder(customBlack));

        if(Arrays.asList(topSymbols).contains(buttonValue))
        {
            button.setBackground(customLightGrey);
            button.setForeground(customBlack);

        }
        else if(Arrays.asList(rightSymbols).contains(buttonValue))
        {
            button.setBackground(customOrange);
            button.setForeground(Color.white);

        }
        else
        {
            button.setBackground(customDarkGrey);
            button.setForeground(Color.white);
        }
        buttonsPanel.add(button);
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e)
            {
                JButton button=(JButton) e.getSource();
                String buttonValue=button.getText();
                if(Arrays.asList(rightSymbols).contains(buttonValue))
                {
                    if(buttonValue=="=")
                    {
                        double numA=0;
                        double numB=0;
                        if(A!=null)
                        {
                            B=displayLabel.getText();
                            numA=Double.parseDouble(A);
                            numB=Double.parseDouble(B);

                        }
                        if(op=="+")
                        {
                            displayLabel.setText(removeZero(numA+numB));
                        }
                        else if(op=="-")
                        {
                            displayLabel.setText(removeZero(numA-numB));
                        }
                        else if(op=="×")
                        {
                            displayLabel.setText(removeZero(numA*numB));
                        }
                        else if(op=="÷")
                        {
                            displayLabel.setText(removeZero(numA/numB));
                        }
                        clearAll();

                    }
                    else if("÷×-+".contains(buttonValue))
                    {
                        if(op==null)
                        {
                            A=displayLabel.getText();
                            displayLabel.setText("0");
                            B="0";
                        }
                        op=buttonValue;

                    }
                }
                else if(Arrays.asList(topSymbols).contains(buttonValue))
                {
                    if(buttonValue=="AC")
                    {
                        clearAll();
                        displayLabel.setText("0");
                    }
                    else if(buttonValue=="+/-")  //to make a number positive or negative.
                    {
                        double num=Double.parseDouble(displayLabel.getText());
                        num*=-1;
                        displayLabel.setText(removeZero(num));
                    }
                    else if(buttonValue=="%")
                    {
                        double num=Double.parseDouble(displayLabel.getText());
                        num /=100;
                        displayLabel.setText(removeZero(num));
    
                    }
                    
                    
                }
                else if(buttonValue.equals("√")) 
                {
                    
                    double num = Double.parseDouble(displayLabel.getText());
                    if(num >= 0) {
                        num = Math.sqrt(num);
                        displayLabel.setText(removeZero(num));
                    } else {
                        displayLabel.setText("Error"); // can't take sqrt of negative number
                    }
                }
                else  //extra digits or decimal places
                {
                    if(buttonValue==".")  //decimal places
                    {
                        if(!displayLabel.getText().contains(buttonValue))
                        {
                            displayLabel.setText(displayLabel.getText()+buttonValue);
                        }

                    }
                    
                    else if("0123456789".contains(buttonValue))
                    {
                        if(displayLabel.getText()=="0")
                        {
                            displayLabel.setText(buttonValue);
                        }
                        else
                        {
                            displayLabel.setText(displayLabel.getText()+buttonValue);  //adding digits more than one
                        }
                    }           
             }

            }
        });
        frame.setVisible(true);
    }
}
void clearAll()   //if we press AC,everything should comes to initial state.
{
    A="0";
    op=null;
    B=null;
}
String removeZero(double num)   //if there is decimal,then we need to return the ans in decimal,but if it int,return in int.
{
    if(num%1==0)
    {
        return Integer.toString((int) num);
    }
    else
    return Double.toString(num);
}
}
