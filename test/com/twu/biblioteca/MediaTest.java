package com.twu.biblioteca;

import org.junit.Test;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertFalse;

public class MediaTest {

    @Test
    public void testCheckoutMedia() {
        Media media = new Book("book1", "2000", "");
        User user = new User("111-2222", "password12");
        assertTrue("Media is initially not available", media.isAvailable());
        media.checkout(user);
        assertFalse("Checked out media is available", media.isAvailable());
    }

    @Test
    public void testSuccessfulCheckout() {
        Media media = new Book("book1", "2000", "");
        User user = new User("111-2222", "password12");
        assertTrue("Available media cannot be checked out", media.checkout(user));
        assertFalse("Checked out media can be checked out", media.checkout(user));
    }

    @Test
    public void testReturnMedia() {
        Media media = new Book("book1", "2000", "");
        User user = new User("111-2222", "password12");
        media.checkout(user);
        media.giveBack(user);
        assertTrue("Returned media is not available", media.isAvailable());
    }

    @Test
    public void testSuccessfulReturn() {
        Media media = new Book("book1", "2000", "");
        User user = new User("111-2222", "password12");
        assertFalse("Available media can be returned", media.giveBack(user));
        media.checkout(user);
        assertTrue("Checked out media cannot be returned", media.giveBack(user));
    }

    @Test
    public void testCheckedOutMediaIsAssociatedWithUser() {
        Media media = new Book("book1", "2000", "");
        User user12 = new User("111-2222", "password12");
        assertTrue("Available media has holder", media.getHolder() == null);
        media.checkout(user12);
        assertTrue("Checked out media doesn't have correct holder", media.getHolder() == user12);
        User user34 = new User("333-4444", "password34");
        assertFalse("Any user can return media", media.giveBack(user34));
        media.giveBack(user12);
        assertTrue("Available media has holder", media.getHolder() == null);
    }
}
