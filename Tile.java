public class Tile {
    
    private int value;
    
    public Tile(int value) {
        this.value = value;
    }
    
    public Tile(int valueX, int valueY) {
        this.value = valueX + valueY;
    }
    
    public int getValue() {
        return value;
    }
    
    public void draw(int col, int row){
        if (value == 2) {
            PennDraw.setPenColor(PennDraw.WHITE);
        } else if (value == 4) {
            PennDraw.setPenColor(255, 238, 168);
        } else if (value == 8) {
            PennDraw.setPenColor(255, 169, 84);
        } else if (value == 16) {
            PennDraw.setPenColor(255, 127, 0);
        } else if (value == 32) {
            PennDraw.setPenColor(255, 94, 89);
        } else if (value == 64) {
            PennDraw.setPenColor(247, 67, 12);
        } else if (value == 128) {
            PennDraw.setPenColor(255, 226, 86);
        } else if (value == 256) {
            PennDraw.setPenColor(255, 212, 0);
        } else if (value == 512) {
            PennDraw.setPenColor(255, 212, 0);
        } else if (value == 1024) {
            PennDraw.setPenColor(255, 212, 0);
        } else if (value == 2048) {
            PennDraw.setPenColor(255, 212, 0);
        }
        
        PennDraw.filledSquare(0.14 + col * 0.24, 0.14 + row * 0.24, 0.1);
        PennDraw.setPenColor(PennDraw.BLACK);
        
        if (value < 1000) {
            PennDraw.setFontSize(50);
        } else if (value < 10000) {
            PennDraw.setFontSize(30);
        } else {
            PennDraw.setFontSize(15);
        }
        PennDraw.text(0.14 + col * 0.24, 0.12 + row * 0.24, "" + value);
    }
    
    public Tile combine(Tile o) {
        Tile newCombined = new Tile(this.value, o.value);
        return newCombined;
    }
}

