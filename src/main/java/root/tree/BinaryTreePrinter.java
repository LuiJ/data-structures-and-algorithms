package root.tree;

import java.util.*;

public class BinaryTreePrinter
{
    private static final double TREE_BASE = 2;
    private static final String VALUE_FORMAT = "  (%s)  ";

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
        LinkedList<List<Integer>> levelsOfTree = extractLevels(tree);
        LinkedList<String> formattedLevels = formatLevels(levelsOfTree);
        printLevels(formattedLevels);
    }

    private LinkedList<List<Integer>> extractLevels(BinaryTree<Integer> tree)
    {
        BinaryTree.Node<Integer> root = tree.getRoot();
        LinkedList<List<Integer>> levels = new LinkedList<>();
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

    private LinkedList<String> formatLevels(LinkedList<List<Integer>> levels)
    {
        LinkedList<String> formattedLevels = new LinkedList<>();
        Iterator<List<Integer>> iterator = levels.descendingIterator();
        String formattedWidestLevel = formatWidestLevel(iterator.next());
        int widestLevelLength = formattedWidestLevel.length();
        formattedLevels.add(formattedWidestLevel);
        while (iterator.hasNext())
        {
            String formattedLevel = formatLevel(iterator.next(), widestLevelLength);
            formattedLevels.add(formattedLevel);
        }
        return formattedLevels;
    }

    private String formatWidestLevel(List<Integer> level)
    {
        StringBuilder formattedLevel = new StringBuilder();
        level.forEach(value ->
        {
            formattedLevel.append(formatValue(value));
        });
        return formattedLevel.toString();
    }

    private String formatLevel(List<Integer> level, int widestLevelLength)
    {
        char[] formattedLevel = new char[widestLevelLength];
        int numberOfSection = level.size();
        int sectionLength = widestLevelLength / numberOfSection;
        int sectionStartPointer = 0;
        Iterator<Integer> iterator = level.iterator();
        while (iterator.hasNext())
        {
            String formattedValue = formatValue(iterator.next());
            int formattedValueLength = formattedValue.length();
            int valueStartPointer = sectionStartPointer + (sectionLength - formattedValueLength) / 2;
            int valueEndPointer = valueStartPointer + formattedValueLength;
            int i = 0;
            for (int p = valueStartPointer; p < valueEndPointer; p++)
            {
                formattedLevel[p] = formattedValue.charAt(i);
                i++;
            }
            sectionStartPointer += sectionLength;
        }
        return new String(formattedLevel);
    }

    private String formatValue(Integer value)
    {
        return String.format(VALUE_FORMAT, value == null ? "-" : value.toString());
    }

    private void printLevels(LinkedList<String> levels)
    {
        Iterator<String> iterator = levels.descendingIterator();
        while (iterator.hasNext())
        {
            System.out.println(iterator.next());
        }
    }
}
