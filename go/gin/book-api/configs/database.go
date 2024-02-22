package configs

import (
	"fmt"

	"github.com/guisofiati/book-api-frameworks/go/gin/book-api/helpers"
	"github.com/guisofiati/book-api-frameworks/go/gin/book-api/models"
	"gorm.io/driver/postgres"
	"gorm.io/gorm"
)

const (
	user     = "postgres"
	password = "123456"
	host     = "localhost"
	port     = 5432
	database = "postgres"
)

func DatabaseConnection() *gorm.DB {
	dsn := fmt.Sprintf("postgres://%s:%s@%s:%d/%s", user, password, host, port, database)
	db, err := gorm.Open(postgres.Open(dsn), &gorm.Config{})
	helpers.Error(err)

	db.AutoMigrate(&models.Book{})

	return db
}
