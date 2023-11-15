package com.frostholl.financeCalcBackend.expensesLimit;

import com.frostholl.financeCalcBackend.category.Category;
import com.frostholl.financeCalcBackend.user.User;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ExpensesLimitService {

    private final ExpensesLimitRepository expensesLimitRepository;

    public ExpensesLimitService(ExpensesLimitRepository expensesLimitRepository) {
        this.expensesLimitRepository = expensesLimitRepository;
    }

    public List<ExpensesLimit> getLimitsByUser(User user) {
        var limits = expensesLimitRepository.getLimitsByUserId(user.getId());
        return limits.orElse(null);
    }

    public ExpensesLimit getUserLimitByCategory(User user, Category category) {
        var limit = expensesLimitRepository.getUserLimitByCategoryId(user.getId(), category.getId());
        return limit.orElse(null);
    }

    public void addOrUpdateLimit(User user, ExpensesLimit limit) {
        var limitDB = getUserLimitByCategory(user, limit.getCategory());
        if (limitDB != null) {
            limitDB.setLimitAmount(limit.getLimitAmount());
            expensesLimitRepository.save(limitDB);
            return;
        }
        expensesLimitRepository.save(limit);
    }
}
