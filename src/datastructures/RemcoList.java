package datastructures;

public class RemcoList<T> {
    private Node<T> head; // begin van de lijst
    private int size; // counter om grootte bij te houden

    public RemcoList() {
        this.head = null; // initieel lege lijst, dus 'head' is null
        this.size = 0; // initieel 0
    }

    public boolean isEmpty() {
        return size == 0; // return true als size 0 is
    }

    public int size() {
        return size; // return het aantal elementen in de lijst
    }

    public void add(T value) {
        Node<T> newNode = new Node<>(value); // maak een nieuwe node aan met gegeven parameter
        if (head == null) {
            head = newNode; // als de lijst leeg is, wordt het nieuwe element het hoofdelement
        } else {
            Node<T> current = head;
            while (current.next != null) {
                current = current.next; // doorloop de lijst tot het laatste element
            }
            current.next = newNode; // voeg het nieuwe element toe aan het einde van de lijst
        }
        size++; // list size++ na toevoegen nieuw element
    }

    public boolean contains(T value) {
        Node<T> current = head; //eerste element, loopt door lijst in while
        while (current != null) { //loop tot element null is
            if (current.data.equals(value)) {
                return true; // check elk element; return true als er een match is
            }
            current = current.next; // geen return? check volgende element
        }
        return false; // not found, return false
    }

    public T get(T value) {
        Node<T> current = head;
        while (current != null) {
            if (current.data.equals(value)) {
                return current.data; // return data if found
            }
            current = current.next; // geen return? go next
        }
        return null; // return null als geen match
    }

    public T remove(T value) {
        if (head == null) {
            return null; // geen eerste element? geen lijst, return null
        }

        if (head.data.equals(value)) {
            T data = head.data;
            head = head.next; // als head to be deleted is zet head naar het volgend element
            size--;
            return data;
        }

        Node<T> current = head;
        while (current.next != null) {
            if (current.next.data.equals(value)) {
                T data = current.next.data;
                current.next = current.next.next; // verwijder het element door de link te verbreken
                size--;
                return data;
            }
            current = current.next; // niet gevonden, check volgende element
        }

        return null; // return null als het element niet wordt gevonden
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        Node<T> current = head;
        while (current != null) {
            sb.append(current.data).append(" -> ");
            current = current.next;
        }
        sb.append("null");
        return sb.toString();
    }

    public static class Node<T> {
        private final T data; // de generieke data
        private Node<T> next; // ref naar next element

        public Node(T data) {
            this.data = data;
            this.next = null;
        }

        public T getData() {
            return data;
        }

        public Node<T> getNext() {
            return next;
        }
    }

    public Node<T> getHead() {
        return head;
    }
}
