package library.controller;

import java.sql.SQLException;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import library.dto.BookDTO;
import library.service.LibraryService;

@RestController
@RequestMapping("/api/books")
@CrossOrigin(origins = "http://localhost:3000")
public class BookController {

    private final LibraryService service = new LibraryService();

    @GetMapping
    public ResponseEntity<List<BookDTO>> getAllBooks() {
        try {
            List<BookDTO> books = service.getAllBooks();
            return ResponseEntity.ok(books);
        } catch (SQLException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping
    public ResponseEntity<String> createBook(@RequestBody BookDTO bookDTO) {
        BookDTO book = BookDTO.builder()
                .title(bookDTO.getTitle())
                .author(bookDTO.getAuthor())
                .status("Y")
                .build();

        int result = service.addBook(book);

        if (result == 1) {
            return ResponseEntity.status(HttpStatus.CREATED).body("도서 등록 성공");
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("도서 등록 실패");
        }
    }

    @GetMapping("/{bookId}")
    public ResponseEntity<?> findBook(@PathVariable("bookId") int bookId) {
        BookDTO book = service.getBookById(bookId);

        if (book != null) {
            return ResponseEntity.ok(book);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("해당 도서가 존재하지 않습니다.");
        }
    }

    @PostMapping("/{bookId}/rent")
    public ResponseEntity<String> rentBook(@PathVariable("bookId") int bookId, @RequestParam("memberId") int memberId) {
        int result = service.rentBook(bookId, memberId);

        if (result == 1) {
            return ResponseEntity.ok("대여 성공");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("대여 실패");
        }
    }

    @PostMapping("/{bookId}/return")
    public ResponseEntity<String> returnBook(@PathVariable("bookId") int bookId) {
        int result = service.returnBook(bookId);

        if (result == 1) {
            return ResponseEntity.ok("반납 성공");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("반납 실패");
        }
    }
}