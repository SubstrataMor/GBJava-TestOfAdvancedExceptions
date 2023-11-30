package outputService;
import user.User;

import java.io.*;
import java.nio.file.FileSystemException;

public interface SaveDataFile {

    void saveData(User user) throws FileSystemException, RuntimeException;
}
