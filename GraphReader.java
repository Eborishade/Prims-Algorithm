import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * @since April 15, 2020
 * @author Emmanuel Borishade
 * 
 * Program Description: Graph Reader
 * 
 * Program Objective: 
 *  -Read file and make a graph representation out of it
 *
 */
public class GraphReader{
    public String file;
    public EGraph graph;

    /**
     * Ctor, pass local graph file to be used
     * @param f
     */
    public GraphReader(String f){
        reader(f);
    }


    /**
     * Create Object representation of Graph from passed url.
     * @param String url
     */
    private void reader(String url){
        file = url;

        BufferedReader reader;
        String data[];
        int vertex1 = -1;
        int vertex2 = -1; 
        double weight = 1;


        //read file

        //first two lines of file are special. line 1: total num of vertices. line2: total num of edges.
        //total number of edges determines total number of lines in file (plus 2), as well as graph size.
    
        try {
            reader = new BufferedReader(new FileReader(file));
            String line = reader.readLine();//not actually used
            String line2 = reader.readLine();//not actually used
            String otherLines = reader.readLine();//will change in while loop, starts as line 3

            
            //create new Graph and pass all edges in. 
            graph = new EGraph();

            while(otherLines != null){
                data = otherLines.split(" "); //split on space, shouldnt be more than 2 spaces meaning max values
                
                /*
                For future Graphs
                -data[0] will never be null,
                -data[1] may be null if it is a single vertex
                -data[2] may be null if it is unweighted
                -but EGraph won't work unless all three have values.
                */
                
                vertex1 = Integer.parseInt(data[0]);

                if (data[1] != null){
                    vertex2 = Integer.parseInt(data[1]);
                }
                if (data[2] != null){
                    weight = Double.parseDouble(data[2]);
                }

                //pass in to graph
                graph.addEdge(vertex1, vertex2, weight);
                
                otherLines = reader.readLine();
            }   
            reader.close();
        } catch (IOException e) { e.printStackTrace(); }
    
    }


    /**
     * Return Graph
     * @return EGraph graph
     */
    public EGraph getGraph(){
        return graph;
    }


}

