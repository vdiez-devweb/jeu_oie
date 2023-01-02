public class Main {
    public static void main(String[] args) {
        //commencer le jeu
        Game game = new Game();
        Player player1 = new Player("X",0);
        //lancer les dés
        player1.setPosition(firstThrowDice(game));
        //tester si combinaison speciale
        game.initBoard();
        game.setBoardPosition(player1.getPosition());
        //continuer le jeu jusqu'à ce qu'on arrive en 63
        while (!game.gameOver(player1)){
            int sum = throwDice(game);
            int newPosition = player1.getPosition()+sum;
            while (game.isSpecialBox(newPosition)){
                if (game.isGooseBox(newPosition)){
                    newPosition = newPosition + sum;
                    System.out.println("Tu es tombé sur une case oie, tes dés sont doublés !");
                }
                if (newPosition>game.getBoardSize()){
                    int advanceMax = game.getBoardSize() - player1.getPosition();
                    int goBack = sum - advanceMax;
                    newPosition = game.getBoardSize() - goBack;
                }
            }

            player1.setPosition(newPosition);
            System.out.println("nouvelle position : " + newPosition);

            game.initBoard();
            game.setBoardPosition(player1.getPosition());
        }
        System.out.println("Bravo " + player1 + ", tu as gagné en " + game.getGameLap() + " tours !");
    }
    public static int firstThrowDice(Game currentGame){
//        Game game = new Game();
        int dice1 = currentGame.getRandomDice();
        int dice2 = currentGame.getRandomDice();
        int sumDice = dice1 + dice2;
        currentGame.setGameLap(currentGame.getGameLap()+1);
        System.out.println("tour n° : " + currentGame.getGameLap() + "          lancé de dés : " + dice1 + " | " + dice2 + "         total = " + sumDice);

        if (sumDice == 9){
            if ((dice1 == 6 || dice2 == 6)) {
                System.out.println("Coup Special ! nouvelle position : 26");
                return 26;
            }else {
                System.out.println("Coup Special ! nouvelle position : 53");
                return 53;
            }
        }else if (sumDice == 6){
            System.out.println("Coup Special ! nouvelle position : 12");
            return 12;
        }else{
            System.out.println("nouvelle position : " + sumDice);
            return sumDice;
        }
    }
    public static int throwDice(Game currentGame){
        //Game game = new Game();
        int dice1 = currentGame.getRandomDice();
        int dice2 = currentGame.getRandomDice();
        int sumDice = dice1 + dice2;
        currentGame.setGameLap(currentGame.getGameLap()+1);
        System.out.println("tour n° : " + currentGame.getGameLap() + "          lancé de dés : " + dice1 + " | " + dice2 + "         total = " + sumDice);

        return sumDice;
    }
}
