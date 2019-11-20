public class Game2048 {
    
    public static void main(String[] args) {
        
        TileBoard test = new TileBoard();
        test.drawBoard();
        test.generateTwoTiles();
        test.drawTiles();
        while (!test.isGameOver()) {
            Tile[][] newBoard = test.copyBoard();
            if (PennDraw.hasNextKeyTyped()) {
                char pressed = PennDraw.nextKeyTyped();
                if (pressed == 'a') {
                    System.out.println("hi");
                    test.shiftLeft();
                }
                else if (pressed == 'd') {
                    System.out.println("hi");
                    test.shiftRight();
                }
                else if (pressed == 's') {
                    System.out.println("hi");
                    test.shiftDown();
                }
                else if (pressed == 'w') {
                    System.out.println("hi");
                    test.shiftUp();
                }
                test.print();
                System.out.println(test.compareBoard(newBoard));
                
                if (!test.isGameOver()) {
                    if (test.compareBoard(newBoard) == false) {
                        test.generateOneTile();
                        test.drawTiles();
                        test.increaseMoves();
                        test.drawTopBoard();
                        test.drawMoves();
                    } else {
                        test.drawTopBoard();
                        test.drawMoves();
                    }
                }                
            }           
        }
        PennDraw.setPenColor(PennDraw.BLUE);
        PennDraw.setFontSize(70);
        PennDraw.text(0.5, 0.7, "GAME OVER");
    }
}