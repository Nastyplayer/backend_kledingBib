package KledingBib.demo.service;

import KledingBib.demo.dto.UserDto;
import KledingBib.demo.exceptions.RecordNotFoundException;
import KledingBib.demo.models.Authority;
import KledingBib.demo.models.User;
import KledingBib.demo.repository.UserRepository;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/*moest hier niet een annotatie? DONE!!!*/
@Service
public class UserService {
    /*inject de juiste repository  DONE!!!*/
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }


    public List<UserDto> getUsers() {
        List<UserDto> collection = new ArrayList<>();
        List<User> list = userRepository.findAll();
        for (User user : list) {
            collection.add(fromUser(user));
        }
        return collection;
    }
    public UserDto getUser(String username) {
        return userRepository.findById(username)
                .map(this::fromUser)
                .orElseThrow(() -> new UsernameNotFoundException(username));
    }

//    public UserDto getUser(String username) {
//        UserDto userDto;  // = new UserDto();
//        Optional<User> user = userRepository.findById(username);
//        if (user.isPresent()) {
//            userDto = fromUser(user.get());
//        } else {
//            throw new UsernameNotFoundException(username);
//        }
//        return userDto;
//    }
//
//    public boolean userExists(String username) {
//        return userRepository.existsById(username);
//}

    public String createUser(UserDto userDto) {
      //  String randomString = RandomStringGenerator.generateAlphaNumeric(20);
     //  userDto.setApikey(randomString);
        userDto.setPassword(passwordEncoder.encode(userDto.getPassword()));
        User newUser = userRepository.save(toUser(userDto));
        newUser = userRepository.save(newUser);
        return newUser.getUsername();
    }

    public void deleteUser(String username) {
        userRepository.deleteById(username);
    }

    public void updateUser(String username, UserDto newUser) {
        if (!userRepository.existsById(username)) throw new RecordNotFoundException();
        User user = userRepository.findById(username).get();

        user.setPassword(newUser.getPassword());
//         if (newUser.getEnabled() != null) {
//             user.setEnabled(newUser.getEnabled());
//         }

        userRepository.save(user);
    }

    public void patchUser(String username, UserDto changeUser) {
        if (!userRepository.existsById(username)) throw new UsernameNotFoundException(username);
        User user = userRepository.findById(username).get();
        if (changeUser.getPassword() != "") {
            user.setPassword(passwordEncoder.encode(changeUser.getPassword()));
        }
//        if (changeUser.getEnabled() != null) {
//            user.setEnabled(changeUser.getEnabled());
//        }
//
//        ///// apikey  blijft
        if (changeUser.getEmail() != "") {
            user.setEmail(changeUser.getEmail());
        }
        userRepository.save(user);
    }



    public Set<Authority> getAuthorities(String username) {
        if (!userRepository.existsById(username)) throw new UsernameNotFoundException(username);
        User user = userRepository.findById(username).get();
        UserDto userDto = fromUser(user);
        return userDto.getAuthorities();
    }

    public void addAuthority(String username, String authority) {

        if (!userRepository.existsById(username)) throw new UsernameNotFoundException(username);
        User user = userRepository.findById(username).get();
        user.addAuthority(new Authority(username, authority));
        userRepository.save(user);
    }

    public void removeAuthority(String username, String authority) {
        if (!userRepository.existsById(username)) throw new UsernameNotFoundException(username);
        User user = userRepository.findById(username).get();
        Authority authorityToRemove = user.getAuthorities().stream().filter((a) -> a.getAuthority().equalsIgnoreCase(authority)).findAny().get();
        user.removeAuthority(authorityToRemove);
        userRepository.save(user);
    }

    public UserDto fromUser(User user){

        var userDto = new UserDto();
        userDto.order = user.getOrder();
        userDto.item = user.getItem();
        userDto.username = user.getUsername();
        userDto.password = user.getPassword();
//        userDto.enabled = user.isEnabled();
//        userDto.apikey = user.getApikey();
     userDto.email = user.getEmail();
//        userDto.comment = user.getComment();
        userDto.authorities = user.getAuthorities();
        if(userDto.getAccount()== null) {
            userDto.setAccount(user.getAccount());
        }

        return userDto;
    }

    public User toUser(UserDto userDto) {

        var user = new User();
        user.setOrder(userDto.getOrder());
        user.setItem(userDto.getItem());
        user.setUsername(userDto.getUsername());
        user.setPassword(userDto.getPassword());
        if(userDto.getAccount()!=null) {
            user.setAccount(userDto.getAccount());
        }


//        user.setApikey(userDto.getApikey());
         user.setEmail(userDto.getEmail());
//        user.setComment(userDto.getComment());
//        Boolean enabled = userDto.getEnabled();
//        boolean enabledValue = Objects.requireNonNullElse(enabled, false).booleanValue();
//        user.setEnabled(enabledValue);

        return user;
    }



}
