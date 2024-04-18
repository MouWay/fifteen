package Model.Abstract;

import jdk.jshell.spi.ExecutionControl;

public abstract class SolveStrategy {
    protected Board board;

    public SolveStrategy(Board board){
        this.board = board;
    }

    public abstract void solve() throws ExecutionControl.NotImplementedException;
}
