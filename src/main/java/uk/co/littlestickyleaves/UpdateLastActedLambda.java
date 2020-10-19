package uk.co.littlestickyleaves;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.jr.ob.JSON;
import software.amazon.awssdk.auth.credentials.EnvironmentVariableCredentialsProvider;
import software.amazon.awssdk.core.SdkSystemSetting;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.http.urlconnection.UrlConnectionHttpClient;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;
import uk.co.littlestickyleaves.aws.lambda.base.LambdaWorker;

import java.io.IOException;
import java.io.UncheckedIOException;

import static java.util.Objects.requireNonNull;

/**
 * Update Last Acted
 */
public class UpdateLastActedLambda implements LambdaWorker {

    private static final JSON json = JSON.std;

    private static final String LAST_ACTED = "lastActed.txt";

    private static final TimeHandler TIME_HANDLER = new TimeHandler();

    private static S3Client s3Client = null;

    @Override
    public String handleRaw(String rawInput) {
        try {
            BucketLastActed bucketLastActed = json.beanFrom(BucketLastActed.class, rawInput);
            return handle(bucketLastActed);
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }

    public String handle(BucketLastActed input) {
        if (s3Client == null) {
            s3Client = setUpS3Client();
        }

        String bucket = requireNonNull(input.getBucket());
        String time = TIME_HANDLER.examine(input);

        s3Client.putObject(
                PutObjectRequest.builder().bucket(bucket).key(LAST_ACTED).build(),
                RequestBody.fromString(time));

        return "Updated " + LAST_ACTED + " on bucket " + bucket + " to read " + time;
    }

    public String handleRequest(BucketLastActed input, Context context) {
        LambdaLogger lambdaLogger = context.getLogger();
        lambdaLogger.log("Received " + input);
        String success = handle(input);
        lambdaLogger.log(success);
        return success;
    }

    private S3Client setUpS3Client() {
        return S3Client.builder()
                .credentialsProvider(EnvironmentVariableCredentialsProvider.create())
                .region(Region.of(System.getenv(SdkSystemSetting.AWS_REGION.environmentVariable())))
                .httpClientBuilder(UrlConnectionHttpClient.builder())
                .build();
    }

}
