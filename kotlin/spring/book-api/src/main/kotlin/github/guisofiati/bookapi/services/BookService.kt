package github.guisofiati.bookapi.services

import github.guisofiati.bookapi.entities.Book
import github.guisofiati.bookapi.repositories.BookRepository
import jakarta.persistence.EntityNotFoundException
import org.springframework.beans.BeanUtils
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.*

@Service
class BookService(val bookRepository: BookRepository) {

    fun insert(book: Book): Book {
        return bookRepository.save(book)
    }

    @Transactional(readOnly = true)
    fun findAll(): List<Book> {
        return bookRepository.findAll()
    }

    @Transactional(readOnly = true)
    fun findOne(bookId: UUID): Book {
        return bookRepository.findByIdOrNull(bookId) ?: throw EntityNotFoundException("Book id: $bookId not found.")
    }

    @Transactional
    fun update(bookId: UUID, book: Book): Book {
        val bookEntity = bookRepository.getReferenceById(bookId)
        if (bookEntity == null) {
            throw EntityNotFoundException("Book id: $bookId not found.")
        }
        BeanUtils.copyProperties(book, bookEntity, "bookId")
        return bookRepository.save(bookEntity)
    }

    fun delete(bookId: UUID) {
        val bookEntity = bookRepository.getReferenceById(bookId)
        if (bookEntity == null) {
            throw EntityNotFoundException("Book id: $bookId not found.")
        }
        bookRepository.deleteById(bookId)
    }
}
