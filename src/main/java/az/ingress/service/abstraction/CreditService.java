package az.ingress.service.abstraction;

import az.ingress.model.request.InitializeCreditRequest;
import az.ingress.model.response.CreditResponse;

public interface CreditService {

    void initializeCredit(InitializeCreditRequest creditRequest);

    CreditResponse getCreditById(Long id);

    void updateExpiredCreditStatus();

    void acceptCredit(Long id, Long offerId);

    void rejectCredit(Long id);
}