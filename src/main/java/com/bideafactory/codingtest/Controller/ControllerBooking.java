package com.bideafactory.codingtest.Controller;



import com.bideafactory.codingtest.Dto.Response;
import com.bideafactory.codingtest.Entity.Booking;
import com.bideafactory.codingtest.Repository.BookRepository;
import com.bideafactory.codingtest.Service.ApiDiscountService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/bookings")
@Api(tags = "Booking", description = "Metodos")
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT})
public class ControllerBooking {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private ApiDiscountService discountService;

    @ApiOperation("Reserva de una casa")
    @PostMapping("/Create")
    public ResponseEntity<?> createBooking(@Valid @RequestBody Booking booking) throws Exception {
try {
    if (booking.getDiscountCode() != null )
    {
        if( !discountService.validateDiscount(booking.getHouseId(),booking.getId(),booking.getDiscountCode())) {
            return new ResponseEntity(new Response(400, "Conflicto", "Codigo Invalido'"), HttpStatus.CONFLICT);
        }

    }

    bookRepository.save(booking);
    return new ResponseEntity(new Response(200, null, "Book Accepted"), HttpStatus.OK);
}catch (Exception e){

    return new ResponseEntity(new Response(400, "Conflicto", "Excepcion"+e), HttpStatus.BAD_GATEWAY);

}

    }
}
