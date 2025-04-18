package api.runners;

import com.intuit.karate.core.FeatureRuntime;
import com.intuit.karate.core.ScenarioRuntime;
import io.qameta.allure.karate.AllureKarate;

public class CustomAllureKarate extends AllureKarate {

    @Override
    public void afterScenario(ScenarioRuntime scenarioRuntime) {
        // Лог для завершения сценария
        System.out.println("DEBUG: Сценарий завершён: " + scenarioRuntime.scenario.getDescription());
        super.afterScenario(scenarioRuntime); // Вызов базовой реализации
    }

    @Override
    public void afterFeature(FeatureRuntime featureRuntime) {
        // Используем toString() для получения информации о фиче
        System.out.println("DEBUG: Фича завершена: " + featureRuntime.toString());
        super.afterFeature(featureRuntime); // Вызов базовой реализации
    }

}