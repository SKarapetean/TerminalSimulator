package org.example.fileSystem;


import org.example.TerminalEmulator;

import java.util.HashMap;


public class Directory extends FileSystemComponent {

    private Directory superDirectory;
    private final HashMap<String, Directory> subDirectories;
    private final HashMap<String, File> files;

    public Directory (String name, Directory superDirectory) {
        super(name);
        this.superDirectory = superDirectory;
        files = new HashMap<>();
        subDirectories = new HashMap<>();
    }

    public Directory (String name, Directory superDirectory, int ... permission) {
        super(name, permission);
        this.superDirectory = superDirectory;
        files = new HashMap<>();
        subDirectories = new HashMap<>();
    }


    public boolean addFile (String name, int ... permission) {
       if (files.containsKey(name)) {
           return false;
       }

       files.put(name, new File(name, permission));
       return true;
    }

    public boolean addDirectory (String name, Directory superDirectory, int ... permission) {

        if (subDirectories.containsKey(name)) {
            return false;
        }

        subDirectories.put (name, new Directory(name, this, permission));
        return true;
    }

    public boolean removeFile (String name) {
        return files.remove(name) != null;
    }

    public boolean removeDirectory (String name) {
        return subDirectories.remove(name) != null;
    }

    @Override
    public String display() {


        return null;
    }

    public String getPath() {
        StringBuilder sb = new StringBuilder();
        Directory parrent = TerminalEmulator.getCurrentDirectory();
        while (parrent != null) {
            sb.append(parrent.getName() + " ");
            parrent = parrent.superDirectory;
        }
        String[] path = sb.toString().split(" ");
        sb.delete(0,sb.length());
        for (int i = path.length - 1; i >=0; --i) {
            sb.append(path[i]);
            sb.append("/");
        }
        return sb.toString();
    }

    @Override
    public String toString() {
        return "d" + super.getPermissions()[0] + super.getPermissions()[1] + super.getPermissions()[2]
                 + " " + super.getName();
    }


    public void setDefaultPermission() {
        this.setPermissions(7,7,1);
    }

    public void setName(String oldName, String newName) {
        if (subDirectories.containsKey(oldName)) {
            if (subDirectories.containsKey(newName)) {

            } else {
                Directory directory = subDirectories.remove(oldName);
                directory.setName(newName);
                subDirectories.put(newName, directory);
            }
        }
    }

    public Directory getSuperDirectory() {
        return superDirectory;
    }

    public HashMap<String, Directory> getSubDirectories() {
        return subDirectories;
    }

    public void setSuperDirectory(Directory directory) {
        this.superDirectory = directory;
    }
}
