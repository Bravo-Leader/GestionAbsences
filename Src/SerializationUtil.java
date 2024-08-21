import java.io.*;

public class SerializationUtil {

    public static void saveObjectToFile(Object object, String filePath) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filePath))) {
            oos.writeObject(object);
        } catch (IOException e) {
            System.out.println("Erreur lors de la sauvegarde de l'objet.");
            e.printStackTrace();
        }
    }

    public static Object loadObjectFromFile(String filePath) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filePath))) {
            return ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Erreur lors du chargement de l'objet.");
            e.printStackTrace();
            return null;
        }
    }
}
