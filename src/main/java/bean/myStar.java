package bean;

public class myStar {
    int book_id;
    int user_id;

    @Override
    public String toString() {
        return "myStar{" +
                "book_id=" + book_id +
                ", user_id=" + user_id +
                '}';
    }

    public int getBook_id() {
        return book_id;
    }

    public void setBook_id(int book_id) {
        this.book_id = book_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public myStar(int book_id, int user_id) {
        this.book_id = book_id;
        this.user_id = user_id;
    }
}
