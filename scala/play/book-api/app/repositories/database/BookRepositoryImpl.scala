package repositories.database

import anorm.{Macro, RowParser}
import entities.Book
import jakarta.inject.Inject
import play.api.db.Database
import repositories.BookRepository

import java.util.UUID
import scala.concurrent.{ExecutionContext, Future}

final class BookRepositoryImpl @Inject()(db: Database,
                                         ec: ExecutionContext, bookRepositoryDAO: BookRepositoryDAO) extends BookRepository {
  val parser: RowParser[Book] = Macro.namedParser[Book]

  override def findAll(): Future[List[Book]] = {
    Future {
      db.withConnection { implicit conn =>
        bookRepositoryDAO.findAll()
      }
    } (ec)
  }

  override def findById(id: UUID): Future[Option[Book]] = {
    Future {
      db.withConnection { implicit conn =>
        bookRepositoryDAO.findById(id)
      }
    } (ec)
  }

  override def insert(book: Book): Future[Either[Error, Book]] = {
    Future {
      db.withConnection { implicit conn =>
        bookRepositoryDAO.insert(book)
      }
    } (ec)
  }

  override def update(id: UUID, book: Book): Future[Int] = {
    Future {
      db.withConnection { implicit conn =>
        bookRepositoryDAO.update(id, book)
      }
    } (ec)
  }

  override def delete(id: UUID): Future[Int] = {
    Future {
      db.withConnection { implicit conn =>
        bookRepositoryDAO.delete(id)
      }
    } (ec)
  }
}
