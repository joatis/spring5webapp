package guru.springframework.spring5webapp.bootstrap;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import guru.springframework.spring5webapp.domain.Author;
import guru.springframework.spring5webapp.domain.Book;
import guru.springframework.spring5webapp.domain.Publisher;
import guru.springframework.spring5webapp.repositories.AuthorRepository;
import guru.springframework.spring5webapp.repositories.BookRepository;
import guru.springframework.spring5webapp.repositories.PublisherRepository;

@Component
public class BootStrapData implements CommandLineRunner {

    private final AuthorRepository authorrepository;
    private final BookRepository bookrepository;
    private final PublisherRepository publisherrepository;

    public BootStrapData(AuthorRepository authorrepository, BookRepository bookrepository,
            PublisherRepository publisherrepository) {
        this.authorrepository = authorrepository;
        this.bookrepository = bookrepository;
        this.publisherrepository = publisherrepository;
    }

    @Override
    public void run(String... args) throws Exception {
        Publisher publisher = new Publisher("RandoHouse");
        publisher.setAddr1("1 Way St.");
        publisher.setCity("Boston");
        publisher.setState("MA");
        publisher.setZip("02114");

        publisherrepository.save(publisher);

        Author eric = new Author("Eric", "Evans");
        Book ddd = new Book("Domain Driven Design", "1221234");
        eric.getBooks().add(ddd);
        ddd.getAuthors().add(eric);
        publisher.getBooks().add(ddd);

        authorrepository.save(eric);
        bookrepository.save(ddd);
        publisherrepository.save(publisher);

        Author rod = new Author("Rod", "Johnson");
        Book noELB = new Book("J2EE Development Without EJB", "64261582648");
        rod.getBooks().add(noELB);
        noELB.getAuthors().add(rod);
        noELB.setPublisher(publisher);
        publisher.getBooks().add(noELB);

        authorrepository.save(rod);
        bookrepository.save(noELB);
        publisherrepository.save(publisher);

        System.out.println("Started in Bootstrap");
        System.out.println("Number of Books: " + bookrepository.count());
        System.out.println("Number of Publishers:" + publisherrepository.count());
        System.out.println("Publisher number of books: " + publisher.getBooks().size());
    }
}
