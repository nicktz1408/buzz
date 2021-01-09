package GameLogic;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

/**
 * Singleton class that wraps the functionality of getting random questions from the corresponding file on the disk
 */
public class QuestionRepository {

    private final ArrayList<String []> questions;

    private int index = -1;

    private static QuestionRepository singleInstance = null;

    /**
     * Returns a reference to our internal QuestionRepository object, in accordance to the Singleton design
     * pattern (allows us to use the same object from many different places without breaking the order of the
     * questions - for more details, refer to https://refactoring.guru/design-patterns/singleton)
     * @return a reference to our internal object
     */
    public static QuestionRepository getInstance() {
        if (singleInstance == null)
            singleInstance = new QuestionRepository();

        return singleInstance;
    }

    /**
     * Loads the Questions from the disk to the working memory
     */
    protected QuestionRepository() {
        this.questions = new ArrayList<>();

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

    /**
     *
     * @return a single, random Question
     */
    public Question getSingleRandomQuestion() {
        index = (index+1)%this.questions.size();
        return this.buildQuestion(questions.get(index));
    }

    /**
     *
     * @param batchSize the size of Questions to be returned
     * @return a batch of random Questions
     */
    public ArrayList<Question> getRandomQuestionBatch(int batchSize) {
        ArrayList<Question> batch = new ArrayList<>();

        for(int i = 1; i <= batchSize; i++) {
            batch.add(getSingleRandomQuestion());
        }

        return batch;
    }

    /**
     * Utility function that loads the entire file from the disk and stores it to the questions ArrayList
     */
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