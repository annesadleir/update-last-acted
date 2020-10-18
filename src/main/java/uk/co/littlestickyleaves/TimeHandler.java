package uk.co.littlestickyleaves;

import java.time.Instant;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;

/**
 * {A thing} to {do something} for {another thing}
 * -- for example, {this}
 * -- and also {this}
 */
// TODO fill in Javadoc
public class TimeHandler {

    public String examine(BucketLastActed input) {
        String time = input.getTime();

        if (time == null) {
            time = ZonedDateTime.ofInstant(Instant.EPOCH, ZoneOffset.UTC).toString();
        } else if ("now".equals(time)) {
            time = ZonedDateTime.now(ZoneOffset.UTC).toString();
        }

        checkValid(time);

        return time;
    }

    private boolean checkValid(String time) {
        ZonedDateTime.parse(time);
        return true;
    }
}
