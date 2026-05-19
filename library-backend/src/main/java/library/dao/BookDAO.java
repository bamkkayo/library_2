package library.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import library.dto.BookDTO;
import library.util.DBUtil;

public class BookDAO {

    public int insert(BookDTO book) throws SQLException {
        String sql = "INSERT INTO BOOKS(book_id, title, author, status) VALUES (?, ?, ?, 'Y')";

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, book.getBookId());
            pstmt.setString(2, book.getTitle());
            pstmt.setString(3, book.getAuthor());

            return pstmt.executeUpdate();
        }
    }

    public List<BookDTO> selectAll() throws SQLException {
        List<BookDTO> list = new ArrayList<>();
        String sql = "SELECT * FROM BOOKS";

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                BookDTO book = BookDTO.builder()
                        .bookId(rs.getInt("book_id"))
                        .title(rs.getString("title"))
                        .author(rs.getString("author"))
                        .status(rs.getString("status"))
                        .build();
                list.add(book);
            }
        }
        return list;
    }

    public BookDTO selectById(Connection conn, int bookId) throws SQLException {
        String sql = "SELECT * FROM BOOKS WHERE book_id = ?";

        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, bookId);

            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return BookDTO.builder()
                            .bookId(rs.getInt("book_id"))
                            .title(rs.getString("title"))
                            .author(rs.getString("author"))
                            .status(rs.getString("status"))
                            .build();
                }
            }
        }
        return null;
    }

    public BookDTO selectById(int bookId) throws SQLException {
        String sql = "SELECT * FROM BOOKS WHERE book_id = ?";

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, bookId);

            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return BookDTO.builder()
                            .bookId(rs.getInt("book_id"))
                            .title(rs.getString("title"))
                            .author(rs.getString("author"))
                            .status(rs.getString("status"))
                            .build();
                }
            }
        }
        return null;
    }

    public int updateStatus(Connection conn, int bookId, String status) throws SQLException {
        String sql = "UPDATE BOOKS SET status = ? WHERE book_id = ?";

        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, status);
            pstmt.setInt(2, bookId);
            return pstmt.executeUpdate();
        }
    }
}