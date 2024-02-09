using book_api.Entities;
using Microsoft.EntityFrameworkCore;

namespace book_api.Repositories.Impl;

public class BookRepository : IBookRepository
{
    private readonly AppDbContext _dbContext;

    public BookRepository(AppDbContext dbContext) => _dbContext = dbContext;

    public List<Book> FindAll()
    {
        return _dbContext.TB_BOOKS.ToList();
    }

    public Book? FindById(Guid id)
    {
        return _dbContext.TB_BOOKS.Find(id);
    }

    public Book Insert(Book book)
    {
        _dbContext.TB_BOOKS.Add(book);
        _dbContext.SaveChanges();
        return book;
    }

    public Book? Update(Book book)
    {
        var entity = _dbContext.TB_BOOKS.Find(book.Id);
        if (entity == null)
        {
            throw new ArgumentException("Book id: " + book.Id + " not found.");
        }
        entity = book;
        _dbContext.SaveChanges();
        return entity;
    }

    public void Delete(Guid id)
    {
        var entity = _dbContext.TB_BOOKS.Find(id);
        if (entity != null)
        {
            _dbContext.TB_BOOKS.Remove(entity);
            _dbContext.SaveChanges();
        }
        else
        {
            throw new ArgumentException("Book id: " + id + " not found.");
        }

    }
}
