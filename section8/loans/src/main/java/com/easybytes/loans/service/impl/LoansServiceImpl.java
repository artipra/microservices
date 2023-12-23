package com.easybytes.loans.service.impl;

import com.easybytes.loans.Constants.LoansConstants;
import com.easybytes.loans.dto.LoansDto;
import com.easybytes.loans.entity.Loans;
import com.easybytes.loans.exception.LoanAlreadyExistsException;
import com.easybytes.loans.exception.ResourceNotFoundException;
import com.easybytes.loans.mapper.LoansMapper;
import com.easybytes.loans.repository.LoansRepository;
import com.easybytes.loans.service.ILoansService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Random;

@Service
@AllArgsConstructor
public class LoansServiceImpl implements ILoansService {

    private LoansRepository loansRepository;
    @Override
    public void createLoan(String mobileNumber) {
      Optional<Loans> loan = loansRepository.findByMobileNumber(mobileNumber);
      if(loan.isPresent()){
          throw new LoanAlreadyExistsException("Loan already registered with the given mobileNumber "+mobileNumber);
      }
      loansRepository.save(createNewLoan(mobileNumber));
    }

    @Override
    public LoansDto fetchLoan(String mobileNumber) {
        Loans loan = loansRepository.findByMobileNumber(mobileNumber).orElseThrow(
                () -> new ResourceNotFoundException("Loan","mobile Number",mobileNumber)
        );
        return LoansMapper.mapToLoansDto(loan,new LoansDto());
    }

    @Override
    public boolean updateLoan(LoansDto loansDto) {
        Loans loan = loansRepository.findByLoanNumber(loansDto.getLoanNumber()).orElseThrow(
                () -> new ResourceNotFoundException("Loan","Loan Number",loansDto.getLoanNumber())
        );
        loan = LoansMapper.mapToLoans(loansDto,loan);
        loansRepository.save(loan);
        return true;
    }

    @Override
    public boolean deleteLoan(String mobileNumber) {
        Loans loan = loansRepository.findByMobileNumber(mobileNumber).orElseThrow(
                () -> new ResourceNotFoundException("Loan","Mobile Number",mobileNumber)
        );
        loansRepository.delete(loan);
        return true;
    }


    private Loans createNewLoan(String mobileNumber) {
        Loans newLoan = new Loans();
        long randomLoanNumber = 100000000000L + new Random().nextInt(900000000);
        newLoan.setLoanNumber(Long.toString(randomLoanNumber));
        newLoan.setMobileNumber(mobileNumber);
        newLoan.setLoanType(LoansConstants.HOME_LOAN);
        newLoan.setTotalLoan(LoansConstants.NEW_LOAN_LIMIT);
        newLoan.setAmountPaid(0);
        newLoan.setOutstandingAmount(LoansConstants.NEW_LOAN_LIMIT);
        return newLoan;
    }



}
