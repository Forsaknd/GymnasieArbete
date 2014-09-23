package GymnasieArbete.graphics;
 
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
 
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
 
public class GUI {
       
    public JLabel labelMenu;
    public JButton buttonBlue;
    public JButton buttonRed;
 
    public GUI(){
    }
   
    public void renderMenu(JFrame frame){
    	//LABELS
    	labelMenu = new JLabel("MENU");
    	labelMenu.setForeground(Color.red);
    	labelMenu.setFont(new Font(labelMenu.getName(), Font.BOLD , 50));
   
    	//BUTTONS
    	buttonBlue = new JButton("blå");
    	buttonBlue.addActionListener((ActionListener) this);
    	buttonRed = new JButton("röd");
    	buttonRed.addActionListener((ActionListener) this);
   
    	//ADD TO FRAME
    	frame.add(labelMenu);
    	frame.add(buttonBlue);
    	frame.add(buttonRed);
    }
   
    public void actionPerformed(ActionEvent e) {
            if(e.getSource() == buttonBlue){
                    System.out.println("hej");
                labelMenu.setForeground(Color.blue);
        }
        if(e.getSource() == buttonRed){
                System.out.println("hejdå");
                labelMenu.setForeground(Color.red);
        }
    }
}

