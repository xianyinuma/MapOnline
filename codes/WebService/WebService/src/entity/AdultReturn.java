package entity;

/**
 * Created by Victor on 2017/5/5.
 */
public class AdultReturn {
    private String requestId;

    private Metadata metadata;

    private Adult adult;

    public AdultReturn() {
    }

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    public Metadata getMetadata() {
        return metadata;
    }

    public void setMetadata(Metadata metadata) {
        this.metadata = metadata;
    }

    public Adult getAdult() {
        return adult;
    }

    public void setAdult(Adult adult) {
        this.adult = adult;
    }
}
