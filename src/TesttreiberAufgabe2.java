public class TesttreiberAufgabe2 {


    public void printTraversals(Node root) {
        System.out.print("The pre-order traversal is: ");
        Tree.preOrderTraversal(root);
        System.out.println();

        System.out.print("The in-order traversal is: ");
        Tree.inOrderTraversal(root);
        System.out.println();

        System.out.print("The post-order traversal is: ");
        Tree.postOrderTraversal(root);
        System.out.println();
    }
}