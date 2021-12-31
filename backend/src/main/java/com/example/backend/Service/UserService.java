package com.example.backend.Service;

import com.example.backend.Model.UserModel;
import com.example.backend.Repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService
{
    @Autowired
    UserRepo backEnd;

    public boolean addUser(UserModel user)
    {
        user.setUserPassword(new BCryptPasswordEncoder().encode(user.getUserPassword()));
        this.backEnd.save(user);
        return true;
    }

    public boolean updateUser(long uId, UserModel prev)
    {
        UserModel updated = this.backEnd.findById(uId).get();
        updated.setUserName(prev.getUserName());
        updated.setUserEmailId(prev.getUserEmailId());
        updated.setUserContact(prev.getUserContact());
        updated.setUserAge(prev.getUserAge());
        updated.setUserAddress(prev.getUserAddress());
        this.backEnd.save(updated);

        return true;
    }

    public String findMyUserIdandRole(String emailId)
    {
        return backEnd.findMyUserIdandRole(emailId);
    }

    public UserModel getMyProfile(long id)
    {
        return this.backEnd.findById(id).get();
    }

    public boolean ifSignupRequired(String emailId)
    {
        int n = backEnd.ifUserPresentorNot(emailId);

        if(n==0)
        {
            return true;
        }

        else
        {
            return false;
        }
    }
}
