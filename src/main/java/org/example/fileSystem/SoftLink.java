package org.example.fileSystem;

public class SoftLink extends File {
    private String targetPath;

    public SoftLink (String name, String targetPath) {
        super(name, 7,7,7);
        this.targetPath = targetPath;
    }

    public void setTargetPath (String targetPath) {
        this.targetPath = targetPath;
    }

    public String getTargetPath () {
        return targetPath;
    }

    public String display() {
        StringBuilder sb = new StringBuilder("l");
        for (Permission p : super.getPermissions()) {
            sb.append(p);
        }
        sb.append("  ").append(super.getName());

        return sb.toString();
    }
}
