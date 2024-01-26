package github.guisofiati.bookapi.controllers

import github.guisofiati.bookapi.entities.Book
import github.guisofiati.bookapi.services.BookService
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
@RequestMapping("/api/v1/books")
class BookController(val bookService: BookService) {

    @PostMapping
    fun insert(@Valid @RequestBody book: Book): ResponseEntity<Book> {
        return ResponseEntity.status(HttpStatus.CREATED).body(bookService.insert(book))
    }

    @GetMapping
    fun findAll(): ResponseEntity<List<Book>> {
        return ResponseEntity.status(HttpStatus.OK).body(bookService.findAll())
    }

    @GetMapping("/{bookId}")
    fun findOne(@PathVariable bookId: UUID): ResponseEntity<Book> {
        return ResponseEntity.status(HttpStatus.OK).body(bookService.findOne(bookId))
    }

    @PutMapping("/{bookId}")
    fun update(@PathVariable bookId: UUID, @RequestBody book: Book): ResponseEntity<Book> {
        return ResponseEntity.status(HttpStatus.OK).body(bookService.update(bookId, book))
    }

    @DeleteMapping("/{bookId}")
    fun delete(@PathVariable bookId: UUID): ResponseEntity<Unit> {
        bookService.delete(bookId)
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build()
    }
}