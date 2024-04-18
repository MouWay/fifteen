package Model.Abstract;

import jdk.jshell.spi.ExecutionControl;

public class Solver {
    protected SolveStrategy strategy;

    public Solver(SolveStrategy strategy) {
        this.strategy = strategy;
    }

    public void setStrategy(SolveStrategy strategy) {
        this.strategy = strategy;
    }

    public void solve() throws ExecutionControl.NotImplementedException {
        strategy.solve();
    }
}