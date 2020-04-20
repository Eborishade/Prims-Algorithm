import java.util.ArrayList;
import java.util.List;

/**
 * @since April 18, 2020 
 * @author Emmanuel Borishade
 * 
 * Program Description: List of Adjacent Vertices
 * 
 * Program Objective: 
 *  -Hold a list of edges connected to a vertex
 */
public class AdjacencyList {
    int vertex;
    List<Edge210> edges; 


    /**
     * Set vertex for adjacency List
     * @param v
     */
    public AdjacencyList(int v){
        vertex = v;
        edges = new ArrayList<Edge210>();
    }


    /**
     * Add edge to List of connected edges
     * @param edge
     */
    public void add(Edge210 edge){
        edges.add(edge);
    }


    /**
     * Return all Edge Objects as List
     * @return List<Edge210>
     */
    public List<Edge210> getEdges(){
        return edges;
    }


    /**
     * Return number of edges in list
     * @return int
     */
    public int size(){
        return edges.size();
    }


    /**
     * Returns vertex
     * @return int
     */
    public int getVertex(){
        return vertex;
    }


    /**
     * Return all edges to String
     * @return String
     */
    public String toString(){
        StringBuilder returnable = new StringBuilder("");

        for (Edge210 e: edges){
            returnable.append(e);
            returnable.append("\n");
        }
        
        return returnable.toString();
    }

}
