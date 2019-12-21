package com.arun.libraryApplication.Controller;

import com.arun.libraryApplication.Entity.Book;
import com.arun.libraryApplication.Entity.Student;
import com.arun.libraryApplication.Repository.BookRepository;
import com.arun.libraryApplication.Repository.StudentRepository;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class Controller {
    @Autowired
    BookRepository bookRepository;

    @Autowired
    StudentRepository studentRepository;

    @CrossOrigin("http://localhost:4200")
    @RequestMapping(value = "/books", method = RequestMethod.GET)
    public List<Book> getBooks(){
       return bookRepository.findAll();
    }

    @RequestMapping(value = "/book/add", method = RequestMethod.POST)
    public Book addBook(@RequestBody Book book){
        return bookRepository.save(book);
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    public List<Book> deleteBooks(@PathVariable int id){
        bookRepository.deleteById(id);
        return bookRepository.findAll();
    }

    @RequestMapping(value = "book/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Book>  updateBook(@RequestBody Book book, @PathVariable int id){
        return ResponseEntity.ok(bookRepository.save(book));
    }

    @RequestMapping(value = "/student/add", method = RequestMethod.POST)
    public Student addStudent(@RequestBody Student stud){
        return studentRepository.save(stud);
    }

    @RequestMapping(value = "/students", method = RequestMethod.GET)
    public List<Student> getStudentList(){
        return studentRepository.findAll();
    }

    @RequestMapping(value = "/student/{id}", method = RequestMethod.DELETE)
    public List<Student> deleteStudent(@PathVariable String rollNo){
         studentRepository.deleteById(rollNo);
        return studentRepository.findAll();
    }

    @RequestMapping(value = "/student/{id}", method = RequestMethod.GET)
    public Student getStudent(@PathVariable String rollNo){
        return studentRepository.getOne(rollNo);
    }
}
