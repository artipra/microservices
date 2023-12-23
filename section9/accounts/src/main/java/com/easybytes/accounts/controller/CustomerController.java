package com.easybytes.accounts.controller;

import com.easybytes.accounts.dto.CustomerDetailsDto;
import com.easybytes.accounts.service.ICustomerService;
import jakarta.validation.constraints.Pattern;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(path="/api",produces = MediaType.APPLICATION_JSON_VALUE)
@Validated
public class CustomerController {
    private static final Logger logger = LoggerFactory.getLogger(CustomerController.class);
    private ICustomerService iCustomerService;

    public CustomerController(ICustomerService iCustomerService){
      this.iCustomerService = iCustomerService;
    }

    @GetMapping("/fetchCustomerDetails")
    public ResponseEntity<CustomerDetailsDto> fetchCustomerDetails(@RequestHeader("eazybank-correlation-id")
                                                                       String correlationId,
                                                                   @RequestParam@Pattern(regexp="(^$|[0-9]{10})",message = "MobileNumber must be 10 digits")
                                                                   String mobileNumber){
        logger.debug("eazyBank-correlation-id found: {} ", correlationId);
        CustomerDetailsDto customerDetailsDto = iCustomerService.fetchCustomerDetails(mobileNumber,correlationId);
        return ResponseEntity.status(HttpStatus.OK).body(customerDetailsDto);
    }
}
