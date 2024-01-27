package github.guisofiati.bookapi.controllers

import github.guisofiati.bookapi.entities.Book
import github.guisofiati.bookapi.services.BookService
import jakarta.validation.Valid
import org.apache.coyote.Request
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/books")
class BookController {

    final BookService bookService;

    BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @PostMapping
    ResponseEntity<Book> insert(@Valid @RequestBody Book book) {
        return ResponseEntity.status(HttpStatus.CREATED).body(bookService.insert(book))
    }

    @GetMapping
    ResponseEntity<List<Book>> findAll() {
        return ResponseEntity.status(HttpStatus.OK).body(bookService.findAll())
    }

    @GetMapping("/{bookId}")
    ResponseEntity<Book> findOne(@PathVariable("bookId") UUID bookId) {
        return ResponseEntity.status(HttpStatus.OK).body(bookService.findOne(bookId))
    }

    @PutMapping("/{bookId}")
    ResponseEntity<Book> update(@PathVariable("bookId") UUID bookId, @Valid @RequestBody Book book) {
        return ResponseEntity.status(HttpStatus.OK).body(bookService.update(bookId, book))
    }

    @DeleteMapping("/{bookId}")
    ResponseEntity<Void> delete(@PathVariable("bookId") UUID bookId) {
        bookService.delete bookId
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build()
    }
}
