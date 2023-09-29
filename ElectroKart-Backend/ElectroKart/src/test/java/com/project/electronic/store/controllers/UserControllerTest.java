package com.project.electronic.store.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.electronic.store.dto.PageableResponse;
import com.project.electronic.store.dto.UserDto;
import com.project.electronic.store.entities.Role;
import com.project.electronic.store.entities.User;
import com.project.electronic.store.services.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Arrays;
import java.util.Set;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerTest {

    @MockBean
    private UserService userService;

    private Role role;

    private User user;

    @Autowired
    private ModelMapper mapper;

    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    public void init(){

        role= Role.builder().roleId("abc").roleName("NORMAL").build();

        user= User.builder()
                .name("Prakhar")
                .email("prakhar@gmail.com")
                .about("This is testing create method")
                .gender("Male")
                .imageName("abc.png")
                .password("abcd")
                .roles(Set.of(role))
                .build();

    }

    @Test
    public void createUserTest() throws Exception {

        UserDto dto = mapper.map(user, UserDto.class);

        Mockito.when(userService.createUser(Mockito.any())).thenReturn(dto);

        this.mockMvc.perform(MockMvcRequestBuilders.post("/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(convertObjectToJsonString(user))
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name").exists());

    }

    @Test
    public void updateUserTest() throws Exception {

        String userId="123";
        UserDto dto = this.mapper.map(user, UserDto.class);

        Mockito.when(userService.updateUser(Mockito.any(),Mockito.anyString())).thenReturn(dto);

        this.mockMvc.perform(
                MockMvcRequestBuilders.put("/users/"+userId)
                        .header(HttpHeaders.AUTHORIZATION,"Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJwYW5rYWpAZGV2LmluIiwiaWF0IjoxNjk1NzYwNzMxLCJleHAiOjE2OTU3Nzg3MzF9.mmnm2BxFOVK6P1_jSK9dMl2ABx1GnWSHVyl368tMwJcvQOeqcYPKJY_4svKpfQuxejOmO_wrTa-uDEupgy3azw")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(convertObjectToJsonString(user))
                        .accept(MediaType.APPLICATION_JSON)
        )
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").exists());


    }

    private String convertObjectToJsonString(Object user) {

        try{
            return new ObjectMapper().writeValueAsString(user);
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }

    }

    @Test
    public void getAllUsersTest() throws Exception {

        UserDto object1 = UserDto.builder().name("prakhar").email("prakhar@gmail.com").password("prakhar").about("Testing").build();
        UserDto object2 = UserDto.builder().name("amit").email("prakhar@gmail.com").password("prakhar").about("Testing").build();
        UserDto object3 = UserDto.builder().name("sumit").email("prakhar@gmail.com").password("prakhar").about("Testing").build();
        UserDto object4 = UserDto.builder().name("ankit").email("prakhar@gmail.com").password("prakhar").about("Testing").build();


        PageableResponse<UserDto> pageableResponse=new PageableResponse<>();
        pageableResponse.setContent(Arrays.asList(
                object1,object2,object3,object4
        ));
        pageableResponse.setLastPage(false);
        pageableResponse.setPageSize(10);
        pageableResponse.setTotalPages(100);
        pageableResponse.setTotalElements(1000);
        Mockito.when(userService.getAllUser(Mockito.anyInt(),Mockito.anyInt(),Mockito.anyString(),Mockito.anyString())).thenReturn(pageableResponse);

        this.mockMvc.perform(MockMvcRequestBuilders.get("/users")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)

        )
                .andDo(print())
                .andExpect(status().isOk());

    }

}
