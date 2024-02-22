package response

import (
	"time"

	"github.com/google/uuid"
)

type BookDataResponse struct {
	Id              uuid.UUID `json:"id"`
	Title           string    `json:"title"`
	Synopsis        string    `json:"synopsis"`
	Pages           int       `json:"pages"`
	Language        string    `json:"language"`
	Publisher       string    `json:"publisher"`
	PublicationDate time.Time `json:"publicationDate"`
}
