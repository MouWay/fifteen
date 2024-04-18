package Model.Fifteen;

import Model.Abstract.Board;

import java.util.Random;

public class FifteenBoard extends Board {
    private int blankPosition;

    public FifteenBoard(){
        super(4);
    }

    @Override
    public void generate() {
        fill();

        do {
            shuffle();
            updateBlankPosition();
        } while (isSolvable() == false);
    }

    private void fill(){
        for (int i = 0; i < tiles.length - 1; i++)
            tiles[i] = i + 1;

        blankPosition = tiles.length - 1;
    }

    private void shuffle(){
        var random = new Random();
        int n = tiles.length;

        while (n > 0) {
            int r = random.nextInt(n--);

            int temp = tiles[r];
            tiles[r] = tiles[n];
            tiles[n] = temp;
        }
    }

    private void updateBlankPosition(){
        for (int i = 0; i < tiles.length; i++) {
            if (tiles[i] == 0){
                blankPosition = i;
                break;
            }
        }
    }

    private boolean isSolvable() {
        int countInversions = 0;

        for (int i = 0; i < tiles.length - 1; i++) {
            if (tiles[i] == 0)
                continue;

            for (int j = i + 1; j < tiles.length; j++) {
                if (tiles[j] == 0)
                    continue;

                if (tiles[i] > tiles[j])
                    countInversions++;
            }
        }

        return countInversions % 2 == 0 && blankPosition / 4 == 0 ||
                countInversions % 2 == 1 && blankPosition / 4 == 1;
    }
}
