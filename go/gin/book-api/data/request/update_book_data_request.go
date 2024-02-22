package request

import (
	"time"
)

type UpdateBookDataRequest struct {
	Title           string    `validate:"omitempty"`
	Synopsis        string    `validate:"omitempty"`
	Pages           int       `validate:"omitempty"`
	Language        string    `validate:"omitempty"`
	Publisher       string    `validate:"omitempty"`
	PublicationDate time.Time `validate:"omitempty"`
}
