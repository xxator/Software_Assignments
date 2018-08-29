/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab2;

/**
 *
 * @author placements2019
 */
import java.util.*;
import java.util.HashSet;
import java.util.regex.*;
import java.io.*;
import java.io.FileWriter;  
class Lab2 {
    public static void main(String[] args) throws FileNotFoundException, IOException {
        File file = new File("/home/placements2019/input.c");
        
        BufferedReader br = new BufferedReader(new FileReader(file));
        String hello = "";
        String st;
        while ((st = br.readLine()) != null)
          hello += st;
       // System.out.println(hello);
        Set<String> Data_Types = new HashSet<String>();
        Map<String, Integer> variables = new HashMap<String, Integer>();
        Pattern pattern = Pattern.compile("(?:\\w+\\s+)([a-zA-Z_]([,a-zA-Z0-9_]|[\\s*=\\s*\\d+])*)");
        Pattern forloop = Pattern.compile("(for\\s*[\\w+\\s*]*)");
        //Pattern pattern = Pattern.compile("^(int|[sS]tring)\\s+\\w+\\s*(=\\s*[^,]+)?(,\\s*\\w+\\s*(=\\s*[^,]+)?)*$");
        //Pattern pattern = Pattern.compile("[\\w+\\s+\\w+] [,\\w+\\s*]*");
        Matcher  matcher = pattern.matcher(hello);
        String temp = "";
        int count = 0;
        int count_for = 0;
        int init_count = 0;
        while (matcher.find()){
            //System.out.println(matcher.start() + "    " + matcher.end());
            temp = "";
            for(int i = matcher.start();i<matcher.end();i++)
            {
                temp = temp + hello.charAt(i);
            }
            System.out.println(temp);
            String[] arrOfvar = temp.split(" |,");
            System.out.println(arrOfvar.length);
            temp = arrOfvar[0];
            //System.out.println(temp);
            Data_Types.add(temp);
            for(int k = 0;k< arrOfvar.length;k++)
            {
                if(arrOfvar[k].indexOf("=")>=0)
                {
                    init_count++;
                }
            }
            count += arrOfvar.length-1;
            if(!variables.containsKey(temp))
            {
                variables.put(temp,new Integer(arrOfvar.length-1));
            }
            else
            {
                variables.put(temp,variables.get(temp) + (arrOfvar.length-1));
            }
            
        }
        matcher = forloop.matcher(hello);
        while(matcher.find())
        {
            count_for++;
        }
        int count_while = 0;
        Pattern whileloop = Pattern.compile("(while\\s*[\\w+\\s*]*)");
        matcher = whileloop.matcher(hello);
        while(matcher.find())
        {
            count_while++;
        }
        int cond = 0;
        
          try{    
           PrintWriter fw=new PrintWriter("/home/placements2019/iit2016026.txt");    
            Set< Map.Entry< String,Integer> > sti = variables.entrySet();   
            fw.print("Number of data types : " + Data_Types.size() + '\n');
            fw.print("Number of initialised variables : " + init_count + '\n');
            fw.print("Number of unitialised variables : " + (count-init_count) + '\n');
            fw.print("Number of for loops : " + count_for + '\n');
            fw.print("Number of while loops : " + count_while + '\n');
            for (Map.Entry< String,Integer> me:sti)
            {
               fw.print((String)me.getKey()+":");
               fw.print(variables.get(me.getKey()));
               fw.print('\n');
            }
           fw.close();    
          }catch(Exception e){System.out.println(e);}    
          System.out.println("Success...");    
         Set< Map.Entry< String,Integer> > sti = variables.entrySet();   
        System.out.println("Number of data types : " + Data_Types.size());
        System.out.println("Number of initialised variables : " + init_count);
        for (Map.Entry< String,Integer> me:sti)
       {
           System.out.print(me.getKey()+":");
           System.out.println(me.getValue());
       }
        
    }
}

