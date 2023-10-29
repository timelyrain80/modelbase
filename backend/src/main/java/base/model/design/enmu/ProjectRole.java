package base.model.design.enmu;


public enum ProjectRole implements BaseEnum {
    ADMIN("管理员"),
    USER("开发人员"),
    VIEWER("只读用户");

    String label;

    ProjectRole(String label) {
        this.label = label;
    }

    ProjectRole() {
    }

    @Override
    public String label() {
        return this.label;
    }
}
