
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Algorithms {

    private static State start;
    private static State finish = new State(0, 0, 3, 3, "right");

    public static void main(String[] args) {
        ArrayList<State> possibleStartStates = new ArrayList<State>();
        try {
            BufferedReader bf = new BufferedReader(new FileReader("input.txt"));
            while (true) {
                String line = bf.readLine();
                if (line == null) {
                    break;
                }
                String[] arr = line.split(",");
                State temp = new State(Integer.parseInt(arr[0]), Integer.parseInt(arr[1]), Integer.parseInt(arr[2]), Integer.parseInt(arr[3]), arr[4]);
                possibleStartStates.add(temp);
            }
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
        if (!possibleStartStates.isEmpty()) {
            int max = possibleStartStates.size();
            int poz = (int) (Math.random() * max);
            start = possibleStartStates.get(poz);
        } else {
            start = new State(3, 3, 0, 0, "left");
        }
        System.out.println("Initial state -> "+start.toString());
        BreadthFS breadthfs = new BreadthFS(start, finish);
        DepthFS deapthfs = new DepthFS(start, finish);
        BestFS bestfs = new BestFS(start, finish);
    }
}
