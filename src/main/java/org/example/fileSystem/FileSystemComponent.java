package org.example.fileSystem;


public abstract class FileSystemComponent {
    private String name;
    private Permission[] permissions;

    public FileSystemComponent(String name) {
        this.name = name;
        setDefaultPermission();
    }

    public FileSystemComponent(String name, int ... permission) {
        this.name = name;
        permissions = new Permission[3];
        setPermissions(permission);
    }

    protected abstract void setDefaultPermission();
    public abstract String display();

    public void setPermissions(int ... permissions) {
        if (permissions.length != 3) {
            throw new IllegalStateException("Permissions are not valid!");
        }
        for (int i = 0; i < permissions.length; ++i) {
            switch (permissions[i]) {
                case 0 :
                        this.permissions[i] = Permission.none;
                    break;
                case 1 :
                        this.permissions[i] = Permission.__x;
                    break;
                case 2 :
                        this.permissions[i] = Permission._w_;
                    break;
                case 3 :
                        this.permissions[i] = Permission._wx;
                    break;
                case 4 :
                        this.permissions[i] = Permission.r__;
                    break;
                case 5 :
                        this.permissions[i] = Permission.r_x;
                    break;
                case 6 :
                        this.permissions[i] = Permission.rw_;
                    break;
                case 7 :
                        this.permissions[i] = Permission.rwx;
                    break;
                default :
                        throw new IllegalStateException("Permission is not valid!");
            }
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String newName) {
        this.name = newName;
    }

    public Permission[] getPermissions() {
        return permissions.clone();
    }

}
