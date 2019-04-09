import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.AbstractMap.SimpleEntry;
import java.util.stream.Collectors;

public class ManipulateGraph {
	protected SymbolGraph symbolGraph;				//aggreation
	protected Graph GraphObject;					//aggreation
	protected PrimMST primMSTObj;
	protected FileManager fileObj;					//aggreation
	protected ArrayList<String> wordVectorFile;
	protected ArrayList<String> wordPairsFile;
	protected AbstractMap.SimpleEntry<String, String> word1word2Pair;
	public ManipulateGraph() {
		this.symbolGraph = new SymbolGraph();
		this.fileObj=new FileManager();
	}
	protected void setSymbolGraphSize(){
		this.symbolGraph.stSize=this.wordVectorFile.size();
	}
	/*
	 * read file and prevent empty lines when reached end of line
	 */
	 protected ArrayList<String> readFile(String txt) throws IOException,FileNotFoundException,UncheckedIOException {
		 this.fileObj.buffReadManager=this.fileObj.openFile(txt);
		 return this.fileObj.buffReadManager.lines().map(line -> line.trim()).filter(line -> !line.isEmpty()).collect(Collectors.toCollection(ArrayList::new));
	 }
	 /*
	  * trims line of file and trims vectors of ST key words
	  */
	 protected List<Double> trimVector(String s) {
		 String trimmed=s.contains(" ") ? s.substring(s.indexOf(' ')).trim() : "";
		 return Arrays.stream(trimmed.split(" ")).map(Double::parseDouble).collect(Collectors.toList());
	 }
	 /*
	  * constructs symbol graph from vector file and weighted Graph from word pair file
	  */
	 public void constructSymbolGraphFromFile(){
		 for (ListIterator<String> iterator = wordVectorFile.listIterator(); iterator.hasNext();) {
			 String line = iterator.next();
			 String withoutQuotes_word = line.split(" ")[0].trim().replaceAll("\"", "");
			 if (!this.symbolGraph.st.contains(withoutQuotes_word)){
				 this.symbolGraph.st.put(withoutQuotes_word, iterator.previousIndex());
				 this.symbolGraph.st.putWordVectortoST(iterator.previousIndex(), this.trimVector(line));
			 }
		 }
		 this.symbolGraph.keys = new String[this.symbolGraph.stSize];		//prevent outofbound exception
		 for (String name : this.symbolGraph.st.keys()) {
			 this.symbolGraph.keys[this.symbolGraph.st.get(name)] = name;
		 }
		 
		 this.symbolGraph.constructgraph();
		 this.GraphObject=this.symbolGraph.graph;
		 for (ListIterator<String> iterator = wordPairsFile.listIterator(); iterator.hasNext();) {
			 String line = iterator.next();
			 String vertex1=line.split("-")[0];
			 String vertex2=line.split("-")[1];
			 if( this.symbolGraph.contains(vertex1) && this.symbolGraph.contains(vertex2)){
				 this.GraphObject.addEdge(new Edge(this.symbolGraph.indexOf(vertex1), this.symbolGraph.indexOf(vertex2), calculateConsineSim(vertex1,vertex2)));
			 }
		 }

	}
	private double calculateConsineSim(String vertex1, String vertex2) {
		List<Double> vec1=this.symbolGraph.st.wordvectors.get(this.symbolGraph.indexOf(vertex1));
		List<Double> vec2=this.symbolGraph.st.wordvectors.get(this.symbolGraph.indexOf(vertex2));
		double VectWord1Word2 = 0.0000000;
		double VectWord1_Sq = 0.0000000;
		double VectWord2_Sq = 0.0000000;
		int i;
		for(i=0;i<vec1.size();i++){
			VectWord1Word2+=vec1.get(i)*vec2.get(i);
			VectWord1_Sq+=Math.pow(vec1.get(i),2);
			VectWord2_Sq+=Math.pow(vec2.get(i),2);
		}
		return VectWord1Word2/(Math.sqrt(VectWord1_Sq)*Math.sqrt(VectWord2_Sq));
	}
	
}
