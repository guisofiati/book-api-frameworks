package repositories

import scala.concurrent.Future
import entities.Book

import java.util.UUID

trait BookRepository {
  def findAll(): Future[List[Book]]
  def findById(id: UUID): Future[Option[Book]]
  def insert(book: Book): Future[Either[Error, Book]]
  def update(id: UUID, book: Book): Future[Int]
  def delete(id: UUID): Future[Int]
}
