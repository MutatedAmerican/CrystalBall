package android.diazb.crystalball;

import java.util.Random;

public class Predictions {

    private static Predictions predictions;
    private String[] answers;

    private Predictions() {
        answers=new String[] {
                "NO",
                "YES",
                "Ask Bill Nye the Science Guy",
                "My IQ just lowered because of your question",
                "WHY WOULD YOU ASK THAT?",
                "That depends, are you single?",
                "Don't ask me, ask Moises"
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

        return answers[answer.nextInt(8)];


    }
}