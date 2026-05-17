package library.main;

import java.sql.SQLException;
import java.util.Scanner;

import library.dto.BookDTO;
import library.service.LibraryService;

public class MainApp {

    // 비즈니스 로직 담당
    // Service 계층 객체
    private static LibraryService service = new LibraryService();

    // 사용자 입력을 받기 위한 Scanner
    private static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) throws SQLException {

        // 프로그램이 종료되기 전까지 계속 실행되는 무한 반복문
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
            // 버퍼 정리
            sc.nextLine();

            // switch 화살표 문법 사용
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

    // 도서 등록
    private static void insertBook() {
        System.out.print("제목: ");
        String title = sc.nextLine();

        System.out.print("글쓴이: ");
        String author = sc.nextLine();

        // DTO 생성
        BookDTO book = new BookDTO(0, title, author, "Y");


        int result = service.addBook(book);

        if (result == 1) {
            System.out.println("등록 성공했습니다");
        } else {
            System.out.println("\n등록 실패했습니다");
        }
    }

    // 도서 목록 조회
    private static void listBooks() throws SQLException {

        service.getAllBooks()
                .forEach(System.out::println);
    }

    // 도서 대여
    private static void rentBook() {
        System.out.print("도서 번호: ");
        int bookId = sc.nextInt();

        System.out.print("회원 번호: ");
        int memberId = sc.nextInt();

        int result = service.rentBook(bookId, memberId);

        System.out.println(result == 1 ? "대여 성공" : "대여 실패");
    }

    // 도서 반납
    private static void returnBook() {
        System.out.print("도서 번호: ");
        int bookId = sc.nextInt();

        // Service에서 반납 처리, 상태 변경 수행
        int result = service.returnBook(bookId);

        System.out.println(result == 1 ? "반납 성공" : "반납 실패");
    }

    // 도서 단건 조회
    private static void findBook() {
        System.out.print("조회할 도서 번호: ");
        int bookId = sc.nextInt();


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