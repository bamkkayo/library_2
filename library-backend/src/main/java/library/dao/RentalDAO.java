package library.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class RentalDAO {

    public int updateReturnDate(Connection conn, int bookId) throws SQLException {
        String sql = """
                UPDATE RENTALS
                SET return_date = CURRENT_DATE
                WHERE book_id = ?
                AND return_date IS NULL
                """;

        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, bookId);
            return pstmt.executeUpdate();
        }
    }

    public int insert(Connection conn, int bookId, int memberId) throws SQLException {
        String sql = """
                INSERT INTO RENTALS (book_id, member_id, rent_date)
                VALUES (?, ?, CURRENT_DATE)
                """;

        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, bookId);
            pstmt.setInt(2, memberId);
            return pstmt.executeUpdate();
        }
    }
}