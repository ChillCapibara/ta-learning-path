package framework.data;

import framework.model.User;
import framework.config.Config;

public class Users {

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
