package org.nclc.content.data;

import java.util.Date;

public class ECaseData {

    private String email;
    private String text;
    private String errorText;
    private Date testDate;
    private String resourceFileId;
    
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getErrorText() {
        return errorText;
    }

    public void setErrorText(String errorText) {
        this.errorText = errorText;
    }

    public Date getTestDate() {
        return testDate;
    }

    public void setTestDate(Date testDate) {
        this.testDate = testDate;
    }

    public String getResourceFileId() {
        return resourceFileId;
    }

    public void setResourceFileId(String resourceFileId) {
        this.resourceFileId = resourceFileId;
    }

}
