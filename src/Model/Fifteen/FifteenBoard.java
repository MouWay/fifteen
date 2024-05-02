package Model.Fifteen;

import Model.Abstract.Board;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class FifteenBoard extends Board {
    private int blankPosition;

    public FifteenBoard(int size){
        super(size);
    }

    public FifteenBoard(int[] tiles, int blankPosition, int size) {
        super(size);
        this.tiles = tiles;
        this.blankPosition = blankPosition;
    }

    @Override
    public void generate() {
        fill();

        do {
            shuffle();
            updateBlankPosition();
        } while (isSolvable() == false);
    }

    @Override
    public Iterable<Board> getPossibleMoves() {
        List<Board> moves = new ArrayList<>();

        if (blankPosition % size != 0) {
            var newTiles = Arrays.copyOf(tiles, tiles.length);
            newTiles[blankPosition] = newTiles[blankPosition - 1];
            newTiles[blankPosition - 1] = 0;
            moves.add(new FifteenBoard(newTiles, blankPosition - 1, size));
        }

        if (blankPosition % size != size - 1) {
            var newTiles = Arrays.copyOf(tiles, tiles.length);
            newTiles[blankPosition] = newTiles[blankPosition + 1];
            newTiles[blankPosition + 1] = 0;
            moves.add(new FifteenBoard(newTiles, blankPosition + 1, size));
        }

        if (blankPosition / size != 0) {
            var newTiles = Arrays.copyOf(tiles, tiles.length);
            newTiles[blankPosition] = newTiles[blankPosition - size];
            newTiles[blankPosition - size] = 0;
            moves.add(new FifteenBoard(newTiles, blankPosition - size, size));
        }

        if (blankPosition / size != size - 1) {
            var newTiles = Arrays.copyOf(tiles, tiles.length);
            newTiles[blankPosition] = newTiles[blankPosition + size];
            newTiles[blankPosition + size] = 0;
            moves.add(new FifteenBoard(newTiles, blankPosition + size, size));
        }

        return moves;
    }

    @Override
    public int getHeuristic() {
        return getManhattanDistance() + getCellDisplacement();
    }

    public int getLinearConflict(){
        int result = 0;

        for (int i = 0; i < tiles.length; i += 4) {
            for (int j = i; j - i < size; j++){
                for (int k = j + 1; k - i < size; k++){
                    if (tiles[j] == 0 || tiles[k] == 0)
                        continue;

                    if (isCellInCorrectRow(j) && isCellInCorrectRow(k) && tiles[j] > tiles[k]) result += 2;
                }
            }
        }

        return result;
    }

    private boolean isCellInCorrectRow(int index){
        return tiles[index] / size == (index + 1) / size;
    }

    private int getCellDisplacement(){
        int result = 0;

        for (int i = 0; i < tiles.length; i++) {
            if (tiles[i] == 0)
                continue;

            if (tiles[i] != i + 1)
                result += (size * size - tiles[i]) * (size * size - tiles[i]) * getK(tiles[i]);
        }

        return result;
    }

    public int getManhattanDistance(){
        int result = 0;

        for (int i = 0; i < tiles.length; i++) {
            int currentTile = tiles[i];

            if (currentTile == 0)
                continue;

            int boardSize = size;
            int offset = Math.abs((currentTile % boardSize) - ((i + 1) % boardSize)) + Math.abs(currentTile / boardSize - (i + 1) / boardSize);

            result += offset * (size * size - currentTile) * (size * size - currentTile) * getK(tiles[i]);
        }

        return result;
    }

    private int getK(int tile){
        int k = 1;

        if (tile <= size * (size - 2))
            k = 100000;
        else if (tile == (size - 2) * size + 1)
            k = 1000;
        else if (tile == (size - 1) * size + 1)
            k = 100;
        else if (tile > (size - 2) * size + 1 && tile < (size - 1) * size + 1)
            k = 10;

        return k;
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

        return  size % 2 == 1 && countInversions % 2 == 0 ||
                size % 2 == 0 && (
                countInversions % 2 == 0 && (blankPosition / size) % 2 == 1 ||
                countInversions % 2 == 1 && (blankPosition / size) % 2 == 0
                );
    }
}
