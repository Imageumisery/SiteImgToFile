import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.util.Arrays;
import java.util.List;

public class MyWriter {

    long millis = System.currentTimeMillis();
    Date date = new Date(millis);

    public void writeToFolder(List<String> list, String folder) {
        String changedFolder = folder + "/" + date + "/";
        File newFolder = new File(changedFolder);
        if (!newFolder.exists()) {
            newFolder.mkdir();
        }
        list.forEach(s -> {
            try {
                getImage(changedFolder, s);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        Arrays.stream(newFolder.list()).forEach(System.out::println);
        System.out.println("All is Done");
    }

    private void getImage(String folder, String s) throws IOException {
        URL url = new URL(s);
        String fileName = folder + s.replaceAll("https://icdn.lenta.ru/images/", "").replaceAll("/", "");
        File file = new File(fileName);
        if (file.exists()) return;
        BufferedInputStream bis = new BufferedInputStream(url.openStream());
        FileOutputStream fos = new FileOutputStream(file);
        byte[] buffer = new byte[1024];
        int count;
        while ((count = bis.read(buffer, 0, 1024)) != -1) {
            fos.write(buffer, 0, count);
        }
        fos.close();
        bis.close();
    }
}
