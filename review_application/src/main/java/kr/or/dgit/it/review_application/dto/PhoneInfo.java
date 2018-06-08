package kr.or.dgit.it.review_application.dto;

public class PhoneInfo extends ItemVO {

    private int id;
    private String name;
    private String date;

    public PhoneInfo() {

    }

    public PhoneInfo(String name, String date) {
        this.name = name;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }


    @Override
    public int getType() {
        return ItemVO.TYPE_DATA;
    }

    @Override
    public String toString() {
        return "PhoneInfo{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", date='" + date + '\'' +
                '}';
    }
}
