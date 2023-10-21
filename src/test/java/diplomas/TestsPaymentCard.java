package diplomas;

import LibraryOfData.ElementsFormPage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.sql.SQLException;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class TestsPaymentCard {

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
		elementsFormPage.pushContinueButton();
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
		elementsFormPage.pushContinueButton();
		elementsFormPage.checkMessageError();
	}

	@Test
	void shouldNotPayByWrongCard() {
		elementsFormPage.buyForYourMoney();
		elementsFormPage.setCardNumber("44DR 4F44 444L PQ43");
		elementsFormPage.setCardMonth("12");
		elementsFormPage.setCardYear("25");
		elementsFormPage.setCardOwner("Sergei Semenov");
		elementsFormPage.setCardCVV("111");
		elementsFormPage.pushContinueButton();
		elementsFormPage.checkMessageWrongFormat();
	}

	@Test
	void shouldNotPayByUnknownCard() {
		elementsFormPage.buyForYourMoney();
		elementsFormPage.setCardNumber("4444444444444443");
		elementsFormPage.setCardMonth("12");
		elementsFormPage.setCardYear("25");
		elementsFormPage.setCardOwner("Sergei Semenov");
		elementsFormPage.setCardCVV("111");
		elementsFormPage.pushContinueButton();
		elementsFormPage.checkMessageError();
	}

	@Test
	void shouldNotPayByApprovedCardWrongDateMonth() {
		elementsFormPage.buyForYourMoney();
		elementsFormPage.setCardNumber("4444444444444441");
		elementsFormPage.setCardMonth("13");
		elementsFormPage.setCardYear("25");
		elementsFormPage.setCardOwner("Sergei Semenov");
		elementsFormPage.setCardCVV("111");
		elementsFormPage.pushContinueButton();
		elementsFormPage.checkMessageWrongDate();
	}

	@Test
	void shouldNotPayByApprovedCardWrongDateYear() {
		elementsFormPage.buyForYourMoney();
		elementsFormPage.setCardNumber("4444444444444441");
		elementsFormPage.setCardMonth("12");
		elementsFormPage.setCardYear("20");
		elementsFormPage.setCardOwner("Sergei Semenov");
		elementsFormPage.setCardCVV("111");
		elementsFormPage.pushContinueButton();
		elementsFormPage.checkMessageOverDate();
	}

	@Test
	void shouldNotPayByApprovedCardWrongName() {
		elementsFormPage.buyForYourMoney();
		elementsFormPage.setCardNumber("4444444444444441");
		elementsFormPage.setCardMonth("12");
		elementsFormPage.setCardYear("25");
		elementsFormPage.setCardOwner("Sergei123 Semen%&#");
		elementsFormPage.setCardCVV("111");
		elementsFormPage.pushContinueButton();
		elementsFormPage.checkMessageError();
	}

	@Test
	void shouldNotPayByApprovedCardWrongNameRu() {
		elementsFormPage.buyForYourMoney();
		elementsFormPage.setCardNumber("4444444444444441");
		elementsFormPage.setCardMonth("12");
		elementsFormPage.setCardYear("25");
		elementsFormPage.setCardOwner("Сергей Семёнов");
		elementsFormPage.setCardCVV("111");
		elementsFormPage.pushContinueButton();
		elementsFormPage.checkMessageError();
	}

	@Test
	void shouldNotPayByApprovedCardWrongNameEmpty() {
		elementsFormPage.buyForYourMoney();
		elementsFormPage.setCardNumber("4444444444444441");
		elementsFormPage.setCardMonth("12");
		elementsFormPage.setCardYear("25");
		elementsFormPage.setCardOwner("");
		elementsFormPage.setCardCVV("111");
		elementsFormPage.pushContinueButton();
		elementsFormPage.checkMessageRequiredField();
	}

	@Test
	void shouldNotPayByApprovedCardWrongDateEmptyYear() {
		elementsFormPage.buyForYourMoney();
		elementsFormPage.setCardNumber("4444444444444441");
		elementsFormPage.setCardMonth("12");
		elementsFormPage.setCardYear("");
		elementsFormPage.setCardOwner("123 15");
		elementsFormPage.setCardCVV("111");
		elementsFormPage.pushContinueButton();
		elementsFormPage.checkMessageWrongFormat();
	}

	@Test
	void shouldNotPayByApprovedCardWrongDateEmptyMonth() {
		elementsFormPage.buyForYourMoney();
		elementsFormPage.setCardNumber("4444444444444441");
		elementsFormPage.setCardMonth("");
		elementsFormPage.setCardYear("25");
		elementsFormPage.setCardOwner("Sergei Semenov");
		elementsFormPage.setCardCVV("111");
		elementsFormPage.pushContinueButton();
		elementsFormPage.checkMessageWrongFormat();
	}

	@Test
	void shouldNotPayByApprovedCardWrongCVVEmpty() {
		elementsFormPage.buyForYourMoney();
		elementsFormPage.setCardNumber("4444444444444441");
		elementsFormPage.setCardMonth("12");
		elementsFormPage.setCardYear("25");
		elementsFormPage.setCardOwner("Sergei123 Semen%&#");
		elementsFormPage.setCardCVV("");
		elementsFormPage.pushContinueButton();
		elementsFormPage.checkMessageRequiredField();
	}

	@Test
	void shouldNotPayByApprovedCardWrongCVV() {
		elementsFormPage.buyForYourMoney();
		elementsFormPage.setCardNumber("4444444444444441");
		elementsFormPage.setCardMonth("12");
		elementsFormPage.setCardYear("25");
		elementsFormPage.setCardOwner("Sergei123 Semen%&#");
		elementsFormPage.setCardCVV("D12");
		elementsFormPage.pushContinueButton();
		elementsFormPage.checkMessageWrongFormat();
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
