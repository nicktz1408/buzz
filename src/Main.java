import java.util.*;

public class Main {
    /**
     * The entry point of our command-line application
     * @param args potential arguments provided to the execution command of our appliation on the terminal
     */
    public static void main(String[] args) {
        final int numberOfRounds = 5;
        final int numberOfQuetions = 4;
        int currentRound = 0;

        // Initialize the questions
        DataBase database1 = new DataBase();
        DataBase database2 = new DataBase();
        DataBase database3 = new DataBase();
        DataBase database4 = new DataBase();
        DataBase database5 = new DataBase();
        DataBase database6 = new DataBase();
        DataBase database7 = new DataBase();
        DataBase database8 = new DataBase();
        DataBase database9 = new DataBase();
        DataBase database10 = new DataBase();
        DataBase database11= new DataBase();
        DataBase database12 = new DataBase();
        DataBase database13 = new DataBase();
        DataBase database14 = new DataBase();
        DataBase database15 = new DataBase();
        DataBase database16 = new DataBase();
        DataBase database17 = new DataBase();
        DataBase database18 = new DataBase();
        DataBase database19 = new DataBase();
        DataBase database20 = new DataBase();

        HashMap<Integer, DataBase> game = new HashMap<>();

        // Setup the questions
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

        database5.setTheQuetionCategory("Ποιός στρατηγός νίκησε τους Βούλγαρους στο Σπερχειό ποταμό?", "History");
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

        database8.setTheQuetionCategory("Ποιοί δύο λαοίσυμμάχησαν ενάντια στου Βυζαντινούς?", "History");
        database8.setTheAnswers("Άβαροι ικαι Πέρσες","Πέρσες και Σλάβοι","Άραβες και Πέρσες","Άβαροι και Άραβες");
        database8.setTheRightAnswer("Άβαροι και Πέρσες");
        game.put(8, database8);

        database9.setTheQuetionCategory("Ο Ηράκλειος πριν γίνει αυτοκράτορας ήταν: ", "History");
        database9.setTheAnswers("γνωστός Ακρίτας","αρχιστράτηγος στην Αίγυπτο","Πατριάρχης","διοκητής της Καρχηδόνας");
        database9.setTheRightAnswer("διοικητής της Καρχηδόνας");
        game.put(9, database9);

        database10.setTheQuetionCategory("Τι δεν έκανε ο αυτοκράτορας Ηράκλειος με τα λεφτά που πήρε από την εκκλησία?", "History");
        database10.setTheAnswers("Επιδιόρθωσε τα τείχη","Συγκέντρωσε τρόφιμαγια τους κατοίκους της Πόλης","Μοίρασε γη στους Ακρίτες","Χοσρόης");
        database10.setTheRightAnswer("Έκλεισε συμφωνία ειρήνης με τους Άραβες");
        game.put(10, database10);

        database11.setTheQuetionCategory("Πως ονομαζόταν ο Σλάβος ηγεμόνας που ζήτησε απ' τους Βυζαντινούς να στείλουν ιεραποστόλους?", "History");
        database11.setTheAnswers("Χαγάνος","Ρατισλάβος","Σαμουήλ","Ραφαήλ");
        database11.setTheRightAnswer("Ρατισλάβος");
        game.put(11, database11);

        database12.setTheQuetionCategory("Τι έφεραν μαζί τους πίσω οι Βυζαντινοί από την Περσία?", "History");
        database12.setTheAnswers("Τον Τίμιο Σταυρό","Το Σταυρό του Κωνσταντίνου","Θησαυρούς από την Περσία","Αιχμάλωτη τη βασιλική οικογένεια των Περσών");
        database12.setTheRightAnswer("Τον Τίμιο Σταυρό");
        game.put(12, database12);

        database13.setTheQuetionCategory("Ποιός ήταν ο Πατριάρχης που έδωσε χρήματα και σκεύη της εκκλησίας στον αυτοκράτορα του Βυζαντίου για να τον βοηθήσει στις δύσκολες στιγμές που περνούσε η αυτοκρατορία?", "History");
        database13.setTheAnswers("Ο Πατριάρχης Φώτιος","Ο Πατριάρχης Χρυσόστομος","Ο Πατριάρχης Γεννάδιος","Ο Πατριάρχης Σέργιος");
        database13.setTheRightAnswer("Ο Πατριάρχης Σέργιος");
        game.put(13, database13);

        database14.setTheQuetionCategory("Ο Ακάθιστος Ύμνος γράφτηκε προς τιμήν?", "History");
        database14.setTheAnswers("της Παναγίας","του Χριστού","προς τιμήν του προστάτη αγίου της Κων/πολης","του αυτοκράτορα");
        database14.setTheRightAnswer("της Παναγίας");
        game.put(14, database14);

        database15.setTheQuetionCategory("Ποιές δύο χώρες είχαν συμφωνίες ειρήμης με τους Βυζαντινούς, αλλά δεν τις τήρησαν?", "History");
        database15.setTheAnswers("Οι Πέρσες και οι Άβαροι","Οι Άραβες και οι Βούλγαροι","Οι Ρώσοι και οι Σλάβοι","Οι Άβαροι και οι Βούλγαροι");
        database15.setTheRightAnswer("Οι Άβαροι και οι Βούλγαροι");
        game.put(15, database15);

        database16.setTheQuetionCategory("Που ηττήθηκαν για δεύτερη φορά οι Άραβες (η πρώτη τους ήττα ήταν στην Κωνσταντινύπολη)?", "History");
        database16.setTheAnswers("Στη Νίκαια","Στο Πουατιέ","Στην Καρχηδόνα","Στην Ρώμη");
        database16.setTheRightAnswer("Στο Πουατιέ");
        game.put(16, database16);

        database17.setTheQuetionCategory("Linux is a: ", "Technology");
        database17.setTheAnswers("Simple Kernel","Microkernel","Hybrid Kernel","Monolithic Kernel");
        database17.setTheRightAnswer("Monolithic Kernel");
        game.put(17, database17);

        database18.setTheQuetionCategory("What does \"GPS\" stand for", "Technology");
        database18.setTheAnswers("Global Positioning System","Graphic Planetary Schematic","Ground Plan Scenario","General Polar Size");
        database18.setTheRightAnswer("Global Positioning System");
        game.put(18, database18);

        database19.setTheQuetionCategory("What is the name of the blue bird that is Twitter's logo?", "Technology");
        database19.setTheAnswers("Freddy","Larry","Manny","Tony");
        database19.setTheRightAnswer("Larry");
        game.put(19, database19);

        database20.setTheQuetionCategory("What is the closest star to the Earth, beside the Sun?", "Science");
        database20.setTheAnswers("Rigel","Proxima Centauri","Canopus","Arcturus");
        database20.setTheRightAnswer("Proxima Centauri");
        game.put(20, database20);

        // Shuffle the questions order
        ArrayList<Integer> randomQuetions = new ArrayList<>();
        for(int i=0;i<game.size();i++){
            randomQuetions.add(i+1);
        }

        Collections.shuffle(randomQuetions, new Random(System.currentTimeMillis()));



        // Start the game
        int playerPoints = 0;


        System.out.println("Hello, let's play Buzz!\n");
        System.out.println("Do you want to play?");
        System.out.println("Press [yes/no]: ");


        Scanner input = new Scanner(System.in);
        String answer = input.nextLine();

        // Main logic of the game
        if (answer.equals("yes")) {
            while (currentRound < numberOfRounds) { // counting of the # of rounds
                System.out.println("Round "+(currentRound+1));
                System.out.println("Which type of game do you want to play? Σωστή Απάντηση or Ποντάρισμα?[Σωστή Απάντηση / Ποντάρισμα]");

                String answer1 = input.nextLine();

                int currentQuetions = 1;

                while(currentQuetions <= numberOfQuetions) { // counting the # of questions for each round
                    if (answer1.equals("Σωστή Απάντηση")) {
                        System.out.println("Quetion #" + currentQuetions + ": ");

                        String question = game.get(randomQuetions.get(4*currentRound+currentQuetions - 1)).getTheQuetion();
                        System.out.println(question);
                        (game.get(randomQuetions.get(4*currentRound+currentQuetions - 1))).randomOrderAnswers();

                        System.out.println("Give me your answer [1,2,3,4]: ");

                        int b = input.nextInt();
                        String userAnswer = (String) game.get(randomQuetions.get(4*currentRound+currentQuetions - 1)).getTheAnswers().get(b - 1);
                        if (userAnswer.equals(game.get(randomQuetions.get(4*currentRound+currentQuetions - 1)).getTheRightAnswer())) {
                            System.out.println("Your answer is correct!");
                            playerPoints += 1000;
                        } else {
                            System.out.println("Your answer is incorrect");
                            System.out.println("The correct answer is "+"'"+game.get(randomQuetions.get(4*currentRound+currentQuetions - 1)).getTheRightAnswer()+"'");
                        }
                        System.out.println("Current Score: " + playerPoints + "\n");
                    } else if (answer1.equals("Ποντάρισμα")) {
                        int betPoints;

                        String cat = game.get(randomQuetions.get(4*currentRound+currentQuetions - 1)).getCategory();
                        System.out.println("The question is about " + cat);
                        System.out.println("How points do you like to bet? 250, 500, 750 or 1000");

                        do {
                            betPoints = input.nextInt(); // prompt to input the desired bet
                        } while(betPoints != 250 && betPoints != 500 && betPoints != 750 && betPoints != 1000);

                        System.out.println("Quetion #" + currentQuetions + ": ");

                        String question = game.get(randomQuetions.get(4*currentRound+currentQuetions - 1)).getTheQuetion();
                        System.out.println(question);
                        (game.get(randomQuetions.get(4*currentRound+currentQuetions - 1))).randomOrderAnswers();

                        System.out.println("Give me your answer [1,2,3,4]: ");

                        int b = input.nextInt();
                        String userAnswer = (String) game.get(randomQuetions.get(4*currentRound+currentQuetions - 1)).getTheAnswers().get(b - 1);
                        if (userAnswer.equals(game.get(randomQuetions.get(4*currentRound+currentQuetions - 1)).getTheRightAnswer())) {
                            System.out.println("Your answer is correct!");
                            playerPoints += betPoints;
                        } else {
                            System.out.println("Your answer is incorrect");
                            System.out.println("The correct answer is "+"'"+game.get(randomQuetions.get(4*currentRound+currentQuetions - 1)).getTheRightAnswer()+"'");
                            playerPoints -= betPoints;
                        }
                        System.out.println("Current Score: " + playerPoints + "\n");
                    } else {
                        System.out.println("Wrong Game");
                        System.exit(1);
                    }

                    currentQuetions++;
                }
                currentRound++;
                input.nextLine();
            }
        }
        else{
            System.out.println("GoodBye!");
            System.exit(0);
        }
        System.out.println("Your Points are " + playerPoints);

    }
}
