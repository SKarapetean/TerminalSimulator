package org.example.fileSystem;

public class HardLink extends File {

    private File targetFile;

    public HardLink (String name, File targetFile) {
        super(name);
        this.targetFile = targetFile;
    }

    public File getTargetFile () {
        return targetFile;
    }

    public void setTargetFile (File targetFile) {
        this.targetFile = targetFile;
    }
}
