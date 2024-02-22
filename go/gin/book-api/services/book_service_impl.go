package services

import (
	"github.com/go-playground/validator/v10"
	"github.com/google/uuid"
	"github.com/guisofiati/book-api-frameworks/go/gin/book-api/data/request"
	"github.com/guisofiati/book-api-frameworks/go/gin/book-api/data/response"
	"github.com/guisofiati/book-api-frameworks/go/gin/book-api/helpers"
	"github.com/guisofiati/book-api-frameworks/go/gin/book-api/models"
	"github.com/guisofiati/book-api-frameworks/go/gin/book-api/repositories"
)

type BookServiceImpl struct {
	BookRepository repositories.BookRepository
	Validate       *validator.Validate
}

func NewBookServiceImpl(bookRepository repositories.BookRepository, validate *validator.Validate) BookService {
	return &BookServiceImpl{
		BookRepository: bookRepository,
		Validate:       validate,
	}
}

func (s BookServiceImpl) FindAll() []response.BookDataResponse {
	result := s.BookRepository.FindAll()

	if len(result) == 0 {
		return []response.BookDataResponse{}
	}

	var books []response.BookDataResponse
	for _, v := range result {
		book := response.BookDataResponse{
			Id:              v.Id,
			Title:           v.Title,
			Synopsis:        v.Synopsis,
			Pages:           v.Pages,
			Language:        v.Language,
			Publisher:       v.Publisher,
			PublicationDate: v.PublicationDate,
		}
		books = append(books, book)
	}

	return books
}

func (s BookServiceImpl) FindById(bookId uuid.UUID) response.BookDataResponse {
	book, err := s.BookRepository.FindById(bookId)
	helpers.Error(err)

	response := response.BookDataResponse{
		Id:              book.Id,
		Title:           book.Title,
		Synopsis:        book.Synopsis,
		Pages:           book.Pages,
		Language:        book.Language,
		Publisher:       book.Publisher,
		PublicationDate: book.PublicationDate,
	}
	return response
}

func (s BookServiceImpl) Insert(book request.InsertBookDataRequest) response.BookDataResponse {
	err := s.Validate.Struct(book)
	helpers.Error(err)

	bookModel := models.Book{
		Title:           book.Title,
		Synopsis:        book.Synopsis,
		Pages:           book.Pages,
		Language:        book.Language,
		Publisher:       book.Publisher,
		PublicationDate: book.PublicationDate,
	}
	result := s.BookRepository.Insert(bookModel)

	response := response.BookDataResponse{
		Id:              result.Id,
		Title:           result.Title,
		Synopsis:        result.Synopsis,
		Pages:           result.Pages,
		Language:        result.Language,
		Publisher:       result.Publisher,
		PublicationDate: result.PublicationDate,
	}

	return response
}

func (s BookServiceImpl) Update(bookId uuid.UUID, dto request.UpdateBookDataRequest) response.BookDataResponse {
	book, err := s.BookRepository.FindById(bookId)
	helpers.Error(err)

	book.Title = dto.Title
	book.Synopsis = dto.Synopsis
	book.Pages = dto.Pages
	book.Language = dto.Language
	book.Publisher = dto.Publisher
	book.PublicationDate = dto.PublicationDate

	s.BookRepository.Update(bookId, *book)

	response := response.BookDataResponse{
		Id:              book.Id,
		Title:           book.Title,
		Synopsis:        book.Synopsis,
		Pages:           book.Pages,
		Language:        book.Language,
		Publisher:       book.Publisher,
		PublicationDate: book.PublicationDate,
	}
	return response
}

func (s BookServiceImpl) Delete(bookId uuid.UUID) {
	s.BookRepository.Delete(bookId)
}
