
import java.util.Scanner;

public class Tree {
    // Create a static variable called "root" of type Node. This will be the root of the tree.
    static Node root;

    // This method is used to build a binary tree based on user input.
    public static void buildTree() {
        // Create a Scanner object to read user input
        Scanner scanner = new Scanner(System.in);

        // Print a message prompting the user to input the binary tree representation
        System.out.println("Enter the binary tree representation:");

        // Read the next line of input from the user and store it in a String variable called "input".
        String input = scanner.nextLine();

        // Close the Scanner object to free up resources
        scanner.close();

        // Split the input String into an array of String tokens based on spaces
        String[] tokens = input.split(" ");

        // Call the "buildTreeFromTokens" method with the "tokens" array and a new array with a single
        // element 0 as arguments, and assign the result to "root"
        root = buildTreeFromTokens(tokens, new int[]{0});
    }

    // Define a private static method called "buildTreeFromTokens" that takes an array of String tokens
    // and an array of integers as parameters and returns a Node. This method is used to recursively
    // build a binary tree from the tokens.
    private static Node buildTreeFromTokens(String[] tokens, int[] index) {
        // If the first element of "index" is greater or equal to the length of "tokens", return null.
        // This is the base case for the recursion when all tokens have been processed.
        if (index[0] >= tokens.length) {
            return null;
        }

        // Get the token at the index specified by the first element of "index" and store it in a
        // String variable called "value", then increment the first element of "index"
        String value = tokens[index[0]++];

        // Check if the token is a valid string or '@' using the "isValidToken" method.
        // If not, throw an IllegalArgumentException with a custom error message.
        if (!isValidToken(value)) {
            throw new IllegalArgumentException("Tokens must be only String, Invalid token: " + value);
        }

        // If the token is "@", it represents an empty subtree, so return null
        if (value.equals("@")) {
            return null;
        }

        // Create a new NodeData object and set its "w" field to "value"
        NodeData nodeData = new NodeData();
        nodeData.w = value;

        // Create a new Node object, set its "data" field to "nodeData"
        Node node = new Node();
        node.data = nodeData;

        // Recursively call "buildTreeFromTokens" method to build the left and right subtrees
        node.left = buildTreeFromTokens(tokens, index);
        node.right = buildTreeFromTokens(tokens, index);

        // Return the current node
        return node;
    }

    // It takes a String token as a parameter
    // and returns a boolean. This method is used to check whether a token is a valid string or '@'.
    // isValidToken, checks whether a string matches the regular expression for an alphabetic string
    // or is equal to '@', returning true or false accordingly
    private static boolean isValidToken(String token) {
        // The token is valid if it's an alphabetic string or '@'
        return token.matches("[a-zA-Z]+") || token.equals("@");
    }

    // The main method is the entry point for the program. It calls the buildTree method to construct the tree
    // from user input and then tests the tree using an instance of the TesttreiberAufgabe2 class.
    public static void main(String[] args) {
        buildTree();
        TesttreiberAufgabe2 obj = new TesttreiberAufgabe2();
        obj.printTraversals(root);
        System.out.println();
        System.out.println("======The insertion of existing and new nodes======");
        System.out.println();
        // Inserting some existed with another new nodes
        String[] newDataStrings = {"D", "E", "A", "C", "F", "G"};
        for (String dataString : newDataStrings) {
            NodeData newData = new NodeData();
            newData.w = dataString;
            root = findOrInsert(root, newData);
        }

        obj.printTraversals(root);

    }


    // preOrderTraversal, inOrderTraversal, and postOrderTraversal are methods for performing
    // different types of depth-first traversal of the tree. Each method takes a node as an argument
    // and prints the data in that node (via the visit method of the NodeData class), then recursively
    // performs the same traversal on the left and/or right child nodes.

    public static void preOrderTraversal(Node node) {
        if (node == null) {
            return;
        }
        node.data.visit();
        preOrderTraversal(node.left);
        preOrderTraversal(node.right);
    }

    public static void inOrderTraversal(Node node) {
        if (node == null) {
            return;
        }
        inOrderTraversal(node.left);
        node.data.visit();
        inOrderTraversal(node.right);
    }

    public static void postOrderTraversal(Node node) {
        if (node == null) {
            return;
        }
        postOrderTraversal(node.left);
        postOrderTraversal(node.right);
        node.data.visit();
    }


    // The findOrInsert method is a utility for inserting a new node into the tree or finding
    // an existing one. It takes as arguments a node (the root of the tree or subtree to search)
    // and a NodeData object (containing the data to find or insert). If the node is null,
    // it creates a new node containing the data. Otherwise, it compares the data to the data
    // in the node, and either calls itself recursively on the left or right child node or
    // returns the node if the data already exists in the tree.

    public static Node findOrInsert(Node node, NodeData d) {
        if (node == null) {
            Node newNode = new Node();
            newNode.data = d;
            newNode.left = null;
            newNode.right = null;
            return newNode;
        }

        int comparison = d.w.compareTo(node.data.w);
        if (comparison < 0) {
            node.left = findOrInsert(node.left, d);
        } else if (comparison > 0) {
            node.right = findOrInsert(node.right, d);
        }
        // If comparison == 0, the data already exists, so we don't need to insert it.
        return node;
    }


    // The find method is similar to findOrInsert, but it only searches for existing data in the tree.
    // If it finds the data, it returns the node containing the data; if it doesn't find the data, it returns null.

    public static Node find(Node node, NodeData d) {
        if (node == null) {
            return null;
        }

        // Compare the data of the given node with the data to be found
        int comparison = d.w.compareTo(node.data.w);
        if (comparison < 0) {
            // If the data to be found is less than the node's data, continue searching in the left subtree
            return find(node.left, d);
        } else if (comparison > 0) {
            // If the data to be found is greater than the node's data, continue searching in the right subtree
            return find(node.right, d);
        } else {
            // If the data to be found is equal to the node's data, return the node
            return node;
        }
    }
}
