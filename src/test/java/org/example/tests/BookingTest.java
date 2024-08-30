package org.example.tests;

import io.restassured.response.Response;
import org.example.models.Booking;
import org.example.models.BookingDates;
import org.example.requests.BookingRequests;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.LocalDate;
import java.util.List;

public class BookingTest extends BookingRequests {

    private static int bookingId;

    @Test(priority = 1)
    public void testCreateBooking() {
        BookingDates bookingDates = new BookingDates(LocalDate.parse("2018-01-01"), LocalDate.parse("2019-01-01"));
        Booking booking = new Booking("Jim", "Brown", 111, true, bookingDates, "Breakfast");

        Response response = createBooking(booking);
        Assert.assertEquals(response.statusCode(), 200);

        bookingId = response.jsonPath().getInt("bookingid");
        Assert.assertNotNull(bookingId, "Booking ID should not be null");

    }

    @Test(priority = 2,dependsOnMethods = {"testCreateBooking"})
    public void testGetAllBookings() {
        Response response = getAllBookings();
        Assert.assertEquals(response.statusCode(), 200);
    }

    @Test(priority = 3,dependsOnMethods = {"testCreateBooking"})
    public void testGetBookingById() {
        Response response = getBookingById(bookingId);
        Assert.assertEquals(response.statusCode(), 200);
    }

    @Test(priority = 4,dependsOnMethods = {"testCreateBooking"})
    public void testUpdateBooking() {
        BookingDates bookingDates = new BookingDates(LocalDate.parse("2018-01-01"), LocalDate.parse("2019-01-01"));
        Booking booking = new Booking("James", "Brown", 111, true, bookingDates, "Breakfast");

        Response response = updateBooking(bookingId, booking);
        Assert.assertEquals(response.statusCode(), 200);
    }

    @Test(priority = 5,dependsOnMethods = {"testCreateBooking"})
    public void testPartialUpdateBooking() {
        Booking booking = new Booking("James", "Brown");

        Response response = partialUpdateBooking(bookingId, booking);
        Assert.assertEquals(response.statusCode(), 200);
    }

    @Test(priority = 6,dependsOnMethods = {"testCreateBooking"})
    public void testDeleteBooking() {
        Response response = deleteBooking(bookingId);
        Assert.assertEquals(response.statusCode(), 201);
    }
}

