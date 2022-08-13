import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    Resume[] storage = new Resume[10000];

    void clear() {
        for (int i = 0; i < storage.length; i++) {
            if (storage[i] != null) {
                storage[i] = null;
            }
        }
    }

    void save(Resume r) {
        for (int i = 0; i < storage.length; i++) {
            if (storage[i] == null) {
                storage[i] = r;
                break;
            }
        }
    }

    Resume get(String uuid) {
        for (int i = 0; i < storage.length; i++) {
            if (storage[i] == null) {
                break;
            } else if (uuid.equals(storage[i].uuid)) {
                return storage[i];
            }
        }
        return null;
    }

    void delete(String uuid) {
        for (int i = 0; i < storage.length; i++) {
            if (storage[i] == null) {
                break;
            } else if (uuid.equals(storage[i].uuid)) {
                storage[i] = null;
            }
        }
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    Resume[] getAll() {
        int count = 0;
        for (int i = 0; i < storage.length; i++) {
            if (storage[i] != null) {
                count++;
            }
        }

        Resume[] destStorage = new Resume[count];
        count = 0;
        int len = 0;

        for (int i = 0; i < storage.length; i++) {
            if (storage[i] != null) {
                for (int j = i; j < storage.length; j++) {
                    if (storage[j] == null) {
                        break;
                    }
                    len++;
                }
                System.arraycopy(storage, i, destStorage, count, len);
                count++;
                len = 0;
            }
        }
        return destStorage;
    }

    int size() {
        int countSize = 0;
        for (int i = 0; i < storage.length; i++) {
            if (storage[i] != null) {
                countSize++;
            }
        }
        return countSize;
    }
}
