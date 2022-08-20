package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    final static protected int STORAGE_LIMIT = 10000;
    private final Resume[] storage = new Resume[STORAGE_LIMIT];
    private int size;

    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    public void update(Resume r) {
        int index = getSearchKey(r.getUuid());

        if (storage[index] == r) {
            storage[index] = r;
        } else {
            System.out.println("Данного резюме " + r.getUuid() + " нет в списке.");
        }
    }

    public void save(Resume r) {
        int index = getSearchKey(r.getUuid());

        if (size >= storage.length) {
            System.out.println("Список переполнен.");
        } else if (index >= 0 && storage[index] == r) {
            throw new ArrayIndexOutOfBoundsException("Данное резюме " + r.getUuid() + " уже есть в списке.");
        } else {
            storage[size] = r;
            size++;
        }
    }

    public Resume get(String uuid) {
        int index = getSearchKey(uuid);

        try {
            if (storage[index].getUuid().equals(uuid)) {
                return storage[index];
            } else {
                throw new ArrayIndexOutOfBoundsException("Данное резюме " + uuid + " уже есть в списке.");
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Данного резюме " + uuid + " нет в списке.");
        }
        return null;
    }

    public void delete(String uuid) {
        int index = getSearchKey(uuid);

        if (storage[index].equals(uuid)) {
            storage[index] = storage[size - 1];
            storage[size - 1] = null;
            size--;
        } else {
            System.out.println("Данного резюме " + uuid + " нет в списке.");
        }
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    public Resume[] getAll() {
        return Arrays.copyOf(storage, size);
    }

    public int size() {
        return size;
    }

    public int getSearchKey(String uuid) {
        for (int i = 0; i < size; i++) {
            if (storage[i].getUuid().equals(uuid)) {
                return i;
            }
        }
        return -1;
    }
}