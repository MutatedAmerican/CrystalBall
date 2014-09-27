package android.diazb.crystalball;
import java.util.Random;

public class Predictions {

    private static Predictions predictions;
    private String[] answers;

    private Predictions() {

        // The selection of random predictions the app shall "predict"
        answers = new String[] {
                "Yes",
                "No",
                "Don't ask me, ask Tinkerbell",
                "First, find the Pendant of Courage",
                "First, find the Pendant of Wisdom",
                "First, find the Pendant of Power",
                "HELP ME!",
                "You don't deserve a second chance",
                "Talk to Mario",
                "Maybe",
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