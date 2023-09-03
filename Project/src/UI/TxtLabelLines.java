package UI;

import javax.swing.JLabel;

/**
 *
 * @author Lara
 */
public class TxtLabelLines extends JLabel
{
    public TxtLabelLines()
    {
    
    }
    public TxtLabelLines (String text) {
        super(text);
    }
    
 public void breakintoLines()
        {
            String  text=this.getText();
            Character c=null,temp=null;
            int i=text.length()/2;
            String str[]=new String[2];
            if(this.getText().length()>=15)
            {              
                 c=text.charAt(text.length()/2);
                 if(!c.equals(' '))
                 {
                     for(i=text.length()/2+1;i<text.length();i++)
                     {
                         temp=text.charAt(i);
                         if(temp.equals(' '))
                         {
                           str[0]=text.subSequence(0,i).toString();
                           str[1]=text.subSequence(i, text.length()).toString();
                           break;
                         }
                     }
                     if(str[0]==null)
                     {
                         str[0]=text.subSequence(0,text.length()/2).toString();
                         str[1]=text.subSequence(text.length()/2, text.length()).toString();
                     }
                 }
                 else{
                 str[0]=text.subSequence(0,text.length()/2).toString();  
                 str[1]=text.subSequence(text.length()/2+1, text.length()).toString();
                 }
                 
                 System.out.println(str[0]+" "+str[1]);
                 this.setText("<html>"+str[0]+"<br>"+str[1]+"<html>");                 
        }
}   
}
