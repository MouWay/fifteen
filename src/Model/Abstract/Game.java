package Model.Abstract;

import Model.PlayMode;
import Model.Fifteen.AutomaticFifteenSolveStrategy;
import Model.Fifteen.AStarFifteenSolveStrategy;
import jdk.jshell.spi.ExecutionControl;

public abstract class Game {
    protected PlayMode mode;
    protected Board board;
    protected Solver solver;

    public Game(PlayMode mode){
        solver = new Solver(null);
        setMode(mode);
    }

    public abstract void start() throws ExecutionControl.NotImplementedException;

    public Board getBoard() {
        return board;
    }

    public void showState(){
        System.out.println(board);
    }

    protected void play() throws ExecutionControl.NotImplementedException {
        solver.solve();
    }

    private void setMode(PlayMode mode){
        this.mode = mode;

        switch (mode){
            case Auto:
                solver.setStrategy(new AutomaticFifteenSolveStrategy(board));
                break;
            case Manual:
                solver.setStrategy(new AStarFifteenSolveStrategy(board));
                break;
        }
    }

    public void setSolvingStrategy(SolveStrategy strategy){
        solver.setStrategy(strategy);
    }
}
