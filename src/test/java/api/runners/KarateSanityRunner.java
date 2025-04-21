package api.runners;

import com.intuit.karate.Runner;
import config.CustomAllureKarate;

public class KarateSanityRunner {

    public static void main(String[] args) {
        Runner.builder()
                .path("classpath:feature")
                .tags("@Sanity")
                .hook(new CustomAllureKarate())
                .parallel(1);
    }
}
