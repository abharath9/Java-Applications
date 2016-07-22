package tangible;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

class GetEmpInfo {
	public static void main(String args[]) throws SQLException, IOException {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException x) {
			System.out.println("Driver could not be loaded");
		}
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, -1);
		String yesterday = dateFormat.format(cal.getTime());
		String dbacct, passwrd, Name, Title;
		System.out.print("Enter database account:");
		dbacct = System.console().readLine();
		System.out.print("Enter password:");
		passwrd = System.console().readLine();
		Connection conn = DriverManager.getConnection("jdbc:oracle:oci8:" + dbacct + "/" + passwrd);
		String stmt1 = "select BORROWER.Name, BOOK.Title from BORROWER, BOOK, BOOK_LOANS where BOOK_LOANS.Book_id=BOOK.Book_id and BOOK_LOANS.Due_date="
				+ yesterday + " and BORROWER.Card_no=BOOK_LOANS.Card_no and BOOK_LOANS.Book_id=BOOK.Book_id";
		PreparedStatement p = conn.prepareStatement(stmt1);
		ResultSet r = p.executeQuery();
		while (r.next()) {
			Name = r.getString(1);
			Title = r.getString(2);
			System.out.println(Name + "	" + Title);
		}
	}
}