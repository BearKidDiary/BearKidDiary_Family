package bearkid.com.bearkiddiaryfamily.model.bean;

import bearkid.com.bearkiddiaryfamily.utils.ContactsType;

/**
 * 联系人列表的Bean
 */
public class ContactBean implements Comparable {

    public String name;
    public String pingyin;
    public int ischecked = ContactsType.NOCHECKED;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPingyin() {
        return pingyin;
    }

    public void setPingyin(String pingyin) {
        this.pingyin = pingyin;
    }

    public int getIschecked() {
        return ischecked;
    }

    public void setIschecked(int ischecked) {
        this.ischecked = ischecked;
    }

    @Override
    public int compareTo(Object o) {
        if (pingyin.equals("@")
                || ((ContactBean)o).getPingyin().equals("#")) {
            return -1;
        } else if (pingyin.equals("#")
                || ((ContactBean)o).getPingyin().equals("@")) {
            return 1;
        }else {
            return pingyin.compareToIgnoreCase(((ContactBean)o).getPingyin());
        }

    }
}
