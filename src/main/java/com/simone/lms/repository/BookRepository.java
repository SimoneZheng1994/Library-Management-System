package com.simone.lms.repository;

import com.simone.lms.model.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface BookRepository extends JpaRepository<Book, Long> {

    Optional<Book> findByIsbn(String isbn);

    boolean existsByIsbn(String isbn);

// book - java programing
// prog

    @Query("SELECT b FROM Book b WHERE " +
            "b.active = true AND " +
            "(:searchTerm IS NULL OR " +
            "LOWER(b.title) LIKE lower(concat ('%', :searchTerm, '%')) OR " +
            "LOWER(b.author) LIKE lower(concat ('%', :searchTerm, '%')) OR " +
            "LOWER(b.isbn) LIKE lower(concat ('%', :searchTerm, '%'))) AND " +
            "(:genreId IS NULL OR b.genre.id = :genreId) AND " +
            "(:availableOnly = false OR b.availableCopies > 0)"
    )
    Page<Book> searchBooksWithFilters(
            @Param("searchTerm") String searchTerm,
            @Param("genreId") Long genreId,
            @Param("availableOnly") boolean availableOnly,
            Pageable pageable
    );

    long countByActiveTrue();

    @Query("SELECT COUNT(b) FROM Book b WHERE b.availableCopies > 0 AND b.active = true")
    long countAvailableBooks();
}
