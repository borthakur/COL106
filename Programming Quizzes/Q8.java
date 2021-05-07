import java.util.Comparator;
import java.util.LinkedList;
import java.util.Scanner;

public class Graph {
    private int V; // number of vertices
    LinkedList<Edge>[] adj; // edges
    int[] levels;
    int[] colour;

    LinkedList<Vertex> LVLCURRENT;
    LinkedList<Vertex> LVLPLUS1;
    LinkedList<Vertex> LVLPLUS2;

    public Graph(int v) {
        this.V = v;
        this.adj = new LinkedList[v];
        for(int i=0; i<v; i++)
            this.adj[i] = new LinkedList<>();
        LVLCURRENT = new LinkedList<>();
        LVLPLUS1 = new LinkedList<>();
        LVLPLUS2 = new LinkedList<>();
        levels = new int[v];
        colour = new int[v];
        for(int k=0;k<v;k++) colour[k] = 0;
    }

    public void copyQueue() {
        LVLCURRENT = LVLPLUS1;
        LVLPLUS1 = LVLPLUS2;
        LVLPLUS2 = new LinkedList<>();
    }

    public static void BFS_Phase_Print(int phase_num, LinkedList<Vertex> LVLPLUS1, LinkedList<Vertex> LVLPLUS2) {
        System.out.println(phase_num);
        LVLPLUS1.sort(Comparator.comparing(Vertex::getId));
        LVLPLUS2.sort(Comparator.comparing(Vertex::getId));
        LVLPLUS1.forEach((v) -> { System.out.print(v.getId() + " "); });
        System.out.println();
        LVLPLUS2.forEach((v) -> { System.out.print(v.getId() + " "); });
        System.out.println();
    }

    public void BFSProcessPhase(int i) {
        // Write your code here.
        for(int j = 0; j < LVLCURRENT.size(); j++){
            levels[(LVLCURRENT.get(j).getId())] = i;
            colour[(LVLCURRENT.get(j).getId())] = 1;
            for(int k = 0;k < adj[LVLCURRENT.get(j).getId()].size();k++){
                if(colour[adj[LVLCURRENT.get(j).getId()].get(k).getVertexId()]==1) continue;
                if(adj[LVLCURRENT.get(j).getId()].get(k).getLength() == 1){
                    Vertex s1 = new Vertex(adj[LVLCURRENT.get(j).getId()].get(k).getVertexId());
                    LVLPLUS1.add(s1);
                }
                else{
                    Vertex s1 = new Vertex(adj[LVLCURRENT.get(j).getId()].get(k).getVertexId());
                    LVLPLUS2.add(s1);
                }
                colour[adj[LVLCURRENT.get(j).getId()].get(k).getVertexId()] = 1;
            }
        }
    }

    public void BFS_New(Vertex s)
    {
        if(s == null)
            return;
        int i = 0;
        LVLCURRENT.add(s);
        colour[0] = 1;
        while(LVLCURRENT.size() > 0 || LVLPLUS1.size() > 0) {
            BFSProcessPhase(i);
            BFS_Phase_Print(i, LVLPLUS1, LVLPLUS2);
            copyQueue();
            i++;
        }
    }
    
    /**
     * DO NOT CHANGE THIS FUNCTION - The main function to generate your graph
     * @param args
     */
    private void addEdge(int s, int t, int l) {
        this.adj[s].add(new Edge(t, l));
    }

    /**
     * DO NOT CHANGE THIS FUNCTION - The main function to take input and call your method.
     * @param args
     */
    public static void main(String args[]) {
        Scanner scanner = new Scanner(System.in);
        int numVertices = scanner.nextInt();
        Graph g = new Graph(numVertices);
        int numEdges = scanner.nextInt();
        for (int i = 0; i < numEdges; i++) {
            int from = scanner.nextInt();
            int to = scanner.nextInt();
            int length = scanner.nextInt();
            g.addEdge(from, to, length);
        }
        scanner.close();
        g.BFS_New(new Vertex(0));
    }
}

class Vertex
{
    private int id;

    public Vertex(int id) {
        this.id = id;
    }

    public Vertex(Vertex v) {
        this.id = v.getId();
    }

    public int getId() {
        return id;
    }
}

class Edge
{
    // Target vertex ID
    private int vertexId;
    private int length;

    public Edge(int vertexId, int length) {
        this.vertexId = vertexId;
        this.length = length;
    }

    public int getVertexId() {
        return vertexId;
    }

    public int getLength() {
        return length;
    }
}
