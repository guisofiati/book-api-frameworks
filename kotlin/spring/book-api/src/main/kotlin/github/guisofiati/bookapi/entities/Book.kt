package github.guisofiati.bookapi.entities

import github.guisofiati.bookapi.dtos.BookDto
import jakarta.persistence.*
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Past
import jakarta.validation.constraints.Size
import org.springframework.beans.BeanUtils
import java.time.LocalDateTime
import java.util.*
import kotlin.reflect.full.createInstance

@Entity
@Table(name = "TB_BOOKS")
class Book(
    @Id @GeneratedValue(strategy = GenerationType.AUTO) var bookId: UUID?,
    @field:NotBlank @field:Size(min = 4, max = 150, message = "Title must be greater than 3") var title: String,
    @field:NotBlank @Column(columnDefinition = "TEXT") var synopsis: String,
    @field:NotBlank var language: String,
    @field:NotBlank var publisher: String,
    @field:Past var publicationDate: LocalDateTime,
//    var slug: String = title.toSlug()
) {
    fun convertBookEntityToBookDto(): BookDto {
        val bookDto = BookDto::class.createInstance()
        BeanUtils.copyProperties(this, bookDto)
        return bookDto
    }
}
