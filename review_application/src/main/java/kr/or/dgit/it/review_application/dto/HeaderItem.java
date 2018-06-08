package kr.or.dgit.it.review_application.dto;

public class HeaderItem extends ItemVO {

    String headerTitle;

    @Override
    public int getType() {
        return ItemVO.TYPE_HEADER;
    }

    public String getHeaderTitle() {
        return headerTitle;
    }

    public void setHeaderTitle(String headerTitle) {
        this.headerTitle = headerTitle;
    }
}
