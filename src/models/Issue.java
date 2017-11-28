package models;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.sql.Date;

@Entity
public class Issue {
    private int issueId;
    private String author;
    private Date creationDate;
    private String content;

    @Id
    @Column(name = "IssueID", nullable = false)
    public int getIssueId() {
        return issueId;
    }

    public void setIssueId(int issueId) {
        this.issueId = issueId;
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

        Issue issue = (Issue) o;

        if (issueId != issue.issueId) return false;
        if (author != null ? !author.equals(issue.author) : issue.author != null) return false;
        if (creationDate != null ? !creationDate.equals(issue.creationDate) : issue.creationDate != null) return false;
        if (content != null ? !content.equals(issue.content) : issue.content != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = issueId;
        result = 31 * result + (author != null ? author.hashCode() : 0);
        result = 31 * result + (creationDate != null ? creationDate.hashCode() : 0);
        result = 31 * result + (content != null ? content.hashCode() : 0);
        return result;
    }
}
