package LibraryOfData;

import com.mchange.v2.c3p0.DriverManagerDataSource;
import lombok.val;
import java.sql.DriverManager;
import java.sql.SQLException;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

public class DataBase {
	private static String url = System.getProperty("db.url");
	private static String appURL = System.getProperty("app.url");
	private static String appPORT = System.getProperty("app.port");
	private static String userDB = System.getProperty("app.userDB");
	private static String password = System.getProperty("app.password");

/*	public static Status mysqlDataSource() throws SQLException {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		val runner = new QueryRunner();
		val conn = DriverManager.getConnection(url, userDB, password);
		val creditDataSQL = "SELECT status FROM credit_request_entity;";
		val credit = runner.query(conn, creditDataSQL, new BeanHandler<>(Credit.class));
		return credit.status;
*//*		dataSource.setDriverClass("com.mysql.cj.jdbc.Driver");
		dataSource.setUrl("jdbc:mysql://localhost:3306/app");
		dataSource.setUsername("app");
		dataSource.setPassword("9mREsvXDs9Gk89Ef");*//*
*//*		return dataSource;*//*
	}*/

	public static void clearAllData() throws SQLException {
		val runner = new QueryRunner();
		val conn = DriverManager.getConnection(url, userDB, password);
		runner.update(conn, "DELETE FROM credit_request_entity;");
		runner.update(conn, "DELETE FROM payment_entity;");
		runner.update(conn, "DELETE FROM order_entity;");
	}

	public static void checkPaymentStatus(Status status) throws SQLException {
		val runner = new QueryRunner();
		val conn = DriverManager.getConnection(url, userDB, password);
		val paymentDataSQL = "SELECT status FROM payment_entity;";
		val payment = runner.query(conn, paymentDataSQL, new BeanHandler<>(Payment.class));
		assertEquals(status, payment.status);
	}

	public static void checkCreditStatus(Status status) throws SQLException {
		val runner = new QueryRunner();
		val conn = DriverManager.getConnection(url, userDB, password);
		val creditDataSQL = "SELECT status FROM credit_request_entity;";
		val credit = runner.query(conn, creditDataSQL, new BeanHandler<>(Credit.class));
		assertEquals(status, credit.status);
	}
}
