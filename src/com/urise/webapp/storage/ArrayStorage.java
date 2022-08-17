package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    Resume[] storage = new Resume[10000];
    private int size;

    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    public void update(Resume r) {
        try {
            storage[includeResume(r.getUuid())] = r;
        } catch (NumberFormatException e) {
            System.out.println("Такого резюме " + r.getUuid() + " нет в нашем списке.");
        }
    }

    public void save(Resume r) {
        if (size <= storage.length) {
            try {
                boolean includeResume = false;
                for (int i = 0; i < size; i++) {
                    if (storage[i] == r) {
                        includeResume = true;
                        break;
                    }
                }
                if (!includeResume) {
                    storage[size] = r;
                    size++;
                } else throw new IllegalStateException("Резюме " + r.getUuid() + " уже есть в списке.");
            } catch (IllegalStateException e) {
                System.out.println("Резюме " + r.getUuid() + " уже есть в списке.");
            }
        } else System.out.println("Массив переполнен");
    }

    public Resume get(String uuid) {
        try {
            return storage[includeResume(uuid)];
        } catch (NumberFormatException e) {
            System.out.println("Такого резюме " + uuid + " нет в нашем списке.");
        }
        return null;
    }

    public void delete(String uuid) {
        try {
            storage[includeResume(uuid)] = storage[size - 1];
            size--;
        } catch (NumberFormatException e) {
            System.out.println("Такого резюме " + uuid + " нет в нашем списке.");
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

    public int includeResume(String uuid) {
        for (int i = 0; i < size; i++) {
            if (uuid.equals(storage[i].getUuid())) {
                return i;
            } else throw new NumberFormatException("Такого резюме " + uuid + " нет в нашем списке.");
        }
        return 0;
    }
}
