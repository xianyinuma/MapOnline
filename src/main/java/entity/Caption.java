package entity;

/**
 * Created by Victor on 2017/5/4.
 */
public class Caption {
    private String text;
    private double confidence;

    public Caption() {
    }

    public String getText() {

        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public double getConfidence() {
        return confidence;
    }

    public void setConfidence(double confidence) {
        this.confidence = confidence;
    }
}
