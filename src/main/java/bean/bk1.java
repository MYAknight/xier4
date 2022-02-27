package bean;

public class bk1 {
    String name;
    String book_ID;
    String intro;
    String front;

    @Override
    public String toString() {
        return "bk1{" +
                "name='" + name + '\'' +
                ", book_ID='" + book_ID + '\'' +
                ", intro='" + intro + '\'' +
                ", front='" + front + '\'' +
                ", type='" + type + '\'' +
                ", content='" + content + '\'' +
                '}';
    }

    String type;
    String content;

    public bk1(String name, String book_ID, String intro, String front, String type, String content) {
        this.name = name;
        this.book_ID = book_ID;
        this.intro = intro;
        this.front = front;
        this.type = type;
        this.content = content;
    }
}
