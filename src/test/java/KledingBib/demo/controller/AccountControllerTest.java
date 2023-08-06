//package KledingBib.demo.controller;
//
//
//import KledingBib.demo.dto.AccountDto;
//import KledingBib.demo.filter.JwtRequestFilter;
//import KledingBib.demo.service.AccountService;
//import com.fasterxml.jackson.core.JsonProcessingException;
//import com.fasterxml.jackson.databind.ObjectMapper;
//
//
//import jakarta.servlet.http.HttpServlet;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.http.MediaType;
//import org.springframework.test.context.junit.jupiter.SpringExtension;
//import org.springframework.test.web.servlet.MockMvc;
//
//import java.util.List;
//import static org.mockito.ArgumentMatchers.anyString;
//import static org.mockito.BDDMockito.given;
//import static org.mockito.Mockito.verify;
//
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//@ExtendWith(SpringExtension.class)
//@WebMvcTest(AccountController.class)
//@AutoConfigureMockMvc(addFilters = false)
//class AccountControllerTest {
//
//    @MockBean
//    private AccountService accountService;
//    @MockBean
//    HttpServlet httpServlet;
//    @MockBean
//    private JwtRequestFilter jwtRequestFilter;
//
//    @Autowired
//    private MockMvc mockMvc;
//
//    AccountDto accountDto1;
//    AccountDto accountDto2;
//
//
//    @BeforeEach
//    void setUp() {
//
//        accountDto1 = new AccountDto("userInfo", "subscriptionInfo");
//        accountDto2 = new AccountDto("userInfo", "subscriptionInfo");
//
//    }
//
//    @Test
//    public void getAllAccountsTest() throws Exception {
//        // Arrange
//        accountDto1 = new AccountDto("userInfo", "subscriptionInfo");
//        accountDto2 = new AccountDto("userInfo", "subscriptionInfo");
//
//        given(accountService.getAllAccounts()).willReturn(List.of(accountDto1, accountDto2));
//
//        // Act and Assert
//        mockMvc.perform(get("/accounts"))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$[0].userInfo").value("user1"))
//                .andExpect(jsonPath("$[1].userInfo").value("user2"));
//        verify(accountService).getAllAccounts();
//    }
//
//    @Test
//    void getAccount() throws Exception {
//        given(accountService.getAccount(Long.valueOf(anyString()))).willReturn(accountDto2);
//
//        mockMvc.perform(get("/accounts/user2"))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("userInfo").value("user2"))
//                .andExpect(jsonPath("subscriptionInfo").value("annual"));
//
//        verify(accountService).getAccount("userInfo", "subscriptionInfo");
//    }
//
//    @Test
//    void createAccount() throws Exception {
//        given(accountService.createAccount(accountDto2)).willReturn(Long.valueOf("Account created!"));
//
//        mockMvc.perform(post("/accounts")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(asJsonString(accountDto2)))
//                .andExpect(status().isCreated());
//    }
//
//    @Test
//    void deleteAccount() throws Exception{
//        String Long = "testAccount";
//
//        mockMvc.perform(delete("/accounts/testAccount"))
//                .andExpect(status().isOk());
//
//        verify(accountService).deleteById(java.lang.Long.valueOf(Long));
//    }
//
//    public static String asJsonString(final Object obj) {
//        try {
//            return new ObjectMapper().writeValueAsString(obj);
//        } catch (JsonProcessingException e) {
//            throw new RuntimeException(e);
//        }
//    }
//}