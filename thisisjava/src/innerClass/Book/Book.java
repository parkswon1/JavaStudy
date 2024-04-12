package thisisjava.src.innerClass.Book;

public class Book {
    private String title;
    private Author author;

    public Book(String title){
        this.title = title;
        this.author = new Author();
    }

    public void setAuthorName(String authorName){
        author.setName(authorName);
    }

    public String getAuthorName(){
        return author.getName();
    }
    public void printBookDetails(){
        System.out.println("Book Title: " + title);
        System.out.println("Author " + getAuthorName());
    }

    public class Author{
        private String name;

        public void setName(String name){
            this.name = name;
        }

        public String getName(){
            return this.name;
        }
    }
}
