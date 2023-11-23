package com.frostholl.financeCalcBackend.deposit;

import com.frostholl.financeCalcBackend.user.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepositService {

    private final DepositRepository depositRepository;

    public DepositService(DepositRepository depositRepository) {
        this.depositRepository = depositRepository;
    }

    public List<Deposit> getDepositsByUser(User user) {
        var deposits = depositRepository.getDepositsByUserId(user.getId());
        return deposits.orElse(null);
    }

    public void addNewDeposit(Deposit deposit) {
        depositRepository.save(deposit);
    }

    public void deleteDeposit(Deposit deposit) {
        depositRepository.delete(deposit);
    }

}
