import java.util.Stack;

public class ShortestPathsTopological {
    private int[] parent;
    private int s;
    private double[] dist;

    public ShortestPathsTopological(WeightedDigraph G, int s) {
        // TODO
        this.s = s;
        dist = new double[G.V()];
        parent = new int[G.V()];
        for(int v=0;v<G.V();v++){
            dist[v] = 1000;
        }
        dist[s] = 0;
        TopologicalWD tpwd = new TopologicalWD(G);
        tpwd.dfs(s);
        Stack<Integer> haupt = tpwd.order();
        while (haupt.size() != 0){
            for (DirectedEdge e: G.incident(haupt.pop())) {
                relax(e);
            }
        }
    }

    public void relax(DirectedEdge e) {
        // TODO
        int v = e.from();
        int w = e.to();
        if(dist[w] > dist[v] + e.weight()){
            parent[w] = v;
            dist[w] = dist[v] + e.weight();
        }
    }

    public boolean hasPathTo(int v) {
        return parent[v] >= 0;
    }

    public Stack<Integer> pathTo(int v) {
        if (!hasPathTo(v)) {
            return null;
        }
        Stack<Integer> path = new Stack<>();
        for (int w = v; w != s; w = parent[w]) {
            path.push(w);
        }
        path.push(s);
        return path;
    }
}

