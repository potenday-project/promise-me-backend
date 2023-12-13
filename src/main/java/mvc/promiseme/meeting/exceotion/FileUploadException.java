package mvc.promiseme.meeting.exceotion;

public class FileUploadException extends RuntimeException{
    public FileUploadException() {
    }

    public FileUploadException(String message) {
        super(message);
    }
}
