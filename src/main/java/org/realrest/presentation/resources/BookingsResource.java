package org.realrest.presentation.resources;

import org.realrest.application.service.BookingService;
import org.realrest.domain.Booking;
import org.realrest.presentation.representations.BookingRepresentationBuilder;
import org.realrest.presentation.transitions.CreateBookingTransition;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.net.URI;

/**
 * @author volodymyr.tsukur
 */
@Path("/bookings")
public class BookingsResource {

    @Inject
    private BookingService bookingService;

    @Inject
    private BookingResource bookingResource;

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response create(final CreateBookingTransition data, @Context final UriInfo uriInfo) {
        final Booking result = bookingService.create(data);
        final URI bookingURI = BookingRepresentationBuilder.selfURI(result, uriInfo);
        return Response.created(bookingURI).build();
    }

    @Path("/item")
    public BookingResource item() {
        return bookingResource;
    }

}
