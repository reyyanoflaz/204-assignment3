I. File list
------------
Assingment3.java
Bag.java
Edge.java
FileManager.java
Graph.java
IndexMinPQ.java
ManipulateGraph.java
PrimMST.hava
Queue.java
ST.java
SymbolGraph.java


II. Design
----------
Algorithms, 4th edition, Robert Sedgewick and Kevin Wayne, the Java source code 
for algorithms and clients in the tutorial was used to build the Graph structure.



III. Compilation
-----------
javac Assignment3.java
java Assignment3 wordVec.txt sample1_word_pairs.txt clusters.txt 1


VI. Algorithm Analysis
------------------------------


In order to construct weighted Graph,I use symbol Graph that has word-index pair for every vertex,once symbol 
constructed it is easy to use wordVec and wordpair file for vertex match.

CalcCosineSim() function calculates consine similarty between word pairs and construct Graph with related to it.
My algorithm only constructs weigted Graph,PrimMst implemented but not used.

