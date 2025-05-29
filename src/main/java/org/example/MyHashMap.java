package org.example;

import java.util.Objects;

public class MyHashMap<K, V> {
    // Массив корзин (buckets)
    private Node<K,V>[] table;

    // Текущий размер хеш-таблицы
    private int size;

    // Начальная емкость таблицы
    private static final int DEFAULT_CAPACITY = 16;

    @SuppressWarnings("unchecked")
    public MyHashMap() {
        table = (Node<K,V>[]) new Node[DEFAULT_CAPACITY];
        size = 0;
    }


    static class Node<K,V> {
        final int hash;
        final K key;
        V value;
        MyHashMap.Node<K,V> next;

        Node(int hash, K key, V value, MyHashMap.Node<K,V> next) {
            this.hash = hash;
            this.key = key;
            this.value = value;
            this.next = next;
        }

    }

    public V put(K key, V value) {
        //Считаем хэшкод и номер ячейки для вставки
        int hash = (key == null) ? 0 : key.hashCode();
        int index = hash & (table.length - 1);
        Node<K, V> newNode = new Node<>(hash, key, value, null);

        var currentNode = table[index];

        //Если в ячейке ничего нет, то вставляем в нее
        if (currentNode == null) {
            table[index] = newNode;
            size++;
            return null;
        }

        while (currentNode != null) {

            //если текущий Node имеет такой же ключ, то переписать значение. Отправить обратно старое значение.
            if (currentNode.hash == hash && Objects.equals(currentNode.key, key)) {
                var oldValue = currentNode.value;
                currentNode.value = value;
                return oldValue;
            }

            //Если ссылка на следующий Node равна null, то выйти из цикла
            if (currentNode.next == null) {
                break;
            }
            currentNode = currentNode.next;
        }

        currentNode.next = newNode;
        size++;
        return null;
    }

    public V get(K key) {
        int hash = (key == null) ? 0 : key.hashCode();
        int index = hash & (table.length - 1);

        var currentNode = table[index];

        while (currentNode != null) {
            if (currentNode.hash == hash && (Objects.equals(currentNode.key, key))) {
                return currentNode.value;
            }
            currentNode = currentNode.next;
        }
        return null;
    }

    public V remove(K key) {
        int hash = (key == null) ? 0 : key.hashCode();
        int index = hash & (table.length - 1);

        Node<K, V> currentNode = table[index];
        Node<K, V> prev = null;

        while (currentNode != null) {
            if (currentNode.hash == hash && (Objects.equals(currentNode.key, key))) {
                if (prev == null) {
                    table[index] = currentNode.next;
                } else {
                    prev.next = currentNode.next;
                }
                size--;
                return currentNode.value;
            }
            prev = currentNode;
            currentNode = currentNode.next;
        }
        return null;
    }

    public int size() {
        return size;
    }

}
