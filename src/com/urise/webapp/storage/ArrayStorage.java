package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage extends AbstractArrayStorage {
    protected int getSearchKey(String uuid) {
        for (int i = 0; i < size; i++) {
            if (storage[i].getUuid().equals(uuid)) {
                return i;
            }
        }
        return -1;
    }

    protected void insertResume(Resume r, int index) {
        storage[size] = r;
    }

    protected void deleteResume(String uuid, int index) {
        storage[index] = storage[size - 1];
        storage[size - 1] = null;
    }
}