package LLD.DesignFileSystemFindAPI;

import java.util.*;

public class FileSystemFind {
    private Map<String, File> filePathMap;
    private Map<String, String> currentFiltersMap;

    public FileSystemFind() {
        this.filePathMap = new HashMap<>();
        this.currentFiltersMap = new HashMap<>();
    }

    public void addFile(String path, int size, String extension) {
        File file;

        if(this.filePathMap.containsKey(path)){
            file = this.filePathMap.get(path);

        }else{
            file = new File(extension,path,size);
        }

        this.filePathMap.put(path,file);
    }

    public void addFilterByName(String name) {
        System.out.println("Setting name: "+name);
        this.currentFiltersMap.put("Name", name);
    }

    public void addFilterByMinSize(int minSize) {
        System.out.println("Setting minSize: "+minSize);
        String sizeInString = String.valueOf(minSize);
        this.currentFiltersMap.put("Size", sizeInString);
    }

    public void addFilterByExtension(String extension) {
        System.out.println("Setting Extension: "+extension);
        this.currentFiltersMap.put("Extension", extension);
    }

    public void clearFilters() {
        this.currentFiltersMap.clear();
    }

    public List<String> find(String path) {
        Set<String> filePathsList = new HashSet<>();
        String fileExtension = "", fileSize = "", fileName = "";

        if (this.currentFiltersMap.containsKey("Extension")) {
            fileExtension = this.currentFiltersMap.get("Extension");
        }
        if (this.currentFiltersMap.containsKey("Size")) {
            fileSize = this.currentFiltersMap.get("Size");
        }
        if (this.currentFiltersMap.containsKey("Name")) {
            fileName = this.currentFiltersMap.get("Name");
        }


        for(Map.Entry<String, File> entry : this.filePathMap.entrySet()){
            String filePath = entry.getKey();
            File currentFile = entry.getValue();

            if(filePath.indexOf(path) >= 0){
                if(this.currentFiltersMap.size() == 0){
                    filePathsList.add(filePath);
                }else {
                    if (!fileExtension.equals("") && filePath.indexOf(fileExtension) >= 0) {
                        if(!fileSize.equals("")) {
                            if (currentFile.getFileSize() >= (Integer.parseInt(fileSize))) {
                                filePathsList.add(filePath);
                            }
                        }else{
                            filePathsList.add(filePath);
                        }
                    }
                    else if(!fileSize.equals("") && currentFile.getFileSize() >= (Integer.parseInt(fileSize))) {
                        if(!fileExtension.equals("") && filePath.indexOf(fileExtension) >= 0) {
                            filePathsList.add(filePath);
                        }
                    }

                    if (!fileName.equals("") && filePath.indexOf(fileName) >= 0) {
                        filePathsList.add(filePath);
                    }
                }
            }
        }

        return new ArrayList<>(filePathsList);
    }
}
