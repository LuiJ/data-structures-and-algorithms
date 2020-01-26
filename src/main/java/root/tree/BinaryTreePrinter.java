package root.tree;

import java.util.*;
import java.util.stream.Collectors;

public class BinaryTreePrinter
{
    private static final String VALUE_FORMAT = "( %s )";
    private static final double TREE_BASE = 2;

    public static void main(String[] args)
    {
        BinaryTree<Integer> tree = new BinaryTree<>();
        tree.addNode(10);
        tree.addNode(20);
        tree.addNode(5);
        tree.addNode(7);
        tree.addNode(3);
        tree.addNode(13);
        tree.addNode(21);

        new BinaryTreePrinter().print(tree);
    }

    public void print(BinaryTree<Integer> tree)
    {
        List<List<Integer>> levelsOfTree = extractLevels(tree);
        List<String> formatedLevels = formatLevels(levelsOfTree);
        formatedLevels.forEach(System.out::println);
    }

    private List<List<Integer>> extractLevels(BinaryTree<Integer> tree)
    {
        BinaryTree.Node<Integer> root = tree.getRoot();
        List<List<Integer>> levels = new LinkedList<>();
        levels.add(Arrays.asList(root.getValue()));
        Queue<BinaryTree.Node<Integer>> queue = new LinkedList<>();
        queue.add(root);
        double levelNumber = 1;
        double levelSize;
        while (!queue.isEmpty())
        {
            List<Integer> level = new LinkedList<>();
            levelSize = Math.pow(TREE_BASE, levelNumber);
            while (level.size() < levelSize)
            {
                if (queue.isEmpty()) break;
                BinaryTree.Node<Integer> node = queue.remove();
                processNode(node, level, queue);
            }
            if (level.stream().allMatch(Objects::isNull))
            {
                break;
            }
            else
            {
                levels.add(level);
                levelNumber++;
            }
        }
        return levels;
    }

    private void processNode(BinaryTree.Node<Integer> node, List<Integer> level, Queue<BinaryTree.Node<Integer>> queue)
    {
        if (node == null)
        {
            level.add(null);
            level.add(null);
            queue.add(null);
            queue.add(null);
        }
        else
        {
            processChildNode(node.getLeft(), level, queue);
            processChildNode(node.getRight(), level, queue);
        }
    }

    private void processChildNode(BinaryTree.Node<Integer> node, List<Integer> level, Queue<BinaryTree.Node<Integer>> queue)
    {
        if (node == null)
        {
            level.add(null);
            queue.add(null);
        }
        else
        {
            level.add(node.getValue());
            queue.add(node);
        }
    }

    private List<String> formatLevels(List<List<Integer>> levels)
    {
        List<String> formatedLevels = new ArrayList<>();
        // TODO: Impl
        return formatedLevels;
    }
}
