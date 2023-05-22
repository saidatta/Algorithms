package Leetcode.Design;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

class InMemoryFileSystem {

    FileNode root;

    public InMemoryFileSystem() {
        root = new FileNode("/");
    }

    public List<String> ls(String path) {
        FileNode node = findNode(path, false);
        return node == null ? null : node.listSubDirectories();
    }

    public void mkdir(String path) {
        findNode(path, true);

    }

    public void addContentToFile(String filePath, String content) {
        FileNode node = findNode(filePath, true);
        node.addContent(content);
    }

    public String readContentFromFile(String filePath) {
        FileNode node = findNode(filePath, true);
        return node.readFile();
    }

    private FileNode findNode(String path, boolean make) {
        String[] nodes = path.split("/");
        FileNode fNode = root;

        for (String node : nodes) {
            if (node.isEmpty()) continue;

            if (!fNode.getSubDirectories().containsKey(node)) {
                if (make) {
                    fNode.createSubDirectory(node);
                } else {
                    return null;
                }

            }
            fNode = fNode.getSubDirectories().get(node);

        }
        return fNode;
    }

    public class FileNode {
        private String name;
        private StringBuilder file;
        private TreeMap<String, FileNode> subDirectories;

        public FileNode(String name) {
            this.name = name;
            this.file = new StringBuilder();
            this.subDirectories = new TreeMap<>();
        }

        public String getName() {
            return this.name;
        }

        public TreeMap<String, FileNode> getSubDirectories() {
            return this.subDirectories;
        }

        public void createSubDirectory(String pathName) {
            this.subDirectories.put(pathName, new FileNode(pathName));
        }

        public void addContent(String content) {
            this.file.append(content);
        }

        public String readFile() {
            return this.file.toString();
        }

        public List<String> listSubDirectories() {
            List<String> list = new ArrayList<>();

            if (this.file.length() > 0) {
                list.add(this.name);
            } else {
                list.addAll(this.subDirectories.keySet());
            }
            return list;
        }
    }
}
