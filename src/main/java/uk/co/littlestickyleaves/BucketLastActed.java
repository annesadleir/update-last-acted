package uk.co.littlestickyleaves;

/**
 * POJO for instructions
 */
public class BucketLastActed {

    private String bucket;

    private String time;

    public String getBucket() {
        return bucket;
    }

    public void setBucket(String bucket) {
        this.bucket = bucket;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "BucketLastActed{" +
                "bucket='" + bucket + '\'' +
                ", time='" + time + '\'' +
                '}';
    }
}
