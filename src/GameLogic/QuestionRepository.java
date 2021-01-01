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

    private static QuestionRepository singleInstance = null;

    // static method to create instance of Singleton class
    public static QuestionRepository getInstance() {
        if (singleInstance == null)
            singleInstance = new QuestionRepository();

        return singleInstance;
    }

    private QuestionRepository() {
        filename = "Questions.txt";
        this.questions = new ArrayList<String[]>();

        this.parseFile();
        Collections.shuffle(questions, new Random(System.currentTimeMillis()));
    }

    Question buildQuestion(String[] questionData) {
        if(questionData.length == 7) { // normal question
            return Question.builder()
                    .setCategory(questionData[0])
                    .setQuestionText(questionData[1])
                    .addAnswer(questionData[2])
                    .addAnswer(questionData[3])
                    .addAnswer(questionData[4])
                    .addAnswer(questionData[5])
                    .setRightAnswerIndex(Integer.parseInt(questionData[6]))
                    .build();
        } else { // question with image
            return QuestionWithImage.builder()
                    .setCategory(questionData[0])
                    .setQuestionText(questionData[1])
                    .addAnswer(questionData[2])
                    .addAnswer(questionData[3])
                    .addAnswer(questionData[4])
                    .addAnswer(questionData[5])
                    .setRightAnswerIndex(Integer.parseInt(questionData[6]))
                    .setImagePath(questionData[7])
                    .build();
        }
    }

    public Question getSingleRandomQuestion() {
        index = (index+1)%this.questions.size();
        return this.buildQuestion(questions.get(index));
    }

    public ArrayList<Question> getRandomQuestionBatch(int batchSize) {
        ArrayList<Question> batch = new ArrayList<>();

        for(int i = 1; i <= batchSize; i++) {
            batch.add(getSingleRandomQuestion());
        }

        return batch;
    }

    private void parseFile() {
        try {
            String currentDirectory = new File(".").getCanonicalPath();

            BufferedReader br = new BufferedReader(new FileReader(currentDirectory + "/src/Questions.txt"));
            String data;
            while ((data = br.readLine()) != null) {

                String[] arrOfStr = data.split("-");
                questions.add(arrOfStr);
            }
            br.close();
        } catch(IOException e) {
            System.out.println("An error has occurred while reading the Questions file!");
        }
    }
}