package accountservice.accountservice.client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import accountservice.accountservice.model.MessageDTO;

@FeignClient(name = "notification-service", url = "http://localhost:9083", fallback = NotificationServiceImpl.class)
public interface NotificationService {

    @PostMapping("/send-notification")
    public void sendNotification(@RequestBody MessageDTO messageDTO);
}

@Component
class NotificationServiceImpl implements NotificationService {
    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public void sendNotification(MessageDTO messageDTO) {
        // fallback
	logger.error("Notification Service is slow");
    }
}