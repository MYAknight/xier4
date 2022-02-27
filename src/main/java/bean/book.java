package bean;

public class book {
    String name;
    String book_ID;
    String intro;
    String front;
    String type;
    String content;
    int xianshi;
    public book(String name, String book_ID, String intro, String front, String type, String content, int xianshi) {
        this.name = name;
        this.book_ID = book_ID;
        this.intro = intro;
        this.front = front;
        this.type = type;
        this.content = content;
        this.xianshi = xianshi;
    }
    @Override
    public String toString() {
        return "book{" +
                "name='" + name + '\'' +
                ", book_ID='" + book_ID + '\'' +
                ", intro='" + intro + '\'' +
                ", front='" + front + '\'' +
                ", type='" + type + '\'' +
                ", content='" + content + '\'' +
                ", xianshi=" + xianshi +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBook_ID() {
        return book_ID;
    }

    public void setBook_ID(String book_ID) {
        this.book_ID = book_ID;
    }

    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }

    public String getFront() {
        return front;
    }

    public void setFront(String front) {
        this.front = front;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
