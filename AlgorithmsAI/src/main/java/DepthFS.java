
import java.util.ArrayList;

public class DepthFS {

    private ArrayList<State> visited = new ArrayList<State>();
    private ArrayList<State> toVisit = new ArrayList<State>();
    private ArrayList<State> solution = new ArrayList<State>();
    private State start;
    private State finish;

    public DepthFS(State start, State finish) {
        this.start = start;
        this.finish = finish;
        this.run();
    }

    public void run() {
        System.out.println("\u001B[34m"+"\tDepth First Search");
        toVisit.add(start);
        ArrayList<Transition> lTr = new ArrayList<Transition>();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (j + i != 0 && j + i <= 2) {
                    Transition temp = new Transition(i, j);
                    lTr.add(temp);
                }
            }
        }
        State curent = start;
        int j;
        System.out.println("\u001B[32m"+"\t\tSteps");
        int nr = 0;
        long startTime = System.currentTimeMillis();
        while (!toVisit.isEmpty()) {
            curent = toVisit.get(0);
            visited.add(curent);
            System.out.println("State -> " + curent.toString());
            if (curent.equals(finish)) {
                break;
            }
            nr++;
            if (curent.getSheep().equals("left")) {
                j = 0;
                for (int i = 0; i < lTr.size(); i++) {
                    Transition tr = lTr.get(i);
                    State temp = curent.minus(tr);
                    if (temp.isValid()) {
                        temp.setFather(curent);
                        if (!visited.contains(temp) && !toVisit.contains(temp)) {
                            toVisit.add(j, temp);
                            j++;
                        }
                    }
                }
            } else if (curent.getSheep().equals("right")) {
                j = 0;
                for (int i = 0; i < lTr.size(); i++) {
                    Transition tr = lTr.get(i);
                    State temp = curent.plus(tr);
                    if (temp.isValid()) {
                        temp.setFather(curent);
                        if (!visited.contains(temp) && !toVisit.contains(temp)) {
                            toVisit.add(j, temp);
                            j++;
                        }
                    }
                }
            }
            toVisit.remove(curent);
        }
        long endTime = System.currentTimeMillis();
        long time = endTime - startTime;
        if (curent.equals(finish)) {
            while (curent.getFather() != null) {
                solution.add(curent);
                curent = curent.getFather();
            }
            solution.add(curent);
            System.out.println("\u001B[32m"+"\t\tSolution\nStep\tLeft\tRight\tSheep");
            j = 1;
            for (int i = solution.size() - 1; i > -1; i--) {
                State temp = solution.get(i);
                System.out.println(j + "\t" + temp.toString());
                j++;
            }
            System.out.println("\u001B[32m"+"\tStatistics");
            System.out.println("Solution length: " + solution.size() + " pas(i)");
            System.out.println("Nr. of states: " + nr);
            System.out.println("Duration: " + time);
        } else {
            System.out.println("There is no convenient solution");
        }
    }
}
