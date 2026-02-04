package framework.testdata;

import framework.data.model.User;
import framework.config.Config;

public class UserFactory {

    public static User valid(){
        return new User(
                Config.get("login.username"),
                Config.get("login.password")
        );
    }

    public static User invalid(String invalidUsername, String invalidPassword){
        return new User(invalidUsername, invalidPassword);
    }

}
