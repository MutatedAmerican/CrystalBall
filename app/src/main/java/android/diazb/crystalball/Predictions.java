package android.diazb.crystalball;

import java.util.Random;

public class Predictions {

    private static Predictions predictions;
    private String[] answers;

    private Predictions() {
        answers=new String[] {
                "You're wishes will come true.",
                "You're wishes will NEVER come true.",
                "You're wishes will MAYBE come true.",
                "You're wishes will come true, when pigs fly.",
                "That depends, are you single?"
        };
    }

    public static Predictions get(){
        if(predictions==null) {
            predictions=new Predictions();
        }
           return predictions;
    }

    public String getPrediction() {

        Random answer = new Random();


        return answers[answer.nextInt(5)];


    }
}