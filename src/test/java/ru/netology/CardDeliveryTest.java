package ru.netology;

import com.codeborne.selenide.Condition;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Calendar;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.*;

public class CardDeliveryTest {

    public String setDate(int numberDays) {
        Calendar currentDate = Calendar.getInstance();
        currentDate.add(Calendar.DATE, numberDays);
        DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
        return dateFormat.format(currentDate.getTime());
    }

    @Test
    void shouldSubmittingAValidForm(){
        open("http://localhost:9999");
        $("[placeholder=\"Город\"]").setValue("Казань");
        $("[placeholder=\"Дата встречи\"]").sendKeys(Keys.chord(Keys.CONTROL, "a"), Keys.DELETE);
        $("[placeholder=\"Дата встречи\"]").setValue(setDate(4));
        $("[name=\"name\"]").setValue("Иванов Иван");
        $("[name=\"phone\"]").setValue("+79200000000");
        $("[class=\"checkbox__box\"]").click();
        $("[class=\"button button_view_extra button_size_m button_theme_alfa-on-white\"]").click();
        $(withText("Успешно")).shouldBe(Condition.visible, Duration.ofSeconds(15));
    }

    @Test
    void shouldSubmittingAValidFormUsingElements(){
        open("http://localhost:9999");
        $("[placeholder=\"Город\"]").sendKeys("К", "а");
        $(byText("Казань")).click();
        Calendar currentDate = Calendar.getInstance();
        Calendar newDate = Calendar.getInstance();
        newDate.add(Calendar.DATE, 7);
        String[] months = { "Январь", "Февраль", "Март", "Апрель", "Май", "Июнь", "Июль", "Август", "Сентябрь", "Октябрь", "Ноябрь", "Декабрь" };
        String month = months[newDate.get(Calendar.MONTH)];
        $("[class=\"input__icon\"]").click();
        String calendarMonths = $("[class=\"calendar__name\"]").getText();
        if (!calendarMonths.contains(month)) {
            $(".calendar__title [data-step='1']").click();
        }
        String day = Integer.toString(newDate.get(Calendar.DATE));
        $$(".calendar__day").find(exactText(day)).click();
        $("[name=\"name\"]").setValue("Иванов Иван");
        $("[name=\"phone\"]").setValue("+79200000000");
        $("[class=\"checkbox__box\"]").click();
        $("[class=\"button button_view_extra button_size_m button_theme_alfa-on-white\"]").click();
        $(withText("Успешно")).shouldBe(Condition.visible, Duration.ofSeconds(15));
    }
}
