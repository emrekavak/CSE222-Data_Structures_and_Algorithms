package experimentlist;// EMRE KAVAK 151044085 HW2
import java.util.Iterator;


// NOT : ExperimentList classının add metodu , objeleri listeye sıralı ekler. O yuzden test sonucları farklı cıkabilir.

class ExperimentListIterator implements Iterator{   // for iterator
    protected Node current;
    public ExperimentListIterator(Node head){
        current = head;
    }
    @Override
    public boolean hasNext() { // Override for itetator
        if(current!=null ) return true;
        else return false;
    }
    @Override
    public Node next() {
        Node temp = new Node();
        temp = current;
        current = current.next;
        return temp;
    }
    public String toString(){
        return this.current.toString();
    }
}