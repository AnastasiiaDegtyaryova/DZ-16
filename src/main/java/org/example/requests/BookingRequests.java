package org.example.requests;

import io.restassured.response.Response;
import org.example.config.TestBase;
import org.example.models.Booking;
import org.example.utils.TokenManager;

import static io.restassured.RestAssured.given;

public class BookingRequests extends TestBase {

    public Response getAllBookings() {
        return requestSpec.get("/booking");
    }

    public Response getBookingById(int id) {
        return requestSpec.get("/booking/" + id);
    }

    public Response createBooking(Booking booking) {
        return requestSpec.body(booking)
                .post("/booking");
    }

    public Response updateBooking(int id, Booking booking) {
        return requestSpec
                .cookie("token", TokenManager.getToken())
                .body(booking)
                .put("/booking/" + id);
    }

    public Response partialUpdateBooking(int id, Booking booking) {
        return requestSpec
                .cookie("token", TokenManager.getToken())
                .body(booking)
                .patch("/booking/" + id);
    }

    public Response deleteBooking(int id) {
        return requestSpec
                .cookie("token", TokenManager.getToken())
                .delete("/booking/" + id);
    }
}

