import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    Resume[] storage = new Resume[10000];
    private int size;

    void clear() {
        int count = 0;
        for (int i = 0; i < size; i++) {
            if (storage[i] != null) {
                Arrays.fill(storage, i, i + 1, null);
            }
        }
        size = 0;
    }

    void save(Resume r) {
        for (int i = 0; i < storage.length; i++) {
            if (storage[i] == null) {
                storage[i] = r;
                size++;
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
            if (uuid.equals(storage[i].uuid)) {
                storage[i] = null;
                size--;
                break;
            }
        }

        for (int i = 0; i < storage.length; i++) {
            for (int j = 0; j < i; j++) {
                if (storage[j] == null && storage[j + 1] != null) {
                    storage[j] = storage[j + 1];
                    storage[j + 1] = null;
                }
            }
        }
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    Resume[] getAll() {
        int count = 0;
        for (int i = 0; i < size; i++) {
            if (storage[i] == null) {
                break;
            } else {
                count++;
            }
        }
        return Arrays.copyOfRange(storage, 0, count);
    }

    int size() {
        return size;
    }
}
