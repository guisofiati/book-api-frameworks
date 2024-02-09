using book_api.Entities;

namespace book_api.Repositories;

public interface IBookRepository
{
    List<Book> FindAll();
    Book? FindById(Guid id);
    Book Insert(Book book);
    Book? Update(Book book);
    void Delete(Guid id);
}
