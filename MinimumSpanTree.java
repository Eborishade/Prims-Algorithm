import java.util.Scanner;
public class MinimumSpanTree{
    public static void main(String[] args){
        Scanner scan = new Scanner(System.in);
        String userInput = "";
        int startVertex = -1;

        System.out.println("Prim's Algorithm: Minimum Spanning Tree");
        while(!userInput.equals("exit")){

            //Initialize Graph
            System.out.print("\n\nEnter file path >> ");
            userInput = scan.next();
            GraphReader read = new GraphReader(userInput);
            EGraph graph = read.getGraph(); //crashes program if graph not found

            //get starting Vertex
            while (startVertex > graph.numVerts() || startVertex < 0){
                System.out.print("\nEnter starting vertex :: ");
                startVertex = scan.nextInt();
                if (startVertex > graph.numVerts() || startVertex < 0){
                    System.out.print("Error: Not on graph. Try again.");
                }
            }   

            //Find MinSpanTree, return
            CalcSpanTree minTree = new CalcSpanTree(graph, startVertex); //graph, starting Vertex
            System.out.println(minTree);
            
            while(!userInput.equals("prims") && !userInput.equals("exit")){
                System.out.print("\ntype 'prims' to continue, or type 'help' for more options >>");
                userInput = scan.next();

                if (userInput.equals("help")){
                    System.out.println("\n\n>stats: prints additional developer information for testing");
                    System.out.println(">print: prints out graph");
                    System.out.println(">normalize: normalize graph for better printing");
                    System.out.println(">prims: prints out minimum spanning tree");
                    System.out.println(">help: print commands");
                    System.out.println(">exit: exit program");

                }
                else if (userInput.equals("stats")){
                    System.out.println("Total Vertices: "+graph.numVerts()+" Total Edges: "+graph.numEdges());
                }
                else if (userInput.equals("normalize")){
                    graph.normalizeEdges();
                }
                else if (userInput.equals("print")){
                    System.out.println("\n"+graph);
                }
            }
        }
        System.out.println("Bye!");
        scan.close();
    }
}