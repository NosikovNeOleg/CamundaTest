package org.example.service.sendMoneyImpls;


import org.example.service.SendMoneyService;
import org.example.service.SimpleLogService;
import org.springframework.stereotype.Component;

@Component
public class SendMoneyRusServiceImpl implements SendMoneyService {

    @Override
    public void sendMoney(String bk, int passport, int amount) {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        SimpleLogService.logInfo(bk, String.format("Клиенту %s отправлена сумма %s в рублях",passport,amount));
    }
}
