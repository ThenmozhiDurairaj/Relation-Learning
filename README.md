# Code for learning relation for concept pair

1. To execute in command prompt with a jar file
  
   a. Download [stanford-corenlp-2012-07-06-models.jar](https://github.com/evandrix/stanford-corenlp/raw/master/stanford-corenlp-2012-07-06-models.jar) in **/lib** folder
  
   b. Run the command
	   
	     java –jar LearnRel2.jar data/corpus_sample.txt data/concept_pair_sample.txt result/out_sample.txt

      Agriculture Domain:
	
	     java –jar LearnRel2.jar data/corpus_agri.txt data/concept_pairs_agri.txt result/out_agri.txt

      Computer Domain:

	     java –jar LearnRel2.jar data/corpus_computer.txt data/concept_pairs_comp.txt result/out_comp.txt


	(**Note 1:** The performance may be measured by comparing *result/out_agri.txt* against *data/gt_agri.txt* and 	*result/out_comp.txt* against *data/gt_comp.txt*)

2. To visualize the output

   a. Download [Cmap tool](http://cmap.ihmc.us/) 
   
   b. Create new Cmap
   
   c. Enter new proposition by using Cmap List View (e.g. to enter a triple (Pesticide, causes, Pllution), enter Pesticide as concept, causes as Linking Phrase and Pollution as concept)
   
   d. Do required alignment
   
   e. Export Cmap as image file 
   
 (**Note 2:** The visualized form of *out_agri.txt*, *out_comp.txt*, *gt_agri.txt* and *gt_comp.txt* are available in */result* folder)  
   
   	
