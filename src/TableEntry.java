public class TableEntry {

    double dist;     
    int path;        
    boolean known;   

    public TableEntry() {
        dist = Double.MAX_VALUE; 
        path = -1;                
        known = false;            
    }

    public double getDist() {
        return dist;
    }

    public void setDist(double dist) {
        this.dist = dist;
    }

    public int getPath() {
        return path;
    }

    public void setPath(int prev) {
        this.path = prev;
    }

    public boolean isKnown() {
        return known;
    }

    public void setKnown(boolean known) {
        this.known = known;
    }   
}
