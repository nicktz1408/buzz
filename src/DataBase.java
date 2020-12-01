import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;


/**
 * Class model for the Question
 */
public class DataBase {
    private ArrayList<String> answers;
    private String quetion;
    private String rightAnswer;
    private String category;

    public DataBase(){}

    /**
     * @return the ArrayList with the potential answers
     */
    public ArrayList getTheAnswers(){
        return answers;
    }

    /**
     * Function that sets the text of the answers of the question
     * @param a First answer to bet set
     * @param b Second answer to bet set
     * @param c Third answer to bet set
     * @param d Fourth answer to bet set
     */
    public void setTheAnswers(String a, String b, String c, String d){
        answers = new ArrayList<String>();
        answers.add(a);
        answers.add(b);
        answers.add(c);
        answers.add(d);
    }

    /**
     * Function that sets the text of the right answer
     * @param rightAnswer Right answer to bet set
     */
    public void setTheRightAnswer(String rightAnswer){
        this.rightAnswer = rightAnswer;
    }

    /**
     * Function that returns the right answer text
     * @return the right answer
     */
    public String getTheRightAnswer(){
        return rightAnswer;
    }

    /**
     * Function that returns the question text
     * @return the question text
     */
    public String getTheQuetion(){
        return quetion;
    }

    /**
     * Function that sets the question and the category
     * @param quetion the question text
     * @param category the category text
     */
    // TODO: create seperate setters for the question and the category
    public void setTheQuetionCategory(String quetion, String category){
        this.quetion = quetion;
        this.category = category;
    }

    /**
     * Function that returns the category of the question
     * @return the category text
     */
    public String getCategory(){
        return category;
    }


    /**
     * Utility function that suffles the question answers on the ArrayList
     */
    public void randomOrderAnswers(){

        Collections.shuffle(answers, new Random(System.currentTimeMillis()));
        for(int i=0;i<answers.size();i++){
            System.out.println((i+1) + ". " + answers.get(i));
        }
    }

    /**
     * Function that checkes wheter the answer provided is the right one
     * @param answer the answer provided by the client application
     * @return true if correct, false otherwise
     */
    public boolean isTheCorrectAnswer(String answer){
        // TODO: replace that with a single line
        if(answer.equals(rightAnswer)){
            return true;
        }else{
            return false;
        }
    }
}
