package dao;

import dm.User;

import java.io.*;
import java.util.HashMap;

public class UserDaoImpl implements IDao<Long, User> {

    public static final String FILE_PATH = "C:\\Users\\97254\\Desktop\\TheBestRecipts.project 3\\TheBestRecipts.project 3\\TheBestRecipts.project 2\\Resorce\\userdatasource.txt";

    public UserDaoImpl() {
        try {
            boolean emptyData = emptyDB();
            if (emptyData) {
                saveUsersToFile(new HashMap<>()); // Initialize with an empty HashMap if file is empty
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(User entity) {
        HashMap<Long, User> users = readUsersFromFile();
        if (users != null) {
            users.remove(entity.getId());
            saveUsersToFile(users);
        }
    }

    private void saveUsersToFile(HashMap<Long, User> users) {
        try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(FILE_PATH))) {
            objectOutputStream.writeObject(users);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public HashMap<Long, User> readUsersFromFile() {
        try (ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(FILE_PATH))) {
            return (HashMap<Long, User>) objectInputStream.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public User find(Long id) throws IllegalArgumentException {
        HashMap<Long, User> users = readUsersFromFile();
        if (users != null) {
            return users.get(id);
        }
        return null;
    }

    @Override
    public boolean save(User user) throws IllegalArgumentException {
        HashMap<Long, User> users = readUsersFromFile();
        if (users != null) {
            users.put(user.getId(), user);
            saveUsersToFile(users);
            return true;
        }
        return false;
    }

    private boolean emptyDB() throws IOException, ClassNotFoundException {
        File file = new File(FILE_PATH);
        return !file.exists() || file.length() == 0;
    }
}
