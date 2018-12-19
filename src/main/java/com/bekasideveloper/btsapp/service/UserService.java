package com.bekasideveloper.btsapp.service;

import com.bekasideveloper.btsapp.model.Perusahaan;
import com.bekasideveloper.btsapp.model.User;

public interface UserService {
    public void createUser(User user, Perusahaan perusahaan);
    public void createAdmin();
    public User getUser(String userName);
}
