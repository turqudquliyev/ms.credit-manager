package az.ingress.scheduler;

import az.ingress.service.abstraction.CreditService;
import lombok.RequiredArgsConstructor;
import net.javacrumbs.shedlock.core.SchedulerLock;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CreditStatusScheduler {
    private final CreditService creditService;

    @Scheduled(cron = "0 * * * * *")
    @SchedulerLock(name = "updateExpiredCreditStatus", lockAtLeastForString = "PT1M", lockAtMostForString = "PT3M")
    public void updateExpiredCreditStatus() {
        creditService.updateExpiredCreditStatus();
    }
}