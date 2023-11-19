package diplomas;

import LibraryOfData.DataBase;
import LibraryOfData.ElementsFormPage;
import LibraryOfData.Status;
import LibraryOfData.GenData;
import com.codeborne.selenide.logevents.SelenideLogger;
import org.junit.jupiter.api.*;
import io.qameta.allure.selenide.AllureSelenide;

import java.sql.SQLException;

import static LibraryOfData.Status.APPROVED;


public class TestsCreditCard {

	private ElementsFormPage elementsFormPage;
	public DataBase dataBase;

	@BeforeEach
	void setElementsFormPage() {
		elementsFormPage = new ElementsFormPage();
	}

	@BeforeAll
	static void setUpAll() {
		SelenideLogger.addListener("allure", new AllureSelenide());
	}

	@AfterEach
	void clearAll() {
		DataBase.clearAllData();
	}

	@AfterAll
	static void tearDownAll() {
		SelenideLogger.removeListener("allure");
	}


	@Test
	void shouldPayByApprovedCard() {
		String month = GenData.generateMonth(+3);
		String year = GenData.generateYear(+2);
		elementsFormPage.buyOnCredit();
		elementsFormPage.setCardNumber(GenData.approvedCard());
		elementsFormPage.setCardMonth(month);
		elementsFormPage.setCardYear(year);
		elementsFormPage.setCardOwner("Sergei Semenov");
		elementsFormPage.setCardCVV("111");
		elementsFormPage.pushContinueButton();
		elementsFormPage.checkMessageSuccess();
		dataBase.checkCreditStatus(Status.APPROVED);
	}

	@Test
	void shouldNotPayByDeclinedCard() {
		String month = GenData.generateMonth(+3);
		String year = GenData.generateYear(+2);
		elementsFormPage.buyOnCredit();
		elementsFormPage.setCardNumber(GenData.declinedCard());
		elementsFormPage.setCardMonth(month);
		elementsFormPage.setCardYear(year);
		elementsFormPage.setCardOwner("Sergei Semenov");
		elementsFormPage.setCardCVV("111");
		elementsFormPage.pushContinueButton();
		elementsFormPage.checkMessageError();
		dataBase.checkCreditStatus(Status.DECLINED);
	}

	@Test
	void shouldNotPayByWrongCard() {
		String month = GenData.generateMonth(+3);
		String year = GenData.generateYear(+2);
		elementsFormPage.buyOnCredit();
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
		elementsFormPage.buyOnCredit();
		elementsFormPage.setCardNumber(GenData.unknownCard());
		elementsFormPage.setCardMonth(month);
		elementsFormPage.setCardYear(year);
		elementsFormPage.setCardOwner("Sergei Semenov");
		elementsFormPage.setCardCVV("111");
		elementsFormPage.pushContinueButton();
		elementsFormPage.checkMessageError();
	}

	@Test
	void shouldNotPayByApprovedCardWrongDateMonth() {
		String year = GenData.generateYear(+2);
		elementsFormPage.buyOnCredit();
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
		elementsFormPage.buyOnCredit();
		elementsFormPage.setCardNumber(GenData.approvedCard());
		elementsFormPage.setCardMonth(month);
		elementsFormPage.setCardYear("20");
		elementsFormPage.setCardOwner("Sergei Semenov");
		elementsFormPage.setCardCVV("111");
		elementsFormPage.pushContinueButton();
		elementsFormPage.checkMessageOverDate();
	}

	@Test
	void shouldNotPayByApprovedCardWrongName() {
		String month = GenData.generateMonth(+3);
		String year = GenData.generateYear(+2);
		elementsFormPage.buyOnCredit();
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
		elementsFormPage.buyOnCredit();
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
		elementsFormPage.buyOnCredit();
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
		elementsFormPage.buyOnCredit();
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
		elementsFormPage.buyOnCredit();
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
		elementsFormPage.buyOnCredit();
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
		elementsFormPage.buyOnCredit();
		elementsFormPage.setCardNumber(GenData.approvedCard());
		elementsFormPage.setCardMonth(month);
		elementsFormPage.setCardYear(year);
		elementsFormPage.setCardOwner("Sergei123 Semen%&#");
		elementsFormPage.setCardCVV("D12");
		elementsFormPage.pushContinueButton();
		elementsFormPage.checkMessageWrongFormat();
	}

	@Test
	void shouldPayByApprovedCardStatusInDB() {
		String month = GenData.generateMonth(+3);
		String year = GenData.generateYear(+2);
		elementsFormPage.buyOnCredit();
		elementsFormPage.setCardNumber(GenData.approvedCard());
		elementsFormPage.setCardMonth(month);
		elementsFormPage.setCardYear(year);
		elementsFormPage.setCardOwner("Sergei Semenov");
		elementsFormPage.setCardCVV("111");
		elementsFormPage.pushContinueButton();
		elementsFormPage.checkMessageSuccess();
		dataBase.checkCreditStatus(APPROVED);
	}

	@Test
	void shouldNotPayByDeclinedCardInDB() {
		String month = GenData.generateMonth(+3);
		String year = GenData.generateYear(+2);
		elementsFormPage.buyOnCredit();
		elementsFormPage.setCardNumber(GenData.declinedCard());
		elementsFormPage.setCardMonth(month);
		elementsFormPage.setCardYear(year);
		elementsFormPage.setCardOwner("Sergei Semenov");
		elementsFormPage.setCardCVV("111");
		elementsFormPage.pushContinueButton();
		elementsFormPage.checkMessageSuccess();
		dataBase.checkCreditStatus(Status.DECLINED);
	}
}
