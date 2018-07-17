package com.twu.biblioteca;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class MediaTest {

    @Test
    public void testCheckoutMedia() {
        Media media = new Book("book1", "", 2000);
        assertEquals(true, media.isAvailable());
        media.checkout();
        assertEquals(false, media.isAvailable());
    }

    @Test
    public void testSuccessfulCheckout() {
        Media media = new Book("book1", "", 2000);
        assertEquals(true, media.checkout());
        assertEquals(false, media.checkout());
    }

    @Test
    public void testReturnMedia() {
        Media media = new Book("book1", "", 2000);
        media.checkout();
        media.giveBack();
        assertEquals(true, media.isAvailable());
    }

    @Test
    public void testSuccessfulReturn() {
        Media media = new Book("book1", "", 2000);
        assertEquals(false, media.giveBack());
        media.checkout();
        assertEquals(true, media.giveBack());
    }
}
