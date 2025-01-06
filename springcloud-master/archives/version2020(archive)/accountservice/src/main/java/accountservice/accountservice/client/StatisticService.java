package accountservice.accountservice.client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;

import accountservice.accountservice.model.StatisticDTO;

@FeignClient(name = "statistic-service", fallback = StatisticServiceImpl.class)
public interface StatisticService {

    @PostMapping(value = "/statistic")
    StatisticDTO add(StatisticDTO statisticDTO);
}

@Component
class StatisticServiceImpl implements StatisticService {
    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public StatisticDTO add(StatisticDTO statisticDTO) {
	logger.error("Fallback statistic add");
	return statisticDTO;
    }

}