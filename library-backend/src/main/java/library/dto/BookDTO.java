package library.dto;

public class BookDTO {

    private int bookId;
    private String title;
    private String author;
    private String status;

    public BookDTO() {
    }

    public BookDTO(int bookId, String title, String author, String status) {
        this.bookId = bookId;
        this.title = title;
        this.author = author;
        this.status = status;
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return bookId + " | " + title + " | " + author + " | " + status;
    }

    public static BookDTOBuilder builder() {
        return new BookDTOBuilder();
    }

    public static class BookDTOBuilder {
        private int bookId;
        private String title;
        private String author;
        private String status;

        BookDTOBuilder() {
        }

        public BookDTOBuilder bookId(int bookId) {
            this.bookId = bookId;
            return this;
        }

        public BookDTOBuilder title(String title) {
            this.title = title;
            return this;
        }

        public BookDTOBuilder author(String author) {
            this.author = author;
            return this;
        }

        public BookDTOBuilder status(String status) {
            this.status = status;
            return this;
        }

        public BookDTO build() {
            return new BookDTO(this.bookId, this.title, this.author, this.status);
        }
    }
}