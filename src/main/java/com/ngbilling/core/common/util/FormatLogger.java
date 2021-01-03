package com.ngbilling.core.common.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * To defer invocation of toString, StringBuilder and message concatenation, a
 * fairly costly step from memory perspective.
 * 
 * @author Vikas Bodani
 * 
 */
public final class FormatLogger {

    private final Logger log;

    public FormatLogger(Logger logger) {
        this.log= logger;
    }

    public FormatLogger(Class<?> clazz) {
    	this.log= LoggerFactory.getLogger(clazz);
    }


    /* debug method overloaded */
    public void debug(String s) {
        log.debug(s);
    }

    public void debug(Throwable e) {
        debug("Error: ", e);
    }

    public void debug(String s, Throwable e) {
        if (log.isDebugEnabled()) {
            log.debug(s, e);
        }
    }

    public void debug(Object o) {
    	if (log.isDebugEnabled()) {
            log.debug(o.toString());
        }
    }

    /* error method overloaded */
    public void error(Throwable e) {
        error("Error: ", e);
    }
    
    public void error(String s) {
    	if (log.isErrorEnabled()) {
            log.error(s);
        }
    }

    public void error(String s, Throwable e) {
    	if (log.isErrorEnabled()) {
            log.error(s, e);
        }
    }
    
    public void warn(String s, Throwable e) {
        if (log.isWarnEnabled()) {
            log.error(s, e);
        }
    }
    
    public void fatal(String s, Throwable e) {
        if (log.isErrorEnabled()) {
            log.error(s, e);
        }
    }

      /* formatter methods */
    
    public void debug(String formatter, Object... args) {
        log.debug(formatter, args);
    }

    public void info(String formatter, Object... args) {
    	log.info(formatter, args);
    }

    public void warn(String formatter, Object... args) {
    	log.warn(formatter, args);
    }

    public void error(String formatter, Object... args) {
    	log.error(formatter, args);
    }
    
    public void fatal(String formatter, Object... args) {
    	log.error(formatter, args);
    }

}
