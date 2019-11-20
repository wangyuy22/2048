public class TileBoard {
    
    private Tile[][] board;
    private int moves = 0;
    
    public TileBoard() {
        board = new Tile[4][4];
    }
    
    public void drawBoard() {
        //Drawing Background
        PennDraw.setPenColor(PennDraw.DARK_GRAY);
        PennDraw.filledSquare(0.5, 0.5, 0.5);
        
        //Drawing empty tile blocks
        PennDraw.setPenColor(PennDraw.LIGHT_GRAY);
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                PennDraw.filledSquare(0.14 + j * 0.24, 0.14 + i * 0.24, 0.1);
            }
        }
        
    }
    
    public void drawEmptyTile(int col, int row) {
        PennDraw.setPenColor(PennDraw.LIGHT_GRAY);
        PennDraw.filledSquare(0.14 + col * 0.24, 0.14 + row * 0.24, 0.1);
    }
    
    public void drawTiles() {
        for (int col = 0; col < 4; col++) {
            for (int row = 0; row < 4; row++) {
                if (board[col][row] != null) {
                    board[col][row].draw(col, row);
                } else {
                    drawEmptyTile(col, row);
                }
            }
        }
    }
    
    public void drawTopBoard() {
        PennDraw.setPenColor(PennDraw.DARK_GRAY);
        PennDraw.filledRectangle(0.5, 0.98, 0.5, 0.015);
    }
    
    public void drawMoves() {
        PennDraw.setFontSize(15);
        PennDraw.setPenColor(PennDraw.WHITE);
        PennDraw.text(0.85, 0.98, "Moves: " + moves);
    }
    
    public void increaseMoves() {
        moves++;
    }
    
    public void generateOneTile() {
        int xVal = (int) ((Math.random() / 2.5) * 10);
        int yVal = (int) ((Math.random() / 2.5) * 10);
        double prob = Math.random();
        int number;
        if (prob < 0.5) {
            number = 2;
        } else {
            number = 4;
        }
        if (board[xVal][yVal] == null) {
            board[xVal][yVal] = new Tile(number);
        } else {
            generateOneTile();
        }
    }
    
    public void generateTwoTiles() {
        generateOneTile();
        generateOneTile();
    }
    
    public void print() {
        for (int row = 0; row < 4; row++) {
            String text = "";
            for (int col = 0; col < 4; col ++) {
                if (board[col][row] == null) {
                    text = text + "null" + ", ";
                } else {
                    text = text + board[col][row].getValue() + ", ";
                }
            }
            //System.out.println(text);
        }
    }
    
    public boolean compareBoard(Tile[][] o) {
        for (int row = 0; row < 4; row++) { 
            for (int col = 0; col < 4; col++) {
                if (board[col][row] != o[col][row]) {
                    return false;
                }
            }
        }
        return true;
    }
    
    public Tile[][] copyBoard() {
        Tile[][] newBoard = new Tile[4][4];
        for (int row = 0; row < 4; row++) { 
            for (int col = 0; col < 4; col++) {
                newBoard[col][row] = board[col][row];
            }
        }
        return newBoard;
    }
    
    public void shiftLeft() {
        
        //Moving every tile to the left
        for (int row = 0; row < 4; row++) { 
            for (int col = 1; col < 4; col++) {
                if (board[col][row] != null) {
                    int min = col;
                    for (int check = col - 1; check > -1; check--) {
                        if (board[check][row] == null) {
                            min = check;
                        }
                    }
                    if (min != col) {
                        board[min][row] = board[col][row];
                        board[col][row] = null;
                    }
                }
            }
        }
//Combining like tiles
        for (int row = 0; row < 4; row++) { 
            for (int col = 0; col < 3; col++) {
                if (board[col][row] != null && board[col+1][row] != null) {
                    if (board[col][row].getValue() == board[col+1][row].getValue()) {
                        board[col][row] = board[col][row].combine(board[col+1][row]);
                        board[col+1][row] = null;
                    }
                }
            } 
        }
        //Moving every tile to the left
        for (int row = 0; row < 4; row++) { 
            for (int col = 1; col < 4; col++) {
                if (board[col][row] != null) {
                    int min = col;
                    for (int check = col - 1; check > -1; check--) {
                        if (board[check][row] == null) {
                            min = check;
                        }
                    }
                    if (min != col) {
                        board[min][row] = board[col][row];
                        board[col][row] = null;
                    }
                }
            }
        }
        
        drawTiles();
    }
    
    public void shiftRight() {
        
        
        //Moving every tile to the right
        for (int row = 0; row < 4; row++) { 
            for (int col = 2; col > -1; col--) {
                if (board[col][row] != null) {
                    int min = col;
                    for (int check = col + 1; check < 4; check++) {
                        if (board[check][row] == null) {
                            min = check;
                        }
                    }
                    if (min != col) {
                        board[min][row] = board[col][row];
                        board[col][row] = null;
                    }
                }
            }
        }
        //Combining like tiles
        for (int row = 0; row < 4; row++) { 
            for (int col = 3; col > 0; col--) {
                if (board[col][row] != null && board[col-1][row] != null) {
                    if (board[col][row].getValue() == board[col-1][row].getValue()) {
                        board[col][row] = board[col][row].combine(board[col-1][row]);
                        board[col-1][row] = null;
                    }
                }
            } 
        }
        
        //Moving every tile to the right
        for (int row = 0; row < 4; row++) { 
            for (int col = 2; col > -1; col--) {
                if (board[col][row] != null) {
                    int min = col;
                    for (int check = col + 1; check < 4; check++) {
                        if (board[check][row] == null) {
                            min = check;
                        }
                    }
                    if (min != col) {
                        board[min][row] = board[col][row];
                        board[col][row] = null;
                    }
                }
            }
        }
        drawTiles();
    }
    
    public void shiftUp() {
        
        //Moving every tile down
        for (int col = 0; col < 4; col++) { 
            for (int row = 2; row > -1; row--) {
                if (board[col][row] != null) {
                    int min = row;
                    for (int check = row + 1; check < 4; check++) {
                        if (board[col][check] == null) {
                            min = check;
                        }
                    }
                    if (min != row) {
                        board[col][min] = board[col][row];
                        board[col][row] = null;
                    }
                }
            }
        }
        //Combining like tiles
        for (int col = 0; col < 4; col++) { 
            for (int row = 3; row > 0; row--) {
                if (board[col][row] != null && board[col][row-1] != null) {
                    if (board[col][row].getValue() == board[col][row-1].getValue()) {
                        board[col][row] = board[col][row].combine(board[col][row-1]);
                        board[col][row-1] = null;
                    }
                }
            } 
        }
        
        //Moving every tile down
        for (int col = 0; col < 4; col++) { 
            for (int row = 2; row > -1; row--) {
                if (board[col][row] != null) {
                    int min = row;
                    for (int check = row + 1; check < 4; check++) {
                        if (board[col][check] == null) {
                            min = check;
                        }
                    }
                    if (min != row) {
                        board[col][min] = board[col][row];
                        board[col][row] = null;
                    }
                }
            }
        }
        drawTiles();
    }
    
    public void shiftDown() {
        
        
        //Moving every tile down
        for (int col = 0; col < 4; col++) { 
            for (int row = 1; row < 4; row++) {
                if (board[col][row] != null) {
                    int min = row;
                    for (int check = row - 1; check > -1; check--) {
                        if (board[col][check] == null) {
                            min = check;
                        }
                    }
                    if (min != row) {
                        board[col][min] = board[col][row];
                        board[col][row] = null;
                    }
                }
            }
        }
        //Combining like tiles
        for (int col = 0; col < 4; col++) { 
            for (int row = 0; row < 3; row++) {
                if (board[col][row] != null && board[col][row+1] != null) {
                    if (board[col][row].getValue() == board[col][row+1].getValue()) {
                        board[col][row] = board[col][row].combine(board[col][row+1]);
                        board[col][row+1] = null;
                    }
                }
            } 
        }
        
        //Moving every tile down
        for (int col = 0; col < 4; col++) { 
            for (int row = 1; row < 4; row++) {
                if (board[col][row] != null) {
                    int min = row;
                    for (int check = row - 1; check > -1; check--) {
                        if (board[col][check] == null) {
                            min = check;
                        }
                    }
                    if (min != row) {
                        board[col][min] = board[col][row];
                        board[col][row] = null;
                    }
                }
            }
        }
        drawTiles();
    }
    
    private boolean isFull() {
        for (int col = 0; col < 4; col++) { 
            for (int row = 0; row < 4; row++) {
                if (board[col][row] == null) {
                    return false;
                }
            }
        }
        return true;
    }
    
    public boolean isGameOver() {
        if (!isFull()) {
            return false;
        }
        for (int col = 1; col < 3; col++) { 
            for (int row = 1; row < 3; row++) {
                if (board[col][row].getValue() == board[col][row + 1].getValue()
                    || board[col][row].getValue() == board[col][row - 1].getValue() ||
                    board[col][row].getValue() == board[col + 1][row].getValue() || 
                    board[col][row].getValue() == board[col - 1][row].getValue()) {
                    return false;
                }
            }
        }
        if (board[0][0].getValue() == board[0][1].getValue() ||
            board[0][0].getValue() == board[1][0].getValue()) {
            return false;
        }
        
        if (board[0][3].getValue() == board[0][2].getValue() ||
            board[0][3].getValue() == board[1][3].getValue()) {
            return false;
        }
        
        if (board[3][0].getValue() == board[3][1].getValue() ||
            board[3][0].getValue() == board[2][0].getValue()) {
            return false;
        }
        
        if (board[3][3].getValue() == board[3][2].getValue() ||
            board[3][3].getValue() == board[2][3].getValue()) {
            return false;
        }
        return true;
    }   
}