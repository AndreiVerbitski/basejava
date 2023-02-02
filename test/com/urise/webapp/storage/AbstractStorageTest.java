package com.urise.webapp.storage;

import com.urise.webapp.exception.ExistStorageException;
import com.urise.webapp.exception.NotExistStorageException;
import com.urise.webapp.model.Resume;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public abstract class AbstractStorageTest {

    private final Storage storage;

    private final String UUID_NOT_EXIST = "dummy";
    private final Resume RESUME_NOT_EXIST = new Resume(UUID_NOT_EXIST);
    private static final String UUID_1 = "uuid1";
    private static final Resume RESUME_1 = new Resume(UUID_1);
    private static final String UUID_2 = "uuid2";
    private static final Resume RESUME_2 = new Resume(UUID_2);
    private static final String UUID_3 = "uuid3";
    private static final Resume RESUME_3 = new Resume(UUID_3);
    private static final String UUID_4 = "uuid4";
    private static final Resume RESUME_4 = new Resume(UUID_4);

    protected AbstractStorageTest(Storage storage) {
        this.storage = storage;
    }

    @BeforeEach
    void setUp()  {
        storage.clear();
        storage.save(RESUME_1);
        storage.save(RESUME_2);
        storage.save(RESUME_3);
    }

    @Test
    void size() {
        assertSize(3);
    }

    @Test
    void get() {
        assertGet(RESUME_1);
        assertGet(RESUME_2);
        assertGet(RESUME_3);
    }

    @Test
    void save() {
        storage.save(RESUME_4);
        assertGet(RESUME_4);
        assertSize(4);
    }

    @Test
    void delete() {
        storage.delete(UUID_1);
        assertSize(2);
        assertThrows(NotExistStorageException.class, () -> {assertGet(RESUME_1);});
    }

    @Test
    void clear() {
        storage.clear();
        assertSize(0);
    }

    @Test
    void update() {
        Resume newResume = new Resume(UUID_1);
        storage.update(newResume);
        assertSame(newResume, storage.get(UUID_1));
    }

    @Test
    void getAll() {
        Resume[] array = storage.getAll();
        assertEquals(storage.get(UUID_1), array[0]);
        assertEquals(storage.get(UUID_2), array[1]);
        assertEquals(storage.get(UUID_3), array[2]);
    }

    @Test
    void saveExist() {
        assertThrows(ExistStorageException.class, () -> {storage.save(RESUME_1);});
    }

    @Test
    void getNotExist() {
        assertThrows(NotExistStorageException.class, () -> {storage.get(UUID_NOT_EXIST);});
    }

    @Test
    void updateNotExist() {
        assertThrows(NotExistStorageException.class, () -> {storage.update(RESUME_NOT_EXIST);});
    }

    @Test
    void deleteNotExist() {
        assertThrows(NotExistStorageException.class, () -> {storage.delete(UUID_NOT_EXIST);});
    }

    private void assertGet(Resume r) {
        assertEquals(r, storage.get(r.getUuid()));
    }

    private void assertSize(int size) {
        assertEquals(size, storage.size());
    }
}