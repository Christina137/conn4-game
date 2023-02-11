
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Random;


public class Board {
    private static final int ROWS = 6;
    private static final int COLUMNS = 7;


    private int currentPlayer = 1;

    private static final int human = 1;
    private static final int AI = 2;

    private int[][] cells;

    public Board() {
        cells = new int[ROWS][COLUMNS];
    }

    public int[][] getCells() {
        return cells;
    }

    public void reset(){
        cells = new int[ROWS][COLUMNS];
        currentPlayer = 1;
    }

    public int getRows() {
        return ROWS;
    }
    public int getCurrentPlayer(){
        return currentPlayer;
    }

    public int getColumns() {
        return COLUMNS;
    }

    public int getCell(int row, int column) {
        return cells[row][column];
    }

    public void setCell(int row, int column) {
        cells[row][column] = currentPlayer;
        if (!containsWin()){
            if (currentPlayer == 1){
                currentPlayer = 2;
            }else {
                currentPlayer = 1;
            }
        }
    }
    public void setCell2(int row, int column,int player) {
        cells[row][column] = player;
        if (!containsWin()){
            if (player == 1){
                currentPlayer = 2;
            }else {
                currentPlayer = 1;
            }
        }
    }
    public int getNextOpenRow(int column) {
        for (int row = ROWS - 1; row >= 0; row--) {
            if (cells[row][column] == 0) {
                return row;
            }
        }
        return -1;
    }

    public void PrintBoard(){
        System.out.println("current Board");
        for (int[] i : cells) {
            System.out.println(Arrays.toString(i));
        }
    }

    public boolean containsWin() {

        for (int i = 0; i < this.ROWS; i++) {
            for (int j = 0; j < this.COLUMNS-3; j++) {
                if (this.cells[i][j+1]==this.cells[i][j] && this.cells[i][j+2]==this.cells[i][j] && this.cells[i][j+3]==this.cells[i][j] && this.cells[i][j] != 0){
                    return true;
                }
            }
        }

        for (int i = 0; i < this.ROWS-3; i++) {
            for (int j = 0; j < this.COLUMNS; j++) {
                if (this.cells[i+1][j]==this.cells[i][j] && this.cells[i+2][j]==this.cells[i][j] && this.cells[i+3][j]==this.cells[i][j] && this.cells[i][j] != 0){
                    return true;
                }
            }
        }

        for (int i = 0; i < this.ROWS-3; i++) {
            for (int j = 0; j < this.COLUMNS-3; j++) {
                if (this.cells[i+1][j+1]==this.cells[i][j] && this.cells[i+2][j+2]==this.cells[i][j] && this.cells[i+3][j+3]==this.cells[i][j] && this.cells[i][j] != 0){
                    return true;
                }
            }
        }

        for (int i = 3; i < this.ROWS; i++) {
            for (int j = 0; j < this.COLUMNS-3; j++) {
                if (this.cells[i-1][j+1]==this.cells[i][j] && this.cells[i-2][j+2]==this.cells[i][j] && this.cells[i-3][j+3]==this.cells[i][j]&& this.cells[i][j] != 0){
                    return true;
                }
            }
        }
        return false;
    }
    public static boolean containsWin2(Board board,int c) {

        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLUMNS-3; j++) {
                if (board.cells[i][j+1]== c && board.cells[i][j+2]==c && board.cells[i][j+3]==c && board.cells[i][j] == c){
                    return true;
                }
            }
        }

        for (int i = 0; i < ROWS-3; i++) {
            for (int j = 0; j < COLUMNS; j++) {
                if (board.cells[i+1][j]==c && board.cells[i+2][j]==c && board.cells[i+3][j]==c && board.cells[i][j] == c){
                    return true;
                }
            }
        }

        for (int i = 0; i < ROWS-3; i++) {
            for (int j = 0; j < COLUMNS-3; j++) {
                if (board.cells[i+1][j+1]==c && board.cells[i+2][j+2]==c && board.cells[i+3][j+3]==c && board.cells[i][j] == c){
                    return true;
                }
            }
        }

        for (int i = 3; i < ROWS; i++) {
            for (int j = 0; j < COLUMNS-3; j++) {
                if (board.cells[i-1][j+1]==c && board.cells[i-2][j+2]==c && board.cells[i-3][j+3]==c&& board.cells[i][j] == c){
                    return true;
                }
            }
        }
        return false;
    }

    public boolean isTie() {
        for (int i = 0; i < this.COLUMNS; i++) {
            if (this.cells[0][i]==0){
                return false;
            }
        }
        return true;
    }
    public static boolean isTie2(Board board) {
        for (int i = 0; i < COLUMNS; i++) {
            if (board.cells[0][i]==0){
                return false;
            }
        }
        return true;
    }



    public boolean win_step_check(int your_symbol){
        for (int row = 0; row < this.ROWS; row++) {
            for (int col = 0; col < this.COLUMNS; col++) {
                if (this.cells[row][col] == 0) {
                    this.cells[row][col] = your_symbol;
                    if (containsWin() == true) {
                        this.cells[row][col] = 0;
                        if (row == this.ROWS - 1) {
                            return true;
                        } else {
                            if (this.cells[row + 1][col] != 0) {
                                return true;
                            }
                        }
                    } else {
                        this.cells[row][col] = 0;
                    }
                }
            }
        }
        return false;
    }


    public int win_step_location(int your_symbol){
        for (int row = 0; row < this.ROWS; row++) {
            for (int col = 0; col < this.COLUMNS; col++) {
                if (this.cells[row][col] == 0l) {
                    this.cells[row][col] = your_symbol;
                    if (containsWin() == true) {
                        this.cells[row][col] = 0;
                        if (row == this.ROWS - 1) {
                            return col;
                        } else {
                            if (this.cells[row + 1][col] != 0) {
                                return col;
                            }
                        }
                    } else {
                        this.cells[row][col] = 0;
                    }
                }
            }
        }
        return -1;
    }
    public boolean input_check(int symbol,int user_input_int){
        for (int i = 0; i < ROWS; i++) {
            if (this.cells[i][user_input_int] == 0){
                insert_into_board(symbol,user_input_int);
                return true;
            }
        }
        return false;
    }


    private void insert_into_board(int symbol,int user_input_int){
        for (int i = ROWS-1; i >= 0 ; i--) {
            if (this.cells[i][user_input_int] == 0){
                this.cells[i][user_input_int] = symbol;
                break;
            }
        }
        if (!containsWin()){
            if (currentPlayer == 1){
                currentPlayer = 2;
            }else {
                currentPlayer = 1;
            }
        }
    }

    public Board copy(){
        Board board = new Board();
        board.currentPlayer = getCurrentPlayer();
        for (int i = 0; i < ROWS; i++) {
            board.cells[i] = this.cells[i].clone();
        }
        return board;
    }

    public int[] valid_location(){
        ArrayList<Integer> result = new ArrayList<>();
        for (int i = 0; i < COLUMNS; i++) {
            if (this.getNextOpenRow(i) != -1){
                result.add(i);
            }
        }
        return result.stream().mapToInt(i -> i).toArray();
    }

    public static boolean is_terminal_node(Board board){
        return containsWin2(board,human) || containsWin2(board,AI) || isTie2(board);
    }
    public static int evaluate(ArrayList<Integer> list, int player){
        int score = 0;
        int oppplayer = human;
        if (player != human){
            oppplayer = AI;
        }
        int count = Collections.frequency(list, player);
        int countopp = Collections.frequency(list, oppplayer);
        int count0 = Collections.frequency(list, 0);
        if (count == 4){
            score += 100;
        } else if (count == 3 && count0 == 1) {
            score += 5;
        } else if (count == 2 && count0 == 2 ) {
            score += 2;
        }
        if (countopp == 3 && count0 == 1){
            score -= 4;
        }
        return score;
    }
    public static int score(Board board,int player){
        int score = 0;

        //centre array
        ArrayList<Integer> center = new ArrayList<>();
        for (int i = 0; i < ROWS; i++) {
            center.add(board.cells[i][3]);
        }
        score += evaluate(center,player) * 3;


        // horizontal
        for (int i = 0; i < ROWS; i++) {
            int[] row_array = board.cells[i].clone();
            for (int j = 0; j < 4; j++) {
                int[] copy = Arrays.copyOfRange(row_array, j, j+4);
                ArrayList<Integer> window = new ArrayList<>();
                for (int a : copy) {
                    window.add(a);
                }
                score += evaluate(window,player);
            }
        }
        
        //vertial
        for (int i = 0; i < COLUMNS; i++) {
            int[] col_array = {board.cells[0][i],board.cells[1][i],board.cells[2][i],board.cells[3][i],
                    board.cells[4][i],board.cells[5][i]};
            for (int j = 0; j < 4; j++) {
                int[] copy = Arrays.copyOfRange(col_array, j, j+4);
                ArrayList<Integer> window = new ArrayList<>();
                for (int a : copy) {
                    window.add(a);
                }
                score += evaluate(window,player);
            }
        }

        //左斜
        for (int i = 0; i < ROWS - 3; i++) {
            for (int j = 0; j < COLUMNS - 3; j++) {
                int[] copy = {board.cells[i][j],board.cells[i+1][j+1],board.cells[i+2][j+2],board.cells[i+3][j+3]};
                ArrayList<Integer> window = new ArrayList<>();
                for (int a : copy) {
                    window.add(a);
                }
                score += evaluate(window,player);
            }
        }

        //右斜
        for (int i = 0; i < ROWS - 3; i++) {
            for (int j = 0; j < COLUMNS - 3; j++) {
                int[] copy = {board.cells[i+3][j],board.cells[i+3-1][j+1],board.cells[i+3-2][j+2],board.cells[i+3-3][j+3],};
                ArrayList<Integer> window = new ArrayList<>();
                for (int a : copy) {
                    window.add(a);
                }
                score += evaluate(window,player);
            }
        }

        
        return score;
    }

    public static move minimax(Board board,int depth,int alpha,int beta, boolean maximizingPlayer){
        int[] vaild_location = board.valid_location();
        if (depth == 0 || is_terminal_node(board)){
            if (is_terminal_node(board)){
                if (containsWin2(board,AI)){
                    return new move(-1,Integer.MAX_VALUE);
                } else if (containsWin2(board,human)) {
                    return new move(-1,Integer.MIN_VALUE);
                }else {
                    return new move(-1,0);
                }
            }
            else{
                return new move(-1,score(board,AI));
            }
        }

        if (maximizingPlayer){
            int value = Integer.MIN_VALUE;
            int column = vaild_location[new Random().nextInt(vaild_location.length)];
            for (int col: vaild_location) {
                Board bcopy = board.copy();
                int row = bcopy.getNextOpenRow(col);
                bcopy.setCell2(row,col,AI);
                int new_score = minimax(bcopy,depth-1,alpha,beta,false).getScore();
                if (new_score > value){
                    value = new_score;
                    column = col;
                }
                alpha = Math.max(alpha,value);
                if (alpha >= beta){
                    break;
                }
            }
            return new move(column,value);
        }
        else {
            int value = Integer.MAX_VALUE;
            int column = vaild_location[new Random().nextInt(vaild_location.length)];
            for (int col: vaild_location) {
                Board bcopy = board.copy();
                int row = bcopy.getNextOpenRow(col);
                bcopy.setCell2(row,col,human);
                int new_score = minimax(bcopy,depth-1,alpha,beta,true).getScore();
                if (new_score < value){
                    value = new_score;
                    column = col;
                }
                beta = Math.min(beta,value);
                if (alpha >= beta){
                    break;
                }
            }
            return new move(column,value);
        }
    }


}


