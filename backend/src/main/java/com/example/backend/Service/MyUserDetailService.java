package com.example.backend.Service;
import com.example.backend.Model.UserModel;
import com.example.backend.Repository.UserRepo;
import com.example.backend.Security.MyUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailService implements UserDetailsService
{
    @Autowired
    UserRepo repo;

    @Override
    public UserDetails loadUserByUsername(String emailId) throws UsernameNotFoundException
    {
        Optional<UserModel> user = repo.findByUserEmailId(emailId);
        user.orElseThrow(() -> new UsernameNotFoundException("Sorry User is Not Registered"));

        return (UserDetails) user.map(MyUserDetails::new).get();

    }
}
