using book_api.Dtos;
using book_api.Entities;
using book_api.Services;
using Microsoft.AspNetCore.Mvc;

namespace book_api.Controllers;
[Route("books")]
[ApiController]
public class BookController : ControllerBase
{
    private readonly BookService _bookService;

    public BookController(BookService bookService) => _bookService = bookService;

    [HttpGet]
    [ProducesResponseType(typeof(List<Book>), StatusCodes.Status200OK)]
    public IActionResult FindAll()
    {
        return Ok(_bookService.FindAll());
    }

    [HttpGet]
    [Route("{bookId}")]
    [ProducesResponseType(typeof(Book), StatusCodes.Status200OK)]
    [ProducesResponseType(StatusCodes.Status404NotFound)]
    public IActionResult FindById([FromRoute] Guid bookId)
    {
        var book = _bookService.FindById(bookId);
        if (book == null)
        {
            return NotFound();
        }
        return Ok(book);
    }

    [HttpPost]
    [ProducesResponseType(typeof(Book), StatusCodes.Status201Created)]
    public IActionResult Insert([FromBody] BookDto bookDto)
    {
        return Created(string.Empty, _bookService.Insert(bookDto));
    }

    [HttpPut]
    [Route("{bookId}")]
    [ProducesResponseType(typeof(Book), StatusCodes.Status200OK)]
    [ProducesResponseType(StatusCodes.Status404NotFound)]
    public IActionResult Update([FromRoute] Guid bookId, [FromBody] BookDto bookDto) 
    {
        var book = _bookService.FindById(bookId);
        if (book == null)
        {
            return NotFound();
        }
        return Ok(_bookService.Update(bookId, bookDto));
    }

    [HttpDelete]
    [Route("{bookId}")]
    [ProducesResponseType(StatusCodes.Status204NoContent)]
    [ProducesResponseType(StatusCodes.Status404NotFound)]
    public IActionResult Delete([FromRoute] Guid bookId) 
    {
        var book = _bookService.FindById(bookId);
        if (book == null)
        {
            return NotFound();
        }
        _bookService.Delete(bookId);
        return NoContent();
    }
}
