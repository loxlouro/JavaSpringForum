package com.example.register2.services;

import com.example.register2.config.WebSecurityConfig;
import com.example.register2.exceptions.ProfileImageNotFoundException;
import com.example.register2.exceptions.UserAlreadyExistsException;
import com.example.register2.exceptions.UserNotFoundException;
import com.example.register2.models.ProfileImage;
import com.example.register2.models.Role;
import com.example.register2.models.UserAccount;
import com.example.register2.repo.ProfileImageRepository;
import com.example.register2.repo.RoleRepository;
import com.example.register2.repo.UserAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class UserAccountService implements UserDetailsService {
    @Autowired
    RoleRepository roleRepository;
    @Autowired
    UserAccountRepository userAccountRepository;
    @Autowired
    ProfileImageRepository profileImageRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        UserAccount userAccount = userAccountRepository.findByEmail(email);
        if (userAccount == null) {
            throw new UsernameNotFoundException("User not found");
        }
        return userAccount;
    }

    public boolean saveUserAccount(UserAccount userAccount) {
        try {
            UserAccount userAccountFromDB = userAccountRepository.findByEmail(userAccount.getEmail());

            if (userAccountFromDB != null) {
                throw new UserAlreadyExistsException("User already exists");
            }
            Role role = roleRepository.getById(1L);
            userAccount.setRoles(List.of(role));
            userAccount.setPassword(WebSecurityConfig.passwordEncoder()
                    .encode(userAccount.getPassword()));
            userAccountRepository.save(userAccount);
            return true;

        } catch (UserAlreadyExistsException e) {
            e.getMessage();
            return false;
        }
    }

    public boolean updateUserAccountProfile(UserAccount userAccount, MultipartFile multipartFile) {
        try {
            UserAccount userAccountFromDB = userAccountRepository.findById(userAccount.getId()).get();
            if (userAccountFromDB==null) {
                throw new UserNotFoundException("User not found");
            }
            ProfileImage profileImageFromDB= saveProfileImage(multipartFile, userAccountFromDB);
            profileImageRepository.save(profileImageFromDB);
            userAccountFromDB.setProfileImage(profileImageFromDB);
            userAccountFromDB.setProfileStatus(userAccount.getProfileStatus());
            userAccountFromDB.setAboutMe(userAccount.getAboutMe());
            userAccountFromDB.setFirstname(userAccount.getFirstname());
            userAccountFromDB.setLastname(userAccount.getLastname());
            userAccountRepository.save(userAccountFromDB);
            return true;

        } catch (UserNotFoundException e) {
            e.printStackTrace();
            return false;
        }
    }
    public ProfileImage saveProfileImage(MultipartFile multipartFile, UserAccount userAccount){
        try{
            ProfileImage profileImageFromDB=userAccount.getProfileImage();
            if(profileImageFromDB!=null){
                profileImageRepository.delete(profileImageFromDB);
            }
            return profileImageRepository.save(new ProfileImage(multipartFile, userAccount));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public UserAccount findById(Long id) throws UserNotFoundException {
        try {
            UserAccount temp = userAccountRepository.getById(id);
            if (temp != null) return temp;
            else throw new UserNotFoundException("User not found");
        } catch (UserNotFoundException e) {
            e.getMessage();
            return null;
        }
    }

    public UserAccount findByEmail(String email){
        try{
            UserAccount userAccountFromDB = userAccountRepository.findByEmail(email);
            if(userAccountFromDB!=null) return userAccountFromDB;
            else throw new UserNotFoundException("Email not found");

        }catch (UserNotFoundException e){
            return null;
        }
    }
    public ProfileImage findByUserAccount(UserAccount userAccount) throws UserNotFoundException {
        try {
            if (userAccount==null)throw new UserNotFoundException("User not found");
            ProfileImage profileImageFromDB = profileImageRepository.findByUserAccount(userAccount);
            if (profileImageFromDB != null) return profileImageFromDB;
            else return profileImageRepository.getById(1111L);

        } catch (Exception e) {
            e.getMessage();
            return null;
        }
    }
}
