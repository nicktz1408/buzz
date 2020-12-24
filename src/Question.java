import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class Question {
    private String filename;



    public Question(){
        filename = "Questions.txt";
    }


    public ArrayList<String> returnRandomOrder() throws IOException {
        ArrayList<String> questions = new ArrayList<String>();
        BufferedReader br = new BufferedReader(new FileReader("/home/manolis/Desktop/buzzquizworld-thelamogia/Qustions"));
        String data;
        while ((data = br.readLine()) != null) {
            String[] arrOfStr = data.split(" ", 2);
            questions.add(arrOfStr[0]);
        }
        br.close();
        Collections.shuffle(questions, new Random(System.currentTimeMillis()));
        return questions;
    }
}