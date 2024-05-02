package Model.Fifteen;

import Model.Abstract.Board;
import Model.State;
import jdk.jshell.spi.ExecutionControl;

import java.util.*;

public class AStarFifteenSolveStrategy extends FifteenSolveStrategy {
    public AStarFifteenSolveStrategy(Board board) {
        super(board);
    }

    @Override
    public void solve() throws ExecutionControl.NotImplementedException {
        for (var state : search(new State(board))){
            System.out.println(state.getBoard());
        }
    }

    public Collection<State> search(State startState) {
        LinkedList<State> close = new LinkedList<State>();
        LinkedList<State> open = new LinkedList<State>();
        open.add(startState);
        startState.setG(0);
        startState.setH();

        while (!open.isEmpty()) {
            State x = getStateWithMinF(open);

            if (x.isTerminate()) {
                var closedStates = close.size();
                return restorePath(x);
            }

            System.out.println(x.getG() + " " + x.getH());

            open.remove(x);
            close.add(x);

            List<State> neighbors = new ArrayList<>();

            for (var board : x.getBoard().getPossibleMoves())
                neighbors.add(new State(board));

            for (State neighbor : neighbors) {
                if (close.contains(neighbor))
                    continue;

                int g = x.getG() + 1;
                boolean isGBetter;

                if (open.contains(neighbor) == false) {
                    neighbor.setH();
                    open.add(neighbor);
                    isGBetter = true;
                } else {
                    isGBetter = g < neighbor.getG();
                }

                if (isGBetter) {
                    neighbor.setParent(x);
                    neighbor.setG(g);
                }
            }
        }
        return null;
    }

    private State getStateWithMinF(Collection<State> open) {
        State result = null;
        int min = Integer.MAX_VALUE;
        for (State state : open) {
            if (state.getF() < min) {
                min = state.getF();
                result = state;
            }
        }
        return result;
    }

    private Collection<State> restorePath(State terminate) {
        Deque<State> path = new LinkedList<State>();
        State c = terminate;

        while (c != null) {
            path.addFirst(c);
            c = c.getParent();
        }

        return path;
    }
}
