package LLD.DesignFileSystemFindAPI;

public class File {

    private String filePath;
    private int fileSize;
    private String fileExtension;

    public File(String fileExtension, String filePath, int fileSize) {
        this.fileExtension = fileExtension;
        this.filePath = filePath;
        this.fileSize = fileSize;
    }

    public String getFileExtension() {
        return fileExtension;
    }

    public void setFileExtension(String fileExtension) {
        this.fileExtension = fileExtension;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public int getFileSize() {
        return fileSize;
    }

    public void setFileSize(int fileSize) {
        this.fileSize = fileSize;
    }
}
