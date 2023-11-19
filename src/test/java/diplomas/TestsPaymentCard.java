package diplomas;

import LibraryOfData.DataBase;
import LibraryOfData.ElementsFormPage;
import LibraryOfData.Status;
import LibraryOfData.GenData;
import com.codeborne.selenide.logevents.SelenideLogger;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import io.qameta.allure.selenide.AllureSelenide;


public class TestsPaymentCard {
	private ElementsFormPage elementsFormPage;
	public DataBase dataBase;

	@BeforeEach
	void setElementsFormPage() {
		elementsFormPage = new ElementsFormPage();
	}

	@AfterEach
	void clearAll() {
		DataBase.clearAllData();
	}

	@BeforeAll
	static void setUpAll() {
		SelenideLogger.addListener("allure", new AllureSelenide());
	}

	@Test
	void shouldPayByApprovedCard() {
		String month = GenData.generateMonth(+3);
		String year = GenData.generateYear(+2);
		elementsFormPage.buyForYourMoney();
		elementsFormPage.setCardNumber(GenData.approvedCard());
		elementsFormPage.setCardMonth(month);
		elementsFormPage.setCardYear(year);
		elementsFormPage.setCardOwner("Sergei Semenov");
		elementsFormPage.setCardCVV("111");
		elementsFormPage.pushContinueButton();
		elementsFormPage.checkMessageSuccess();
		dataBase.checkPaymentStatus(Status.APPROVED);
	}

	@Test
	void shouldNotPayByDeclinedCard() {
		String month = GenData.generateMonth(+3);
		String year = GenData.generateYear(+2);
		elementsFormPage.buyForYourMoney();
		elementsFormPage.setCardNumber(GenData.declinedCard());
		elementsFormPage.setCardMonth(month);
		elementsFormPage.setCardYear(year);
		elementsFormPage.setCardOwner("Sergei Semenov");
		elementsFormPage.setCardCVV("111");
		elementsFormPage.pushContinueButton();
		elementsFormPage.checkMessageError();
		dataBase.checkPaymentStatus(Status.DECLINED);
	}

	@Test
	void shouldNotPayByWrongCard() {
		String month = GenData.generateMonth(+3);
		String year = GenData.generateYear(+2);
		elementsFormPage.buyForYourMoney();
		elementsFormPage.setCardNumber("44DR 4F44 444L PQ43");
		elementsFormPage.setCardMonth(month);
		elementsFormPage.setCardYear(year);
		elementsFormPage.setCardOwner("Sergei Semenov");
		elementsFormPage.setCardCVV("111");
		elementsFormPage.pushContinueButton();
		elementsFormPage.checkMessageWrongFormat();
	}

	@Test
	void shouldNotPayByUnknownCard() {
		String month = GenData.generateMonth(+3);
		String year = GenData.generateYear(+2);
		elementsFormPage.buyForYourMoney();
		elementsFormPage.setCardNumber(GenData.unknownCard());
		elementsFormPage.setCardMonth(month);
		elementsFormPage.setCardYear(year);
		elementsFormPage.setCardOwner("Sergei Semenov");
		elementsFormPage.setCardCVV("111");
		elementsFormPage.pushContinueButton();
		elementsFormPage.checkMessageError();
		dataBase.checkPaymentStatus(Status.DECLINED);
	}

	@Test
	void shouldNotPayByApprovedCardWrongDateMonth() {
		String year = GenData.generateYear(+2);
		elementsFormPage.buyForYourMoney();
		elementsFormPage.setCardNumber(GenData.approvedCard());
		elementsFormPage.setCardMonth("13");
		elementsFormPage.setCardYear(year);
		elementsFormPage.setCardOwner("Sergei Semenov");
		elementsFormPage.setCardCVV("111");
		elementsFormPage.pushContinueButton();
		elementsFormPage.checkMessageWrongDate();
	}

	@Test
	void shouldNotPayByApprovedCardWrongDateYear() {
		String month = GenData.generateMonth(+3);
		String year = GenData.generateYear(-2);
		elementsFormPage.buyForYourMoney();
		elementsFormPage.setCardNumber(GenData.approvedCard());
		elementsFormPage.setCardMonth(month);
		elementsFormPage.setCardYear(year);
		elementsFormPage.setCardOwner("Sergei Semenov");
		elementsFormPage.setCardCVV("111");
		elementsFormPage.pushContinueButton();
		elementsFormPage.checkMessageOverDate();
	}

	@Test
	void shouldNotPayByApprovedCardWrongName() {
		String month = GenData.generateMonth(+3);
		String year = GenData.generateYear(+2);
		elementsFormPage.buyForYourMoney();
		elementsFormPage.setCardNumber(GenData.approvedCard());
		elementsFormPage.setCardMonth(month);
		elementsFormPage.setCardYear(year);
		elementsFormPage.setCardOwner("Sergei123 Semen%&#");
		elementsFormPage.setCardCVV("111");
		elementsFormPage.pushContinueButton();
		elementsFormPage.checkMessageError();
	}

	@Test
	void shouldNotPayByApprovedCardWrongNameRu() {
		String month = GenData.generateMonth(+3);
		String year = GenData.generateYear(+2);
		elementsFormPage.buyForYourMoney();
		elementsFormPage.setCardNumber(GenData.approvedCard());
		elementsFormPage.setCardMonth(month);
		elementsFormPage.setCardYear(year);
		elementsFormPage.setCardOwner("Сергей Семёнов");
		elementsFormPage.setCardCVV("111");
		elementsFormPage.pushContinueButton();
		elementsFormPage.checkMessageError();
	}

	@Test
	void shouldNotPayByApprovedCardWrongNameEmpty() {
		String month = GenData.generateMonth(+3);
		String year = GenData.generateYear(+2);
		elementsFormPage.buyForYourMoney();
		elementsFormPage.setCardNumber(GenData.approvedCard());
		elementsFormPage.setCardMonth(month);
		elementsFormPage.setCardYear(year);
		elementsFormPage.setCardOwner("");
		elementsFormPage.setCardCVV("111");
		elementsFormPage.pushContinueButton();
		elementsFormPage.checkMessageRequiredField();
	}

	@Test
	void shouldNotPayByApprovedCardWrongDateEmptyYear() {
		String month = GenData.generateMonth(+3);
		elementsFormPage.buyForYourMoney();
		elementsFormPage.setCardNumber(GenData.approvedCard());
		elementsFormPage.setCardMonth(month);
		elementsFormPage.setCardYear("");
		elementsFormPage.setCardOwner("123 15");
		elementsFormPage.setCardCVV("111");
		elementsFormPage.pushContinueButton();
		elementsFormPage.checkMessageWrongFormat();
	}

	@Test
	void shouldNotPayByApprovedCardWrongDateEmptyMonth() {
		String year = GenData.generateYear(+2);
		elementsFormPage.buyForYourMoney();
		elementsFormPage.setCardNumber(GenData.approvedCard());
		elementsFormPage.setCardMonth("");
		elementsFormPage.setCardYear(year);
		elementsFormPage.setCardOwner("Sergei Semenov");
		elementsFormPage.setCardCVV("111");
		elementsFormPage.pushContinueButton();
		elementsFormPage.checkMessageWrongFormat();
	}

	@Test
	void shouldNotPayByApprovedCardWrongCVVEmpty() {
		String month = GenData.generateMonth(+3);
		String year = GenData.generateYear(+2);
		elementsFormPage.buyForYourMoney();
		elementsFormPage.setCardNumber(GenData.approvedCard());
		elementsFormPage.setCardMonth(month);
		elementsFormPage.setCardYear(year);
		elementsFormPage.setCardOwner("Sergei123 Semen%&#");
		elementsFormPage.setCardCVV("");
		elementsFormPage.pushContinueButton();
		elementsFormPage.checkMessageRequiredField();
	}

	@Test
	void shouldNotPayByApprovedCardWrongCVV() {
		String month = GenData.generateMonth(+3);
		String year = GenData.generateYear(+2);
		elementsFormPage.buyForYourMoney();
		elementsFormPage.setCardNumber(GenData.approvedCard());
		elementsFormPage.setCardMonth(month);
		elementsFormPage.setCardYear(year);
		elementsFormPage.setCardOwner("Sergei123 Semen%&#");
		elementsFormPage.setCardCVV("D12");
		elementsFormPage.pushContinueButton();
		elementsFormPage.checkMessageWrongFormat();
	}

	@Test
	void shouldPayByApprovedCardInBD() {
		String month = GenData.generateMonth(+3);
		String year = GenData.generateYear(+2);
		elementsFormPage.buyForYourMoney();
		elementsFormPage.setCardNumber(GenData.approvedCard());
		elementsFormPage.setCardMonth(month);
		elementsFormPage.setCardYear(year);
		elementsFormPage.setCardOwner("Sergei Semenov");
		elementsFormPage.setCardCVV("111");
		elementsFormPage.pushContinueButton();
		elementsFormPage.checkMessageSuccess();
		dataBase.checkPaymentStatus(Status.APPROVED);
	}

	@Test
	void shouldNotPayByDeclinedCardInBD() {
		String month = GenData.generateMonth(+3);
		String year = GenData.generateYear(+2);
		elementsFormPage.buyForYourMoney();
		elementsFormPage.setCardNumber(GenData.declinedCard());
		elementsFormPage.setCardMonth(month);
		elementsFormPage.setCardYear(year);
		elementsFormPage.setCardOwner("Sergei Semenov");
		elementsFormPage.setCardCVV("111");
		elementsFormPage.pushContinueButton();
		elementsFormPage.checkMessageSuccess();
		dataBase.checkPaymentStatus(Status.DECLINED);
	}
}
