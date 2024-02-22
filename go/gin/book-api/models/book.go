package models

import (
	"time"

	"github.com/google/uuid"
	"gorm.io/gorm"
)

type Book struct {
	gorm.Model
	Id              uuid.UUID `gorm:"type:uuid;default:gen_random_uuid();primaryKey"`
	Title           string
	Synopsis        string
	Pages           int
	Language        string
	Publisher       string
	PublicationDate time.Time
}
