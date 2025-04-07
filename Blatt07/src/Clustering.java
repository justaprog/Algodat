import java.util.*;
import java.awt.Color;

/**
 * This class solves a clustering problem with the Prim algorithm.
 */
public class Clustering {
	EdgeWeightedGraph G;
	List <List<Integer>>clusters;
	List <List<Integer>>labeled;

	/**
	 * Constructor for the Clustering class, for a given EdgeWeightedGraph and no labels.
	 * @param G a given graph representing a clustering problem
	 */
	public Clustering(EdgeWeightedGraph G) {
            this.G=G;
		clusters= new LinkedList <List<Integer>>();
	}

	/**
	 * Constructor for the Clustering class, for a given data set with labels
	 * @param in input file for a clustering data set with labels
	 */
	public Clustering(In in) {
            int V = in.readInt();
            int dim= in.readInt();
            G= new EdgeWeightedGraph(V);
            labeled=new LinkedList <List<Integer>>();
            LinkedList labels= new LinkedList();
            double[][] coord = new double [V][dim];
            for (int v = 0;v<V; v++ ) {
                for(int j=0; j<dim; j++) {
					coord[v][j]=in.readDouble();
                }
                String label= in.readString();
                    if(labels.contains(label)) {
						labeled.get(labels.indexOf(label)).add(v);
                    }
                    else {
						labels.add(label);
						List <Integer> l= new LinkedList <Integer>();
						labeled.add(l);
						labeled.get(labels.indexOf(label)).add(v);
						System.out.println(label);
                    }                
            }
             
            G.setCoordinates(coord);
            for (int w = 0; w < V; w++) {
                for (int v = 0;v<V; v++ ) {
					if(v!=w) {
						double weight=0;
                    for(int j=0; j<dim; j++) {
						weight= weight+Math.pow(G.getCoordinates()[v][j]-G.getCoordinates()[w][j],2);
                    }
                    weight=Math.sqrt(weight);
                    Edge e = new Edge(v, w, weight);
                    G.addEdge(e);
					}
                }
            }
		clusters= new LinkedList <List<Integer>>();
	}

	/**
	 * This method finds a specified number of clusters based on a MST.
	 *
	 * It is based in the idea that removing edges from a MST will create a
	 * partition into several connected components, which are the clusters.
	 * @param numberOfClusters number of expected clusters
	 */
	public void findClusters(int numberOfClusters){
		// TODO
		PrimMST mst = new PrimMST(G);
		LinkedList<Edge> kanten = new LinkedList<Edge>();
		for (Edge e: mst.edges()) {
			if(kanten.size() == 0){
				kanten.add(e);
			}else {
				for (int i = 0; i < kanten.size(); i++) {
					if (e.weight() <= kanten.get(i).weight()) {
						kanten.add(i, e);
						break;
					}
				}
				if(!kanten.contains(e)){
					kanten.add(e);
				}
			}
		}
		for(int i=1;i<numberOfClusters;i++) {
			kanten.removeLast();
		}
		UF uni = new UF(G.V());
		for (Edge e: kanten) {
			int v = e.either();
			int w = e.other(v);
			uni.union(v,w);
		}
		for(int i=0;i<numberOfClusters;i++){
			List<Integer> list = new ArrayList<Integer>();
			clusters.add(list);
		}
		for(int v=0;v<G.V();v++){
			for(int i=0;i<numberOfClusters;i++){
				if(clusters.get(i).isEmpty()){
					clusters.get(i).add(v);
					break;
				}
				if(uni.find(v) == uni.find(clusters.get(i).get(0))){
					clusters.get(i).add(v);
					break;
				}
			}
		}
		for(int i=0;i<numberOfClusters;i++){
			Collections.sort(clusters.get(i));
		}
	}

	/**
	 * This method finds clusters based on a MST and a threshold for the coefficient of variation.
	 *
	 * It is based in the idea that removing edges from a MST will create a
	 * partition into several connected components, which are the clusters.
	 * The edges are removed based on the threshold given. For further explanation see the exercise sheet.
	 *
	 * @param threshold for the coefficient of variation
	 */
	public void findClusters(double threshold) {
		// TODO

	}

	/**
	 * Evaluates the clustering based on a fixed number of clusters.
	 * @return array of the number of the correctly classified data points per cluster
	 */
	public int[] validation() {
		// TODO
		int[] list = null;
		return list;
	}

	/**
	 * Calculates the coefficient of variation.
	 * For the formula see exercise sheet.
	 * @param part list of edges
	 * @return coefficient of variation
	 */
	public double coefficientOfVariation(List <Edge> part) {
		// TODO
		double result1 = 0.0;
		double result2=0.0;
		if (part.size()==0){
			return 0;
		}
		double ein =(double) (1.0/part.size());
		for (Edge e:part) {
				result1= result1 + e.weight()*e.weight();
				result2= result2 + e.weight();
		}
		double result= Math.sqrt(ein*result1 - (ein*result2*ein*result2))/(ein*result2);
		return result;
	}

	/**
	 * Plots clusters in a two-dimensional space.
	 */
	public void plotClusters() {
		int canvas=800;
		StdDraw.setCanvasSize(canvas, canvas);
		StdDraw.setXscale(0, 15);
		StdDraw.setYscale(0, 15);
		StdDraw.clear(new Color(0,0,0));
		Color[] colors= {new Color(255, 255, 255), new Color(128, 0, 0), new Color(128, 128, 128),
				new Color(0, 108, 173), new Color(45, 139, 48), new Color(226, 126, 38), new Color(132, 67, 172)};
		int color=0;
		for(List <Integer> cluster: clusters) {
			if(color>colors.length-1) color=0;
			StdDraw.setPenColor(colors[color]);
			StdDraw.setPenRadius(0.02);
			for(int i: cluster) {
				StdDraw.point(G.getCoordinates()[i][0], G.getCoordinates()[i][1]);
			}
			color++;
		}
		StdDraw.show();
	}


	public static void main(String[] args) {
		// FOR TESTING
		In in = new In("graph_small.txt");
		EdgeWeightedGraph G = new EdgeWeightedGraph(in);
		Clustering c = new Clustering(G);
		c.findClusters(3);
		for(int i=0;i<c.clusters.get(1).size();i++){
			System.out.println(c.clusters.get(1).get(i));
		}
    }
}

