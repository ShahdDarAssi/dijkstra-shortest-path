public class PQItem implements Comparable<PQItem> {
  
    private int vertex;
    private double cost; 
    
    public PQItem(int v, double cost) {
        this.vertex = v;
        this.cost = cost;
    }

    public int getVertex() {
		return vertex;
	}

	public void setVertex(int vertex) {
		this.vertex = vertex;
	}

	public double getPriority() {
		return cost;
	}

	public void setPriority(double cost) {
		this.cost = cost;
	}

	@Override
    public int compareTo(PQItem other) {
        return Double.compare(this.cost, other.cost);
    }
    
}
