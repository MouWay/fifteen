package Model.Fifteen;

import Model.Abstract.Board;
import jdk.jshell.spi.ExecutionControl;

public class AutomaticFifteenSolveStrategy extends FifteenSolveStrategy {
    public AutomaticFifteenSolveStrategy(Board board) {
        super(board);
    }

    @Override
    public void solve() throws ExecutionControl.NotImplementedException {
        throw new ExecutionControl.NotImplementedException("Automatic solving not implemented");
    }
}
