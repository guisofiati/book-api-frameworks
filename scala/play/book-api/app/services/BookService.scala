package services

import jakarta.inject.Inject
import play.api.db.Database
import repositories.database.BookRepositoryImpl
import entities.Book

import java.util.UUID
import scala.concurrent.{ExecutionContext, Future}

class BookService @Inject()(db: Database, bookRepository: BookRepositoryImpl)(implicit ec: ExecutionContext) {

  def findAll(): Future[List[Book]] = {
    bookRepository.findAll()
  }

  def findById(id: UUID): Future[Option[Book]] = {
    bookRepository.findById(id)
  }

  def insert(book: Book): Future[Either[Error, Book]] = {
    bookRepository.insert(book)
  }

  def update(id: UUID, book: Book): Future[Int] = {
    bookRepository.update(id, book)
  }

  def delete(id: UUID): Future[Int] = {
    bookRepository.delete(id)
  }
}
