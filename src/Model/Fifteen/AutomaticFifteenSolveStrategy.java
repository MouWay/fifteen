package Model.Fifteen;

import Model.Abstract.Board;
import jdk.jshell.spi.ExecutionControl;

import java.util.*;

public class AutomaticFifteenSolveStrategy extends FifteenSolveStrategy {
    public AutomaticFifteenSolveStrategy(Board board) {
        super(board);
    }

    @Override
    public void solve() throws ExecutionControl.NotImplementedException {
        int initialSearchDepth = 12;
        int searchDepthAddition = 3;
        int searchDepth = initialSearchDepth;

        int totalMoves = 0;

        System.out.println(board);

        while (isSolved() == false) {
            var temp = search(board, searchDepth);

            if (temp.getLast() == board) {
                searchDepth += searchDepthAddition;
            }
            else {
                for (int i = 1; i < temp.size(); i++){
                    board = temp.get(i);
                    System.out.println(board);
                }

                totalMoves += temp.size() - 1;
            }
        }

        System.out.println("Total moves: " + totalMoves);
    }

    private LinkedList<Board> search(Board initialState, int searchDepth){
        LinkedList<Board> result = new LinkedList<>();
        result.addFirst(initialState);

        int bestHeuristic = initialState.getHeuristic();

        var moves = initialState.getPossibleMoves();

        if (searchDepth == 1) {
            result.add(getBestMove(moves));
        }
        else {
            for (var move : moves){
                var temp = search(move,searchDepth - 1);

                var last = temp.getLast();
                if (last.getHeuristic() < bestHeuristic){
                    if (bestHeuristic < initialState.getHeuristic()) {
                        result.clear();
                        result.addFirst(initialState);
                    }

                    bestHeuristic = last.getHeuristic();
                    result.addAll(temp);
                }
            }
        }

        return result;
    }


    private Board getBestMove(Iterable<Board> moves){
        Board result = null;
        int heuristic = Integer.MAX_VALUE;

        for (var move : moves){
            if (move.getHeuristic() < heuristic){
                heuristic = move.getHeuristic();
                result = move;
            }
        }

        return result;
    }

    private boolean isSolved(){
        return board.getHeuristic() == 0;
    }
}
