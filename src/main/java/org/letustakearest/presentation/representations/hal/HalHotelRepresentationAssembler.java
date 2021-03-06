package org.letustakearest.presentation.representations.hal;

import com.theoryinpractise.halbuilder.api.Representation;
import org.letustakearest.domain.Hotel;
import org.letustakearest.domain.Place;
import org.letustakearest.presentation.representations.HotelRepresentationAssembler;
import org.letustakearest.presentation.resources.HotelResource;

import javax.ws.rs.core.UriInfo;
import java.net.URI;

/**
 * @author volodymyr.tsukur
 */
public class HalHotelRepresentationAssembler extends BaseHalRepresentationAssembler
        implements HotelRepresentationAssembler {

    public HalHotelRepresentationAssembler(final UriInfo uriInfo) {
        super(uriInfo);
    }

    @Override
    public Object from(final Hotel hotel) {
        return withRooms(
                newRepresentation(selfURI(hotel)).
                        withProperty("city", hotel.getCity().getName()).
                        withProperty("name", hotel.getName()),
                hotel);
    }

    private Representation withRooms(final Representation representation, final Hotel hotel) {
        return hotel.getPlaces().stream().
                map(this::newEmbeddedRoomRepresentation).
                reduce(
                        representation,
                        (accumulator, i) -> accumulator.withRepresentation(rel("room"), i));
    }

    private Representation newEmbeddedRoomRepresentation(final Place place) {
        return newRepresentation().
                withProperty("id", place.getId()).
                withProperty("type", place.getCategory()).
                withProperty("price", place.getPrice()).
                withLink(rel("book"), HotelResource.bookingURI(place, uriInfo));
    }

    private URI selfURI(final Hotel hotel) {
        return HotelResource.selfURI(hotel, uriInfo);
    }

}
