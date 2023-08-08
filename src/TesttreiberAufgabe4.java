import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class TesttreiberAufgabe4 {

    public static void main(String[] args) {
        if (args.length != 1) {
            System.err.println("Usage: java Task4 <path_to_word2count.txt>");
            return;
        }

        String filePath = args[0];
        List<String> lines;
        try {
            lines = Files.readAllLines(Paths.get(filePath));
        } catch (IOException e) {
            System.err.println("Error reading file: " + filePath);
            return;
        }

        Tree wordTree = new Tree();

        for (String line : lines) {
            String[] words = line.split("\\s+");
            for (String word : words) {
                NodeData data = new NodeData();
                data.w = word;
                Node existingNode = Tree.find(wordTree.root, data);
                if (existingNode == null) {
                    data.count = 1; // Set count to 1 for new node
                    wordTree.root = Tree.findOrInsert(wordTree.root, data);
                } else {
                    existingNode.data.count++; // Increment count for existing node
                }
            }
        }

        System.out.println("In-order traversal of the binary tree:");
        inOrderTraversal(wordTree.root);
    }

    private static void inOrderTraversal(Node node) {
        if (node == null) {
            return;
        }

        inOrderTraversal(node.left);
        System.out.printf("%s: %d%n", node.data.w, node.data.count);
        inOrderTraversal(node.right);
    }
}
