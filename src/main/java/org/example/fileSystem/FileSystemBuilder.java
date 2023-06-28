package org.example.fileSystem;

public class FileSystemBuilder {
    private FileSystemBuilder () {}

    public static Directory build () {
        Directory root = new Directory(DefaultDirectories.ROOT.getPath(), null, 7,5,5);
        root.addDirectory(DefaultDirectories.BOOT.getPath(), root, 7,5,5);
        bootBuilder(root.getSubDirectories().get(DefaultDirectories.BOOT.getPath()));
        root.addDirectory(DefaultDirectories.ETC.getPath(), root, 7,5,5);
        etcBuilder(root.getSubDirectories().get(DefaultDirectories.ETC.getPath()));
        root.addDirectory(DefaultDirectories.HOME.getPath(), root, 7,5,5);
        root.getSubDirectories().get("home").addDirectory("defaultUser", root.getSubDirectories().get("home"), 7,6,0);
        root.addDirectory(DefaultDirectories.SUPERROOT.getPath(), root, 7,0,0);

        root.addDirectory(DefaultDirectories.RUN.getPath(), root, 7,5,5);
        root.addDirectory(DefaultDirectories.PROC.getPath(), root, 5,5,5);
        root.addDirectory(DefaultDirectories.MNT.getPath(), root, 7,5,5);
        root.addDirectory(DefaultDirectories.MEDIA.getPath(), root, 7,5,5);
        root.addDirectory(DefaultDirectories.USR.getPath(), root, 7,5,5);
        root.addDirectory(DefaultDirectories.VAR.getPath(), root, 7,5,5);
        root.addDirectory(DefaultDirectories.OPT.getPath(), root, 7,5,5);
        root.addDirectory(DefaultDirectories.SRV.getPath(), root, 7,5,5);
        root.addDirectory(DefaultDirectories.SYS.getPath(), root, 7,5,5);
        root.addDirectory(DefaultDirectories.TMP.getPath(), root, 7,5,5);
        return root;
    }

    private static void bootBuilder (Directory boot) {
        boot.addDirectory("efi", boot, 7,0,0);
        boot.addDirectory("grub", boot, 7,5,5);
    }

    private static void etcBuilder (Directory etc) {
        etc.addFile("passwd", 6,4,4);
        etc.addFile("group", 6,4,4);
        etc.addFile("shadow", 6,4,0);
        etc.addFile("hosts", 6,4,4);
        etc.addFile("hostname", 6,4,4);
        etc.addFile("networks", 6,4,4);
        etc.addDirectory("network", etc, 7,5,5);
    }

}
