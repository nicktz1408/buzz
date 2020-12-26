package GameLogic;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class QuestionRepository {
    private String filename;

    private ArrayList<String []> questions;

    private int index = -1;

    public QuestionRepository() throws IOException {
        filename = "Questions.txt";
        this.questions = new ArrayList<String[]>();

        this.parseFile();
        Collections.shuffle(questions, new Random(System.currentTimeMillis()));
    }

    public String[] getSingleRandomQuestion() {
        index = (index+1)%this.questions.size();
        return questions.get(index);
    }

    public ArrayList<String []> getRandomQuestionBatch(int batchSize) {
        ArrayList<String []> batch = new ArrayList<String []>();

        for(int i = 1; i <= batchSize; i++) {
            batch.add(getSingleRandomQuestion());
        }

        return batch;
    }

    private void parseFile() throws IOException {
        String currentDirectory = new File(".").getCanonicalPath();

        BufferedReader br = new BufferedReader(new FileReader(currentDirectory + "/src/Questions.txt"));
        String data;
        while ((data = br.readLine()) != null) {

            String[] arrOfStr = data.split("-", 7);
            questions.add(arrOfStr);
        }
        br.close();
    }
}