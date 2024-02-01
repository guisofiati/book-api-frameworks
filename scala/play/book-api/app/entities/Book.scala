package entities

import play.api.libs.json.{Json, OFormat}

import java.time.LocalDateTime
import java.util.UUID

case class Book(bookId: UUID, title: String, synopsis: String, language: String, publisher: String, pages: Int, publicationDate: LocalDateTime)

object Book {
  implicit val bookFormat: OFormat[Book] = Json.format[Book]
}
