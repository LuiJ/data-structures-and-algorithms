package root.tree;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

public class BinaryTree<V extends Comparable<V>>
{
    @Getter
    private Node<V> root;

    public void addNode(V value)
    {
        if (root == null)
        {
            root = new Node<>(value);
        }
        else
        {
            root.addChildNode(value);
        }
    }

    @RequiredArgsConstructor
    @Getter
    public static class Node<V extends Comparable<V>>
    {
        private final V value;
        private Node<V> left;
        private Node<V> right;

        private void addChildNode(V childNodeValue)
        {
            if (childNodeValue.compareTo(this.value) > 0)
            {
                if (right == null)
                {
                    right = new Node<>(childNodeValue);
                }
                else
                {
                    right.addChildNode(childNodeValue);
                }
            }
            else
            {
                if (left == null)
                {
                    left = new Node<>(childNodeValue);
                }
                else
                {
                    left.addChildNode(childNodeValue);
                }
            }
        }
    }

}
