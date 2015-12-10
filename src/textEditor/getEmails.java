/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package textEditor;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.IOException;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;



public class getEmails extends javax.swing.JPanel {
    private Pattern pattern;
    private Matcher matcher;
    private static String EMAIL_PATTERN;
    public static String  emailsForTest;

    
    /**
     * Creates new form JPanelTextEditor
     * @throws java.io.IOException
     */
    public getEmails() throws IOException {
        initComponents();
    }
    public static void main(String[] args) throws IOException{
        // TODO code application logic here
        JFrame attach = new JFrame();
        getEmails editor = new getEmails();
        attach.getContentPane().add(editor);
        attach.setSize(800, 350);
        attach.setVisible(true);
    }

    public void FindEmail(ArrayList<String> domainsArray,String emailsPaths) throws IOException{
        
        int i, dotIndex=0,atIndex=0, domainsCounter=0, lastIndex=0, preIndex=0,theFirstIndex=0, theLastIndex=0, secDotIndexs=0, firstPos=0;
        String theLastDomain="", theFirstDomain="";
        int atFlag = 0, dotFlag = 0;
        Writer lastOutput = null;
        String newFileName="Final Results.csv";
        String emails = new String(Files.readAllBytes(Paths.get(emailsPaths)));
        lastOutput = new BufferedWriter(new FileWriter(newFileName, false));
        String tempString, testString;
        lastOutput.append("emails address");
        lastOutput.append(System.lineSeparator());

        for(i=0;i<=emails.length()-1;i++){
            if((emails.charAt(i)=='.')&&(atFlag>0)){
                dotFlag++;
                if(dotFlag==1)
                    dotIndex=i;
                else
                    secDotIndexs=i;
            }
            else if(emails.charAt(i)=='@'){
                atFlag++;
                if(atFlag==2)
                    atIndex=i;
            }
            else if(i==emails.length()-1){
                lastOutput.append(emails.substring(preIndex, i+1));
               // lastOutput.append(System.lineSeparator());
                lastIndex=0; tempString=""; atFlag=1; atIndex=0; dotFlag=0; dotIndex=0;
                break;
            }
            if(((atFlag==2)&&(dotFlag==1))){
                tempString = emails.substring(dotIndex, atIndex-1);
                for(String domain:domainsArray){
                    domain = domain.replaceAll("\\r", "");
                    testString = "." + domain.toLowerCase();
                    if(tempString.contains(testString)){
                        lastIndex=emails.indexOf(domain.toLowerCase(), dotIndex)+domain.length();
                        lastOutput.append(emails.substring(preIndex, lastIndex));
                        lastOutput.append(System.lineSeparator());
                        preIndex = lastIndex;
                        lastIndex=0; tempString=""; atFlag=1; atIndex=0; dotFlag=0; dotIndex=0;
                        break;
                }
             }
            }
            else if((atFlag==2)&&(dotFlag>1)){
                tempString = emails.substring(dotIndex, atIndex);
                
                for(String domain:domainsArray){
                    domain = domain.replaceAll("\\r", "");
                   testString = "." + domain.toLowerCase();
                   
                    if(tempString.contains(testString)&&(emails.charAt(domain.length()+emails.indexOf(domain.toLowerCase(), dotIndex))!='@')){
                        domainsCounter++;
                        
                        if((domainsCounter==1)){
                            theFirstIndex = emails.indexOf(domain.toLowerCase(), dotIndex);
                            theFirstDomain = domain;
                            firstPos = theFirstIndex;
                            
                            if((!tempString.contains(testString+"."))){
                                theLastDomain = theFirstDomain;
                                theLastIndex = theFirstIndex;
                                break;
                            }
                        }
                        else {
                            
                            if(firstPos != emails.indexOf(domain.toLowerCase(), dotIndex)){
                                theLastDomain = domain;
                                theLastIndex = emails.indexOf(domain.toLowerCase(), dotIndex);
                            }
                            
                            if(theFirstIndex > theLastIndex){
                            theLastIndex = theFirstIndex;
                            theLastDomain = theFirstDomain;
                            }
                        }
                    }
                }
                lastIndex=emails.indexOf(theLastDomain.toLowerCase(), dotIndex)+ theLastDomain.length();
                lastOutput.append(emails.substring(preIndex, lastIndex));
                lastOutput.append(System.lineSeparator());
                preIndex = lastIndex;
                lastIndex=0; tempString=""; atFlag=1; atIndex=0; dotFlag=0; dotIndex=0; domainsCounter=0;
            }
        }
                lastOutput.close();
    }
    
    public Writer EmailValidator( Writer outWriter) throws IOException {
        
       EMAIL_PATTERN = "([A-Za-z][^@\\s].+)@([A-Za-z0-9][^\\s]+.[A-Za-z0-9][^\\s])";
       pattern = Pattern.compile(EMAIL_PATTERN, Pattern.CASE_INSENSITIVE);
       matcher = pattern.matcher(emailsForTest);

       while(matcher.find()){
           //System.out.println("Found Value: " + matcher.group(0));
           outWriter.append(matcher.group(0));
       }
       return outWriter;
    }
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton1 = new javax.swing.JButton();
        jTextField1 = new javax.swing.JTextField();

        jButton1.setText("Attach");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(53, Short.MAX_VALUE)
                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 648, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton1)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(238, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField1ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        JFileChooser chooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter("TEXT FILES", "txt", "text");
        chooser.setFileFilter(filter);
        chooser.showOpenDialog(null);
        File f = chooser.getSelectedFile();
        String fileName = f.getAbsolutePath();
        ArrayList<String> domainsArrayList = null;
       String domainsArray[]=null;
       String emails=null;
       String newFileName="not separated Emails.txt";
        try {

           Writer output = null;
           output = new BufferedWriter(new FileWriter(newFileName, false));
           String content = new String(Files.readAllBytes(Paths.get("topLevelDomains.txt")));
           domainsArray = content.split("\n");
           domainsArrayList = new ArrayList<>(Arrays.asList(domainsArray));
           emails = new String(Files.readAllBytes(Paths.get(fileName)));
           getEmails.emailsForTest = emails.toLowerCase();
           
           try {
                output = EmailValidator(output);
                output.close();
                FindEmail(domainsArrayList, newFileName);
                
            } catch (FileNotFoundException ex) {
                Logger.getLogger(getEmails.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(getEmails.class.getName()).log(Level.SEVERE, null, ex);
            }

        } catch (FileNotFoundException ex) {
            Logger.getLogger(getEmails.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(getEmails.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JTextField jTextField1;
    // End of variables declaration//GEN-END:variables
}