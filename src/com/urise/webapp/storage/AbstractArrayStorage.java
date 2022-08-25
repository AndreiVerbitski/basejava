package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.Arrays;

public abstract class AbstractArrayStorage implements Storage {

    protected final static int STORAGE_LIMIT = 10000;
    protected final Resume[] storage = new Resume[STORAGE_LIMIT];
    protected int size = 0;

    public int size() {
        return size;
    }

    public Resume get(String uuid) {
        int index = getSearchKey(uuid);

        if (index >= 0) {
            return storage[index];
        } else {
            System.out.println("Данного резюме " + uuid + " нет в списке.");
        }
        return null;
    }

    public void save(Resume r) {
        insertResume(r);
    }

    public void delete(String uuid) {
        deleteResume(uuid);
    }

    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    public void update(Resume r) {
        int index = getSearchKey(r.getUuid());

        if (index >= 0) {
            storage[index] = r;
        } else {
            System.out.println("Данного резюме " + r.getUuid() + " нет в списке.");
        }
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    public Resume[] getAll() {
        return Arrays.copyOf(storage, size);
    }

    protected abstract int getSearchKey(String uuid);

    protected abstract Resume insertResume(Resume r);

    protected abstract String deleteResume(String uuid);
}