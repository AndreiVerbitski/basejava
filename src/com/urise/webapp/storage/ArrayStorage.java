package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage extends AbstractArrayStorage {
    public void delete(String uuid) {
        deleteResume(uuid);
    }

    public void save(Resume r) {
        insertResume(r);
    }

    protected int getSearchKey(String uuid) {
        for (int i = 0; i < size; i++) {
            if (storage[i].getUuid().equals(uuid)) {
                return i;
            }
        }
        return -1;
    }

    protected Resume insertResume(Resume r) {
        int index = getSearchKey(r.getUuid());

        if (size >= storage.length) {
            System.out.println("Список переполнен.");
        } else if (index >= 0) {
            System.out.println("Данное резюме " + r.getUuid() + " уже есть в списке.");
        } else {
            storage[size] = r;
            size++;
            return storage[size];
        }
        return null;
    }

    protected String deleteResume(String uuid) {
        int index = getSearchKey(uuid);

        if (index >= 0) {
            storage[index] = storage[size - 1];
            storage[size - 1] = null;
            size--;
        } else {
            System.out.println("Данного резюме " + uuid + " нет в списке.");
        }
        return "";
    }
}