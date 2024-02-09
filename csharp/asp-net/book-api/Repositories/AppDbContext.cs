using book_api.Entities;
using Microsoft.EntityFrameworkCore;

namespace book_api.Repositories;

public class AppDbContext : DbContext
{
    public AppDbContext(DbContextOptions options) : base(options) { }

    public DbSet<Book> TB_BOOKS { get; set; }
}
