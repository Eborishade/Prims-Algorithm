import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

/**
 * @since April 15, 2020
 * @author Emmanuel Borishade
 * 
 * Program Description: Minimum Spanning Tree Calculator
 * 
 * Program Objective: Calculate minimum spanning tree using Prim's
 * algorithm and return result (toString)
 */
public class CalcSpanTree{
    EGraph graph;
    PriorityQueue<Edge210> pq;
    int startVertex;
    Integer[] parent;
    Double[] cost;
    boolean[] knownVertices;


    /**
     * ctor, Uses int as starting vertex in calculating Minimum Spanning tree of Provided Graph
     * @param EGraph
     * @param Integer 
     */
    public CalcSpanTree(EGraph graph, int vertex){
        this.graph = graph;
        this.graph.normalizeEdges();
        EdgeCostComparator cmp = new EdgeCostComparator();
        final int initial_capacity = graph.numVerts();
        pq = new PriorityQueue<Edge210>(initial_capacity, cmp); 
        startVertex = vertex;

        calculate();
    }

    
    /**
     * Use Prim's Algorithm to calculate min Spanning tree
     */
    private void calculate(){

        //create arrays to hold final data
        
        //note: +1 prevents potential index not found error.
        //Error is due to certain graphs not utilizing a 0 vertex.
        knownVertices = new boolean[graph.numVerts()+1];
        cost = new Double[graph.numVerts()+1];
        parent = new Integer[graph.numVerts()+1]; 
        
        //place-keeping variables
        int currentVertex = -1; 
        int connectedVertex = -1;
        int knownCount = 0; //to forcibly end while
        List<Edge210> connectedEdges;
        Edge210 currentEdge = new GraphEdge();


        //set all costs to infinity
        for (int x = 0; x <cost.length; x++){
            cost[x] = Double.POSITIVE_INFINITY;
        }
        //set path & cost for starting Vertex
        cost[startVertex] = 0.0;
        parent[startVertex] = startVertex;


        //Prim's Algorithm
    
        //add all edges connected to start vertex to PriorityQueue
        connectedEdges = graph.edges(startVertex);
        for (Edge210 edge : connectedEdges){
            pq.add(edge);
        }

        /*
        Algorithm:
            ...pop minimum cost edge
            mark parent vert as known
            search all other connected edges and update costs and parent paths
            add children of connected edges to PriorityQueue, then...
        */
        while(!pq.isEmpty() && knownCount <= graph.numVerts()){
            
            //pop minimum cost vertex and begin searching edges
            currentVertex = checkPoll(pq, currentEdge); //checkPoll returns valid vertex from pq

            //mark known
            knownVertices[currentVertex] = true;
            knownCount++;

            //update all connected edges
            connectedEdges = graph.edges(currentVertex);
            for (Edge210 edge: connectedEdges){
                //get vertex on other end of edge, if vertex is unknown and path cost is less, update.
                connectedVertex = edge.otherVert(currentVertex); 
                if ((!knownVertices[connectedVertex]) && (edge.getWeight() < cost[connectedVertex])   ){
                    cost[connectedVertex] = edge.getWeight();
                    parent[connectedVertex] = currentVertex;
                    
                    //if unknown, add to pq to be updated 
                    pq.add(edge);
                }
            }
        }
    }


    /**
     * Ensures valid Edge is chosen, returns valid vertex
     * @param pq
     * @param currentEdge
     * @return int
     */
    private int checkPoll(PriorityQueue<Edge210> pq, Edge210 currentEdge){
        int currentVertex = 0; 

        if (pq.peek()==null){
            return currentVertex;
        }
        /*
            note: there are cases when edge provided has a Vert1 that is already known,
            in which case v2 is the desired vertex. 
            But if they are both known, then a new edge must be chosen.
        */
        currentEdge = pq.poll(); //passed currentEdge is overriden by new poll.
        

        //if both vertices are known
        if (knownVertices[currentEdge.getVert1()] && knownVertices[currentEdge.getVert2()]){
            currentVertex = checkPoll(pq, currentEdge);//ensures poll chooses new current edge
        }
        //if one known
        else if (knownVertices[currentEdge.getVert1()]){
            currentVertex = currentEdge.getVert2();
        }
        else if (knownVertices[currentEdge.getVert2()]){
            currentVertex = currentEdge.getVert1();
        }

        //if none are known and v1 = starting. 
        else if (currentEdge.getVert1()==startVertex){
            currentVertex = currentEdge.getVert1();
        }
        return currentVertex;
    }


    /**
     * Returns Minimum Spanning Tree Paths to String
     */
    public String toString(){
        StringBuilder returnable = new StringBuilder("");
        int x = 0;
        //written as while loop for potential additional condition: && parent[x] != null. removes extra slot
        while(x <parent.length){//extra slots in paths array printed as null
            returnable.append("Vertex: "+x+"  Parent: "+parent[x]+" Cost: "+cost[x]+"\n");
            x++;
        }
        return returnable.toString();
    }


    /**
     * Inner class for comparing edges by weight. 
     * Note: weights are doubles, yet results are truncated ints, so there may be some discrepancy.
     * Requires further testing.
     */
    private class EdgeCostComparator implements Comparator<Edge210> {
        @Override
        public int compare(Edge210 e1, Edge210 e2) {
            //double comparison from https://stackoverflow.com/questions/4242023/comparator-with-double-type
            if (e1.getWeight() < e2.getWeight()){return -1;}
            if (e1.getWeight() > e2.getWeight()){return 1;}
            return 0;

        }
    }     
}
