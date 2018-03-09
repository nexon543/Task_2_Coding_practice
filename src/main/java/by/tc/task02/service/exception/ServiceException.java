package by.tc.task02.service.exception;

public class ServiceException extends Exception {
    public static int FILE_NAME_ERROR = 0;
    public static int CRITERIA_TYPE_ERROR = 1;
    private int errorCode;
    public ServiceException(int errorCode) {
        this.errorCode = errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public int gerErrorCode() {
        return errorCode;
    }
}
