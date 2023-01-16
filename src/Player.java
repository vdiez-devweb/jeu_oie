public class Player {
    private String name;
    private int position;
    private String interactions; // can be "actif", "puis", "prison"
    private int lapToStayAtHotel;
    private int round;
    private int control; // permet d'indiquer que le coup recherché à eu lieu pendant le jeu si à 1'

    public Player(String name) {
        this.name = name;
        this.position = 0;
        this.lapToStayAtHotel = 0;
        this.interactions = "actif";
        this.control = 0;
        this.round = 0;
    }
    public int getLapToStayAtHotel() {
        return lapToStayAtHotel;
    }
    public void setLapToStayAtHotel(int lapToStayAtHotel) {
        this.lapToStayAtHotel = lapToStayAtHotel;
    }
    public int getPosition() {
        return position;
    }
    public void setPosition(int position) {
        this.position = position;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getRound() {
        return round;
    }
    public void setRound(int round) {
        this.round = round;
    }
    public int getControl() {
        return control;
    }
    public void setControl(int control) {
        this.control = control;
    }
}
