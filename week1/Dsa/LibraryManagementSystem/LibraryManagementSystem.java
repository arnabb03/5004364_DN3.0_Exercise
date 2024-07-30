import java.util.Arrays;

public class LibraryManagementSystem {

    static class Book {
        private int bookId;
        private String title;
        private String author;

        public Book(int bookId, String title, String author) {
            this.bookId = bookId;
            this.title = title;
            this.author = author;
        }

        public int getBookId() {
            return bookId;
        }

        public String getTitle() {
            return title;
        }

        public String getAuthor() {
            return author;
        }

        @Override
        public String toString() {
            return "Book(id=" + bookId + ", title=" + title + ", author=" + author + ")";
        }
    }

    public static Book linearSearch(Book[] books, String title) {
        for (Book book : books) {
            if (book.getTitle().equalsIgnoreCase(title)) {
                return book;
            }
        }
        return null;
    }

    public static Book binarySearch(Book[] books, String title) {
        int low = 0;
        int high = books.length - 1;
        while (low <= high) {
            int mid = (low + high) / 2;
            int comparison = books[mid].getTitle().compareToIgnoreCase(title);
            if (comparison == 0) {
                return books[mid];
            } else if (comparison < 0) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return null;
    }

    public static void main(String[] args) {
        Book[] books = {
            new Book(1, "To Kill a Mockingbird", "Harper Lee"),
            new Book(2, "1984", "George Orwell"),
            new Book(3, "The Great Gatsby", "F. Scott Fitzgerald"),
            new Book(4, "The Catcher in the Rye", "J.D. Salinger"),
            new Book(5, "Moby-Dick", "Herman Melville")
        };

        // Sort the array by title for binary search
        Arrays.sort(books, (a, b) -> a.getTitle().compareToIgnoreCase(b.getTitle()));

        String searchTitle = "1984";
        Book result;

        result = linearSearch(books, searchTitle);
        System.out.println("Linear Search Result:");
        System.out.println(result != null ? result : "Book not found");

        result = binarySearch(books, searchTitle);
        System.out.println("Binary Search Result:");
        System.out.println(result != null ? result : "Book not found");
    }
}
