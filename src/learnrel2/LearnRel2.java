
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
         //   String s1= "data/in2.txt";
                if (args.length == 0) {

   System.out.println("No Command Line arguments");
   
  } else {
                for (int i = 0; i < args.length; i++) {
                    System.out.println("args[" + i + "]: " + args[i]);}
                }
                    
                    //String s1= "data/"+args[0];
                      String s1= args[0];
             //     String s1= "data/corpus_computer.txt";
        
                //String s2="data/concept_pairs_comp.txt";
                 //String s2="data/"+args[1];
                 String s2=args[1];
      //              String s2="data/concept_pair.txt";
                 
                   String s4="result/temp/cansentcm.txt";
                     
            String concept1,concept2, line1;
            cansent cs=new cansent();
            chunker ch=new chunker();
            sentdiv sd=new sentdiv();
            cantrip ct=new cantrip();
            relsug rg=new relsug();
            
            //String s5="result/temp/rellabel.txt";
            
             //String s5="result/"+args[2];
               String s5=args[2];
        //       String s5="result/out.txt";
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
             //ch.chunk(s1);
             ch.chunk(s4);
            sd.div();
            ct.trip(concept1,concept2);
            rg.rel(concept1,concept2,bw);
                
           }    
            System.out.println("Program Executed Successfully");
                         
    bw.close();    
            
            }
            catch(Exception E)
            {
                System.out.println(E);
            }
    }
}
