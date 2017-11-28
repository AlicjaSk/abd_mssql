

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.sql.Date;
import java.util.Calendar;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import models.Issue;
import models.Comment;
import models.Cause;
import models.ClosingComment;
import models.Task;

/**
 *
 * @author skurc
 */
public class ManageIssue {

    Session session;
    int issue;
    Scanner reader;
    String author;

    public ManageIssue(int IssueID, String author, Session s) {
        reader = new Scanner(System.in);
        this.author = author;
        session = s;
        this.issue = IssueID;

        int n = -1;
        while (true) {
            n = menu();
            if (n == 1) {
                addComment();
            } else if (n == 2) {
                addCause();
            } else if (n == 3) {
                addTask();
            } else if (n == 4) {
                closeIssue();
            } else if (n == 5) {
                showEvents();
            } else if (n == 6) {
                updateIssue();
            } else if (n == 7) {
                deleteIssue();
                break;
            } else if (n == 8) {
                try {
                    this.transactionExample();
                } catch (Throwable ex) {
                    Logger.getLogger(ManageIssue.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else if (n == 9) {
                break;
            }
        }
    }

    public int menu() {
        System.out.println("Select:");
        System.out.println("    1 Add Comment");
        System.out.println("    2 Add Cause");
        System.out.println("    3 Add Task");
        System.out.println("    4 Close Issue");
        System.out.println("    5 Show events");
        System.out.println("    6 Edit content");
        System.out.println("    7 Delete issue");
        System.out.println("    8 Example transaction");
        System.out.println("    9 Exit");
        System.out.println("[1-9]");

        Scanner reader = new Scanner(System.in);
        int n = reader.nextInt();
        return n;

    }

    public int getNewID(String query) {
        Query id = session.createQuery(query);
        List ids = id.list();
        Scanner reader = new Scanner(System.in);
        int n = 0;

        if (n==0) {
            return 1;
        } else {
            return n+1;
        }
    }

    public void addComment() {
        int n = this.getNewID("select max(commentId) from Comment");

        Date date = new Date(Calendar.getInstance().getTime().getTime());

        System.out.println("    Insert content");
        String content = reader.next();

        Comment u = null;
        session.beginTransaction();
        u = new Comment();
        u.setCommentId(n);
        u.setIssueFk(this.issue);
        u.setTaskFk(null);
        u.setAuthor(author);
        u.setCreationDate(date);
        session.save(u);
        session.getTransaction().commit();
    }

    public void addCause() {
        int n = this.getNewID("select max(causeId) from Cause");

        Date date = new Date(Calendar.getInstance().getTime().getTime());
        System.out.println("    Insert content");
        String content = reader.next();

        Cause u = null;
        session.beginTransaction();
        u = new Cause();
        u.setCauseId(n);
        u.setIssueFk(this.issue);
        u.setAuthor(author);
        u.setCreationDate(date);
        u.setContent(content);
        session.save(u);
        session.getTransaction().commit();
    }

    public Date convertStringIntoDate(String dateStr) {
        DateFormat formatter = new SimpleDateFormat("MM/dd/yy");
        try {
            Date date = (Date) formatter.parse(dateStr);
            return date;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void addTask() {
        int n = this.getNewID("select max(taskId) from Task");

        System.out.println("    Insert doer");
        String doer = reader.next();

        System.out.println("    Insert plain (MM/dd/yy)");
        String planStr = reader.next();
        Date plain = this.convertStringIntoDate(planStr);

        Date date = new Date(Calendar.getInstance().getTime().getTime());

        System.out.println("    Insert content");
        String content = reader.next();

        Task u = null;
        session.beginTransaction();
        u = new Task();
        u.setTaskId(n);
        u.setIssueFk(this.issue);
        u.setPlanTask(plain);
        u.setAuthor(author);
        u.setDoer(doer);
        u.setCreationDate(date);
        u.setContent(content);
        session.save(u);
        session.getTransaction().commit();
    }

    public void closeIssue() {
        System.out.println("    Please create closing comment");
        int n = this.getNewID("select max(closingCommentId) from ClosingComment");

        Date date = new Date(Calendar.getInstance().getTime().getTime());

        System.out.println("    Insert content");
        String content = reader.next();

        ClosingComment u = null;
        session.beginTransaction();
        u = new ClosingComment();
        u.setCommentId(n);
        u.setIssueFk(this.issue);
        u.setAuthor(author);
        u.setCreationDate(date);
        session.save(u);
        session.getTransaction().commit();
        System.out.println("    Issue closed");
    }

    public void showEvents() {
        System.out.println("    Comments:");
        Query comments = session.createQuery("from Comment where issueFk=:issue");
        comments.setParameter("issue", issue);
        showComment(comments);

        System.out.println("    Tasks:");
        Query tasks = session.createQuery("from Task where issueFk=:issue");
        tasks.setParameter("issue", issue);
        showTasks(tasks);

        System.out.println("    Causes:");
        Query causes = session.createQuery("from Cause where issueFk=:issue");
        causes.setParameter("issue", issue);
        showCause(causes);
    }

    public void showComment(Query q) {
        List<Comment> rows = q.list();

        System.out.println("CommentId\t\t\tAuthor\t\t\tDate\t\t\tContent\t\t\tIssueID");
        for (Comment i : rows) {
            String date = new SimpleDateFormat("yyyy-MM-dd").format(i.getCreationDate());
            System.out.println(i.getCommentId() + "\t\t\t" + i.getAuthor() + "\t\t\t" + date + "\t\t\t" +
                    i.getIssueFk());
        }
    }

    public void showTasks(Query q) {
        List<Task> rows = q.list();

        System.out.println("TaskId\t\t\tAuthor\t\t\tDate\t\t\tContent\t\t\tdoer\t\t\tplan\t\t\tIssueID");
        for (Task i : rows) {
            String date = new SimpleDateFormat("yyyy-MM-dd").format(i.getCreationDate());
            String plan = new SimpleDateFormat("yyyy-MM-dd").format(i.getPlanTask());
            System.out.println(i.getTaskId() + "\t\t\t" + i.getAuthor() + "\t\t\t" + date + "\t\t\t" + i.getContent() + "\t\t\t"
                    + i.getDoer() + "\t\t\t" + plan + "\t\t\t" + i.getIssueFk());
        }
    }

    public void showCause(Query q) {
        List<Cause> rows = q.list();

        System.out.println("CommentId\t\t\tAuthor\t\t\tDate\t\t\tContent\t\t\tIssueID");
        for (Cause i : rows) {
            String date = new SimpleDateFormat("yyyy-MM-dd").format(i.getCreationDate());
            System.out.println(i.getCauseId() + "\t\t\t" + i.getAuthor() + "\t\t\t"
                    + date + "\t\t\t" + i.getContent() + "\t\t\t" + i.getIssueFk());
        }
    }

    public void updateIssue() {
        Query query = session.createQuery("update Issue set content = :content"
                + " where issueId = :issueId");

        System.out.println("    Insert new content");
        String content = reader.next();

        query.setParameter("content", content);
        query.setParameter("issueId", issue);
        int result = query.executeUpdate();
        System.out.println("Record updated");
    }

    public void deleteIssue() {
        Transaction transaction = session.beginTransaction();
        try {
            // your code
            String hql = "delete from Comment where issueFk=:issue";

            Query query = session.createQuery(hql);
            query.setParameter("issue", issue);

            System.out.println(query.executeUpdate());

            // your code
            hql = "delete from Cause where issueFk=:issue";

            query = session.createQuery(hql);
            query.setParameter("issue", issue);

            System.out.println(query.executeUpdate());

            // your code
            hql = "delete from Task where issueFk=:issue";

            query = session.createQuery(hql);
            query.setParameter("issue", issue);

            System.out.println(query.executeUpdate());

            hql = "delete from Issue where issueId=:issue";

            query = session.createQuery(hql);
            query.setParameter("issue", issue);

            System.out.println(query.executeUpdate());

            transaction.commit();

        } catch (Throwable t) {
            transaction.rollback();
            throw t;
        }
    }

    public void transactionExample() throws Throwable {
        Transaction transaction = session.beginTransaction();
        try {
            //TimeUnit.MINUTES.sleep(1);
            transaction.commit();

        } catch (Throwable t) {
            transaction.rollback();
            throw t;
        }
    }
}
