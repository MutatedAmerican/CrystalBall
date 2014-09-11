package android.diazb.crystalball;

public class Predictions {

    private static Predictions predictions;
    private String[] answers;

    private Predictions() {
        answers=new String[] {
                "You're wishes will come true.",
                "You're wished will NEVER come true."
        };
    }

    public static Predictions get(){
        if(predictions==null) {
            predictions=new Predictions();
        }
           return predictions;
    }

    public String getPrediction() {

        return answers[1];
    }
}