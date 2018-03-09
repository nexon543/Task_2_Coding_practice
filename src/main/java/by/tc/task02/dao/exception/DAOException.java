package by.tc.task02.dao.exception;

public class DAOException extends Exception {

    public static int SOURCE_ERROR = 0;
    public static int RECORD_ERROR = 1;
    public static int CONFIG_FILE_ERROR = 2;
    public static int SOURCE_CLOSE_EXCEPTION = 3;
    private int errorCode;


    public DAOException(int errorCode) {
        this.errorCode = errorCode;
    }


    public String toString() {
        return "DAOException with error code:" + errorCode;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }
}
