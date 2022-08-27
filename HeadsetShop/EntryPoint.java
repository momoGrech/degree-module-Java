import itemSrc.*;
import java.io.*;
import java.util.Scanner;
import java.util.*;
import GUI.*;

/**
 * Write a description of class EntryPoint here.
 * @author (Mohammed Fuad Gurun)
 * @version (22/11/2021)
 */
public class EntryPoint
{
    public static void main(String[] args){
        //Initialise manager object
        Manager newMang = new Manager();
        //import the headset list and the order list by calling initialiseData
        newMang.initialiseData();
        
        //Initialise GUI object and pass the required information which is in Manager class
        JavaGUI GUI = new JavaGUI(newMang);
     }
    
    
    
    
    
}
