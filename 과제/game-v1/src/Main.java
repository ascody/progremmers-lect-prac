import com.flight.game.frame.MainFrame;
import com.flight.game.menu.Menu;
import com.flight.game.set.User;

public class Main {


    public static void main(String[] args) {

        User user = new User();
        Menu menu = new Menu( user );

        // 시나리오
        menu.init();

        while ( true ) {
            for ( int i = 0; i < 50; ++i ) System.out.println();
            int idx = menu.printMenu();
            switch ( idx ) {
                case 1:
                    new MainFrame();
                    break;
                case 2:
                    System.out.println("감사합니다!");
                    System.exit(0);
                    return;
            }

        }

    }

}
