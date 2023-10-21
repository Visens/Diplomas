package LibraryOfData;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

import java.time.Duration;
import java.util.List;

import static com.codeborne.selenide.Selenide.open;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$$;

public class ElementsFormPage {

	List<SelenideElement> input = $$(".input__control");
	SelenideElement cardNumber = input.get(0);
	SelenideElement month = input.get(1);
	SelenideElement year = input.get(2);
	SelenideElement cardOwner = input.get(3);
	SelenideElement cvcOrCvvNumber = input.get(4);

	public void buyForYourMoney() {
		open("http://localhost:8080");
		$$(".button__content").find(Condition.exactText("Купить")).click();
		$$(".heading_theme_alfa-on-white").find(Condition.exactText("Оплата по карте")).shouldBe(visible);
	}

	public void buyOnCredit(){
		open("http://localhost:8080");
		$$(".button__content").find(Condition.exactText("Купить в кредит")).click();
		$$(".heading_theme_alfa-on-white").find(Condition.exactText("Кредит по данным карты")).shouldBe(visible);
	}

	public void checkMessageSuccess() {
		$$(".notification__title").find(Condition.exactText("Успешно")).shouldBe(Condition.visible, Duration.ofSeconds(15));
	}

	public void checkMessageError() {
		$$(".notification__title").find(Condition.exactText("Ошибка")).shouldBe(Condition.visible, Duration.ofSeconds(15));
	}

	public void checkMessageWrongFormat() {
		$$(".input__sub").find(Condition.exactText("Неверный формат")).shouldBe(visible);
	}

	public void checkMessageWrongDate() {
		$$(".input__sub").find(Condition.exactText("Неверно указан срок действия карты")).shouldBe(visible);
	}

	public void checkMessageOverDate() {
		$$(".input__sub").find(Condition.exactText("Истёк срок действия карты")).shouldBe(visible);
	}

	public void checkMessageRequiredField() {
		$$(".input__sub").find(Condition.exactText("Поле обязательно для заполнения")).shouldBe(visible);
	}

	public void setCardNumber(String cNumber) {
		cardNumber.setValue(cNumber);
	}

	public void setCardMonth(String cMonth) {
		month.setValue(cMonth);
	}

	public void setCardYear(String cYear) {
		year.setValue(cYear);
	}

	public void setCardOwner(String cOwner) {
		cardOwner.setValue(cOwner);
	}

	public void setCardCVV(String cCvv) {
		cvcOrCvvNumber.setValue(cCvv);
	}

	public void pushContinueButton(){
		$$(".button__content").find(Condition.exactText("Продолжить")).click();
	}

}
