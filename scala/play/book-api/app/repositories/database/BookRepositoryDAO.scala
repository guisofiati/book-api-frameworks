package repositories.database

import anorm.{Macro, RowParser, SQL}
import jakarta.inject.Inject
import play.api.Logging
import entities.Book
import play.api.db.Database

import java.sql.Connection
import java.util.UUID

class BookRepositoryDAO @Inject()(db: Database) extends Logging {
  private val parser: RowParser[Book] = Macro.namedParser[Book]

  def findAll()(implicit conn: Connection): List[Book] = {
    SQL("SELECT * FROM tb_books").as(parser.*)
  }

  def findById(id: UUID)(implicit conn: Connection): Option[Book] = {
    SQL("SELECT * FROM tb_books WHERE bookId = {id}::uuid")
      .on("id" -> id)
      .as(parser.singleOpt)
  }

  def insert(book: Book)(implicit conn: Connection): Either[Error, Book] = {
    try {
      SQL("INSERT INTO tb_books (bookId, title, synopsis, language, publisher, pages, publicationDate) " +
        "VALUES ({bookId}::uuid, {title}, {synopsis}, {language}, {publisher}, {pages}, {publicationDate})")
        .on("bookId" -> book.bookId, "title" -> book.title, "synopsis" -> book.synopsis,
          "language" -> book.language, "publisher" -> book.publisher, "pages" -> book.pages, "publicationDate" -> book.publicationDate)
        .execute()
      Right(book)
    } catch {
      case e: Exception => {
        logger.error(e.getMessage)
        Left(throw new Error(e.getMessage))
      }
    }
  }

  def update(id: UUID, book: Book)(implicit conn: Connection): Int = {
    SQL("UPDATE tb_books SET title = {title}, synopsis = {synopsis}, language = {language}, " +
      "publisher = {publisher}, pages = {pages}, publicationDate = {publicationDate} WHERE bookId = {id}::uuid")
      .on("id" -> book.bookId, "title" -> book.title, "synopsis" -> book.synopsis,
        "language" -> book.language, "publisher" -> book.publisher, "pages" -> book.pages, "publicationDate" -> book.publicationDate)
      .executeUpdate()
  }

  def delete(id: UUID)(implicit conn: Connection): Int = {
    SQL("DELETE FROM tb_books WHERE bookId = {id}::uuid").on("id" -> id).executeUpdate()
  }
}
