package datastructures;

public class RemcoList<T> {
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
        Node<T> newEntry = new Node<>(value); // maak een nieuwe node aan met gegeven parameter
        if (head == null) {
            head = newEntry; // als de lijst leeg is, wordt het nieuwe element het hoofdelement
        } else {
            Node<T> huidigeEntry = head;
            while (huidigeEntry.next != null) {
                huidigeEntry = huidigeEntry.next; // doorloop de lijst tot het laatste element
            }
            huidigeEntry.next = newEntry; // voeg het nieuwe element toe aan het einde van de lijst
        }
        size++; // list size++ na toevoegen nieuw element
    }

    public boolean contains(T value) {
        Node<T> huidigeEntry = head; //eerste element, loopt door lijst in while
        while (huidigeEntry != null) { //loop tot element null is
            if (huidigeEntry.data.equals(value)) {
                return true; // check elk element; return true als er een match is
            }
            huidigeEntry = huidigeEntry.next; // geen return? check volgende element
        }
        return false; // not found, return false
    }

    public T get(T value) {
        Node<T> huidigeEntry = head;
        while (huidigeEntry != null) {
            if (huidigeEntry.data.equals(value)) {
                return huidigeEntry.data; // return data if found
            }
            huidigeEntry = huidigeEntry.next; // geen return? go next
        }
        return null; // return null zonder match
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

        Node<T> huidigeEntry = head;
        while (huidigeEntry.next != null) {
            if (huidigeEntry.next.data.equals(value)) {
                T data = huidigeEntry.next.data;
                huidigeEntry.next = huidigeEntry.next.next; // verwijder het element door de link te verbreken
                size--;
                return data;
            }
            huidigeEntry = huidigeEntry.next; // niet gevonden, check volgende element
        }

        return null; // return null als het element niet wordt gevonden
    }

    public Node<T> getHead() {
        return head;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        Node<T> huidigeEntry = head;
        while (huidigeEntry != null) {
            stringBuilder.append(huidigeEntry.data).append(" -> ");
            huidigeEntry = huidigeEntry.next;
        }
        stringBuilder.append("null");
        return stringBuilder.toString();
    }
}
