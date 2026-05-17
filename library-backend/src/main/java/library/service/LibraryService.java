package library.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import library.dao.BookDAO;
import library.dao.RentalDAO;
import library.dto.BookDTO;
import library.util.DBUtil;

public class LibraryService {

    private static final Logger log = LoggerFactory.getLogger(LibraryService.class);

    private BookDAO bookDAO = new BookDAO();
    private RentalDAO rentalDAO = new RentalDAO();

    public int addBook(BookDTO book) {
        try {
            return bookDAO.insert(book);
        } catch (SQLException e) {
            log.error("도서 등록 중 예외 발생", e);
            return 0;
        }
    }

    public List<BookDTO> getAllBooks() throws SQLException {
        return bookDAO.selectAll();
    }

    public BookDTO getBookById(int bookId) {
        try {
            return bookDAO.selectById(bookId);
        } catch (SQLException e) {
            log.error("도서 단건 조회 중 예외 발생 - 도서 ID: {}", bookId, e);
            return null;
        }
    }

    public int rentBook(int bookId, int memberId) {
        Connection conn = null;

        try {
            conn = DBUtil.getConnection();
            conn.setAutoCommit(false);

            BookDTO book = bookDAO.selectById(conn, bookId);

            if (book == null || !"Y".equals(book.getStatus())) {
                log.info("도서 대여 불가 - 도서 ID: {}, 회원 ID: {}", bookId, memberId);
                return 0;
            }

            rentalDAO.insert(conn, bookId, memberId);
            bookDAO.updateStatus(conn, bookId, "N");

            conn.commit();
            return 1;

        } catch (Exception e) {
            log.error("도서 대여 중 예외 발생 - 도서 ID: {}, 회원 ID: {}", bookId, memberId, e);

            if (conn != null) {
                try {
                    conn.rollback();
                } catch (SQLException ex) {
                    log.error("도서 대여 롤백 중 예외 발생", ex);
                }
            }
            return 0;

        } finally {
            if (conn != null) {
                try {
                    conn.setAutoCommit(true);
                    conn.close();
                } catch (SQLException e) {
                    log.error("도서 대여 커넥션 종료 중 예외 발생", e);
                }
            }
        }
    }

    public int returnBook(int bookId) {
        Connection conn = null;

        try {
            conn = DBUtil.getConnection();
            conn.setAutoCommit(false);

            bookDAO.updateStatus(conn, bookId, "Y");
            rentalDAO.updateReturnDate(conn, bookId);

            conn.commit();
            return 1;

        } catch (Exception e) {
            log.error("도서 반납 중 예외 발생 - 도서 ID: {}", bookId, e);

            if (conn != null) {
                try {
                    conn.rollback();
                } catch (SQLException ex) {
                    log.error("도서 반납 롤백 중 예외 발생", ex);
                }
            }
            return 0;

        } finally {
            if (conn != null) {
                try {
                    conn.setAutoCommit(true);
                    conn.close();
                } catch (SQLException e) {
                    log.error("도서 반납 커넥션 종료 중 예외 발생", e);
                }
            }
        }
    }
}