package dtos

import play.api.libs.json.{Json, OFormat}

import java.time.LocalDateTime

case class BookDto(title: String, synopsis: String, language: String, publisher: String, pages: Int, publicationDate: LocalDateTime)

object BookDto {
  implicit val bookDtoJson: OFormat[BookDto] = Json.format[BookDto]
}