package com.project.electronic.store.services;

import com.project.electronic.store.dto.PageableResponse;
import com.project.electronic.store.dto.UserDto;
import com.project.electronic.store.entities.Role;
import com.project.electronic.store.entities.User;
import com.project.electronic.store.repositories.RoleRepository;
import com.project.electronic.store.repositories.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.*;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.*;

@SpringBootTest
public class UserServiceTest
{

    @MockBean
    private UserRepository userRepository;

    @MockBean
    private RoleRepository roleRepository;

    @Autowired
    private UserService userService;

    User user;
    Role role;
    String roleId;
    @Autowired
    private ModelMapper mapper;

    @BeforeEach
    public void init(){

        role=Role.builder().roleId("abc").roleName("NORMAL").build();

        user=User.builder()
                .name("Prakhar")
                .email("prakhar@gmail.com")
                .about("This is testing create method")
                .gender("Male")
                .imageName("abc.png")
                .password("abcd")
                .roles(Set.of(role))
                .build();

        roleId="abc";

    }

    @Test
    public void createUserTest(){

        Mockito.when(userRepository.save(Mockito.any())).thenReturn(user);
        Mockito.when(roleRepository.findById(Mockito.anyString())).thenReturn(Optional.of(role));

        UserDto user1 =  userService.createUser(mapper.map(user, UserDto.class));
        System.out.println(user1.getName());
        Assertions.assertNotNull(user1);
        Assertions.assertEquals("Prakhar",user1.getName());

    }

    @Test
    public void updateUserTest(){

        String userId="nmnbnmbnbh";
        UserDto userDto=UserDto.builder()
                .name("Prakhar Srivastava")
                .about("This is updated user about details")
                .gender("Male")
                .imageName("xyz.png")
                .build();

        Mockito.when(userRepository.findById(Mockito.anyString())).thenReturn(Optional.of(user));
        Mockito.when(userRepository.save(Mockito.any())).thenReturn(user);

        UserDto updatedUser = userService.updateUser(userDto, userId);
        //UserDto updatedUser=mapper.map(user, UserDto.class);
        System.out.println(updatedUser.getName());
        System.out.println(updatedUser.getImageName());
        Assertions.assertNotNull(userDto);
        Assertions.assertEquals(userDto.getName(),updatedUser.getName(),"Name is not validated");

    }

    @Test
    public void deleteUserTest(){

        String userId="fjksjsfkhfsh";

        Mockito.when(userRepository.findById("fjksjsfkhfsh")).thenReturn(Optional.of(user));
        userService.deleteUser(userId);
        Mockito.verify(userRepository, Mockito.times(1)).delete(user);

    }

    @Test
    public void getAllUsersTest(){

        User user1=User.builder()
                .name("Ankit")
                .email("ankit@gmail.com")
                .about("This is testing create method")
                .gender("Male")
                .imageName("abc.png")
                .password("abcd")
                .roles(Set.of(role))
                .build();

        User user2=User.builder()
                .name("Uttam")
                .email("uttam@gmail.com")
                .about("This is testing create method")
                .gender("Male")
                .imageName("abc.png")
                .password("abcd")
                .roles(Set.of(role))
                .build();

        List<User> userList= Arrays.asList(user,user1,user2);
        Page<User> page=new PageImpl<>(userList);
        Mockito.when(userRepository.findAll((Pageable) Mockito.any())).thenReturn(page);
        PageableResponse<UserDto> allUser = userService.getAllUser(1,2,"name","asc");
        Assertions.assertEquals(3,allUser.getContent().size());

    }

    @Test
    public void getUserByIdTest(){

        String userId="abxvchdhdkjfhd";
        Mockito.when(userRepository.findById(userId)).thenReturn(Optional.of(user));

        UserDto userDto = userService.getUserById(userId);

        Assertions.assertNotNull(userDto);
        Assertions.assertEquals(user.getName(),userDto.getName(),"Name not matched !!");

    }

    @Test
    public void getUserByEmailTest(){

        String emailId="prakhar@gmail.com";

        Mockito.when(userRepository.findByEmail(emailId)).thenReturn(Optional.of(user));

        UserDto userDto = userService.getUserByEmail(emailId);

        Assertions.assertNotNull(userDto);
        Assertions.assertEquals(user.getEmail(),userDto.getEmail(),"Email not matched !!");

    }

    @Test
    public void searchUserTest(){

        User user1=User.builder()
                .name("Ankit kumar")
                .email("ankit@gmail.com")
                .about("This is testing create method")
                .gender("Male")
                .imageName("abc.png")
                .password("abcd")
                .roles(Set.of(role))
                .build();

        User user2=User.builder()
                .name("Pankaj kumar")
                .email("pankaj@gmail.com")
                .about("This is testing create method")
                .gender("Male")
                .imageName("abc.png")
                .password("abcd")
                .roles(Set.of(role))
                .build();

        User user3=User.builder()
                .name("Uttam kumar")
                .email("uttam@gmail.com")
                .about("This is testing create method")
                .gender("Male")
                .imageName("abc.png")
                .password("abcd")
                .roles(Set.of(role))
                .build();

        String keywords="kumar";

        Mockito.when(userRepository.findByNameContaining(keywords)).thenReturn(Arrays.asList(user,user1,user2,user3));

        List<UserDto> userDtos = userService.searchUser(keywords);

        Assertions.assertEquals(4,userDtos.size(),"Size not matched !!");

    }

    @Test
    public void findUserByEmailOptionalTest(){

        String email="prakharkumar@gmail.com";

        Mockito.when(userRepository.findByEmail(email)).thenReturn(Optional.of(user));

        Optional<User> userByEmailOptional = userService.findUserByEmailOptional(email);

        Assertions.assertTrue(userByEmailOptional.isPresent());
        User user1 = userByEmailOptional.get();
        Assertions.assertEquals(user.getEmail(),user1.getEmail(),"email does not matched !!");

    }

}
