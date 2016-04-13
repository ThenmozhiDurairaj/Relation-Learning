/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package learnrel2;


import edu.sussex.nlp.jws.JWS;
import edu.sussex.nlp.jws.Lin;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.util.NoSuchElementException;
import java.util.StringTokenizer;

/**
 *
 * @author staff
 */
public class relset {
     public static void rel_set(BufferedReader br2) throws FileNotFoundException, NoSuchElementException{
      try
      {
          int i=0;
          String line1;
            String rels[]=new String[1000];
            String rels1[]=new String[1000];
             while((line1=br2.readLine())!=null)
           {
            
               StringTokenizer st=new StringTokenizer(line1," ");
   
               String str1="";
               while(st.hasMoreTokens())
               {
               
               str1=st.nextToken();
              
               if (str1.startsWith("R:")||str1.startsWith("I:"))
               {
                   str1=str1.replace("R:", "");
                   str1=str1.replace("I:", "");
                   if (!str1.equals("null"))
                   {
                        rels[i]=str1;
                    i++;
                   }
               }
               }
           
           }
             
            String dir = "WordNet";
            JWS	ws = new JWS(dir, "2.1");
            Lin lin = ws.getLin();
                int l=0;
                int m=0;
                int r=0;
                int flag;
                rels1[0]=rels[0];
                int count=1;
                int x=1;
                while(x<i)
                {
                    int y=0;
                    flag=0;
                    while(y<i)
                    {
                     
                           if((rels[x].equals(rels1[y])))
                           {
                            count++;
                            flag++;
                            y++;
                        }
                        else
                            y++;
                    }
                    if(flag==0)
                    {
                        rels1[++l]=rels[x];
                     
                    }
                     x++;
                    
                }       
                
                                  
              for(int gg=0;gg<l-1;gg++)
                    for (int jj=gg+1; jj<l;jj++)
                        if (lin.max(rels1[gg], rels1[jj], "v")>=0.1)
                            rels1[jj]=rels1[gg];   
     
              System.out.println("Set of Relations\n");
              for(int g=0;g<=l;g++)
                {
                      System.out.println(rels1[g]);
               
                }
      } 
      
      catch(Exception e){System.out.println(e);}
      
     }   
      
 
    

}
