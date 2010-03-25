package testClass.testContains;

public class BasicClass {

    private String name;

    private String mail;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof BasicClass)) {
            return false;
        }
        BasicClass bean = (BasicClass) obj;
        if (bean.mail != null && !"".equals(bean.mail.trim())
                && this.mail != null && !"".equals(this.mail.trim())
                && bean.mail.equals(this.mail)) {
            return true;
        }
        return super.equals(obj);
    }
}
