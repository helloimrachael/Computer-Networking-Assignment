public class MainServer {
    public static void main(String[] args) {
        if (args.length < 1) {
            System.out.println("ERR -arg[0]");
            return;
        }
        int porta = 0;
        try {
            porta = Integer.parseInt(args[0]);
        } catch (Exception e) {
            System.out.println("ERR -arg[0]");
            return;
        }
        long seed = 0;
        try {
            seed = Long.parseLong(args[1]);
        } catch (Exception e) {
            System.out.println("ERR -arg1");
            return;
        }
        ServerPing server = new ServerPing(porta, seed);
        server.start();
    }
}
