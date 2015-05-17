/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication1;
import javaapplication1.Element;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.text.SimpleDateFormat;
import java.util.Date;
/**
 *
 * @author HardFive
 */
public class DataBaseDriver {
    private String DataFile;
    
    DataBaseDriver(String path){
        DataFile = path; 
};
    public Element StringToElem (String A){
        String Qr="";
        String Description="";
        String Destination="";
        Qr = A.substring(0,A.indexOf(";")-1);
        Description =  A.substring(A.indexOf(";")+1,A.indexOf(">")-1);
        Destination = A.substring(A.indexOf(">")+1,A.indexOf("@")-1);
        Element B = new Element(Qr,Description);
        B.Destination = Destination;
        return B;
    };
    
    public Integer AddElem(String QrAdd, String Desc, String Dest) throws IOException{
        Scanner in = new Scanner(DataFile);
        while (in.hasNext()){
             String H =in.nextLine();
             Element X = StringToElem(H);
             if (QrAdd == X.QR) return 2;
        };
        in.close();
        Date d = new Date();
        SimpleDateFormat format1 = new SimpleDateFormat("dd.MM.yyyy hh:mm");
        FileWriter sw = new FileWriter(DataFile,true);
        sw.write(QrAdd+":"+Desc+">"+Dest+"@"+format1.format(d)+"!");
        sw.close();            
        return 0;   
        }
        
    public Integer MoveElem(String QrMove, String MoveTo) throws IOException{  
        Scanner in = new Scanner(DataFile);
        String Desc="";
        Boolean exist = false;
        while (in.hasNext()){
             String H =in.nextLine();
             Element X = StringToElem(H);
             if (X.QR == QrMove) {
                 Desc =X.Description; 
                 exist = true;
             }
        };
        in.close();
        if (exist == false ) return 2;
        Date d = new Date();
        SimpleDateFormat format1 = new SimpleDateFormat("dd.MM.yyyy hh:mm");
        FileWriter sw = new FileWriter(DataFile,true);
        sw.write(QrMove+":"+Desc+">"+MoveTo+"@"+format1.format(d));
        sw.close();            
        return 0;   
          
    } 
}
    

