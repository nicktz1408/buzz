import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;



public class DataBase {
    private ArrayList<String> answers;
    private String quetion;
    private String rightAnswer;
    private String category;

    public DataBase(){}

    public ArrayList getTheAnswers(){
        return answers;
    }


    public void setTheAnswers(String a, String b, String c, String d){
        answers = new ArrayList<String>();
        answers.add(a);
        answers.add(b);
        answers.add(c);
        answers.add(d);
    }

    public void setTheRightAnswer(String rightAnswer){
        this.rightAnswer = rightAnswer;
    }

    public String getTheRightAnswer(){
        return rightAnswer;
    }

    public String getTheQuetion(){
        return quetion;
    }

    public void setTheQuetionCategory(String quetion, String category){
        this.quetion = quetion;
        this.category = category;
    }

    public String getCategory(){
        return category;
    }



    public void randomOrderAnswers(){

        Collections.shuffle(answers, new Random(System.currentTimeMillis()));
        for(int i=0;i<answers.size();i++){
            System.out.println((i+1) + ". " + answers.get(i));
        }
    }


    public boolean isTheCorrectAnswer(String answer){
        if(answer.equals(rightAnswer)){
            return true;
        }else{
            return false;
        }
    }
}
