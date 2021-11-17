package objects;

import java.util.Date;
import java.util.StringJoiner;

public class Authors {
    private int authorID;
    private String authorName;
    private Date dateOfBirth;
    private Date dateOfDeath;
    private String authorInfo;

    public Authors() {
    }

    public Authors(int authorID, String userName, Date dateOfBirth, Date dateOfDeath, String authorInfo) {
        this.authorID = authorID;
        this.authorName = userName;
        this.dateOfBirth = dateOfBirth;
        this.dateOfDeath = dateOfDeath;
        this.authorInfo = authorInfo;
    }

    public Authors(int authorID, String userName, Date dateOfBirth) {
        this.authorID = authorID;
        this.authorName = userName;
        this.dateOfBirth = dateOfBirth;
    }

    public int getAuthorID() {
        return authorID;
    }

    public void setAuthorID(int authorID) {
        this.authorID = authorID;
    }

    public String getUserName() {
        return authorName;
    }

    public void setUserName(String userName) {
        this.authorName = userName;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public Date getDateOfDeath() {
        return dateOfDeath;
    }

    public void setDateOfDeath(Date dateOfDeath) {
        this.dateOfDeath = dateOfDeath;
    }

    public String getAuthorInfo() {
        return authorInfo;
    }

    public void setAuthorInfo(String authorInfo) {
        this.authorInfo = authorInfo;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Authors.class.getSimpleName() + "[", "]")
                .add("authorID=" + authorID)
                .add("userName='" + authorName + "'")
                .add("dateOfBirth=" + dateOfBirth)
                .add("dateOfDeath=" + dateOfDeath)
                .add("authorInfo='" + authorInfo + "'")
                .toString();
    }

}

