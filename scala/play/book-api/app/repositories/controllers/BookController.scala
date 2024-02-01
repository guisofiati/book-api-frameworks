package repositories.controllers

import dtos.BookDto
import jakarta.inject.{Inject, Singleton}
import play.api.mvc.{Action, AnyContent, BaseController, ControllerComponents}
import services.BookService
import entities.Book
import play.api.libs.json._

import java.util.UUID
import scala.concurrent.{ExecutionContext, Future}

@Singleton
class BookController @Inject()(val controllerComponents: ControllerComponents, bookService: BookService)
                              (implicit ec: ExecutionContext) extends BaseController {

  implicit val bookFormat: OFormat[Book] = Json.format[Book]

  def findAll(): Action[AnyContent] = Action.async {implicit request =>
    bookService.findAll().map { book =>
      Ok(Json.toJson(book))
    }
  }

  def findById(id: UUID): Action[AnyContent] = Action.async {implicit request =>
    bookService.findById(id).map {
      case Some(book) =>  Ok(Json.toJson(book))
      case None => NotFound(s"Book id: $id not found.")
    }
  }

  def insert: Action[JsValue] = Action(parse.json).async {implicit request =>
    request.body
      .validate[BookDto]
      .fold(
        errors => Future {
          BadRequest(errors.mkString)
        },
        bookDto => {
          bookService.insert(Book(UUID.randomUUID(), bookDto.title, bookDto.synopsis, bookDto.language, bookDto.publisher, bookDto.pages, bookDto.publicationDate))
            .map {
              case Left(_) => BadRequest("Error inserting a new book")
              case Right(book) => Created(Json.toJson(book))
            }
        }
      )
  }

  // TODO: remove bookId from request body
  def update(id: UUID): Action[JsValue] = Action(parse.json).async {implicit request =>
    request.body
      .validate[Book]
      .fold(
        errors => Future {
          BadRequest(errors.mkString)
        },
        book => {
          bookService.update(id, book)
            .map {
              case 1 => Ok(Json.toJson(book))
              case 0 => BadRequest(s"Book id: $id not found.")
            }
        }
      )
  }

  def delete(id: UUID): Action[AnyContent] = Action.async { implicit request =>
    bookService.delete(id).map {
      case 1 => NoContent
      case 0 => BadRequest(s"Book id: $id not found.")
    }
  }
}
