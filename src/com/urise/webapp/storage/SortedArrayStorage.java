package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.Arrays;
import java.util.Comparator;

public class SortedArrayStorage extends AbstractArrayStorage {

    @Override
    protected int getSearchKey(String uuid) {
        Resume searchKey = new Resume();
        searchKey.setUuid(uuid);
        return Arrays.binarySearch(storage, 0, size, searchKey, Comparator.comparing(Resume::getUuid));
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