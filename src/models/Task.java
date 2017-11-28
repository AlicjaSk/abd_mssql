package models;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.sql.Date;

@Entity
public class Task {
    private int taskId;
    private Integer issueFk;
    private Integer causeFk;
    private String author;
    private Date creationDate;
    private String doer;
    private String content;
    private Date planTask;

    @Id
    @Column(name = "TaskID", nullable = false)
    public int getTaskId() {
        return taskId;
    }

    public void setTaskId(int taskId) {
        this.taskId = taskId;
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
    @Column(name = "Cause_FK", nullable = true)
    public Integer getCauseFk() {
        return causeFk;
    }

    public void setCauseFk(Integer causeFk) {
        this.causeFk = causeFk;
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
    @Column(name = "doer", nullable = true, length = 100)
    public String getDoer() {
        return doer;
    }

    public void setDoer(String doer) {
        this.doer = doer;
    }

    @Basic
    @Column(name = "content", nullable = true, length = 255)
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Basic
    @Column(name = "planTask", nullable = true)
    public Date getPlanTask() {
        return planTask;
    }

    public void setPlanTask(Date planTask) {
        this.planTask = planTask;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Task task = (Task) o;

        if (taskId != task.taskId) return false;
        if (issueFk != null ? !issueFk.equals(task.issueFk) : task.issueFk != null) return false;
        if (causeFk != null ? !causeFk.equals(task.causeFk) : task.causeFk != null) return false;
        if (author != null ? !author.equals(task.author) : task.author != null) return false;
        if (creationDate != null ? !creationDate.equals(task.creationDate) : task.creationDate != null) return false;
        if (doer != null ? !doer.equals(task.doer) : task.doer != null) return false;
        if (content != null ? !content.equals(task.content) : task.content != null) return false;
        if (planTask != null ? !planTask.equals(task.planTask) : task.planTask != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = taskId;
        result = 31 * result + (issueFk != null ? issueFk.hashCode() : 0);
        result = 31 * result + (causeFk != null ? causeFk.hashCode() : 0);
        result = 31 * result + (author != null ? author.hashCode() : 0);
        result = 31 * result + (creationDate != null ? creationDate.hashCode() : 0);
        result = 31 * result + (doer != null ? doer.hashCode() : 0);
        result = 31 * result + (content != null ? content.hashCode() : 0);
        result = 31 * result + (planTask != null ? planTask.hashCode() : 0);
        return result;
    }
}
