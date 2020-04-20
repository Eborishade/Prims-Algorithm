import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * @since April 17, 2020 
 * @author Emmanuel Borishade
 * 
 * Program Description: Virtual Graph Representor
 * 
 * Program Objective: 
 *  -Represent an undirected graph as adjacency list/matrix
 *  -vertices connected by weighted and unweighted edges
 *
 */
public class EGraph implements Graph210 {
    ArrayList<AdjacencyList> vertexList;
    List<Edge210> edgeList;
 

    /**
     * Create Graph represented by Adjacency List
     */
    public EGraph(){
        vertexList = new ArrayList<AdjacencyList>();
        edgeList = new ArrayList<Edge210>();
    }


    /**
     * Returns the number of vertices in the graph.
     * @return Returns the number of vertices
     */
    public int numVerts(){
        return vertexList.size(); 
    }


    /**
     * Returns the number of edges in the graph.
     * @return int
     */
    public int numEdges(){
        edgeList = allEdges();
        return edgeList.size();
    }


    /**
     * Adds an edge to the graph.
     * @param v1 is vert1, from vert
     * @param v2 is vert2, to vert
     * @param weight Edge weight
     * @return Returns the edge just added; on error, null is returned
     */
    public Edge210 addEdge(int v1, int v2, double weight){
        Edge210 newEdge = new GraphEdge(v1, v2, weight);
        boolean v1Present = false;
        boolean v2Present = false;
        AdjacencyList newVertex;

        //check to see if v1 is present in list of AdjacencyLists, adds edge to vertex. O(n)
        for(AdjacencyList vertex: vertexList){
            if (vertex.getVertex() == v1){
                vertex.add(newEdge);
                v1Present = true;
            }
            //if v2 is the already in the list, but v1 isn't then add vertex, but also make new entry for v1
            //note: making a second entry will create duplicates, but will allow searching for any vertex using edges(v)
            if (vertex.getVertex() == v2){
                vertex.add(newEdge);
                v2Present = true;
            }
        }

        //if vertex is not found in list, add new vertices for v1 & v2 and add edge
        if (!v1Present){
            newVertex = new AdjacencyList(v1); 
            newVertex.add(newEdge);
            vertexList.add(newVertex);
        }
        if (!v2Present){
            newVertex = new AdjacencyList(v2); 
            newVertex.add(newEdge);
            vertexList.add(newVertex);
        }
        return newEdge;
    }


    /**
     * Returns a list of edges connected to the vertex.
     * @param v The vertex
     * @return Returns a list of edges connected to the vertex; null is never returned
     * an empty list is returned if 1) no edges, or 2) a bad vert is specified; 
     */
    public List<Edge210> edges(int v){
        List<Edge210> connectedEdges = new ArrayList<Edge210>();

        //Warning: O(n)  
        for(AdjacencyList vertex: vertexList){
            if (v == vertex.getVertex() ){
                connectedEdges = vertex.getEdges();
            }
        }      
        return connectedEdges;
    }


    /**
     * Returns a list of all edges in the graph.
     * @return Returns a list of all edges; never null
     */
    public List<Edge210> allEdges(){
        List<List<Edge210>> all = new ArrayList<List<Edge210>>();
        List<Edge210> returnable = new ArrayList<Edge210>();

        //Add every list of edges to one big list. Warning: O(n)
        for(AdjacencyList vertex: vertexList){
            all.add(vertex.getEdges());
        }
        //for every list of edges, add each edge to new list returnable.
        //Warning: O(n^2)!
        for (List<Edge210> twoDList: all){
            //prevents duplicate edges from being returned. duplicate edges are guarenteeed to be present by addEdges()
            for(Edge210 edge: twoDList){
                if (!returnable.contains(edge)){ //ArrayList.contains utilizes .equals from Object, overwritten in GraphEdge
                    returnable.add(edge);
                }
            }
        }

        return returnable;
    }


    /**
     * Normalizes all edges numerically.
     */
    public void normalizeEdges(){
        /*
        Two steps: 1) Normalize each edge in the graph, and 2) Properly sort all edges.
        We do this so that we can (easily) compare two normalized graphs
        */
        edgeList = allEdges();

        for(Edge210 edge: edgeList){
            edge.normalize();
        }
        //Comparator lambda: https://www.techiedelight.com/sort-list-of-objects-using-comparator-java/
		Collections.sort(edgeList, new Comparator<Edge210>() {
			@Override
			public int compare(Edge210 p1, Edge210 p2) {
				return p1.getVert1() - p2.getVert1();
			}
		});
    }


    /**
     * Return graph to String.
     * @return String
     */
    public String toString(){
        edgeList = allEdges();
        StringBuilder returnable = new StringBuilder("");

        for (Edge210 edge: edgeList){
            returnable.append(edge);
            returnable.append("\n");
        }
        
        return returnable.toString();
    }
}



