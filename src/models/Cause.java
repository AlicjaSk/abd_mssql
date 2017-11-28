package models;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.sql.Date;

@Entity
public class Cause {
    private int causeId;
    private Integer issueFk;
    private String author;
    private Date creationDate;
    private String content;

    @Id
    @Column(name = "CauseID", nullable = false)
    public int getCauseId() {
        return causeId;
    }

    public void setCauseId(int causeId) {
        this.causeId = causeId;
    }

    @Basic
    @Column(name = "Issue_FK", nullable = true)
    public Integer getIssueFk() {
        return issueFk;
    }

    public void setIssueFk(Integer issueFk) {
        this.issueFk = issueFk;
    }

    @Basic
    @Column(name = "author", nullable = true, length = 100)
    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    @Basic
    @Column(name = "creationDate", nullable = true)
    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    @Basic
    @Column(name = "content", nullable = true, length = 255)
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Cause cause = (Cause) o;

        if (causeId != cause.causeId) return false;
        if (issueFk != null ? !issueFk.equals(cause.issueFk) : cause.issueFk != null) return false;
        if (author != null ? !author.equals(cause.author) : cause.author != null) return false;
        if (creationDate != null ? !creationDate.equals(cause.creationDate) : cause.creationDate != null) return false;
        if (content != null ? !content.equals(cause.content) : cause.content != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = causeId;
        result = 31 * result + (issueFk != null ? issueFk.hashCode() : 0);
        result = 31 * result + (author != null ? author.hashCode() : 0);
        result = 31 * result + (creationDate != null ? creationDate.hashCode() : 0);
        result = 31 * result + (content != null ? content.hashCode() : 0);
        return result;
    }
}
