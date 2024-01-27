package github.guisofiati.bookapi.services

import github.guisofiati.bookapi.entities.Book
import github.guisofiati.bookapi.repositories.BookRepository
import jakarta.persistence.EntityNotFoundException
import org.springframework.beans.BeanUtils
import org.springframework.stereotype.Service

@Service
class BookService {

    final BookRepository bookRepository

    BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository
    }

    Book insert(Book book) {
        bookRepository.save book
    }

    List<Book> findAll() {
        return bookRepository.findAll()
    }

    Book findOne(UUID bookId) {
        def book = bookRepository.findById(bookId)
        if (!book) {
            throw new EntityNotFoundException("Book id: ${bookId} not found.")
        }
        return book.get()
    }

    Book update(UUID bookId, Book book) {
        def bookEntity = bookRepository.getReferenceById(bookId);
        if (!bookEntity) {
            throw new EntityNotFoundException("Book id: ${bookId} not found.")
        }
        BeanUtils.copyProperties(book, bookEntity, "bookId")
        return bookRepository.save(bookEntity)
    }

    void delete(UUID bookId) {
        def bookEntity = bookRepository.getReferenceById(bookId);
        if (!bookEntity) {
            throw new EntityNotFoundException("Book id: ${bookId} not found.")
        }
        bookRepository.deleteById bookId
    }
}
