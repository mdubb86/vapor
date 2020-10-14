package dev.jarcadia.vapor;

import dev.jarcadia.redao.RedaoCommando;
import dev.jarcadia.retask.RetaskManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class PostStartupLogger implements ApplicationListener<ApplicationReadyEvent> {

    private final Logger logger = LoggerFactory.getLogger(PostStartupLogger.class);
    
    private final RedaoCommando rcommando;
    private final RetaskManager retaskManager;

    @Autowired
    public PostStartupLogger(RetaskManager retaskManager,
            RedaoCommando rcommando) {
        this.rcommando = rcommando;
        this.retaskManager = retaskManager;
    }

    /**
     * This event is executed as late as conceivably possible to indicate that 
     * the application is ready to service requests.
     */
    @Override
    public void onApplicationEvent(final ApplicationReadyEvent event) {
    	retaskManager.addWorkerProvider(clazz -> event.getApplicationContext().getBean(clazz));
        retaskManager.start((rcommando, retask) -> {
            logger.info("Starting vapor backkend");
        });
    }
}