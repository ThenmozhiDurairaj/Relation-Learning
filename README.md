# Code for learning relation for concept pair

1. To execute in command prompt with a jar file
  
   a. Download [stanford-corenlp-2012-07-06-models.jar](https://github.com/evandrix/stanford-corenlp/raw/master/stanford-corenlp-2012-07-06-models.jar) in *\lib* folder
  
   b. Run the command
	   
	     java –jar LearnRel2.jar data/corpus.txt data/concept_pair.txt result/out.txt

      Agriculture Domain:
	
	     java –jar LearnRel2.jar data/corpus_agri.txt data/concept_pairs_agri.txt result/out_agri.txt

      Computer Domain:

	     java –jar LearnRel2.jar data/corpus_computer.txt data/concept_pairs_comp.txt result/out_comp.txt


2. To use the source
  
  	 a. Software Required:  edu.mit.jwi_2.1.4.jar
                         
				edu.sussex.nlp.jws.beta.11.jar
                      
   				jaws-bin.jar
                      
   				stanford-corenlp-2012-07-06-models.jar
                      
   				stanford-corenlp-2012-07-09.jar
    			
				xom.jar
  
 	b. Input: **/data/in.txt**
  
	c. Output: **/result/out.txt**
 
 	d. Run the source file **LearnRel2.java**
