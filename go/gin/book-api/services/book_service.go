package services

import (
	"github.com/google/uuid"
	"github.com/guisofiati/book-api-frameworks/go/gin/book-api/data/request"
	"github.com/guisofiati/book-api-frameworks/go/gin/book-api/data/response"
)

type BookService interface {
	FindAll() []response.BookDataResponse
	FindById(bookId uuid.UUID) response.BookDataResponse
	Insert(book request.InsertBookDataRequest) (e response.BookDataResponse)
	Update(bookId uuid.UUID, book request.UpdateBookDataRequest) (e response.BookDataResponse)
	Delete(bookId uuid.UUID)
}
