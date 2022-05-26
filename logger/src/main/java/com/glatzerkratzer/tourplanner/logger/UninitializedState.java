package com.glatzerkratzer.tourplanner.logger;

public class UninitializedState extends LoggerStateBase {

    @Override
    public boolean isDebugEnabled(){
        return false;
    }

    @Override
    public boolean isInfoEnabled(){
        return false;
    }

    @Override
    public boolean isWarnEnabled(){
        return false;
    }

    @Override
    public boolean isFatalEnabled(){
        return false;
    }

    @Override
    public void debug(String message) {
        this.printUninitializedWarning();
        return;
    }

    @Override
    public void fatal(String message) {
        this.printUninitializedWarning();
        return;
    }

    @Override
    public void error(String message) {
        this.printUninitializedWarning();
        return;
    }

    @Override
    public void warn(String message) {
        this.printUninitializedWarning();
        return;
    }

    @Override
    public void info(String message) {
        this.printUninitializedWarning();
        return;
    }

    private void printUninitializedWarning() {
        System.out.println("Operation was called in state uninitialized.");
    }
}
