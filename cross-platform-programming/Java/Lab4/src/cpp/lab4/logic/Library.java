package cpp.lab4.logic;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.concurrent.ArrayBlockingQueue;

public class Library {
    private LinkedList<String> availableBooks = new LinkedList<>(
            Arrays.asList("Lord Of The Rings", "Harry Potter", "Lolita",
                    "Winnie-the-Pooh", "1984"));

    private HashMap<String, ArrayBlockingQueue<String>> bookQueueMap;

    public Library() {
       bookQueueMap = new HashMap<>(availableBooks.size());
       for(String bookName : availableBooks) {
           ArrayBlockingQueue<String> bookQueue = new ArrayBlockingQueue<>(1);
           bookQueue.add(bookName);
           bookQueueMap.put(bookName, bookQueue);
       }
    }

    public LinkedList<String> getAvailableBooks() {
        return  availableBooks;
    }

    public ArrayBlockingQueue<String> getBookQueue(String book) {
        return bookQueueMap.get(book);
    }
}
