import java.text.DecimalFormat;
import java.util.*;


public class NetworkAnalysis{
    private static Scanner reader = new Scanner(System.in);
    private static int count;
    private static boolean[] marked;
    private static DijkstraSP[] all;

    public static void main(String[] args){
        graph g = new graph(args[0]);
        makeAllPairs(g);//stores the shortest paths of each pair in an array
        int options = -1;

        while(options != 0){
            options = inputValidation(options);

            switch(options){
                case 0:
                    break;
                case 1:
                    lowestLatencyPath(g);
                    break;
                case 2: 
                    copperOnly(g);
                    break;
                case 3:
                    twoVertexFail(g);
                    break;
                default:
                    System.out.println("\nNot a valid number!\n");
            }
        }
    }
    
    public static void lowestLatencyPath(graph g){//uses the books Dijkstra implentation to find the shortest paths
        int lowest = Integer.MAX_VALUE;
        int start = validateVertex("\nEnter the first vertex: ", g);
        int end = validateVertex("\nEnter the second vertex: ", g);
        double latency = 0;
        DecimalFormat lat = new DecimalFormat("#.00");
        String s = "";

        if(start != end){
            if(hasPath(start, end)){
                for(edge e: path(start, end)){
                    s = e.getStart() + "->" + s;
                    latency += e.getTime();
                    if(e.getBandwidth() < lowest){
                        lowest = e.getBandwidth();
                    }
                }
                System.out.println("\nPath: "+s+end);
                System.out.println("\nThe minimum bandwidth of all the edges in the path from " + start 
                + " to " + end + " is \n" + lowest + "Gb/s with a latency of " + lat.format(latency) + " ns\n");
            }else{
                System.out.println("\nNo path exists!\n");
            }
        }else{
            System.out.println("\nPath: " + start);
            System.out.println("\nThe minimum bandwidth of all the edges in the path from " + start 
            + " to " + end + " is \n0Gb/s with a latency of 0.00 ns\n");
        }
    }

    public static void copperOnly(graph g){//uses DFS to traverse the tree 
        marked = new boolean[g.getV()];
        count = 0;
        count = dfsCopper(g,0);

        if(count == g.getV()){
            System.out.println("\nThe graph is copper-only connected.\n");
        }else{
            System.out.println("\nThe graph is not copper-only connected.\n");
        }
    }

    public static void twoVertexFail(graph g){//uses DFS to traverse the tree
        marked = new boolean[g.getV()];
        boolean fail = false;
        for(int i = 0; i < g.getV() - 1; i++){//double loop to go through all the possible vertices
            for(int j = i+1; j  < g.getV(); j++){
                marked = new boolean[g.getV()];//reset after every go
                count = 0;
                marked[i] = true;//mark the visited ones 
                marked[j] = true;
                int v = 0;
                for(int k = 0; k<marked.length;k++){//starting point for DFS cant be a marked vertex
                    if(marked[k] == false){
                        v = k;
                        break;
                    }
                }

                dfsVertexFail(g, v);//dfs

                for(int k = 0; k<marked.length;k++){//if any of the vertices are not marked then the graph is not connected
                    if(marked[k] == false){
                        fail = true;
                        System.out.println("\nThe graph is not connected when vertex " + i + " and " + j + " are gone.\n");
                        break;
                    }
                }
            }
            if(fail){//stop loop when it finds one 
                break;
            }
        }
        if(!fail){
            System.out.println("\nThe graph is connected.\n");
        }
    }

    private static int inputValidation(int options){
        do{//input validation for option choice
            System.out.print("\n1. Find Lowest Latency Path\n2. Check if graph is copper-only connected\n"+
            "3. Determine a disconnection\n0. Exit\nWhich would you like to do: ");
            if(reader.hasNextInt()){
                options = reader.nextInt();
                break;
            }else{
                reader.nextLine();
                System.out.println("\nNot an integer!\n");
            }
        }while(true);

        return options;
    }   
    
    private static void makeAllPairs(graph g){//methods from book that implements the pairs of shortest paths
        all  = new DijkstraSP[g.getV()];
        for (int v = 0; v < g.getV(); v++)
            all[v] = new DijkstraSP(g, v);
    }

    private static Iterable<edge> path(int s, int t) {
        validateVertex(s);
        validateVertex(t);
        return all[s].pathTo(t);
    }

    private static boolean hasPath(int s, int t) {
        validateVertex(s);
        validateVertex(t);
        return dist(s, t) < Double.POSITIVE_INFINITY;
    }

    private static double dist(int s, int t) {
        validateVertex(s);
        validateVertex(t);
        return all[s].distTo(t);
    }

    private static void validateVertex(int v) {
        int V = all.length;
        if (v < 0 || v >= V)
            throw new IllegalArgumentException("vertex " + v + " is not between 0 and " + (V-1));
    }

    private static void dfsVertexFail(graph g, int v){//DFS for two vertex fail
        count++;
        marked[v] = true;

        for (edge e : g.adj(v)) {
            int w = e.getStart();
            if (!marked[w]) {
                dfsVertexFail(g, w);
            }
        }
    }

    private static int validateVertex(String prompt, graph g){//input validation for vertex
        int i;
        do{
            reader.nextLine();
            System.out.print(prompt);
            if(reader.hasNextInt()){
                i = reader.nextInt();
                if(i < g.getV() && i >= 0){
                    break;
                }else{
                    System.out.println("\nNot a vertex!\n");
                }
            }else{
                System.out.println("\nNot a number!\n");
            }
            
        }while(true);
        return i;
    }

    private static int dfsCopper(graph G, int v) {//special dfs to see if the edge is copper
        count++;
        marked[v] = true;
        for (edge e : G.adj(v)) {
            int w = e.getStart();
            if (!marked[w] && e.getMaterial().equals("copper")) {
                dfsCopper(G, w);
            }
        }
       return count;
    }
}