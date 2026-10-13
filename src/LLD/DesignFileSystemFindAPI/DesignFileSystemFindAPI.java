package LLD.DesignFileSystemFindAPI;

public class DesignFileSystemFindAPI {

    public static void main(String[] args) {
        FileSystemFind fs = new FileSystemFind();

        fs.addFile("/home/user/docs/report.txt", 500, "txt") ;
        fs.addFile("/home/user/docs/notes.txt", 100, "txt");
        fs.addFile("/home/user/photos/vacation.jpg", 2000, "jpg");
        fs.addFile("/home/user/docs/summary.pdf", 800, "pdf");
        System.out.println(fs.find("/home"));
        fs.addFilterByExtension("txt");
        System.out.println(fs.find("/home"));
        fs.addFilterByMinSize(200);
        System.out.println(fs.find("/home"));
        fs.clearFilters();
        fs.addFilterByName("vacation.jpg");
        System.out.println(fs.find("/home"));

        fs = new FileSystemFind();
        fs.addFile("/a/b.txt", 10, "txt") ;
        fs.addFile("/a/c.py", 20, "py");
        System.out.println(fs.find("/a"));
        fs.addFilterByExtension("py");
        System.out.println(fs.find("/a"));
        fs.clearFilters();
        System.out.println(fs.find("/a"));
    }
}
