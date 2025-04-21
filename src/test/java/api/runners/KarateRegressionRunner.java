package api.runners;

import com.intuit.karate.Runner;
import config.CustomAllureKarate;

public class KarateRegressionRunner {

    public static void main(String[] args) {
        Runner.builder()
                .path("classpath:feature")
                .tags("@Regression")
                .hook(new CustomAllureKarate())
                .parallel(1);
    }
}
