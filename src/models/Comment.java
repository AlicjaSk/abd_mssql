package models;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.sql.Date;

@Entity
public class Comment {
    private int commentId;
    private Integer taskFk;
    private Integer issueFk;
    private String author;
    private Date creationDate;

    @Id
    @Column(name = "CommentID", nullable = false)
    public int getCommentId() {
        return commentId;
    }

    public void setCommentId(int commentId) {
        this.commentId = commentId;
    }

    @Basic
    @Column(name = "Task_FK", nullable = true)
    public Integer getTaskFk() {
        return taskFk;
    }

    public void setTaskFk(Integer taskFk) {
        this.taskFk = taskFk;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Comment comment = (Comment) o;

        if (commentId != comment.commentId) return false;
        if (taskFk != null ? !taskFk.equals(comment.taskFk) : comment.taskFk != null) return false;
        if (issueFk != null ? !issueFk.equals(comment.issueFk) : comment.issueFk != null) return false;
        if (author != null ? !author.equals(comment.author) : comment.author != null) return false;
        if (creationDate != null ? !creationDate.equals(comment.creationDate) : comment.creationDate != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = commentId;
        result = 31 * result + (taskFk != null ? taskFk.hashCode() : 0);
        result = 31 * result + (issueFk != null ? issueFk.hashCode() : 0);
        result = 31 * result + (author != null ? author.hashCode() : 0);
        result = 31 * result + (creationDate != null ? creationDate.hashCode() : 0);
        return result;
    }
}
