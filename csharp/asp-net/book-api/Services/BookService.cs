using book_api.Dtos;
using book_api.Entities;
using book_api.Repositories;
using Microsoft.IdentityModel.Tokens;

namespace book_api.Services;

public class BookService
{
    private readonly IBookRepository _repository;

    public BookService(IBookRepository repository) => _repository = repository;

    public List<Book> FindAll()
    {
        return _repository.FindAll();
    }

    public Book? FindById(Guid id)
    {
        var entity = _repository.FindById(id);
        return entity;
    }

    public Book Insert(BookDto bookDto)
    {
        var newBook = new Book
        {
            Title = bookDto.Title,
            Synopsis = bookDto.Synopsis,
            Language = bookDto.Language,
            Publisher = bookDto.Publisher,
            PublicationDate = bookDto.PublicationDate,
        };
        return _repository.Insert(newBook);
    }

    public Book? Update(Guid id, BookDto bookDto) 
    {
        var entity = _repository.FindById(id);
        if (entity == null) 
        {
            throw new ArgumentException("Book id: " + id + " not found");
        } 
        if (!bookDto.Title.IsNullOrEmpty()) entity.Title = bookDto.Title;
        if (!bookDto.Synopsis.IsNullOrEmpty()) entity.Synopsis = bookDto.Synopsis;
        if (!bookDto.Language.IsNullOrEmpty()) entity.Language = bookDto.Language;
        if (!bookDto.Publisher.IsNullOrEmpty()) entity.Publisher = bookDto.Publisher;
        if (!bookDto.PublicationDate.Equals(null)) entity.PublicationDate = bookDto.PublicationDate;
        return _repository.Update(entity);
    }

    public void Delete(Guid id)
    {
        var entity = _repository.FindById(id) ?? throw new ArgumentException("Book id: " + id + " not found");
        _repository.Delete(id);
    }
}
