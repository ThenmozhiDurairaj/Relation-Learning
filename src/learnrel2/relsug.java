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

public class relsug {
    //public static void rel(String con1, String con2, BufferedWriter bw, String relation[], int n[]) throws FileNotFoundException, NoSuchElementException{
       public static void rel(String con1, String con2, BufferedWriter bw) throws FileNotFoundException, NoSuchElementException{
      try
      {
        //String line1, line,concept1,concept2, subject="", object="", verb="",str1;
            String line1, line, subject="", object="", verb="",str1;
            int i=0, j=0;
            String vb1[]=new String[1000];
            String vb2[]=new String[1000];
          String s1="result/temp/cantripcm.txt";
     /*       String s2="data/concept1.txt";
            String s3="data/concept2.txt";
            String s4="result/temp/cantripcm.txt";
           // FileReader fr=new FileReader(s1);
           // BufferedReader br=new BufferedReader(fr);
               FileReader fr1=new FileReader(s2);
            BufferedReader br1=new BufferedReader(fr1);
               FileReader fr2=new FileReader(s3);
            BufferedReader br2=new BufferedReader(fr2); */
            
   /*          String s4="result/temp/relation.txt";
            FileWriter fw=new FileWriter(s4);
             BufferedWriter bw1=new BufferedWriter(fw);  */
            
          String concept1, concept2;
      //         System.out.println("concept1 "+ con1);
      //         System.out.println("concept2 "+ con2);
               concept1=lem(con1);
               concept2=lem(con2);
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
                       verb=verb.replace(" among", "");
                       verb=verb.replace("can be", "is");
                       verb=verb.replace("may be", "is");
                                              
                       subject=lem(subject.toLowerCase());
                       object=lem(object.toLowerCase());
                       verb=verb.toLowerCase();
                    
                    StringTokenizer st1=new StringTokenizer(verb," ");
                     st1.nextToken();
                                
                   
                    if(concept1.contains(subject)&&(concept2.contains(object)))
                    {
                  /*      System.out.println("subject "+ subject);
                        System.out.println("verb "+ verb);
                        System.out.println("object "+ object);*/
                        if (verb.equals("is ")||verb.equals("was ")||verb.equals("are ")||verb.equals("were "))
                     //   if (verb.startsWith("is")||verb.startsWith("was")||verb.startsWith("are")||verb.startsWith("were"))
                        {
                            verb="isA";
                        vb1[i++]=verb;
                        }
                 //       else if(verb.endsWith("for ")||verb.endsWith("in ")||verb.endsWith("with ")||verb.endsWith("by ")||verb.endsWith("on ")||verb.endsWith("from ")||verb.endsWith("to ")||verb.endsWith("as "))
                        else  if(st1.hasMoreTokens())
                            vb1[i++]=(verb);
                        else
                            vb1[i++]=lem(verb);
                  //      bw.write("C1:"+concept1+" "+"C2:"+concept2+" "+"R:"+verb+" "+"I:---"+"\n");
                    }
                    if(concept1.contains(object)&&(concept2.contains(subject)))
                    {
              /*          System.out.println("subject "+ subject);
                        System.out.println("verb "+ verb);
                        System.out.println("object "+ object);*/
                         if (verb.equals("is ")||verb.equals("was ")||verb.equals("are ")||verb.equals("were "))
                       // if (verb.startsWith("is")||verb.startsWith("was")||verb.startsWith("are")||verb.startsWith("were"))
                         {
                            verb="isA";
                        vb2[j++]=verb;
                        }
                    //     else if(verb.endsWith("for ")||verb.endsWith("in ")||verb.endsWith("with ")||verb.endsWith("by ")||verb.endsWith("on ")||verb.endsWith("from ")||verb.endsWith("to ")||verb.endsWith("as "))
                         else  if(st1.hasMoreTokens())
                             vb2[j++]=(verb);
                         else
                            vb2[j++]=lem(verb);
                    //    bw.write("C1:"+concept1+" "+"C2:"+concept2+" "+"R:---"+" "+"I:"+verb+"\n");
                    }  
                    
            }
            int k;
            String rel="", inv="", lbl="";
     /*       for (k=0;k<i;k++)
                System.out.println("Verb 1:"+vb1[k]);
            for (k=0;k<j;k++)
                System.out.println("Verb 2:"+vb2[k]); */
            rel=label(vb1,i);
            inv=label(vb2,j);
           
            
          //  lbl="C1:"+concept1+" "+"C2:"+concept2+" "+"R:"+rel+" "+"I:"+inv+"\n";
          //  System.out.println("C1:"+concept1+" "+"C2:"+concept2+" "+"R:"+rel+" "+"I:"+inv+"\n");
            lbl="C1:"+con1+" "+"C2:"+con2+" "+"R:"+rel+" "+"I:"+inv+"\n";
      //      System.out.println("C1:"+con1+" "+"C2:"+con2+" "+"R:"+rel+" "+"I:"+inv+"\n");
             bw.write(lbl);
      //       bw1.close();
             //     bw.close();
             br.close();
             
             
     //        br1.close();
     }
      catch(Exception e){System.out.println(e);} 
      
   
}
     
     public static String label(String vb[], int i) throws FileNotFoundException, IOException
        {
            int count=0,l,m,s=0,r;
        String str,str3;
    //    String vb[]=new String[1000];
        int freq[]=new int[1000];
        String freq1[]=new String[1000];
                l=0;
                m=0;
                r=0;
                int flag;
                freq1[0]=vb[0];
                count=1;
                int x=1;
                while(x<i)
                {
                    int y=0;
                    flag=0;
                    while(y<i)
                    {
                        if(vb[x].equalsIgnoreCase(freq1[y]))                        
                        {
                            count++;
                            flag++;
                            y++;
                        }
                        else
                            y++;
                    }
                    if(flag==0)
                        freq1[++l]=vb[x];
                    
                 
                    x++;
                    //count=1;
                }
        //        System.out.println("freq: "+l);
                l=l+1;
        //        System.out.println("unique verbs\n");
   /*            for(int g=0;g<l;g++)
                {
                    System.out.println(freq1[g]);
                    System.out.println(freq[g]);
                } */
                    r=0;
                    int p=0;
                    int q=0;
                    int sum=0;
              //      System.out.println("l"+l+"i"+i);
                        while(q<l)
                        {
                   
                            count=0;
                            p=0;
                            while(p<i)
                            {
                            if(freq1[q].equalsIgnoreCase(vb[p]))
                            {
                             
                                count=count+1;
                             
                            }                                                        
                                p=p+1;
                            }
                            
                            freq[r]=count;
                            sum+=count;
                //            System.out.println(count);
                            r=r+1;
                            q=q+1;
                            
                        }
             //           System.out.println(sum);
            //            System.out.println(l);
                        int ii, jj, temp;
                        String stemp;
   
                        for(ii=0;ii<l-1;ii++)
                        {
                            for(jj=ii+1;jj<l;jj++)
                            {
                                if(freq[ii]<freq[jj])
                                {
                                    temp=freq[ii];
                                    stemp=freq1[ii];
                                    freq[ii]=freq[jj];
                                    freq1[ii]=freq1[jj];
                                    freq[jj]=temp;
                                    freq1[jj]=stemp;
                                }
                            }
                        }
                        int f;
             /*           System.out.println(l);
                        for(f=0;f<l;f++)
                        {
                                   
                             System.out.println(f+" "+freq1[f]+" "+freq[f]+" "+(freq[f]*100.0)/sum);
                        }  */
                        
                        return freq1[0];
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
       
