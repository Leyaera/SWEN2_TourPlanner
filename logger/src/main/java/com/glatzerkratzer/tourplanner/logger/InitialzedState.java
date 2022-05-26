package com.glatzerkratzer.tourplanner.logger;

import lombok.Getter;
import org.apache.logging.log4j.Logger;

//import java.util.logging.Logger;

public class InitialzedState extends LoggerStateBase {

    private final Logger logger;

    public InitialzedState(Logger logger) {
        this.logger = logger;
    }


    @Override
    public boolean isDebugEnabled(){
        return this.logger.isDebugEnabled();
    }

    @Override
    public boolean isInfoEnabled(){
        return this.logger.isInfoEnabled();
    }

    @Override
    public boolean isWarnEnabled(){
        return this.logger.isWarnEnabled();
    }

    @Override
    public boolean isFatalEnabled(){
        return this.logger.isFatalEnabled();
    }

    @Override
    public void debug(String message) {
        this.logger.debug(message);
    }

    @Override
    public void fatal(String message) {
        this.logger.fatal(message);
    }

    @Override
    public void error(String message) {
        this.logger.error(message);
    }

    @Override
    public void warn(String message) {
        this.logger.warn(message);
    }

    @Override
    public void info(String message) {
        this.logger.info(message);
    }
}
