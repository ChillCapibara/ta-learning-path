package com.szymon.ta.data;

import com.szymon.ta.model.User;
import com.szymon.ta.utils.Config;

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
