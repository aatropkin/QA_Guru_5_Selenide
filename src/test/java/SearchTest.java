
import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;

public class SearchTest {
    String textOfSoftAssertion = """    
            @ExtendWith({SoftAssertsExtension.class})
            class Tests {
              @Test
              void test() {
                Configuration.assertionMode = SOFT;
                open("page.html");

                $("#first").should(visible).click();
                $("#second").should(visible).click();
              }
            }
            """;
    //Вынесли искомый текст в переменную

    @BeforeAll
    static void beforeAll() {
        Configuration.browserSize = "1920x1080";
        Configuration.baseUrl = "https://github.com/";
        Configuration.pageLoadStrategy = "eager";
        Configuration.holdBrowserOpen = false;
    }

    @Test
    void serchExampleJUnit5Test() {

        //Откройте страницу Selenide в Github
        open("selenide/selenide/");

        // Перейдите в раздел Wiki проекта
        $("#wiki-tab").click();

        // Убедитесь, что в списке страниц (Pages) есть страница SoftAssertions
        $(".wiki-more-pages-link").$(".btn-link").click();
        $("#wiki-pages-box").shouldHave(text("Softassertions")).shouldHave(visible);

        //открыть страницу SoftAssertion
        $(byText("Soft assertions")).click();

        // проверить, что внутри есть пример кода для JUnit5
        $("#wiki-body").shouldHave(text(textOfSoftAssertion)).shouldHave(visible);
    }
}
