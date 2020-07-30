package tests;

import com.codeborne.selenide.WebDriverRunner;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;

class GoogleTest {
    public ArrayList<String> tabs;

    @Test
    void selenideGoogleSearchTest() {
        // Открыть google
        open("https://google.com");

        // Ввести Selenide в поиск
        $(byName("q")).setValue("Selenide").pressEnter();

        // Проверить, что Selenide появился в результатах поиска
        $("html").shouldHave(text("Selenide"));
    }

    @Test
    void selenideYaSearchTest() {
        String searchText = "Selenide";

        open("https://yandex.ru/");

        $(byName("text")).setValue(searchText).pressEnter();

        $("#search-result").$(byText(searchText)).shouldBe(visible);
    }


    @Test
    void selenideYaSearchBadTest() {
        open("https://yandex.ru/");

        $(byName("text")).setValue("Selenide").pressEnter();
        $(byXpath("//ul[@id='search-result']//li//a")).shouldHave(attribute("href", "https://ru.selenide.org/")).click();

        tabs = new ArrayList<String>(WebDriverRunner.getWebDriver().getWindowHandles());
        WebDriverRunner.getWebDriver().switchTo().window(tabs.get(1));
        $(byXpath("//div[@class='short wiki']//h3")).shouldHave(text("ЧТО ТАКОЕ SELENIDE?"));
    }

    @Test
    void siteShoudBeOpenedFromYandexSearchResultsTest() {
        open("https://yandex.ru/");

        $(byName("text")).setValue("Selenide").pressEnter();
        $("#search-result .serp-item a[href='https://ru.selenide.org/']").click();

        switchTo().window(1);
        $(byXpath("//div[@class='short wiki']//h3")).shouldHave(text("ЧТО ТАКОЕ SELENIDE?"));
    }

    @Test
    void goldTest() {
        // 1 // Arrange // Given

        // 2 // Act     // When

        // 3 // Assert  // Then
    }
}