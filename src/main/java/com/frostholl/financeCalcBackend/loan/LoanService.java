package com.frostholl.financeCalcBackend.loan;

import com.frostholl.financeCalcBackend.user.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LoanService {
    private final LoanRepository loanRepository;

    public LoanService(LoanRepository loanRepository) {
        this.loanRepository = loanRepository;
    }

    public List<Loan> getLoansByUser(User user) {
        var loansList = loanRepository.getLoansByUserId(user.getId());
        return loansList.orElse(null);
    }

    public void addNewLoan(Loan loan) {
        loanRepository.save(loan);
    }

    public void deleteLoan(Loan loan) {
        loanRepository.delete(loan);
    }
}
