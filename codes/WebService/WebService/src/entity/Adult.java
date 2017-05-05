package entity;

/**
 * Created by Victor on 2017/5/5.
 */
public class Adult {
    short isAdultContent, isRacyContent;
    float adultScore, racyScore;

    public short getIsAdultContent() {
        return isAdultContent;
    }

    public void setIsAdultContent(short isAdultContent) {
        this.isAdultContent = isAdultContent;
    }

    public short getIsRacyContent() {
        return isRacyContent;
    }

    public void setIsRacyContent(short isRacyContent) {
        this.isRacyContent = isRacyContent;
    }

    public float getAdultScore() {
        return adultScore;
    }

    public void setAdultScore(float adultScore) {
        this.adultScore = adultScore;
    }

    public float getRacyScore() {
        return racyScore;
    }

    public void setRacyScore(float racyScore) {
        this.racyScore = racyScore;
    }

    public Adult() {

    }
}
