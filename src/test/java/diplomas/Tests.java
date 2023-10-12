package diplomas;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class Tests {

	private ElementsFormPage elementsFormPage;

	@BeforeEach
	void setElementsFormPage() {
		elementsFormPage = new ElementsFormPage();
	}


	@Test
	void shouldPayByApprovedCard() {
		elementsFormPage.buyForYourMoney();
		elementsFormPage.setCardNumber("4444444444444441");
		elementsFormPage.setCardMonth("12");
		elementsFormPage.setCardYear("25");
		elementsFormPage.setCardOwner("Sergei Semenov");
		elementsFormPage.setCardCVV("111");
		elementsFormPage.pushСontinueButton();
		elementsFormPage.checkMessageSuccess();
	}

	@Test
	void shouldNotPayByDeclinedCard() {
		elementsFormPage.buyForYourMoney();
		elementsFormPage.setCardNumber("4444444444444442");
		elementsFormPage.setCardMonth("12");
		elementsFormPage.setCardYear("25");
		elementsFormPage.setCardOwner("Sergei Semenov");
		elementsFormPage.setCardCVV("111");
		elementsFormPage.pushСontinueButton();
		elementsFormPage.checkMessageError();
	}

	@Test
	void shouldNotPayByApprovedCard() {
		elementsFormPage.buyForYourMoney();
		elementsFormPage.setCardNumber("4444444444444441");
		elementsFormPage.setCardMonth("13");
		elementsFormPage.setCardYear("25");
		elementsFormPage.setCardOwner("Sergei Semenov");
		elementsFormPage.setCardCVV("111");
		elementsFormPage.pushСontinueButton();
		elementsFormPage.checkMessageWrongDate();
	}

//Пробники
/*	@Test
	public void validCardApproved () {
		open("http://localhost:8080");
		$("[class='heading heading_size_l heading_theme_alfa-on-white']").shouldHave(Condition.exactText("Путешествие дня"));
	}

	@Test
	public void validCardApprovedDebet () {
		open("http://localhost:8080");
		$("[class='button button_size_m button_theme_alfa-on-white']").click();
		$("[class=''] input").setValue("4444444444444441");
		$("[class=''] input").setValue("12");
		$("[class=''] input").setValue("2025");
		$("[class='']").setValue("Sergei Semenov");
		$("[class='']").setValue("Sergei Semenov");
	}*/




}
