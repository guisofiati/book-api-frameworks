package repositories

import (
	"github.com/google/uuid"
	"github.com/guisofiati/book-api-frameworks/go/gin/book-api/models"
)

type BookRepository interface {
	FindAll() []models.Book
	FindById(id uuid.UUID) (*models.Book, error)
	Insert(entity models.Book) *models.Book
	Update(id uuid.UUID, entity models.Book) (*models.Book, error)
	Delete(id uuid.UUID) error
}
