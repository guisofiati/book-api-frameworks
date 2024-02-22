package controllers

import (
	"net/http"

	"github.com/gin-gonic/gin"
	"github.com/google/uuid"
	"github.com/guisofiati/book-api-frameworks/go/gin/book-api/data/request"
	"github.com/guisofiati/book-api-frameworks/go/gin/book-api/helpers"
	"github.com/guisofiati/book-api-frameworks/go/gin/book-api/services"
)

type BookController struct {
	bookService services.BookService
}

func NewBookController(service services.BookService) *BookController {
	return &BookController{bookService: service}
}

func (c *BookController) FindAll(ctx *gin.Context) {
	books := c.bookService.FindAll()

	// webResponse := response.Response{
	// 	Code:   http.StatusOK,
	// 	Status: "Ok",
	// 	Data:   books,
	// }

	ctx.Header("Content-Type", "application/json")
	ctx.JSON(http.StatusOK, books)
}

func (c *BookController) FindById(ctx *gin.Context) {
	bookId, err := uuid.Parse(ctx.Param("bookId"))
	helpers.Error(err)

	book := c.bookService.FindById(bookId)

	// webResponse := response.Response{
	// 	Code:   http.StatusOK,
	// 	Status: "Ok",
	// 	Data:   book,
	// }

	ctx.Header("Content-Type", "application/json")
	ctx.JSON(http.StatusOK, book)
}

func (c *BookController) Insert(ctx *gin.Context) {
	bookRequest := request.InsertBookDataRequest{}
	dataRequestError := ctx.ShouldBindJSON(&bookRequest)
	helpers.Error(dataRequestError)

	book := c.bookService.Insert(bookRequest)

	// webResponse := response.Response{
	// 	Code:   http.StatusCreated,
	// 	Status: "Created",
	// 	Data:   book,
	// }

	ctx.Header("Content-Type", "application/json")
	ctx.JSON(http.StatusCreated, book)
}

func (c *BookController) Update(ctx *gin.Context) {
	bookId, err := uuid.Parse(ctx.Param("bookId"))
	helpers.Error(err)

	bookRequest := request.UpdateBookDataRequest{}
	dataRequestError := ctx.ShouldBindJSON(&bookRequest)
	helpers.Error(dataRequestError)

	book := c.bookService.Update(bookId, bookRequest)

	// webResponse := response.Response{
	// 	Code:   http.StatusOK,
	// 	Status: "Ok",
	// 	Data:   book,
	// }

	ctx.Header("Content-Type", "application/json")
	ctx.JSON(http.StatusOK, book)
}

func (c *BookController) Delete(ctx *gin.Context) {
	bookId, err := uuid.Parse(ctx.Param("bookId"))
	helpers.Error(err)

	c.bookService.Delete(bookId)

	// webResponse := response.Response{
	// 	Code:   http.StatusNoContent,
	// 	Status: "No Content",
	// 	Data:   nil,
	// }

	ctx.Header("Content-Type", "application/json")
	ctx.JSON(http.StatusNoContent, nil)
}
