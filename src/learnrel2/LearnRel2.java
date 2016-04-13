
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package learnrel2;

/**
 *
 * @author staff
 */

import java.io.*;
import java.util.StringTokenizer;

import java.util.TreeMap;
import edu.sussex.nlp.jws.*;

import java.util.*;
import edu.stanford.nlp.pipeline.*;
import edu.stanford.nlp.ling.*;
import edu.stanford.nlp.ling.CoreAnnotations.*;
import edu.stanford.nlp.util.CoreMap;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;

public class LearnRel2 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)throws FileNotFoundException, IOException
    {
        // TODO code application logic here
         try
            {
              if (args.length == 0) {

   System.out.println("No Command Line arguments");
   
  } else {
                for (int i = 0; i < args.length; i++) {
                    System.out.println("args[" + i + "]: " + args[i]);}
                }
                    
                     String s1= args[0];
             
                 String s2=args[1];
                       
                   String s4="result/temp/cansentcm.txt";
                     
            String concept1,concept2, line1;
            cansent cs=new cansent();
            chunker ch=new chunker();
            sentdiv sd=new sentdiv();
            cantrip ct=new cantrip();
            relsug rg=new relsug();
            relset rs=new relset();
            
      
               String s5=args[2];
      
            FileWriter fw=new FileWriter(s5);
             BufferedWriter bw=new BufferedWriter(fw); 
                
       
               FileReader fr1=new FileReader(s2);
            BufferedReader br1=new BufferedReader(fr1);
       
                
            while((line1=br1.readLine())!=null)
           {
            
               StringTokenizer st=new StringTokenizer(line1,",");
                concept1=st.nextToken();
                concept2=st.nextToken();
            cs.sent(s1,concept1,concept2);
      
             ch.chunk(s4);
            sd.div();
            ct.trip(concept1,concept2);
            rg.rel(concept1,concept2,bw);
                
           }    
           
                         
    bw.close();    
            
        FileReader fr2=new FileReader(s5);
            BufferedReader br2=new BufferedReader(fr2);
            
             rs.rel_set(br2);
             
              System.out.println("\n\nProgram Executed Successfully");
            }
            catch(Exception E)
            {
                System.out.println(E);
            }
    }
}
