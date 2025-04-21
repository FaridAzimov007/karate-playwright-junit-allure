package api.runners;

import com.intuit.karate.Runner;
import config.CustomAllureKarate;


public class KarateTestRunner {
    public static void main(String[] args) {
        Runner.builder()
                .path("classpath:feature")
                .tags("@Smoke")
                .hook(new CustomAllureKarate())
                .parallel(1);
    }

}
