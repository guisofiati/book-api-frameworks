package github.guisofiati.bookapi.repositories

import github.guisofiati.bookapi.entities.Book
import org.springframework.data.jpa.repository.JpaRepository

interface BookRepository extends JpaRepository<Book, UUID> {}