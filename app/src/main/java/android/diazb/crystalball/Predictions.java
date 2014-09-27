package android.diazb.crystalball;
import java.util.Random;

public class Predictions {

    private static Predictions predictions;
    private String[] answers;

    private Predictions() {

        // The selection of random predictions the app shall "predict"
        answers = new String[] {
                ":)",
                ":(",
                ":-/",
                ":-??",
                "<3",
                "~:>",
                "[-O<",
                "[-X",
                "o.O",
                "(y)",
        };
    }

    //checks if the predictions variable is set to null
    public static Predictions get(){
        if(predictions == null){
            predictions = new Predictions();
        }
        return predictions;
    }

    // Sends back the "prediction" to the CrystalBall.java
    public String getPredictions() {

        //Generates a random number to select an answer from
        Random prediction = new Random();

        return answers[prediction.nextInt(11)];
    }

}