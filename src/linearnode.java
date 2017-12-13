public class linearnode<E>
{
    private linearnode<E> next;
    private E element;
    
    /**
     * Creates an empty node.
     */
    public linearnode()
    {
        next = null;
        element = null;
    }
    
    /**
     * Creates a node storing the specified element.
     *
     * @param elem  the element to be stored within the new node
     */
    public linearnode (E elem)
    {
        next = null;
        element = elem;
    }
    
    /**
     * Returns the node that follows this one.
     *
     * @return  the node that follows the current one
     */
    public linearnode<E> getNext()
    {
        return next;
    }
    
    /**
     * Sets the node that follows this one.
     *
     * @param node  the node to be set to follow the current one
     */
    public void setNext (linearnode<E> node)
    {
        next = node;
    }
    
    /**
     * Returns the element stored in this node.
     *
     * @return  the element stored in this node
     */
    public E getElement()
    {
        return element;
    }
    
    /**
     * Sets the element stored in this node.
     *
     * @param elem  the element to be stored in this node
     */
    public void setElement (E elem)
    {
        element = elem;
    }
}