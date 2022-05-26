package com.glatzerkratzer.tourplanner.logger;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


// Wraps the log4j2 logger instances by realizing interface ILoggerWrapper
// This avoids direct dependencies to log4j2 package
public class Log4J2Wrapper implements ILoggerWrapper {

    private Logger logger;
    private LoggerStateBase state = new UninitializedState();

    public void initialize() {
        this.state = new InitialzedState(logger = LogManager.getLogger(this.getClass().getName()));
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
        this.state.debug(message);
    }
    @Override
    public void fatal(String message) {
        this.state.fatal(message);
    }
    @Override
    public void error(String message) {
        this.state.error(message);
    }
    @Override
    public void warn(String message) {
        this.state.warn(message);
    }
    @Override
    public void info(String message) {
        this.state.info(message);
    }


}
