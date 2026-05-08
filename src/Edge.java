
public class Edge {

	private int to;
	private double distance;
	private double time;

	public Edge(int to, double distance, double time) {
		super();
		this.to = to;
		this.distance = distance;
		this.time = time;
	}

	public int getTo() {
		return to;
	}

	public void setTo(int to) {
		this.to = to;
	}

	public double getDistance() {
		return distance;
	}

	public void setDistance(double distance) {
		this.distance = distance;
	}

	public double getTime() {
		return time;
	}

	public void setTime(double time) {
		this.time = time;
	}

}
