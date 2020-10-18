package uk.co.littlestickyleaves;

import software.amazon.awssdk.services.s3.S3Client;

/**
 * {A thing} to {do something} for {another thing}
 * -- for example, {this}
 */
// TODO fill in Javadoc
public class TracingAgentPath {

    public static void main(String[] args) throws Exception {
        UpdateLastActedLambda updateLastActedLambda = new UpdateLastActedLambda();

        System.out.println(updateLastActedLambda.handleRaw("{\"bucket\":\"papirtape\"}"));
        System.out.println(updateLastActedLambda.handleRaw("{\"bucket\":\"papirtape\"," +
                "\"time\":\"now\"}"));
        System.out.println(updateLastActedLambda.handleRaw("{\"bucket\":\"papirtape\"," +
                "\"time\":\"2020-10-18T20:51:00.000\"}"));
    }
}
