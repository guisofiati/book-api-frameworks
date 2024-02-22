package request

import (
	"time"
)

type InsertBookDataRequest struct {
	Title           string    `validate:"required"`
	Synopsis        string    `validate:"required"`
	Pages           int       `validate:"required"`
	Language        string    `validate:"required"`
	Publisher       string    `validate:"required"`
	PublicationDate time.Time `validate:"required"`
}
