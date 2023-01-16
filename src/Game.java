import java.util.Arrays;
public class Game {
    private final int boardSize = 63;
    private String [] boardGame = new String[boardSize+1];
    private String [] boardPosition = new String[boardSize+1];

    private int dice1;
    private int dice2;
    private int gameLap = 0;
    private int nbPlayer = 1;

    private Integer specialsBoxes[]={42,58,9,18,27,36,45,54,63};
    //private Integer specialsBoxes[]={0,3,19,42,52,58,9,18,27,36,45,54};
    private String messageSpecialBox="";
    public int getDice1() {
        return dice1;
    }
    public int getDice2() {
        return dice2;
    }

    public void setDice1(int dice1) {
        this.dice1 = dice1;
    }

    public void setDice2(int dice2) {
        this.dice2 = dice2;
    }

    public int getBoardSize() {
        return boardSize;
    }
    public int getGameLap() {
        return gameLap;
    }
    public void setGameLap(int gameLap) {
        this.gameLap = gameLap;
    }
    public int getNbPlayer() {
        return nbPlayer;
    }
    public void setNbPlayer(int nbPlayer) {
        this.nbPlayer = nbPlayer;
    }
    public String getMessageSpecialBox() {
        return messageSpecialBox;
    }
    public void setMessageSpecialBox(String messageSpecialBox) {
        this.messageSpecialBox = messageSpecialBox;
    }

    public int getRandomDice(){
        int max = 6;
        int min = 1;
        int range = max - min + 1;
        int random = (int)(Math.random() * range) + min;

        return random;
    }

    public void initBoard(){
        for ( int i = 0; i <= this.boardSize; ++i){
            if (i < 10){
                this.boardGame[i] = i + "  ";
            }else{
                this.boardGame[i] = i + " ";
            }
            System.out.print(boardGame[i]);
        }
        System.out.println("");
    }
    public void setBoardPosition(int position){
        for ( int i = 0; i <= this.boardSize; ++i){
            if (i==position){
                this.boardPosition[i] = "X |";
            }else{
                this.boardPosition[i] = "  |";
            }
            System.out.print(boardPosition[i]);
        }
        System.out.println("");
        System.out.println("");
    }
    public boolean gameOver(Player player){
        return (player.getPosition() == boardSize);
    }

    public boolean isSpecialBox (int position){
        //if (isGooseBox(position)){
        //    return true;

        if (Arrays.asList(specialsBoxes).contains(position)){
            return true;
        }else if(position>getBoardSize()){
            return true;
        }else {
            return false;
        }
    }
    public boolean isGooseBox(int position){
        if (position != this.boardSize && position != 0) {
            return position % 9 == 0;
        }else{
            return false;
        }
    }
}
