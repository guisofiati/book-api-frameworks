package github.guisofiati.bookapi.entities

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Past
import jakarta.validation.constraints.Size

import java.time.LocalDateTime

@Entity
@Table(name = "TB_BOOKS")
class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    UUID bookId

    @NotBlank
    @Size(min = 4, max = 150, message = "Title must be greater than 3")
    String title

    @Column(columnDefinition = "TEXT")
    @NotBlank
    String synopsis

    @NotBlank
    String language

    @NotBlank
    String publisher

    @Past
    LocalDateTime publicationDate
}
