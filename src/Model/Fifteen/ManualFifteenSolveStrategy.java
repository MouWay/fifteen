package Model.Fifteen;

import Model.Abstract.Board;
import Model.Abstract.SolveStrategy;
import jdk.jshell.spi.ExecutionControl;

public class ManualFifteenSolveStrategy extends SolveStrategy {
    public ManualFifteenSolveStrategy(Board board) {
        super(board);
    }

    @Override
    public void solve() throws ExecutionControl.NotImplementedException {
        throw new ExecutionControl.NotImplementedException("Manual solving not implemented");
    }
}
