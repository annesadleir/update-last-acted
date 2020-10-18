package uk.co.littlestickyleaves;

import uk.co.littlestickyleaves.aws.lambda.base.LambdaRunner;

/**
 * Just a class to hold the psvm method which starts the runtime
 */
public class LambdaRunnerMain {

    public static void main(String[] args) throws Exception {
        new LambdaRunner(new UpdateLastActedLambda()).loop();
    }
}
