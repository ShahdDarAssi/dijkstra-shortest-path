import java.util.*;

public class Dijkstra {

	public static TableEntry[] initializeTable(Graph g, int start) {

		int n = g.getNumberOfVertices();
		TableEntry[] table = new TableEntry[n];

		for (int i = 0; i < n; i++) {
			table[i] = new TableEntry();
		}

		table[start].setDist(0);
		return table;
	}

	public static TableEntry[] dijkstra(Graph g, int start, boolean useTime) {

		int n = g.getNumberOfVertices();
		TableEntry[] table = initializeTable(g, start);

		PriorityQueue<PQItem> pq = new PriorityQueue<>();
		pq.enQueue(new PQItem(start, 0));

		while (!pq.isEmpty()) {

			PQItem current = pq.deQueue();
			int v = current.getVertex();

			if (current.getPriority() > table[v].getDist()) {
				continue;
			}

			if (table[v].isKnown()) {
				continue;
			}

			table[v].setKnown(true);

			MyArrayList<Edge> edge = g.getAdjList(v);

			for (int i = 0; i < edge.getSize(); i++) {

				Edge e = edge.get(i);
				int v1 = e.getTo();

				double cost;

				if (useTime) {
					cost = e.getTime();
				} else {
					cost = e.getDistance();
				}

				if (!table[v1].isKnown()) {

					double newDist = table[v].getDist() + cost;

					if (newDist < table[v1].getDist()) {

						table[v1].setDist(newDist);
						table[v1].setPath(v);
						pq.enQueue(new PQItem(v1, newDist));
					}
				}
			}
		}

		return table;
	}

	public static void printPath(int endId, TableEntry[] table, Graph g, StringBuilder sb, boolean useTime) {

	    if (table[endId].getDist() == Double.MAX_VALUE) {
	        sb.append("No path exists from start to vertex ").append(endId).append("\n");
	        return;
	    }

	    MyArrayList<Integer> path = new MyArrayList<>();
	    int current = endId;

	    while (current != -1) {
	        path.add(current);
	        TableEntry entry = table[current];
	        current = entry.getPath(); 
	    }

	    sb.append("Path: ");

//	    for (int i = path.getSize() - 1; i >= 0; i--) {
//	        sb.append(path.get(i));
//	        if (i != 0) {
//	            sb.append(" -> ");
//	        }
//	    }
	    
	    
        for (int i = path.getSize() - 1; i >= 0; i--) {
           
        	int from = path.get(i);
            sb.append(from);
     
            if (i != 0) {
               
            	int to = path.get(i - 1);
                double cost = 0;
                MyArrayList<Edge> edges = g.getAdjList(from);
                for (int j = 0; j < edges.getSize(); j++) {
                   
                	Edge e = edges.get(j);
                    
                	if (e.getTo() == to) {
                    	
                    	if(useTime) {
                    		cost=e.getTime();
                    	}else {
                    		cost=e.getDistance();
                    	}               
                    	
                    	break;
                    }
                }
                sb.append(" ---(").append(String.format("%.2f", cost)).append(" ) ---> ");
                
            }
        }


	    sb.append("\n");
	    sb.append("Total: ").append(String.format("%.2f", table[endId].getDist()))
	      .append(useTime ? " min" : " Km").append("\n");
	     
	}

	
}
