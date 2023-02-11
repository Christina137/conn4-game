import java.util.Random;

public class AIPlayer {
    private int Enemy_symbol;
    private Board board;
    private int symbol = 2;
    public AIPlayer(int enemy_symbol, Board board) {
        Enemy_symbol = enemy_symbol;
        this.board = board;
    }

    public void makeMove() {
        if (this.Enemy_symbol != 0){
            if (board.win_step_check(this.symbol)==true){
                int location = board.win_step_location(this.symbol);
                board.input_check(this.symbol,location);
            }else if (board.win_step_check(this.Enemy_symbol)==true){
                int location = board.win_step_location(this.Enemy_symbol);
                board.input_check(this.symbol,location);
            }else {
                Random random=new Random();
                int a;
                while (true){
                    a = random.nextInt(7);
                    if (board.input_check(this.symbol,a)==true){
                        break;
                    }
                }
            }
        }else {
            if (board.win_step_check(this.symbol)==true){
                int location = board.win_step_location(this.symbol);
                board.input_check(this.symbol,location);
            }else {
                Random random=new Random();
                int a;
                while (true){
                    a = random.nextInt(7);
                    if (board.input_check(this.symbol,a)==true){
                        break;
                    }
                }
            }
        }
    }

    public void smartMove(){
        move m = Board.minimax(board,5,Integer.MIN_VALUE,Integer.MAX_VALUE,true);
        board.input_check(symbol,m.getCol());
    }
}
