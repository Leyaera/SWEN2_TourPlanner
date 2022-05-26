package com.glatzerkratzer.tourplanner.logger;

public interface ILoggerWrapper {

    public boolean isDebugEnabled();
    public boolean isInfoEnabled();
    public boolean isWarnEnabled();
    public boolean isFatalEnabled();

    void debug(String message);
    void fatal(String message);
    void error(String message);
    void warn(String message);
    void info(String message);
}
