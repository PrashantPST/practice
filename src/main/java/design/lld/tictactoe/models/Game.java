package design.lld.tictactoe.models;

import design.lld.snakeladder.enums.GameStatus;
import design.lld.tictactoe.enums.CellState;
import design.lld.tictactoe.exceptions.InvalidGameConstructionParametersException;
import design.lld.tictactoe.strategies.GameWinningStrategy;
import design.lld.tictactoe.strategies.OrderOneGameWinningStrategy;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;


@Getter
@Setter
public class Game {

    private Board board;
    private List<Player> players;
    private List<Move> moves;
    private GameStatus gameStatus;
    private int nextPlayerIndex;
    private GameWinningStrategy gameWinningStrategy;
    private Player winner;

    private Game() {
    }

    public static Builder getBuilder() {
        return new Builder();
    }

    public void makeNextMove() {
        Player currentPlayer = players.get(nextPlayerIndex);
        System.out.println("It is " + currentPlayer.getName() + "'s turn.");
        Move move = currentPlayer.move(this.board);

        // validate move

        int row = move.getCell().getRow();
        int col = move.getCell().getCol();

        System.out.println("Move happened at: " + row + ", " + col + ".");

        board.getBoard().get(row).get(col).setCellState(CellState.FILLED);
        board.getBoard().get(row).get(col).setPlayer(players.get(nextPlayerIndex));

        this.moves.add(move);

        if (gameWinningStrategy.checkWinner(board, currentPlayer, move.getCell())) {
            gameStatus = GameStatus.FINISHED;
            winner = players.get(nextPlayerIndex);
        }
        nextPlayerIndex = (nextPlayerIndex + 1) % players.size();
    }

    public void displayBoard() {
        this.board.display();
    }


    public static class Builder {
        private int dimension;
        private List<Player> players;


        public Builder setDimension(int dimension) {
            this.dimension = dimension;
            return this;
        }


        public Builder setPlayers(List<Player> players) {
            this.players = players;
            return this;
        }

        private void valid() throws InvalidGameConstructionParametersException {
            if (this.dimension < 3) {
                throw new InvalidGameConstructionParametersException("Dimension of game can't be 1");
            }

            if (this.players.size() != this.dimension - 1) {
                throw new InvalidGameConstructionParametersException("Number of Players must be Dimension - 1");
            }

            // Validate no 2 people with same char

            // Validate all 1 bot

        }

        public Game build() throws InvalidGameConstructionParametersException {
            try {
                valid();
            } catch (Exception e) {
                throw new InvalidGameConstructionParametersException(e.getMessage());
            }

            Game game = new Game();
            game.setGameStatus(GameStatus.IN_PROGRESS);
            game.setPlayers(players);
            game.setMoves(new ArrayList<>());
            game.setBoard(new Board(dimension));
            game.setNextPlayerIndex(0);
            game.setGameWinningStrategy(new OrderOneGameWinningStrategy());

            return game;
        }
    }
}
