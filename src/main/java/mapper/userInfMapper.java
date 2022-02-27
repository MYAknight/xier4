package mapper;

import bean.book;
import bean.myStar;
import bean.user;

import java.util.List;
import java.util.Map;

public interface userInfMapper {

    void addUserInf(user u1);

    void addMyStars(myStar mystar);

    Integer checkExist(String name);

    Integer checkUserID(String phone);

    List<Integer> getUserStarBooks(int UID);

    List<book> getBookInf(String name);

    user getUserInf(String phone);

    List<book> getBookInfByPage(Map<String,Object> m1);

    List<book> getBookByType(String type);

    //查询所有未显示的书目
    List<book> getShowed();

//    根据ID获取书的信息
    book getBookByID(String book_ID);

    String checkName2Password(String phone);

    String checkAdminPassword(String phone);

    int getNextId();

    int getBookListLength(String name);

    void addNewBook(book b1);

    void deleteByID(String book_ID);

    void changeBookShowState(String book_ID);
}
