package diplomas;

import LibraryOfData.DataBase;
import LibraryOfData.ElementsFormPage;
import LibraryOfData.Status;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static LibraryOfData.Status.APPROVED;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestsCreditCard {

	private ElementsFormPage elementsFormPage;

	@BeforeEach
	void setElementsFormPage() {
		elementsFormPage = new ElementsFormPage();
	}

	@AfterEach
	void clearAll() throws SQLException{
		DataBase.clearAllData();
	}

	@Test
	void shouldPayByApprovedCard() {
		elementsFormPage.buyOnCredit();
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
		elementsFormPage.buyOnCredit();
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
		elementsFormPage.buyOnCredit();
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
		elementsFormPage.buyOnCredit();
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
		elementsFormPage.buyOnCredit();
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
		elementsFormPage.buyOnCredit();
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
		elementsFormPage.buyOnCredit();
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
		elementsFormPage.buyOnCredit();
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
		elementsFormPage.buyOnCredit();
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
		elementsFormPage.buyOnCredit();
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
		elementsFormPage.buyOnCredit();
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
		elementsFormPage.buyOnCredit();
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
		elementsFormPage.buyOnCredit();
		elementsFormPage.setCardNumber("4444444444444441");
		elementsFormPage.setCardMonth("12");
		elementsFormPage.setCardYear("25");
		elementsFormPage.setCardOwner("Sergei123 Semen%&#");
		elementsFormPage.setCardCVV("D12");
		elementsFormPage.pushContinueButton();
		elementsFormPage.checkMessageWrongFormat();
	}

	@Test
	void shouldPayByApprovedCardStatusInDB() throws SQLException {
		elementsFormPage.buyOnCredit();
		elementsFormPage.setCardNumber("4444444444444441");
		elementsFormPage.setCardMonth("12");
		elementsFormPage.setCardYear("25");
		elementsFormPage.setCardOwner("Sergei Semenov");
		elementsFormPage.setCardCVV("111");
		elementsFormPage.pushContinueButton();
		elementsFormPage.checkMessageSuccess();
/*		var expectedStatus = APPROVED;
		var actualStatus = DataBase.mysqlDataSource();
		assertEquals(expectedStatus, actualStatus);*/
		DataBase.checkCreditStatus(Status.APPROVED);
	}

	@Test
	void shouldNotPayByDeclinedCardInDB() throws SQLException {
		elementsFormPage.buyOnCredit();
		elementsFormPage.setCardNumber("4444444444444442");
		elementsFormPage.setCardMonth("12");
		elementsFormPage.setCardYear("25");
		elementsFormPage.setCardOwner("Sergei Semenov");
		elementsFormPage.setCardCVV("111");
		elementsFormPage.pushContinueButton();
		elementsFormPage.checkMessageError();
		DataBase.checkCreditStatus(Status.DECLINED);
	}
}
