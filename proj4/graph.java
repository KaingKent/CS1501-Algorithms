import java.io.*;
import java.util.*;

public class graph{//used the book's graph implementation as reference

    private int V;
    private int E;

    private LinkedList<edge>[] adj;
    
    public graph(String fileName){
        load(fileName);
    }

    @SuppressWarnings("unchecked")
    private void load(String file){
        try{
            BufferedReader graphFile = new BufferedReader( new FileReader( file ) );
            this.E = 0;
            this.V = Integer.parseInt(graphFile.readLine());

            adj = (LinkedList<edge>[]) new LinkedList[V];

            for (int v = 0; v < V; v++) {
                adj[v] = new LinkedList<edge>();
            }

            while(graphFile.ready()){
                String[] info = graphFile.readLine().split(" ");
                add(new edge(info));

                String temp = info[0];//back and forth(full duplex)
                info[0] = info[1];
                info[1] = temp;
                add(new edge(info));

            }

            graphFile.close();
        } catch(FileNotFoundException e) {
            System.out.println("Could not load [" + file + "] file.");
            System.exit(0);
        } catch(IOException e) {
            System.out.println("Error processing [" + file + "] file.");
            System.exit(0);
        }
        
    }

    public void add(edge e){
        int v = e.getStart();
        int w = e.getEnd();
        validateVertex(v);
        validateVertex(w);
        adj[v].add(e);
        adj[w].add(e);
        E++;
    }

    public int getV(){
        return V;
    }

    public int getE(){
        return E;
    }

    public Iterable<edge> adj(int v) {
        validateVertex(v);
        return adj[v];
    }

    public Iterable<edge> edges() {
        HashSet<edge> list = new HashSet<>();

        for (int v = 0; v < V; v++) {
            int selfLoops = 0;
            for (edge e : adj(v)) {
                if (e.other(v) != v) {
                    list.add(e);
                }
                // add only one copy of each self loop (self loops will be consecutive)
                else if (e.other(v) == v) {
                    if (selfLoops % 2 == 0) list.add(e);
                    selfLoops++;
                }
            }
        }
        return list;
    }

    private void validateVertex(int v) {
        if (v < 0 || v >= V)
            throw new IllegalArgumentException("vertex " + v + " is not between 0 and " + (V-1));
    }
}