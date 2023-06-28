package org.example.fileSystem;

public class File extends FileSystemComponent {

    private StringBuilder context;
    public File(String name) {
        super(name);
    }

    public File(String name, int ... permission) {
        super(name, permission);
    }

    public void setContext(String string, boolean b) {
        if (!b) {
            context.delete(0, context.length());
        }
        context.append(string).append("\n");
    }

    public String getContext() {
        return context.toString();
    }

    @Override
    public String display() {
        StringBuilder sb = new StringBuilder("_");
        for (Permission p : super.getPermissions()) {
            sb.append(p);
        }
        sb.append("  ").append(super.getName());

        return sb.toString();
    }

    public void setDefaultPermission() {
        setPermissions(6,6,4);
    }

}
