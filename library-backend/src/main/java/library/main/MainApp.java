package library.main;

import java.sql.SQLException;
import java.util.Scanner;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import library.dto.BookDTO;
import library.service.LibraryService;

@SpringBootApplication
@ComponentScan(basePackages = "library")
public class MainApp implements CommandLineRunner {

    private static LibraryService service = new LibraryService();
    private static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        SpringApplication.run(MainApp.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        runConsoleMenu();
    }

    private void runConsoleMenu() throws SQLException {
        while (true) {
            System.out.println("\n===== 도서 관리 시스템 =====");
            System.out.println("1. 도서 등록");
            System.out.println("2. 도서 목록 조회");
            System.out.println("3. 도서 대여");
            System.out.println("4. 도서 반납");
            System.out.println("5. 도서 단건 조회");
            System.out.println("0. 종료");
            System.out.println("===========================");
            System.out.print("선택: ");

            int menu = sc.nextInt();
            sc.nextLine();

            switch (menu) {
                case 1 -> insertBook();
                case 2 -> listBooks();
                case 3 -> rentBook();
                case 4 -> returnBook();
                case 5 -> findBook();
                case 0 -> {
                    System.out.println("프로그램 종료");
                    return;
                }
                default -> System.out.println("잘못된 입력입니다.");
            }
        }
    }

    private static void insertBook() {
        System.out.print("도서 번호: ");
        int bookId = sc.nextInt();
        sc.nextLine();

        System.out.print("제목: ");
        String title = sc.nextLine();

        System.out.print("글쓴이: ");
        String author = sc.nextLine();

        BookDTO book = new BookDTO(bookId, title, author, "Y");
        int result = service.addBook(book);

        if (result == 1) {
            System.out.println("등록 성공했습니다");
        } else {
            System.out.println("\n등록 실패했습니다");
        }
    }

    private static void listBooks() throws SQLException {
        service.getAllBooks().forEach(System.out::println);
    }

    private static void rentBook() {
        System.out.print("도서 번호: ");
        int bookId = sc.nextInt();

        System.out.print("회원 번호: ");
        int memberId = sc.nextInt();
        sc.nextLine();

        int result = service.rentBook(bookId, memberId);
        System.out.println(result == 1 ? "대여 성공" : "대여 실패");
    }

    private static void returnBook() {
        System.out.print("도서 번호: ");
        int bookId = sc.nextInt();
        sc.nextLine();

        int result = service.returnBook(bookId);
        System.out.println(result == 1 ? "반납 성공" : "반납 실패");
    }

    private static void findBook() {
        System.out.print("조회할 도서 번호: ");
        int bookId = sc.nextInt();
        sc.nextLine();

        BookDTO book = service.getBookById(bookId);

        if (book != null) {
            System.out.println("\n===== 조회 결과 =====");
            System.out.println("도서번호: " + book.getBookId());
            System.out.println("제목: " + book.getTitle());
            System.out.println("저자: " + book.getAuthor());
            System.out.println("상태: " + book.getStatus());
            System.out.println("=====================");
        } else {
            System.out.println("해당 도서가 존재하지 않습니다.");
        }
    }
}