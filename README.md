# Code for learning relation for concept pair

1. To execute in command prompt with a jar file
  
   a. Download [stanford-corenlp-2012-07-06-models.jar](https://github.com/evandrix/stanford-corenlp/raw/master/stanford-corenlp-2012-07-06-models.jar) in **/lib** folder
  
   b. Run the command
	   
	     java –jar LearnRel2.jar data/corpus_sample.txt data/concept_pair_sample.txt result/out_sample.txt

      Agriculture Domain:
	
	     java –jar LearnRel2.jar data/corpus_agri.txt data/concept_pairs_agri.txt result/out_agri.txt

      Computer Domain:

	     java –jar LearnRel2.jar data/corpus_computer.txt data/concept_pairs_comp.txt result/out_comp.txt


(**Note:** The performance may be measured by comparing *out_agri.txt* against *gt_agri.txt* and *out_comp.txt* against *gt_comp.txt*)
