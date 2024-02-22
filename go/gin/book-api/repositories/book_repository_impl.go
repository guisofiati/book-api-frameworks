package repositories

import (
	"errors"

	"github.com/google/uuid"
	"github.com/guisofiati/book-api-frameworks/go/gin/book-api/data/request"
	"github.com/guisofiati/book-api-frameworks/go/gin/book-api/helpers"
	"github.com/guisofiati/book-api-frameworks/go/gin/book-api/models"
	"gorm.io/gorm"
)

type BookRepositoryImpl struct {
	Db *gorm.DB
}

func NewBookRepositoryImpl(Db *gorm.DB) BookRepository {
	return &BookRepositoryImpl{Db: Db}
}

func (r *BookRepositoryImpl) FindAll() []models.Book {
	var books []models.Book
	r.Db.Find(&books)
	return books
}

func (r *BookRepositoryImpl) FindById(bookId uuid.UUID) (*models.Book, error) {
	var book models.Book
	if err := r.Db.First(&book, bookId).Error; err != nil {
		if errors.Is(err, gorm.ErrRecordNotFound) {
			return nil, errors.New("book not found")
		}
		return nil, err
	}
	return &book, nil
}

func (r *BookRepositoryImpl) Insert(book models.Book) *models.Book {
	result := r.Db.Create(&book)
	helpers.Error(result.Error)
	return &book
}

func (r *BookRepositoryImpl) Update(bookId uuid.UUID, book models.Book) (*models.Book, error) {
	entity, err := r.FindById(bookId)
	helpers.Error(err)

	var updatedBook = request.UpdateBookDataRequest{
		Title:           book.Title,
		Synopsis:        book.Synopsis,
		Pages:           book.Pages,
		Language:        book.Language,
		Publisher:       book.Publisher,
		PublicationDate: book.PublicationDate,
	}

	result := r.Db.Model(&book).Updates(updatedBook)
	helpers.Error(result.Error)
	return entity, nil
}

func (r *BookRepositoryImpl) Delete(bookId uuid.UUID) error {
	book, err := r.FindById(bookId)
	helpers.Error(err)
	r.Db.Delete(book, bookId)
	return nil
}
