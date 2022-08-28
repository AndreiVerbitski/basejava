package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.Arrays;

public class SortedArrayStorage extends AbstractArrayStorage {

    @Override
    protected int getSearchKey(String uuid) {
        for (int i = 0; i < size; i++) {
            for (int j = 0; i < size; j++) {
                if (storage[j].getUuid().compareTo(storage[j + 1].getUuid()) > 0) {
                    Resume temp = storage[i];
                    storage[i] = storage[j];
                    storage[j] = temp;
                }
            }
        }
        Resume searchKey = new Resume();
        searchKey.setUuid(uuid);
        return Arrays.binarySearch(storage, 0, size, searchKey);
    }

    @Override
    protected void insertResume(Resume r, int index) {
        int insertionKey = -index - 1;
        System.arraycopy(storage, insertionKey, storage, insertionKey + 1, size - insertionKey);
        storage[insertionKey] = r;
    }

    @Override
    protected void deleteResume(String uuid, int index) {
        System.arraycopy(storage, index + 1, storage, index, size - index - 1);
    }
}