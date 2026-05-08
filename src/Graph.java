
public class Graph {

    private MyArrayList<Edge>[] graph; 
    private int numVertices;          

     public Graph(int numVertices) {
    	 
        this.numVertices = numVertices;
        graph = new MyArrayList[numVertices];
        
        for (int i = 0; i < numVertices; i++) {
            graph[i] = new MyArrayList<>();
        }
    }

    public void addEdge(int from, int to, double distance, double time) {
      
    	if (from < 0 || from >= numVertices || to < 0 || to >= numVertices) {
            throw new IndexOutOfBoundsException("Invalid vertex ID : from =" + from + " to =" + to + " numVertices =" + numVertices);
        }
        graph[from].add(new Edge(to, distance, time));
    }

     public MyArrayList<Edge> getAdjList(int vertexId) {
       
    	 if (vertexId < 0 || vertexId >= numVertices) {
        	return new MyArrayList<>();
        }
        return graph[vertexId];
    }

    public int getNumberOfVertices() {
        return numVertices;
    }
    
}