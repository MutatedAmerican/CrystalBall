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
                "Wow, that was so sicknessing, I have a virus",
                "KHAN!",
                "That depends, are you single?",
                "Don't ask me, ask Tom Cruise",
                "Yes, if you the Dance of the Sugar Plum Fairy",
                "Yeah sure, whatever",
                "10 OUTTA 10-IGN"

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

        return answers[answer.nextInt(10)];


    }
}