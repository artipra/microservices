package com.easybytes.cards.mapper;

import com.easybytes.cards.constants.CardsConstants;
import com.easybytes.cards.dto.CardsDto;
import com.easybytes.cards.entity.Cards;

public class CardMapper {


    public static CardsDto mapToCardDto(Cards card,CardsDto dto){
        dto.setCardNumber(card.getCardNumber());
        dto.setMobileNumber(card.getMobileNumber());
        dto.setCardType(card.getCardType());
        dto.setTotalLimit(card.getTotalLimit());
        dto.setAmountUsed(card.getAmountUsed());
        dto.setAvailableAmount(card.getAvailableAmount());
       return dto;
    }

    public static Cards mapToCard(CardsDto dto,Cards card){
        card.setCardNumber(dto.getCardNumber());
        card.setMobileNumber(dto.getMobileNumber());
        card.setCardType(dto.getCardType());
        card.setTotalLimit(dto.getTotalLimit());
        card.setAmountUsed(dto.getAmountUsed());
        card.setAvailableAmount(dto.getAvailableAmount());

        return card;
    }
}
