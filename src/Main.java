import java.util.HashMap;
import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        final int numberOfRounds = 5;
        int currentRound = 1;


        DataBase database1 = new DataBase();
        DataBase database2 = new DataBase();
        DataBase database3 = new DataBase();
        DataBase database4 = new DataBase();
        DataBase database5 = new DataBase();
        DataBase database6 = new DataBase();
        DataBase database7 = new DataBase();

        HashMap<Integer, DataBase> game = new HashMap<>();

        database1.setTheQuetionCategory("Ποιός αιχμαλώτισε τον Πατριάρχη Ιεροσολύμων?", "Hostory");
        database1.setTheAnswers("Αντίχοος Β'","Βαρτάσης","Ρατισλάβος","Χοσρόης");
        database1.setTheRightAnswer("Χοσρόης");
        game.put(1, database1);

        database2.setTheQuetionCategory("Ποιοί πολιόρκησαν 2 φορές την Κωνσταντινούπολη χωρίς να την κατακτήσουν?", "History");
        database2.setTheAnswers("Βούλγαροι","Άβαροι","Πέρσες","Άραβες");
        database2.setTheRightAnswer("Άραβες");
        game.put(2, database2);

        database3.setTheQuetionCategory("Ποιός αυτοκράτορας ζήτησε την βοήθεια των Ρώσων για να αντιμετωπίσει τους Βολγαρους?", "History");
        database3.setTheAnswers("Νικηφόρος Φωκάς","Λέων Γ' ο Ίσαυρος","Βασίλειος Β'","Ιωάννης Τσιμισκής");
        database3.setTheRightAnswer("Νικηφόρος Φωκάς");
        game.put(3, database3);

        database4.setTheQuetionCategory("Ποιός έδιωξε τους Ρώσους από τα βυζαντινά εδάφη?", "History");
        database4.setTheAnswers("Νικηφόρος Φωκάς","Λέων Γ' Ίσαυρος","Ηράκλειος","Ιωάννης Τσιμισκής");
        database4.setTheRightAnswer("Ιωάννης Τσιμισκής");
        game.put(4, database4);

        database5.setTheQuetionCategory("Ποιοός στρατηγός νίκησε τους Βούλγαρους στο Σπερχειό ποταμό?", "History");
        database5.setTheAnswers("Νικηφόρος Φωκάς","Λέων Γ' Ίσαυρος","Νικηφόρος Ουρανός","Ιωάννης Τσιμισκής");
        database5.setTheRightAnswer("Νικηφόρος Ουρανός");
        game.put(5, database5);

        database6.setTheQuetionCategory("Στα χρόνια ποιανού βασιλιά εκχριστιανίστηκαν οι Ρώσοι?", "History");
        database6.setTheAnswers("Νικηφόρος Φωκάς","Λέων Γ' Ίσαυρος","Βασίλειος Β'","Ιωάννης Τσιμισκής");
        database6.setTheRightAnswer("Βασίλειος Β'");
        game.put(6, database6);

        database7.setTheQuetionCategory("Βούλγαρος βασιλιάς που με τον στρατό του έφτασε ως την Πελοπόννησο", "History");
        database7.setTheAnswers("Βόγορη","Κρούμον","Σαμουήλ","Συμεών");
        database7.setTheRightAnswer("Σαμουήλ");
        game.put(7, database7);




        int playerPoints = 0;
        System.out.println("Hello, let's play Buzz!");
        System.out.println("Do you want to play?");
        System.out.println("Press [yes/no]: ");


        Scanner input = new Scanner(System.in);
        String answer = input.nextLine();

        if (answer.equals("yes")) {

            System.out.println("Which type of game do you want to play? Σωστή Απάντηση or Ποντάρισμα?");

            String answer1 = input.nextLine();

            if (answer1.equals("Σωστή Απάντηση")) {
                int i = 1;
                boolean play = true;
                Random rand = new Random();
                while (play && currentRound <= numberOfRounds) {
                    System.out.println("Quetion #" + i + ": ");
                    int randomNumber = rand.nextInt(game.size());


                    String question = game.get(randomNumber+1).getTheQuetion();
                    System.out.println(question);
                    (game.get(randomNumber+1)).randomOrderAnswers();


                    System.out.println("Give me your answer [1,2,3,4]: ");

                    int b = input.nextInt();
                    String userAnswer = (String) game.get(randomNumber+1).getTheAnswers().get(b-1);
                    if(userAnswer.equals(game.get(randomNumber+1).getTheRightAnswer())){
                        System.out.println("Your answer is correct!");
                        playerPoints += 1000;
                    } else{
                        System.out.println("Your answer is incorrect");
                    }
                    currentRound++;
                    i++;
                }
                System.out.println("Your Points are : "+playerPoints);


            }else if (answer1.equals("Ποντάρισμα")) {
                int i = 1;
                int betPoints;
                boolean play = true;
                Random rand = new Random();
                while (play && currentRound <= numberOfRounds) {

                    int randomNumber = rand.nextInt(game.size());
                    String cat = game.get(randomNumber+1).getCategory();
                    System.out.println("The question is about "+cat);
                    System.out.println("How points do you like to bet? 150, 500, 750 or 1000");
                    betPoints = input.nextInt();
                    System.out.println("Quetion #" + i + ": ");



                    String question = game.get(randomNumber+1).getTheQuetion();
                    System.out.println(question);
                    (game.get(randomNumber+1)).randomOrderAnswers();


                    System.out.println("Give me your answer [1,2,3,4]: ");

                    int b = input.nextInt();
                    String userAnswer = (String) game.get(randomNumber+1).getTheAnswers().get(b-1);
                    if(userAnswer.equals(game.get(randomNumber+1).getTheRightAnswer())){
                        System.out.println("Your answer is correct!");
                        playerPoints += betPoints;
                    } else{
                        System.out.println("Your answer is incorrect");
                        playerPoints -= betPoints;
                    }
                    currentRound++;
                }
                System.out.println("Your Points are : "+playerPoints);
                i++;


            }else{
                System.out.println("Wrong Game");
                System.exit(1);
            }
        } else {
            System.out.println("GoodBye!");
            System.exit(0);
        }

    }
}
