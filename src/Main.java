public class Main {
    public static void main(String[] args) {
        //commencer le jeu
        Game game = new Game();
        Player player1 = new Player("Vivi");
        int newPosition = 0;

        //continuer le jeu jusqu'à ce qu'on arrive en 63
        while (!game.gameOver(player1)) {
            game.setGameLap(game.getGameLap() + 1);
            int lapToStay = player1.getLapToStayAtHotel();
            String messageHotel = "";
            boolean displayDiceMsg = true;
            /*
            if (lapToStay == 0) {
                blocked = false;
            } else {
                blocked = true;
            }*/
            //if (!blocked) {


                if (player1.getPosition() == 0) {
                    newPosition = resolvePositionFromZero(game);
                } else  {
                    newPosition = resolvePosition(game, player1);
                }


            if (newPosition == 19 && player1.getPosition() !=19) {
                messageHotel= "*** Case Hotel, passe tes 2 prochains tours";
                lapToStay = 2;
                player1.setLapToStayAtHotel(lapToStay);
            } else if (player1.getPosition() == 19 && lapToStay != 0){
                player1.setRound(player1.getRound() + 1);
                messageHotel= "*** Tu es bloqué à l'hôtel";
                newPosition = 19;
                switch (lapToStay) {
                    case 1:
                        messageHotel += ". Tu as passé tes 2 tours, tu pourra rejouer au prochain!";
                        lapToStay = 0;
                        displayDiceMsg = false;

                        break;
                    case 2:
                        messageHotel += ". Encore 1 tour à passer";
                        lapToStay = 1;
                        displayDiceMsg = false;

                        break;
                }
                player1.setLapToStayAtHotel(lapToStay);

            }
            /*
            if (game.getNbPlayer() > 1) {
                if (newPosition == 3) {
                    System.out.println("Case Puis, tu dois attendre qu'un autre joueur y tombe à ta place");
                }
                if (newPosition == 52) {
                    System.out.println("Case Prison, tu dois attendre qu'un autre joueur vienne te libérer !");
                }
            }*/
            if (newPosition == 19) {
                player1.setControl(1);
            }
            System.out.println("Tour n° " + game.getGameLap() + " de " + player1.getName() + " : " + "Position initiale : " + player1.getPosition());

            if (displayDiceMsg) {
                int sumDice = game.getDice1() + game.getDice2();
                System.out.println(" Lancé de dés : [" + game.getDice1() + "] + [" + game.getDice2() + "] = " + sumDice);
                if (game.getMessageSpecialBox() != "") { System.out.println(game.getMessageSpecialBox()); }
            }
            if ((player1.getPosition() == 19 && lapToStay!=0) || (newPosition == 19)){
                System.out.println(messageHotel);
            }
            System.out.println(" Nouvelle position : " + newPosition + " | LapToStay : " + lapToStay);

            player1.setPosition(newPosition);
            //if (lapToStay == 0){ // si on n'est pas bloqué à l'hôtel, on affiche le board
                game.initBoard();
                game.setBoardPosition(player1.getPosition());
            //}
            game.setMessageSpecialBox("");

        }
        System.out.println("Bravo " + player1.getName() + ", tu as gagné en " + game.getGameLap() + " tours !");
        if (player1.getControl() == 1) {
            System.out.println("le control recherché a eu lieu");
        }
    }

    public static int throwDice(Game currentGame) {

        int dice1 = currentGame.getRandomDice();
        currentGame.setDice1(dice1);
        int dice2 = currentGame.getRandomDice();
        currentGame.setDice2(dice2);
        //int dice2 = currentGame.getRandomDice();
        int sumDice = dice1 + dice2;
        //System.out.println("tour n° : " + currentGame.getGameLap() + "          lancé de dés : " + dice1 + " | " + dice2 + "         total = " + sumDice);

        return sumDice;
    }

    public static int resolvePositionFromZero(Game currentGame) {
        int dice1 = currentGame.getRandomDice();
        currentGame.setDice1(dice1);
        int dice2 = currentGame.getRandomDice();
        currentGame.setDice2(dice2);
        //int dice1 = currentGame.getRandomDice();
        //int dice2 = currentGame.getRandomDice();
        int sumDice = dice1 + dice2;
        //System.out.println("tour n° : " + currentGame.getGameLap() + "          lancé de dés : " + dice1 + " | " + dice2 + "         total = " + sumDice);

        if (sumDice == 9) {
            if ((dice1 == 6 || dice2 == 6)) {
                currentGame.setMessageSpecialBox(">>> Coup Special ! nouvelle position : 26");
                return 26;
            } else {
                currentGame.setMessageSpecialBox(">>> Coup Special ! nouvelle position : 53");
                return 53;
            }
        } else if (sumDice == 6) {
            currentGame.setMessageSpecialBox(">>> Coup Special ! nouvelle position : 12");
            return 12;
        } else {
            return sumDice;
        }
    }

    public static int resolvePosition(Game currentGame, Player player) {
        int sumDice = throwDice(currentGame);
        int initialPosition = player.getPosition();
        int newPosition = initialPosition + sumDice;

        while (currentGame.isSpecialBox(newPosition)) {
            if (newPosition > currentGame.getBoardSize()) {
                int advanceMax = currentGame.getBoardSize() - initialPosition;
                int goBack = sumDice - advanceMax;
                newPosition = currentGame.getBoardSize() - goBack;
            } else if (currentGame.isGooseBox(newPosition) && sumDice == 9) {
                currentGame.setMessageSpecialBox(">>> Tu es tombé sur une case oie, tes dés sont doublés (case " + newPosition + " somme des dés = 9)!");
                newPosition = newPosition + sumDice;

                return newPosition;
            } else if (currentGame.isGooseBox(newPosition)) {
                currentGame.setMessageSpecialBox(">>> Tu es tombé sur une case oie, tes dés sont doublés ! (case " + newPosition + ")");
                if (newPosition + sumDice > 63){
                    int advanceMax = currentGame.getBoardSize() - newPosition;
                    int goBack = sumDice - advanceMax;
                    newPosition = currentGame.getBoardSize() - goBack;
                }else {
                    newPosition = newPosition + sumDice;
                }
            } else if (newPosition == 42) {
                newPosition = 30;
                currentGame.setMessageSpecialBox(">>> Case 42, tu t'es perdu dans le labyrinthe ! retourne en case 30");
            } else if (newPosition == 58) {
                newPosition = 0;
                currentGame.setMessageSpecialBox(">>> Case 58, Tête de mort ! recommence de 0");
            } else if(newPosition == 63) {

                return newPosition;
            }
        }

        return newPosition;
    }
}
