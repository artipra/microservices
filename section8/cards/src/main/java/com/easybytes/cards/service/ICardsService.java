package com.easybytes.cards.service;

import com.easybytes.cards.dto.CardsDto;

public interface ICardsService {


    /**
     * @param mobileNumber - Mobile Number of the Customer
     */

    void createCard(String mobileNumber);

        CardsDto fetchCard(String mobileNumber);

        boolean updateCard(CardsDto customerDto);

        boolean deleteCard(String mobileNumber);

}



