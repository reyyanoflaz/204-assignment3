import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

/******************************************************************************
 *  @author Robert Sedgewick
 *  @author Kevin Wayne
 */
public class SymbolGraph{
	protected String[] keys;           // index  -> string
    protected Graph graph;             // the underlying graph
    protected int stSize;
    protected ST<String, Integer> st;  // string -> index
    /**  
     * Initializes a graph from a file using the specified delimiter.Each line in the file contains
     * the name of a vertex, followed by a list of the names of the vertices adjacent to that vertex, separated by the delimiter.
     */
    public SymbolGraph() {
        this.st = new ST<String, Integer>();
        // inverted index to get string keys in an array
        this.keys = new String[this.stSize];
        this.graph = new Graph(this.stSize);	
        //TO-DO build connected graph
        // second pass builds the graph by connecting first vertex on each
        // line to all others
    }
    public void constructgraph(){
    	this.graph=new Graph(this.stSize);
    }
    /**
     * Does the graph contain the vertex named {@code s}?
     * @param s the name of a vertex
     * @return {@code true} if {@code s} is the name of a vertex, and {@code false} otherwise
     */
    public boolean contains(String s) {
        return this.st.contains(s);
    }
    /**
     * Returns the integer associated with the vertex named {@code s}.
     * @param s the name of a vertex
     * @return the integer (between 0 and <em>V</em> - 1) associated with the vertex named {@code s}
     */
    public int indexOf(String s) {
        return this.st.get(s);
    }
    /**
     * Returns the name of the vertex associated with the integer {@code v}.
     * @param  v the integer corresponding to a vertex (between 0 and <em>V</em> - 1) 
     * @throws IllegalArgumentException unless {@code 0 <= v < V}
     * @return the name of the vertex associated with the integer {@code v}
     */
    public String nameOf(int v) {
        validateVertex(v);
        return this.keys[v];
    }
    /**
     * Returns the graph assoicated with the symbol graph. It is the client's responsibility
     * not to mutate the graph.
     * @return the graph associated with the symbol graph
     */
    public Graph graph() {
        return this.graph;
    }
	public String[] getKeys() {
		return keys;
	}
    // throw an IllegalArgumentException unless {@code 0 <= v < V}
    private void validateVertex(int v) {
         int V = this.graph.V();
        if (v < 0 || v >= V)
            throw new IllegalArgumentException("vertex " + v + " is not between 0 and " + (V-1));
    }
}
