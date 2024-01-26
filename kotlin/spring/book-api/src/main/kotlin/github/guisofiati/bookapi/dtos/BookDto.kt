package github.guisofiati.bookapi.dtos

import java.time.LocalDateTime
import java.util.*

class BookDto(
     var bookId: UUID?,
     var title: String,
     var synopsis: String,
     var language: String,
     var publisher: String,
     var publicationDate: LocalDateTime,
)
