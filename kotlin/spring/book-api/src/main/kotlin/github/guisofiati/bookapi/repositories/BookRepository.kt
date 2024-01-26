package github.guisofiati.bookapi.repositories

import github.guisofiati.bookapi.entities.Book
import org.springframework.data.jpa.repository.JpaRepository
import java.util.UUID

interface BookRepository: JpaRepository<Book, UUID> {}
