package com.simone.lms.controller;

import com.simone.lms.exception.BookException;
import com.simone.lms.payload.dto.BookDTO;
import com.simone.lms.payload.request.BookSearchRequest;
import com.simone.lms.payload.response.ApiResponse;
import com.simone.lms.payload.response.PageResponse;
import com.simone.lms.services.BookService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/books")
public class BookController {

    private final BookService bookService;

    @PostMapping("/admin/bulk")
    public ResponseEntity<?> createBooksBulk (@Valid @RequestBody List<BookDTO> bookDTOs) throws BookException {

        List<BookDTO> createdBooks = bookService.createBooksBulk(bookDTOs);

        return ResponseEntity.ok(createdBooks);
    }

    @GetMapping("/{bookId}")
    public ResponseEntity<BookDTO> getBookById(@PathVariable("bookId") Long bookId) throws BookException {

        BookDTO bookDTO = bookService.getBookById(bookId);

        return ResponseEntity.ok(bookDTO);
    }

    @GetMapping("/isbn/{isbn}")
    public ResponseEntity<BookDTO> getBookByIsbn(@PathVariable("isbn") String isbn) throws BookException {

        BookDTO bookDTO = bookService.getBookByIsbn(isbn);

        return ResponseEntity.ok(bookDTO);
    }

    @PutMapping("/admin/{bookId}")
    public ResponseEntity<BookDTO> updateBook(
            @PathVariable("bookId") Long bookId,
            @Valid @RequestBody BookDTO bookDTO) throws BookException{

        BookDTO updatedBook = bookService.updateBook(bookId, bookDTO);
        return ResponseEntity.ok(updatedBook);
    }

    @DeleteMapping("/admin/{bookId}")
    public ResponseEntity<ApiResponse> deleteBook(@PathVariable("bookId") Long id) throws BookException {
        bookService.deleteBook(id);
        return ResponseEntity.ok(new ApiResponse("Book deleted successfully", true));
    }

    @DeleteMapping("/admin/{bookId}/permanent")
    public ResponseEntity<ApiResponse> hardDeleteBook(@PathVariable("bookId") Long bookId) throws BookException {
        bookService.hardDeleteBook(bookId);
        return ResponseEntity.ok(new ApiResponse("Book permanently deleted", true));
    }

    @GetMapping
    public ResponseEntity<PageResponse<BookDTO>> searchBooks (
            @RequestParam(required = false) Long genreId,
            @RequestParam(required = false, defaultValue = "false") Boolean availableOnly,
            @RequestParam(defaultValue = "true") boolean activeOnly,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size,
            @RequestParam(defaultValue = "createdAt") String sortBy,
            @RequestParam(defaultValue = "DESC") String sortDirection) {

        BookSearchRequest searchRequest = new BookSearchRequest();
        searchRequest.setGenreId(genreId);
        searchRequest.setAvailableOnly(availableOnly);
        searchRequest.setPage(page);
        searchRequest.setSize(size);
        searchRequest.setSortBy(sortBy);
        searchRequest.setSortDirection(sortDirection);

        PageResponse<BookDTO> books = bookService.searchBookWithFilters(searchRequest);
        return ResponseEntity.ok(books);

    }


    @PostMapping("/search")
    public ResponseEntity<PageResponse<BookDTO>> advancedSearch (
            @RequestBody BookSearchRequest searchRequest) {

        PageResponse<BookDTO> books = bookService.searchBookWithFilters(searchRequest);

        return ResponseEntity.ok(books);
    }

    @GetMapping("/stats")
    public ResponseEntity<BookStatResponse> getBookStats() {
        long totalActive = bookService.getTotalActiveBooks();
        long totalAvailable = bookService.getTotalAvailableBooks();

        BookStatResponse stats = new BookStatResponse(totalActive, totalAvailable);
        return ResponseEntity.ok(stats);
    }

    public static class BookStatResponse {
        public long totalActiveBooks;
        public long totalAvailableBooks;

        public BookStatResponse(long totalActiveBooks, long totalAvailableBooks) {
            this.totalActiveBooks = totalActiveBooks;
            this.totalAvailableBooks = totalAvailableBooks;
        }
    }

}
