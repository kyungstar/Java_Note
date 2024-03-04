package com.group.libraryapp.service.book;

import com.group.libraryapp.repository.book.BookMemoryRepository;

public class BookService {

    private final BookMemoryRepository bookMemoryRepository
    = new BookMemoryRepository();

    public void saveBook() {
        bookMemoryRepository.saveBook();
    }
}
