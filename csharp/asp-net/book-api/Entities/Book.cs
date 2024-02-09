namespace book_api.Entities;

public class Book
{
    public Guid Id {  get; set; }
    public string Title { get; set; } = string.Empty;
    public string Synopsis { get; set; } = string.Empty;
    public string Language { get; set; } = string.Empty;
    public string Publisher { get; set; } = string.Empty;
    public DateTime PublicationDate { get; set; }
}
