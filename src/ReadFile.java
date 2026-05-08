import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class ReadFile {

	public static Graph readGraphFromFile(String filename) {

		MyArrayList<EdgeRead> readEdge = new MyArrayList<>();
		int maxVertexId = -1;

		try (BufferedReader br = new BufferedReader(new FileReader(filename))) {

			String line;
			line = br.readLine();
			if (line == null || line.trim().isEmpty()) {
				System.err.println("File is empty or invalid first line");
				return null;
			}

			while ((line = br.readLine()) != null) {

				if (line.trim().isEmpty()) {
					continue;
				}
				String[] parts = line.trim().split("\\s+");

				if (parts.length < 4) {
					continue;
				}

				int from = Integer.parseInt(parts[0]);
				int to = Integer.parseInt(parts[1]);
				double distance = Double.parseDouble(parts[2]);
				double time = Double.parseDouble(parts[3]);

				readEdge.add(new EdgeRead(from, to, distance, time));

				if (from > maxVertexId) {
					maxVertexId = from;
				}

				if (to > maxVertexId) {
					maxVertexId = to;
				}

			}

		} catch (IOException e) {
			e.printStackTrace();
		}

		Graph g = new Graph(maxVertexId + 1);

		for (int i = 0; i < readEdge.getSize(); i++) {
			EdgeRead e = readEdge.get(i);
			g.addEdge(e.from, e.to, e.distance, e.time);
		}

		return g;
	}

	public static int[] readFirstLine(String filename) {
		
        int[] result = new int[3]; 
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
          
        	String line = br.readLine();
            if (line != null && !line.trim().isEmpty()) {
            	
                String[] parts = line.trim().split("\\s+");
                
                if (parts.length >= 3) {
                    result[0] = Integer.parseInt(parts[0]); 
                    result[1] = Integer.parseInt(parts[1]); 
                    result[2] = Integer.parseInt(parts[2]); 
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }
}
