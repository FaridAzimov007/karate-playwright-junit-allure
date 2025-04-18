package api.runners;

import com.intuit.karate.junit5.Karate;
import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.TestFactory;

import java.util.Collection;


public class KarateTestRunner {
    @Karate.Test
    Karate runKarateTagged() {
        System.getProperty("karate.options", "--tags @Smoke");
        String tag = "@Smoke";
        return Karate.run()
                .path("classpath:feature")
                .tags(tag)
                .hook(new CustomAllureKarate());
    }

//    @Karate.Test
//    Karate runKarateTagged() {
//        String karateOptions = System.getProperty("karate.options", "--tags @Smoke");
//        String tag = "@Smoke";
//
//        // Пытаемся извлечь тег из karate.options (если передано --tags @TagName)
//        if (karateOptions.contains("--tags")) {
//            String[] parts = karateOptions.split(" ");
//            for (int i = 0; i < parts.length - 1; i++) {
//                if (parts[i].equals("--tags")) {
//                    tag = parts[i + 1];
//                    break;
//                }
//            }
//        }
//        return Karate.run()
//                .path("classpath:feature")
//                .tags(tag) // сюда уже приходит просто "@Smoke"
//                .hook(new CustomAllureKarate());
//    }



}
