
public class MainClient {
    public static void main(String[] args) {
        if (args.length < 2) {
            System.out.println("ERR -arg[0] || ERR -arg[1]");
            return;
        }
        int porta = 0;
        try {
            porta = Integer.parseInt(args[1]);
        } catch (Exception e) {
            System.out.println("ERR -arg[1]");
            return;
        }
        ClientPing client = new ClientPing(porta, args[0], 2000);
        client.start();
    }

}