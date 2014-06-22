package org.realrest.domain.service.impl;

import org.realrest.domain.Booking;
import org.realrest.domain.repository.BookingRepository;
import org.realrest.domain.service.BookingService;
import org.realrest.infrastructure.rest.jaxrs.transitions.CreateBookingTransition;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

/**
 * @author volodymyr.tsukur
 */
@ApplicationScoped
public class DefaultBookingService implements BookingService {

    @Inject
    private BookingRepository bookingRepository;

    @Override
    public Booking create(final CreateBookingTransition data) {
        final Booking booking = new Booking();
        booking.setFrom(data.getFrom());
        booking.setTo(data.getTo());
        booking.setIncludeBreakfast(data.isIncludeBreakfast());
        return bookingRepository.create(booking);
    }

    @Override
    public Booking findById(final Long id) {
        return bookingRepository.findById(id);
    }

}
