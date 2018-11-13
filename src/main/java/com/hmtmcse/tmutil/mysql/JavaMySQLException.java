package com.hmtmcse.tmutil.mysql;

/**
 * Created by Touhid Mia on 11/09/2014.
 */
public class JavaMySQLException extends Exception {

    public JavaMySQLException(){
        super("Java MySQL Util Exception Occurred!");
    }

    public JavaMySQLException(String message){
        super(message);
    }
}
