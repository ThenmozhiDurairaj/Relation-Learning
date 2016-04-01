

package learnrel2;

import edu.stanford.nlp.parser.lexparser.LexicalizedParser;
import edu.stanford.nlp.trees.PennTreebankLanguagePack;
import edu.stanford.nlp.trees.Tree;
import edu.stanford.nlp.trees.TreePrint;
import edu.stanford.nlp.trees.TreebankLanguagePack;
import edu.stanford.nlp.pipeline.*;
import java.io.*;
import java.util.StringTokenizer;
import java.lang.Object;
import java.util.AbstractList;


public class chunker {

    private void typedef(LexicalizedParser lp, String sent, BufferedWriter out2, BufferedWriter out3) throws IOException, ClassNotFoundException {

        Tree parse = lp.apply(sent);//class(tree) obj-parse      
        TreebankLanguagePack t = new PennTreebankLanguagePack();
        TreePrint p = new TreePrint("penn", "removeTopBracket", t);//write the tree in the file
        PrintWriter out = new PrintWriter(new FileWriter("result/temp/chunkin.txt"));
        p.printTree(parse, out);
        out.close();
        //headwords
    //    FileInputStream fstream = new FileInputStream("E:/Research/Programs/Chunker/src/Chunker/in.txt");
            FileInputStream fstream = new FileInputStream("result/temp/chunkin.txt");
        //BufferedWriter out1 = new BufferedWriter(new FileWriter("E:/Research/Programs/Chunker/src/Chunker/out.txt", true));
        BufferedWriter out1 = new BufferedWriter(new FileWriter("result/temp/chunkout.txt", true));
        
       
        
        DataInputStream in = new DataInputStream(fstream);
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        
        String strLine, chk;
        String posTag = null;
        // Read File Line By Line
        while ((strLine = br.readLine()) != null) {
            StringTokenizer st = new StringTokenizer(strLine);
            if (st.countTokens() >= 2) { //morethan 1
                String key = st.nextToken();
                
                key = key.replace("(", " ");                
                         
                String key1 = null;
                String prev = null;
                while (st.hasMoreTokens()) {
                    prev = key1;
                  
                    key1 = st.nextToken(); //(PRP
                    key1 = key1.replace("(", " ");
                    key1 = key1.replace(")", " ");
                    //key1 = key1.replace("CC", "");
                 
                }
                 // System.out.print(prev);
                if (prev == null) {
                    prev = key;
                }
         //        System.out.print(" " + key.trim());     
       //          out2.write(" " + key.trim());

                if (!key1.trim().equals(",") && !key.trim().equals(",")) {
                  
                   //System.out.println(" " + key1.trim());                   
          //          System.out.print(" " + key1.trim());                   
                    String output = key1.trim() + " " + key.trim() + " " + prev;
                    chk = key.trim() + " " + key1.trim() + " ";
                            
                    out1.write(output + "\n");
                 //   chk=chk.replace("CC", "");
                    out2.write(chk);
                    char c;
                //    String h="Hello";
                    //c=key1.trim().charAt(0);
                    c=key1.trim().charAt(0);
                    if((Character.isUpperCase(c))&&key.trim().equals("NP")&&!key1.trim().equals("He"))
                    {
                        out3.write(key1.trim()+"\n");
                    }
                    
                }
            }
            //out2.write("\n");

        }//key1=head,key-phrase,prev-pos
        out1.close();
        
    }

    public void chunk(String s1) throws IOException, ClassNotFoundException {
        try{
        LexicalizedParser lp = LexicalizedParser.loadModel("edu/stanford/nlp/models/lexparser/englishPCFG.ser.gz"); 
     
        chunker p = new chunker();
//System.out.println("Called chunker "+s1);
        //FileInputStream fstream = new FileInputStream("E:/Research/Programs/Chunker/src/Chunker/in1.txt");
  FileReader fr=new FileReader(s1);
            BufferedReader br=new BufferedReader(fr);
      //  FileInputStream fstream = new FileInputStream(s1);
      //  DataInputStream in = new DataInputStream(fstream);
      //  BufferedReader br = new BufferedReader(new InputStreamReader(in));   
         BufferedWriter out2 = new BufferedWriter(new FileWriter("result/temp/chunk.txt"));
         BufferedWriter out3 = new BufferedWriter(new FileWriter("result/temp/ne.txt"));
        String strLine;
        // Read File Line By Line
 //       System.out.println("\n"+br.readLine());
        while ((strLine = br.readLine()) != null) {
  //          System.out.println("Input texts::" + strLine);
        //    System.out.println("entered chunking process");
            //out2.write("\n");
            strLine=strLine.replace("'s", "");
                    strLine=strLine.replace("n't", " not");
                    strLine=strLine.replace("&", "and");
                    strLine=strLine.replace("'m", " am");
                    strLine=strLine.replace("\"", "");
                    strLine=strLine.replace("'", "");
            p.typedef(lp, strLine, out2, out3); 
            out2.write("\n");
        }    
        
        out2.close();
        out3.close();
        uniquene();
        }
        catch(Exception e){System.out.println(e);}
        }
    
    public static void uniquene()
    {
        try{
      int i,count=0,l,m,s=0,r;
        String str,str2="";
        String str1[]=new String[1000];

        String freq1[]=new String[1000];

        FileReader fr=new FileReader("result/temp/ne.txt");
                BufferedReader br=new BufferedReader(fr);

        FileWriter fw=new FileWriter("result/temp/neset.txt");
             BufferedWriter bw=new BufferedWriter(fw);
   
                str=br.readLine();
                i=0;
                while((str)!=null)
                {
                str1[i]= str;
                i++;
                str=br.readLine();
                }
            
                //for(int g=0;g<i;g++)
                  //  System.out.println(str1[g]);
                l=0;
                m=0;
                r=0;
                int flag;
                freq1[0]=str1[0];
                count=1;
                int x=1;
                while(x<i)
                {
                    int y=0;
                    flag=0;
                    while(y<i)
                    {
        
                        if(str1[x].equalsIgnoreCase(freq1[y]))
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
                        freq1[++l]=str1[x];
        
                    }
                    x++;
        
                }       
        
                for(int g=0;g<=l;g++)
                {
                   //   System.out.println(g+" "+freq1[g]);
                      bw.write(freq1[g]+"\n");
                }
                             
                    bw.close();
            br.close();


      }

      catch(Exception e){System.out.println(e);}
        
    }
    

    }

