package models;

/**
 * Created by seanx_000 on 2/12/2015.
 */
public class ModelAnonTips {

    private String subject;
    private String message;

    public ModelAnonTips(String subject, String message) {
        this.subject = subject;
        this.message = message;
    }

    public void setSubject( String subject ) {
        this.subject = subject;
    }

    public void setMessage( String message ) {
        this.message = message;
    }

    public void save() {
        // TODO: Network Send Data. [HTTP]&&[POST]

    }
}
