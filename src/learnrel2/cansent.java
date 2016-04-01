package learnrel2;

import java.io.FileNotFoundException;
import java.util.NoSuchElementException;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author staff
 */

import java.io.*;
//import java.net.*;
import java.util.StringTokenizer;
import java.util.*;
import java.util.*;
import edu.stanford.nlp.pipeline.*;
import edu.stanford.nlp.ling.*;
import edu.stanford.nlp.ling.CoreAnnotations.*;
import edu.stanford.nlp.util.CoreMap;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;

public class cansent {
    public static void sent(String s1, String concept1, String concept2) throws FileNotFoundException, NoSuchElementException{
      try
      {
        //String line1, line,concept1,concept2, subject="", object="", verb="",str1;
            String line1, line, subject="", object="", verb="",str1;
      //    String s1="result/temp/tripcm.txt";
            
       // String s1="data/in1.txt";
        /*    String s2="data/concept1.txt";    
            String s3="data/concept2.txt";
            String s4="result/temp/cansentcm.txt";
           // FileReader fr=new FileReader(s1);
           // BufferedReader br=new BufferedReader(fr);
               FileReader fr1=new FileReader(s2);
            BufferedReader br1=new BufferedReader(fr1);
               FileReader fr2=new FileReader(s3);
            BufferedReader br2=new BufferedReader(fr2); */
            
              String s4="result/temp/cansentcm.txt";
            FileWriter fw=new FileWriter(s4);
             BufferedWriter bw=new BufferedWriter(fw);
            
          //   concept1=lem(br1.readLine());
         //   concept2=lem(br2.readLine());
          //  System.out.println("concept1 "+ concept1);
           //         System.out.println("concept2 "+ concept2);
       /*    while((concept1=br1.readLine())!=null&&(concept2=br2.readLine())!=null)
           { */
         //      System.out.println("concept1 "+ concept1);
           //    System.out.println("concept2 "+ concept2);
         //      concept1=lem(concept1);
           //    concept2=lem(concept2); 
               
      /*          String concept1, concept2;
               System.out.println("concept1 "+ con1);
               System.out.println("concept2 "+ con2);
               concept1=lem(con1);
               concept2=lem(con2);  */
               concept1=concept1.toLowerCase();
               concept2=concept2.toLowerCase();
                 FileReader fr=new FileReader(s1);
            BufferedReader br=new BufferedReader(fr);
            while((line1=br.readLine())!=null)
            {
           subject="";verb="";object="";
                   line1=line1.toLowerCase();
                    if((line1.contains(concept1)&&(line1.contains(concept2)))||(line1.contains(concept2)&&(line1.contains(concept1))))
                    {
                  //      System.out.println("subject "+ subject);
                    //    System.out.println("verb "+ verb);
                      //  System.out.println("object "+ object);
                        bw.write(line1+"\n");
                    }
                        
                    
            }
   //        }
             
             bw.close();
      }
      catch(Exception e){System.out.println(e);}
}
     public static String lem(String str) throws FileNotFoundException, IOException
        {
        Properties props = new Properties();
        props.put("annotators", "tokenize, ssplit, pos,lemma,");
        StanfordCoreNLP pipeline = new StanfordCoreNLP(props, false);
        
                Annotation document = pipeline.process(str);
                String lemma=null;
                for (CoreMap sentence : document.get(SentencesAnnotation.class)) 
                    for (CoreLabel token : sentence.get(TokensAnnotation.class)) 
                    {
                        lemma = token.get(LemmaAnnotation.class);
                
                    }                
               return lemma;
     }
}
       
