public class Main {
    private static final String folder = "/C:/downs/Abb";
    private static Parser parser = new Parser();
    private static MyWriter writer = new MyWriter();

    public static void main(String[] args) {
        writer.writeToFolder(parser.parseDocument(), folder);
    }
}
