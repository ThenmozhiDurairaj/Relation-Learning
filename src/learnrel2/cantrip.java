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

public class cantrip {
    public static void trip(String concept1, String concept2) throws FileNotFoundException, NoSuchElementException{
      try
      {
        //String line1, line,concept1,concept2, subject="", object="", verb="",str1;
            String line1, line, subject="", object="", verb="",str1;
          String s1="result/temp/tripcm.txt";
     /*       String s2="data/concept1.txt";
            String s3="data/concept2.txt";
            String s4="result/temp/cantripcm.txt";
           // FileReader fr=new FileReader(s1);
           // BufferedReader br=new BufferedReader(fr);
               FileReader fr1=new FileReader(s2);
            BufferedReader br1=new BufferedReader(fr1);
               FileReader fr2=new FileReader(s3);
            BufferedReader br2=new BufferedReader(fr2); */
            
             String s4="result/temp/cantripcm.txt";
            FileWriter fw=new FileWriter(s4);
             BufferedWriter bw=new BufferedWriter(fw);
            
        
          //     System.out.println("concept1 "+ concept1);
         //      System.out.println("concept2 "+ concept2);
               concept1=lem(concept1);
               concept2=lem(concept2);
                 FileReader fr=new FileReader(s1);
            BufferedReader br=new BufferedReader(fr);
            while((line1=br.readLine())!=null)
            {
           subject="";verb="";object="";
            
                StringTokenizer st=new StringTokenizer(line1," ");
                st.nextToken();
                subject=st.nextToken();
                    
                    str1=st.nextToken();
                  
                    while(st.hasMoreTokens())
                    {
                        if((str1.endsWith("NP")))
                             object=str1+" ";
                        else
                            verb=verb+str1+" ";
                        
                        str1=st.nextToken();
                    }
                    object=object+str1;
            
                       subject=subject.replace("NP ", "");
                       object=object.replace("NP ", "");
                       verb=verb.replace("VP ", "");
                       verb=verb.replace("PP ", "");
                       
                       subject=lem(subject.toLowerCase());
                       object=lem(object.toLowerCase());
                       verb=verb.toLowerCase();
                      //      sbar[k]=subject;
                        //    vbar[k]=verb;
                          //  obar[k]=object;
                            //str[i]=subj+" "+obj;
                            //rel[k]=vbar[k].replace("vp ", "");
                            //rel[k]=rel[k].replace("pp ", "");
                    
            /*        System.out.println("subject "+ subject);
                    System.out.println("verb "+ verb);
                    System.out.println("object "+ object);  */
                    if(lem(concept1).equals(lem(subject))&&(lem(concept2).equals(lem(object)))||lem(concept1).equals(lem(object))&&(lem(concept2).equals(lem(subject))))
               //     if(concept1.contains(subject)&&(concept2.contains(object))||concept1.contains(object)&&(concept2.contains(subject)))
                    {
                /*        System.out.println("subject "+ subject);
                        System.out.println("verb "+ verb);
                        System.out.println("object "+ object);*/
                        bw.write("NP "+subject+" "+"VP "+verb+" "+"NP "+object+" "+"\n");
                    }
                        
                    
            }
    //       }
             
             bw.close();
             br.close();
     //        br1.close();
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
       
