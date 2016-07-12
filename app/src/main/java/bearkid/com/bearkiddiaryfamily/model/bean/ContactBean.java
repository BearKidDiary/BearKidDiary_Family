package bearkid.com.bearkiddiaryfamily.model.bean;

/**
 * 联系人列表的Bean
 */
public class ContactBean implements Comparable {
    public String name;
    public String pingyin;

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
