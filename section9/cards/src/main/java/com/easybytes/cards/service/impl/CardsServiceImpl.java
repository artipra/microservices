package com.easybytes.cards.service.impl;

import com.easybytes.cards.constants.CardsConstants;
import com.easybytes.cards.dto.CardsDto;
import com.easybytes.cards.entity.Cards;
import com.easybytes.cards.exception.CardAlreadyExistsException;
import com.easybytes.cards.exception.ResourceNotFoundException;
import com.easybytes.cards.mapper.CardMapper;
import com.easybytes.cards.repository.CardsRepository;
import com.easybytes.cards.service.ICardsService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Random;

@Service
@AllArgsConstructor
public class CardsServiceImpl implements ICardsService {

    private CardsRepository cardsRepository;

    /**
     * @param mobileNumber - Mobile Number of the Customer
     */
    @Override
    public void createCard(String mobileNumber) {
        Optional<Cards> optionalCards = cardsRepository.findByMobileNumber(mobileNumber);
        if (optionalCards.isPresent()) {
            throw new CardAlreadyExistsException("Card already registered with given mobileNumber " + mobileNumber);
        }
        cardsRepository.save(createNewCard(mobileNumber));
    }

    @Override
    public CardsDto fetchCard(String mobileNumber) {
         Cards card = cardsRepository.findByMobileNumber(mobileNumber)
                .orElseThrow(
                 () -> new ResourceNotFoundException("Card","mobile number",mobileNumber)
                      );
        return CardMapper.mapToCardDto(card,new CardsDto());
    }

    @Override
    public boolean updateCard(CardsDto cardsDto) {
        Cards optionalCards =
                cardsRepository.findByCardNumber(cardsDto.getCardNumber())
               .orElseThrow(() ->  new ResourceNotFoundException("Card","card number",cardsDto.getCardNumber()));

        optionalCards = CardMapper.mapToCard(cardsDto,optionalCards);
        cardsRepository.save(optionalCards);
        return true;
    }

    @Override
    public boolean deleteCard(String mobileNumber) {
        Cards card = cardsRepository.findByMobileNumber(mobileNumber)
                .orElseThrow(
                        () -> new ResourceNotFoundException("Card","mobile number",mobileNumber)
                );
        cardsRepository.delete(card);
        return true;
    }

    /**
     * @param mobileNumber - Mobile Number of the Customer
     * @return the new card details
     */
    private Cards createNewCard(String mobileNumber) {
        Cards newCard = new Cards();
        long randomCardNumber = 100000000000L + new Random().nextInt(900000000);
        newCard.setCardNumber(Long.toString(randomCardNumber));
        newCard.setMobileNumber(mobileNumber);
        newCard.setCardType(CardsConstants.CREDIT_CARD);
        newCard.setTotalLimit(CardsConstants.NEW_CARD_LIMIT);
        newCard.setAmountUsed(0);
        newCard.setAvailableAmount(CardsConstants.NEW_CARD_LIMIT);
        return newCard;
    }



}