package main

import (
	"net/http"

	"github.com/gin-gonic/gin"
	"github.com/go-playground/validator/v10"
	"github.com/guisofiati/book-api-frameworks/go/gin/book-api/configs"
	"github.com/guisofiati/book-api-frameworks/go/gin/book-api/controllers"
	"github.com/guisofiati/book-api-frameworks/go/gin/book-api/helpers"
	"github.com/guisofiati/book-api-frameworks/go/gin/book-api/repositories"
	"github.com/guisofiati/book-api-frameworks/go/gin/book-api/routes"
	"github.com/guisofiati/book-api-frameworks/go/gin/book-api/services"
)

func main() {
	db := configs.DatabaseConnection()
	validator := validator.New()

	bookRepository := repositories.NewBookRepositoryImpl(db)
	bookService := services.NewBookServiceImpl(bookRepository, validator)
	bookController := controllers.NewBookController(bookService)
	bookRoutes := routes.NewBookRoutes(bookController)

	bookRoutes.NoRoute(func(c *gin.Context) {
		c.JSON(404, gin.H{"code": "ROUTE_NOT_FOUND", "message": "Route not found"})
	})

	server := &http.Server{
		Addr:    ":8080",
		Handler: bookRoutes,
	}

	err := server.ListenAndServe()
	helpers.Error(err)
}
