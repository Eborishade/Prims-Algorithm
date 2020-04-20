/**
 * @since April 17, 2020 
 * @author Emmanuel Borishade
 * 
 * Program Description: Edge for Virtual Graph
 * 
 * Program Objective: 
 *  Create Edge that connects one or two vertices to the graph
 * 
 */
public class GraphEdge implements Edge210, Comparable<GraphEdge>{

    private int vertex1;
    private int vertex2;
    private double weight;


    /**
     * ctor for weighted connected Edge
     * @param v1
     * @param v2
     * @param w
     */
    public GraphEdge(int v1, int v2, double w){
        vertex1 = v1;
        vertex2 = v2;
        weight = w;
    }


    /**
     * ctor for Unweighted connected Edge
     * @param v1
     * @param v2
     */
    public GraphEdge(int v1, int v2){
        vertex1 = v1;
        vertex2 = v2;
        weight = 1;
    }


    /**
     * ctor for Unweighted unconnected Edge
     * @param v1
     */
    public GraphEdge(int v1){
        vertex1 = v1;
        vertex2 = -1;
        weight = 1;
    }


    /**
     * default ctor, create unweighted disconnected edge
     */
    public GraphEdge(){
        vertex1 = -1;
        vertex2 = -1;
        weight = 1;
    }
 
    
    /**
     * Returns the first "from" vert on the edge.
     * @return Returns the first vert
     */
    public int getVert1(){
        return vertex1;
    }


    /**
     * Returns the second "to" vert on the edge.
     * @return Returns the second vert
     */
    public int getVert2(){
        return vertex2;
    }


    /**
     * Returns the edge weight, a positive number (greater than 0)
     * @return Returns the edge weight
     */
    public double getWeight(){
        return weight;
    }


    /**
     * Returns the "other" vertex in the edge
     * @param v This vert
     * @return Returns the other vert; if v is not on the edge, then vert1 is returned
     */
    public int otherVert( int v){
        if (v == vertex1 && vertex2 > -1){
            return vertex2;
        }

        else if (v == vertex2 && vertex1 > -1){
            return vertex1;
        }

        return vertex1;
    }


    /**
     * Returns true if vert is part of the edge.
     * @param v The vert to check
     * @return Returns true if part of edge
     */
    public boolean hasVert( int v){
        if (v == vertex1){
            return true;
        }

        else if (v == vertex2){
            return true;
        }
        return false;
    }


    /**
     * Normalize the edge by ordering its verts, smallest first.
     * Warning: Only use for UNDIRECTED graphs!
     */
    public void normalize(){
        int temp; 
        if (vertex1 > vertex2){
            temp = vertex2;
            vertex2 = vertex1;
            vertex1 = temp;
        }
        //else already normalized
    }


    //Override methods

    /**
     * Return v1, v2, and Weight to String
     * @return String
     */
    public String toString(){
        return vertex1+" "+vertex2+" "+weight;
    }


    /**
     * Compare two GraphEdge Objects; If vertices and weight are the same, returns true
     * @param GraphEdge
     * @return boolean
     */
    public boolean equals(GraphEdge object2){ 
        if (vertex1 == object2.getVert1() && vertex2 == object2.getVert2() && weight == object2.getWeight() ){
            return true;
        }
        return false;
    }


    /**
     * Compare Vertex to passed object. a.compareTo(b) = -1 
     * @param GraphEdge
     * @return int
     */
    @Override
    public int compareTo(GraphEdge object2) {
        return this.getVert1() - object2.getVert1();	
    }

}


