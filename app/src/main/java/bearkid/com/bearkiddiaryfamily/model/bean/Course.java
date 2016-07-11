package bearkid.com.bearkiddiaryfamily.model.bean;

import cn.bmob.v3.BmobObject;
import cn.bmob.v3.datatype.BmobDate;

/**
 * �γ���Ϣ
 */
public class Course extends BmobObject {
    /**
     * ����ʱ��
     */
    private BmobDate Cclasstime;
    /**
     * ����ʱ��
     */
    private BmobDate Cendtime;
    /**
     * ��ѧʱ��
     */
    private BmobDate Ctime;
    /**
     * ��ҵʱ��
     */
    private BmobDate Cofftime;
    /**
     * ����������
     */
    private Teacher Capprover;
    /**
     * �γ̵Ŀ��α�����ԭ��
     */
    private String Cbackground;
    /**
     * �γ�����
     */
    private String Cdesc;
    /**
     * �γ�����
     */
    private String Cname;
    /**
     * ��һ�������Ƿ���Ҫ�Ͽ�
     */
    private Boolean Cmonday, Ctuesday, Cwednesday, Cthursday, Cfriday, Csaturday;

    public BmobDate getCclasstime() {
        return Cclasstime;
    }

    public void setCclasstime(BmobDate cclasstime) {
        Cclasstime = cclasstime;
    }

    public BmobDate getCendtime() {
        return Cendtime;
    }

    public void setCendtime(BmobDate cendtime) {
        Cendtime = cendtime;
    }

    public BmobDate getCtime() {
        return Ctime;
    }

    public void setCtime(BmobDate ctime) {
        Ctime = ctime;
    }

    public BmobDate getCofftime() {
        return Cofftime;
    }

    public void setCofftime(BmobDate cofftime) {
        Cofftime = cofftime;
    }

    public Teacher getCapprover() {
        return Capprover;
    }

    public void setCapprover(Teacher capprover) {
        Capprover = capprover;
    }

    public String getCbackground() {
        return Cbackground;
    }

    public void setCbackground(String cbackground) {
        Cbackground = cbackground;
    }

    public String getCdesc() {
        return Cdesc;
    }

    public void setCdesc(String cdesc) {
        Cdesc = cdesc;
    }

    public String getCname() {
        return Cname;
    }

    public void setCname(String cname) {
        Cname = cname;
    }

    public Boolean getCmonday() {
        return Cmonday;
    }

    public void setCmonday(Boolean cmonday) {
        Cmonday = cmonday;
    }

    public Boolean getCtuesday() {
        return Ctuesday;
    }

    public void setCtuesday(Boolean ctuesday) {
        Ctuesday = ctuesday;
    }

    public Boolean getCwednesday() {
        return Cwednesday;
    }

    public void setCwednesday(Boolean cwednesday) {
        Cwednesday = cwednesday;
    }

    public Boolean getCthursday() {
        return Cthursday;
    }

    public void setCthursday(Boolean cthursday) {
        Cthursday = cthursday;
    }

    public Boolean getCfriday() {
        return Cfriday;
    }

    public void setCfriday(Boolean cfriday) {
        Cfriday = cfriday;
    }

    public Boolean getCsaturday() {
        return Csaturday;
    }

    public void setCsaturday(Boolean csaturday) {
        Csaturday = csaturday;
    }
}
