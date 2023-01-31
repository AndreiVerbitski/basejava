package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.ArrayList;
import java.util.List;

public class ListStorage extends AbstractStorage {

    protected List<Resume> storage = new ArrayList<>();

    @Override
    protected Integer getSearchKey(String uuid) {
        for (int i = 0; i < storage.size(); i++) {
            if (storage.get(i).getUuid().equals(uuid)) {
                return i;
            }
        }
        return null;
    }

    @Override
    protected void doUpdate(Resume r, Object searchKey) {
        storage.set((Integer)getSearchKey(r.getUuid()), r);
    }

    @Override
    protected void doSave(Resume r, Object searchKey) {
        storage.add((Integer)getSearchKey(r.getUuid()), r);
    }

    @Override
    protected Resume doGet(Object searchKey) {
        return storage.get((Integer)searchKey);
    }

    @Override
    protected void doDelete(Object searchKey) {
        storage.remove((Integer)searchKey);
    }

    @Override
    protected boolean isExist(Object searchKey) {
        return storage.contains(searchKey);
    }

    @Override
    public void clear() {}

    @Override
    public Resume[] getAll() {
        return new Resume[0];
    }

    @Override
    public int size() {
        return 0;
    }
}
