package com.easybytes.accounts.mapper;

import com.easybytes.accounts.dto.AccountsDto;
import com.easybytes.accounts.dto.CustomerDetailsDto;
import com.easybytes.accounts.dto.CustomerDto;
import com.easybytes.accounts.entity.Customer;

public class CustomerMapper {

    public static CustomerDto mapToCustomerDto(Customer customer, CustomerDto customerDto){
        customerDto.setEmail(customer.getEmail());
        customerDto.setName(customer.getName());
        customerDto.setMobileNumber(customer.getMobileNumber());
        return customerDto;
    }

    public static Customer mapToCustomer(CustomerDto customerDto,Customer customer){
        customer.setMobileNumber(customerDto.getMobileNumber());
        customer.setName(customerDto.getName());
        customer.setEmail(customerDto.getEmail());
        return customer;
    }

    public static CustomerDetailsDto mapToCustomerDetailsDto(Customer customer, CustomerDetailsDto customerDetailDto){
        customerDetailDto.setEmail(customer.getEmail());
        customerDetailDto.setName(customer.getName());
        customerDetailDto.setMobileNumber(customer.getMobileNumber());
        return customerDetailDto;
    }



}
