package entity;

/**
 * Created by Victor on 2017/5/4.
 */
public class AnalyzeReturn {
    private String requestId;

    private Metadata metadata;

    private Description description;

    public AnalyzeReturn() {
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

    public Description getDescription() {
        return description;
    }

    public void setDescription(Description description) {
        this.description = description;
    }

}
