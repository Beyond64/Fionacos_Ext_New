package com.colin.job;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.colin.service.DeleteService;

/**
 * Class description
 * @version        18/08/30
 * @author         Colin
 */
@Component
public class DeleteJob {
    @Autowired
    private DeleteService deleteService;

    /**
     * 定时清理库存周转天表(5天前的数据)
     */
    @Scheduled(cron = "0 0 1 * * ?")
    public void execute() {
        deleteService.deleteTurnoverHistoey();
    }
}


//~ Formatted by Jindent --- http://www.jindent.com
