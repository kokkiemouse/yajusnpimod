package net.kokkiemouse.yaju.util;

import org.apache.logging.log4j.Logger;

public class CustomLogger {
    private static org.apache.logging.log4j.Logger logger;
    public static void registry(Logger l)
    {
        logger =l;
    }

    public static void log( String s )
    {
        logger.log( org.apache.logging.log4j.Level.OFF, s );
    }

    public static void log( org.apache.logging.log4j.Level lv, String s )
    {
        logger.log( lv, s );
    }

    public static void log( org.apache.logging.log4j.Level lv, String s, Object... params )
    {
        logger.log( lv, s, params );
    }

    public static void info( String s )
    {
        logger.info( s );
    }

    public static void info( String s, Object... params )
    {
        logger.info( s, params );
    }

    public static void warn( String s )
    {
        logger.warn( s );
    }

    public static void warn( String s, Object... params )
    {
        logger.warn( s, params );
    }

    public static void trace( String s )
    {
        logger.trace( s );
    }

    public static void trace( String s, Object... params )
    {
        logger.trace( s, params );
    }

    public static void fatal( String s )
    {
        logger.fatal( s );
    }

    public static void fatal( String s, Object... params )
    {
        logger.fatal( s, params );
    }

    public static void debug( String s )
    {
        logger.debug( s );
    }

    public static void debug( String s, Object... params )
    {
        logger.debug( s, params );
    }

    public static void error( String s )
    {
        logger.error( s );
    }

    public static void error( String s, Object... params )
    {
        logger.error( s, params );
    }
}
