package entity;

/**
 * Created by Victor on 2017/5/4.
 */
public class Description {
    private String[] tags;
    private Caption[] captions;

    public Description() {

    }

    public String[] getTags() {
        return tags;
    }

    public void setTags(String[] tags) {
        this.tags = tags;
    }

    public Caption[] getCaptions() {
        return captions;
    }

    public void setCaptions(Caption[] captions) {
        this.captions = captions;
    }

}
