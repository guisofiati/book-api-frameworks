package routes

import (
	"github.com/gin-gonic/gin"
	"github.com/guisofiati/book-api-frameworks/go/gin/book-api/controllers"
)

func NewBookRoutes(bookController *controllers.BookController) *gin.Engine {
	router := gin.Default()
	bookRouter := router.Group("/books")

	bookRouter.GET("/", bookController.FindAll)
	bookRouter.GET("/:bookId", bookController.FindById)
	bookRouter.POST("/", bookController.Insert)
	bookRouter.PUT("/:bookId", bookController.Update)
	bookRouter.DELETE("/:bookId", bookController.Delete)

	return router
}
